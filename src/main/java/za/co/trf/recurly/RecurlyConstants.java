package za.co.trf.recurly;

/**
 * List of Recurly constants
 */
public class RecurlyConstants {

    // Recurly JS operation type names (claims)
    public final static String RECURLY_JS_CLAIM_BILLING_INFO_UPDATE = "billinginfoupdate";
    public final static String RECURLY_JS_CLAIM_BILLING_INFO_UPDATED = "billinginfoupdated";
    public final static String RECURLY_JS_CLAIM_SUBSCRIPTION_CREATE = "subscriptioncreate";
    public final static String RECURLY_JS_CLAIM_SUBSCRIPTION_CREATED = "subscriptioncreated";
    public final static String RECURLY_JS_CLAIM_TRANSACTION_CREATE = "transactioncreate";
    public final static String RECURLY_JS_CLAIM_TRANSACTION_CREATED = "transactioncreated";

    // Recurly JS parameter names
    public final static String RECURLY_JS_PARAM_ACCOUNT_CODE = "account_code";
    public final static String RECURLY_JS_PARAM_PLAN_CODE = "plan_code";
    public final static String RECURLY_JS_PARAM_QUANTITY = "quantity";
    public final static String RECURLY_JS_PARAM_AMOUNT_IN_CENTS = "amount_in_cents";
    public final static String RECURLY_JS_PARAM_CURRENCY = "currency";
    public final static String RECURLY_JS_PARAM_UUID = "uuid";
    public final static String RECURLY_JS_PARAM_COUPON_CODE = "coupon_code";
    public final static String RECURLY_JS_PARAM_ADD_ONS = "add_ons";
    public final static String RECURLY_JS_PARAM_ADD_ON_CODE = "add_on_code";
    public final static String RECURLY_JS_PARAM_LIST_ARG_PREFIX = "list_arg_";
    public final static String RECURLY_JS_PARAM_LIST_ARG = "list_arg_%05d";

    public final static String HMAC_SHA1 = "HmacSHA1";
    
}
