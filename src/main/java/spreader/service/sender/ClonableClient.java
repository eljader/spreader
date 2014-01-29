package spreader.service.sender;

public interface ClonableClient <T> extends Client<T> {
    public ClonableClient<T> clone();
}
