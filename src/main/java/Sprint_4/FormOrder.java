package Sprint_4;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import static org.openqa.selenium.Keys.ARROW_DOWN;
import static org.openqa.selenium.Keys.ENTER;

/*
https://qa-scooter.praktikum-services.ru/order - страница заказа
класс, в котором перечислены элементы формы заказа
 */
public class FormOrder {
    private final WebDriver driver;

    public FormOrder(WebDriver driver) {
        this.driver = driver;
    }

    //Поле Имя - name
    private By name = By.xpath(".//input[contains(@placeholder, '* Имя')]");

    // Поле Фамилия - surname
    private By surname = By.xpath(".//input[contains(@placeholder, '* Фамилия')]");

    // Поле Адрес - adress
    private By adress = By.xpath(".//input[contains(@placeholder, '* Адрес: куда привезти заказ')]");

    //Поле Станция метро - metroStation
    private By metroStation = By.xpath(".//input[contains(@placeholder, '* Станция метро')]");

    //Поле Телефон:на него позвонит курьер - phone
    private By phone = By.xpath(".//input[contains(@placeholder, '* Телефон: на него позвонит курьер')]");

    //Кнопка Далее - next
    private By next = By.xpath(".//button[text()='Далее']");

    //Поле Когда привезти самокат - whenBringScooter
    private By whenBringScooter = By.xpath(".//input[contains(@placeholder, '* Когда привезти самокат')]");

    //Дата доставки самоката - orderDate
    private By orderDate = (By.xpath(".//div[@aria-label='Choose воскресенье, 29-е октября 2023 г.']"));

    //Поле срок аренды - rentalPeriod
    private By rentalPeriod = By.xpath("//div[text()='* Срок аренды']");

    //Пятеро суток срок аренды - fiveDays
    private By fiveDays = By.xpath(".//div[text()='пятеро суток']");

    //Цвет самоката -  colorScooter
    private By colorScooter = (By.xpath(".//input[@id='black' and @type='checkbox']"));

    //Комменатрий для курьера - commentForCourier
    private By commentForCourier = (By.xpath(".//input[@placeholder='Комментарий для курьера']"));

    //Кнопка заказать
    private By orderButton = (By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']"));

    //Подтвердить заказ - clickYes
    private By clickYes = (By.xpath("//button[contains(text(), 'Да')]"));

    public void firstData(String nameValue, String surnameValue, String adressValue, String metroStationValue, String phoneValue) {
        driver.findElement(name).sendKeys(nameValue);
        driver.findElement(surname).sendKeys(surnameValue);
        driver.findElement(adress).sendKeys(adressValue);
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

    public void orderButton() {
        driver.findElement(orderButton).click();
    }

    public void yes() {
        driver.findElement(clickYes).click();
    }
}
