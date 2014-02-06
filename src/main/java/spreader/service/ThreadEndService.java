package spreader.service;

import spreader.SAXElementStructData;
import spreader.service.sender.buffer.ThreadBuffer;
import spreader.thread.ThreadManager;

public class ThreadEndService<T> extends BaseSAXService {
    
    ThreadManager<T> manager;
    ThreadBuffer<T> buffer;
       
    public ThreadEndService(ThreadManager<T> manager, ThreadBuffer<T> buffer, String[] targetNodes) {
        super(targetNodes);
        this.manager = manager;
        this.buffer = buffer;       
    }
        
    @Override
    protected void handleData(SAXElementStructData data) {/**__**/}

    @Override
    protected SAXService getDummy() {
        return new DummySAXService();
    }

    public void run() {      
        buffer.setLast(true);
        while(!manager.isThreadsFinished()) {/**wait**/}
    }
}
