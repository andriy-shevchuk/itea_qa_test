package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class FirstSearchResultsPage extends BasePage {

    @FindBy(className = "cur")
    private WebElement currentPageNumber;

    @FindBy(className = "rc")
    private List<WebElement> resultsList;

    /**
     * LoginPage constructor
     *
     * @param driver WebDriver instance
     */
    public FirstSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded(String pageNumber) {
        waitUntilElementDisplayed(currentPageNumber).isDisplayed();
        System.out.println(currentPageNumber.getText());
        if (currentPageNumber.getText().equalsIgnoreCase(pageNumber)) {
            return true;
        } else {
            return false;
        }

    }

    public int getResultsQuantity() {
        return resultsList.size();
    }

    /**
     * Gets List of desired Incident cards details elements
     *
     * @param details String "cities", "streets", "timestamps"
     * @return listDetails List of WebElements
     */
    public List<String> getIncidentCardsDetails(String details) {
        List<String> listDetails = new ArrayList<>();
        String elementXpath = getCardsDetailsElementXpath(details);
        for (WebElement cardsElement: incidentsCardsList) {
            String cardsElementText = cardsElement.findElement(By.xpath(elementXpath)).getText();
            listDetails.add(cardsElementText);
        }
        return listDetails;
    }

    public String getSearchDetailsXpath(String searchText) {

        switch (searchText.toLowerCase()) {
            case "searchHeaders":
                return "//h3//a";
            case "searchAddresses":
                return ".//*[@class='_Rm']";
            default:
                return "";
        }


    }

    public boolean eachResultContains(String searchText) {
    }
}
