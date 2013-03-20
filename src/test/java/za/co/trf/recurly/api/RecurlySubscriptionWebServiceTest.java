package za.co.trf.recurly.api;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import za.co.trf.recurly.api.rest.RecurlyRestTemplate;
import za.co.trf.recurly.api.rest.RecurlySubscriptionWebServiceImpl;
import za.co.trf.recurly.api.rest.dto.request.SubscriptionUpdateRequest;
import za.co.trf.recurly.api.rest.dto.response.Subscription;

import java.util.List;

public class RecurlySubscriptionWebServiceTest {

    private static final String EXISTING_ACCOUNT_CODE = "ACCOUNT_CODE_HERE";
    private static final String EXISTING_SUBSCRIPTION_UUID = "UUID_HERE";

    private static RecurlySubscriptionWebServiceImpl subscriptionWebService;

    private final Logger log = Logger.getLogger(getClass());

    @BeforeClass
    public static void setup() {
        subscriptionWebService = new RecurlySubscriptionWebServiceImpl();
        subscriptionWebService.setRecurlyRestTemplate(new RecurlyRestTemplate("KEY_HERE"));
    }

    @Test
    public void testGetAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionWebService.getAllSubscriptions();
        for (Subscription subscription : subscriptions) {
            log.debug("Recurly Subscription: " + subscription.getUuid());
        }
    }

    @Test
    public void testGetSubscription() {
        Subscription subscription = subscriptionWebService.getSubscription(EXISTING_SUBSCRIPTION_UUID);
        log.debug("Recurly Subscription: " + subscription.getUuid());
    }

    @Test
    public void testGetSubscriptionsForAccount() {
        List<Subscription> subscriptions = subscriptionWebService.getSubscriptionsForAccount(EXISTING_ACCOUNT_CODE);
        for (Subscription subscription : subscriptions) {
            log.debug("Recurly Subscription: " + subscription.getUuid());
        }
    }

    @Test
    public void testUpdateSubscription() {
        SubscriptionUpdateRequest updateRequest = new SubscriptionUpdateRequest(SubscriptionUpdateRequest.TimeFrame.NOW);
        updateRequest.setPlanCode("16_50");
        Subscription updatedSubscription = subscriptionWebService.updateSubscription(EXISTING_SUBSCRIPTION_UUID, updateRequest);
        log.debug("Recurly Subscription Updated: " + updatedSubscription.getUuid());
    }

    @Test
    public void testCancelSubscription() {
        Subscription subscription = subscriptionWebService.cancelSubscription(EXISTING_SUBSCRIPTION_UUID);
        log.debug("Recurly Subscription Cancelled: " + subscription.getUuid());
    }

    @Test
    public void testReactivateSubscription() {
        Subscription subscription = subscriptionWebService.reactivateSubscription(EXISTING_SUBSCRIPTION_UUID);
        log.debug("Recurly Subscription Reactivated: " + subscription.getUuid());
    }

}
