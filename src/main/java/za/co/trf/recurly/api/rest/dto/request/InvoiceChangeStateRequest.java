package za.co.trf.recurly.api.rest.dto.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="invoice")
public class InvoiceChangeStateRequest {

    private String uuid;

    public InvoiceChangeStateRequest() {
    }

    public InvoiceChangeStateRequest(String uuid) {
        setUuid(uuid);
    }

    @XmlElement(name = "uuid")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

}
