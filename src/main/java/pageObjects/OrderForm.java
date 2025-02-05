package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderForm {

    private final WebDriver driver;

    public OrderForm (WebDriver driver) {
        this.driver = driver;
    }

    private final By orderStageOneHeader = By.xpath(".//div[starts-with(@class, 'Order_Header') and text()='Для кого самокат']");
    private final By firstNameField = By.xpath(".//div[starts-with(@class, 'Input_InputContainer')]/input[contains(@placeholder, 'Имя')]");
    private final By secondNameField = By.xpath(".//div[starts-with(@class, 'Input_InputContainer')]/input[contains(@placeholder, 'Фамилия')]");
    private final By addressLineField = By.xpath(".//div[starts-with(@class, 'Input_InputContainer')]/input[contains(@placeholder, 'Адрес')]");
    private final By subwayField = By.xpath(".//div[@class='select-search__value']/input[contains(@placeholder, 'Станция метро')]");
    private final By selectedSubwayField = By.xpath(".//ul[@class='select-search__options']/li[@class='select-search__row']");
    private final By telField = By.xpath(".//div[starts-with(@class, 'Input_InputContainer')]/input[contains(@placeholder, 'Телефон')]");
    private final By nextButton = By.xpath(".//div[starts-with(@class, 'Order_NextButton')]/button[text()='Далее']");


    private final By orderStageTwoHeader = By.xpath(".//div[starts-with(@class, 'Order_Header') and text()='Про аренду']");
    private final By dateField = By.xpath(".//div[@class='react-datepicker-wrapper']//input[contains(@placeholder, 'Когда привезти самокат')]");
    private final By selectedDateField = By.xpath(".//div[contains(@class, 'react-datepicker__day--selected')]");

    private final By rentPeriodField = By.xpath(".//div[@class='Dropdown-control']/div[contains(text(), 'Срок аренды')]");
    private final By rentPeriodDropDownList = By.xpath(".//div[@class='Dropdown-menu']");
    private final By rentDropDownOption = By.xpath(".//div[@class='Dropdown-option']");

    private final By blackCheckBox = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]/label[@for='black']");
    private final By greyCheckBox = By.xpath(".//div[starts-with(@class, 'Order_Checkboxes')]/label[@for='grey']");

    private final By commentField = By.xpath(".//div[starts-with(@class, 'Input_InputContainer')]/input[@placeholder='Комментарий для курьера']");

    private final By orderButtonDown = By.xpath(".//div[starts-with(@class, 'Order_Buttons')]/button[text()='Заказать'] ");

    private final By orderButtonUp = By.xpath(".//div[starts-with(@class, 'Header_Nav')]/button[text()='Заказать']");


    private final By modalHeader = By.xpath(".//div[starts-with(@class, 'Order_Modal')]/div[text()='Хотите оформить заказ?']");

    private final By yesButton = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//button[text()='Да']");

    private final By orderCompleted = By.xpath(".//div[starts-with(@class, 'Order_ModalHeader') and text()='Заказ оформлен']");



    public boolean isStageOne() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(orderStageOneHeader));
        return driver.findElement(orderStageOneHeader).isDisplayed();
    }

    private void putText(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public void setFirstNameField(String firstName) {
        putText(driver.findElement(firstNameField), firstName);
    }

    public void setSecondNameField(String secondName) {
        putText(driver.findElement(secondNameField), secondName);
    }

    public void setAddressLineField(String addressLine) {
        putText(driver.findElement(addressLineField), addressLine);
    }

    public void setSubwayField(String subway) {
        putText(driver.findElement(subwayField), subway);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(selectedSubwayField));
        WebElement element = driver.findElement(selectedSubwayField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void setTelField(String tel) {
        putText(driver.findElement(telField), tel);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public boolean isStageTwo() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderStageTwoHeader));
        return driver.findElement(orderStageTwoHeader).isDisplayed();
    }

    public void setDateField(String date) {
        putText(driver.findElement(dateField), date);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(selectedDateField));
        WebElement element = driver.findElement(selectedDateField);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.sendKeys(Keys.ENTER);
    }

    public void choiceRentPeriod(String period) {
        driver.findElement(rentPeriodField).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(rentPeriodDropDownList));
        List<WebElement> dropDownItems = driver.findElement(rentPeriodDropDownList).findElements(rentDropDownOption);
        for (WebElement element : dropDownItems) {
            System.out.println(element.getText());
            if (element.getText().equals(period)) {
                element.click();
                break;

            }
        }

    }

    public void selectBlackCheckbox() {
        driver.findElement(blackCheckBox).click();
    }

    public void selectGreyCheckbox() {
        driver.findElement(greyCheckBox).click();
    }

    public void setCommentField(String comment) {
        putText(driver.findElement(commentField), comment);
    }

    public OrderForm clickOrderButtonUp() {
        driver.findElement(orderButtonUp).click();
        return new OrderForm(driver);
    }

    // Метод для клика на нижнюю кнопку "Заказать"
    public OrderForm clickOrderButtonDown() {
        driver.findElement(orderButtonDown).click();
        return new OrderForm(driver);
    }

    public boolean isModalOrderVisible() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(modalHeader));
        return driver.findElement(modalHeader).isDisplayed();
    }

    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    public boolean isOrderCompleted() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(orderCompleted));
        return driver.findElement(orderStageTwoHeader).isDisplayed();
    }

}
