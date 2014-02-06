package spreader.service;

import java.util.List;

import spreader.service.converter.Converter;
import spreader.service.sender.Sender;
import spreader.thread.ThreadSource;

public class DefaultThreadSenderService<T> extends BaseThreadSenderService<T> {
    
    public DefaultThreadSenderService(ThreadSource<T> source, Sender<T> sender,
            Converter<List<T>, T> converter) {
        super(source, sender, converter);
    }

    public void run() {         
        try {       
            while(true) { 
                converter.setInput(source.extract());
                converter.convert();
                sender.send(converter.getResult());
            }    
        } catch(InterruptedException e) {/**quit**/}                       
    }
}
