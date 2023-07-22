package com.example.hr_management.service;

import java.util.List;

import com.example.hr_management.entity.Dependent;

public interface DependentService {
    Dependent createDependent(Dependent dependent);

    public Dependent getDependent(int id);

    public List<Dependent> getAllDependents();

    public Dependent updateDependent(int id, Dependent dependent);

    public void deleteDependent(int id);

}