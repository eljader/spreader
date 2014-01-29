package spreader.service.converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.xml.sax.Attributes;

public final class StringBuildConverter implements Converter<Attributes, String> {

    private Attributes input;
    private String output;
    private String template;
    
    public StringBuildConverter(String template) {
        this.template = template;
    }
    
    public void setInput(Attributes input) {
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
        String value = input.getValue(key);       
        return StringUtils.isNotEmpty(value) ? value : "";
    }
}
