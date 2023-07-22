package com.example.hr_management.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hr_management.entity.Dependent;
import com.example.hr_management.exception.DependentNotFoundException;
import com.example.hr_management.repository.DependentRepository;
import com.example.hr_management.service.DependentService;

@Service
public class DependentServiceImpl implements DependentService {
    @Autowired
    private DependentRepository dependentRepository;

    public DependentServiceImpl(DependentRepository dependentRepository) {
        this.dependentRepository = dependentRepository;
    }

    @Override
    public Dependent createDependent(Dependent dependent) {
        Dependent newDependent = dependentRepository.save(dependent);
        return newDependent;
    }

    @Override
    public Dependent getDependent(int id) {
        Optional<Dependent> wrappedDependentToUpdate = dependentRepository.findById(id);

        if (!wrappedDependentToUpdate.isPresent()) {
            throw new DependentNotFoundException(id);
        }

        Dependent foundDependent = wrappedDependentToUpdate.get();
        return foundDependent;
    }

    @Override
    public List<Dependent> getAllDependents() {
        List<Dependent> allDependents = dependentRepository.findAll();
        return allDependents;
    }

    @Override
    public Dependent updateDependent(int id, Dependent dependent) {
        Optional<Dependent> wrappedDependentToUpdate = dependentRepository.findById(id);

        if (!wrappedDependentToUpdate.isPresent()) {
            throw new DependentNotFoundException(id);
        }

        Dependent dependentToUpdate = wrappedDependentToUpdate.get();
        dependentToUpdate.setFirstName(dependent.getFirstName());
        dependentToUpdate.setLastName(dependent.getLastName());
        dependentToUpdate.setRelationship(dependent.getRelationship());

        return dependentRepository.save(dependentToUpdate);
    }

    @Override
    public void deleteDependent(int id) {
        dependentRepository.deleteById(id);
    }

}
