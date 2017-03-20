package se.groupfish.springcasemanagement.auditing;

import org.springframework.data.domain.AuditorAware;

public class CustomAuditorAware implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {

		String userName = "";
		userName = System.getProperty("user.name");
		return userName;
	}

}
