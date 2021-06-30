package step;

import io.cucumber.java.ParameterType;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import io.cucumber.java.ru.Пусть;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Step {
    private static WebDriverWait wait;

    public static void screenshot(WebDriver driver) {
        Allure.addAttachment("Скриншот", new ByteArrayInputStream(((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES)));
    }

    @Пусть("открыт ресурс авито")
    public static void openWeb() {
        Hook.getDriver().get("https://www.avito.ru/");
        screenshot(Hook.getDriver());
    }

    @ParameterType(".*")
    public Category category(String categoryName) {
        return Category.valueOf(categoryName);
    }

    @И("в выпадающем списке категорий выбрана {category}")
    public static void selectCategory(Category category) {
        Select categories = new Select(Hook.getDriver().findElement(By.id("category")));
        categories.selectByValue(category.getValue());
        screenshot(Hook.getDriver());
    }

    @И("в поле поиска введено значение {word}")
    public static void enterPrinter(String string) {
        wait = new WebDriverWait(Hook.getDriver(), 30);
        Hook.getDriver().findElement(By.cssSelector("[data-marker='search-form/suggest']")).sendKeys(string);
        wait.until(ExpectedConditions.visibilityOf(Hook.getDriver().findElement(By.cssSelector
                ("[data-marker='suggest/list/item-with-category']")))).click();
        screenshot(Hook.getDriver());
    }

    @Тогда("кликнуть по выпадающему списку региона")
    public static void selectRegion() {
        wait = new WebDriverWait(Hook.getDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-marker='search-form/region']")))
                .click();
        screenshot(Hook.getDriver());
    }

    @Тогда("в поле регион введено значение {word}")
    public static void enterCityName(String string) {
        wait = new WebDriverWait(Hook.getDriver(), 30);
        WebElement fieldCity = Hook.getDriver().findElement(By.cssSelector
                ("[data-marker='popup-location/region/input']"));
        fieldCity.clear();
        fieldCity.sendKeys(string, Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(Hook.getDriver().findElement(By.cssSelector
                ("[data-marker='suggest(0)']")))).click();
        screenshot(Hook.getDriver());
    }

    @И("нажата кнопка показать объявления")
    public static void showAds() {
        Hook.getDriver().findElement(By.cssSelector("[data-marker='popup-location/save-button']")).click();
        screenshot(Hook.getDriver());
    }

    @Тогда("открылась страница результаты по запросу {word}")
    public static void openPageResult(String expected) {
        String actual = Hook.getDriver().findElement(By.cssSelector("[data-marker='search-form/suggest']"))
                .getAttribute("value");
        assertEquals(actual, expected);
        screenshot(Hook.getDriver());
    }

    @И("активирован чекбокс только с фотографией")
    public static void checkBox() {
        List<WebElement> elements =
                Hook.getDriver().findElements(By.cssSelector(".checkbox-checkbox-7igZ6"));
        elements.get(1).click();
        screenshot(Hook.getDriver());
    }

    @И("в выпадающем списке сортировка выбрано значение {category}")
    public static void sortByPrice(Category category) {
        WebElement option = Hook.getDriver().findElement(By.cssSelector(".sort-select-3QxXG.select-select-box-3LBfK" +
                ".select-size-s-2gvAy"));
        Select moreExpensive = new Select(option.findElement(By.cssSelector(".select-select-3CHiM")));
        moreExpensive.selectByVisibleText(String.valueOf(category));
        screenshot(Hook.getDriver());
    }

    @И("в консоль выведено значение названия и цены {int} первых товаров")
    public static void showFirstThreeProducts(int item) {
        List<WebElement> printList = Hook.getDriver().findElements(By.cssSelector("[data-marker='item']"));
        for (int i = 0; i < 3; i++) {
            System.out.println("Название принтера: " +
                    printList.get(i).findElement(By.cssSelector("[itemprop='name']")).getText()
                    + " Стоимость принтера: " + printList.get(i).findElement(By.cssSelector
                    ("[data-marker='item-price']")).getText());
            screenshot(Hook.getDriver());
        }
    }
}
