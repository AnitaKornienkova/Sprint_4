package sprint.four;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {//класс, в котором перечислены элементы главной страницы
    private final WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Основная страница
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";

    //Куки - clickAgreeCookieButton
    private By clickAgreeCookieButton = By.xpath("//button[@class='App_CookieButton__3cvqF']");

    //Кнопка "Заказать" вверху страницы справа - clickButtonForOrderInUpPage
    private By clickButtonForOrderInUpPage = By.xpath("//button[@class='Button_Button__ra12g' and contains(text(), 'Заказать')]");

    // Кнопка "Заказать" внизу страницы - clickButtonForOrderInDownPage
    private By clickButtonForOrderInDownPage = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Заказать')]");

    // метод открытия страницы
    public void open() {
        webDriver.get(URL);
    }

    //метод для нажатия кнопки "заказать", расположенной сверху справа
    public void clickButtonForOrderInUpPage() {
        webDriver.findElement(clickButtonForOrderInUpPage).click();
    }

    //метод для нажатия кнопки "заказать", расположенной снизу
    public void clickButtonForOrderInDownPage() {
        webDriver.findElement(clickButtonForOrderInUpPage).click();
    }

    //метод для нажатия кнопки "да все привыкли"
    public void setClickAgreeCookieButton() {
        webDriver.findElement(clickAgreeCookieButton).click();
    }

}
