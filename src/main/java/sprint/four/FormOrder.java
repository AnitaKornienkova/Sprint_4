package sprint.four;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.Keys.ARROW_DOWN;
import static org.openqa.selenium.Keys.ENTER;

/*
https://qa-scooter.praktikum-services.ru/order - страница заказа
класс, в котором перечислены элементы формы заказа
 */
public class FormOrder {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public FormOrder(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Поле Имя - name
    private final By name = By.xpath(".//input[contains(@placeholder, '* Имя')]");

    // Поле Фамилия - surname
    private final By surname = By.xpath(".//input[contains(@placeholder, '* Фамилия')]");

    // Поле Адрес - adress
    private final By address = By.xpath(".//input[contains(@placeholder, '* Адрес: куда привезти заказ')]");

    //Поле Станция метро - metroStation
    private final By metroStation = By.xpath(".//input[contains(@placeholder, '* Станция метро')]");

    //Поле Телефон:на него позвонит курьер - phone
    private final By phone = By.xpath(".//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]");

    //Кнопка Далее - next
    private final By next = By.xpath(".//button[text()='Далее']");

    //Поле Когда привезти самокат - whenBringScooter
    private final By whenBringScooter = By.xpath(".//input[contains(@placeholder, '* Когда привезти самокат')]");

    //Дата доставки самоката - orderDate
    private final By orderDate = By.xpath(".//div[@aria-label='Choose четверг, 30-е ноября 2023 г.']");

    //Поле срок аренды - rentalPeriod
    private final By rentalPeriod = By.xpath("//div[text()='* Срок аренды']");

    //Пятеро суток срок аренды - fiveDays
    private final By fiveDays = By.xpath(".//div[text()='пятеро суток']");

    //Цвет самоката -  colorScooter
    private final By colorScooter = By.xpath(".//input[@id='black' and @type='checkbox']");

    //Комменатрий для курьера - commentForCourier
    private final By commentForCourier = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Кнопка заказать
    private final By orderButton =
            By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");

    //Подтвердить заказ - clickYes
    private final By clickYes = By.xpath("//button[contains(text(), 'Да')]");

    private final By orderModalText = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public void waitForForm() {
        wait.until(ExpectedConditions.presenceOfElementLocated(name));
    }

    public void firstData(
            String nameValue,
            String surnameValue,
            String addressValue,
            String metroStationValue,
            String phoneValue
    ) {
        driver.findElement(name).sendKeys(nameValue);
        driver.findElement(surname).sendKeys(surnameValue);
        driver.findElement(address).sendKeys(addressValue);
        driver.findElement(metroStation).sendKeys(metroStationValue + ARROW_DOWN + ENTER);
        driver.findElement(phone).sendKeys(phoneValue);
    }

    public void secondData(String commentForCourierValue) {
        driver.findElement(whenBringScooter).click();
        driver.findElement(orderDate).click();
        driver.findElement(rentalPeriod).click();
        driver.findElement(fiveDays).click();
        driver.findElement(colorScooter).click();
        driver.findElement(commentForCourier).sendKeys(commentForCourierValue);
    }

    public void clickButtonNext() {
        driver.findElement(next).click();
    }

    public void waitForOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton));
    }

    public void orderButton() {
        driver.findElement(orderButton).click();
    }

    public void waitForYes() {
        wait.until(ExpectedConditions.elementToBeClickable(clickYes));
    }

    public void yes() {
        driver.findElement(clickYes).click();
    }

    public String getOrderResultText() {
        return driver.findElement(orderModalText).getText();
    }
}
