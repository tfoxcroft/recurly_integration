package za.co.trf.recurly.api;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import za.co.trf.recurly.api.rest.RecurlyInvoiceWebServiceImpl;
import za.co.trf.recurly.api.rest.RecurlyRestTemplate;
import za.co.trf.recurly.api.rest.dto.response.Invoice;

import java.io.FileOutputStream;
import java.util.List;

public class RecurlyInvoiceWebServiceTest {

    private static final String EXISTING_INVOICE_NUMBER = "INVOICE_NUMBER_HERE";
    private static final String EXISTING_ACCOUNT_NUMBER = "ACCOUNT_NUMBER_HERE";

    private static RecurlyInvoiceWebServiceImpl invoiceWebService;

    private final Logger log = Logger.getLogger(getClass());

    @BeforeClass
    public static void setup() {
        invoiceWebService = new RecurlyInvoiceWebServiceImpl();
        invoiceWebService.setRecurlyRestTemplate(new RecurlyRestTemplate("KEY_HERE"));
    }

    @Test
    public void testGetAllInvoices() {
        List<Invoice> invoices = invoiceWebService.getAllInvoices();
        for (Invoice invoice : invoices) {
            log.debug("Recurly Invoice: " + invoice.getUuid());
        }
    }

    @Test
    public void testGetInvoicesForAccount() {
        List<Invoice> invoices = invoiceWebService.getInvoicesForAccount(EXISTING_ACCOUNT_NUMBER);
        for (Invoice invoice : invoices) {
            log.debug("Recurly Invoice: " + invoice.getUuid());
        }
    }

    @Test
    public void testGetInvoice() {
        Invoice invoice = invoiceWebService.getInvoice(EXISTING_INVOICE_NUMBER);
        log.debug("Recurly Invoice: " + invoice.getUuid());
    }

    @Test
    public void testGetInvoicePDF() throws Exception {
        byte[] pdfData = invoiceWebService.getInvoicePDF(EXISTING_INVOICE_NUMBER, null);
        log.debug("Recurly Invoice PDF size: " + pdfData.length);

        new FileOutputStream("test-invoice.pdf").write(pdfData);
    }

    @Test
    public void testMarkInvoiceAsPaidSuccessfully() {
        invoiceWebService.markInvoiceAsPaidSuccessfully(EXISTING_INVOICE_NUMBER);
    }

    @Test
    public void testMarkInvoiceAsFailedCollection() {
        invoiceWebService.markInvoiceAsFailedCollection(EXISTING_INVOICE_NUMBER);
    }

}
