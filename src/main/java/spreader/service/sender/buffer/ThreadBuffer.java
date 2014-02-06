package spreader.service.sender.buffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import spreader.thread.ThreadSource;

public class ThreadBuffer<T> implements ThreadSource<T> {
    
    private Boolean last = false;
    private Boolean dead = false;    
    protected List<T> buffer;
    private int size;
    
    public ThreadBuffer(int size) {
        this.size = size;
        initBuffer();
    }
    
    public ThreadBuffer(int size, ArrayList<T> buffer) {
        this.size = size;
        this.buffer = buffer;
    }
    
    private void initBuffer() {
        buffer = new ArrayList<T>();
    }
    
    public void append(T payload) {
        buffer.add(payload);    
    }
    
    public Boolean isFull() {
        return buffer.size() >= size;
    }
              
    public void setLast(Boolean last) {
        this.last = last;
    }
        
    synchronized public List<T> extract() throws InterruptedException {         
        await();
        List<T> result = buffer;
        clear();
        makeDeadIfLast();
        return result;
    }
    
    private void await() throws InterruptedException {
        if (dead) throw new InterruptedException(); 
        while(!isFull() && !last) {/**wait**/}       
    }
    
    private void clear() {
        initBuffer();
    } 
    
    private void makeDeadIfLast() {
        dead = last;
    }
    
    @SuppressWarnings("unchecked")
    public ThreadBuffer<T> clone() {
        return new ThreadBuffer<T>(
            size, 
            new ArrayList<T>((Collection<? extends T>) Arrays.asList(buffer.toArray()))
        );
    }
}
