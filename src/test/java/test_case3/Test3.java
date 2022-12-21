package test_case3;

import org.testng.annotations.BeforeClass;
import page_objects.ItemPage;
import utilities.config_utility.ConfigUtil;
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
    private static String mainPageURL;
    private static String testData;
    private static String dotaResult;
    private static String lifestealerResult;
    private static String immortalResult;
    private static String searchBoxText;

    @BeforeClass
    public static void setUp() {
        SingleDriver.getInstance(false);
        mainPageURL = ConfigUtil.getConfProperty("mainPageURL");
        testData = ConfigUtil.getConfProperty("testDataForTestCase3");
        SingleDriver.getDriver().get(ConfigUtil.getConfProperty("mainPageURL"));
        ConfigUtil.setTestData(ConfigUtil.getConfProperty("testDataForTestCase3"));
        dotaResult = ConfigUtil.getTestProperty("dotaResult");
        lifestealerResult = ConfigUtil.getTestProperty("lifestealerResult");
        immortalResult = ConfigUtil.getTestProperty("immortalResult");
        searchBoxText = ConfigUtil.getTestProperty("searchBoxText");
    }

    @Test
    public static void marketTest() {
        MainPage mainPage = new MainPage();
        Assert.assertTrue(mainPage.checkMainPage(), "Главная страница не открылась.");

        MarketPage marketPage = new MarketPage();
        marketPage.clickMarketBtn();
        Assert.assertTrue(marketPage.checkMarketPage(), "Страница Community Market не открылась.");

        marketPage.clickAdvancedOptions();
        Assert.assertTrue(marketPage.checkSearchForm(), "Форма SEARCH COMMUNITY MARKET не открылась.");

        marketPage.setSearchParameters(dotaResult, lifestealerResult, immortalResult, searchBoxText);
        marketPage.searchBtnClick();

        Assert.assertTrue(marketPage.compareItemParameters(dotaResult, lifestealerResult, immortalResult, searchBoxText),
                "Фильтры поиска не появились/неправильные.");

        Assert.assertTrue(marketPage.checkGolden(searchBoxText),
                "Первые 5 элементов не содержат слово golden в названии.");

        marketPage.removeOptions(searchBoxText);
        Assert.assertTrue(marketPage.compareSearchResults(), "Список предметов не обновился.");

        String firstItemName = marketPage.returnFirstItemName();
        marketPage.firstItemClick();

        ItemPage itemPage = new ItemPage();
        Assert.assertTrue(itemPage.compareItems(firstItemName, dotaResult, lifestealerResult, immortalResult),
                "Информация на странице предмета не соответствует фильтрам и названию предмета с предыдущей страницы.");
    }

    @AfterClass
    public static void tearDown() {
        SingleDriver.quitDriver();
    }
}
