package basicWeb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Date;

public class CUDProjectTest {    ChromeDriver chrome;
    @BeforeEach
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/chrome/chromedriver.exe");
        chrome = new ChromeDriver();
        chrome.manage().window().maximize();
        // implicit
        chrome.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
        chrome.get("http://todo.ly/");
    }
    //  .basic("upbapi@upbapi.com", "12345")
    @Test
    public void createUpdateDeleteProject() throws InterruptedException {
        // click login button
        chrome.findElement(By.xpath("//img[@src=\"/Images/design/pagelogin.png\"]")).click();
        // set email
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("selenium@seleniumupb.com");
        // set password
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("12345");
        // click login
        chrome.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        // verificar si existe el control del logout


        /* CREATE PROJECT*/
        // click add new project
        String nameProject = "UPB"+new Date().getTime();

        chrome.findElement(By.xpath("//td[text()='Add New Project']")).click();
        // fill name
        chrome.findElement(By.id("NewProjNameInput")).sendKeys(nameProject);
        // click save
        chrome.findElement(By.id("NewProjNameButton")).click();
        //verificat que se creo el elemento
        Thread.sleep(2000);
        String actualResult= chrome.findElement(By.id("CurrentProjectTitle")).getText();
        String expectedResult= nameProject;
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el projecto no se creo");

        /* UPDATE  PROJECT*/
        // select my project
        chrome.findElement(By.xpath("//li[last()]//td[text()='"+nameProject+"']")).click();
        // click menu option
        chrome.findElement(By.xpath("//div[@style=\"display: block;\"]/img[@title='Options']")).click();
        // click edit
        chrome.findElement(By.xpath("//ul[contains(@style,'block')]/li/a[text()='Edit']")).click();
        //fill new name project
        String newNameProject = "QA"+new Date().getTime();
        chrome.findElement(By.xpath("//td/div/input[@id=\"ItemEditTextbox\"]")).clear();
        chrome.findElement(By.xpath("//td/div/input[@id=\"ItemEditTextbox\"]")).sendKeys(newNameProject);

        // click save
        chrome.findElement(By.xpath("//td/div/img[@id=\"ItemEditSubmit\"]")).click();
        // verificamos
        Assertions.assertTrue(chrome.findElement(By.xpath("//li[last()]//td[text()='"+newNameProject+"']")).isDisplayed(),
                "ERROR!! el nombre no fue actualizado");


        /* DELETE  PROJECT*/
        // select my project
        chrome.findElement(By.xpath("//li[last()]//td[text()='"+newNameProject+"']")).click();
        // click menu option
        chrome.findElement(By.xpath("//div[@style=\"display: block;\"]/img[@title='Options']")).click();
        // click delete
        chrome.findElement(By.id("ProjShareMenuDel")).click();
        //>>>>> ALERT <<<<
        chrome.switchTo().alert().accept();
        Thread.sleep(2000);
        Assertions.assertTrue(chrome.findElements(By.xpath("//li[last()]//td[text()='"+newNameProject+"']")).size() <= 0,
                "ERROR!! el project no fue eliminado");

    }

    @AfterEach
    public void closeBrowser(){
        chrome.quit();
    }

}
