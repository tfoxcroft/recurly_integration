package za.co.trf.recurly.api;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import za.co.trf.recurly.api.rest.RecurlyAccountWebServiceImpl;
import za.co.trf.recurly.api.rest.RecurlyRestTemplate;
import za.co.trf.recurly.api.rest.dto.response.Account;

import java.util.List;

public class RecurlyAccountWebServiceTest {

    private static final String EXISTING_ACCOUNT_CODE = "ACCOUNT_CODE_HERE";

    private static RecurlyAccountWebServiceImpl accountWebService;

    private final Logger log = Logger.getLogger(getClass());

    @BeforeClass
    public static void setup() {
        accountWebService =  new RecurlyAccountWebServiceImpl();
        accountWebService.setRecurlyRestTemplate(new RecurlyRestTemplate("KEY_HERE"));
    }

    @Test
    public void testGetAllAccounts() {
        List<Account> accounts = accountWebService.getAllAccounts();
        for (Account account : accounts) {
            log.debug("Recurly Account: " + account.getAccountCode());
        }
    }

    @Test
    public void testGetAccount() {
        Account account = accountWebService.getAccount(EXISTING_ACCOUNT_CODE);
        log.debug("Recurly Account: " + account.getAccountCode());
    }

    @Test
    public void testCloseAccount() {
        accountWebService.closeAccount(EXISTING_ACCOUNT_CODE);
    }

    @Test
    public void testReopenAccount() {
        accountWebService.reopenAccount(EXISTING_ACCOUNT_CODE);
    }



}
