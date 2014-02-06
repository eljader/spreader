package spreader.service.converter;

import java.util.List;

import org.apache.commons.lang.StringUtils;

public class ListToStringConverter implements Converter<List<String>, String> {

    private List<String> input;
    private String output;
    private String delimiter = System.getProperty("line.separator");
    
    public ListToStringConverter() {}
    
    public ListToStringConverter(String delimiter) {
        this.delimiter = delimiter;
    }
    
    public void setInput(List<String> input) {
        this.input = input;
    }

    public String getResult() {
        return output;
    }

    public void convert() {
        output = StringUtils.join(input.toArray(), delimiter);
    }
}
