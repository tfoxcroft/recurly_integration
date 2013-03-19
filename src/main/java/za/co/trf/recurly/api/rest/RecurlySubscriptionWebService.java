package za.co.trf.recurly.api.rest;

import za.co.trf.recurly.api.rest.dto.request.SubscriptionUpdateRequest;
import za.co.trf.recurly.api.rest.dto.response.Subscription;

import java.util.List;

/**
 * Service for interacting with Subscriptions through Recurly's RESTS API
 */
public interface RecurlySubscriptionWebService {

    /**
     * Get all Subscriptions
     */
    public List<Subscription> getAllSubscriptions();

    /**
     * Get all Subscriptions related to a specific Account
     * @param accountCode the account code of the account in question
     */
    public List<Subscription> getSubscriptionsForAccount(final String accountCode);

    /**
     * Get a specific Subscription
     * @param uuid the uuid (Universally Unique Identifier) of the Subscription to retrieve
     */
    public Subscription getSubscription(final String uuid);

    /**
     * Update a Subscription's details
     * @param uuid the uuid (Universally Unique Identifier) of the Subscription to update
     * @param subscriptionUpdateRequest dto containing the values to be updated
     * @return the updated Subscription
     */
    public Subscription updateSubscription(final String uuid, final SubscriptionUpdateRequest subscriptionUpdateRequest);

    /**
     * Cancel a Subscription
     * @param uuid the uuid (Universally Unique Identifier) of the Subscription to cancel
     * @return the updated Subscription
     */
    public Subscription cancelSubscription(final String uuid);

    /**
     * Reactive a Subscription
     * @param uuid the uuid (Universally Unique Identifier) of the Subscription to reactive
     * @return the updated Subscription
     */
    public Subscription reactivateSubscription(final String uuid);

}