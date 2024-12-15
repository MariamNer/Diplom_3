package Pages;
import org.openqa.selenium.*;

public class RegistrationPage {

    private WebDriver driver;

    private By nameField = By.xpath("(.//input[@class=\"text input__textfield text_type_main-default\"])[1]");

    private By emailField = By.xpath ("(.//input[@class=\"text input__textfield text_type_main-default\"])[2]");

    private By passwordField = By.xpath( ".//input[@class=\"text input__textfield text_type_main-default\"][@type=\"password\"]");


    private By registerButton = By.xpath(".//button[contains(text(),'Зарегистрироваться')]");

    public By invalidPasswordMessage = By.xpath( ".//p[@class=\"input__error text_type_main-default\"]");

    public By logInButton = By.className ("Auth_link__1fOlj");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name){
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String pass){
        driver.findElement(passwordField).sendKeys(pass);
    }

    public void registerButtonClick(){
        driver.findElement(registerButton).click();
    }

    public void invalidPasswordMessageIsVisible(){
        driver.findElement(invalidPasswordMessage).getText();
    }

    public void logInButtonClick(){
        driver.findElement(logInButton).click();
    }

    public void fill_out_form(String name, String email, String pass){
        setName(name);
        setEmail(email);
        setPassword(pass);
    }

}
