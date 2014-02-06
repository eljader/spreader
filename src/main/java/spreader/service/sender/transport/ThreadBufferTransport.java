package spreader.service.sender.transport;

import org.apache.log4j.Logger;

import spreader.service.sender.buffer.ThreadBuffer;

public class ThreadBufferTransport<T> implements ClonableTransport<T> {

    private ThreadBuffer<T> buffer;
    
    public ThreadBufferTransport(ThreadBuffer<T> buffer) {
        this.buffer = buffer;
    }
    
    public void sendPayload(T payload) {           
        while(buffer.isFull()) {/**wait**/}
        buffer.append(payload);
    }
    
  
    public ThreadBufferTransport<T> clone() {
        try {
            return new ThreadBufferTransport<T>(buffer.clone());                            
        } 
        catch (Exception e) { Logger.getLogger(this.getClass().getName()).error(e); }
        return null;
    }
}
