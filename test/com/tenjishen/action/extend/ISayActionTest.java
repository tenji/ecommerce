package com.tenjishen.action.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLDivElement;

public class ISayActionTest {

	@Test
	@Ignore
	public void login() throws Exception {
		System.out.println("---------- Entering Login Method ----------");
		
		final String loginUrl = "http://i-say.com/SignIn/tabid/262/language/en-US/Default.aspx";
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
		
		final String loginUrl = "http://i-say.com/SignIn/tabid/262/language/en-US/Default.aspx";
		final String loginFormName = "Form";
		final String userNameInputName = "dnn$ctr882$Login$Login_DNN$txtUsername";
		final String passwordInputName = "dnn$ctr882$Login$Login_DNN$txtPassword";
		final String submitInputName = "dnn$ctr882$Login$Login_DNN$cmdLogin";
		final String moreSurveyButtonName = "dnn_ctr675_NewSurveysSmall_lblBtnMoreSurveys";

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setCssEnabled(true);
		final HtmlPage page = webClient.getPage(loginUrl);
		
		final HtmlForm loginForm = page.getFormByName(loginFormName);
		final HtmlTextInput userNameTextInput = loginForm.getInputByName(userNameInputName);
		final HtmlPasswordInput passwordTextInput = loginForm.getInputByName(passwordInputName);
		final HtmlSubmitInput submitInput = loginForm.getInputByName(submitInputName);
		
		userNameTextInput.click();
		userNameTextInput.setText("smithllljj@gmail.com");
		Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
		passwordTextInput.click();
		passwordTextInput.setText("smithllljj123");
		
		final HtmlPage surveysPage = submitInput.click(); // Click to login
		
		final HtmlSpan moreSurveySpan = (HtmlSpan) surveysPage.getElementById(moreSurveyButtonName);
		final HtmlPage moreSurveysPage = moreSurveySpan.click();
		
		DomElement postContainerElement = moreSurveysPage.getElementById("postContainer");
		
		List<HtmlElement> postElements = (List<HtmlElement>) postContainerElement.getByXPath("//div[@class='post']"); // Get the 'post' elements
		
		// System.out.println("page" + page.asXml());
		// System.out.println("loginForm" + loginForm.asXml());
		// System.out.println("userNameTextInput" + userNameTextInput.asXml());
		// System.out.println("moreSurveyPage: " + moreSurveysPage.asXml());
		// System.out.println(postContainerElement.asXml());
		for (HtmlElement htmlElement : postElements) {
			HtmlElement callToActionElement = (HtmlElement) htmlElement.getByXPath("//div[@class='callToAction']").get(0);
			HtmlAnchor answerTheSurveyAnchor = (HtmlAnchor) callToActionElement.getElementsByTagName("a").get(0);
			
			HtmlPage surveyDetailPage = answerTheSurveyAnchor.click();
			
			final int maxCycleNums = 50; // The max cycle numbers
		    /*
		     * Set the maximum number of cycles to prevent endless loop
		     */
			for (int i = 0; i < maxCycleNums; i++) {
				System.out.println(surveyDetailPage.asXml());
				List<FrameWindow> windows = surveyDetailPage.getFrames(); // 获取frame列表
				
				HtmlPage surveyContentPage = (HtmlPage) windows.get(0).getEnclosedPage(); // 获取第一个frame，也就是调查内容页
				HtmlElement contentElement = (HtmlElement) surveyContentPage.getElementById("content");
				
				// TODO 1. Dealing with the welcome page, no 'input' elements in contentElement
				
				// TODO 2. Dealing with the zip code page, there is an 'input' element whose id is 'ddb1'
				
				// TODO 3. Dealing with the people who live with you
				/*
				 * Gender: _QHHCMP20_Q__2_QGENDERHH_C _QHHCMP20_Q__3_QGENDERHH_C
				 * Age: _QHHCMP20_Q__2_QAGEHH _QHHCMP20_Q__3_QAGEHH
				 * Relationship: _QHHCMP20_Q__2_QRELATIONSHIPHH_C _QHHCMP20_Q__3_QRELATIONSHIPHH_C
				 * 
				 */
				
				// TODO 4. Dealing with confidential information, 'radio' name is '_QNDAIPS1_C'
				
				// TODO 5. Dealing with the state select page, 'select' element whose id is '_Q0_C'
				
				// TODO 6. Dealing with the household income page, 'radio' name is '_QQS5_C'
				
				// TODO 7. Dealing with the game plays page, 'radio' name is '_QrewardRedirParticipate1_C'
				
				HtmlSubmitInput submitButtonInput = (HtmlSubmitInput) surveyContentPage.getByXPath("//input[@type='submit']").get(0);
				surveyContentPage = submitButtonInput.click();
				
				System.out.println(surveyContentPage.asText());
				Thread.sleep(new Random().nextInt(3000)); // 每提交一页随机暂停3秒以内
			}
		}
	}
	
	@Test
	@Ignore
	public void test() throws Exception {
		final String loginUrl = "http://localhost:8080/ecommerce/shops/test/test20140911";

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setCssEnabled(true);
		final HtmlPage page = webClient.getPage(loginUrl);
		
		HtmlForm form = page.getForms().get(0);
		List<HtmlInput> radioInputs = form.getInputsByName("sex");
		
		for (HtmlInput radioInput : radioInputs) {
			System.out.println(radioInput.isChecked());
			radioInput.click();
			System.out.println(radioInput.asXml());
			System.out.println(radioInput.isChecked());
		}
	}
	
	@Test
	public void test20140911() throws Exception {
		final String loginUrl = "http://www.evansdata.com/net/index.php";
		final String emailInputName = "email";
		final String passwordInputName = "password";

		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setCssEnabled(true);
		HtmlPage page = webClient.getPage(loginUrl);
		
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
		
		for (HtmlElement surveyAnchor : surveyListAnchors) {
			HtmlPage surveyDetailPage = surveyAnchor.click();
			
			boolean isSubmitPage = false;
			while (!isSubmitPage) {
				HtmlForm form = surveyDetailPage.getForms().get(0);
				
				// 1. Dealing with text inputs
				List<HtmlTextInput> textInputs = (List<HtmlTextInput>) form.getByXPath("//input[@type='text']");
				String[] textContents = {"Nope", "None", "Nothing comes to mind now!"};
				for (HtmlTextInput textInput : textInputs) {
					System.out.println("Text Input: " + textInput.asXml());
					String textInputName = textInput.getNameAttribute();
					textInput.click();
					
					if (textInputName.equals("xx_email")) { // Email
						textInput.setText("joetodf@gmail.com");
					} else if (textInputName.equals("xx_fname")) { // First Name
						textInput.setText("Patricia");
					} else if (textInputName.equals("xx_lname")) { // Last Name
						textInput.setText("Butler");
					} else if (textInputName.equals("xx_co")) { // Company
						textInput.setText("Aavid Thermalloy LLC");
					} else if (textInputName.equals("xx_city")) { // City
						textInput.setText("Dallas");
					} else if (textInputName.equals("xx_zip")) { // Zip Code
						textInput.setText("75260");
					} else if (textInputName.equals("xx_fr")) { // Friend
						continue ; // leave blank
					} else if (textInputName.equals("xx_phone")) { // Phone
						continue ; // leave blank
					} else {
						try {
							textInput.click();
							textInput.setText(textContents[new Random().nextInt(3)]);
						} catch (Exception e) {
							System.out.println("---------- Text Input Click Exception ----------");
						}
					}
					
					Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
				}
				
				// 2. Dealing with select options
				List<HtmlSelect> selects = (List<HtmlSelect>) form.getByXPath("//select");
				for (HtmlSelect select : selects) {
					System.out.println(select.asXml());
					if ("xx_cnt".equals(select.getNameAttribute())) {
						select.setSelectedAttribute("US", true);
					} else {
						// Select an option randomly
						select.setSelectedAttribute(select.getOption(new Random().nextInt(select.getOptionSize() - 1)), true);
					}
					
					Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
				}
				
				// 3. Dealing with radio inputs
			    List<String> UncheckedRadioOptions = getUncheckedRadioOptions(form); // Get the unchecked radio options
			    
			    while (UncheckedRadioOptions.size() > 0) {
			    	for (String radioOption : UncheckedRadioOptions) {
						System.out.println(radioOption);
						List<HtmlRadioButtonInput> buttonInputs = form.getRadioButtonsByName(radioOption);
						if (radioOption.equals("q1")) { // How are you involved with the development of software?
							buttonInputs.get(1).click(); // I both use software development tools and manage others who use them
						} else {
							// Select the radio options randomly
							if (buttonInputs.size() > 0) {
								try {
									buttonInputs.get(new Random().nextInt(buttonInputs.size() - 1)).click(); // random check
								} catch (Exception e) {
									System.out.println("---------- Radio Input Click Exception ----------");
								}
							}
						}
						
						Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
					}
			    	
			    	UncheckedRadioOptions = getUncheckedRadioOptions(form); // Reverse
				}
			    
				// 4. Dealing with checkbox inputs
				List<String> surveyCheckBoxOptions = getUncheckedCheckBoxOptions(form);
				// Generate two random numbers
				int[] ranNums = new int[2];
				for (String checkBoxOption : surveyCheckBoxOptions) {
					System.out.println(checkBoxOption);
					List<HtmlInput> checkBoxInputs = form.getInputsByName(checkBoxOption);
					if (checkBoxInputs.size() < 3) {
						for (HtmlInput checkBoxInput : checkBoxInputs) {
							checkBoxInput.setChecked(true);
						}
					} else { 
						ranNums[0] = new Random().nextInt(checkBoxInputs.size() - 2);				
						ranNums[1] = new Random().nextInt(checkBoxInputs.size() - 2);
						for (int i = 0; i < checkBoxInputs.size(); i++) {
							if (ranNums[0] == i || ranNums[1] == i) {
								checkBoxInputs.get(i).setChecked(true);
							}
						}
					}
					
					Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
				}
				
				System.out.println(form.asXml());
				
				try {
					if (form.getElementById("Submit") != null) {
						isSubmitPage = true;
					} else {
						HtmlInput nextInput = form.getElementById("Next");
						System.out.println(nextInput.asXml());
					}
				} catch (Exception e) {
					System.out.println("---------- Submit Input Not Found Exception  ----------");
				}
			}
		}
		
	}
	
	// Get survey radio options
	private static List<String> getUncheckedRadioOptions(HtmlForm form) {
		// List<HtmlRadioButtonInput> uncheckedRadioInputs = new ArrayList<HtmlRadioButtonInput>();
		List<String> surveyRadioOptions = new ArrayList<String>();
		List<HtmlElement> htmlElements = (List<HtmlElement>) form.getByXPath("//input[@type='radio']");
	    for (int i = 0; i < htmlElements.size(); i++) {
	    	HtmlRadioButtonInput radioButtonInput = (HtmlRadioButtonInput) htmlElements.get(i);
	    	String radioOptionName = radioButtonInput.getNameAttribute();
	    	if (!surveyRadioOptions.contains(radioOptionName)) {
	    		List<HtmlRadioButtonInput> radioButtonInputs = form.getRadioButtonsByName(radioOptionName);
	    		boolean checkFlag = false; // The radio group is checked or not
	    		for (HtmlRadioButtonInput buttonInput : radioButtonInputs) {
					if (buttonInput.isChecked()) {
						checkFlag = true;
					}
				}
	    		if (!checkFlag) {
	    			surveyRadioOptions.add(radioOptionName);
	    			// uncheckedRadioInputs.add(radioButtonInput);
				}
			}
		}
	    
		return surveyRadioOptions;
	}
	
	// Get survey checkbox options
	private static List<String> getUncheckedCheckBoxOptions(HtmlForm form) {
		List<String> surveyCheckBoxOptions = new ArrayList<String>();
		List<HtmlElement> htmlElements = (List<HtmlElement>) form.getByXPath("//input[@type='checkbox']");
		for (int i = 0; i < htmlElements.size(); i++) {
	    	HtmlCheckBoxInput checkBoxInput = (HtmlCheckBoxInput) htmlElements.get(i);
	    	String checkBoxOptionName = checkBoxInput.getNameAttribute();
	    	if (!surveyCheckBoxOptions.contains(checkBoxOptionName)) {
	    		List<HtmlInput> checkBoxInputs = form.getInputsByName(checkBoxOptionName);
	    		boolean checkFlag = false; // The radio group is checked or not
	    		for (HtmlInput input : checkBoxInputs) {
					if (input.isChecked()) {
						checkFlag = true;
					}
				}
	    		if (!checkFlag) {
	    			surveyCheckBoxOptions.add(checkBoxOptionName);
				}
			}
		}
		
		return surveyCheckBoxOptions;
	}
}
