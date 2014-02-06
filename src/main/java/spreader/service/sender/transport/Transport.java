package spreader.service.sender.transport;

public interface Transport <T> {
    public void sendPayload(T payload);
}
