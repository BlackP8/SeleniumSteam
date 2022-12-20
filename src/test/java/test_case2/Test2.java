package test_case2;

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
    @Test
    public static void sellingLeadersTest() {
        SingleDriver.getInstance(true);
        SingleDriver.getDriver().get(ConfigUtil.getConfProperty("mainPageURL"));
        ConfigUtil.setTestData(ConfigUtil.getConfProperty("testDataForTestCase2"));

        MainPage mainPage = new MainPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        Assert.assertEquals(mainPage.checkMainPage(), true, "Главная страница не открылась.");

        Assert.assertEquals(mainPage.clickSalesLeadersButton(), true, "Страница Лидеры продаж не открылась.");

        SalesLeadersPage leadersPage = new SalesLeadersPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        Assert.assertEquals(leadersPage.clickMoreBtn(), true, "Полная версия страницы Лидеры продаж не открылась.");

        Assert.assertEquals(leadersPage.chooseOS(ConfigUtil.getTestProperty("osCheckBoxText")), true, "Чекбокс SteamOS + Linux не выбран.");

        Assert.assertEquals(leadersPage.clickPlayersCountBtn(ConfigUtil.getTestProperty("playersCountCheckBoxText")), false, "Чекбокс Кооператив LAN не выбран.");

        Assert.assertEquals(leadersPage.chooseAction(ConfigUtil.getTestProperty("actionCheckBoxText")), false,"Чекбокс Экшен не выбран.");

        Assert.assertEquals(leadersPage.compareResults(), false, "Указанное количество результатов по запросу " +
                "не соответствует количеству игр в списке.");

        Game gameInfo = leadersPage.getGameInfo();
        Assert.assertEquals(leadersPage.clickFirstGame(), true, "Страница с описанием игры не открыта.");

        GamePage gamePage = new GamePage(ConfigUtil.getConfProperty("explicitWaitTime"));
        Assert.assertEquals(gamePage.getData(gameInfo), true,"Данные об игре не соответствуют данным из списка.");
    }

    @AfterClass
    public static void tearDown() {
        SingleDriver.quitDriver();
    }
}
