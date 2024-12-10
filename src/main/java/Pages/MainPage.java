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
    // Кнопка войти в аккаунт на главной странице
    private final By enterButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // Кнопка Соусы в конструкторе
    private final By sauceButton = By.xpath(".//div/span[text()='Соусы']");
    // Кнопка Начинки в конструкторе
    private final By filingsButton = By.xpath(".//div/span[text()='Начинки']");

    //картинка с Булкой для проверки видимости раздела
    public By bunsImg = By.xpath(".//img[@alt='Флюоресцентная булка R2-D3']");
    //текст заголовка Булки для проверки видимости раздела
    public By bunsText = By.xpath(".//h2[text()='Булки']");
    //картинка с Соусом для проверки видимости раздела
    public By saucesImg = By.xpath(".//p[text()='Соус традиционный галактический']");
    //картинка с Начинкой для проверки видимости раздела
    public By fillingsImg = By.xpath(".//img[@alt='Говяжий метеорит (отбивная)']");

    private final WebDriver driver;
    private static final String url = "https://stellarburgers.nomoreparties.site/";

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickPersonalAccount() {
        driver.findElement(personalAccount).click();
    }


    public void clickSauceButton() {
        driver.findElement(sauceButton).click();
    }

    public void clickFilingsButton() {
        driver.findElement(filingsButton).click();
    }

    public boolean isOpened() {
        return driver.findElement(createOrder).isDisplayed();
    }

    public boolean isOpenedUnauth() {
        return driver.findElement(enterButton).isDisplayed();
    }

    public void waitForLoad(){
        // подожди 3 секунды, чтобы элемент с нужным текстом стал видимым
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrder));
    }

    public void waitForLoadUnauth(){
        // подожди 3 секунды, чтобы элемент с нужным текстом стал видимым
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(enterButton));
    }
    public void waitForLoadBuns() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsImg));
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsText));
    }
    public void waitForLoadSause() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(saucesImg));
    }

    public void waitForLoadFilling() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsImg));
    }
}