package spreader;

import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.xml.sax.helpers.DefaultHandler;

import spreader.args.*;

public class App 
{     
    public static void main(String[] args) 
    {      
        try {
        
            ArgsParser argsParser = new DefaultArgsParser();        
            CommandLine line = argsParser.parse(args);
                                                    
            FileSystemXmlApplicationContext context = 
                    new FileSystemXmlApplicationContext("file:" + line.getOptionValue("configuration"));
        	    	
    		SAXParserFactory.newInstance().newSAXParser().parse(
	            line.getOptionValue("source"), 
	            (DefaultHandler) context.getBean("handler")
            );
    		  
    		context.close();
    		
        }  catch (ArgsUsageException e) { 
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("default", e.getOptions());
        }  catch (Exception e) {
            e.printStackTrace();
        } 
    } 
}
