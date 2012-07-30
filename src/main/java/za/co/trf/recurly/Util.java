package za.co.trf.recurly;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {

    /**
     * Determine if any of a list of arguments are null or empty
     * @param args arguments to check
     * @return true if any of the provided arguments are null or empty, false if no arguments are null or empty 
     */
    public static boolean isAnyEmpty(String... args) {
        for (String arg : args) {
            if (arg == null || arg.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /**
     * An implementation of PHP's http_build_query. This is central to the Recurly.js
     * signature scheme.
     * @see <a href="http://docs.recurly.com/api/recurlyjs/signatures>Recurly.js Signature Generation</a>
     * @see <a href="http://php.net/manual/en/function.http-build-query.php">PHP's http_build_query</a>
     * @param args
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
	public static String httpBuildQuery(Map<String, Object> args, String key) {
        final String ENCODING = "UTF8";

        ArrayList<String> pairs = new ArrayList<String>();
        if (args != null) {
            String[] sortedKeys = args.keySet().toArray(new String[0]);
            Arrays.sort(sortedKeys);
            for (String k: sortedKeys) {
                try {
                    Object obj = args.get(k);
                    if (key != null) {
                        k = key + "[" + URLEncoder.encode(k, ENCODING) + "]";
                    }

                    if (obj instanceof Map<?,?>) {
                        pairs.add(httpBuildQuery((Map<String, Object>)obj, k));
                    } else if (obj instanceof Object[]) {
                        Object[] values = (Object[])obj;
                        HashMap<String, Object> mappedArgs = new HashMap<String, Object>();
                        for (int i = 0; i < values.length; i++) {
                            mappedArgs.put(Integer.toString(i), values[i]);
                        }
                        pairs.add(httpBuildQuery(mappedArgs, k));
                    } else {
                        pairs.add(URLEncoder.encode(k, ENCODING) + '=' + URLEncoder.encode(obj.toString(), ENCODING));
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        }
        return join(pairs, "&");
    }

    /**
     * Joins the list of strings by the given separator.
     * This is a common utility method that can be found in many libraries. It's added
     * here because it's small enough to make an additional dependency unnecessary.
     * @param values the list of strings to join
     * @param separator the string to place between each of the values
     * @return a string of the joined values
     */
    public static String join(List<String> values, String separator) {
        StringBuilder sb = new StringBuilder();
        if (values != null) {
            for (String value: values) {
                if (sb.length() > 0) {
                    sb.append(separator);
                }
                sb.append(value);
            }
        }
        return sb.toString();
    }
}
