package za.co.trf.recurly.api.rest.dto.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Wrapper object for a list of Recurly {@link za.co.trf.recurly.api.rest.dto.response.Invoice}s
 */
@XmlRootElement(name = "invoices")
public class InvoiceList {

    private List<Invoice> invoiceList;

    @XmlElement(name = "invoice")
    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
    }

}