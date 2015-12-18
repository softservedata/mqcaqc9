package com.softserve.edu.oms.data;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.softserve.edu.oms.pages.LoginStartPage;

public class ReporterWrapper {
    
	public static enum ReporterLevels {
        SCREENSHOT_LEVEL(2),
        ERROR_LEVEL(3),
        WARNING_LEVEL(5),
        INFO_LEVEL(7);
        private int level;

        private ReporterLevels(int level) {
            this.level = level;
        }

        public int getLevel() {
            return this.level;
        }
    }

	//
	private final String TIME_TEMPLATE = "yyyy_MM_dd HH-mm-ss";
    private final String FILE_SUFFIX = " CaptureScreenImage.png";
    private final String DEFAULT_DIRECTORY = "./test-output";
    private final String MAVEN_DIRECTORY = "surefire.reports.directory";
    private final String SLASH = "/";
    private final String FAILED_TO_CREATE = "Failed to create screenshot: %s";

    private String getCurrentTime() {
		return new SimpleDateFormat(TIME_TEMPLATE).format(new Date());
	}

    private String getOutputDirectory() {
        String outputDirectory = System.getProperty(MAVEN_DIRECTORY);
        if ((outputDirectory == null) || (outputDirectory.isEmpty())) {
            outputDirectory = DEFAULT_DIRECTORY;
        }
        return outputDirectory + SLASH;
    }

	private String getAbsolutePathFileName() {
		return getOutputDirectory() + getCurrentTime() + FILE_SUFFIX;
	}

	public String captureAndSaveScreen() {
		String absolutePathFileName = getAbsolutePathFileName();
		try {
		    // TODO Remove Thread sleep. 
		    //Thread.sleep(2000);
			LoginStartPage.getWebDriver().manage().timeouts()
				.implicitlyWait(0, TimeUnit.SECONDS);
		    (new WebDriverWait(LoginStartPage.getWebDriver(), 5))
		    	.until(new ExpectedCondition<Boolean>( ) {
		    			public Boolean apply(WebDriver d) {
				           return (boolean)((JavascriptExecutor)d)
				        		   .executeScript("return $('.all')[0].style.opacity == ''");
				  } } );
		    LoginStartPage.getWebDriver().manage().timeouts()
		    	.implicitlyWait(10, TimeUnit.SECONDS);
            File srcFile = ((TakesScreenshot) LoginStartPage
                    .getWebDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(absolutePathFileName));
		} catch (Exception e) {
			// Develop custom exception.
			throw new RuntimeException(String.format(FAILED_TO_CREATE,
					absolutePathFileName), e);
		}
		return absolutePathFileName;
	}

}