package com.fullstack.angular.repository;

import com.fullstack.angular.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;




@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
