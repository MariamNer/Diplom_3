import Pages.LoginPage;
import Pages.MainPage;
import Pages.RecoveryFormPage;
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
public class LogInTest {
    private WebDriver driver;
    private final static String name = "TEST";
    private final static String email = "TESTTEST1"+RandomStringUtils.randomNumeric(8)+"@mail.ru";
    private final static String password = "1234567";

    public LogInTest(String driverType){
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
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickButtonFromRegistration();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fill_out_form(name,email, password);
        new WebDriverWait(driver, Duration.ofSeconds(10));
        registrationPage.registerButtonClick();

    }



    @Test
    @DisplayName("Вход")
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    @Step
    public void logInByLoginButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginEnterFieldsAndClick(email, password);
        mainPage.waitForLoad();
        assertTrue(mainPage.isOpened());
    }

    @Test
    @DisplayName("Вход")
    @Description("Вход через кнопку «Личный кабинет»")
    @Step
    public void logInByPersonalAccountButtonTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(email,password);
        loginPage.clickButtonEnter();
        mainPage.waitForLoad();
        Assert.assertTrue(mainPage.isOpened());
    }

    @Test
    @DisplayName("Вход")
    @Description("Вход через кнопку в форме регистрации")
    @Step
    public void logInBylogInButtonRegistrationTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickButtonFromRegistration();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.logInButtonClick();
        loginPage.waitForLoadEntrance();
        Assert.assertTrue(loginPage.isOpened());
    }

    @Test
    @DisplayName("Вход")
    @Description("Вход через кнопку в форме восстановления пароля")
    @Step
    public void logInBylogInButtonPasswordRecoveryFormTest(){
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickbuttonRecoveryPassword();
        RecoveryFormPage recoveryFormPage = new RecoveryFormPage(driver);
        recoveryFormPage.waitForLoadedRecoveryForm();
        recoveryFormPage.clickOnLoginLink();
        loginPage.waitForLoadEntrance();
        loginPage.authorization(email, password);
        loginPage.clickButtonEnter();
        mainPage.waitForLoad();
        Assert.assertTrue(mainPage.isOpened());
    }

    @After
    public void closeBrowser () {
        driver.quit();
    }

}
