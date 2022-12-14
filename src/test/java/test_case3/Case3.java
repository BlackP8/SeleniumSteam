package test_case3;

import config_utility.ConfigUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import page_objects.MainPage;
import page_objects.MarketPage;
import driver.SingleDriver;

/**
 * @author Pavel Romanov 10.12.2022
 */
public class Case3 {
    @Test
    public static void doTest() {
        SingleDriver.getInstance().get(ConfigUtil.getConfProperty("mainPageURL"));
        ConfigUtil.setTestData(ConfigUtil.getConfProperty("testDataForTestCase3"));

        MainPage mainPage = new MainPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        WebElement expectedEl = SingleDriver.getInstance().findElement(By.xpath(ConfigUtil.getTestProperty("mainPageIdentifier")));
        Assert.assertEquals(SingleDriver.getInstance().findElement(By.xpath(ConfigUtil.getTestProperty("mainPageIdentifier"))),
                expectedEl);
        MarketPage marketPage = new MarketPage();
        marketPage.clickCommunityBtn();
        marketPage.clickMarketBtn();
        expectedEl = SingleDriver.getInstance().findElement(By.xpath(ConfigUtil.getTestProperty("marketPage")));
        Assert.assertEquals(SingleDriver.getInstance().findElement(By.xpath(ConfigUtil.getTestProperty("marketPage"))),
                expectedEl);
    }

    @AfterClass
    public static void tearDown() {
        SingleDriver.quitDriver();
    }
}
