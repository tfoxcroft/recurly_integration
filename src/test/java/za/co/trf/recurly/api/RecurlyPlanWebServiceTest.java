package za.co.trf.recurly.api;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import za.co.trf.recurly.api.rest.RecurlyPlanWebServiceImpl;
import za.co.trf.recurly.api.rest.RecurlyRestTemplate;
import za.co.trf.recurly.api.rest.dto.response.Plan;

import java.util.List;

public class RecurlyPlanWebServiceTest {

    private static final String EXISTING_PLAN_CODE = "PLAN_CODE_HERE";

    private static RecurlyPlanWebServiceImpl planWebService;

    private final Logger log = Logger.getLogger(getClass());

    @BeforeClass
    public static void setup() {
        planWebService = new RecurlyPlanWebServiceImpl();
        planWebService.setRecurlyRestTemplate(new RecurlyRestTemplate("KEY_HERE"));
    }

    @Test
    public void testGetAllPlans() {
        List<Plan> plans = planWebService.getAllPlans();
        for (Plan plan : plans) {
            log.debug("Recurly Plan: " + plan.getName());
        }
    }

    @Test
    public void testGetPlan() {
        Plan plan = planWebService.getPlan(EXISTING_PLAN_CODE);
        log.debug("Recurly Plan: " + plan.getName());
    }

    @Test
    public void testDeletePlan() {
        planWebService.deletePlan(EXISTING_PLAN_CODE);
    }

}
