package spreader.service.sender;

import static org.easymock.EasyMock.*;
import spreader.service.sender.MultithreadedProxySender;
import spreader.service.sender.PayloadBuffer;
import spreader.service.sender.ThreadSenderBuilder;
import junit.framework.TestCase;

public class MultithreadedProxySenderTest extends TestCase{

    PayloadBuffer<String> buffer;
    ThreadSenderBuilder<String> builder;
    MultithreadedProxySender<String> proxySender;
    
    @SuppressWarnings("unchecked")
    public void setUp() {       
        buffer = createMock(PayloadBuffer.class);
        builder = createMock(ThreadSenderBuilder.class);
        proxySender = new MultithreadedProxySender<String>(builder, buffer, 1, 1);
    }
      
    public void testAppendtoBufferAndExtract() {          
        buffer.append("test data");
        expect(buffer.retrieveAll()).andReturn("test data").once();
        buffer.clear();
        replay(buffer);
        proxySender.send("test data");
        assertEquals("test data", proxySender.extract());
        verify(buffer);
    }
}
