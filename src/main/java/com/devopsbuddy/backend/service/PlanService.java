package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.enums.PlanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Carren.Dsouza on 23/04/2017.
 */
@Service
@Transactional(readOnly = true)
public class PlanService {

    @Autowired
    private PlanRepository planRepository;


    public Plan findPlanById(int planId){
        return planRepository.findOne(planId);
    }

    @Transactional
    public Plan createPlan(int planId){
        Plan plan = new Plan();

        if (planId ==1 )
            plan = planRepository.save(new Plan(PlanEnum.BASIC));
        else if (planId == 2){
            plan = planRepository.save(new Plan(PlanEnum.PRO));
        }

        return plan;
    }


}
