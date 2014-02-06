package spreader.service;

import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.List;

import spreader.service.converter.Converter;
import spreader.service.sender.Sender;
import spreader.thread.ThreadSource;
import junit.framework.TestCase;

public class DefaultThreadSenderServiceTest extends TestCase {
        
    private Converter<List<String>, String> converter;
    private Sender<String> sender;
    private ThreadSource<String> source;
    private Thread service; 
    private List<String> list = new ArrayList<String>();
    
    @SuppressWarnings("unchecked")
    public void setUp() {
        converter = createMock(Converter.class);
        sender = createMock(Sender.class);
        source = createMock(ThreadSource.class);
        service = new Thread(new DefaultThreadSenderService<String>(source, sender, converter));   
    }
    
    public void testRun() throws InterruptedException {
        expect(source.extract()).andReturn(list).anyTimes().andStubThrow(new InterruptedException());
        
        converter.setInput(list);
        expectLastCall().anyTimes();
        
        converter.convert();
        expectLastCall().anyTimes();
        
        expect(converter.getResult()).andReturn("test data").anyTimes();
        
        sender.send("test data");
        expectLastCall().anyTimes();
        
        replay(source, converter, sender);
        
        service.start();

        verify(source, converter, sender);
    }
}
