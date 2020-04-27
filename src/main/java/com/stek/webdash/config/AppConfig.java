package com.stek.webdash.config;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.blueconic.browscap.BrowsCapField;
import com.blueconic.browscap.ParseException;
import com.blueconic.browscap.UserAgentParser;
import com.blueconic.browscap.UserAgentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class AppConfig {

	private final BrowsCapField[] userAgentFieldsForInspection = { BrowsCapField.BROWSER, BrowsCapField.BROWSER_TYPE, BrowsCapField.BROWSER_MAJOR_VERSION, BrowsCapField.DEVICE_TYPE,
			BrowsCapField.PLATFORM, BrowsCapField.PLATFORM_VERSION };

	@Bean
	public UserAgentParser userAgentParser() throws IOException, ParseException {
		return new UserAgentService().loadParser(Arrays.asList(userAgentFieldsForInspection));
	}

}
