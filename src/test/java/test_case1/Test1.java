package test_case1;

import org.testng.annotations.BeforeClass;
import utilities.config_utility.ConfigUtil;
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
    private static String mainPageURL;

    @BeforeClass
    public static void setUp() {
        SingleDriver.getInstance(false);
        mainPageURL = ConfigUtil.getConfProperty("mainPageURL");
        SingleDriver.getDriver().get(mainPageURL);
    }

    @Test
    public static void aboutTest() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.checkMainPage(), "Главная страница не открылась.");

        mainPage.clickAboutBtn();
        Assert.assertTrue(mainPage.checkAboutPage(), "Страница About не открылась.");

        AboutPage aboutPage = new AboutPage();
        Assert.assertTrue(aboutPage.compareAmountOfPlayers(), "Количество игроков в игре больше, чем онлайн.");

        aboutPage.clickStoreBtn();
        Assert.assertTrue(aboutPage.checkMainPage(), "Главная страница не открылась.");
    }

    @AfterClass
    public static void tearDown() {
        SingleDriver.quitDriver();
    }
}
