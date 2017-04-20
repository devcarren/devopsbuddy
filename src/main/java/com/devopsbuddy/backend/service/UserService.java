package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


/**
 * Created by Carren.Dsouza on 20/04/2017.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public User createUser(User user, PlanEnum plansEnum, Set<UserRole> userRoles){
        Plan plan = new Plan(plansEnum);
        if (!planRepository.exists(plansEnum.getId())){
            plan = planRepository.save(plan);
        }
        user.setPlan(plan);

        for (UserRole ur : userRoles){
            roleRepository.save(ur.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        user = userRepository.save(user);

        return user;

    }
}
