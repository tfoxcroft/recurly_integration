package za.co.trf.recurly.api.rest;

import za.co.trf.recurly.api.rest.dto.response.Plan;

import java.util.List;

/**
 * Service for interacting with Plans through Recurly's RESTS API
 */
public interface RecurlyPlanWebService {

    /**
     * Get all Plans
     */
    public List<Plan> getAllPlans();

    /**
     * Get a specific Plan
     * @param planCode the plan code of the Plan to retrieve
     */
    public Plan getPlan(final String planCode);

    /**
     * Delete a Plan
     * @param planCode the plan code of the Plan to delete
     */
    public void deletePlan(final String planCode);

}