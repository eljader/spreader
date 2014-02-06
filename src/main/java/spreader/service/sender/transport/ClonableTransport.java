package spreader.service.sender.transport;

public interface ClonableTransport <T> extends Transport<T> {
    
    public ClonableTransport<T> clone();
}
