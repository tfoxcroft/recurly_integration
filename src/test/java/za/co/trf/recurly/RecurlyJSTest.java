package za.co.trf.recurly;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import za.co.trf.recurly.api.entity.AddOn;
import za.co.trf.recurly.js.RecurlyJS;

import java.util.*;

/**
 * Recurly JS Tests
 */
public class RecurlyJSTest extends TestCase {

    private static final Logger LOG = Logger.getLogger(RecurlyJSTest.class);

    private static final String SECRET_KEY = "a7e8ccc62d1d4127bcfd822a33496943";
    private static final Date TRANSACTION_DATE = new Date(1326460500000L);

    private static final String EXPECTED_FLATTENED_ARGS = "account_code:123,amount_in_cents:500,currency:USD";
    private static final String EXPECTED_FLATTENED_ARGS_RECURSIVE =
            "account_code:123,add_ons:[[add_on_code:extra,quantity:5]," +
            "[add_on_code:bonus,quantity:2]],plan_code:gold,quantity:1";

    MockRecurlyJS recurlyJS = new MockRecurlyJS(SECRET_KEY);

    @Test
    public void testHash() throws Exception {
        // Hash created using PHP's hash_hmac command:
        // hash_hmac('sha1', 'test', sha1("a7e8ccc62d1d4127bcfd822a33496943", true));        
        String hash = recurlyJS.hash(SECRET_KEY, "test");
        LOG.info("Hash: " + hash);
        Assert.assertEquals("Token not created as expected", "d5273cfab6811962217cdd6ed457a22ab67ed426", hash);
    }

    @Test
    public void testFlattenArgs() throws Exception {
        SortedMap<String, Object> args = new TreeMap<String, Object>();
        args.put("account_code", "123");
        args.put("amount_in_cents", String.valueOf(500));
        args.put("currency", "USD");
        String flattened = recurlyJS.flatten(args);
        LOG.info("Flatted args: " + flattened);
        Assert.assertEquals("Args not flattened as expected", EXPECTED_FLATTENED_ARGS, flattened);
    }

    @Test
    public void testFlattenArgsRecursive() {
        SortedMap<String, Object> addOn1 = new TreeMap<String, Object>();
        addOn1.put("add_on_code", "extra");
        addOn1.put("quantity", "5");
        SortedMap<String, Object> addOn2 = new TreeMap<String, Object>();
        addOn2.put("add_on_code", "bonus");
        addOn2.put("quantity", "2");

        SortedMap<String, Object> addOnArgs = new TreeMap<String, Object>();
        addOnArgs.put("list_arg_00001", addOn1);
        addOnArgs.put("list_arg_00002", addOn2);

        SortedMap<String, Object> args = new TreeMap<String, Object>();
        args.put("account_code", "123");
        args.put("plan_code", "gold");
        args.put("add_ons", addOnArgs);
        args.put("quantity", "1");

        String flattened = recurlyJS.flatten(args);
        LOG.info("Flatted args: " + flattened);
        Assert.assertEquals("Args not flattened as expected", EXPECTED_FLATTENED_ARGS_RECURSIVE, flattened);
    }

    @Test
    public void testSignSubscription() {
        String hash = recurlyJS.signSubscription("free", "001", TRANSACTION_DATE);
        LOG.info("SignSubscription: " + hash);
        Assert.assertEquals("Subscription hash not as expected",
                "8847aacfd562bb6fbe01f3fc6fa5339995393cbe-1326460500", hash);
    }

    @Test
    public void testVerifySubscription() {
        boolean isVerified = recurlyJS.verifySubscription(
                "d32f8b84ba325edf32a317c9c822874fac2a7990-1326460500", "free", "001", "1", null, null);
        Assert.assertTrue("Subscription hash not verified", isVerified);
    }

    @Test
    public void testVerifySubscriptionWithCoupon() {
        boolean isVerified = recurlyJS.verifySubscription(
                "65998f02991b17189aa551429854bcd42ea3c965-1326460500", "free", "001", "1", "999999", null);
        Assert.assertTrue("Subscription hash not verified", isVerified);
    }

    @Test
    public void testVerifySubscriptionWithAddOn() {
        List<AddOn> addOns = new ArrayList<AddOn>(2);
        addOns.add(new AddOn("extra", 5));
        addOns.add(new AddOn("bonus", 2));

        boolean isVerified = recurlyJS.verifySubscription(
                "ba2f299716c3062af21ea616ab217f2502787950-1326460500", "free", "001", "1", null, addOns);
        Assert.assertTrue("Subscription hash not verified", isVerified);
    }

    @Test
    public void testSignTransaction() {
        String hash = recurlyJS.signTransaction(100, "USD", "100", TRANSACTION_DATE);
        LOG.info("SignTransaction: " + hash);
        Assert.assertEquals("Transaction hash not as expected",
                "f3361c9c255df5f689ccccc60e1848fd3c1dfa5a-1326460500", hash);
    }

    @Test
    public void testVerifyTransaction() {
        boolean isVerified = recurlyJS.verifyTransaction(
                "8ca933e479c27c2db21b94c47cfea81362678234-1326460500", "001", "100", "USD", "1234");
        Assert.assertTrue("Transaction hash not verified", isVerified);
    }

    @Test
    public void testSignBillingInfoUpdated() {
        String hash = recurlyJS.signBillingInfoUpdate("testaccount", TRANSACTION_DATE);
        LOG.info("SignBillingInfoUpdated: " + hash);
        Assert.assertEquals("Billing info hash not as expected",
                "6adc99f28ffefe08679a1329c7863e63d11d2310-1326460500", hash);
    }

    @Test
    public void testVerifyBillingInfoUpdated() throws Exception {
        boolean isVerified = recurlyJS.verifyBillingInfoUpdated(
                "784277fcce49b0250c4a2e245ba872ea890dcbf5-1326460500", "001");
        Assert.assertTrue("BillingInfoUpdated hash not verified", isVerified);
    }

    /**
     * RecurlyJS subclass, overriding the
     *  getPrivateKey() method to use private key used in testing, and the
     *  timeDifference() method to use a constant date for the date/time of the transaction.
     *  Also exposes protected methods.
     */
    private class MockRecurlyJS extends RecurlyJS {

        public MockRecurlyJS(String privateKey) {
            super(privateKey);
        }

        @Override
        public long timeDifference(Date timestamp) {
            return TRANSACTION_DATE.getTime() - timestamp.getTime();
        }

        @Override
        public String hash(String privateKey, String message) {
            return super.hash(privateKey, message);
        }

        @Override
        public String flatten(SortedMap<String, Object> args) {
            return super.flatten(args);
        }

        @Override
        public String generateSignature(String claim, SortedMap<String, Object> args, Date timestamp) {
            return super.generateSignature(claim, args, timestamp);
        }
    }
}