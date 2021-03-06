package junit3.tutorial_1_server;

import com.google.common.testing.TearDown;
import com.google.guiceberry.junit3.ManualTearDownGuiceBerry;
import com.google.inject.Inject;

import junit.framework.TestCase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import tutorial_1_server.testing.PetStoreEnv0Simple;
import tutorial_1_server.testing.PortNumber;

public class Example0HelloServerTest extends TestCase {

  private TearDown toTearDown;
  
  @Override
  protected void tearDown() throws Exception {
    toTearDown.tearDown();
    super.tearDown();
  }
  
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    toTearDown = ManualTearDownGuiceBerry.setUp(this, PetStoreEnv0Simple.class);
  }
  
  @Inject
  WebDriver driver;
  
  @Inject
  @PortNumber int portNumber;

  public void testPetStoreWelcomeMessage() {
    driver.get("http://localhost:" + portNumber);
    WebElement element = driver.findElement(By.xpath("//div[@id='welcome']"));
    assertEquals("Welcome!", element.getText());
  }

  public void testPetStoreTitle() {
    driver.get("http://localhost:" + portNumber);
    assertEquals("Welcome to the pet store", driver.getTitle());
  }
}
