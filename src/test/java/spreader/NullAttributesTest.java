package spreader;

import spreader.NullAttributes;
import junit.framework.TestCase;

public class NullAttributesTest extends TestCase {

    protected NullAttributes attr;
    
    protected void setUp() {
       attr =  new NullAttributes();       
    }
    
    public void testGetIndex() {      
        assertEquals("getIndex 0", 0, attr.getIndex("test"));
        assertEquals("getIndex 0", 0, attr.getIndex("http//:test.com", "test"));
    }

    public void testGetLength() {
        assertEquals("getLength 0", 0, attr.getLength());
    }

    public void getLocalName() {
        assertEquals("getLocalName null", null, attr.getLocalName(1));
        assertEquals("getLocalName null", null, attr.getLocalName(2));
    }

    public void testGetQName() {
        assertEquals("getQName null", null, attr.getQName(1));
        assertEquals("getQName null", null, attr.getQName(2));
    }

    public void testGetType() {  
        assertEquals("getType null", null, attr.getType(1));
        assertEquals("getType null", null, attr.getType(2));
        assertEquals("getType null", null, attr.getType("test"));
        assertEquals("getType null", null, attr.getType("http//:test.com", "test"));
    }

    public void testGetURI() {
        assertEquals("getURI null", null, attr.getURI(1));
        assertEquals("getURI null", null, attr.getURI(2));
    }

    public void testGetValue(int index) {  
        assertEquals("getValue null", null, attr.getValue(1));
        assertEquals("getValue null", null, attr.getValue(2));
        assertEquals("getValue null", null, attr.getValue("test"));
        assertEquals("getValue null", null, attr.getValue("http//:test.com", "test"));
    }
}
