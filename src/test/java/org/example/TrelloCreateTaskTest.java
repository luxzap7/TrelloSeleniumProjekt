package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class TrelloCreateTaskTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String homeURL = "https://trello.com";

    @BeforeMethod
    public void setupTest() {
        WebDriverManager.chromedriver().setup();

        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.setBinary("C:\\Users\\Matej\\IdeaProjects\\chrome-win64\\chrome.exe");
        options.addArguments("profile-directory=Default");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.navigate().to(homeURL);
    }

    @Test
    public void createTaskWithDueDateAndLabel() throws InterruptedException {

        // 1) Trello login
        driver.navigate().to("https://trello.com/login");

        try {
            WebElement loginLink = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//a[contains(@href,'id.atlassian.com/login') and contains(.,'Log in')]")
                    )
            );
            loginLink.click();
        } catch (Exception ignored) {
        }

        WebElement emailInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[@id='username-uid1' or @id='username']")
                )
        );
        emailInput.clear();
        emailInput.sendKeys("UNESITE PRIMJER EMAIL");

        WebElement continueButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@id='login-submit' or @data-testid='login-submit-idf-testid' or normalize-space(.)='Continue']")
                )
        );
        continueButton.click();

        WebElement passwordInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@type='password' and (@id='password' or @data-testid='password')]")
                )
        );
        passwordInput.clear();
        passwordInput.sendKeys("UNESITE LOZINKU");   // po potrebi promijeni

        WebElement loginButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[@id='login-submit' or @data-testid='login-submit-idf-testid' or normalize-space(.)='Log in']")
                )
        );
        loginButton.click();

        WebElement boardsMenu = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//a[contains(@href, '/boards')]")
                )
        );

        WebElement springBloomBoard = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("a[href='/b/czsVVp5a/spring-bloom'][title='Spring bloom']")
                )
        );
        springBloomBoard.click();

        Thread.sleep(3000);

        WebElement addCardButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-testid='list-add-card-button']")
                )
        );
        addCardButton.click();

        WebElement cardTitleTextArea = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("textarea[data-testid='list-card-composer-textarea']")
                )
        );
        cardTitleTextArea.sendKeys("TESTIRANJE");
        cardTitleTextArea.submit();

        Thread.sleep(2000);

        WebElement createdCard = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[@data-testid='card-name' and normalize-space(text())='TESTIRANJE']")
                )
        );
        createdCard.click();

        Thread.sleep(2000);

        WebElement datesButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[normalize-space(text())='Dates']")
                )
        );
        datesButton.click();

        Thread.sleep(1000);

        WebElement dateInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("input[data-testid='due-date-field'][name='date']")
                )
        );
        dateInput.clear();
        dateInput.sendKeys("2/14/2026");

        WebElement saveDateButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.cssSelector("button[data-testid='save-date-button']")
                )
        );
        saveDateButton.click();


        WebElement body = driver.findElement(By.tagName("body"));
        body.sendKeys("1");


        Thread.sleep(2000);
    }

    @AfterMethod
    public void teardownTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
