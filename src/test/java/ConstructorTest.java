
import Pages.MainPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class ConstructorTest {
    private WebDriver driver;
    private final static String name = "TEST";
    private final static String email = "TESTTEST1"+ RandomStringUtils.randomNumeric(8)+"@mail.ru";
    private final static String password = "1234567";

    public ConstructorTest(String driverType){
        switch (driverType) {

            case "yandex":
                createYandexDriver();
                break;
            case "chrome":
            default:
                createChromeDriver();

        }
    }

    private void createChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\local_user\\Desktop\\Diplom\\Diplom_3\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    private void createYandexDriver() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\local_user\\Desktop\\Diplom\\Diplom_3\\yandex_chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Users\\local_user\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(chromeOptions);
    }

    @Parameterized.Parameters
    public static Object[][] getDriver(){
        return new Object[][]{
                {"chrome"},
                {"yandex"},
        };
    }


    @Before
    public void open(){
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Переход")
    @Description("Переход на вкладку «Булки»")
    @Step
    public void transitionBunsConstructorTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpenedUnauth();
        mainPage.waitForLoadBuns();
        assertTrue(driver.findElement(mainPage.bunsText).isDisplayed());
    }

    @Test
    @DisplayName("Переход")
    @Description("Переход на вкладку «Соусы»")
    @Step
    public void transitionSaucesConstructorTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpenedUnauth();
        mainPage.clickSauceButton();
        mainPage.waitForLoadSause();
        assertTrue(driver.findElement(mainPage.saucesImg).isDisplayed());

    }

    @Test
    @DisplayName("Переход")
    @Description("Переход на вкладку «Начинки»")
    @Step
    public void transitionFillingsConstructorTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.isOpenedUnauth();
        mainPage.clickFilingsButton();
        mainPage.waitForLoadFilling();
        assertTrue(driver.findElement(mainPage.fillingsImg).isDisplayed());

    }

    @After
    public void closeBrowser () {
        driver.quit();
    }
}
