package za.co.trf.recurly.api.rest.dto.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="subscription")
public class SubscriptionUpdateRequest {

    public enum TimeFrame {
        NOW, RENEWAL
    }

    private String timeframe;
    private String planCode;

    public SubscriptionUpdateRequest() {
    }

    public SubscriptionUpdateRequest(TimeFrame timeframe) {
        setTimeframe(timeframe.name().toLowerCase());
    }

    @XmlElement(name = "timeframe")
    public String getTimeframe() {
        return timeframe;
    }

    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    @XmlElement(name = "plan_code")
    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

}
