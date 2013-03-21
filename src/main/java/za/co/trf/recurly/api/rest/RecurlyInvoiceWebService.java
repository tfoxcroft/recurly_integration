package za.co.trf.recurly.api.rest;

import za.co.trf.recurly.api.rest.dto.response.Invoice;

import java.util.List;

/**
 * Service for interacting with Invoices through Recurly's RESTS API
 */
public interface RecurlyInvoiceWebService {

    /**
     * Get all Invoices
     */
    public List<Invoice> getAllInvoices();

    /**
     * Get all Invoices related to a specific Account
     * @param accountCode the account code of the account in question
     */
    public List<Invoice> getInvoicesForAccount(final String accountCode);

    /**
     * Get a specific Plan
     * @param invoiceNumber the invoice number of the Invoice to retrieve
     */
    public Invoice getInvoice(final String invoiceNumber);

    /**
     * Get a PDF of an Invoice
     * @param invoiceNumber the invoice number of the Invoice
     * @param language the language and locale preferred for the Invoice
     * @return the PDF as a byte[]
     */
    public byte[] getInvoicePDF(final String invoiceNumber, final String language);

    /**
     * Mark an invoice as paid successfully
     * @param invoiceNumber the invoice number of the Invoice
     * @return the updated Invoice
     */
    public Invoice markInvoiceAsPaidSuccessfully(final String invoiceNumber);

    /**
     * Mark an invoice as failed collection
     * @param invoiceNumber the invoice number of the Invoice
     * @return the updated Invoice
     */
    public Invoice markInvoiceAsFailedCollection(final String invoiceNumber);

}