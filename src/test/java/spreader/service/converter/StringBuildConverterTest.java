package spreader.service.converter;

import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;

import spreader.service.converter.StringBuildConverter;
import junit.framework.TestCase;

public class StringBuildConverterTest extends TestCase {
    
    protected Attributes input;
    
    protected void setUp() {
        AttributesImpl impl = new AttributesImpl();
        impl.addAttribute("", "localName1", "quotes", "type1", "value1");
        impl.addAttribute("", "localName2", "braces", "type2", "value2");
        impl.addAttribute("", "localName3", "empty", "type3", "");
        input = impl;
    }
    
    public void testStringBuildConverter() {        
        StringBuildConverter converter = 
                new StringBuildConverter("'#<quotes>' + (#<braces>) + #<empty>");
        converter.setInput(input);
        converter.convert();        
        assertEquals("substituted values", "'value1' + (value2) + ", converter.getResult());
    }    
}


