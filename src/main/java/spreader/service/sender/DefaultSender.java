package spreader.service.sender;

import spreader.service.sender.transport.ClonableTransport;

public class DefaultSender<T> implements CloneableSender<T> {

    private ClonableTransport<T> transport;
    
    public DefaultSender(ClonableTransport<T> transport) {
        this.transport = transport;
    }
    
    public void send(T payload) {
        transport.sendPayload(payload);    
    }
    
    public DefaultSender<T> clone() {
        return new DefaultSender<T>(transport.clone());
    } 
}
