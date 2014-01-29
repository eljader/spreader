package spreader.service.sender;

import static org.easymock.EasyMock.*;
import spreader.service.sender.ClonableClient;
import spreader.service.sender.CloneableSender;
import spreader.service.sender.StringSender;
import junit.framework.TestCase;

public class StringSenderTest extends TestCase {
    
    protected ClonableClient<String> mock;
    
    @SuppressWarnings("unchecked")
    protected void setUp() {
        mock = createMock(ClonableClient.class);
    }
    
    public void testStringSender() {

        CloneableSender<String> sender = new StringSender(mock);
        String payload = "test_message";
        
        mock.execute(payload);
        replay(mock);
        sender.send(payload);
        verify(mock);
    }
    
    public void testClone() {

        CloneableSender<String> sender1 = new StringSender(mock);
        
        expect(mock.clone()).andReturn(mock);
        replay(mock);
        CloneableSender<String> sender2 = sender1.clone();
        verify(mock);
        
        assertNotSame(sender1, sender2);
    }
}
