package com.tenjishen.service.affiliate.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.tenjishen.common.util.RandomUtil;
import com.tenjishen.dao.affiliate.EvansDataAccountDao;
import com.tenjishen.model.affiliate.Email;
import com.tenjishen.model.affiliate.EvansDataAccount;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.affiliate.EvansDataAccountService;

@Service
@Transactional
public class EvansDataAccountServiceImpl extends BaseServiceImpl<EvansDataAccount, Long> implements EvansDataAccountService {
	@Resource
	private EvansDataAccountDao evansDataAccountDao;

	@Resource
	public void setBaseDao(EvansDataAccountDao evansDataAccountDao) {
		super.setBaseDao(evansDataAccountDao);
	}

	@Override
	public EvansDataAccount answerSurvey(EvansDataAccount evansDataAccount)
			throws Exception {
		final String loginUrl = "http://www.evansdata.com/net/index.php";
		final String emailInputName = "email";
		final String passwordInputName = "password";
		
		Email email = evansDataAccount.getEmail(); // Email Account
		
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setUseInsecureSSL(true); // Use insecure SSL connection(important)
		
		HtmlPage page = webClient.getPage(loginUrl);
		
		final HtmlForm loginForm = page.getForms().get(0);
		final HtmlTextInput emailTextInput = loginForm.getInputByName(emailInputName);
		final HtmlPasswordInput passwordTextInput = loginForm.getInputByName(passwordInputName);
		final HtmlSubmitInput submitInput = loginForm.getInputByValue("LOG IN");
		
		emailTextInput.click();
		emailTextInput.setText(email.getEmail()); // Email
		Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
		passwordTextInput.click();
		passwordTextInput.setText(evansDataAccount.getPassword()); // Password
		
		final HtmlPage homePage = submitInput.click(); // Click to login
		System.out.println(homePage.asXml());
		HtmlAnchor newSurveys = (HtmlAnchor) homePage.getElementsByTagName("li").get(2).getElementsByTagName("a").get(0);
		final HtmlPage surveyListPage = newSurveys.click(); // Go to the survey list page
		
		DomElement surveyListDiv = surveyListPage.getElementById("history");
		List<HtmlElement> surveyListAnchors = surveyListDiv.getElementsByTagName("a"); // Get the survey list anchors
		
		for (HtmlElement surveyAnchor : surveyListAnchors) {
			HtmlPage surveyDetailPage = surveyAnchor.click();
			System.out.println(surveyDetailPage.asXml());
			
			/*
			 * TODO:
			 * 1. What percent of your programming time do you spend using the following languages TODAY?
			 * 2. What percent of the time you spend using scripting languages are used with the following TODAY?
			 * 3. What percentage of a project's time do you typically spend:
			 * 4. onclick=FunQ97(); There are new radio options.
			 * 5. About how many hours per week do you spend using the following? q104*
			 * 6. How would you rate the likelihood of your organization using development tools in the Cloud versus on-premises? 
			 * sliderindex
			 * 7. What percent of your development work is created from scratch or is integrated with existing applications or systems?
			 * 
			 */
			boolean isSubmitPage = false;
			while (!isSubmitPage) {
				HtmlForm form = surveyDetailPage.getForms().get(0);
				
				// 1. Dealing with text inputs
				List<HtmlTextInput> textInputs = (List<HtmlTextInput>) form.getByXPath("//input[@type='text']");
				String[] textContents = {"Nope", "None", "Nothing comes to mind now!", "Nothing to share!"};
				for (HtmlTextInput textInput : textInputs) {
					System.out.println("---------- Text Input: " + textInput.asXml());
					String textInputName = textInput.getNameAttribute();
					
					if (textInputName.equals("xx_email")) { // Email
						textInput.setText(email.getEmail());
					} else if (textInputName.equals("xx_fname")) { // First Name
						textInput.setText(email.getFirstName());
					} else if (textInputName.equals("xx_lname")) { // Last Name
						textInput.setText(email.getLastName());
					} else if (textInputName.equals("xx_co")) { // Company
						textInput.setText(email.getCompany());
					} else if (textInputName.equals("xx_city")) { // City
						textInput.setText(email.getCity().getName());
					} else if (textInputName.equals("xx_zip")) { // Zip Code
						textInput.setText(email.getPostalCode());
					} else if (textInputName.equals("xx_fr")) { // Friend
						continue ; // leave blank
					} else if (textInputName.equals("xx_phone")) { // Phone
						continue ; // leave blank
					} else if (textInputName.equals("xx_cosz")) { // About how many people are in your company at all locations?
						textInput.setText(String.valueOf(RandomUtil.randomInt(200, 1000))); // 200 to 1000 people.
					} else if (textInputName.equals("q5")) {
						textInput.setText(String.valueOf(RandomUtil.randomInt(10, 200))); // 10 to 200 people.
					} else {
						try {
							textInput.click();
							textInput.setText(textContents[new Random().nextInt(textContents.length)]);
						} catch (Exception e) {
							System.out.println("---------- Text Input Click Exception ----------");
						}
					}
					
					Thread.sleep(new Random().nextInt(3000)); // 每次点击随机暂停3秒以内
				}
				System.out.println(surveyDetailPage.asXml());
				
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
					
					Thread.sleep(new Random().nextInt(3000)); // 每次点击随机暂停3秒以内
				}
				
				// 3. Dealing with radio inputs
			    List<String> UncheckedRadioOptions = getUncheckedRadioOptions(form); // Get the unchecked radio options
			    
			    while (UncheckedRadioOptions.size() > 0) {
			    	for (String radioOption : UncheckedRadioOptions) {
						System.out.println(radioOption);
						List<HtmlRadioButtonInput> buttonInputs = form.getRadioButtonsByName(radioOption);
						if (radioOption.equals("q1")) { // How are you involved with the development of software?
							buttonInputs.get(1).click(); // I both use software development tools and manage others who use them
						} else if (radioOption.equals("q3")) { // What type of project are you most experienced in?
							buttonInputs.get(0).click(); //  Business Intelligence
						} else if (radioOption.equals("q26")) { // What type of project are you working on now?
							buttonInputs.get(0).click(); //  Business Intelligence
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
						
						Thread.sleep(new Random().nextInt(3000)); // 每次点击随机暂停3秒以内
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
							if (new Random().nextInt(2) == 1) { // 33.3 percents to be checked
								checkBoxInput.setChecked(true);
							}
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
				
				List<HtmlSubmitInput> submitInputs = (List<HtmlSubmitInput>) form.getByXPath("//input[@id='Submit']");
				if (submitInputs.size() > 0) {
					isSubmitPage = true;
					surveyDetailPage = submitInputs.get(0).click();
					System.out.println(surveyDetailPage.asXml());
				} else {
					List<HtmlSubmitInput> nextInputs = (List<HtmlSubmitInput>) form.getByXPath("//input[@id='Next']");
					surveyDetailPage = nextInputs.get(0).click();
					System.out.println(surveyDetailPage.asXml());
				}
				
			}
		}
		
		return evansDataAccount;
	}
	
	// Get survey radio options
	private List<String> getUncheckedRadioOptions(HtmlForm form) {
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
	private List<String> getUncheckedCheckBoxOptions(HtmlForm form) {
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
