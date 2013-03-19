package za.co.trf.recurly.api.rest;

import org.springframework.http.HttpMethod;
import za.co.trf.recurly.api.rest.dto.response.Subscription;

import java.util.HashMap;
import java.util.Map;

public class RecurlyJSResultServiceImpl extends RecurlyWebServiceBase implements RecurlyJSResultService {

    @Override
    public Subscription fetchSubscriptionResult(String resultToken) {
        log.debug("Fetching Recurly JS Subscription Result");
        return fetchResult(resultToken, Subscription.class);
    }

    private <T> T fetchResult(String resultToken, Class<T> resultType) {
        if (resultToken == null || resultToken.isEmpty()) {
            throw new RecurlyAPIException(new IllegalArgumentException("resultToken cannot be null or empty"));
        }

        String uri = RECURLY_API_BASE_URL + "recurly_js/result/{resultToken}";
        log.debug("Fetching Recurly JS Result");

        Map<String, String> params = new HashMap<String, String>();
        params.put("resultToken", resultToken);
        return recurlyRestTemplate.exchangeXml(uri, params, null, resultType, HttpMethod.GET);
    }

}
