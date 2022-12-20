package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utilities.ConvertUtil;
import utilities.wait_utility.WaitUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pavel Romanov 12.12.2022
 */
public class SalesLeadersPage {
    private WaitUtil util;
    private static WebElement expectedEl;
    private static JavascriptExecutor executor;
    private static List<WebElement> elements = new ArrayList<>();
    private static final String SCROLL_SCRIPT = "arguments[0].scrollIntoView();";
    private static final String MORE_BUTTON_PATH = "//*[contains(@class, 'weeklytopsellers_BrowseTopSellersButton')]//button";
    private static final String MORE_PAGE_IDENTIFIER = "//*[@id='price_range']";
    private static final String UNCHECKED_BOX_PATH = "//*[@data-loc='%s' and contains(@class,'tab_filter_control_row')]";
    private static final String OS_CHECKED_BOX_PATH = "//*[@data-loc='SteamOS + Linux' and contains(@class, 'checked')]";
    private static final String PLAYERS_COUNT_PATH = "//*[@data-collapse-name='category3' and contains(@class,'collapsed')]";
    private static final String PLAYERS_COUNT_POPUP_PATH = "//*[contains(@class,'block_content_inner') and @style='display: block;']";
    private static final String COOP_CHECKED_BOX_PATH = "//*[@id='category3']";
    private static final String ACTION_CHECKED_BOX_PATH = "//*[@id='tags']";
    private static final String RESULTS_COUNT_TEXT = "//*[contains(@id,'search_results_filtered')]";
    private static final String RESULT_ELEMENTS = "//*[@id='search_resultsRows']//a";
    private static final String FIRST_ELEMENT_PATH = "//*[@id='search_resultsRows']//a[1]";
    private static final String FIRST_ELEMENT_NAME = "//span[@class='title']";
    private static final String FIRST_ELEMENT_RELEASE = "//*[contains(@class, 'search_released')]";
    private static final String FIRST_ELEMENT_PRICE = "//*[contains(@class, 'search_price ')]";
    private static final String GAME_PAGE_IDENTIFIER = "//*[@id='game_highlights']";

    public SalesLeadersPage(String waitTime) {
        executor = (JavascriptExecutor) SingleDriver.getDriver();
        util = new WaitUtil(waitTime);
    }

    public boolean clickMoreBtn() {
        WebElement moreBtn = util.setPresenceWait(MORE_BUTTON_PATH);
        executor.executeScript(SCROLL_SCRIPT, moreBtn);
        moreBtn.click();

        expectedEl = util.setPresenceWait(MORE_PAGE_IDENTIFIER);
        return expectedEl.isDisplayed();
    }

    public boolean chooseOS(String os) {
        WebElement osCheckbox = util.setPresenceWait(String.format(UNCHECKED_BOX_PATH, os));
        executor.executeScript(SCROLL_SCRIPT, osCheckbox);
        osCheckbox.click();

        expectedEl = SingleDriver.getDriver().findElement(By.xpath(OS_CHECKED_BOX_PATH));
        return expectedEl.isDisplayed();
    }

    public boolean clickPlayersCountBtn(String playersType) {
        boolean result = false;
        WebElement  playersCountBtn = util.setPresenceWait(PLAYERS_COUNT_PATH );
        playersCountBtn.click();

        WebElement popupMenu = util.setPresenceWait(PLAYERS_COUNT_POPUP_PATH);

        if(popupMenu.isDisplayed() == true) {
            WebElement coopCheckBoxUnchecked = util.setPresenceWait(String.format(UNCHECKED_BOX_PATH, playersType));
            coopCheckBoxUnchecked.click();

            WebElement coopCheckBoxChecked = util.setPresenceWait(COOP_CHECKED_BOX_PATH);
            String value = coopCheckBoxChecked.getAttribute("value");
            result = value.isEmpty();
        }
        return result;
    }

    public boolean chooseAction(String genre) {
        WebElement actionCheckBox = util.setPresenceWait(String.format(UNCHECKED_BOX_PATH, genre));
        executor.executeScript(SCROLL_SCRIPT, actionCheckBox);
        actionCheckBox.click();

        expectedEl = util.setPresenceWait(ACTION_CHECKED_BOX_PATH);
        String value = expectedEl.getAttribute("value");
        return value.isEmpty();
    }

    public boolean compareResults() {
        WebElement resultsCountText = util.setPresenceWait(RESULTS_COUNT_TEXT);
        executor.executeScript(SCROLL_SCRIPT, resultsCountText);
        String resultString = resultsCountText.getText();

        int countFromText = Integer.parseInt(ConvertUtil.findNumber(resultString));
        elements = util.setAllPresenceWait(RESULT_ELEMENTS);
        int elementsCount = elements.size();

        return countFromText == elementsCount;
    }

    public Game getGameInfo() {
        Game firstGame = new Game(util.setPresenceWait(FIRST_ELEMENT_PATH + FIRST_ELEMENT_NAME).getText(),
                SingleDriver.getDriver().findElement(By.xpath(FIRST_ELEMENT_PATH + FIRST_ELEMENT_PRICE)).getText(),
                SingleDriver.getDriver().findElement(By.xpath(FIRST_ELEMENT_PATH + FIRST_ELEMENT_PRICE)).getText());

        return firstGame;
    }

    public boolean clickFirstGame() {
        elements.get(0).click();

        WebElement gamePage = util.setPresenceWait(GAME_PAGE_IDENTIFIER);
        return gamePage.isDisplayed();
    }
}
