package spreader.service.sender;

public interface Client <T> {
    public void execute(T payload);
}
