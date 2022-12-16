package driver;

import config_utility.ConfigUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

/**
 * @author Pavel Romanov 09.12.2022
 */
public class SingleDriver {
    private static WebDriver webDriver;

    private SingleDriver() { }

    public static WebDriver getInstance(boolean langFlag) {
        if(webDriver == null) {
            ConfigUtil.setConfig();
            WebDriverManager.chromedriver().setup();
            if (langFlag == true) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments(ConfigUtil.getConfProperty("incognitoMode"));
                webDriver = new ChromeDriver(options);
            }
            else {
                ChromeOptions options = new ChromeOptions();
                options.addArguments(ConfigUtil.getConfProperty("incognitoMode"), ConfigUtil.getConfProperty("engLang"));
                webDriver = new ChromeDriver(options);
            }
            webDriver.manage().deleteAllCookies();
            webDriver.manage().timeouts().implicitlyWait(Long.parseLong(ConfigUtil.getConfProperty("implicitWaitTime")),
                    TimeUnit.SECONDS);
            webDriver.manage().window().maximize();
        }
        return getDriver();
    }

    public static WebDriver getDriver() {
        return webDriver;
    }

    public static void quitDriver() {
        webDriver.quit();
        webDriver = null;
    }
}
