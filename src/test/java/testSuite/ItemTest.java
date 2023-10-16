package testSuite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.MenuSection;
import session.Session;

import java.util.Date;

public class ItemTest {
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
    public void itemTesting() throws InterruptedException {
        loginTest.loginTesting();
        /* CREATE PROJECT*/
        // click add new project
        String nameProject = "UPB"+new Date().getTime();
        String nameItem = "Item de Samuel";
        String updatedItem = "Actualizado " +  + new Date().getTime();
        menuSection.addNewProject.click();
        menuSection.newProjNameInput.setText(nameProject);
        menuSection.newProjNameButton.click();

        //verificat que se creo el elemento
        Thread.sleep(2000);
        String actualResult= Session.getInstance().getBrowser().findElement(By.id("CurrentProjectTitle")).getText();
        String expectedResult= nameProject;
        Assertions.assertEquals(expectedResult,actualResult,"ERROR el projecto no se creo");

        /* Create Item*/
        // select my project
        menuSection.getProject(nameProject).click();
        menuSection.newItemContentInput.setText(nameItem);
        menuSection.newItemAddButton.click();

        //verificar que se creo el item
        Thread.sleep(2000);
        String actualItemResult = Session.getInstance().getBrowser().findElement(By.xpath("//div[@class='ItemContentDiv' and @style='color: rgb(0, 0, 0);']")).getText();
        String expectedItemResult = nameItem;
        Assertions.assertEquals(expectedItemResult,actualItemResult,"ERROR el item no se creo");

        /* UPDATE ITEM */
        menuSection.getItem(nameItem).click();
        menuSection.editItemName.clearSetText(updatedItem);

        //verificar que se actualizo el item
        Thread.sleep(2000);
        actualItemResult = Session.getInstance().getBrowser().findElement(By.xpath("//div[@class='ItemContentDiv' and @style='color: rgb(0, 0, 0);']")).getText();
        expectedItemResult = updatedItem;
        Assertions.assertEquals(expectedItemResult,actualItemResult,"ERROR el item no se actualizo");
    }
}
