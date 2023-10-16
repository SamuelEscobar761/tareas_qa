package pages;

import controls.Button;
import controls.Control;
import controls.TextBox;
import org.openqa.selenium.By;

public class LoginTodoistSection {
    public TextBox emailTextBox = new TextBox(By.xpath("//input[@type='email']"));
    public TextBox pwdTextBox = new TextBox(By.xpath("//input[@type='password']"));
    public Button startEmailLoginButton = new Button(By.xpath("//button[@data-gtm-id='start-email-login']"));
}
