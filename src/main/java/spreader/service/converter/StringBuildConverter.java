package spreader.service.converter;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

final public class StringBuildConverter implements Converter<HashMap<String, String>, String> {

    private HashMap<String, String> input;
    private String output;
    private String template;
    
    public StringBuildConverter(String template) {
        this.template = template;
    }
    
    public void setInput(HashMap<String, String> input) {
        this.input = input;        
    }

    public String getResult() {
        return this.output;
    }

    public void convert() {              
        this.output = buildTemplate();     
    }
    
    private String buildTemplate() {        
        StringBuffer buffer = new StringBuffer(); 
        Matcher matcher = Pattern.compile("\\#\\<(\\w*)\\>").matcher(template);
        int sharpAndLeftBrace = 2;
        int rightBrace = 1;
        
        while(matcher.find())
        {
            matcher.appendReplacement(
                buffer, 
                getValue(
                    template.substring(
                        matcher.start() + sharpAndLeftBrace, matcher.end() - rightBrace
                    )
                )
            );
        }      
        matcher.appendTail(buffer);
        
        return buffer.toString();
    }
    
    private String getValue(String key) {
        String value = input.get(key);       
        return StringUtils.isNotEmpty(value) ? value : "";
    }
}
