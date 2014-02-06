package spreader.thread;

import static org.easymock.EasyMock.*;

import java.util.List;

import spreader.service.converter.Converter;
import spreader.service.sender.CloneableSender;
import junit.framework.TestCase;

public class DefaultThreadSenderBuilderTest extends TestCase{
    
    private ThreadSource<String> buffer;
    private CloneableSender<String> sender;
    private Converter<List<String>, String> converter;                   
    private DefaultThreadSenderBuilder<String> builder;
    
    @SuppressWarnings("unchecked")
    public void setUp() {
        buffer = createMock(ThreadSource.class);   
        sender = createMock(CloneableSender.class);
        converter = createMock(Converter.class);         
        builder = new DefaultThreadSenderBuilder<String>(buffer, sender, converter);     
    }
           
    public void testBuildThread() {                           
        expect(sender.clone()).andReturn(sender).times(2);
        
        replay(sender, buffer, converter);
        
        Thread thread1 = builder.buildThread();
        Thread thread2 = builder.buildThread();
        
        verify(sender, buffer, converter);
        
        assertNotSame(thread1, thread2);        
    }
}
