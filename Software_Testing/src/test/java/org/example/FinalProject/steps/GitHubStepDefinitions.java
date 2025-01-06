package org.example.FinalProject.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class GitHubStepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;

    @Given("I open GitHub")
    public void i_open_github() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://github.com");
    }

    @Given("I log in with valid credentials")
    public void i_log_in_with_valid_credentials() {
        driver.findElement(By.linkText("Sign in")).click();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_field")));
        highlightElement(usernameField);
        usernameField.sendKeys("Chaitanyathulava");
        WebElement passwordField = driver.findElement(By.id("password"));
        highlightElement(passwordField);
        passwordField.sendKeys("Chinnu_1306");
        driver.findElement(By.name("commit")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("I navigate to {string} page")
    public void i_navigate_to_page(String pageName) {
        WebElement profileIcon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='https://avatars.githubusercontent.com/u/116910543?v=4']")));
        profileIcon.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement yourRepositories = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(pageName)));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = 'light blue';", yourRepositories);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        yourRepositories.click();
    }

    @Then("I should verify the task status of each repository")
    public void i_should_verify_the_task_status_of_each_repository() {
        List<WebElement> repositories = driver.findElements(By.xpath("//li[@itemprop='owns']//a[@itemprop='name codeRepository']"));

        for (WebElement repo : repositories) {
            String repoName = repo.getText();
            System.out.println("Selecting repository: " + repoName);
            repo.click();
            System.out.println("Verifying status for repository: " + repoName);

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                WebElement issuesTab = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Issues")));
                issuesTab.click();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                WebElement openIssuesCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.Counter")));
                String openIssuesCountText = openIssuesCountElement.getText();
                int openIssuesCount = Integer.parseInt(openIssuesCountText);

                if (openIssuesCount == 0) {
                    System.out.println("Repository " + repoName + " is Completed.");
                } else {
                    System.out.println("Repository " + repoName + " is Not Completed. Open issues: " + openIssuesCount);
                }
            } catch (Exception e) {
                System.out.println("No Issues tab found for " + repoName + ". Cannot determine status.");
            }

            driver.navigate().back();

            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        driver.quit();
    }

    private void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = 'yellow'; arguments[0].style.color = 'black';", element);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
