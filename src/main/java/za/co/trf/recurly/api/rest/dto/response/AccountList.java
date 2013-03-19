package za.co.trf.recurly.api.rest.dto.response;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Wrapper object for a list of Recurly {@link Account}s
 */
@XmlRootElement(name = "accounts")
public class AccountList {

    private List<Account> accountList;

    @XmlElement(name = "account")
    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

}