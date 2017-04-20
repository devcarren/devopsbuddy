package com.devopsbuddy.backend.persistence.repositories;

import com.devopsbuddy.backend.persistence.domain.backend.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Carren.Dsouza on 20/04/2017.
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
}
