package test_case3;

import config_utility.ConfigUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import page_objects.MainPage;
import page_objects.MarketPage;
import driver.SingleDriver;

/**
 * @author Pavel Romanov 10.12.2022
 */
public class Test3 {
    @Test
    public static void marketTest() {
        SingleDriver.getInstance(false);
        SingleDriver.getDriver().get(ConfigUtil.getConfProperty("mainPageURL"));
        ConfigUtil.setTestData(ConfigUtil.getConfProperty("testDataForTestCase3"));

        MainPage mainPage = new MainPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        Assert.assertEquals(mainPage.checkMainPage(), true, "Главная страница не открылась.");

        MarketPage marketPage = new MarketPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        Assert.assertEquals(marketPage.clickMarketBtn(), true, "Страница Community Market не открылась.");

        Assert.assertEquals(marketPage.clickAdvancedOptions(), true, "Форма SEARCH COMMUNITY MARKET не открылась.");

        marketPage.setSearchParameters(ConfigUtil.getTestProperty("searchBoxText"));
    }

    @AfterClass
    public static void tearDown() {
        SingleDriver.quitDriver();
    }
}
