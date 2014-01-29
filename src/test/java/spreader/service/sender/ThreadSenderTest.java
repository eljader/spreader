package spreader.service.sender;

import spreader.service.sender.CloneableSender;
import spreader.service.sender.MultithreadedProxySender;
import spreader.service.sender.ThreadSender;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;

public class ThreadSenderTest extends TestCase{
    
    public void testThreadSender() {
        
        @SuppressWarnings("unchecked")
        CloneableSender<String> sender = createMock(CloneableSender.class);
        @SuppressWarnings("unchecked")
        MultithreadedProxySender<String> proxySender = createMock(MultithreadedProxySender.class);
        ThreadSender<String> threadSender = new ThreadSender<String>(proxySender, sender, false);
        
        expect(proxySender.extract()).andReturn("test string").once();        
        sender.send("test string");
        replay(sender, proxySender);
        threadSender.run();
        verify(sender, proxySender);
    }
}
