package spreader.service.converter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

public class ListToStringConverterTest extends TestCase {
    
    private List<String> list;
    
    public void setUp() {
        list = new ArrayList<String>(Arrays.asList("line1", "line2", "line3"));
    }
    
    public void testConverterWithDefaultConstructor() {
        String delimiter = System.getProperty("line.separator");        
        assertEquals(
            "line1" + delimiter + "line2" + delimiter + "line3", prepareConverter(null).getResult());
    }
    
    public void testConverterWithDelimeterConstructor() {      
        assertDelimiter("1");
        assertDelimiter("11");
        assertDelimiter("abc");
        assertDelimiter("abc1");
        assertDelimiter("+");
        assertDelimiter("-");
        assertDelimiter("*");
        assertDelimiter("/");
        assertDelimiter("%");
        assertDelimiter("'");
        assertDelimiter("\"");
        assertDelimiter("\\");
        assertDelimiter(" ");
        assertDelimiter("(");
        assertDelimiter(")");
        assertDelimiter("()");
        assertDelimiter("@");
    }
    
    private void assertDelimiter(String delimiter) {
        assertEquals(
                "line1" + delimiter + "line2" + delimiter + "line3", 
                prepareConverter(delimiter).getResult()
        );
    }
    
    private ListToStringConverter prepareConverter(String delimiter) {
        ListToStringConverter converter = 
                (delimiter == null) ? new ListToStringConverter() : new ListToStringConverter(delimiter);
        converter.setInput(list);
        converter.convert();
        return converter;
    }
}
