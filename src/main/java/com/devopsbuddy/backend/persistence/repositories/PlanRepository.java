package com.devopsbuddy.backend.persistence.repositories;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Carren.Dsouza on 20/04/2017.
 */
public interface PlanRepository extends CrudRepository<Plan,Integer> {
}
