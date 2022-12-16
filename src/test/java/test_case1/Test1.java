package test_case1;

import config_utility.ConfigUtil;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
        SingleDriver.getInstance(false);
        SingleDriver.getDriver().get(ConfigUtil.getConfProperty("mainPageURL"));
        ConfigUtil.setTestData(ConfigUtil.getConfProperty("testDataForTestCase1"));

        MainPage mainPage = new MainPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        Assert.assertEquals(mainPage.checkMainPage(), true, "Главная страница не открылась.");

        Assert.assertEquals(mainPage.clickAboutBtn(), true, "Страница About не открылась.");

        AboutPage aboutPage = new AboutPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        Assert.assertEquals(Integer.parseInt(ConfigUtil.getTestProperty("playersCount")),
                aboutPage.compareAmountOfPlayers(), "Количество игроков в игре больше, чем онлайн.");

        Assert.assertEquals(aboutPage.clickStoreBtn(), true, "Главная страница не открылась.");
    }

    @AfterClass
    public static void tearDown() {
        SingleDriver.quitDriver();
    }
}
