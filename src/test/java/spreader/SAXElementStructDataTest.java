package spreader;

import spreader.NullAttributes;
import spreader.SAXElementStructData;
import junit.framework.TestCase;

public class SAXElementStructDataTest extends TestCase {
    
    private String uri = "http//:test.com";
    private String name = "test-name";
    private String nodeName = "test-node-name";
    private NullAttributes attrs = new NullAttributes(); 
        
    public void testSAXElementStructData() {       
        SAXElementStructData data = new SAXElementStructData(uri, name, nodeName, attrs);
        
        assertEquals("uri", uri, data.getUri());
        assertEquals("name", name, data.getName());
        assertEquals("nodeName", nodeName, data.getNodeName());
        assertEquals("attrs", attrs, data.getAttrs());
    }
    
    public void testEquals() {     
        SAXElementStructData data1 = new SAXElementStructData(uri, name, nodeName, attrs);
        SAXElementStructData data2 = new SAXElementStructData(uri, name, nodeName, attrs);
        SAXElementStructData data3 = new SAXElementStructData(uri, name, nodeName, new NullAttributes());
        SAXElementStructData data4 = new SAXElementStructData("", "", "", attrs);
            
        assertTrue("equals", data1.equals(data2));
        assertTrue("equals NullAttributes", data1.equals(data3));
        assertFalse("not equals", data1.equals(data4));
    }
}
