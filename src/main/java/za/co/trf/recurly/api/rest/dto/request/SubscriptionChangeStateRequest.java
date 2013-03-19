package za.co.trf.recurly.api.rest.dto.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="subscription")
public class SubscriptionChangeStateRequest {

    private String uuid;

    public SubscriptionChangeStateRequest() {
    }

    public SubscriptionChangeStateRequest(String uuid) {
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
