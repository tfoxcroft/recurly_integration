package za.co.trf.recurly.api.rest.dto.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="plan")
public class PlanChangeStateRequest {

    private String planCode;

    public PlanChangeStateRequest() {
    }

    public PlanChangeStateRequest(String planCode) {
        setPlanCode(planCode);
    }

    @XmlElement(name = "planCode")
    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

}
