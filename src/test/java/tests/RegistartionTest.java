package tests;

import org.junit.Assert;
import org.testng.annotations.Test;

import pageObjects.BasePage;
import util.testData.TestData;

public class RegistartionTest extends BasePage{
	
	@Test
	public void registartiontest() throws Exception{
		gmailPage().navigateToRegistartion();
        registrationPage().fillRegistartionData(TestData.FIRST_NAME, TestData.LAST_NAME, TestData.EMAIL, TestData.PASS_WORD);
		Assert.assertEquals(registrationPage().geterrormsg_0_Passwd().getText(), TestData.ERR_PWD);
	}

}
