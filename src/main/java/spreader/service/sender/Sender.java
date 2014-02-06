package spreader.service.sender;

public interface Sender <T> {
    
    public void send(T payload);
}
