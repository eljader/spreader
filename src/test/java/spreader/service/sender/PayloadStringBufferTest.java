package spreader.service.sender;

import spreader.service.sender.PayloadStringBuffer;
import junit.framework.TestCase;

public class PayloadStringBufferTest extends TestCase{
    
    public void testAppend() {
        PayloadStringBuffer buffer = new PayloadStringBuffer();       
        
        buffer.append("test string1");       
        
        assertEquals("test string1", buffer.retrieveAll());
    }
    
    public void testAppendTwoTimes() {
        PayloadStringBuffer buffer = new PayloadStringBuffer();       
        
        buffer.append("test string1");  
        buffer.append("+");
        buffer.append("test string2"); 
        
        assertEquals("test string1+test string2", buffer.retrieveAll());
    }
    
    public void testClear() {
        PayloadStringBuffer buffer = new PayloadStringBuffer();       
        
        buffer.append("test string1");  
        buffer.clear();
        
        assertEquals("", buffer.retrieveAll());
    }
    
    public void testAppendClearAppend() {
        PayloadStringBuffer buffer = new PayloadStringBuffer();       
        
        buffer.append("test string1");  
        buffer.clear();
        buffer.append("test string2");  
        
        assertEquals("test string2", buffer.retrieveAll());
    }

}
