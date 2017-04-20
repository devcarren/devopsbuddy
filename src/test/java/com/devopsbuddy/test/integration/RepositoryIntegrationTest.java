package com.devopsbuddy.test.integration;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RolesEnum;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Carren.Dsouza on 20/04/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryIntegrationTest {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception{
        Plan basic =createBasicPlan(PlanEnum.BASIC);
        planRepository.save(basic);
        Plan retrievePlan = planRepository.findOne(1);
        Assert.assertNotNull(retrievePlan);
    }

    private Plan createBasicPlan(PlanEnum planEnum) {

        return new Plan(PlanEnum.BASIC);
    }

    @Test
    public void testCreateNewRole() throws Exception{
        Role basic =createBasicRole(RolesEnum.Basic);
        roleRepository.save(basic);
        Role retrieveRole = roleRepository.findOne(RolesEnum.Basic.getId());
        Assert.assertNotNull(retrieveRole);
    }

    private Role createBasicRole(RolesEnum rolesEnum) {

        return new Role(rolesEnum);
    }

    @Test
    public void testCreateUser() throws  Exception{
        Plan basicPlan = createBasicPlan(PlanEnum.BASIC);
        planRepository.save(basicPlan);

        User basicUser = createBasicUser();
        basicUser.setPlan(basicPlan);

        Role basicRole = createBasicRole(RolesEnum.Basic);
        Set<UserRole> userRoles = new HashSet<>();
        UserRole userRole = new UserRole();
        userRole.setUser(basicUser);
        userRole.setRole(basicRole);
        userRoles.add(userRole);
        basicUser.getUserRoles().addAll(userRoles);

        for (UserRole usrRole: userRoles){
            roleRepository.save(usrRole.getRole());
        }

        basicUser = userRepository.save(basicUser);
        User newUser = userRepository.findOne(basicUser.getId());
    }

    private User createBasicUser() {
        User user = new User();
        user.setFirstName("Carren");
        user.setLastName("Dsdouza");
        user.setCountry("India");
        user.setDescription(" IN india");
        user.setEmail(" Hello@y.com");
        user.setEnabled(true);
        user.setId(1);
        user.setPassword("Login345");
        user.setPhoneNumber("2323232");
        user.setProfileImageUrl("http://djx.ddd/ddd.jpg");
        user.setStripeCustomerId("234234234");
        user.setUsername("carren");

        return user;
    }
}
