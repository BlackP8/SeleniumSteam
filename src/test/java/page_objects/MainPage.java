package page_objects;

import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import wait_utility.WaitUtil;

/**
 * @author Pavel Romanov 09.12.2022
 */
public class MainPage {
    private WebElement aboutButton;
    private WebElement newAndRemarkableBtn;
    private WebElement salesLeadersButton;
    private WaitUtil util;

    public MainPage(String waitTime) {
        PageFactory.initElements(SingleDriver.getInstance(), this);
        util = new WaitUtil(waitTime);
    }

    public boolean clickAboutBtn(String aboutBtnPath, String aboutPageIdentifier) {
        aboutButton = util.setPresenceWait(aboutBtnPath);
        aboutButton.click();

        WebElement expectedEl = util.setPresenceWait(aboutPageIdentifier);
        return expectedEl.isDisplayed();
    }

    public boolean clickSalesLeadersButton(String newAndRemarkPath, String salesLeadersPath, String topSellingPagePath) {
        newAndRemarkableBtn = SingleDriver.getInstance().findElement(By.xpath(newAndRemarkPath));
        newAndRemarkableBtn.click();

        salesLeadersButton = util.setPresenceWait(salesLeadersPath);
        salesLeadersButton.click();

        WebElement expectedEl = util.setPresenceWait(topSellingPagePath);
        return expectedEl.isDisplayed();
    }
}
