package spreader.service.sender;

import static org.easymock.EasyMock.*;
import spreader.service.sender.CloneableSender;
import spreader.service.sender.MultithreadedProxySender;
import spreader.service.sender.ThreadSenderBuilder;
import junit.framework.TestCase;

public class ThreadSenderBuilderTest extends TestCase{
    
    public void testThreadSenderBuilder() {
        
        @SuppressWarnings("unchecked")
        CloneableSender<String> mock = createMock(CloneableSender.class);
        ThreadSenderBuilder<String> builder = new ThreadSenderBuilder<String>(mock);
        @SuppressWarnings("unchecked")
        MultithreadedProxySender<String> sender = createMock(MultithreadedProxySender.class);
        
        expect(mock.clone()).andReturn(mock).times(2);
        
        replay(mock);
        Thread thread1 = builder.buildThread(sender);
        Thread thread2 = builder.buildThread(sender);
        verify(mock);
        
        assertNotSame(thread1, thread2);
    }
}
