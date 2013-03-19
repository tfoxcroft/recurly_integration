package za.co.trf.recurly.api.rest.dto.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="account")
public class AccountChangeStateRequest {

    private String accountCode;

    public AccountChangeStateRequest() {
    }

    public AccountChangeStateRequest(String accountCode) {
        setAccountCode(accountCode);
    }

    @XmlElement(name = "accountCode")
    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }
}
