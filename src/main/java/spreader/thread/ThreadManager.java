package spreader.thread;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager<T> {
    
    private ThreadBuilder<T> builder;
    private List<Thread> threads = new ArrayList<Thread>();
    private int maxThreads;
    
    public ThreadManager(ThreadBuilder<T> builder, int maxThreads) {
        this.builder = builder;
        this.maxThreads = maxThreads;
    }
    
    public void startThreads() {
        while(threads.size() < maxThreads) { 
            Thread thread = builder.buildThread();            
            thread.start();
            threads.add(thread);
        }
    }
        
    public Boolean isThreadsFinished() {
        for(Thread thread : threads) {
            if(thread.getState() != Thread.State.TERMINATED) return false;  
        }
        return true;
    }
}
