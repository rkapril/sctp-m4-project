package com.example.hr_management.exception;

public class DependentNotFoundException extends RuntimeException{
    public DependentNotFoundException(int id){
        super("Dependent with id no " + id + " is not found !!!!!!!");
    }
    
}
