package com.sloon.employee.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sloon.employee.models.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmail(String email);
}
