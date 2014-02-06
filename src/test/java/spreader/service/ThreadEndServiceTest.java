package spreader.service;

import spreader.NullAttributes;
import spreader.SAXElementStructData;
import spreader.service.sender.buffer.ThreadBuffer;
import spreader.thread.ThreadManager;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;

public class ThreadEndServiceTest extends TestCase {
    
    private ThreadManager<String> manager;
    private ThreadBuffer<String> buffer;
    private ThreadEndService<String> service;
    
    @SuppressWarnings("unchecked")
    public void setUp() {
        manager = createMock(ThreadManager.class);
        buffer = createMock(ThreadBuffer.class);
        service = new ThreadEndService<String>(manager, buffer, new String[]{"testNodeName"});
    }
    
    public void testSetData() {
        /**do nothing**/
        replay(manager, buffer);
        SAXService dummuyService = 
                service.setData(new SAXElementStructData("", "", "", new NullAttributes()));  
        SAXService sameService = 
                service.setData(new SAXElementStructData("", "", "testNodeName", new NullAttributes())); 
        verify(manager, buffer);
        
        assertTrue(dummuyService instanceof DummySAXService);
        assertSame(service, sameService);
    }
    
    public void testRun() {
        buffer.setLast(true);    
        expect(manager.isThreadsFinished()).andReturn(true).once();
                
        replay(buffer, manager);
        service.run();
        verify(buffer, manager);
    }
    
    public void testRunWaitAllThreadsFinished() {
        expect(manager.isThreadsFinished()).andReturn(false).anyTimes().andStubReturn(true);
               
        replay(manager);
        Thread thread = new Thread(service);
        thread.start();
        verify(manager);
    }
}
