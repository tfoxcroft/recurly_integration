package za.co.trf.recurly.js;

import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import za.co.trf.recurly.RecurlyConstants;

/**
 * Recurly JS Tests
 */
public class RecurlyJSTest {

    private static final Logger LOG = Logger.getLogger(RecurlyJSTest.class);

    private static final String SECRET_KEY = "0123456789abcdef0123456789abcdef";

    private MockRecurlyJS recurlyJS = new MockRecurlyJS(SECRET_KEY);

    @Test
    public void testSignForNewSubscriptionToPlan() throws Exception {
        String expectedSignature = "439881d8272bb787a85b134d70864d39a603e3b0|" +
                "nonce=1234567890ABC&subscription%5Bplan_code%5D=ANY_PLAN_CODE&timestamp=1330452";
        String actualSignature = recurlyJS.signForNewSubscriptionToPlan("ANY_PLAN_CODE");
        Assert.assertEquals("Incorrect signature generated for signForNewSubscriptionToPlan",
                expectedSignature, actualSignature);
    }

    @Test
    public void testSignForBillingInfoUpdate() throws Exception {
        String expectedSignature = "603beda7b2407d9f140637062411c1a50b1f01d9|" +
                "account%5Baccount_code%5D=ANY_ACCOUNT_CODE&nonce=1234567890ABC&timestamp=1330452";
        String actualSignature = recurlyJS.signForBillingInfoUpdate("ANY_ACCOUNT_CODE");
        Assert.assertEquals("Incorrect signature generated for signForBillingInfoUpdate",
                expectedSignature, actualSignature);
    }

    @Test
    public void testSignForOneTimeTransaction() throws Exception {
        String expectedSignature = "97d4a614791cae4f894ae5db53961616448e0770|" +
                "account%5Baccount_code%5D=ANY_ACCOUNT_CODE&nonce=1234567890ABC&timestamp=1330452" +
                "&transaction%5Bamount_in_cents%5D=100";
        String actualSignature = recurlyJS.signOneTimeTransaction("ANY_ACCOUNT_CODE", 100);
        Assert.assertEquals("Incorrect signature generated for signForBillingInfoUpdate",
                expectedSignature, actualSignature);
    }

    @Test(expected = RecurlyJSException.class)
    public void testHashWithNullPrivateKey_shouldThrowRecurlyJSException() {
        final String nullPrivateKey = null;
        final String anyMessage = "ANY_MESSAGE";
        recurlyJS.hash(nullPrivateKey, anyMessage);
    }

    @Test(expected = RecurlyJSException.class)
    public void testHashWithNullMessage_shouldThrowRecurlyJSException() {
        final String anyPrivateKey = "ANY_PRIVATE_KEY";
        final String nullMessage = null;
        recurlyJS.hash(anyPrivateKey, nullMessage);
    }

    @Test
    public void testHash() throws Exception {
        // Hash created using PHP's hash_hmac command:
        // hash_hmac('sha1', 'test', SECRET_KEY);
        String hash = recurlyJS.hash(SECRET_KEY, "test");
        LOG.info("Hash: " + hash);
        Assert.assertEquals("Token not created as expected", "20874fc2e4f783afcbeda84f43be9667ff49ce2d", hash);
    }

    @Test
    public void testSignSimple() {
        String hash = recurlyJS.sign(new HashMap<String, Object>() {{
            put(RecurlyConstants.JS_PARAM_ACCOUNT, new HashMap<String, Object>() {{
                put(RecurlyConstants.JS_PARAM_ACCOUNT_CODE, "123");
            }});
        }});
        LOG.info("SignSimple: " + hash);
        Assert.assertEquals("Subscription hash not as expected",
            "7e092a0c75c1219f6445cd8d41c59c9ada178879|" +
                    "account%5Baccount_code%5D=123&nonce=1234567890ABC&timestamp=1330452", hash);
    }

    @Test
    public void testSignComplex() {
        String hash = recurlyJS.sign(new HashMap<String, Object>() {{
            put(RecurlyConstants.JS_PARAM_ACCOUNT, new HashMap<String, Object>() {{
                put(RecurlyConstants.JS_PARAM_ACCOUNT_CODE, "123");
            }});
            put(RecurlyConstants.JS_PARAM_PLAN_CODE, "gold");
            put(RecurlyConstants.JS_PARAM_ADD_ONS, new HashMap<?, ?>[] {
                new HashMap<String, Object>() {{
                    put(RecurlyConstants.JS_PARAM_ADD_ON_CODE, "extra");
                    put(RecurlyConstants.JS_PARAM_QUANTITY, 5);
                }},
                new HashMap<String, Object>() {{
                    put(RecurlyConstants.JS_PARAM_ADD_ON_CODE, "bonus");
                    put(RecurlyConstants.JS_PARAM_QUANTITY, 2);
                }}
            });
            put(RecurlyConstants.JS_PARAM_QUANTITY, 1);
        }});
        LOG.info("SignComplex: " + hash);
        Assert.assertEquals("Subscription hash not as expected", 
              "1186ef26e4f69a6b6e5523bea5bb09d1a8a6f2a5|" +
              "account%5Baccount_code%5D=123&add_ons%5B0%5D%5Badd_on_code%5D=extra&ad" +
              "d_ons%5B0%5D%5Bquantity%5D=5&add_ons%5B1%5D%5Badd_on_code%5D=bonus&add" +
              "_ons%5B1%5D%5Bquantity%5D=2&nonce=1234567890ABC&plan_code=gold&quantit" +
              "y=1&timestamp=1330452", hash);
    }

    /**
     * RecurlyJS subclass, mocking the getTimestamp() and getNonce() methods.
     * Also exposes protected methods.
     */
    private class MockRecurlyJS extends RecurlyJS {

        public MockRecurlyJS(String privateKey) {
            super(privateKey);
        }

        @Override
        public Date getTimestamp() {
            return new Date(1330452000);
        }

        @Override
        public String getNonce() {
            return "1234567890ABC";
        }

        @Override
        public String hash(String privateKey, String message) {
            return super.hash(privateKey, message);
        }
    }
}