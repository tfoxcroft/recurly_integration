package za.co.trf.recurly.api.rest;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import za.co.trf.recurly.KeyProvider;
import za.co.trf.recurly.api.rest.dto.request.AccountChangeStateRequest;
import za.co.trf.recurly.api.rest.dto.request.SubscriptionChangeStateRequest;
import za.co.trf.recurly.api.rest.dto.request.SubscriptionUpdateRequest;
import za.co.trf.recurly.api.rest.dto.response.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Configures Spring RestTemplate and provides bases REST methods to interact with Recurly's REST API
 */
public class RecurlyRestTemplate {

    protected final Logger log = Logger.getLogger(getClass());

    private final RestTemplate restTemplate;

    public RecurlyRestTemplate(KeyProvider keyProvider) {
        this(keyProvider.getKey());
    }

    public RecurlyRestTemplate(String apiKey) {
        DefaultHttpClient client = new DefaultHttpClient();
        Credentials credentials = new UsernamePasswordCredentials(apiKey, "");
        client.getCredentialsProvider().setCredentials(AuthScope.ANY, credentials);

        restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
        restTemplate.setMessageConverters(buildMessageConverters());
    }

    /**
     * Execute the HTTP method to the given URI template, writing the given request entity to the request, and
     * returns the response as the type provide
     * @param uri the URI template
     * @param params map of the parameter names and values to substituted in the given URI template
     * @param requestBody the entity to be included as the request body
     * @param resultType the response body class
     * @param httpMethod the HttpMethod to execute
     * @param <B> the request body type
     * @param <R> the response body type
     * @return the response body
     */
    public <B, R> R exchangeXml(String uri, Map<String, String> params, B requestBody,
                                Class<R> resultType, HttpMethod httpMethod) {
        return exchangeXml(uri, params, requestBody, resultType, httpMethod, HttpStatus.OK);
    }

    /**
     * Execute the HTTP method to the given URI template, writing the given request entity to the request, and
     * returns the response as the type provide
     * @param uri the URI template
     * @param params map of the parameter names and values to substituted in the given URI template
     * @param requestBody the entity to be included as the request body
     * @param resultType the response body class
     * @param httpMethod the HttpMethod to execute
     * @param expectedHttpStatus the HttpStatus expected
     * @param <B> the request body type
     * @param <R> the response body type
     * @return the response body
     */
    public <B, R> R exchangeXml(String uri, Map<String, String> params, B requestBody,
                                Class<R> resultType, HttpMethod httpMethod, HttpStatus expectedHttpStatus) {
        List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
        acceptableMediaTypes.add(MediaType.APPLICATION_XML);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);

        HttpEntity httpEntity = requestBody != null
                ? new HttpEntity<B>(requestBody, headers)
                : new HttpEntity(headers);

        log.debug("");

        if (params == null) {
            params = Collections.emptyMap();
        }

        try {
            ResponseEntity<R> response = restTemplate.exchange(uri, httpMethod, httpEntity, resultType, params);

            if (response.getStatusCode() != expectedHttpStatus) {
                throw new RecurlyAPIException("Unexpected HttpStatus " +
                        response.getStatusCode(), new RuntimeException(response.toString()));
            }
            log.debug("RESPONSE = " + response.getStatusCode() + " -> " + response.getBody());

            return response.getBody();
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage() + ". Response body = " + e.getResponseBodyAsString(), e);
            throw new RecurlyAPIException(e.getMessage(), e);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RecurlyAPIException(e.getMessage(), e);
        }

    }

    private List<HttpMessageConverter<?>> buildMessageConverters() {
        Jaxb2Marshaller marshaller = buildMarshaller();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MarshallingHttpMessageConverter(marshaller, marshaller));
        messageConverters.add(new FormHttpMessageConverter());

        return messageConverters;
    }

    private Jaxb2Marshaller buildMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();

        marshaller.setClassesToBeBound(
                AccountChangeStateRequest.class,
                SubscriptionChangeStateRequest.class,
                SubscriptionUpdateRequest.class,
                Account.class,
                AccountList.class,
                Plan.class,
                Subscription.class,
                SubscriptionList.class);

        return marshaller;
    }

}