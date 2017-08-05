package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class StartPage extends BasePage {

    @FindBy(id = "lst-ib")
    private WebElement searchField;

    @FindBy(name = "btnK")
    private WebElement searchButton;


    /**
     * LoginPage constructor
     *
     * @param driver WebDriver instance
     */
    public StartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return waitUntilElementDisplayed(searchField).isDisplayed();
    }

    /**
     * Fills search field with desired text and submits search
     *
     * @param searchText String search text
     * @return PageObject SearchResultsPage
     */
    public SearchResultsPage search(String searchText) {
        searchField.sendKeys(searchText);
        searchButton.click();
        return PageFactory.initElements(webDriver, SearchResultsPage.class);
    }
}
