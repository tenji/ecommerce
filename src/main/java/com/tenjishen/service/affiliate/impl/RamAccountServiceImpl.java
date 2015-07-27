package com.tenjishen.service.affiliate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tenjishen.common.constants.Constants;
import com.tenjishen.dao.LogAdminRamAccountDao;
import com.tenjishen.dao.RamAccountDao;
import com.tenjishen.model.affiliate.RamAccount;
import com.tenjishen.model.log.LogAdminRamAccount;
import com.tenjishen.service.BaseServiceImpl;
import com.tenjishen.service.affiliate.RamAccountService;
import com.tenjishen.vo.PageBean;
import com.tenjishen.vo.query.affiliate.RamAccountQuery;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlCheckBoxInput;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTextArea;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

@Service
@Transactional
public class RamAccountServiceImpl extends BaseServiceImpl<RamAccount, Long> implements RamAccountService {
	@Resource
	private RamAccountDao ramAccountDao;
	@Resource
	private LogAdminRamAccountDao logAdminRamAccountDao;
	
	private static Logger logger = Logger.getLogger(RamAccountServiceImpl.class);

	@Resource
	public void setBaseDao(RamAccountDao ramColumbusDao) {
		super.setBaseDao(ramColumbusDao);
	}

	@Override
	public PageBean findByPageBean(PageBean pageBean, RamAccountQuery ramAccountQuery) {
		
		return ramAccountDao.findByPageBean(pageBean, ramAccountQuery);
	}

	@SuppressWarnings("unchecked")
	@Override
	public RamAccount answerSurvey(RamAccount ramAccount) throws Exception {
		// 创建积分变更日志
		LogAdminRamAccount logAdminRamAccount = new LogAdminRamAccount();
		logAdminRamAccount.setRamAccount(ramAccount);
		logAdminRamAccount.setBeginTime(new Date()); // 开始时间
		
		HtmlPage surveyPage = null;
		// 此账户有未完成的调查
		if (ramAccount.getUnansweredNums() > 0) {
			final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
			final HtmlPage page;
			if (!StringUtils.isBlank(ramAccount.getLoginUrl())) {
				page = webClient.getPage(ramAccount.getLoginUrl()); // When the loginUrl is not null
			} else {
				final HtmlPage loginPage = webClient.getPage(Constants.RAM_SITE_LOGIN_URL);
				final HtmlForm loginForm = loginPage.getForms().get(1);
				System.out.println(loginForm.asText());
				
				final HtmlTextInput userNameInput = loginForm.getInputByName("suid");
				final HtmlPasswordInput passwordInput = loginForm.getInputByName("spwd");
				final HtmlSubmitInput submitInput = loginForm.getInputByName("submit");
				
				userNameInput.click();
				userNameInput.setText(ramAccount.getLoginName());
				Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
				passwordInput.click();
				passwordInput.setText(ramAccount.getLoginPassword());
				
				page = submitInput.click(); // Click to login
			}
		    
		    List<FrameWindow> windows = page.getFrames(); // 获取frame列表
		    final HtmlPage entryMenu = (HtmlPage) windows.get(1).getEnclosedPage(); // 获取第二个frame，也就是rampanel左边栏
		    
		    HtmlAnchor anchor = (HtmlAnchor) entryMenu.getElementsByTagName("a").get(3); // 获取"Answer the survey"链接
		    anchor.click(); // 点击"Answer the survey"链接
		    
		    final HtmlPage mainContent = (HtmlPage) windows.get(2).getEnclosedPage(); // 获取"Answer the survey"页面
		    
			HtmlForm htmlForm = mainContent.getFormByName("surveylist"); // 获取表单对象
		    HtmlAnchor answerSurveys = (HtmlAnchor) htmlForm.getElementsByTagName("a").get(0); // 获取Please answer the surveys链接
		    System.out.println(answerSurveys.asText());
		    
		    answerSurveys.click(); // Click to answer surveys
		    surveyPage = (HtmlPage) windows.get(2).getEnclosedPage();
		    
		    final int maxCycleNums = 50; // The max cycle numbers
		    /*
		     * Set the maximum number of cycles to prevent endless loop
		     */
		    for (int i = 0; i < maxCycleNums; i++) {
		    	// 调查已经完成
				if (surveyPage.getElementsByTagName("a").size() == 0) {
					break;
				}
				HtmlForm panelListSurveyForm = surveyPage.getForms().get(0); // 获取调查表单
				
				Set<String> surveyOptions = getSurveyOptions(panelListSurveyForm); // 获取调查选项
				// Traverse and random select "radio"
				for (String surveyOption : surveyOptions) {
					System.out.println(surveyOption);
					List<HtmlRadioButtonInput> buttonInputs = panelListSurveyForm.getRadioButtonsByName(surveyOption); // 
					if (surveyOption.equals("bPaperRead")) {
						if (buttonInputs.size() > 0) {
							buttonInputs.get(0).click(); // Did you read or look at the newspaper? Choose Yes
						}
					} else if (surveyOption.equals("REG_iAdReadId")) {
						// Did you see/read this page when you read the newspaper? Choose Saw/read something on the page
						buttonInputs.get(buttonInputs.size() - 2).click(); 
					} else if (surveyOption.equals("webFreqDay")) {
						// At present, how often do you visit...
						HtmlInput input =surveyPage.getHtmlElementById(surveyOption);
						input.click();
						input.focus();
						input.type(Integer.toString((new Random().nextInt(4))));
					} else {
						// 排除非选择项
						if (buttonInputs.size() > 0) {
							try {
								buttonInputs.get(new Random().nextInt(buttonInputs.size() - 1)).click(); // random check
							} catch (Exception e) {
								System.out.println("---------------------Exception--------------------");
							}
						}
					}
					
					Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停5秒以内
				}
				
				// Traverse and random select "checkbox"
				final List<HtmlCheckBoxInput> checkBoxInputs = (List<HtmlCheckBoxInput>) panelListSurveyForm.getByXPath("//input[@type='checkbox']");
				for (HtmlCheckBoxInput checkBoxInput: checkBoxInputs) {
					if (new Random().nextInt(5) == 2) {
						checkBoxInput.click(); // Random select
					}
				}
				
				// Deal with Textareas
				final List<HtmlTextArea> textAreas = (List<HtmlTextArea>)panelListSurveyForm.getByXPath("//textarea");
				for (HtmlTextArea htmlTextArea : textAreas) {
					String[] textareaContents = {"Nope", "None", "Nothing comes to mind now"};
					
					System.out.println("Textarea" + htmlTextArea.asXml());
					htmlTextArea.click();
					htmlTextArea.setText(textareaContents[new Random().nextInt(3)]);
				}
				
				// When was the last time you visited
				try {
					final HtmlTable table = surveyPage.getHtmlElementById("mycal_1");
					if (null != table) {
						final List<HtmlTableCell> cells = (List<HtmlTableCell>) table.getByXPath("//td[contains(concat(' ', normalize-space(@class), ' '), ' selectable ')]");
					    HtmlTableCell cellYestoday = cells.get(cells.size() - 1);
					    cellYestoday.click(); // select
					}
				} catch (ElementNotFoundException e) {
					System.out.println("ElementNotFoundException--------------------------------------");
				}
				
				List<HtmlElement> nextOrEndBtns = panelListSurveyForm.getElementsByTagName("button");
				System.out.println(((HtmlButton)nextOrEndBtns.get(0)).asText());
				if (nextOrEndBtns.size() > 1) {
					((HtmlButton)nextOrEndBtns.get(1)).click();
				} else {
					((HtmlButton)nextOrEndBtns.get(0)).click();
				}
				surveyPage = (HtmlPage) windows.get(2).getEnclosedPage();
			    
				System.out.println(surveyPage.asText());
				System.out.println(surveyPage.getElementsByTagName("a").size());
			    Thread.sleep(new Random().nextInt(3000)); // 每提交一页随机暂停3秒以内
			}

		    // get reward points
			anchor = (HtmlAnchor) entryMenu.getElementsByTagName("a").get(4); // 获取"Rewards"链接
			anchor.click();
			HtmlPage rewardPage = (HtmlPage) windows.get(2).getEnclosedPage();
			List<DomElement> list = rewardPage.getElementsByTagName("strong");
			
			// update ramAccount
			ramAccount.setUnansweredNums(0);
			ramAccount.setPointsBeforeOpr(ramAccount.getPointsAfterOpr()); // 操作前积分
			ramAccount.setPointsAfterOpr(Integer.parseInt(list.get(0).asText())); // 操作后积分
			ramAccount.setLatestOprTime(new Date());
			ramAccountDao.update(ramAccount);
			
			// 插入积分变更记录
			logAdminRamAccount.setEndTime(new Date());
			logAdminRamAccount.setPoints(ramAccount.getPointsAfterOpr());
			logAdminRamAccountDao.save(logAdminRamAccount);
		}
		
		return ramAccount;
	}
	
	@Override
	public RamAccount checkSurveyNums(RamAccount ramAccount) throws Exception {
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		final HtmlPage page;
		if (!StringUtils.isBlank(ramAccount.getLoginUrl())) {
			page = webClient.getPage(ramAccount.getLoginUrl()); // When the loginUrl is not null
		} else {
			final HtmlPage loginPage = webClient.getPage(Constants.RAM_SITE_LOGIN_URL); // get ram site login url
			final HtmlForm loginForm = loginPage.getForms().get(1); // get login form
			System.out.println(loginForm.asText());
			
			final HtmlTextInput userNameInput = loginForm.getInputByName("suid");
			final HtmlPasswordInput passwordInput = loginForm.getInputByName("spwd");
			final HtmlSubmitInput submitInput = loginForm.getInputByName("submit");
			
			userNameInput.click();
			userNameInput.setText(ramAccount.getLoginName());
			Thread.sleep(new Random().nextInt(1000)); // Random stop within 1 second
			passwordInput.click();
			passwordInput.setText(ramAccount.getLoginPassword());
			
			page = submitInput.click(); // Click to login
		}
	    
	    List<FrameWindow> windows = page.getFrames(); // 获取frame列表
	    final HtmlPage entryMenu = (HtmlPage) windows.get(1).getEnclosedPage(); // 获取第二个frame，也就是rampanel左边栏
	    
	    HtmlAnchor anchor = (HtmlAnchor) entryMenu.getElementsByTagName("a").get(3); // 获取Anchor链接
	    anchor.click(); // 点击"Answer the survey"链接
	    
	    final HtmlPage mainContent = (HtmlPage) windows.get(2).getEnclosedPage(); // 获取"Answer the survey"页面
	    
	    List<HtmlAnchor> anchors = mainContent.getAnchors(); // get "Please answer the surveys" anchor
	    
	    int surveyNumbers = 0;
	    if (anchors.size() >= 2) {
	    	System.out.println(anchors.get(0).asText());
	    	try {
	    		surveyNumbers = Integer.parseInt(anchors.get(0).asText().trim().substring(0, 1)); // 获取未完成调查数量
			} catch (NumberFormatException nfe) {
				ramAccount.setLatestCheckTime(new Date());
				ramAccountDao.update(ramAccount);
				
				return ramAccount;
			}
	    	
		}
	    ramAccount.setUnansweredNums(surveyNumbers);
	    ramAccount.setLatestCheckTime(new Date());
	    ramAccountDao.update(ramAccount); // update unanswered survey nums
		
		return ramAccount;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public synchronized RamAccount answerSurveyExtend(RamAccount ramAccount)
			throws Exception {
		// 创建积分变更日志
		LogAdminRamAccount logAdminRamAccount = new LogAdminRamAccount();
		logAdminRamAccount.setRamAccount(ramAccount);
		logAdminRamAccount.setBeginTime(new Date()); // 开始时间
		
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setUseInsecureSSL(true); // Use insecure SSL connection(not necessary in this place)
		
		final HtmlPage page; // The 'Page' returned when log in successfully.
		if (!StringUtils.isBlank(ramAccount.getLoginUrl())) {
			page = webClient.getPage(ramAccount.getLoginUrl()); // When the loginUrl is not null, get page directly.
		} else {
			final HtmlPage loginPage = webClient.getPage(Constants.RAM_SITE_LOGIN_URL);
			final HtmlForm loginForm = loginPage.getForms().get(1);
			System.out.println("---------- Login Form Details: ----------");
			System.out.println(loginForm.asText());
			System.out.println("---------- Login Form End ----------");
			
			final HtmlTextInput userNameInput = loginForm.getInputByName("suid");
			final HtmlPasswordInput passwordInput = loginForm.getInputByName("spwd");
			final HtmlSubmitInput submitInput = loginForm.getInputByName("submit");
			
			userNameInput.click();
			userNameInput.setText(ramAccount.getLoginName());
			Thread.sleep(new Random().nextInt(2000)); // Random stop within 2 seconds
			passwordInput.click();
			passwordInput.setText(ramAccount.getLoginPassword());
			Thread.sleep(new Random().nextInt(2000)); // Random stop within 2 seconds
			
			page = submitInput.click(); // Click to login and return the page
		}
		
		List<FrameWindow> windows = page.getFrames(); // Get the frame window list
	    final HtmlPage entryMenu = (HtmlPage) windows.get(1).getEnclosedPage(); // 获取第二个frame，也就是rampanel左边栏
	    
	    HtmlAnchor anchor = (HtmlAnchor) entryMenu.getElementsByTagName("a").get(3); // Get the "Answer the survey" link
	    anchor.click(); // Click to "Answer the survey"
	    
	    final HtmlPage mainContent = (HtmlPage) windows.get(2).getEnclosedPage(); // Get the "Answer the survey" page
	    
	    /*---------- Check available survey numbers ----------*/
	    List<HtmlAnchor> anchors = mainContent.getAnchors(); // get "Please answer the surveys" anchor
	    int surveyNumbers = 0; // Available survey numbers
	    
	    ramAccount.setLatestCheckTime(new Date()); // Update the latest check time
	    ramAccountDao.update(ramAccount); // Save to the DB.
	    
	    if (anchors.size() >= 2) {
	    	System.out.println(anchors.get(0).asText());
	    	try {
	    		surveyNumbers = Integer.parseInt(anchors.get(0).asText().trim().substring(0, 1)); // 获取未完成调查数量
	    		System.out.println("---------- There are " + surveyNumbers + " surveys to answer! ----------");
			} catch (NumberFormatException nfe) {
				return ramAccount;
			}
		}
	    /*---------- end ----------*/
	    
	    /*---------- Begin to answer surveys  ----------*/
	    HtmlPage surveyPage = null;
	    if (surveyNumbers > 0) {
	    	HtmlForm htmlForm = mainContent.getFormByName("surveylist"); // 获取表单对象
		    HtmlAnchor answerSurveys = (HtmlAnchor) htmlForm.getElementsByTagName("a").get(0); // 获取Please answer the surveys链接
		    System.out.println(answerSurveys.asText());
		    
		    answerSurveys.click(); // Click to answer surveys
		    surveyPage = (HtmlPage) windows.get(2).getEnclosedPage(); // Get the survey detail page
		    
		    final int maxCycleNums = 50; // The max cycle numbers
		    /*
		     * Set the maximum number of cycles to prevent endless loop
		     */
		    for (int i = 0; i < maxCycleNums; i++) {
		    	// 调查已经完成
				if (surveyPage.getElementsByTagName("a").size() == 0) {
					break;
				}
				HtmlForm panelListSurveyForm = surveyPage.getForms().get(0); // 获取调查表单
				
				// 0. Dealing with ranking buttons
				List<String> rankingOptions = getRankingOptions(panelListSurveyForm);
				boolean isRankingSuccess = false; // Ranking success or not?
				boolean isRankingFinish = false; // Ranking finish or not?
				int rankSign = 0; // Rank sign that indicates which rankingOption we're in
			    for (String rankOption : rankingOptions) {
			    	rankSign++;
			    	System.out.println(rankOption);
					List<HtmlButton> buttons = (List<HtmlButton>) panelListSurveyForm.getByXPath("//button[starts-with(@name,'" + rankOption + "')]");
					
					while (true) {
						HtmlButton randomButton = buttons.get(new Random().nextInt(buttons.size())); // Rank randomly
						randomButton.click();
						Thread.sleep(new Random().nextInt(3000)); // Sleep within 3 seconds after clicking
						DomElement ulElement = randomButton.getNextElementSibling();
						System.out.println(ulElement.asXml());
						List<HtmlElement> liNodes = (List<HtmlElement>) ulElement.getElementsByTagName("li");
						List<HtmlElement> disabledLiNodes = new ArrayList<HtmlElement>();
						for (HtmlElement liNode : liNodes) {
							if (liNode.getAttribute("class").equals("disabled")) {
								disabledLiNodes.add(liNode);
							}
						}
						if (liNodes.size() < 3) { // This option has been ranked before, just continue
							continue ;
						}
						if (disabledLiNodes.size() == 3) { // The ranking process has finished
							break ;
						}
						List<HtmlElement> links = liNodes.get(disabledLiNodes.size()).getElementsByTagName("a");
						HtmlElement link = links.get(0); // There is only one link normally
						System.out.println(link.asXml());
						try {
							surveyPage = link.click();
							Thread.sleep(new Random().nextInt(3000)); // Sleep within 3 seconds after clicking
							isRankingSuccess = true;
							if (disabledLiNodes.size() == 2 && rankingOptions.size() == rankSign) {
								isRankingFinish = true; // Ranking process is finished
							}
							System.out.println(surveyPage.getForms().get(0).asXml());
							break;
						} catch (Exception e) {
							System.out.println("---------- Rank Button Exception  ----------");
							e.printStackTrace();
						}
					}
					if (isRankingSuccess) {
						break;
					}
				}
			    if (isRankingSuccess && !isRankingFinish) { // Not all ranking processes have finished.
					continue;
				}
			    System.out.println(surveyPage.getForms().get(0).asXml());
				
				// 1. Dealing with text inputs
				List<HtmlTextInput> textInputs = (List<HtmlTextInput>) panelListSurveyForm.getByXPath("//input[@type='text']");
				String[] textContents = {"Nope", "None", "Nothing comes to mind now!", "Nothing to share!"};
				for (HtmlTextInput textInput : textInputs) {
					System.out.println("---------- Text Input: " + textInput.asXml() + " ----------");
					
					try {
						textInput.click();
						String textInputName = textInput.getNameAttribute();
						if (textInputName.equals("webFreqDay")) { // times/day
							textInput.setText("");
							textInput.type("1");
						} else if (textInputName.equals("webFreqWeek")) { // times/week
							continue; // just continue
						} else if (textInputName.equals("webFreqMonth")) { // times/month
							continue; // just continue
						} else {
							textInput.type(textContents[new Random().nextInt(textContents.length)]);
						}
					} catch (Exception e) {
						System.out.println("---------- Text Input Click Exception ----------");
					}
					
					Thread.sleep(new Random().nextInt(3000)); // 每次点击随机暂停3秒以内
				}
				
				// 2. Dealing with select options
				List<HtmlSelect> selects = (List<HtmlSelect>) panelListSurveyForm.getByXPath("//select");
				for (HtmlSelect select : selects) {
					System.out.println(select.asXml());
					// Select an option randomly
					select.setSelectedAttribute(select.getOption(new Random().nextInt(select.getOptionSize() - 1)), true);
					
					Thread.sleep(new Random().nextInt(3000)); // 每次点击随机暂停3秒以内
				}
				
				// 3. Dealing with radio inputs
			    List<String> UncheckedRadioOptions = getUncheckedRadioOptions(panelListSurveyForm); // Get the unchecked radio options
			    boolean radioFlag = false;
			    
			    while (UncheckedRadioOptions.size() > 0) {
			    	for (String radioOption : UncheckedRadioOptions) {
						System.out.println(radioOption);
						List<HtmlRadioButtonInput> buttonInputs = panelListSurveyForm.getRadioButtonsByName(radioOption);
						if (radioOption.equals("bPaperRead")) { // Did you read or look at the newspaper?
							buttonInputs.get(0).click(); // Yes
						} else if (radioOption.equals("REG_iAdReadId")) { // Did you notice/read this item or feature when you read the newspaper?
							if (new Random().nextInt(3) == 1) {
								radioFlag = true; // Set the flag to true
								surveyPage = buttonInputs.get(new Random().nextInt(2)).click();
								break;
							} else {
								buttonInputs.get(buttonInputs.size() - 2).click();
							}
						} else if (radioOption.equals("webFreqDay")) { // At present, how often do you visit...
							HtmlInput input =surveyPage.getHtmlElementById(radioOption);
							input.click();
							input.focus();
							input.type(Integer.toString((new Random().nextInt(4))));
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
			    	
			    	if (radioFlag)
			    		break;
			    	
			    	UncheckedRadioOptions = getUncheckedRadioOptions(panelListSurveyForm); // Reverse
				}
			    if (radioFlag)
			    	continue;
				
				// 4. Dealing with checkbox inputs
				List<String> surveyCheckBoxOptions = getUncheckedCheckBoxOptions(panelListSurveyForm);
				// Generate two random numbers
				int[] ranNums = new int[2];
				for (String checkBoxOption : surveyCheckBoxOptions) {
					System.out.println(checkBoxOption);
					List<HtmlInput> checkBoxInputs = panelListSurveyForm.getInputsByName(checkBoxOption);
					if (checkBoxInputs.size() < 3) {
						for (HtmlInput checkBoxInput : checkBoxInputs) {
							if (checkBoxInput.getId().equals("surveyPartId")) { // I rarely (or never) visit the site(The Arizona Republic), just continue.
								continue;
							}
							if (new Random().nextInt(2) == 1) { // 33.3 percents to be checked
								checkBoxInput.setChecked(true);
							}
						}
					} else { 
						// Only check two options randomly.
						ranNums[0] = new Random().nextInt(checkBoxInputs.size() - 2);				
						ranNums[1] = new Random().nextInt(checkBoxInputs.size() - 2);
						for (int j = 0; j < checkBoxInputs.size(); j++) {
							if (ranNums[0] == j || ranNums[1] == j) {
								checkBoxInputs.get(j).setChecked(true);
							}
						}
					}
					
					Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
				}
				
				// 5. Deal with Textareas
				final List<HtmlTextArea> textAreas = (List<HtmlTextArea>)panelListSurveyForm.getByXPath("//textarea");
				for (HtmlTextArea htmlTextArea : textAreas) {
					String[] textareaContents = {"Nope", "None", "Nothing comes to mind now", "Nothing to share!"};
					
					System.out.println("Textarea" + htmlTextArea.asXml());
					htmlTextArea.click();
					htmlTextArea.setText(textareaContents[new Random().nextInt(textareaContents.length)]);
					
					Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
				}
				
				// When was the last time you visited
				try {
					final HtmlTable table = surveyPage.getHtmlElementById("mycal_1");
					if (null != table) {
						final List<HtmlTableCell> cells = (List<HtmlTableCell>) table.getByXPath("//td[contains(concat(' ', normalize-space(@class), ' '), ' selectable ')]");
					    HtmlTableCell cellYestoday = cells.get(cells.size() - 1);
					    cellYestoday.click(); // select
					    
					    Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
					}
				} catch (ElementNotFoundException e) {
					System.out.println("ElementNotFoundException--------------------------------------");
				}
				
				System.out.println(surveyPage.getForms().get(0).asXml());
				List<HtmlElement> nextOrEndBtns = panelListSurveyForm.getElementsByTagName("button");
				if (nextOrEndBtns.size() > 1) {
					System.out.println(nextOrEndBtns.get(nextOrEndBtns.size() - 1).asXml());
					surveyPage = ((HtmlButton)nextOrEndBtns.get(nextOrEndBtns.size() - 1)).click();
				} else {
					surveyPage = ((HtmlButton)nextOrEndBtns.get(0)).click();
				}
				// surveyPage = (HtmlPage) windows.get(2).getEnclosedPage();
			    
				System.out.println(surveyPage.getForms().get(0).asXml());
				System.out.println(surveyPage.getElementsByTagName("a").size());
				
			    Thread.sleep(new Random().nextInt(3000)); // 每提交一页随机暂停3秒以内
			}
		    
		    // get reward points
			anchor = (HtmlAnchor) entryMenu.getElementsByTagName("a").get(4); // 获取"Rewards"链接
			anchor.click();
			HtmlPage rewardPage = (HtmlPage) windows.get(2).getEnclosedPage();
			List<DomElement> list = rewardPage.getElementsByTagName("strong");
			
			// update ramAccount
			ramAccount.setPointsBeforeOpr(ramAccount.getPointsAfterOpr()); // 操作前积分
			ramAccount.setPointsAfterOpr(Integer.parseInt(list.get(0).asText())); // 操作后积分
			ramAccount.setLatestOprTime(new Date());
			ramAccountDao.update(ramAccount); // Save to the DB.
		}
	    
		// 插入积分变更记录
		logAdminRamAccount.setEndTime(new Date());
		logAdminRamAccount.setPoints(ramAccount.getPointsAfterOpr());
		logAdminRamAccountDao.save(logAdminRamAccount);
		
		return ramAccount;
	}
	
	@Override
	public synchronized RamAccount answerSurveyExtend20141015(RamAccount ramAccount)
			throws Exception {
		// 创建积分变更日志
		LogAdminRamAccount logAdminRamAccount = new LogAdminRamAccount();
		logAdminRamAccount.setRamAccount(ramAccount);
		logAdminRamAccount.setBeginTime(new Date()); // 开始时间
		
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setCssEnabled(true);
		webClient.getOptions().setUseInsecureSSL(true); // Use insecure SSL connection(not necessary in this place)
		
		HtmlPage page; // The 'Page' returned when log in successfully.
		if (!StringUtils.isBlank(ramAccount.getLoginUrl())) {
			page = webClient.getPage(ramAccount.getLoginUrl()); // When the loginUrl is not null, get page directly.
		} else {
			final HtmlPage loginPage = webClient.getPage(Constants.RAM_SITE_LOGIN_URL);
			final HtmlForm loginForm = loginPage.getForms().get(1);
			logger.debug("---------- Login Form Details: ----------");
			logger.debug(loginForm.asText());
			logger.debug("---------- Login Form End ----------");
			
			final HtmlTextInput userNameInput = loginForm.getInputByName("suid");
			final HtmlPasswordInput passwordInput = loginForm.getInputByName("spwd");
			final HtmlSubmitInput submitInput = loginForm.getInputByName("submit");
			
			userNameInput.click();
			userNameInput.setText(ramAccount.getLoginName());
			Thread.sleep(new Random().nextInt(2000)); // Random stop within 2 seconds
			passwordInput.click();
			passwordInput.setText(ramAccount.getLoginPassword());
			Thread.sleep(new Random().nextInt(2000)); // Random stop within 2 seconds
			
			page = submitInput.click(); // Click to login and return the page
		}
		
		/* Need to update profile or not? */
		try {
			HtmlForm updateProfileForm = page.getFormByName("panelistregistrationform");
			if (null != updateProfileForm) {
				HtmlInput submitInput = updateProfileForm.getInputByName("SubmitRegistration");
				
				page = submitInput.click();
			}
		} catch (Exception e) {
			logger.debug("---------- No need to update profile! Just continue ----------");
		}
		/* end */
		
	    /*---------- Check available survey numbers ----------*/
		HtmlForm surveyListForm = page.getForms().get(0);
	    List<HtmlElement> anchors = surveyListForm.getElementsByTagName("a"); // get "Please answer the surveys" anchor
	    int surveyNumbers = 0; // Available survey numbers
	    
	    ramAccount.setLatestCheckTime(new Date()); // Update the latest check time
	    ramAccountDao.update(ramAccount); // Save to the DB.
	    
	    if (anchors.size() >= 1) {
	    	List<HtmlElement> ps = surveyListForm.getElementsByTagName("p");
	    	System.out.println(anchors.get(0).asText());
	    	try {
	    		surveyNumbers = Integer.parseInt(ps.get(0).asText().trim().substring(0, 1)); // 获取未完成调查数量
	    		System.out.println("---------- There are " + surveyNumbers + " surveys to answer! ----------");
			} catch (NumberFormatException nfe) {
				return ramAccount;
			}
		}
	    /*---------- end ----------*/
	    
	    /*---------- Begin to answer surveys  ----------*/
	    HtmlPage surveyPage = null;
	    if (surveyNumbers > 0) {
		    HtmlAnchor answerSurveys = (HtmlAnchor) anchors.get(0); // 获取Please answer the surveys链接
		    System.out.println(answerSurveys.asText());
		    
		    surveyPage = answerSurveys.click(); // Click to answer surveys
		    System.out.println(surveyPage.asXml());
		    
		    final int maxCycleNums = 50; // The max cycle numbers
		    /*
		     * Set the maximum number of cycles to prevent endless loop
		     */
		    for (int i = 0; i < maxCycleNums; i++) {
		    	// 调查已经完成
				if (surveyPage.getForms().get(0).getElementsByTagName("a").size() == 0) {
					break;
				}
				HtmlForm panelListSurveyForm = surveyPage.getForms().get(0); // 获取调查表单
				
				// 0. Dealing with ranking buttons
				List<String> rankingOptions = getRankingOptions(panelListSurveyForm);
				boolean isRankingSuccess = false; // Ranking success or not?
				boolean isRankingFinish = false; // Ranking finish or not?
				int rankSign = 0; // Rank sign that indicates which rankingOption we're in
			    for (String rankOption : rankingOptions) {
			    	rankSign++;
			    	System.out.println(rankOption);
					List<HtmlButton> buttons = (List<HtmlButton>) panelListSurveyForm.getByXPath("//button[starts-with(@name,'" + rankOption + "')]");
					
					while (true) {
						HtmlButton randomButton = buttons.get(new Random().nextInt(buttons.size())); // Rank randomly
						randomButton.click();
						Thread.sleep(new Random().nextInt(3000)); // Sleep within 3 seconds after clicking
						DomElement ulElement = randomButton.getNextElementSibling();
						System.out.println(ulElement.asXml());
						List<HtmlElement> liNodes = (List<HtmlElement>) ulElement.getElementsByTagName("li");
						List<HtmlElement> disabledLiNodes = new ArrayList<HtmlElement>();
						for (HtmlElement liNode : liNodes) {
							if (liNode.getAttribute("class").equals("disabled")) {
								disabledLiNodes.add(liNode);
							}
						}
						if (liNodes.size() < 3) { // This option has been ranked before, just continue
							continue ;
						}
						if (disabledLiNodes.size() == 3) { // The ranking process has finished
							break ;
						}
						List<HtmlElement> links = liNodes.get(disabledLiNodes.size()).getElementsByTagName("a");
						HtmlElement link = links.get(0); // There is only one link normally
						System.out.println(link.asXml());
						try {
							surveyPage = link.click();
							Thread.sleep(new Random().nextInt(3000)); // Sleep within 3 seconds after clicking
							isRankingSuccess = true;
							if (disabledLiNodes.size() == 2 && rankingOptions.size() == rankSign) {
								isRankingFinish = true; // Ranking process is finished
							}
							System.out.println(surveyPage.getForms().get(0).asXml());
							break;
						} catch (Exception e) {
							System.out.println("---------- Rank Button Exception  ----------");
							e.printStackTrace();
						}
					}
					if (isRankingSuccess) {
						break;
					}
				}
			    if (isRankingSuccess && !isRankingFinish) { // Not all ranking processes have finished.
					continue;
				}
			    System.out.println(surveyPage.getForms().get(0).asXml());
				
				// 1. Dealing with text inputs
				List<HtmlTextInput> textInputs = (List<HtmlTextInput>) panelListSurveyForm.getByXPath("//input[@type='text']");
				String textContents = Constants.TEXT_CONTENT;
				for (HtmlTextInput textInput : textInputs) {
					logger.debug("---------- Text Input: " + textInput.asXml() + " ----------");
					
					try {
						textInput.click();
						String textInputName = textInput.getNameAttribute();
						if (textInputName.equals("webFreqDay")) { // times/day
							textInput.setText("");
							textInput.type("1");
						} else if (textInputName.equals("webFreqWeek")) { // times/week
							continue; // just continue
						} else if (textInputName.equals("webFreqMonth")) { // times/month
							continue; // just continue
						} else {
							textInput.type(textContents);
						}
					} catch (Exception e) {
						logger.error("---------- Text Input Click Exception ----------");
					}
					
					Thread.sleep(new Random().nextInt(3000)); // 每次点击随机暂停3秒以内
				}
				
				// 2. Dealing with select options
				List<HtmlSelect> selects = (List<HtmlSelect>) panelListSurveyForm.getByXPath("//select");
				for (HtmlSelect select : selects) {
					logger.debug(select.asXml());
					// Select an option randomly
					select.setSelectedAttribute(select.getOption(new Random().nextInt(select.getOptionSize() - 1)), true);
					
					Thread.sleep(new Random().nextInt(3000)); // 每次点击随机暂停3秒以内
				}
				
				// 3. Dealing with radio inputs
			    List<String> UncheckedRadioOptions = getUncheckedRadioOptions(panelListSurveyForm); // Get the unchecked radio options
			    boolean radioFlag = false;
			    
			    while (UncheckedRadioOptions.size() > 0) {
			    	for (String radioOption : UncheckedRadioOptions) {
						System.out.println(radioOption);
						List<HtmlRadioButtonInput> buttonInputs = panelListSurveyForm.getRadioButtonsByName(radioOption);
						if (radioOption.equals("bPaperRead")) { // Did you read or look at the newspaper?
							buttonInputs.get(0).click(); // Yes
						} else if (radioOption.equals("REG_iAdReadId")) { // Did you notice/read this item or feature when you read the newspaper?
							if (new Random().nextInt(3) == 1) {
								radioFlag = true; // Set the flag to true
								surveyPage = buttonInputs.get(new Random().nextInt(2)).click();
								break;
							} else {
								buttonInputs.get(buttonInputs.size() - 2).click();
							}
						} else if (radioOption.equals("webFreqDay")) { // At present, how often do you visit...
							HtmlInput input =surveyPage.getHtmlElementById(radioOption);
							input.click();
							input.focus();
							input.type(Integer.toString((new Random().nextInt(4))));
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
			    	
			    	if (radioFlag)
			    		break;
			    	
			    	UncheckedRadioOptions = getUncheckedRadioOptions(panelListSurveyForm); // Reverse
				}
			    if (radioFlag)
			    	continue;
				
				// 4. Dealing with checkbox inputs
				List<String> surveyCheckBoxOptions = getUncheckedCheckBoxOptions(panelListSurveyForm);
				// Generate one random numbers
				int ranNums;
				for (String checkBoxOption : surveyCheckBoxOptions) {
					logger.debug(checkBoxOption);
					List<HtmlInput> checkBoxInputs = panelListSurveyForm.getInputsByName(checkBoxOption);
					if (checkBoxInputs.size() <= 3) {
						logger.debug("---------- Check Box Inputs Size <= 3. ----------");
						int count = 0;
						for (HtmlInput checkBoxInput : checkBoxInputs) {
							if (count == 0) {
								checkBoxInput.setChecked(true);
								count++;
								continue;
							}
							if (checkBoxInput.getId().equals("surveyPartId")) { // I rarely (or never) visit the site(The Arizona Republic), just continue.
								count++;
								continue;
							}
							if (new Random().nextInt(2) == 1) { // 33.3 percents to be checked
								checkBoxInput.setChecked(true);
							}
						}
					} else { 
						logger.debug("---------- Check Box Inputs Size > 3. ----------");
						// Check one specific option and the other options randomly.
						ranNums = new Random().nextInt(checkBoxInputs.size() - 3);				
						for (int j = 0; j < checkBoxInputs.size(); j++) {
							if (ranNums == j) {
								checkBoxInputs.get(j).setChecked(true);
							} else {
								if (new Random().nextInt(2) == 1) { // 33.3 percents to be checked
									checkBoxInputs.get(j).setChecked(true);
								}
							}
						}
					}
					
					Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
				}
				
				// 5. Deal with Textareas
				final List<HtmlTextArea> textAreas = (List<HtmlTextArea>)panelListSurveyForm.getByXPath("//textarea");
				for (HtmlTextArea htmlTextArea : textAreas) {
					String[] textareaContents = {"Nope", "None", "Nothing comes to mind now", "Nothing to share!"};
					
					System.out.println("Textarea" + htmlTextArea.asXml());
					htmlTextArea.click();
					htmlTextArea.setText(textareaContents[new Random().nextInt(textareaContents.length)]);
					
					Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
				}
				
				// When was the last time you visited
				try {
					final HtmlTable table = surveyPage.getHtmlElementById("mycal_1");
					if (null != table) {
						final List<HtmlTableCell> cells = (List<HtmlTableCell>) table.getByXPath("//td[contains(concat(' ', normalize-space(@class), ' '), ' selectable ')]");
					    HtmlTableCell cellYestoday = cells.get(cells.size() - 1);
					    cellYestoday.click(); // select
					    
					    Thread.sleep(new Random().nextInt(2000)); // 每次点击随机暂停2秒以内
					}
				} catch (ElementNotFoundException e) {
					System.out.println("ElementNotFoundException--------------------------------------");
				}
				
				System.out.println(surveyPage.getForms().get(0).asXml());
				List<HtmlElement> nextOrEndBtns = panelListSurveyForm.getElementsByTagName("button");
				if (nextOrEndBtns.size() > 1) {
					System.out.println(nextOrEndBtns.get(nextOrEndBtns.size() - 1).asXml());
					surveyPage = ((HtmlButton)nextOrEndBtns.get(nextOrEndBtns.size() - 1)).click();
				} else {
					surveyPage = ((HtmlButton)nextOrEndBtns.get(0)).click();
				}
				// surveyPage = (HtmlPage) windows.get(2).getEnclosedPage();
			    
				System.out.println(surveyPage.getForms().get(0).asXml());
				System.out.println(surveyPage.getElementsByTagName("a").size());
				
			    Thread.sleep(new Random().nextInt(3000)); // 每提交一页随机暂停3秒以内
			}
		    
		    // get reward points
		    List<HtmlElement> headerLinks = (List<HtmlElement>) page.getByXPath("//nav[@id='header_navigation']/ul//a");
		    HtmlPage rewardPage = headerLinks.get(1).click();
		    System.out.println(rewardPage.asXml());
		    List<DomElement> list = rewardPage.getElementsByTagName("strong");
		    int currentPoints = Integer.parseInt(list.get(0).asText());
			
			// update ramAccount
			ramAccount.setPointsBeforeOpr(ramAccount.getPointsAfterOpr()); // 操作前积分
			ramAccount.setPointsAfterOpr(currentPoints); // 操作后积分
			ramAccount.setLatestOprTime(new Date());
			ramAccountDao.update(ramAccount); // Save to the DB.
		}
	    
		// 插入积分变更记录
		logAdminRamAccount.setEndTime(new Date());
		logAdminRamAccount.setPoints(ramAccount.getPointsAfterOpr());
		logAdminRamAccountDao.save(logAdminRamAccount);
		
		return ramAccount;
	}
	

	/*------------------------------------Private Methods-------------------------------------*/
	
	// get survey radio optioins
	private Set<String> getSurveyOptions(HtmlForm panelListSurveyForm) {
		Set<String> surveyOptions = new HashSet<String>();
		List<HtmlElement> htmlElements = panelListSurveyForm.getElementsByTagName("input");
	    for (int i = 0; i < htmlElements.size(); i++) {
	    	surveyOptions.add(((HtmlInput)htmlElements.get(i)).getNameAttribute());
		}
	    
		return surveyOptions;
	}
	
	// Get survey radio options
	@SuppressWarnings("unchecked")
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
	@SuppressWarnings("unchecked")
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
	
	// Get ranking options
	@SuppressWarnings("unchecked")
	private List<String> getRankingOptions(HtmlForm form) {
		List<String> rankingOptions = new ArrayList<String>();
		List<HtmlButton> buttons = (List<HtmlButton>) form.getByXPath("//button[contains(@class,'dropdown-toggle')]");
		for (HtmlButton button : buttons) {
			String buttonName = button.getNameAttribute();
			String[] buttonNameArr = buttonName.split("_");
			if (buttonNameArr.length >= 3) 
				buttonName = buttonNameArr[0] + "_" + buttonNameArr[1];
			if (!rankingOptions.contains(buttonName)) {
				rankingOptions.add(buttonName);
			}
		}
		
		return rankingOptions;
	}

}
