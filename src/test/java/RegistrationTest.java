
import Pages.LoginPage;
import Pages.MainPage;
import Pages.RegistrationPage;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationTest {

    private WebDriver driver;

    public RegistrationTest(String driverType){
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
    @DisplayName("Регистрация пользователя")
    @Description("Успешная регистрация")
    @Step
    public void registrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickButtonFromStartRegistration();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fill_out_form(RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphanumeric(5)+ "@mail.ru",
                RandomStringUtils.randomAlphanumeric(6));
        new WebDriverWait(driver, Duration.ofSeconds(10));
        registrationPage.registerButtonClick();
        assertTrue(loginPage.isOpened());
    }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Ошибку для некорректного пароля")
    @Step
    public void failedRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickButtonFromStartRegistration();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fill_out_form(RandomStringUtils.randomAlphabetic(5),
                RandomStringUtils.randomAlphanumeric(5)+ "@mail.ru",
                RandomStringUtils.randomAlphanumeric(5));
        new WebDriverWait(driver, Duration.ofSeconds(10));
        registrationPage.registerButtonClick();
        Assert.assertEquals("Некорректный пароль",
                driver.findElement(loginPage.messageErrorPassword).getText());
    }





    @After
    public void closeBrowser () {
        driver.quit();
    }
}
