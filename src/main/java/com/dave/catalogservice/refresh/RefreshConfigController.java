package com.dave.catalogservice.refresh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class RefreshConfigController {

		@Value("${com.dave.user}")
		String user;
		
		@GetMapping("/user")
		public String getUser() {
			return user;
		}
}
