package za.co.trf.recurly.api;

import org.junit.BeforeClass;
import org.junit.Test;
import za.co.trf.recurly.api.rest.RecurlyRestTemplate;
import za.co.trf.recurly.api.rest.RecurlySubscriptionWebServiceImpl;
import za.co.trf.recurly.api.rest.dto.request.SubscriptionUpdateRequest;
import za.co.trf.recurly.api.rest.dto.response.Subscription;

import java.util.List;

public class RecurlySubscriptionWebServiceTest {

    private static final String EXISTING_ACCOUNT_CODE = "13";
    private static final String EXISTING_SUBSCRIPTION_UUID = "KEY_HERE";

    private static RecurlyRestTemplate recurlyRestTemplate = new RecurlyRestTemplate("");
    private static RecurlySubscriptionWebServiceImpl subscriptionWebService = new RecurlySubscriptionWebServiceImpl();

    @BeforeClass
    public static void setup() {
        subscriptionWebService.setRecurlyRestTemplate(recurlyRestTemplate);
    }

    @Test
    public void testGetAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionWebService.getAllSubscriptions();
        for (Subscription subscription : subscriptions) {
            System.out.println("Recurly Subscription: " + subscription.getUuid());
        }
    }

    @Test
    public void testGetSubscription() {
        Subscription subscription = subscriptionWebService.getSubscription(EXISTING_SUBSCRIPTION_UUID);
        System.out.println("Recurly Subscription: " + subscription.getUuid());
    }

    @Test
    public void testGetSubscriptionsForAccount() {
        List<Subscription> subscriptions = subscriptionWebService.getSubscriptionsForAccount(EXISTING_ACCOUNT_CODE);
        for (Subscription subscription : subscriptions) {
            System.out.println("Recurly Subscription: " + subscription.getUuid());
        }
    }

    @Test
    public void testUpdateSubscription() {
        SubscriptionUpdateRequest updateRequest = new SubscriptionUpdateRequest(SubscriptionUpdateRequest.TimeFrame.NOW);
        updateRequest.setPlanCode("16_50");
        Subscription updatedSubscription = subscriptionWebService.updateSubscription(EXISTING_SUBSCRIPTION_UUID, updateRequest);
        System.out.println("Recurly Subscription Updated: " + updatedSubscription.getUuid());
    }

    @Test
    public void testCancelSubscription() {
        Subscription subscription = subscriptionWebService.cancelSubscription(EXISTING_SUBSCRIPTION_UUID);
        System.out.println("Recurly Subscription Cancelled: " + subscription.getUuid());
    }

    @Test
    public void testReactivateSubscription() {
        Subscription subscription = subscriptionWebService.reactivateSubscription(EXISTING_SUBSCRIPTION_UUID);
        System.out.println("Recurly Subscription Reactivated: " + subscription.getUuid());
    }

}
