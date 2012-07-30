package za.co.trf.recurly;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Test;

public class HttpBuildQueryTest {

    @Test
    public void testEmpty() {
        assertEquals("", Util.httpBuildQuery(null, null));
        assertEquals("", Util.httpBuildQuery(new HashMap<String, Object>(), null));
    }

    @Test
    public void testJoin() {
        assertEquals("", Util.join(null, "&"));
        assertEquals("", Util.join(new ArrayList<String>(Arrays.asList(new String[]{})), "&"));
        assertEquals("foo", Util.join(new ArrayList<String>(Arrays.asList(new String[]{"foo"})), "&"));
        assertEquals("foo&bar", Util.join(new ArrayList<String>(Arrays.asList(new String[]{"foo", "bar"})), "&"));
    }

    @Test
    public void testSimple() {
        assertEquals("foo=bar", Util.httpBuildQuery(new HashMap<String, Object>() {{
            put("foo", "bar");
        }}, null));
        assertEquals("baz=boom&cow=milk&foo=bar", Util.httpBuildQuery(new HashMap<String, Object>() {{
            put("foo", "bar");
            put("baz", "boom");
            put("cow", "milk");
        }}, null));
    }

    @Test
    public void testEncoding() {
        assertEquals("php=hypertext+processor", Util.httpBuildQuery(new HashMap<String, Object>() {{
            put("php", "hypertext processor");
        }}, null));
    }

    @Test
    public void testComplex() {
        HashMap<String, Object> data = new HashMap<String, Object>() {{
            put("user", new HashMap<String, Object>() {{
                put("name", "Bob Smith");
                put("age", 47);
                put("sex", 'M');
                put("dob", "5/12/1956");
            }});
            put("pastimes", new String[]{"golf", "opera", "poker", "rap"});
            put("children", new HashMap<String, Object>() {{
                put("bobby", new HashMap<String, Object>() {{
                    put("age", 12);
                    put("sex", 'M');
                }});
                put("sally", new HashMap<String, Object>() {{
                    put("age", 8);
                    put("sex", 'F');
                }});
            }});
        }};
        assertEquals(
            "children%5Bbobby%5D%5Bage%5D=12&" +
            "children%5Bbobby%5D%5Bsex%5D=M&" +
            "children%5Bsally%5D%5Bage%5D=8&" +
            "children%5Bsally%5D%5Bsex%5D=F&" +
            "pastimes%5B0%5D=golf&pastimes%5B1%5D=opera&pastimes%5B2%5D=poker&pastimes%5B3%5D=rap&" +
            "user%5Bage%5D=47&" +
            "user%5Bdob%5D=5%2F12%2F1956&" +
            "user%5Bname%5D=Bob+Smith&" +
            "user%5Bsex%5D=M",
            Util.httpBuildQuery(data, null));
    }
}
