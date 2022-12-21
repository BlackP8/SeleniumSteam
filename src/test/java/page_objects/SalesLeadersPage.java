package page_objects;

import data_model.Game;
import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.convert_utility.ConvertUtil;
import utilities.script_util.ScriptUtility;
import utilities.wait_utility.WaitUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pavel Romanov 12.12.2022
 */
public class SalesLeadersPage {
    private static WebElement expectedEl;
    private String value = "value";
    private static List<WebElement> elements = new ArrayList<>();
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

    public SalesLeadersPage() { }

    public void clickMoreBtn() {
        WebElement moreBtn = WaitUtil.setPresenceWait(MORE_BUTTON_PATH);
        ScriptUtility.scrollToElement(moreBtn);
        moreBtn.click();
    }

    public boolean checkMorePage() {
        expectedEl = WaitUtil.setPresenceWait(MORE_PAGE_IDENTIFIER);
        return expectedEl.isDisplayed();
    }

    public void chooseOS(String os) {
        WebElement osCheckbox = SingleDriver.getDriver().findElement(By.xpath(String.format(UNCHECKED_BOX_PATH, os)));
        ScriptUtility.scrollToElement(osCheckbox);
        osCheckbox.click();
    }

    public boolean checkOSCheckBox() {
        expectedEl = SingleDriver.getDriver().findElement(By.xpath(OS_CHECKED_BOX_PATH));
        return expectedEl.isDisplayed();
    }

    public void clickPlayersCountBtn(String playersType) {
        WebElement  playersCountBtn = WaitUtil.setPresenceWait(PLAYERS_COUNT_PATH );
        playersCountBtn.click();

        WebElement popupMenu = SingleDriver.getDriver().findElement(By.xpath(PLAYERS_COUNT_POPUP_PATH));

        if(popupMenu.isDisplayed() == true) {
            WebElement coopCheckBoxUnchecked = SingleDriver.getDriver().findElement(By.xpath(String.format(UNCHECKED_BOX_PATH, playersType)));
            coopCheckBoxUnchecked.click();
        }
    }

    public boolean checkCoopCheckBox() {
        WebElement coopCheckBoxChecked = WaitUtil.setPresenceWait(COOP_CHECKED_BOX_PATH);
        String param = coopCheckBoxChecked.getAttribute(value);
        return param.isEmpty();
    }

    public void chooseAction(String genre) {
        WebElement actionCheckBox = SingleDriver.getDriver().findElement(By.xpath(String.format(UNCHECKED_BOX_PATH, genre)));
        ScriptUtility.scrollToElement(actionCheckBox);
        actionCheckBox.click();
    }

    public boolean checkActionCheckBox() {
        expectedEl = WaitUtil.setPresenceWait(ACTION_CHECKED_BOX_PATH);
        String param = expectedEl.getAttribute(value);
        return param.isEmpty();
    }

    public boolean compareResults() {
        WebElement resultsCountText = WaitUtil.setPresenceWait(RESULTS_COUNT_TEXT);
        ScriptUtility.scrollToElement(resultsCountText);
        String resultString = resultsCountText.getText();

        int countFromText = Integer.parseInt(ConvertUtil.findNumber(resultString));
        elements = WaitUtil.setAllPresenceWait(RESULT_ELEMENTS);
        int elementsCount = elements.size();

        return countFromText == elementsCount;
    }

    public Game getGameInfo() {
        Game firstGame = new Game(WaitUtil.setPresenceWait(FIRST_ELEMENT_PATH + FIRST_ELEMENT_NAME).getText(),
                SingleDriver.getDriver().findElement(By.xpath(FIRST_ELEMENT_PATH + FIRST_ELEMENT_RELEASE)).getText(),
                SingleDriver.getDriver().findElement(By.xpath(FIRST_ELEMENT_PATH + FIRST_ELEMENT_PRICE)).getText());

        return firstGame;
    }

    public void clickFirstGame() {
        elements.get(0).click();
    }

    public boolean checkGamePage() {
        WebElement gamePage = WaitUtil.setPresenceWait(GAME_PAGE_IDENTIFIER);
        return gamePage.isDisplayed();
    }
}
