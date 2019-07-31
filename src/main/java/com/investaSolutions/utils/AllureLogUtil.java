package com.investaSolutions.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;

public class AllureLogUtil {
	
	private static final Logger logger = LogManager.getLogger(AllureLogUtil.class.getSimpleName());
	
	private AllureLogUtil() {
    }

    @Step("{0}")
    public static void log(String message){
    	logger.debug("Logged to allure: " + message);
    }
    
    /**
     * Logs the start of a step to your allure report.
     * Other steps will be sub-steps until you call stepFinish.
     *
     * @param stepName the name of the step
     */
  //We can use it when starting tests
    @Step("{0}")
    public static void StartLog (String testClassName){
    	//logger.info(testClassName);
    }
 
    //We can use it when ending tests
    @Step("{0}")
    public static void EndLog (String testClassName){
    	//logger.info(testClassName);
    }
 
    //Info Level Logs
    @Step("{0}")
    public static void Info (String message) {
    	//logger.info(message);
    }
 
    //Warn Level Logs
    @Step("{0}")
    public static void Warn (String message) {
    	//logger.warn(message);
    }
 
    //Error Level Logs
    @Step("{0}")
    public static void Error (String message, Exception e) {
    	//logger.error(message, e);
    }
 
    //Fatal Level Logs
    @Step("{0}")
    public static void Fatal (String message) {
    	//logger.fatal(message);
    }
 
    //Debug Level Logs
    @Step("{0}")
    public static void Debug (String message) {
    	//logger.debug(message);
    }
    
    @Step("browser name is : {0} and headless status : {1}")
    public static void BrowserNameAndHeadless (String browser, String headless) {
    	//logger.info("Browser name : " + browser + " and headless status : " +headless);
    }

}
