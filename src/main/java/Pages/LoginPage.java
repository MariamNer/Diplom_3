package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    public By entrance = By.xpath(".//main/div/h2[text()='Вход']");
    // Кнопка зарегистрироваться
    public final By buttonRegistration = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");

    //Кнопка восстановить пароль
    public final By buttonRecoveryPassword = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");

    // Поле Email
    private final By fieldEmail = By.xpath(".//div/input[@name='name']");
    // Поле пароль
    private final By fieldPassword = By.xpath(".//div/input[@name='Пароль']");
    // Кнопка войти
    private final By enterButton = By.xpath(".//button[text()='Войти']");
    // Текст сообщения о неправильном пароле
    public final By messageErrorPassword = By.xpath(".//p[text()='Некорректный пароль']");
    //Логотип Stellar Burgers
    public final By logoStellarBurger = By.xpath(".//div/a[@href='/']");
    //Кнопка Конструктор
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");


    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogoStellarBurger() {
        driver.findElement(logoStellarBurger).click();
    }
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
    }


    public void clickbuttonRecoveryPassword(){
        driver.findElement(buttonRecoveryPassword).click();
    }

    public void clickButtonFromRegistration() {
        driver.findElement(buttonRegistration).click();
    }

    public void fillInEmail(String email) {
        driver.findElement(fieldEmail).sendKeys(email);
    }

    public void fillInPassword(String password) {
        driver.findElement(fieldPassword).sendKeys(password);
    }


    public void clickButtonEnter() {
        driver.findElement(enterButton).click();

    }

    public void loginEnterFieldsAndClick(String email, String password) {
        fillInEmail(email);
        fillInPassword(password);
        WebElement wait = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(enterButton));
        clickButtonEnter();
    }

    public boolean isOpened(){
        return driver.findElement(fieldEmail).isDisplayed();
    }


    public void authorization(String email, String password){
        fillInEmail(email);
        fillInPassword(password);
    }

    public void waitForLoadEntrance(){
        // подожди 3 секунды, чтобы элемент с нужным текстом стал видимым
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(entrance));
    }


}
