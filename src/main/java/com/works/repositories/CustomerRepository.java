package com.works.repositories;

import com.works.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {



    @Query(value = "SELECT*FROM fault_work_order as f INNER JOIN user as u ON u.uid = f.uid WHERE u.uid = ?1 ORDER BY f.km", nativeQuery = true)
    List<Customer> customer(int q);
}