package za.co.trf.recurly.api.rest.dto.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * JAXB POJO that represents an Invoice response received from Recurly.
 * An invoice record relates charges, credits, and transactions together.
 */
@XmlRootElement(name = "invoice")
public class Invoice {

    private Account account;
    private String uuid;
    private String state;
    private int invoiceNumber;
    private int subtotalInCents;
    private int taxInCents;
    private int totalInCents;
    private String currency;
    private Date createdAt;

    @XmlElement(name = "account")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @XmlElement(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @XmlElement(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlElement(name = "invoice_number")
    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @XmlElement(name = "subtotal_in_cents")
    public int getSubtotalInCents() {
        return subtotalInCents;
    }

    public void setSubtotalInCents(int subtotalInCents) {
        this.subtotalInCents = subtotalInCents;
    }

    @XmlElement(name = "tax_in_cents")
    public int getTaxInCents() {
        return taxInCents;
    }

    public void setTaxInCents(int taxInCents) {
        this.taxInCents = taxInCents;
    }

    @XmlElement(name = "total_in_cents")
    public int getTotalInCents() {
        return totalInCents;
    }

    public void setTotalInCents(int totalInCents) {
        this.totalInCents = totalInCents;
    }

    @XmlElement(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlElement(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
