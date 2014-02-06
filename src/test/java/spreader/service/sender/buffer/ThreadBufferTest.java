package spreader.service.sender.buffer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class ThreadBufferTest extends Assert {
    
    private List<String> list;
    private ThreadBuffer<String> buffer; 
    private List<String> callback; 
    
    @Before
    public void setUp() {
        list = new ArrayList<String>(Arrays.asList("line1", "line2", "line3", "line4", "line5"));       
    }
    
    @Test
    public void testAppendAndExtract() throws InterruptedException {
        initBuffer(list.size());      
        assertEquals(list, buffer.extract());
    }
    
    @Test
    public void testIsFull() {        
        initBuffer(list.size()); 
        assertTrue(buffer.isFull());
        
        initBuffer(list.size() + 1);
        assertFalse(buffer.isFull());
        
        assertTrue(new ThreadBuffer<String>(0).isFull());
        assertFalse(new ThreadBuffer<String>(10).isFull());
    }
    
    @Test
    public void testLockExtractWhenBufferNotFull() throws InterruptedException {
        initBuffer(list.size() + 1);                  
        Thread thread = getThread();
        thread.start();
        thread.join(10);
        
        assertTrue(thread.isAlive());
    }
    
    @Test
    public void testUnlockExtractWhenSetLastTrue() throws InterruptedException {
        initBuffer(list.size() + 1);        
        Thread thread = getThread();
        thread.start();
        buffer.setLast(true);
        thread.join();
        
        assertEquals(list, callback);
    }
    
    @Test(expected = InterruptedException.class) 
    public void testInterruptedExceptionAfterLastExtract() throws InterruptedException {                  
        initBuffer(list.size() + 1);    
        buffer.setLast(true);
                
        assertEquals(list, buffer.extract());
        buffer.extract();
    }
    
    @Test 
    public void testClone() throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        
        initBuffer(list.size());
        ThreadBuffer<String> clone = buffer.clone();
        
        Field fieldSize = buffer.getClass().getDeclaredField("size");
        Field fieldBuffer = buffer.getClass().getDeclaredField("buffer");
        fieldSize.setAccessible(true);
        fieldBuffer.setAccessible(true);
        
        assertNotSame(buffer, clone);
        assertEquals(fieldSize.get(buffer), fieldSize.get(clone));
        assertEquals(fieldBuffer.get(buffer), fieldBuffer.get(clone));
        assertNotSame(fieldBuffer.get(buffer), fieldBuffer.get(clone));
    }
    
    private void initBuffer(int size) {
        buffer =  new ThreadBuffer<String>(size);
        for(String value : list) buffer.append(value);
    }
    
    private Thread getThread() {
        return new Thread(new Runnable() {            
            public void run() {
                try { 
                    callback = buffer.extract(); 
                } 
                catch (InterruptedException e) {}                
            }          
        });
    }
}
