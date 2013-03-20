package za.co.trf.recurly.api.rest;

import za.co.trf.recurly.api.rest.dto.response.Account;

import java.util.List;

/**
 * Service for interacting with Accounts through Recurly's RESTS API
 */
public interface RecurlyAccountWebService {

    /**
     * Get all Accounts
     */
    public List<Account> getAllAccounts();

    /**
     * Get a specific Account
     * @param accountCode the account code of the Account to retrieve
     */
    public Account getAccount(final String accountCode);

    /**
     * Close an Account
     * @param accountCode the account code of the Account to close
     * @return the updated account
     */
    public Account closeAccount(final String accountCode);

    /**
     * Reopen an Account
     * @param accountCode the account code of the Account to reopen
     * @return the updated account
     */
    public Account reopenAccount(final String accountCode);

}