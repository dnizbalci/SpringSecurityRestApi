package com.works.services;

import com.works.entities.FaultWorkOrder;
import com.works.entities.User;
import com.works.repositories.CustomerRepository;
import com.works.repositories.FaultWorkOrderRepository;
import com.works.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class FaultService {

    final FaultWorkOrderRepository fRepo;
    final CustomerRepository cRepo;
    final UserRepository uRepo;
    public FaultService(FaultWorkOrderRepository fRepo, CustomerRepository cRepo, UserRepository uRepo) {
        this.fRepo = fRepo;
        this.cRepo = cRepo;
        this.uRepo = uRepo;
    }


    public ResponseEntity save (FaultWorkOrder faultWorkOrder){
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<User> oUser = uRepo.findByUidEquals(faultWorkOrder.getUid());
        if (oUser.isPresent()) {
            faultWorkOrder.setName(oUser.get().getFirstName());
            hm.put("status", true);
            hm.put("result", fRepo.save(faultWorkOrder));
        }else {
            hm.put("status", false);
            hm.put("message", "User Not Found");

        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list (){
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("result", fRepo.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public  ResponseEntity customerList(@RequestParam int q){
        Map<String, Object> hm = new LinkedHashMap<>();
        if (fRepo.findByUidEquals(q).isPresent()){
            hm.put("status", true);
            hm.put("result",cRepo.customer(q));
        }else {
            hm.put("status", false);
            hm.put("message", "User not found");
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity updateOrder( FaultWorkOrder faultWorkOrder ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<FaultWorkOrder> oUser = fRepo.findByFidEquals(faultWorkOrder.getFid());
        if (oUser.isPresent() ) {
            FaultWorkOrder dbFaultWorkOrder = oUser.get();
            dbFaultWorkOrder.setUid(faultWorkOrder.getUid());
            dbFaultWorkOrder.setName(faultWorkOrder.getName());
            dbFaultWorkOrder.setPlate(faultWorkOrder.getPlate());
            dbFaultWorkOrder.setBrand(faultWorkOrder.getBrand());
            dbFaultWorkOrder.setBrand(faultWorkOrder.getBrand());
            dbFaultWorkOrder.setModel(faultWorkOrder.getModel());
            dbFaultWorkOrder.setKm(faultWorkOrder.getKm());
            dbFaultWorkOrder.setFault(faultWorkOrder.getFault());
            dbFaultWorkOrder.setDetail(faultWorkOrder.getDetail());
            dbFaultWorkOrder.setPrice(faultWorkOrder.getPrice());
            fRepo.saveAndFlush(dbFaultWorkOrder);
            hm.put("status", true);
            hm.put("message", "FaultWorkOrder is uptated");
            hm.put("result", dbFaultWorkOrder);
        }else {
            hm.put("status", false);
            hm.put("message","FaultWorkOrder id is Not Found" );
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }
    public ResponseEntity updateSituation ( Integer fid, String situation ) {
        Map<String, Object> hm = new LinkedHashMap<>();
        Optional<FaultWorkOrder> oUser = fRepo.findByFidEquals(fid);
        if (oUser.isPresent() ) {
            oUser.get().setSituation(situation);
            fRepo.saveAndFlush(oUser.get());
            hm.put("status", true);
            hm.put("message", "Order uptated");
            hm.put("result", oUser.get());
        }else {
            hm.put("status", false);
            hm.put("message","FaultWorkOrder id is Not Found" );
        }
        return new ResponseEntity(hm, HttpStatus.OK);
    }



}
