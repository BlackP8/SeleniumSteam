package test_case2;

import data_model.Game;
import org.testng.annotations.BeforeClass;
import utilities.config_utility.ConfigUtil;
import driver.SingleDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import page_objects.*;

/**
 * @author Pavel Romanov 09.12.2022
 */
public class Test2 {
    private static String mainPageURL;
    private static String testData;
    private static String osCheckBox;
    private static String playersCountCheckBox;
    private static String actionCheckBox;

    @BeforeClass
    public static void setUp() {
        SingleDriver.getInstance(true);
        mainPageURL = ConfigUtil.getConfProperty("mainPageURL");
        testData = ConfigUtil.getConfProperty("testDataForTestCase2");
        SingleDriver.getDriver().get(mainPageURL);
        ConfigUtil.setTestData(testData);
        osCheckBox = ConfigUtil.getTestProperty("osCheckBoxText");
        playersCountCheckBox = ConfigUtil.getTestProperty("playersCountCheckBoxText");
        actionCheckBox = ConfigUtil.getTestProperty("actionCheckBoxText");
    }

    @Test
    public static void sellingLeadersTest() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.checkMainPage(), "Главная страница не открылась.");

        mainPage.clickSalesLeadersButton();
        Assert.assertTrue(mainPage.checkTopSellingPage(), "Страница Лидеры продаж не открылась.");

        SalesLeadersPage leadersPage = new SalesLeadersPage();
        leadersPage.clickMoreBtn();
        Assert.assertTrue(leadersPage.checkMorePage(), "Полная версия страницы Лидеры продаж не открылась.");

        leadersPage.chooseOS(osCheckBox);
        Assert.assertTrue(leadersPage.checkOSCheckBox(), "Чекбокс SteamOS + Linux не выбран.");

        leadersPage.clickPlayersCountBtn(playersCountCheckBox);
        Assert.assertFalse(leadersPage.checkCoopCheckBox(), "Чекбокс Кооператив LAN не выбран.");

        leadersPage.chooseAction(actionCheckBox);
        Assert.assertFalse(leadersPage.checkActionCheckBox(), "Чекбокс Экшен не выбран.");

        Assert.assertTrue(leadersPage.compareResults(), "Указанное количество результатов по запросу " +
                "не соответствует количеству игр в списке.");

        Game gameInfo = leadersPage.getGameInfo();
        leadersPage.clickFirstGame();
        Assert.assertTrue(leadersPage.checkGamePage(), "Страница с описанием игры не открыта.");

        GamePage gamePage = new GamePage();
        Assert.assertTrue(gamePage.getData(gameInfo), "Данные об игре не соответствуют данным из списка.");
    }

    @AfterClass
    public static void tearDown() {
        SingleDriver.quitDriver();
    }
}
