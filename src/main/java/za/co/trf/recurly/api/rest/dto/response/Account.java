package za.co.trf.recurly.api.rest.dto.response;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Recurly REST API Object for a customer's Account.
 */
@XmlRootElement(name = "account")
public class Account {

    private String accountUrl;
    private String accountCode;
    private String state;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String acceptLanguage;
    private String hostedLoginToken;
    private Date createdAt;

    @XmlAttribute(name = "href")
    public String getAccountUrl() {
        return accountUrl;
    }

    public void setAccountUrl(String accountUrl) {
        this.accountUrl = accountUrl;
    }

    @XmlElement(name = "account_code")
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    @XmlElement(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @XmlElement(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "accept_language")
    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    @XmlElement(name = "hosted_login_token")
    public String getHostedLoginToken() {
        return hostedLoginToken;
    }

    public void setHostedLoginToken(String hostedLoginToken) {
        this.hostedLoginToken = hostedLoginToken;
    }

    @XmlElement(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAccountCodeFromUrl() {
        if (accountUrl == null) {
            return null;
        }

        return accountUrl.substring(accountUrl.lastIndexOf('/') + 1);
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder("Recurly Account [");
        ans.append("accountCode: ").append(accountCode).append(" | ");
        ans.append("firstName: ").append(firstName).append(" | ");
        ans.append("lastName: ").append(lastName).append(" | ");
        ans.append("email: ").append(email);
        ans.append("]");
        return ans.toString();
    }
}
