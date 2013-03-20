package za.co.trf.recurly.api.rest.dto.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Wrapper object for a list of Recurly {@link za.co.trf.recurly.api.rest.dto.response.Plan}s
 */
@XmlRootElement(name = "plans")
public class PlanList {

    private List<Plan> planList;

    @XmlElement(name = "plan")
    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }

}