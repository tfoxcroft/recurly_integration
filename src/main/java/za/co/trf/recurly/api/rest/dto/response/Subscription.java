package za.co.trf.recurly.api.rest.dto.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * JAXB POJO that represents a Subscription response received from Recurly
 */
@XmlRootElement(name = "subscription")
public class Subscription {

    public static final String STATE_ACTIVE = "active";

    private Account account;
    private Plan plan;
    private String uuid;
    private String state;
    private Integer unitAmountInCents;
    private String currency;
    private Integer quantity;
    private Date activatedAt;
    private Date canceledAt;
    private Date expiresAt;
    private Date currentPeriodStartedAt;
    private Date currentPeriodEndsAt;
    private Date trialStartedAt;
    private Date trialEndsAt;

    @XmlElement(name = "account")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @XmlElement(name = "plan")
    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
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

    @XmlElement(name = "unit_amount_in_cents")
    public Integer getUnitAmountInCents() {
        return unitAmountInCents;
    }

    public void setUnitAmountInCents(Integer unitAmountInCents) {
        this.unitAmountInCents = unitAmountInCents;
    }

    @XmlElement(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlElement(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @XmlElement(name = "activated_at")
    public Date getActivatedAt() {
        return activatedAt;
    }

    public void setActivatedAt(Date activatedAt) {
        this.activatedAt = activatedAt;
    }

    @XmlElement(name = "canceled_at")
    public Date getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(Date canceledAt) {
        this.canceledAt = canceledAt;
    }

    @XmlElement(name = "expires_at")
    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    @XmlElement(name = "current_period_started_at")
    public Date getCurrentPeriodStartedAt() {
        return currentPeriodStartedAt;
    }

    public void setCurrentPeriodStartedAt(Date currentPeriodStartedAt) {
        this.currentPeriodStartedAt = currentPeriodStartedAt;
    }

    @XmlElement(name = "current_period_ends_at")
    public Date getCurrentPeriodEndsAt() {
        return currentPeriodEndsAt;
    }

    public void setCurrentPeriodEndsAt(Date currentPeriodEndsAt) {
        this.currentPeriodEndsAt = currentPeriodEndsAt;
    }

    @XmlElement(name = "trial_started_at")
    public Date getTrialStartedAt() {
        return trialStartedAt;
    }

    public void setTrialStartedAt(Date trialStartedAt) {
        this.trialStartedAt = trialStartedAt;
    }

    @XmlElement(name = "trial_ends_at")
    public Date getTrialEndsAt() {
        return trialEndsAt;
    }

    public void setTrialEndsAt(Date trialEndsAt) {
        this.trialEndsAt = trialEndsAt;
    }

    public boolean isActive() {
        return STATE_ACTIVE.equalsIgnoreCase(state);
    }
}
