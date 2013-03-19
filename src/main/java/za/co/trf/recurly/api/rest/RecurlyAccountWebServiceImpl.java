package za.co.trf.recurly.api.rest;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import za.co.trf.recurly.api.rest.dto.request.AccountChangeStateRequest;
import za.co.trf.recurly.api.rest.dto.response.Account;
import za.co.trf.recurly.api.rest.dto.response.AccountList;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Service for interacting with Accounts through Recurly's RESTS API
 */
public class RecurlyAccountWebServiceImpl extends RecurlyWebServiceBase implements RecurlyAccountWebService {

    public List<Account> getAllAccounts() {
        log.debug("Retrieving list of accounts");
        String url = RECURLY_API_BASE_URL + RECURLY_ACCOUNTS_URL_SUFFIX;

        return recurlyRestTemplate.exchangeXml(url, null, null, AccountList.class, HttpMethod.GET).getAccountList();
    }

    public Account getAccount(String accountCode) {
        if (accountCode == null || accountCode.isEmpty()) {
            throw new RecurlyAPIException(new IllegalArgumentException("accountCode cannot be null or empty"));
        }

        log.debug("Retrieving account with account code " + accountCode);
        String url = RECURLY_API_BASE_URL + RECURLY_ACCOUNTS_URL_SUFFIX + "/{accountCode}";

        Map<String, String> params = Collections.singletonMap("accountCode", accountCode);
        return recurlyRestTemplate.exchangeXml(url, params, null, Account.class, HttpMethod.GET);
    }

    public Account closeAccount(String accountCode) {
        if (accountCode == null || accountCode.isEmpty()) {
            throw new RecurlyAPIException(new IllegalArgumentException("accountCode cannot be null or empty"));
        }

        log.debug("Closing account with account code " + accountCode);
        String url = RECURLY_API_BASE_URL + RECURLY_ACCOUNTS_URL_SUFFIX + "/{accountCode}";

        Map<String, String> params = Collections.singletonMap("accountCode", accountCode);
        AccountChangeStateRequest changeStateRequest = new AccountChangeStateRequest(accountCode);
        return recurlyRestTemplate.exchangeXml(url, params, changeStateRequest, Account.class,
                HttpMethod.DELETE, HttpStatus.NO_CONTENT);
    }

    public Account reopenAccount(String accountCode) {
        if (accountCode == null || accountCode.isEmpty()) {
            throw new RecurlyAPIException(new IllegalArgumentException("accountCode cannot be null or empty"));
        }

        log.debug("Reopening account with account code " + accountCode);
        String url = RECURLY_API_BASE_URL + RECURLY_ACCOUNTS_URL_SUFFIX + "/{accountCode}/reopen";

        Map<String, String> params = Collections.singletonMap("accountCode", accountCode);
        AccountChangeStateRequest changeStateRequest = new AccountChangeStateRequest(accountCode);
        return recurlyRestTemplate.exchangeXml(url, params, changeStateRequest, Account.class, HttpMethod.PUT);
    }

}