package za.co.trf.recurly.api.rest;

import za.co.trf.recurly.api.rest.dto.response.Subscription;

/**
 * Service for retrieving the results of a RecurlyJS form submission through Recurly's RESTS API
 */
public interface RecurlyJSResultService {

    /**
     * Retrieve the results of a RecurlyJS Subscription form submission.
     * @param resultToken the token provided by recurly
     * @return the newly submitted Subscription
     */
    public Subscription fetchSubscriptionResult(String resultToken);

}
