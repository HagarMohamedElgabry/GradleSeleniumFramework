package steps;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import pages.HomePage;
import pages.UserRegistrationPage;
import tests.TestBase;

public class UserRegistration extends TestBase {

	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 

	@Given("^the user in the home page$")
	public void the_user_in_the_home_page()  {
		homeObject = new HomePage(driver); 
		homeObject.openRegistrationPage();
	}

	@When("^I click on register link$")
	public void i_click_on_register_link()  {
		Assert.assertTrue(driver.getCurrentUrl().contains("register"));
	}

	@When("^I entered \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\" , \"([^\"]*)\"$")
	public void i_entered(String firstname, String lastname, String email, String password) {
		registerObject = new UserRegistrationPage(driver); 
		registerObject.userRegistration(firstname, lastname,email,password);
	}

	@Then("^The registration page displayed successfully$")
	public void the_registration_page_displayed_successfully()  {
		registerObject.userLogout();
	}
}
