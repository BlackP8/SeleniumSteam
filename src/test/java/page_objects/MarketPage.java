package page_objects;

import config_utility.ConfigUtil;
import driver.SingleDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Pavel Romanov 10.12.2022
 */
public class MarketPage {

    public MarketPage() {
        PageFactory.initElements(SingleDriver.getInstance(), this);
    }

    private WebElement marketButton;

    public void clickCommunityBtn() {
        new Actions(SingleDriver.getInstance()).moveToElement(SingleDriver.getInstance()
                .findElement(By.xpath("//*[@class='submenu_community']//a[contains(text(),'Market')]"))).perform();
    }

    public void clickMarketBtn() {
        marketButton = (new WebDriverWait(SingleDriver.getInstance(), Long.parseLong(ConfigUtil.getConfProperty("explicitWaitTime"))))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='submenu_community']//a[contains(text(),'Market')]")));
        marketButton.click();
    }

}
