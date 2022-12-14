package test_case2;

import config_utility.ConfigUtil;
import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
        SingleDriver.getInstance().get(ConfigUtil.getConfProperty("mainPageURL"));
        ConfigUtil.setTestData(ConfigUtil.getConfProperty("testDataForTestCase2"));

        MainPage mainPage = new MainPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        WebElement expectedEl = SingleDriver.getInstance().findElement(By.xpath(ConfigUtil.getTestProperty("mainPageIdentifier")));
        Assert.assertEquals(SingleDriver.getInstance().findElement(By.xpath(ConfigUtil.getTestProperty("mainPageIdentifier"))),
                expectedEl);

        Assert.assertEquals(mainPage.clickSalesLeadersButton(ConfigUtil.getTestProperty("newAndRemarkableBtnPath"),
                ConfigUtil.getTestProperty("salesLeadersBtnPath"), ConfigUtil.getTestProperty("topSellingPage")),
                true);

        SalesLeadersPage leadersPage = new SalesLeadersPage(ConfigUtil.getConfProperty("explicitWaitTime"));
        Assert.assertEquals(leadersPage.clickMoreBtn(ConfigUtil.getTestProperty("moreBtnPath"),
                ConfigUtil.getTestProperty("morePage")), true);

        Assert.assertEquals(leadersPage.chooseOS(ConfigUtil.getTestProperty("osUncheckedBoxPath"),
                ConfigUtil.getTestProperty("osCheckedBoxPath")), true);

        Assert.assertEquals(leadersPage.clickPlayersCountBtn(ConfigUtil.getTestProperty("playersCountPath"),
                ConfigUtil.getTestProperty("playersCountPopup"), ConfigUtil.getTestProperty("coopUncheckedBoxPath"),
                ConfigUtil.getTestProperty("coopCheckedBoxPath")), false);

        Assert.assertEquals(leadersPage.chooseAction(ConfigUtil.getTestProperty("actionUncheckedBoxPath"),
                ConfigUtil.getTestProperty("actionCheckedBoxPath")), false);

        boolean compareResult = leadersPage.compareResults(ConfigUtil.getTestProperty("resultsCountText"),
                ConfigUtil.getTestProperty("resultElements"));
        Assert.assertEquals(compareResult, true);

        String[] gameInfo = leadersPage.getGameInfo(ConfigUtil.getTestProperty("firstElementPath"),
                ConfigUtil.getTestProperty("firstElementName"), ConfigUtil.getTestProperty("firstElementReleaseDate"),
                ConfigUtil.getTestProperty("firstElementPrice"));
        Assert.assertEquals(leadersPage.clickFirstGame(ConfigUtil.getTestProperty("resultElements"),
                ConfigUtil.getTestProperty("gamePageUnique")), true);

        GamePage gamePage = new GamePage(ConfigUtil.getConfProperty("explicitWaitTime"));
        Assert.assertEquals(gamePage.getData(gameInfo, ConfigUtil.getTestProperty("gameName"),
                ConfigUtil.getTestProperty("releaseDate"), ConfigUtil.getTestProperty("gamePrice")), true);
    }

    @AfterClass
    public static void tearDown() {
        SingleDriver.quitDriver();
    }
}
