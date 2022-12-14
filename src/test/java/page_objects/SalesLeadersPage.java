package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import wait_utility.WaitUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Pavel Romanov 12.12.2022
 */
public class SalesLeadersPage {
    private WebElement moreBtn;
    private WebElement osCheckbox;
    private WebElement playersCountBtn;
    private WebElement coopCheckBoxUnchecked;
    private WebElement coopCheckBoxChecked;
    private WebElement actionCheckBox;
    private WebElement resultsCountText;
    private WebElement gamePage;
    private WaitUtil util;
    private static WebElement expectedEl;
    private static JavascriptExecutor executor;
    private static List<WebElement> elements;

    public SalesLeadersPage(String waitTime) {
        PageFactory.initElements(SingleDriver.getInstance(), this);
        executor = (JavascriptExecutor) SingleDriver.getInstance();
        util = new WaitUtil(waitTime);
    }

    public boolean clickMoreBtn(String moreBtnPath, String morePagePath) {
        moreBtn = util.setPresenceWait(moreBtnPath);
        executor.executeScript("arguments[0].scrollIntoView();", moreBtn);
        moreBtn.click();

        expectedEl = util.setPresenceWait(morePagePath);
        return expectedEl.isDisplayed();
    }

    public boolean chooseOS(String osUncheckedBoxPath, String osCheckedBoxPath) {
        osCheckbox = util.setPresenceWait(osUncheckedBoxPath);
        executor.executeScript("arguments[0].scrollIntoView();", osCheckbox);
        osCheckbox.click();

        expectedEl = SingleDriver.getInstance().findElement(By.xpath(osCheckedBoxPath));
        return expectedEl.isDisplayed();
    }

    public boolean clickPlayersCountBtn(String playersCountPath, String playersCountPopupPath,
                                        String coopUncheckedBoxPath, String coopCheckedBoxPath) {
        boolean result = false;
        playersCountBtn = util.setPresenceWait(playersCountPath);
        playersCountBtn.click();

        WebElement popupMenu = util.setPresenceWait(playersCountPopupPath);

        if(popupMenu.isDisplayed() == true) {
            coopCheckBoxUnchecked = util.setPresenceWait(coopUncheckedBoxPath);
            coopCheckBoxUnchecked.click();

            coopCheckBoxChecked = util.setPresenceWait(coopCheckedBoxPath);
            String value = coopCheckBoxChecked.getAttribute("value");
            result = value.isEmpty();
        }
        return result;
    }

    public boolean chooseAction(String uncheckedBoxPath, String checkedBoxPath) {
        actionCheckBox = util.setPresenceWait(uncheckedBoxPath);
        executor.executeScript("arguments[0].scrollIntoView();", actionCheckBox);
        actionCheckBox.click();

        expectedEl = util.setPresenceWait(checkedBoxPath);
        String value = expectedEl.getAttribute("value");
        return value.isEmpty();
    }

    public boolean compareResults(String countOfResultsText, String resultsList) {
        resultsCountText = util.setPresenceWait(countOfResultsText);
        executor.executeScript("arguments[0].scrollIntoView();", resultsCountText);
        String resultString = resultsCountText.getAttribute("innerText");
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(resultString);

        while(m.find()) {
            resultString = m.group();
            break;
        }

        int countFromText = Integer.parseInt(resultString);
        elements = util.setAllPresenceWait(resultsList);
        int elementsCount = elements.size();

        return countFromText == elementsCount;
    }

    public String[] getGameInfo(String firstElementPath, String name, String releaseDate, String price) {
        String[] gameInfo = new String[3];
        gameInfo[0] = util.setPresenceWait(name).getText();
        gameInfo[1] = SingleDriver.getInstance().findElement(By.xpath(firstElementPath + releaseDate)).getText();
        gameInfo[2] = SingleDriver.getInstance().findElement(By.xpath(firstElementPath + price)).getText();

        return gameInfo;
    }

    public boolean clickFirstGame(String resultsList, String gamePageUniqueEl) {
        elements = util.setAllPresenceWait(resultsList);
        elements.get(0).click();

        gamePage = util.setPresenceWait(gamePageUniqueEl);
        return gamePage.isDisplayed();
    }
}
