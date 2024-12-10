package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    // Кнопка "Личный кабинет"
    private final By personalAccount = By.xpath(".//p[text()='Личный Кабинет']");
    // Кнопка "Оформить заказ"
    private final By createOrder = By.xpath(".//button[text()='Оформить заказ']");
    // Кнопка Вход в форме регистрации
    private final By enterOnRegistrationForm = By.xpath(".//a[text()='Войти']");
    // Кнопка войти в аккаунт на главной странице
    private final By enterButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // Надпись Соберите бургер на главной странице
    private final By chooseBurger = By.xpath(".//h1[text()='Соберите бургер']");
    // Кнопка Булки в конструкторе
    private final By breadButton = By.xpath(".//div/span[text()='Булки']");
    // Кнопка Соусы в конструкторе
    private final By sauceButton = By.xpath(".//div/span[text()='Соусы']");
    // Кнопка Начинки в конструкторе
    private final By filingsButton = By.xpath(".//div/span[text()='Начинки']");
    // Краторная булка
    private final By cratorBread = By.xpath(".//p[text()='Краторная булка N-200i']");
    // Соус Традиционный галактический
    private final By galaxySauce = By.xpath(".//p[text()='Соус традиционный галактический']");
    // Филе Люминесцентного тетраодонтимформа
    private final By luminFiling = By.xpath(".//p[text()='Филе Люминесцентного тетраодонтимформа']");

    private final WebDriver driver;
    private static final String url = "https://stellarburgers.nomoreparties.site/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void clickPersonalAccount() {
        driver.findElement(personalAccount).click();
    }

    public void clickEnterOnRegistrationForm() {
        driver.findElement(enterOnRegistrationForm).click();
    }

    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    public String textOfChooseBurger() {
        return driver.findElement(chooseBurger).getText();
    }

    public String buttonCreateOrderText() {
        return driver.findElement(createOrder).getText();
    }

    public void clickBreadButton() {
        driver.findElement(breadButton).click();
    }

    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    public void clickFilingsButton() {
        driver.findElement(filingsButton).click();
    }

    public String getTextCratorBread() {
        return driver.findElement(cratorBread).getText();
    }

    public String getTextGalaxySauce() {
        return driver.findElement(galaxySauce).getText();
    }

    public String getTextLuminFiling() {
        return driver.findElement(luminFiling).getText();
    }

    public boolean isOpened() {
        return driver.findElement(createOrder).isDisplayed();
    }

    public void waitForLoad(){
        // подожди 3 секунды, чтобы элемент с нужным текстом стал видимым
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrder));
    }
}