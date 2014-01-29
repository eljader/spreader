package spreader.service.sender;

public interface PayloadBuffer <T> {    
    public void append(T payload);
    public T retrieveAll();
    public void clear();
}
