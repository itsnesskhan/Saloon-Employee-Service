package com.sloon.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sloon.employee.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
