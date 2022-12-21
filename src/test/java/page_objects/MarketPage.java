package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.script_util.ScriptUtility;
import utilities.wait_utility.WaitUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Romanov 10.12.2022
 */
public class MarketPage {
    private WebElement expectedEl;
    private static WebElement searchbBox;
    private static String searchResultsCount;
    private static List<WebElement> elements = new ArrayList<>();
    private static final String COMMUNITY_BUTTON_PATH = "//*[@class='supernav_container']//a[contains(text(), 'COMMUNITY')]";
    private static final String MARKET_BUTTON_PATH = "//*[@class='supernav_content']//a[contains(text(),'Market')]";
    private static final String MARKET_PAGE_IDENTIFIER = "//*[@id = 'myMarketTabs']";
    private static final String SHOW_ADVANCED_BUTTON_PATH = "//*[@id='market_search_advanced_show']";
    private static final String SEARCH_FROM_IDENTIFIER = "//*[@id='market_advanced_search']";
    private static final String ALL_GAMES_MENU = "//*[@id='market_advancedsearch_appselect']";
    private static final String GAME_OPTION_PATH = "//*[@id='market_advancedsearch_appselect_options']//*[@alt='%s']";
    private static final String HERO_OPTION_PATH = "//*[@id='market_advancedsearch_filters']//*[contains(text(), '%s')]";
    private static final String RARITY_CHECKBOX_PATH = "//*[@id='market_advancedsearch_filters']//*[contains(@id, '%s')]";
    private static final String SEARCHBOX_PATH = "//*[@id='advancedSearchBox']";
    private static final String SEARCH_BUTTON_PATH = "//*[@id='advancedSearchSubmit']";
    private static final String SHOWED_RESULTS = "//*[@class='market_search_results_header']//a[contains(text(), '%s')]";
    private static final String GAME_RESULT = "//*[@class='market_search_results_header']//a";
    private static final String RESULT_ITEMS = "//*[@id='searchResultsRows']//*[contains(@id,'name')]";
    private static final String DELETE_BTN = "//*[@class='market_search_results_header']//a[contains(text(), '%s')]//span";
    private static final String RESULTS_COUNT = "//*[@id='searchResults_total']";
    private static final String FIRST_ITEM_PATH = "//*[@id='resultlink_0']";
    private static final String FIRST_ITEM_NAME = "//*[@id='result_0_name']";

    public MarketPage() { }

    public void clickMarketBtn() {
        Actions action = new Actions(SingleDriver.getDriver());
        WebElement target = SingleDriver.getDriver().findElement(By.xpath(COMMUNITY_BUTTON_PATH));
        action.moveToElement(target).perform();

        WebElement marketButton = WaitUtil.setPresenceWait(MARKET_BUTTON_PATH);
        marketButton.click();
    }

    public boolean checkMarketPage() {
        expectedEl = WaitUtil.setPresenceWait(MARKET_PAGE_IDENTIFIER);
        return expectedEl.isDisplayed();
    }

    public void clickAdvancedOptions() {
        WebElement showAdvancedBtn = WaitUtil.setPresenceWait(SHOW_ADVANCED_BUTTON_PATH);
        ScriptUtility.scrollToElement(showAdvancedBtn);
        showAdvancedBtn.click();
    }

    public boolean checkSearchForm() {
        expectedEl = WaitUtil.setPresenceWait(SEARCH_FROM_IDENTIFIER);
        return expectedEl.isDisplayed();
    }

    public void setSearchParameters(String gameName, String heroName, String rarity, String searchBoxText) {
        WebElement gamesMenu = WaitUtil.setPresenceWait(ALL_GAMES_MENU);
        gamesMenu.click();

        WebElement dotaOption = WaitUtil.setPresenceWait(String.format(GAME_OPTION_PATH, gameName));
        ScriptUtility.scrollToElement(dotaOption);
        dotaOption.click();

        WebElement heroOption = WaitUtil.setPresenceWait(String.format(HERO_OPTION_PATH, heroName));
        ScriptUtility.scrollToElement(heroOption);;
        heroOption.click();

        WebElement immortalCheckBox = WaitUtil.setPresenceWait(String.format(RARITY_CHECKBOX_PATH, rarity));
        immortalCheckBox.click();

        searchbBox = WaitUtil.setPresenceWait(SEARCHBOX_PATH);
        searchbBox.click();
        searchbBox.sendKeys(searchBoxText);
    }

    public void searchBtnClick() {
        WebElement searchBtn = WaitUtil.setPresenceWait(SEARCH_BUTTON_PATH);
        searchBtn.click();
    }

    public boolean compareItemParameters(String gameName, String heroName, String rarity, String searchBoxText) {
        boolean output = false;
        String result1 = WaitUtil.setPresenceWait(GAME_RESULT).getText();
        String result2 = WaitUtil.setPresenceWait(String.format(SHOWED_RESULTS, heroName)).getText();
        String result3 = WaitUtil.setPresenceWait(String.format(SHOWED_RESULTS, rarity)).getText();
        String result4 = WaitUtil.setPresenceWait(String.format(SHOWED_RESULTS, searchBoxText)).getText().replace("\"", "");

        if(gameName.equals(result1) && heroName.equals(result2) && rarity.equals(result3)
                && searchBoxText.equals(result4)) {
            output = true;
        }

        return output;
    }

    public boolean checkGolden(String goldenText) {
        boolean result = false;
        int count = 0;
        elements = WaitUtil.setAllPresenceWait(RESULT_ITEMS);

        for (int i = 0; i < 5; i++) {
            if (elements.get(i).getText().toLowerCase().contains(goldenText)) {
                count++;
            }
        }

        if (count == 5) {
            result = true;
        }

        return result;
    }

    public void removeOptions(String goldenText) {
        searchResultsCount = WaitUtil.setPresenceWait(RESULTS_COUNT).getText();
        WebElement deleteBTN = WaitUtil.setPresenceWait(String.format(DELETE_BTN, goldenText));
        deleteBTN.click();
    }

    public boolean compareSearchResults() {
        boolean result = false;
        if (searchResultsCount != WaitUtil.setPresenceWait(RESULTS_COUNT).getText()) {
            result = true;
        }

        return result;
    }

    public String returnFirstItemName() {
        return WaitUtil.setPresenceWait(FIRST_ITEM_NAME).getText();
    }

    public void firstItemClick() {
        WebElement firstItem = WaitUtil.setPresenceWait(FIRST_ITEM_PATH);
        firstItem.click();
    }
}
