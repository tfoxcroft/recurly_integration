package za.co.trf.recurly.api.rest;

import org.apache.log4j.Logger;

/**
 * Base class for services to interact with Recurly's REST API
 */
public abstract class RecurlyWebServiceBase {

    protected static final String RECURLY_API_BASE_URL = "https://api.recurly.com/v2/";
    protected static final String RECURLY_ACCOUNTS_URL_SUFFIX = "accounts";
    protected static final String RECURLY_SUBSCRIPTIONS_URL_SUFFIX = "subscriptions";
    protected static final String RECURLY_BILLING_INFO_URL_SUFFIX = "billing_info";
    protected static final String RECURLY_TRANSACTIONS_URL_SUFFIX = "transactions";
    protected static final String RECURLY_ADJUSTMENTS_URL_SUFFIX = "adjustments";
    protected static final String RECURLY_COUPONS_URL_SUFFIX = "coupons";
    protected static final String RECURLY_INVOICES_URL_SUFFIX = "invoices";
    protected static final String RECURLY_PLANS_URL_SUFFIX = "plans";

    protected final Logger log = Logger.getLogger(getClass());

    protected RecurlyRestTemplate recurlyRestTemplate;

    public void setRecurlyRestTemplate(RecurlyRestTemplate recurlyRestTemplate) {
        this.recurlyRestTemplate = recurlyRestTemplate;
    }

}