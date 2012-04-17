package za.co.trf.recurly;

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

}
