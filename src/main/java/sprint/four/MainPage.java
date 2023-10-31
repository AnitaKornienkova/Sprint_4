package sprint.four;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {//класс, в котором перечислены элементы главной страницы
    private final WebDriver webDriver;

    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Основная страница
    private static final String URL = "https://qa-scooter.praktikum-services.ru/";

    //Куки - clickAgreeCookieButton
    private final By clickAgreeCookieButton = By.xpath("//button[@class='App_CookieButton__3cvqF']");

    //Кнопка "Заказать" вверху страницы справа - clickButtonForOrderInUpPage
    private final By clickButtonForOrderInUpPage = By.xpath("//button[@class='Button_Button__ra12g' and contains(text(), 'Заказать')]");

    // Кнопка "Заказать" внизу страницы - clickButtonForOrderInDownPage
    private final By clickButtonForOrderInDownPage = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and contains(text(), 'Заказать')]");

    //Блок о важном
    private final By importantQuestionsBlock = By.xpath("//div[contains(text(), 'Вопросы о важном')]");

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

    //Выполнить прокрутку до блока "Вопросы о важном"
    public void scrollIntoImportantQuestionsBlock(){
        WebElement element = webDriver.findElement((By.xpath("//div[contains(text(), 'Вопросы о важном')]")));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();",element);
    }

    public void selectQuestionHeader(String question) {
        webDriver.findElement(By.xpath(".//div[text()='" + question + "']")).click();
    }

    public String getAccordionPanelText(int number) {
        WebElement accordionPanel = webDriver.findElement(By.id("accordion__panel-" + number));
        new WebDriverWait(webDriver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(accordionPanel));
        return accordionPanel.getText();
    }
}
