package com.tenjishen.action.extend;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.javascript.host.html.HTMLDivElement;

public class RampanelActionTest {
	
	@Test
	@Ignore
	public void homePage() throws Exception {
	    final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	    Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

	    final String pageAsXml = page.asXml();
	    Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));

	    final String pageAsText = page.asText();
	    Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));

	    webClient.closeAllWindows();
	}
	
	@Test
	@Ignore
	public void homePage_Firefox() throws Exception {
	    final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
	    final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
	    Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());

	    webClient.closeAllWindows();
	}
	
	@Test
	@Ignore
	public void getElements() throws Exception {
	    final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
//	    final HtmlDivision div = page.getHtmlElementById("contentBox");
//	    final HtmlAnchor anchor = page.getAnchorByName("contentBox");

	    System.out.println(page.asText());
	    webClient.closeAllWindows();
	}
	
	@Test
	@Ignore
	public void rampanel() throws Exception {
		// 创建浏览器，可以选择IE, Firefox, Chrome等等
	    final WebClient webClient = new WebClient();
	    // 获取URL页面
	    final HtmlPage page = webClient.getPage("https://www.rampanel.com/ram/RAM?lng=10&uid=GAG4GCG3G6ECENG4ECE5E2&pwd=G4G3E74545G4AKAN3H4CG63KCAEG3EE2ENG2EGCG3341CCC14G43EGC8");
	    
	    final List<FrameWindow> windows = page.getFrames(); // 获取frame列表
	    final HtmlPage entryMenu = (HtmlPage) windows.get(1).getEnclosedPage(); // 获取第二个frame，也就是rampanel左边栏
	    // final HtmlPage mainContent = (HtmlPage) windows.get(2).getEnclosedPage(); // 获取第三个frame，也就是rampanel内容
	    
	    HtmlAnchor anchor = (HtmlAnchor) entryMenu.getElementsByTagName("a").get(5); // 获取Anchor链接
	    final HtmlPage mainContent = anchor.click(); // 模拟点击事件并获取点击后的页面
	    
	    HtmlInput input = mainContent.getElementByName("sStreetAdress1"); // 根据元素名称查找元素
	    input.setValueAttribute(""); // 清空input选项
	    input.click(); // 模拟点击事件
	    input.type("Washington"); // 模拟输入事件
	    
	    // 获取按钮，注意这里是Bootstrap的Input按钮，实际上是Input元素
	    HtmlInput updateBtn = (HtmlInput)mainContent.getElementByName("SubmitRegistration");  
	    // 点击并获得返回结果  
	    // Page resultPage = updateBtn.click(); 
	    updateBtn.click();
	    
	    // System.out.println(mainContent.asText()); // 将返回的页面以字符串形式输出到Console
	    
	    webClient.closeAllWindows();
	}
	
	@Test
	@Ignore
	public void answerSurveys() throws Exception {
	    final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage("https://www.rampanel.com/ram/RAM?lng=10&uid=GAG4GCG3G6ECENG4ECE5E5&pwd=3C3A3LGH32354G463435E2G5G64AEGE8CEE43GGHE24AEG3KCCE5EGC8");
	    
	    final List<FrameWindow> windows = page.getFrames(); // 获取frame列表
	    final HtmlPage entryMenu = (HtmlPage) windows.get(1).getEnclosedPage(); // 获取第二个frame，也就是rampanel左边栏
	    
	    HtmlAnchor anchor = (HtmlAnchor) entryMenu.getElementsByTagName("a").get(3); // 获取Anchor链接
	    final HtmlPage mainContent = anchor.click(); // 获取Answer the survey页面
	    
	    HtmlForm htmlForm = mainContent.getFormByName("surveylist"); // 获取表单对象
	    HtmlAnchor answerSurveys = (HtmlAnchor) htmlForm.getElementsByTagName("a").get(0); // 获取Please answer the surveys链接
	    
	    int surveyNumbers = 0;
	    HtmlPage surveyPage = null;
	    if (answerSurveys != null) {
	    	surveyNumbers = Integer.parseInt(answerSurveys.asText().trim().substring(0, 1)); // 获取未完成调查数量
	    	surveyPage = answerSurveys.click(); // Click to answer surveys
		}
	    System.out.println(surveyPage.asText());
	    List<HtmlRadioButtonInput> list;
	    // 遍历调查
	    for (int i = 0; i < surveyNumbers; i++) {
	    	HtmlForm panelListSurveyForm = surveyPage.getFormByName("panelistsurveyform"); // 获取调查表单
	    	if (0 == i) {
	    		// 第一部分
	    		list = panelListSurveyForm.getRadioButtonsByName("bPaperRead"); // Did you read or look at the newspaper
	    		list.get(0).click(); // Choose Yes
	    	}
	    	list = panelListSurveyForm.getRadioButtonsByName("REG_iAdReadId"); // Did you see/read this ad when you read the newspaper?Full page ad: 
	    	list.get(2).click(); // Saw the ad
	    	
	    	list = panelListSurveyForm.getRadioButtonsByName("iReadingTime"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
	    	
	    	list = panelListSurveyForm.getRadioButtonsByName("iPaperReadQuant"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
	    	
	    	list = panelListSurveyForm.getRadioButtonsByName("iAdNoticeGrade"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
	    	
	    	list = panelListSurveyForm.getRadioButtonsByName("REG_iOverallGradeId"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
	    	
	    	list = panelListSurveyForm.getRadioButtonsByName("REG_iAdSenderGradeId"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
	    	
	    	list = panelListSurveyForm.getRadioButtonsByName("iAdvKnowGrade"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
	    	
	    	list = panelListSurveyForm.getRadioButtonsByName("bUseInAd"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
	    	
	    	list = panelListSurveyForm.getRadioButtonsByName("bSeekInfo"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
	    	
/*	    	list = panelListSurveyForm.getRadioButtonsByName("bVisit"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
	    	
	    	list = panelListSurveyForm.getRadioButtonsByName("bBuy"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
	    	
	    	list = panelListSurveyForm.getRadioButtonsByName("bVisitWeb"); // Approximately how much time did you spend reading the newspaper?
	    	list.get(new Random().nextInt(list.size() - 1)).click(); // random check
*/	    	
		    HtmlButton nextBtn = (HtmlButton)panelListSurveyForm.getElementsByTagName("button").get(0);
		    HtmlPage resultPage = nextBtn.click();
		    System.out.println(resultPage.asText());
		}
	    
	    // System.out.println(mainContent.asText());

	    webClient.closeAllWindows();
	}
	
	@Test
	@Ignore
	public void getRadioOptions() throws Exception {
		final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage("https://www.rampanel.com/ram/RAM?lng=10&uid=GAG4GCG3G6ECENG4ECE5E5&pwd=3C3A3LGH32354G463435E2G5G64AEGE8CEE43GGHE24AEG3KCCE5EGC8");
	    
	    final List<FrameWindow> windows = page.getFrames(); // 获取frame列表
	    final HtmlPage entryMenu = (HtmlPage) windows.get(1).getEnclosedPage(); // 获取第二个frame，也就是rampanel左边栏
	    
	    HtmlAnchor anchor = (HtmlAnchor) entryMenu.getElementsByTagName("a").get(3); // 获取Anchor链接
	    final HtmlPage mainContent = anchor.click(); // 获取Answer the survey页面
	    
	    HtmlForm htmlForm = mainContent.getFormByName("surveylist"); // 获取表单对象
	    HtmlAnchor answerSurveys = (HtmlAnchor) htmlForm.getElementsByTagName("a").get(0); // 获取Please answer the surveys链接
	    HtmlPage surveyPage = answerSurveys.click(); // Click to answer surveys
	    
	    HtmlForm panelListSurveyForm = surveyPage.getFormByName("panelistsurveyform"); // 获取调查表单
	    
	    System.out.println(panelListSurveyForm.asText());
	    List<HtmlElement> htmlElements = panelListSurveyForm.getElementsByTagName("a");
	    for (int i = 0; i < htmlElements.size(); i++) {
			System.out.println(((HtmlAnchor)htmlElements.get(i)).getNameAttribute());
		}
	}
	
	@Test
	@Ignore
	public void getRewardPoints() throws Exception {
		final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage("https://www.rampanel.com/ram/RAM?lng=10&uid=GAG4GCG3G6ECENG4ECE5&pwd=GACA3NGCENE8453HG4GE3A38EA4EEGE2E8C13638C6E7CAE3CG4G42C8");
	    
	    final List<FrameWindow> windows = page.getFrames(); // 获取frame列表
	    final HtmlPage entryMenu = (HtmlPage) windows.get(1).getEnclosedPage(); // 获取第二个frame，也就是rampanel左边栏
	    entryMenu.getAnchors().get(4).click(); // 点击Reward链接
	    HtmlPage rewardPage = (HtmlPage) windows.get(2).getEnclosedPage();
	    
	    List<DomElement> list = rewardPage.getElementsByTagName("strong");
	    System.out.println(list.get(0).asText());
	    
	}
	
	@Test
	@Ignore
	public void redeemPoints() throws Exception {
	    
	    final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
	    final HtmlPage page =                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   webClient.getPage("https://www.rampanel.com/ram/RAM?lng=10&uid=GAG4GCG3G6ECENG4ECE5E5CC&pwd=44E64H3H343GE8E24342E244G4454E433AGG4A4G38GE3E424H4HGGC8");
	    
	    List<FrameWindow> windows = page.getFrames(); // 获取frame列表
	    final HtmlPage entryMenu = (HtmlPage) windows.get(1).getEnclosedPage(); // 获取第二个frame，也就是rampanel左边栏
	    
	    HtmlAnchor anchor = (HtmlAnchor) entryMenu.getElementsByTagName("a").get(3); // 获取"Answer the survey"链接
	    anchor.click(); // 点击"Answer the survey"链接
	    
	    final HtmlPage mainContent = (HtmlPage) windows.get(2).getEnclosedPage(); // 获取"Answer the survey"页面
	    
		HtmlForm htmlForm = mainContent.getFormByName("surveylist"); // 获取表单对象
	    HtmlAnchor answerSurveys = (HtmlAnchor) htmlForm.getElementsByTagName("a").get(0); // 获取Please answer the surveys链接
	    System.out.println(answerSurveys.asText());
	    
	    answerSurveys.click(); // Click to answer surveys
	    HtmlPage surveyPage = (HtmlPage) windows.get(2).getEnclosedPage();
	    HtmlForm panelListSurveyForm = surveyPage.getForms().get(0); // 获取调查表单
	    System.out.println(panelListSurveyForm.asText());
	    
	    final HtmlTable table = surveyPage.getHtmlElementById("mycal_1"); // 获取表格
	    
	    // Get tds that are selectable(contain 'selectable' class)
	    final List<HtmlTableCell> cells = (List<HtmlTableCell>) table.getByXPath("//td[contains(concat(' ', normalize-space(@class), ' '), ' selectable ')]");
	    HtmlTableCell cellYestoday = cells.get(cells.size() - 1);
	    cellYestoday.click(); // select
	    
	    System.out.println(cellYestoday.asXml());
	    
	}
	
	@Test
	public void buttonTest() throws Exception {
		final WebClient webClient = new WebClient();
	    final HtmlPage page = webClient.getPage("file:///C:/Users/Administrator/Desktop/RAM.htm");
	    
	    List<FrameWindow> windows = page.getFrames(); // 获取frame列表
	    final HtmlPage entryMenu = (HtmlPage) windows.get(1).getEnclosedPage(); // 获取第二个frame，也就是rampanel左边栏
	    HtmlPage surveyPage = (HtmlPage) windows.get(2).getEnclosedPage();
	    
	    HtmlForm form = surveyPage.getForms().get(0); // Get survey form
	    
	    // Dealing with ranking button
	    List<String> rankingOptions = getRankingOptions(form);
	    for (String rankOption : rankingOptions) {
	    	System.out.println(rankOption);
			List<HtmlButton> buttons = (List<HtmlButton>) form.getByXPath("//button[starts-with(@name,'" + rankOption + "')]");
			
			int rankNum = 0;
			// Only rank the top 3 options
			while (rankNum < 3) {
				HtmlButton randomButton = buttons.get(new Random().nextInt(buttons.size())); // Rank randomly
				randomButton.click();
				DomElement ulElement = randomButton.getNextElementSibling();
				System.out.println(ulElement.asXml());
				List<HtmlElement> liNodes = ulElement.getElementsByTagName("li");
				if (liNodes.size() < 3) { // This option has been ranked before, just continue
					continue ;
				}
				List<HtmlElement> links = liNodes.get(rankNum).getElementsByTagName("a");
				HtmlElement link = links.get(0); // There is only one link normally
				System.out.println(link.asXml());
				// link.click();
				rankNum++;
				
				if (0 == webClient.waitForBackgroundJavaScript(60 * 1000)) { // Wait 60 seconds for background JS to execute
					// System.out.println(surveyPage.asXml());
				}
			}
			
		}
	    
	   /* for (HtmlButton button : buttons) {
			System.out.println(button.asXml());
		}*/
	    /*
	    DomNode parentDiv = buttons.get(0).getParentNode();
	    System.out.println(parentDiv.asXml());
	    buttons.get(0).click();
	    System.out.println(parentDiv.asXml());
	    System.out.println(buttons.size());
	    */
	}
	
	public List<String> getRankingOptions(HtmlForm form) {
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
	
	public static void main(String[] args) throws Exception {
		String url = "https://www.rampanel.com/ram/RAM?choice=panreg";
		final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_17);
		final HtmlPage page = webClient.getPage(url);
		
		List<FrameWindow> windows = page.getFrames(); // 获取frame列表
		HtmlPage regPage = (HtmlPage) windows.get(2).getEnclosedPage(); // Register Page is inside an 'iframe'
		
		List<HtmlSelect> selects = (List<HtmlSelect>) regPage.getByXPath("//select[@name='Choose_NPCI19']");
		HtmlSelect select = selects.get(0);
		
		select.click();
		HtmlPage testPage = select.setSelectedAttribute("37847", true);
		
		System.out.println(testPage.asXml());
		
		
	}
	
	
}
