package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;
import page.SearchResultsPage;
import page.StartPage;

public class SearchResultsTests extends BaseTest {

    WebDriver webDriver;
    StartPage startPage;
    String startURL = "https://www.google.com.ua";

    /**
     * Initialises FirefoxDriver and navigates to startURL page
     */
    @Parameters({ "browserName" })
    @BeforeClass
    public void beforeClass(@Optional("firefox") String browserName) {
        webDriver = StartBrowser(browserName);
        webDriver.navigate().to(startURL);
        startPage = PageFactory.initElements(webDriver, StartPage.class);
        startPage.isPageLoaded();
    }

    /**
     * Closes WebDriver instance
     */
    @AfterClass
    public void afterClass() {
        webDriver.quit();
    }


    @Test
    public void SearchTest() {

        String searchText = "ITEA";

        Assert.assertTrue(startPage.isPageLoaded(), "Search page is not loaded");
        Assert.assertEquals(startPage.getPageTitle(), "Google", "Start page title does not match");
        Assert.assertTrue(startPage.getPageURL().contains(startURL), "Page URL is not " + startURL);

        SearchResultsPage searchResultsPage = startPage.search(searchText);

        Assert.assertTrue(searchResultsPage.isPageLoaded("1"), "First search results page is not loaded");
        Assert.assertEquals(searchResultsPage.getResultsQuantity(), 7, "Results count did not match");
        Assert.assertTrue(searchResultsPage.eachResultContains(searchText), "Not every result contains search term");

        searchResultsPage.switchtoResultsPageNumber(2);

        Assert.assertTrue(searchResultsPage.isPageLoaded("2"), "Second search results page is not loaded");
        Assert.assertEquals(searchResultsPage.getResultsQuantity(), 10, "Results count did not match");
        Assert.assertTrue(searchResultsPage.eachResultContains(searchText), "Not every result contains search term");

    }

}
