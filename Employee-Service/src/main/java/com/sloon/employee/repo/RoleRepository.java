package com.sloon.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sloon.employee.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {

}
