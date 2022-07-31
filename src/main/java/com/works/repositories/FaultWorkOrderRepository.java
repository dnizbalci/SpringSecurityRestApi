package com.works.repositories;

import com.works.entities.FaultWorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FaultWorkOrderRepository extends JpaRepository<FaultWorkOrder, Integer> {
    Optional<FaultWorkOrder> findByUidEquals(Integer uid);

    Optional<FaultWorkOrder> findByFidEquals(Integer fid);



}