package com.devopsbuddy;

import com.devopsbuddy.web.i18n.I18NService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DevopsbuddyApplicationTests {

	@Autowired
	private I18NService i18NService;

	@Test
	public void testMessageByLocaleService() throws Exception{
		String expectedREsult = "Bootstrap starter template";
		String messageid="index.main.callout";
		String actual =i18NService.getMessage(messageid);
		Assert.assertEquals("The Actual and Expected Strings dont match",expectedREsult,actual);

	}

}
