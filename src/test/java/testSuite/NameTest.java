package testSuite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.MenuSection;
import session.Session;

import java.util.Date;

public class NameTest {
    MenuSection menuSection = new MenuSection();
    LoginTest loginTest = new LoginTest();

    @AfterEach
    public void close(){
        Session.getInstance().closeSession();
    }
    @BeforeEach
    public void open(){
        Session.getInstance().getBrowser().get("http://todo.ly/");
    }
    @Test
    public void updateNameTesting() throws InterruptedException {
        String updatedName = "Samuel Actualizado Matias Escobar Bejarano"+new Date().getTime();
        loginTest.loginTesting();
        menuSection.settingsButton.click();
        Thread.sleep(2000);
        menuSection.fullNameInput.clearSetText(updatedName);
        Thread.sleep(2000);
        menuSection.okButton.click();
        menuSection.settingsButton.click();
        Thread.sleep(2000);
        String actualResult = Session.getInstance().getBrowser().findElement(By.id("FullNameInput")).getAttribute("value");
        String expectedResult = updatedName;
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el nombre no se actualizo");
    }
}
