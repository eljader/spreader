package spreader.service.sender;

import java.util.ArrayList;
import java.util.List;

public class MultithreadedProxySender <T> implements Sender<T>, Extractable<T> {
        
    private int rate;
    private int counter = 0;
    private int maxThreads;
    private PayloadBuffer<T> buffer;
    private List<Thread> threads = new ArrayList<Thread>();
    private ThreadSenderBuilder<T> builder;
        
    public MultithreadedProxySender(ThreadSenderBuilder<T> builder, PayloadBuffer<T> buffer, int rate, int maxThreads) {
        this.builder = builder;
        this.buffer = buffer;
        this.rate = rate;
        this.maxThreads = maxThreads;
    }

    public void send(T payload) { 
        while(isFullbuffer()) { if(threads.size() < maxThreads) addTheardAndStart(); }
        addToBuffer(payload);       
    }
        
    private Boolean isFullbuffer() {
        return counter >= rate;
    }
    
    private void addTheardAndStart() {    
        Thread thread = builder.buildThread(this);
        thread.start();
        threads.add(thread);
    }
    
    private void addToBuffer(T payload) {
        counter++;
        buffer.append(payload);
    }
    
    synchronized public T extract() {
        while(!isFullbuffer()) {/**wait**/}
        T payload = buffer.retrieveAll();
        resetBuffer();
        return payload;
    } 
        
    private void resetBuffer() {
        counter = 0;
        buffer.clear(); 
    }
}
