package spreader.service.sender.transport;

import java.lang.reflect.Field;

import org.junit.*;

import spreader.service.sender.buffer.ThreadBuffer;
import junit.framework.Assert;
import static org.easymock.EasyMock.*;

public class ThreadBufferTransportTest extends Assert {

    ThreadBufferTransport<String> transport;
    ThreadBuffer<String> buffer;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        buffer = createMock(ThreadBuffer.class);
        transport = new ThreadBufferTransport<String>(buffer);
    }
    
    @Test
    public void testSendPayloadWaitWhenBufferIsFull() throws InterruptedException {
        expect(buffer.isFull()).andReturn(true).anyTimes();
        replay(buffer);
        
        Thread thread = new Thread(new Runnable(){        
            public void run() {
                transport.sendPayload("test data");
            }           
        });
        thread.start();
        thread.join(10);
        
        assertTrue(thread.isAlive());
        
        verify(buffer);
    }
    
    @Test
    public void testSendPayloadWhenBufferIsEmpty() {
        expect(buffer.isFull()).andReturn(false);
        buffer.append("test data");
        replay(buffer);
        transport.sendPayload("test data");
        verify(buffer);
    }
    
    @Test 
    public void testClone() throws NoSuchFieldException, SecurityException, 
            IllegalArgumentException, IllegalAccessException {
        
        ThreadBufferTransport<String> transport1 = 
                new ThreadBufferTransport<String>(new ThreadBuffer<String>(10));
        ThreadBufferTransport<String> transport2 = transport1.clone();  
        
        Field bufferField = transport1.getClass().getDeclaredField("buffer"); 
        bufferField.setAccessible(true);
                
        assertNotSame(transport1, transport2);
        assertNotSame(bufferField.get(transport1), bufferField.get(transport2));
        assertTrue(transport2 instanceof ThreadBufferTransport);
        assertTrue(bufferField.get(transport2) instanceof ThreadBuffer);
    }
}
