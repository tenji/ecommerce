package com.tenjishen.action.extend;

import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLDivElement;

public class EvansDataActionTest {

	@Test
	public void login() throws Exception {
		System.out.println("---------- Entering Login Method ----------");
		
		final String loginUrl = "http://www.evansdata.com/net/index.php";
		final String loginFormName = "Form";
		final String userNameInputName = "dnn$ctr882$Login$Login_DNN$txtUsername";

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		final HtmlPage page = webClient.getPage(loginUrl);
		final HTMLDivElement divElement = (HTMLDivElement) page.getByXPath("//div[@class='container']");
		System.out.println("divElement" + divElement.getText());
		
		final HtmlForm loginForm = page.getFormByName(loginFormName);
		final List<HtmlTextInput> textInputs = (List<HtmlTextInput>) loginForm.getByXPath("//input[@type='text']");
		
		System.out.println("page" + page.asText());
		System.out.println("loginForm" + loginForm.asText());
		for (HtmlTextInput textInput : textInputs) {
			System.out.println("textInput" + textInput.asText());
		}

	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("---------- Entering Login Method ----------");
		
		final String loginUrl = "http://www.evansdata.com/net/index.php";
		final String emailInputName = "email";
		final String passwordInputName = "password";

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setCssEnabled(true);
		final HtmlPage page = webClient.getPage(loginUrl);
		
		final HtmlForm loginForm = page.getForms().get(0);
		final HtmlTextInput emailTextInput = loginForm.getInputByName(emailInputName);
		final HtmlPasswordInput passwordTextInput = loginForm.getInputByName(passwordInputName);
		final HtmlSubmitInput submitInput = loginForm.getInputByValue("LOG IN");
		
		emailTextInput.click();
		emailTextInput.setText("joetodf@gmail.com");
		Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
		passwordTextInput.click();
		passwordTextInput.setText("joetodf");
		
		final HtmlPage homePage = submitInput.click(); // Click to login
		HtmlAnchor newSurveys = (HtmlAnchor) homePage.getElementsByTagName("li").get(2).getElementsByTagName("a").get(0);
		final HtmlPage surveyListPage = newSurveys.click(); // Go to the survey list page
		
		DomElement surveyListDiv = surveyListPage.getElementById("history");
		List<HtmlElement> surveyListAnchors = surveyListDiv.getElementsByTagName("a"); // Get the survey list anchors
		
		// System.out.println(homePage.asXml());
		// System.out.println(newSurveys.asXml());
		for (HtmlElement surveyAnchor : surveyListAnchors) {
			HtmlPage surveyDetailPage = surveyAnchor.click();
			
			boolean flag = true;
			
			while (flag) {
				// Please enter your email address and other contact information so that we can notify you if you win $500 in the drawing!
				HtmlForm surveyForm = surveyDetailPage.getFormByName("survey");
				HtmlTextInput emailAddressInput = surveyForm.getInputByName("xx_email");
				emailAddressInput.setText("joetodf@gmail.com");
				Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
				HtmlTextInput firstNameInput = surveyForm.getInputByName("xx_fname");
				firstNameInput.setText("Patricia");
				Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
				HtmlTextInput lastNameInput = surveyForm.getInputByName("xx_lname");
				lastNameInput.setText("Butler");
				Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
				HtmlTextInput companyInput = surveyForm.getInputByName("xx_co");
				companyInput.setText("Aavid Thermalloy LLC");
				Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
				
				HtmlSelect countrySelect = surveyForm.getSelectByName("xx_cnt");
				countrySelect.setSelectedAttribute("US", true); // Select state FL
				Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
				
				HtmlTextInput cityTextInput = surveyForm.getInputByName("xx_city");
				if (null != cityTextInput) {
					cityTextInput.setText("Dallas");
					Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
				}
				HtmlTextInput zipCodeTextInput = surveyForm.getInputByName("xx_zip");
				if (null != zipCodeTextInput) {
					zipCodeTextInput.setText("75260");
					Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
				}
				
				
				flag = false;
			}
			
			System.out.println(surveyDetailPage.asXml());

		}
		
	}

}
