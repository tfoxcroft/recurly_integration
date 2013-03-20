package za.co.trf.recurly.api.rest;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import za.co.trf.recurly.api.rest.dto.request.PlanChangeStateRequest;
import za.co.trf.recurly.api.rest.dto.response.Plan;
import za.co.trf.recurly.api.rest.dto.response.PlanList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Service for interacting with Plans through Recurly's RESTS API
 */
public class RecurlyPlanWebServiceImpl extends RecurlyWebServiceBase implements RecurlyPlanWebService {

    public List<Plan> getAllPlans() {
        log.debug("Retrieving list of plans");
        String url = RECURLY_API_BASE_URL + RECURLY_PLANS_URL_SUFFIX;

        return recurlyRestTemplate.exchangeXml(url, null, null, PlanList.class, HttpMethod.GET).getPlanList();
    }

    public Plan getPlan(String planCode) {
        if (planCode == null || planCode.isEmpty()) {
            String errorMessage = "planCode cannot be null or empty";
            throw new RecurlyAPIException(errorMessage, new IllegalArgumentException(errorMessage));
        }

        log.debug("Retrieving plan with plan code " + planCode);
        String url = RECURLY_API_BASE_URL + RECURLY_PLANS_URL_SUFFIX + "/{planCode}";

        Map<String, String> params = Collections.singletonMap("planCode", planCode);
        return recurlyRestTemplate.exchangeXml(url, params, null, Plan.class, HttpMethod.GET);
    }

    @Override
    public void deletePlan(String planCode) {
        if (planCode == null || planCode.isEmpty()) {
            String errorMessage = "planCode cannot be null or empty";
            throw new RecurlyAPIException(errorMessage, new IllegalArgumentException(errorMessage));
        }

        log.debug("Deleting plan with plan code " + planCode);
        String url = RECURLY_API_BASE_URL + RECURLY_PLANS_URL_SUFFIX + "/{planCode}";

        Map<String, String> params = Collections.singletonMap("planCode", planCode);
        PlanChangeStateRequest changeStateRequest = new PlanChangeStateRequest(planCode);
        recurlyRestTemplate.exchangeXml(url, params, changeStateRequest, Plan.class, HttpMethod.DELETE,
                HttpStatus.NO_CONTENT);
    }

}