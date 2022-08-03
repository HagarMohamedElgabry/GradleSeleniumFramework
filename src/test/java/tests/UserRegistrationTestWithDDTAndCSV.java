package tests;



import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencsv.CSVReader;

import pages.HomePage;
import pages.LoginPage;
import pages.UserRegistrationPage;

public class UserRegistrationTestWithDDTAndCSV extends TestBase
{
	HomePage homeObject ; 
	UserRegistrationPage registerObject ; 
	LoginPage loginObject ; 

	CSVReader reader ; 

	@Test(priority=1,alwaysRun=true)
	public void UserCanRegisterSuccssfully() throws IOException 
	{
		// get path of CSV file 
		String CSV_file = System.getProperty("user.dir")+"/src/test/java/data/UserData.csv";
		reader = new CSVReader(new FileReader(CSV_file)); 

		String[] csvCell ; 

		// while loop will be executed till the lastvalue in CSV file . 
		while((csvCell = reader.readNext()) != null) 
		{
			String firstname = csvCell[0]; 
			String lastName = csvCell[1]; 
			String email = csvCell[2]; 
			String password = csvCell[3]; 

			homeObject = new HomePage(driver); 
			homeObject.openRegistrationPage();
			registerObject = new UserRegistrationPage(driver); 
			registerObject.userRegistration(firstname, lastName, email, password);
			Assert.assertTrue(registerObject.successMessage.getText().contains("Your registration completed"));
			registerObject.userLogout();
			homeObject.openLoginPage();
			loginObject = new LoginPage(driver); 
			loginObject.UserLogin(email,password);
			Assert.assertTrue(registerObject.logoutLink.getText().contains("Log out"));
			registerObject.userLogout();
		}
	}
}
