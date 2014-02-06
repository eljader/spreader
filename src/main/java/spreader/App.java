package spreader;

import java.io.FileNotFoundException;

import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.xml.sax.helpers.DefaultHandler;

import spreader.args.*;

public class App 
{         
    static Logger logger = Logger.getLogger(App.class);
    
    public static void main(String[] args) 
    {      
        try {
 
            PropertyConfigurator.configure(
                org.apache.log4j.helpers.Loader.getResource("log4j.properties")
            );
        
            ArgsParser argsParser = new DefaultArgsParser();        
            CommandLine line = argsParser.parse(args);
                                                    
            FileSystemXmlApplicationContext context = 
                    new FileSystemXmlApplicationContext("file:" + line.getOptionValue("configuration"));
        	    	
            System.out.println("Started processing, see details in spreader.log");
                                    
    		SAXParserFactory.newInstance().newSAXParser().parse(
	            line.getOptionValue("source"), 
	            (DefaultHandler) context.getBean("handler")
            );
    		  
    		context.close();
    		
    		System.out.println("Done");
    		
        }  catch (ArgsUsageException e) { 
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("default", e.getOptions());
        }  catch (BeanDefinitionStoreException e) {
            System.out.println(e.getMessage());
        }  catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }  catch (Exception e) {
            logger.error("is: ", e);            
        } 
    } 
}
