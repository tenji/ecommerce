package com.tenjishen.service.affiliate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.tenjishen.common.util.DateUtil;
import com.tenjishen.common.util.RandomUtil;
import com.tenjishen.common.util.StringUtil;
import com.tenjishen.dao.EmailDao;
import com.tenjishen.dao.RamAccountDao;
import com.tenjishen.dao.RamDao;
import com.tenjishen.model.affiliate.Email;
import com.tenjishen.model.affiliate.Ram;
import com.tenjishen.model.affiliate.RamAccount;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.affiliate.RamService;

@Service
@Transactional
public class RamServiceImpl extends BaseServiceImpl<Ram, Long> implements RamService {
	@Resource
	private RamDao ramDao;
	@Resource
	private EmailDao emailDao;
	@Resource
	private RamAccountDao ramAccountDao;

	@Resource
	public void setBaseDao(RamDao ramDao) {
		super.setBaseDao(ramDao);
	}

	@Override
	public boolean regRamAccount(Long ramId, Long emailId) throws Exception {
		String regCodeUrl = "https://www.rampanel.com/ram/RAM?choice=panreg";
		
		Ram ram = ramDao.getById(ramId);
		Email email = emailDao.getById(emailId);
		
		// If the email has been registered before, just return
		// Plus the email has already been registered for 5 ram accounts, just return
		List<RamAccount> existRamAccounts = ramAccountDao.getListByPropertyName("email", email.getEmail());
		if (existRamAccounts.size() >= 5) {
			return false;
		} else {
			for (RamAccount ramAccount : existRamAccounts) {
				if (ramAccount.getRam().getId().equals(ramId))
					return false;
			}
		}
		
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		HtmlPage page = null;
		if (StringUtils.isNotBlank(ram.getRamRegisterUrl())) {
			page = webClient.getPage(ram.getRamRegisterUrl());
		} else {
			page = webClient.getPage(regCodeUrl);
			
			List<FrameWindow> windows = page.getFrames(); // 获取frame列表
			HtmlPage regCodepageInside = (HtmlPage) windows.get(2).getEnclosedPage(); // Register Page is inside an 'iframe'
			
			List<HtmlSelect> selects = (List<HtmlSelect>) regCodepageInside.getByXPath("//select[@name='Choose_NPCI19']");
			HtmlSelect select = selects.get(0);
			
			select.click();
			select.setSelectedAttribute(ram.getRamRegisterCode(), true);
			System.out.println(page.asXml());
		}
		
		HtmlPage regPage = page;
		
		// Generate Random User Name
		String[] userNameSuffix = ram.getName().split(" ");
		String userName = email.getEmail().split("@")[0] + StringUtil.getRandomStr(new Random().nextInt(3) + 1) + 
				userNameSuffix[new Random().nextInt(userNameSuffix.length)].toLowerCase();
		
		// 1. Dealing with radio inputs
		HtmlForm form = regPage.getFormByName("panelistregistrationform");
		List<String> UncheckedRadioOptions = getUncheckedRadioOptions(form); // Get the unchecked radio options
	    
	    while (UncheckedRadioOptions.size() > 0) {
	    	for (String radioOption : UncheckedRadioOptions) {
				System.out.println(radioOption);
				List<HtmlRadioButtonInput> buttonInputs = form.getRadioButtonsByName(radioOption);
				if (radioOption.equals("sGender")) { // Gender
					buttonInputs.get(0).click(); // Male
				} else if (radioOption.equals("bMarried")) { // Married or not
					buttonInputs.get(0).click(); // Yes
				} else if (radioOption.equals("REG_iPetId")) { // Household pets
					buttonInputs.get(new Random().nextInt(3)).click(); // Select the first 3 options randomly
				} else if (radioOption.equals("REG_iCarAccess")) { // Do you have access to a vehicle?
					buttonInputs.get(0).click(); // Yes
				} else if (radioOption.equals("REG_iEducationId")) { // Education/Work
					buttonInputs.get(2).click(); // College or University degree
				} else if (radioOption.equals("REG_iOccupationId")) {
					buttonInputs.get(0).click(); // Full-time work
				} else if (radioOption.equals("REG_iPosition")) { // level of seniority
					buttonInputs.get(email.getSeniorityLevelId()).click();
				} else if (radioOption.equals("REG_iJobArea")) { // job role
					buttonInputs.get(email.getJobRoleId()).click();
				} else if (radioOption.equals("REG_iBransch")) { // industry
					buttonInputs.get(email.getIndustryId()).click();
				} else if (radioOption.equals("REG_iFamilyIncomeId")) { // household imcome
					buttonInputs.get(6).click();
				} else if (radioOption.startsWith("mediaHabit")) { // last read/watched/listened or visited the following media 
					buttonInputs.get(new Random().nextInt(1)).click(); // Select the first two options
				} else if (radioOption.startsWith("REG_iReadingActivityId")) { // How many issues do you read?
					buttonInputs.get(new Random().nextInt(3) + 3).click();
				} else if (radioOption.startsWith("iInterestGrade")) { // How Interested are you in the following?
					buttonInputs.get(new Random().nextInt(2)).click();
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
	    	
	    	UncheckedRadioOptions = getUncheckedRadioOptions(form);
		}
		
		// 2. Dealing with text inputs
		List<HtmlTextInput> textInputs = (List<HtmlTextInput>) regPage.getByXPath("//input[@type='text']");
		for (HtmlTextInput textInput : textInputs) {
			System.out.println("Text Input: " + textInput.asXml());
			String textInputName = textInput.getNameAttribute();
			textInput.click();
			
			if (textInputName.equals("sFirstName")) { // First Name
				textInput.setText(email.getFirstName());
			} else if (textInputName.equals("sSurName")) { // Last Name
				textInput.setText(email.getLastName());
			} else if (textInputName.equals("sStreetAdress1")) { // Street Address 1
				textInput.setText(email.getAddress());
			} else if (textInputName.equals("sZipCode")) { // Zip Code
				textInput.setText(email.getPostalCode());
			} else if (textInputName.equals("city")) { // City
				textInput.setText(email.getCity().getName());
			} else if (textInputName.equals("sEmail")) { // Email
				textInput.setText(email.getEmail());
			} else if (textInputName.equals("USR_lUserKeyId")) { // User Name
				textInput.setText(userName);
			} else if (textInputName.startsWith("webFreqWeek")) { // At present, how often do you visit www.****.com or ****? 
				textInput.setText(String.valueOf(RandomUtil.randomInt(4, 7))); // Random days a week(from 4 to 7)
			}
			
			Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
		}
		
		// 3. Dealing with password inputs
		List<HtmlPasswordInput> passwordInputs = (List<HtmlPasswordInput>) regPage.getByXPath("//input[@type='password']");
		for (HtmlPasswordInput passwordInput : passwordInputs) {
			System.out.println("Password Input: " + passwordInput.asXml());
			passwordInput.click();
			passwordInput.setText(userName);
		}
		
		// 4. Dealing with select options
		List<HtmlSelect> selects = (List<HtmlSelect>) regPage.getByXPath("//select");
		for (HtmlSelect select : selects) {
			System.out.println(select.asXml());
			
			String selectName = select.getNameAttribute();
			if ("state".equals(selectName)) { // State
				select.setSelectedAttribute(email.getState().getAbbr(), true);
			} else if ("birthyear".equals(selectName)) { // Birth Year
				select.setSelectedAttribute(String.valueOf(DateUtil.getYear(email.getBirthday())), true);
			} else if ("REG_iEthnicity".equals(selectName)) { // Ethnicity
				select.setSelectedAttribute("961", true); // White
			} else if ("REG_iFirstLanguage".equals(selectName)) { // Native Language
				select.setSelectedAttribute("68", true); // English
			} else if ("iNbrOfAdults".equals(selectName)) { // Number of adults in the household 
				select.setSelectedAttribute("4", true);
			} else if ("REG_iStateOfLiving".equals(selectName)) { // Residence
				select.setSelectedAttribute("88", true);
			}
			
			Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
		}
		
	    // 5. Dealing with checkbx inputs
	    List<HtmlCheckBoxInput> checkBoxInputs = (List<HtmlCheckBoxInput>) regPage.getByXPath("//input[@type='checkbox']");
	    for (HtmlCheckBoxInput checkBoxInput : checkBoxInputs) {
	    	System.out.println(checkBoxInput.asXml());
			String checkBoxInputName = checkBoxInput.getNameAttribute();
			if (checkBoxInputName.equals("avtal")) { // Click here to read the agreement.
				checkBoxInput.click();
				checkBoxInput.setChecked(true);
			}
			
			Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
		}
	    
	    // 6. Submit
	    List<HtmlButtonInput> buttonInputs = (List<HtmlButtonInput>) regPage.getByXPath("//input[@type='button']");
	    HtmlButtonInput submitButton = buttonInputs.get(0);
	    
	    regPage = submitButton.click(); // Click to sumit the register page
	    
	    if (0 == webClient.waitForBackgroundJavaScript(60 * 1000)) { // Wait 60 seconds for background JS to execute
	    	System.out.println(regPage);
		}
	    
	    System.out.println(regPage.asXml());
	    
		/*
		 * If the registration process is completed successfully, then add a new Ram Account to the DB.
		 */
		// TODO How to judge whether the registration is success or not.
		RamAccount ramAccount = new RamAccount();
		ramAccount.setCreateTime(new Date());
		ramAccount.setEmail(email.getEmail());
		ramAccount.setLoginName(userName);
		ramAccount.setLoginPassword(userName);
		ramAccount.setRedeemStatus(0); // No Redeem
		ramAccount.setUnansweredNums(0);
		ramAccount.setPointsBeforeOpr(0);
		ramAccount.setPointsAfterOpr(0);
		ramAccount.setRam(ram);
		ramAccountDao.save(ramAccount); // Save to the DB.
	
		return true;
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
	
}
