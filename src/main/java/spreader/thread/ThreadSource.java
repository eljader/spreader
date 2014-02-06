package spreader.thread;

import java.util.List;

public interface ThreadSource<T> {
    public void setLast(Boolean last);
    public List<T> extract() throws InterruptedException;
}
