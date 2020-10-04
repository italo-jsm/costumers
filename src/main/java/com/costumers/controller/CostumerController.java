package com.costumers.controller;

import com.costumers.domain.dto.CostumerDto;
import com.costumers.service.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("costumers")
public class CostumerController {

    @Autowired
    private CostumerService costumerService;

    @GetMapping
    public ResponseEntity<?> getAllCostumers(){
        return ResponseEntity.ok(costumerService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCostumer(@PathVariable final Long id){
        return costumerService.getOneCostumer(id)
                .map(costumer -> ResponseEntity.status(HttpStatus.OK).body(costumer))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @GetMapping(path = "/findbyname")
    public ResponseEntity<?> getCostumerByName(@RequestParam("name") final String name){
        return ResponseEntity.status(HttpStatus.OK).body(costumerService.findByName(name));
    }

    @GetMapping(path = "/findbycpf")
    public ResponseEntity<?> getCostumerByCpf(@RequestParam("cpf") final String cpf){
        return ResponseEntity.status(HttpStatus.OK).body(costumerService.findByCpf(cpf));
    }

    @PostMapping
    public ResponseEntity<?> createCostumer(@RequestBody final CostumerDto costumerDto){
        return costumerService.saveNewCostumer(costumerDto.toCostumer())
                .map(savedCostumer -> ResponseEntity.status(HttpStatus.CREATED).body(costumerDto.toCostumer()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PatchMapping("{id}")
    public ResponseEntity<?> partialUpdateCostumer(@PathVariable final Long id, @RequestBody final Map<String, String> changes){
        return costumerService.partialUpdateCostumer(id, changes)
                .map(costumer -> ResponseEntity.status(HttpStatus.OK).body(costumer))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping
    public ResponseEntity<?> fullUpdateCostumer(@RequestBody final CostumerDto costumerDto){
        return costumerService.fullUpdateCostumer(costumerDto.toCostumer())
                .map(savedCostumer -> ResponseEntity.status(HttpStatus.OK).body(costumerDto.toCostumer()))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCostumer(@PathVariable final Long id){
        costumerService.deleteCostumer(id);
        return ResponseEntity.ok().build();
    }
}
