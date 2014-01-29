package spreader.service.converter;

public interface Converter <A,B> {   
    public void setInput(A input); 
    public B getResult();
    public void convert();
}
