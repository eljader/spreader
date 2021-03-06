package spreader.service.sender;

import static org.easymock.EasyMock.*;
import spreader.service.sender.CloneableSender;
import spreader.service.sender.DefaultSender;
import spreader.service.sender.transport.ClonableTransport;
import junit.framework.TestCase;

public class DefaultSenderTest extends TestCase {
    
    private ClonableTransport<String> mock;
    
    @SuppressWarnings("unchecked")
    protected void setUp() {
        mock = createMock(ClonableTransport.class);
    }
    
    public void testStringSender() {
        CloneableSender<String> sender = new DefaultSender<String>(mock);
        String payload = "test_message";
        
        mock.sendPayload(payload);
        replay(mock);
        sender.send(payload);
        verify(mock);
    }
    
    public void testClone() {
        CloneableSender<String> sender1 = new DefaultSender<String>(mock);
        
        expect(mock.clone()).andReturn(mock);
        replay(mock);
        CloneableSender<String> sender2 = sender1.clone();
        verify(mock);
        
        assertNotSame(sender1, sender2);
    }
}
