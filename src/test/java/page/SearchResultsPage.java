package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(className = "cur")
    private WebElement currentPageNumber;

    @FindBy(id = "nav")
    private WebElement navigation;

    @FindBy(xpath = "//*[@class='rc']")
    private List<WebElement> resultsList;

    /**
     * SearchResultsPage constructor
     *
     * @param driver WebDriver instance
     */
    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Checks if desired page loaded by its number
     *
     * @param pageNumber String desired search page number to check
     * @return true if correct page number is loaded
     */
    public boolean isPageLoaded(String pageNumber) {
        waitUntilElementDisplayed(currentPageNumber).isDisplayed();
        System.out.println(currentPageNumber.getText());
        if (currentPageNumber.getText().equalsIgnoreCase(pageNumber)) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Gets quantity of results searches at page
     *
     * @return int quantity of results
     */
    public int getResultsQuantity() {
        return resultsList.size();
    }

    /**
     * Gets List of desired search results details elements
     *
     * @param searchText String "searchHeaders", "searchAddresses"
     * @return listDetails List of WebElements
     */
    public List<String> getSearchDetails(String searchText) {
        List<String> listDetails = new ArrayList<>();
        String elementXpath = getSearchDetailsXpath(searchText);
        for (WebElement resultElement: resultsList) {
            String searchDetailsText = resultElement.findElement(By.xpath(elementXpath)).getText();
            listDetails.add(searchDetailsText);
        }
        return listDetails;
    }

    /**
     * gets Xpath string to get search details
     *
     * @param searchText String "searchHeaders", "searchAddresses"
     * @return String with xpath to get search details elements
     */
    public String getSearchDetailsXpath(String searchText) {

        switch (searchText.toLowerCase()) {
            case "searchheaders":
                return ".//h3//a";
            case "searchaddresses":
                return ".//cite";
            default:
                return "";
        }


    }

    /**
     * Checks if each result on page contains searched string
     *
     * @param searchText String search text
     * @return true if each result contains search text
     */
    public boolean eachResultContains(String searchText) {

        List<String> listHeaders = getSearchDetails("searchHeaders");
        List<String> listAddresses = getSearchDetails("searchAddresses");

        boolean result = false;

        for (int i=0; i<resultsList.size(); i++) {
            boolean isAddressElementContainsSearchTerm = listAddresses.get(i).toLowerCase().contains(searchText.toLowerCase());
            boolean isHeaderElementContainsSearchTerm = listHeaders.get(i).toLowerCase().contains(searchText.toLowerCase());
            result = isAddressElementContainsSearchTerm||isHeaderElementContainsSearchTerm;
        }

        return result;

    }

    /**
     * Switches to desired search results page number
     *
     * @param pageNumber int number of page to switch to
     */
    public void switchtoResultsPageNumber(int pageNumber) {
        navigation.findElement(By.xpath(".//*[text() = '" + pageNumber + "']")).click();
    }
}
