package com.lndev.codeshut.repository;

import com.lndev.codeshut.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e from Employee e WHERE e.name LIKE %:keyword%")
    List<Employee> searchEmployeesByKeyword(@Param("keyword") String keyword);
    long countBySalary(Long salary);
    boolean existsByName(String name);

}
