package test_case1;

import config_utility.ConfigUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import page_objects.AboutPage;
import page_objects.MainPage;
import driver.SingleDriver;

/**
 * @author Pavel Romanov 08.12.2022
 */
public class Test1 {
    @Test
    public static void aboutTest() {
        SingleDriver.getEngInstance().get(ConfigUtil.getConfProperty("mainPageURL"));
        ConfigUtil.setTestData(ConfigUtil.getConfProperty("testDataForTestCase1"));

        MainPage mainPage = new MainPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        WebElement expectedEl = SingleDriver.getInstance().findElement(By.xpath(ConfigUtil.getTestProperty("mainPageIdentifier")));
        Assert.assertEquals(SingleDriver.getInstance().findElement(By.xpath(ConfigUtil.getTestProperty("mainPageIdentifier"))),
                expectedEl);

        Assert.assertEquals(mainPage.clickAboutBtn(ConfigUtil.getTestProperty("aboutBtnPath"),
                ConfigUtil.getTestProperty("aboutPage")), true);

        AboutPage aboutPage = new AboutPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        Assert.assertEquals(Integer.parseInt(ConfigUtil.getTestProperty("playersCount")),
                aboutPage.compareAmountOfPlayers(ConfigUtil.getTestProperty("gamersOnlineCount"),
                        ConfigUtil.getTestProperty("gamersInGameCount")));

        Assert.assertEquals(aboutPage.clickStoreBtn(ConfigUtil.getTestProperty("storeBtnPath"),
                 ConfigUtil.getTestProperty("mainPageIdentifier")), true);
    }

    @AfterClass
    public static void tearDown() {
        SingleDriver.quitDriver();
    }
}
