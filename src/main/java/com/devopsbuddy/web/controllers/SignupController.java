package com.devopsbuddy.web.controllers;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.service.PlanService;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UserUtils;
import com.devopsbuddy.web.domain.frontend.ProAccountBasicPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Carren.Dsouza on 23/04/2017.
 */
@Controller
public class SignupController {
    private static final Logger LOG = LoggerFactory.getLogger(SignupController.class);

    public static  final String SINGUP_URL_MAPPING="/signup";

    public static final String PAYLOAD_MODEL_KEY_NAME="payload";
    public static final String SUBSCRIPTION_VIEW_NAME="registration/signup";

    @Autowired
    private PlanService planService;

    @Autowired
    private UserService userService;

    public SignupController() {
    }

    @RequestMapping(value = SINGUP_URL_MAPPING,method = RequestMethod.GET)
    public String singupGet(@RequestParam("planId") int planId,ModelMap modelMap){

        modelMap.addAttribute(PAYLOAD_MODEL_KEY_NAME,new ProAccountBasicPayload());
        return SUBSCRIPTION_VIEW_NAME;
    }

    @RequestMapping(value = SINGUP_URL_MAPPING,method = RequestMethod.POST)
    public String singupPost(@RequestParam(name="planId", required = true) int planId,
                             @RequestParam(name="file",required = false) MultipartFile file,
                             @ModelAttribute(PAYLOAD_MODEL_KEY_NAME)  ProAccountBasicPayload payload,
                             ModelMap model) throws IOException{
        User user = UserUtils.fromWebUsertoDomain(payload);
        Plan selectedPlan = planService.findPlanById(planId);
        user.setPlan(selectedPlan);

        Set<UserRole> roles = new HashSet<>();
        User registeredUser=null;
        if (planId == PlanEnum.BASIC.getId()){
            roles.add(new UserRole(user,new Role(RolesEnum.Basic)));
            registeredUser = userService.createUser(user,PlanEnum.BASIC,roles);
        } else {
            roles.add(new UserRole(user,new Role(RolesEnum.PRO)));
            registeredUser = userService.createUser(user,PlanEnum.PRO,roles);
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(registeredUser,null,registeredUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        model.addAttribute("message","true");
        return SUBSCRIPTION_VIEW_NAME;
    }



}
