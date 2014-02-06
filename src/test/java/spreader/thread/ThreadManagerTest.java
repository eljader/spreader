package spreader.thread;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.junit.*;

import junit.framework.Assert;
import static org.easymock.EasyMock.*;

public class ThreadManagerTest extends Assert {
    
    private int maxThreads = 4;
    private ThreadManager<String> manager;
    private ThreadBuilder<String> builder;
    private Thread thread;
    
    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        thread = createMock(Thread.class);
        builder = createMock(ThreadBuilder.class);
        manager = new ThreadManager<String>(builder, maxThreads);
    }
    
    @Test
    public void testStartThreads() {
        expect(builder.buildThread()).andReturn(thread).times(maxThreads);  
        thread.start();
        expectLastCall().times(maxThreads);
        
        replay(builder, thread);
        
        manager.startThreads();
        
        verify(builder, thread);
    }
    
    @Test
    public void testIsThreadsFinishedTrue() {
        injectThreadsMock();       
        expect(thread.getState()).andReturn(Thread.State.TERMINATED).times(maxThreads);
        
        replay(thread);
        
        assertTrue(manager.isThreadsFinished());
        
        verify(thread);
    }
    
    @Test
    public void testIsThreadsFinishedFalse() {
        injectThreadsMock();               
        expect(thread.getState()).andReturn(Thread.State.TERMINATED).times(maxThreads - 1); 
        expect(thread.getState()).andReturn(Thread.State.RUNNABLE);
        
        replay(thread);
        
        assertFalse(manager.isThreadsFinished());
        
        verify(thread);
    }
    
    private void injectThreadsMock() {
        try { 
            Field field = manager.getClass().getDeclaredField("threads");            
            field.setAccessible(true);
            field.set(manager, getThreadsList());
        } 
        catch (Exception e) { e.printStackTrace(); }       
    }
        
    private ArrayList<Thread> getThreadsList() {
        ArrayList<Thread> list = new ArrayList<Thread>(); 
        for(int i=0; i < maxThreads; i++) list.add(thread);
        return list; 
    }
}
