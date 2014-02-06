package spreader.service.converter;

import java.util.HashMap;

import spreader.service.converter.StringBuildConverter;
import junit.framework.TestCase;

public class StringBuildConverterTest extends TestCase {
    
    private HashMap<String, String> input;
    private StringBuildConverter converter;
    
    protected void setUp() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("quotes", "1");
        map.put("braces", "2");
        map.put("empty",  "");
        
        input = map;        
        converter = new StringBuildConverter("'#<quotes>' + (#<braces>) + #<empty>");
    }
    
    public void testStringBuildConverter() {        
        converter.setInput(input);
        converter.convert();        
        assertEquals("substituted values", "'1' + (2) + ", converter.getResult());
    }    
    
    public void testSetInputEmpty() {
        converter.setInput(new HashMap<String, String>());
        converter.convert();        
        assertEquals("substituted values", "'' + () + ", converter.getResult());
    }
}


