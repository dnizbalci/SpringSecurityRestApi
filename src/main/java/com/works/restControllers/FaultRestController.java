package com.works.restControllers;

import com.works.entities.FaultWorkOrder;
import com.works.services.FaultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/fault")
public class FaultRestController {

    final FaultService fService;

    public FaultRestController(FaultService fService) {
        this.fService = fService;
    }



    @PostMapping("/save")
    public ResponseEntity save(@Valid @RequestBody FaultWorkOrder faultWorkOrder){
        return fService.save(faultWorkOrder);
    }
    @GetMapping("/list")
    public ResponseEntity list(){
        return fService.list();
    }
    @GetMapping("/customerlist")
    public  ResponseEntity customerList(@RequestParam int q){
        return fService.customerList(q);
    }
    @GetMapping("/updateFaultWorkOrder")
    public  ResponseEntity customerList(@Valid @RequestBody FaultWorkOrder faultWorkOrder){
        return fService.updateOrder(faultWorkOrder);
    }
    @GetMapping("/updateSituation")
    public  ResponseEntity customerList(Integer fid, String situation){
        return fService.updateSituation(fid,situation);
    }

}
