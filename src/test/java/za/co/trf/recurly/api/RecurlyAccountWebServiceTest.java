package za.co.trf.recurly.api;

import org.junit.BeforeClass;
import org.junit.Test;
import za.co.trf.recurly.api.rest.RecurlyAccountWebServiceImpl;
import za.co.trf.recurly.api.rest.RecurlyRestTemplate;
import za.co.trf.recurly.api.rest.dto.response.Account;

import java.util.List;

public class RecurlyAccountWebServiceTest {

    private static RecurlyRestTemplate recurlyRestTemplate = new RecurlyRestTemplate("KEY_HERE");
    private static RecurlyAccountWebServiceImpl accountWebService = new RecurlyAccountWebServiceImpl();

    private static final String EXISTING_ACCOUNT_CODE = "13";

    @BeforeClass
    public static void setup() {
        accountWebService.setRecurlyRestTemplate(recurlyRestTemplate);
    }

    @Test
    public void testGetAllAccounts() {
        List<Account> accounts = accountWebService.getAllAccounts();
        for (Account account : accounts) {
            System.out.println("Recurly Account: " + account.getAccountCode());
        }
    }

    @Test
    public void testGetAccount() {
        Account account = accountWebService.getAccount(EXISTING_ACCOUNT_CODE);
        System.out.println("Recurly Account: " + account.getAccountCode());
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
