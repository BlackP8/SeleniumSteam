package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.wait_utility.WaitUtil;

/**
 * @author Pavel Romanov 09.12.2022
 */
public class MainPage {
    private WaitUtil util;
    private static WebElement expectedEl;
    private static final String MAIN_PAGE_IDENTIFIER = "//*[@id = 'home_maincap_v7']";
    private static final String ABOUT_BUTTON_PATH = "//*[@class='supernav_container']//a[contains(text(), 'ABOUT')]";
    private static final String ABOUT_PAGE_IDENTIFIER = "//*[@id='about_greeting']//a";
    private static final String REMARKABLE_BUTTON_PATH = "//*[@id = 'noteworthy_tab']//a[contains(@class, 'desktop')]";
    private static final String SALES_LEADERS_BUTTON_PATH = "//*[@id = 'noteworthy_flyout']//a[contains(text(), 'Лидеры')]";
    private static final String TOP_SELLING_PAGE = "//*[contains(@class, 'weeklytopsellers_ChartPlaceholder')]";

    public MainPage(String waitTime) {
        util = new WaitUtil(waitTime);
    }

    public boolean checkMainPage() {
        expectedEl = SingleDriver.getDriver().findElement(By.xpath(MAIN_PAGE_IDENTIFIER));
        return expectedEl.isDisplayed();
    }

    public boolean clickAboutBtn() {
        WebElement aboutButton = util.setPresenceWait(ABOUT_BUTTON_PATH);
        aboutButton.click();

        expectedEl = util.setPresenceWait(ABOUT_PAGE_IDENTIFIER);
        return expectedEl.isDisplayed();
    }

    public boolean clickSalesLeadersButton() {
        WebElement newAndRemarkableBtn = SingleDriver.getDriver().findElement(By.xpath(REMARKABLE_BUTTON_PATH));
        newAndRemarkableBtn.click();

        WebElement salesLeadersButton = util.setPresenceWait(SALES_LEADERS_BUTTON_PATH);
        salesLeadersButton.click();

        expectedEl = util.setPresenceWait(TOP_SELLING_PAGE);
        return expectedEl.isDisplayed();
    }
}
