package com.devopsbuddy;

import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.service.PlanService;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DevopsbuddyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DevopsbuddyApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Value("${webmaster.username}")
	private String webmasterUserName;

	@Value("${webmaster.password}")
	private String webmasterPassword;

	@Value("${webmaster.email}")
	private String webmasterEmail;

	@Autowired
	private PlanService planService;


	@Override
	public void run(String... strings) throws Exception {

		planService.createPlan(PlanEnum.BASIC.getId());
		planService.createPlan(PlanEnum.PRO.getId());

		Set<UserRole> userRoles = new HashSet<UserRole>();
		User basicUser = UserUtils.createBasicUser();
		basicUser.setPassword(webmasterPassword);
		userRoles.add(new UserRole(basicUser,new Role(RolesEnum.ADMIN)));

		User user = userService.createUser(basicUser, PlanEnum.BASIC, userRoles);
	}
}
