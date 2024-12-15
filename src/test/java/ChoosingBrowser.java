import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@RunWith(Parameterized.class)
public class ChoosingBrowser {


    protected WebDriver driver;

    public ChoosingBrowser(String driverType){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";


        switch (driverType) {

            case "yandex":
                createYandexDriver();
                break;
            case "chrome":
            default:
                createChromeDriver();

        }
        driver.get("https://stellarburgers.nomoreparties.site/");
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

}
