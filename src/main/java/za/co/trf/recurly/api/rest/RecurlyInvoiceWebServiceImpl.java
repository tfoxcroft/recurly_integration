package za.co.trf.recurly.api.rest;

import org.springframework.http.HttpMethod;
import za.co.trf.recurly.api.rest.dto.request.InvoiceChangeStateRequest;
import za.co.trf.recurly.api.rest.dto.response.Invoice;
import za.co.trf.recurly.api.rest.dto.response.InvoiceList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Service for interacting with Plans through Recurly's RESTS API
 */
public class RecurlyInvoiceWebServiceImpl extends RecurlyWebServiceBase implements RecurlyInvoiceWebService {

    @Override
    public List<Invoice> getAllInvoices() {
        log.debug("Retrieving list of invoices");
        String url = RECURLY_API_BASE_URL + RECURLY_INVOICES_URL_SUFFIX;

        return recurlyRestTemplate.exchangeXml(url, null, null, InvoiceList.class, HttpMethod.GET).getInvoiceList();
    }

    @Override
    public List<Invoice> getInvoicesForAccount(final String accountCode) {
        if (accountCode == null || accountCode.isEmpty()) {
            String errorMessage = "accountCode cannot be null or empty";
            throw new RecurlyAPIException(errorMessage, new IllegalArgumentException(errorMessage));
        }

        log.debug("Retrieving list of invoices for account " + accountCode);
        String url = RECURLY_API_BASE_URL + RECURLY_ACCOUNTS_URL_SUFFIX + "/{accountCode}/invoices";

        Map<String, String> params = Collections.singletonMap("accountCode", accountCode);
        return recurlyRestTemplate.exchangeXml(url, params, null, InvoiceList.class, HttpMethod.GET).getInvoiceList();
    }

    @Override
    public Invoice getInvoice(final String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.isEmpty()) {
            String errorMessage = "invoiceNumber cannot be null or empty";
            throw new RecurlyAPIException(errorMessage, new IllegalArgumentException(errorMessage));
        }

        log.debug("Retrieving invoice with invoiceNumber " + invoiceNumber);
        String url = RECURLY_API_BASE_URL + RECURLY_INVOICES_URL_SUFFIX + "/{invoiceNumber}";

        Map<String, String> params = Collections.singletonMap("invoiceNumber", invoiceNumber);
        return recurlyRestTemplate.exchangeXml(url, params, null, Invoice.class, HttpMethod.GET);
    }

    @Override
    public byte[] getInvoicePDF(final String invoiceNumber, final String language) {
        if (invoiceNumber == null || invoiceNumber.isEmpty()) {
            String errorMessage = "invoiceNumber cannot be null or empty";
            throw new RecurlyAPIException(errorMessage, new IllegalArgumentException(errorMessage));
        }

        log.debug("Retrieving PDF for invoice with invoiceNumber " + invoiceNumber);
        String url = RECURLY_API_BASE_URL + RECURLY_INVOICES_URL_SUFFIX + "/{invoiceNumber}";

        Map<String, String> params = Collections.singletonMap("invoiceNumber", invoiceNumber);
        return recurlyRestTemplate.exchangePDF(url, params, null, byte[].class, HttpMethod.GET);
    }

    @Override
    public Invoice markInvoiceAsPaidSuccessfully(final String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.isEmpty()) {
            String errorMessage = "invoiceNumber cannot be null or empty";
            throw new RecurlyAPIException(errorMessage, new IllegalArgumentException(errorMessage));
        }

        log.debug("Marking invoice with invoiceNumber " + invoiceNumber + " as successful");
        String url = RECURLY_API_BASE_URL + RECURLY_INVOICES_URL_SUFFIX + "/{invoiceNumber}/mark_successful";

        Map<String, String> params = Collections.singletonMap("invoiceNumber", invoiceNumber);
        InvoiceChangeStateRequest invoiceChangeStateRequest = new InvoiceChangeStateRequest(invoiceNumber);
        return recurlyRestTemplate.exchangeXml(url, params, invoiceChangeStateRequest, Invoice.class, HttpMethod.PUT);
    }

    @Override
    public Invoice markInvoiceAsFailedCollection(final String invoiceNumber) {
        if (invoiceNumber == null || invoiceNumber.isEmpty()) {
            String errorMessage = "invoiceNumber cannot be null or empty";
            throw new RecurlyAPIException(errorMessage, new IllegalArgumentException(errorMessage));
        }

        log.debug("Marking invoice with invoiceNumber " + invoiceNumber + " as failed collection");
        String url = RECURLY_API_BASE_URL + RECURLY_INVOICES_URL_SUFFIX + "/{invoiceNumber}/mark_failed";

        Map<String, String> params = Collections.singletonMap("invoiceNumber", invoiceNumber);
        InvoiceChangeStateRequest invoiceChangeStateRequest = new InvoiceChangeStateRequest(invoiceNumber);
        return recurlyRestTemplate.exchangeXml(url, params, invoiceChangeStateRequest, Invoice.class, HttpMethod.PUT);
    }

}