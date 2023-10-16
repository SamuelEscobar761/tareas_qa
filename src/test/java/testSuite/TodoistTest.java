package testSuite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.*;
import session.Session;

public class TodoistTest {
    MenuSectionTodoist menuSection = new MenuSectionTodoist();
    MainPageTodoist mainPage = new MainPageTodoist();
    LoginTodoistSection loginSection = new LoginTodoistSection();

    @AfterEach
    public void close(){
        Session.getInstance().closeSession();
    }
    @BeforeEach
    public void open(){
        Session.getInstance().getBrowser().get("https://todoist.com/es");
    }

    @Test
    public void loginTesting() throws InterruptedException {
        mainPage.loginButton.click();
        loginSection.emailTextBox.setText("samueb8@gmail.com");
        loginSection.pwdTextBox.setText("Samuel/1234");
        loginSection.startEmailLoginButton.click();
        String actualResult = Session.getInstance().getBrowser().findElement(By.xpath("//a[@aria-label='Bandeja de entrada, 2 tareas']/div[@class='X_3UwghUggmfmKrS8M8uwnLl4hgJenHE a83bd4e0 _2f303ac3 _2a3b75a1 _211eebc7']")).getText();
        String expectedResult = "Bandeja de entrada";
        Assertions.assertEquals(expectedResult,actualResult,"No se inicio sesion correctamente");
    }
}
