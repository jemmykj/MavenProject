package mavenProject.MessengerTestScript;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import mavenProject.MessengerAutomation.BaseClass;

public class MainMessenger extends BaseClass {

	Dimension size;

	public void defaultHome() throws Exception {

		getDriver().findElementById("com.facebook.orca:id/recents_tab").click();
	}

	public void homeTab() throws Exception {
		//getDriver().findElementById("com.facebook.orca:id/home_nested_tab").click();
	}

	public void verticalScrolling(int n) throws Exception {
		try {
			size = getDriver().manage().window().getSize();
			int start_y = (int) (size.getHeight() * 0.4);
			int end_y = (int) (size.getHeight() * 0.2);
			int x = (int) (size.getWidth() * 0.2);
			for (int i = 0; i < n; i++)
				getDriver().swipe(x, start_y, x, end_y, 800);

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	@Test
	public void loginScreen() throws Exception {
		Thread.sleep(4000);
		System.out.println(" driver " + getDriver());
		WebElement log= getDriver().findElement(By.id("com.facebook.orca:id/login"));
		if (log==null)
		{
			System.out.println("null");
		}
		Boolean isLogin = getDriver().findElements(By.id("com.facebook.orca:id/login")).size() != 0;
		Boolean isLoginGroup = getDriver().findElements(By.id("com.facebook.orca:id/login_group")).size() > 0;
		
		/*if
		 (driver.findElement(By.id("com.facebook.orca:id/login")).isDisplayed()) {*/
		 
		if (isLogin) {
			System.out.println("isLogin");
			getDriver().findElement(By.id("com.facebook.orca:id/login")).click();
			logIn();
		} else if (isLoginGroup) {
			System.out.println("isLoginGroup");
			getDriver().findElement(By.id("com.facebook.orca:id/login_group")).click();
			System.out.println("Please log in ");
			Thread.sleep(2000);
		} else {
			System.out.println("USER ALREADY LOGGED IN");
			
			defaultHome();
		}
	}

	public void logIn() throws Exception {
		if (getDriver().findElement(By.id("com.facebook.orca:id/skip")).isDisplayed()) {
			getDriver().findElement(By.id("com.facebook.orca:id/skip")).click();
			getDriver().findElement(By.id("com.facebook.orca:id/skip_step")).click();
			getDriver().findElement(By.id("com.facebook.orca:id/button2")).click();
			defaultHome();
			Thread.sleep(3000);
		}

	}

	@Test
	public void verifyInbox() throws Exception {
		System.out.println("VERIFYING INBOX");
		homeTab();
		verticalScrolling(15);

	}

	@Test
	public void openMessages() throws Exception {
		Thread.sleep(2000);
		System.out.println("OPEN ANY MESSAGE");
		// driver.findElementByAccessibilityId("Selected, Home, Tab 1 of
		// 4").click();
		homeTab();
		defaultHome();
		Thread.sleep(3000);
		// WebElement parentElement =
		// driver.findElementById("com.facebook.orca:id/thread_list_recycler_view");
		// List<WebElement> childElements =
		// parentElement.findElements(By.className("android.view.View"));
		List<WebElement> childElements = getDriver().findElements(By.className("android.view.View"));
		int childElementsSize = childElements.size();
		System.out.println("childElementsSize " + childElementsSize);
		for (int i = 1; i < childElementsSize; i++) {

			if (i % 4 == 0) {
				clickMessage(i, childElements);

			}

		}
		verticalScrolling(3);

	}

	public void clickMessage(int i, List<WebElement> childElements) throws Exception {
		// System.out.println("first " + i + childElements.size());

		childElements.get(i).click();

		Boolean isBack = getDriver().findElements(By.id("com.facebook.orca:id/quicksilver_back_button")).size() > 0;
		Boolean isUp = getDriver().findElements(By.id("com.facebook.orca:id/up")).size() > 0;
		if (isBack) {
			getDriver().findElement(By.id("com.facebook.orca:id/quicksilver_back_button")).click();
			// System.out.println(driver.findElement(By.id("com.facebook.orca:id/up")).getClass());
			Thread.sleep(2000);
		} else if (isUp) {
			getDriver().findElement(By.id("com.facebook.orca:id/up")).click();
		}
		/*
		 * if (driver.findElement(By.id("com.facebook.orca:id/up")).isDisplayed
		 * ()) {System.out.println("disp");
		 * driver.findElement(By.id("com.facebook.orca:id/up")).click(); } else
		 * driver.findElementById(
		 * "com.facebook.orca:id/quicksilver_back_button" ).click();
		 */

	}
	
	//@Test
	public void tabActive() throws Exception {
		homeTab();
		System.out.println("VERIFYING ACTIVE TAB");
		defaultHome();
		// Thread.sleep(5000);
		getDriver().findElementById("com.facebook.orca:id/active_now_tab").click();
		Thread.sleep(1000);
		List<WebElement> activeElementsList = getDriver().findElements(By.className("android.view.View"));
		// List<WebElement> childElements =
		// parentElement.findElements(By.className("android.support.v7.widget.RecyclerView"));
		// int activeElementsList = parentElement.size();
		System.out.println("size " + activeElementsList.size());
		// System.out.println(childElements.get(0).getText());
		// driver.findElementById("com.facebook.orca:id/empty_contacts_invite_friends_upsell_layout").getTagName();
		// driver.findElementById("com.facebook.orca:id/empty_contacts_invite_friends_upsell_layout").getText();

		Boolean isActiveList= getDriver().findElements(By.id("com.facebook.orca:id/empty_contacts_title_for_ig_upsell")).size()>0;
		//if (driver.findElementById("com.facebook.orca:id/empty_contacts_title_for_ig_upsell").isDisplayed()) {
		if (isActiveList){
			System.out.println("NO CONTACTS FOUND");
		} else {
			System.out.println("in");
			verticalScrolling(2);
			// driver.findElement(By.xpath("//android.view.View[@text='Jemmy
			// Johnson']")).click();

		}

		Thread.sleep(2000);
		// .findElement(By.id("com.facebook.orca:id/up")).click();

	}

	//@Test
	public void tabGroups() throws Exception {

		System.out.println("VERIFYING GROUP TABS");
		defaultHome();
		Thread.sleep(3000);
		getDriver().findElementById("com.facebook.orca:id/groups_tab").click();
		Thread.sleep(1000);

		List<WebElement> groupsList = getDriver().findElements(By.className("android.widget.RelativeLayout"));
		for (WebElement gList : groupsList) {

			gList.click();
			Thread.sleep(1000);
			getDriver().findElementById("com.facebook.orca:id/up").click();
			verticalScrolling(2);

		}

	}

	//@Test
	public void tabCalls() throws Exception {
		System.out.println("VERIFYING CALL TABS");
		homeTab();
		//defaultHome();
		
		Thread.sleep(2000);
		getDriver().findElementById("com.facebook.orca:id/call_tab").click();
		Thread.sleep(1000);
		verticalScrolling(3);
		homeTab();
	}

	//@Test
	public void composeMessage() throws Exception {
		Thread.sleep(5000);
		homeTab();
		getDriver().findElementByAccessibilityId("Write Message").click();
		getDriver().findElement(By
				.xpath("//android.widget.EditText[@resource-id='com.facebook.orca:id/contact_picker_autocomplete_input']"))
				.sendKeys("jentest mal");

		getDriver().findElement(By
				.xpath("//android.widget.RelativeLayout[@resource-id='com.facebook.orca:id/contact_picker_list_item']"))
				.click();
		// if
		// (driver.findElement(By.id("com.facebook.orca:id/message_requests_accept_button")).isDisplayed())
		Boolean isAccept = getDriver().findElements(By.id("com.facebook.orca:id/message_requests_accept_button")).size() > 0;
		if (isAccept) {
			getDriver().findElement(By.id("com.facebook.orca:id/message_requests_accept_button")).click();
		}
		getDriver().findElement(By.xpath("//android.widget.EditText[@resource-id='com.facebook.orca:id/text_input_bar']"))
				.click();
		getDriver().findElement(By.xpath("//android.widget.EditText[@resource-id='com.facebook.orca:id/text_input_bar']"))
				.sendKeys("Auto");
		getDriver().findElement(
				By.xpath("//android.widget.ImageView[@resource-id='com.facebook.orca:id/composer_send_action_button']"))
				.click();
		getDriver().findElement(By.id("com.facebook.orca:id/up")).click();
		// driver.findElementByAccessibilityId("Navigate Up").click();

	}

	// @Test
	public void peopleTab() throws Exception {

		defaultHome();
		Thread.sleep(2000);
		getDriver().findElementById("com.facebook.orca:id/people_tab").click();
		System.out.println("PEOPLE TAB");
		Thread.sleep(1000);

		List<WebElement> peopleList = getDriver().findElements(By.className("android.widget.ListView"));
		// ?? why does it click the element not in class RelativeLayout
		for (WebElement pList : peopleList) {
			pList.click();
			Thread.sleep(1000);
			getDriver().findElementById("com.facebook.orca:id/up").click();
			verticalScrolling(3);
		}
		homeTab();
	}

	// @Test
	public void gamesTab() throws Exception {
		defaultHome();
		Thread.sleep(2000);
		System.out.println("GAMES TAB");
		getDriver().findElementById("com.facebook.orca:id/games_tab").click();
		Thread.sleep(1000);

		List<WebElement> gameList = getDriver().findElements(By.className("android.widget.LinearLayout"));
		// ?? why does it click the element not in class RelativeLayout
		/*
		 * for (WebElement pList:gameList) {System.out.println("ho" +
		 * pList.getTagName()); verticalScrolling(2); pList.click();
		 * Thread.sleep(1000);
		 * driver.findElementById("com.facebook.orca:id/quicksilver_back_button"
		 * ).click();
		 * 
		 * }
		 */
		//System.out.println("game list size " + gameList.size());
		for (int i = 1; i < gameList.size(); i++) {
			System.out.println("size " + gameList.size() + " i " + i);

			//System.out.println("i inside loop");
			if (i % 3 == 0) {//System.out.println("in");
				clickMessage(i, gameList);
			}
			Thread.sleep(3000);
			//System.out.println("after click ");

		}
		verticalScrolling(3);
	}

	//@Test
	public void discoverTab() throws Exception {
		defaultHome();

		getDriver().findElement(By.xpath("//android.widget.ImageView[@index='4']")).click();
		// driver.findElement(By.xpath("//android.widget.ImageView[5]")).click();
		Thread.sleep(3000);

		List<WebElement> discoverLists = getDriver().findElements(By.xpath("//android.view.View"));
		verticalScrolling(3);
		Thread.sleep(2000);
		/*
		 * for (WebElement ldiscover : discoverLists) { //
		 * driver.findElement(By.xpath("//android.view.View[@index='5']")).click
		 * ();
		 * 
		 * driver.findElement(By.id(
		 * "com.facebook.orca:id/platform_landing_page_back_button")).click(); }
		 */
		System.out.println(" Size discover " + discoverLists.size());
		for (int i = 7; i < discoverLists.size(); i++) {

			if (i == 9) {

				// String str= discoverLists.get(i).getText();

				discoverLists.get(i).click();
				Thread.sleep(2000);
			}
		}

		/*
		 * for (int i = 0; i < discoverLists.size(); i++) {
		 * 
		 * //driver.findElement(By.xpath("//android.view.View")).click();
		 * //driver.findElement(By.xpath(
		 * "//android.support.v7.widget.RecyclerView[@index='0']/android.widget.Button[@index='5']"
		 * )).click(); }
		 */

	}


}
