package za.co.trf.recurly.api.rest.dto.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Wrapper object for a list of Recurly {@link za.co.trf.recurly.api.rest.dto.response.Subscription}s
 */
@XmlRootElement(name = "subscriptions")
public class SubscriptionList {

    private List<Subscription> subscriptionList;

    @XmlElement(name = "subscription")
    public List<Subscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<Subscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

}