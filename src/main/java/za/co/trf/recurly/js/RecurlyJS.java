package za.co.trf.recurly.js;

import za.co.trf.recurly.*;
import za.co.trf.recurly.SimpleKeyProvider;
import za.co.trf.recurly.KeyProvider;
import za.co.trf.recurly.RecurlyConstants;
import za.co.trf.recurly.api.entity.AddOn;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Java implementation of recurly_js.php
 */
public class RecurlyJS {

    private static final int ONE_HOUR_IN_MS = 3600000;

    private KeyProvider keyProvider;

    public RecurlyJS(String privateKey) {
        keyProvider = new SimpleKeyProvider(privateKey);
    }

    public RecurlyJS(KeyProvider keyProvider) {
        this.keyProvider = keyProvider;
    }

    /**
     * Create a signature for the subscription form.
     * @param planCode the planCode of the plan being subscribed to
     * @param accountCode the accountCode of the account being updated
     * @return the signature
     */
    public String signSubscription(String planCode, String accountCode) {
        return signSubscription(planCode, accountCode, null);
    }

    public String signSubscription(String planCode, String accountCode, Date date) {
        // Validate required parameters
        if (Util.isAnyEmpty(planCode, accountCode)) {
            throw new IllegalArgumentException("Both Plan and Account codes are required");
        }

        // Add required parameters to map
        SortedMap<String, Object> args = new TreeMap<String, Object>();
        args.put(RecurlyConstants.RECURLY_JS_PARAM_PLAN_CODE, planCode);
        args.put(RecurlyConstants.RECURLY_JS_PARAM_ACCOUNT_CODE, accountCode);

        return generateSignature(RecurlyConstants.RECURLY_JS_CLAIM_SUBSCRIPTION_CREATE, args, date);
    }

    /**
     * Verify that the Recurly response for a subscription is valid.
     * @param signature the signature as calculated by Recurly and included in response
     * @param accountCode the accountCode
     * @param planCode the planCode
     * @param quantity the quantity
     * @param couponCode the couponCode (optional if null)
     * @param addOns a list of AddOns (optional if null or empty)
     * @return true if the signature and arguments are valid, otherwise false
     */
    public boolean verifySubscription(String signature, String accountCode,
                                      String planCode, String quantity, String couponCode, List<AddOn> addOns) {
        // Validate required parameters
        if (Util.isAnyEmpty(signature, accountCode, planCode, quantity)) {
            throw new IllegalArgumentException("Signature, " +
                    RecurlyConstants.RECURLY_JS_PARAM_ACCOUNT_CODE + ", " +
                    RecurlyConstants.RECURLY_JS_PARAM_PLAN_CODE + ", and/or " +
                    RecurlyConstants.RECURLY_JS_PARAM_QUANTITY + " not present.");
        }

        // Add required parameters to map
        SortedMap<String, Object> args = new TreeMap<String, Object>();
        args.put(RecurlyConstants.RECURLY_JS_PARAM_ACCOUNT_CODE, accountCode);
        args.put(RecurlyConstants.RECURLY_JS_PARAM_PLAN_CODE, planCode);
        args.put(RecurlyConstants.RECURLY_JS_PARAM_QUANTITY, quantity);

        // Add coupon parameters if provided
        if (couponCode != null && !couponCode.isEmpty()) {
            args.put(RecurlyConstants.RECURLY_JS_PARAM_COUPON_CODE, couponCode);
        }

        // Add add-on parameters if provided
        if (addOns != null && !addOns.isEmpty()) {
            SortedMap<String, Object> addOnsMap = new TreeMap<String, Object>();
            for (int i = 0; i < addOns.size(); i++) {
                SortedMap<String, Object> addOn = new TreeMap<String, Object>();
                addOn.put(RecurlyConstants.RECURLY_JS_PARAM_ADD_ON_CODE, addOns.get(i).addOnCode);
                addOn.put(RecurlyConstants.RECURLY_JS_PARAM_QUANTITY, String.valueOf(addOns.get(i).quantity));

                addOnsMap.put(String.format(RecurlyConstants.RECURLY_JS_PARAM_LIST_ARG, i), addOn);
            }
            args.put(RecurlyConstants.RECURLY_JS_PARAM_ADD_ONS, addOnsMap);
        }

        return verifyResults(RecurlyConstants.RECURLY_JS_CLAIM_SUBSCRIPTION_CREATED, signature, args);
    }

    /**
     * Create a signature for a one-time transaction.
     * @param amountInCents the transaction amount in cents
     * @param currency the 3 digit code of the currency the transaction is being conducted in
     * @param accountCode the accountCode of the account being updated
     * @return the signature
     */
    public String signTransaction(int amountInCents, String currency, String accountCode) {
        return signTransaction(amountInCents, currency, accountCode, null);
    }

    public String signTransaction(int amountInCents, String currency, String accountCode, Date date) {
        // Validate currency parameter
        if (currency == null || currency.length() != 3) {
            throw new IllegalArgumentException("Invalid currency");
        }

        // Validate amountInCents parameter
        if (amountInCents <= 0) {
            throw new IllegalArgumentException("Invalid amount in cents");
        }

        // Add required parameters to map
        SortedMap<String, Object> args = new TreeMap<String, Object>();
        args.put(RecurlyConstants.RECURLY_JS_PARAM_ACCOUNT_CODE, accountCode);
        args.put(RecurlyConstants.RECURLY_JS_PARAM_AMOUNT_IN_CENTS, String.valueOf(amountInCents));
        args.put(RecurlyConstants.RECURLY_JS_PARAM_CURRENCY, currency);

        return generateSignature(RecurlyConstants.RECURLY_JS_CLAIM_TRANSACTION_CREATE, args, date);
    }

    /**
     * Verify that the Recurly response for a one-time transaction is valid.
     * @param signature the signature as calculated by Recurly and included in response
     * @param accountCode the accountCode
     * @param amountInCents the transaction amount in cents
     * @param currency the 3 digit code of the currency the transaction is being conducted in
     * @param uuid the uuid
     * @return true if the signature and arguments are valid, otherwise false
     */
    public boolean verifyTransaction(String signature,
                                     String accountCode, String amountInCents, String currency, String uuid) {
        // Validate required parameters
        if (Util.isAnyEmpty(signature, accountCode, amountInCents, currency, uuid)){
            throw new IllegalArgumentException("Signature, " +
                    RecurlyConstants.RECURLY_JS_PARAM_ACCOUNT_CODE + ", " +
                    RecurlyConstants.RECURLY_JS_PARAM_AMOUNT_IN_CENTS + ", " +
                    RecurlyConstants.RECURLY_JS_PARAM_CURRENCY + ", and/or " +
                    RecurlyConstants.RECURLY_JS_PARAM_UUID + " not present.");
        }

        // Add required parameters to map
        SortedMap<String, Object> args = new TreeMap<String, Object>();
        args.put(RecurlyConstants.RECURLY_JS_PARAM_ACCOUNT_CODE, accountCode);
        args.put(RecurlyConstants.RECURLY_JS_PARAM_AMOUNT_IN_CENTS, amountInCents);
        args.put(RecurlyConstants.RECURLY_JS_PARAM_CURRENCY, currency);
        args.put(RecurlyConstants.RECURLY_JS_PARAM_UUID, uuid);

        return verifyResults(RecurlyConstants.RECURLY_JS_CLAIM_TRANSACTION_CREATED, signature, args);
    }

    /**
     * Create a signature for updating billing information.
     * @param accountCode the accountCode of the account being updated
     * @return the signature
     */
    public String signBillingInfoUpdate(String accountCode) {
        return signBillingInfoUpdate(accountCode, null);
    }

    public String signBillingInfoUpdate(String accountCode, Date date) {
        // Validate required parameters
        if (Util.isAnyEmpty(accountCode)) {
            throw new IllegalArgumentException("Account code is required");
        }

        // Add required parameters to map
        SortedMap<String, Object> args = new TreeMap<String, Object>();
        args.put(RecurlyConstants.RECURLY_JS_PARAM_ACCOUNT_CODE, accountCode);

        return generateSignature(RecurlyConstants.RECURLY_JS_CLAIM_BILLING_INFO_UPDATE, args, date);
    }

    /**
     * Verify that the Recurly response for a billing information updateAccount is valid.
     * @param signature the signature as calculated by Recurly and included in response
     * @param accountCode the accountCode
     * @return true if the signature and arguments are valid, otherwise false
     */
    public boolean verifyBillingInfoUpdated(String signature, String accountCode) {
        // Validate required parameters
        if (Util.isAnyEmpty(signature, accountCode)) {
            throw new IllegalArgumentException("Signature and " +
                    RecurlyConstants.RECURLY_JS_PARAM_ACCOUNT_CODE + " not present.");
        }

        // Add required parameters to map
        SortedMap<String, Object> args = new TreeMap<String, Object>();
        args.put(RecurlyConstants.RECURLY_JS_PARAM_ACCOUNT_CODE, accountCode);

        return verifyResults(RecurlyConstants.RECURLY_JS_CLAIM_BILLING_INFO_UPDATED, signature, args);
    }

    /**
     * Create a signature using the private key and given map of arguments
     * @param claim the RecurlyJS operation type
     * @param args the map of arguments
     * @param timestamp the date of the transaction. If null, the current date will be used.
     * @return the signature
     */
    protected String generateSignature(String claim, SortedMap<String, Object> args, Date timestamp) {
        if (timestamp == null) {
            timestamp = new Date();
        }

        long timestampString = (timestamp.getTime() / 1000);
        String argsFlattened = flatten(args);
        String message = String.format("[%s,%s,[%s]]", timestampString, claim, argsFlattened);

        return hash(getPrivateKey(), message) + '-' + timestampString;
    }

    /**
     * Validate the response and parameters returned byt Recurly are authentic. This is achieved by re-creating the
     * signature using the known parameters and private key, and comparing it to the signature returned with Recurly's
     * response.
     * @param claim the RecurlyJS operation type
     * @param signature the signature received from Recurly to be verified
     * @param args the arguments to use for verification
     * @return true if the given signature is valid, otherwise false
     */
    protected boolean verifyResults(String claim, String signature, SortedMap<String, Object> args) {
        int pos = signature.indexOf('-');

        // Signature is invalid if the timestamp is not appended
        if (pos == -1) {
            return false;
        }

        Date timestamp = new Date(Long.parseLong(signature.substring(pos + 1)) * 1000);
        long timeDiff = timeDifference(timestamp);

        // Signature is invalid if it is more than an hour old
        if (timeDiff > ONE_HOUR_IN_MS || timeDiff < -ONE_HOUR_IN_MS) {
            return false;
        }

        // Check that the given and calculated signatures match
        String expectedSignature = generateSignature(claim, args, timestamp);
        return signature.equals(expectedSignature);
    }

    /**
     * Hash a given message using a given private key.
     * @param privateKey the private key to be used
     * @param message the message to be hashed
     * @return the hash
     */
    protected String hash(String privateKey, String message) {
        if (message == null) {
            throw new IllegalArgumentException("Message is not set.");
        }

        if (privateKey == null || privateKey.length() != 32) {
            throw new IllegalArgumentException("Recurly private key is not set. The key must be 32 characters.");
        }

        try {
            Mac mac = Mac.getInstance(RecurlyConstants.HMAC_SHA1);
            SecretKeySpec secret = new SecretKeySpec(sha(privateKey), RecurlyConstants.HMAC_SHA1);
            mac.init(secret);
            byte[] digest = mac.doFinal(message.getBytes());

            StringBuilder hash = new StringBuilder();
            for (byte b : digest) {
                hash.append(String.format("%02x", b));
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RecurlyJSException(e);
        } catch (InvalidKeyException e) {
            throw new RecurlyJSException(e);
        }
    }

    /**
     * Get the private key to be used when creating a signature.
     * Protected so that it may be overridden for testing purposes.
     * @return the private key
     */
    protected String getPrivateKey() {
        return keyProvider.getPrivateKey();
    }

    /**
     * Determine the difference in ms between the current time and a given time.
     * Protected so that it may be overridden for testing purposes.
     * @param timestamp the time to be subtracted
     * @return the difference in ms
     */
    protected long timeDifference(Date timestamp) {
        return new Date().getTime() - timestamp.getTime();
    }

    /**
     * Recursively flatten a Map of arguments.
     * @param args the arguments to be flattened
     * @return the flattened arguments as a String
     */
    protected String flatten(SortedMap<String, Object> args) {
        StringBuilder argsFlattened = new StringBuilder();
        for (String key : args.keySet()) {
            Object value = args.get(key);
            if (value != null) {
                if (value instanceof SortedMap) {
                    // Check if the argument is just an ordered list with no keys rather than a map,
                    // in which case the key should not be included.
                    if (!key.startsWith(RecurlyConstants.RECURLY_JS_PARAM_LIST_ARG_PREFIX)) {
                        argsFlattened.append(key).append(":");
                    }
                    //noinspection unchecked
                    String recursivelyFlattenedArgs = flatten((SortedMap<String, Object>)value);
                    argsFlattened.append(String.format("[%s],", recursivelyFlattenedArgs));
                } else if (value instanceof String) {
                    String valueAsString = (String)value;
                    if (!valueAsString.isEmpty()) {
                        argsFlattened.append(String.format("%s:%s,", key, valueAsString));
                    }
                } else {
                    throw new IllegalArgumentException("The value of an argument was neither a String nor SortedMap");
                }
            }
        }

        // Remove trailing ',' and return
        String argsFlattenedString = argsFlattened.toString();
        return argsFlattenedString.substring(0, argsFlattenedString.length() - 1);
    }

    /**
     * Hash a given String using the SHA1 cryptographic hash function.
     * @param stringToHash the string to be hashed
     * @return the hashed string as a byte[]
     */
    protected byte[] sha(String stringToHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(stringToHash.getBytes("UTF-8"));
            return md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RecurlyJSException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RecurlyJSException(e);
        }
    }
}
