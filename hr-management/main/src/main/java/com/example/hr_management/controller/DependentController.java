package com.example.hr_management.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.hr_management.entity.Dependent;
import com.example.hr_management.service.DependentService;

@RestController
public class DependentController {

    private DependentService dependentService;

    public DependentController(DependentService dependentService) {
        this.dependentService = dependentService;
    }

    @PostMapping("/dependents")
    public ResponseEntity<Dependent> createDependent(@Valid @RequestBody Dependent dependent) {
        Dependent newDependent = dependentService.createDependent(dependent);
        return new ResponseEntity<>(newDependent, HttpStatus.CREATED);
    }

    @GetMapping("/dependents")
    public ResponseEntity<List<Dependent>> getAllDependents() {
        List<Dependent> allDependents = dependentService.getAllDependents();
        return new ResponseEntity<>(allDependents, HttpStatus.OK);
    }

    @GetMapping("/dependents/{id}")
    public ResponseEntity<Dependent> getDependent(@PathVariable int id) {
        Dependent foundDependent = dependentService.getDependent(id);
        return new ResponseEntity<>(foundDependent, HttpStatus.OK);
    }

    @PutMapping("/dependents/{id}")
    public ResponseEntity<Dependent> updateDependent(@PathVariable int id, @Valid @RequestBody Dependent dependent) {
        Dependent updateDependent = dependentService.updateDependent(id, dependent);
        return new ResponseEntity<>(updateDependent, HttpStatus.OK);
    }

    @DeleteMapping("/dependents/{id}")
    public ResponseEntity<HttpStatus> deleteDependent(@PathVariable int id) {
        dependentService.deleteDependent(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    };

}