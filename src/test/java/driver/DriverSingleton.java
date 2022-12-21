package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverSingleton {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver.get()) {
            System.out.println("Driver Singleton. Thread id is: " + Thread.currentThread().getId());
            switch (System.getProperty("browser")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
                    driver.set(new FirefoxDriver(firefoxOptions));
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
                    driver.set(new ChromeDriver(chromeOptions));
                    System.out.println("Driver. Thread id is: " + Thread.currentThread().getId());
                }
            }
            driver.get().manage().window().maximize();
        }
        System.out.println("Driver Singleton outside. Thread id is: " + Thread.currentThread().getId());
        return driver.get();
    }

    public static void closeBrowser() {
        System.out.println("Close driver. Thread id is: " + Thread.currentThread().getId());
        driver.get().quit();
        driver.remove();
    }
}
