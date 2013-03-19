package za.co.trf.recurly.api.rest;

import org.springframework.http.HttpMethod;
import za.co.trf.recurly.api.rest.dto.request.SubscriptionChangeStateRequest;
import za.co.trf.recurly.api.rest.dto.request.SubscriptionUpdateRequest;
import za.co.trf.recurly.api.rest.dto.response.Subscription;
import za.co.trf.recurly.api.rest.dto.response.SubscriptionList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Service for interacting with Subscriptions through Recurly's RESTS API
 */
public class RecurlySubscriptionWebServiceImpl extends RecurlyWebServiceBase implements RecurlySubscriptionWebService {

    @Override
    public List<Subscription> getAllSubscriptions() {
        log.debug("Retrieving list of subscriptions");
        String url = RECURLY_API_BASE_URL + RECURLY_SUBSCRIPTIONS_URL_SUFFIX;

        return recurlyRestTemplate.exchangeXml(url, null, null, SubscriptionList.class, HttpMethod.GET).getSubscriptionList();
    }

    @Override
    public List<Subscription> getSubscriptionsForAccount(String accountCode) {
        if (accountCode == null || accountCode.isEmpty()) {
            throw new RecurlyAPIException(new IllegalArgumentException("accountCode cannot be null or empty"));
        }

        log.debug("Retrieving list of subscriptions for account " + accountCode);
        String url = RECURLY_API_BASE_URL + RECURLY_ACCOUNTS_URL_SUFFIX + "/{accountCode}/subscriptions";

        Map<String, String> params = Collections.singletonMap("accountCode", accountCode);
        return recurlyRestTemplate.exchangeXml(url, params, null, SubscriptionList.class, HttpMethod.GET).getSubscriptionList();
    }

    @Override
    public Subscription getSubscription(final String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            throw new RecurlyAPIException(new IllegalArgumentException("uuid cannot be null or empty"));
        }

        log.debug("Retrieving subscription with uuid " + uuid);
        String url = RECURLY_API_BASE_URL + RECURLY_SUBSCRIPTIONS_URL_SUFFIX + "/{uuid}";

        Map<String, String> params = Collections.singletonMap("uuid", uuid);
        return recurlyRestTemplate.exchangeXml(url, params, null, Subscription.class, HttpMethod.GET);
    }

    @Override
    public Subscription updateSubscription(final String uuid, final SubscriptionUpdateRequest subscriptionUpdateRequest) {
        if (uuid == null || uuid.isEmpty()) {
            throw new RecurlyAPIException(new IllegalArgumentException("uuid cannot be null or empty"));
        } else if (subscriptionUpdateRequest == null) {
            throw new RecurlyAPIException(new IllegalArgumentException("subscriptionUpdateRequest cannot be null"));
        } else if (subscriptionUpdateRequest.getTimeframe() == null
                || subscriptionUpdateRequest.getTimeframe().isEmpty()) {
            throw new RecurlyAPIException(new IllegalArgumentException("timeframe cannot be null or empty"));
        }

        log.debug("Updating subscription with uuid " + uuid);
        String url = RECURLY_API_BASE_URL + RECURLY_SUBSCRIPTIONS_URL_SUFFIX + "/{uuid}";

        Map<String, String> params = Collections.singletonMap("uuid", uuid);
        return recurlyRestTemplate.exchangeXml(url, params, subscriptionUpdateRequest, Subscription.class, HttpMethod.PUT);
    }

    @Override
    public Subscription cancelSubscription(String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            throw new RecurlyAPIException(new IllegalArgumentException("uuid cannot be null or empty"));
        }

        log.debug("Cancelling subscription with uuid " + uuid);
        String url = RECURLY_API_BASE_URL + RECURLY_SUBSCRIPTIONS_URL_SUFFIX + "/{uuid}/cancel";

        Map<String, String> params = Collections.singletonMap("uuid", uuid);
        SubscriptionChangeStateRequest subscriptionChangeStateRequest = new SubscriptionChangeStateRequest(uuid);
        return recurlyRestTemplate.exchangeXml(url, params, subscriptionChangeStateRequest, Subscription.class, HttpMethod.PUT);
    }

    @Override
    public Subscription reactivateSubscription(String uuid) {
        if (uuid == null || uuid.isEmpty()) {
            throw new RecurlyAPIException(new IllegalArgumentException("uuid cannot be null or empty"));
        }

        log.debug("Reactivating subscription with uuid " + uuid);
        String url = RECURLY_API_BASE_URL + RECURLY_SUBSCRIPTIONS_URL_SUFFIX + "/{uuid}/reactivate";

        Map<String, String> params = Collections.singletonMap("uuid", uuid);
        SubscriptionChangeStateRequest subscriptionChangeStateRequest = new SubscriptionChangeStateRequest(uuid);
        return recurlyRestTemplate.exchangeXml(url, params, subscriptionChangeStateRequest, Subscription.class, HttpMethod.PUT);
    }

}