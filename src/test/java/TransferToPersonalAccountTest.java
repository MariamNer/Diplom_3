import Pages.AccoutPage;
import Pages.LoginPage;
import Pages.MainPage;
import Pages.RegistrationPage;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class TransferToPersonalAccountTest {

        private WebDriver driver;
        private final static String name = "TEST";
        private final static String email = "TESTTEST1"+ RandomStringUtils.randomNumeric(8)+"@mail.ru";
        private final static String password = "1234567";

        public TransferToPersonalAccountTest(String driverType){
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
        @DisplayName("Переход")
        @Description("Переход по клику на «Личный кабинет»")
        @Step
        public void transitionToPersonalAccountPageTest(){
            MainPage mainPage = new MainPage(driver);
            mainPage.clickPersonalAccount();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoadEntrance();
            assertTrue(loginPage.isOpened());
        }

        @Test
        @DisplayName("Выход из аккаунта")
        @Description("Выход по кнопке «Выйти» в личном кабинете")
        @Step
        public void exitPersonalAccountPageTest(){
            MainPage mainPage = new MainPage(driver);
            mainPage.clickPersonalAccount();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoadEntrance();
            loginPage.authorization(email, password);
            loginPage.clickButtonEnter();
            mainPage.waitForLoad();
            mainPage.clickPersonalAccount();
            AccoutPage accoutPage = new AccoutPage(driver);
            accoutPage.waitForLoadProfilePage();
            accoutPage.clickexitButton();
            assertTrue(loginPage.isOpened());
        }

        @Test
        @DisplayName("Переход")
        @Description("Переход по клику на логотип Stellar Burgers")
        @Step
        public void transitionToLogoStellarBurgerTest(){
            MainPage mainPage = new MainPage(driver);
            mainPage.clickPersonalAccount();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoadEntrance();
            loginPage.clickLogoStellarBurger();
            mainPage.waitForLoadUnauth();
            assertTrue(mainPage.isOpenedUnauth());
        }

        @Test
        @DisplayName("Переход")
        @Description("Переход по клику на «Конструктор»")
        @Step
        public void transitionToConstructorTest(){
            MainPage mainPage = new MainPage(driver);
            mainPage.clickPersonalAccount();
            LoginPage loginPage = new LoginPage(driver);
            loginPage.waitForLoadEntrance();
            loginPage.clickOnConstructorButton();
            mainPage.waitForLoadUnauth();
            assertTrue(mainPage.isOpenedUnauth());
        }

        @After
        public void closeBrowser () {
            driver.quit();
        }
}
