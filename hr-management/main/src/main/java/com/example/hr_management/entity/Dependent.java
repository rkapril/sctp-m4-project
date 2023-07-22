package com.example.hr_management.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "dependent")
public class Dependent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "First name is mandatory.")
    @Size(min = 2, max = 15, message = "First name must be between 2 and 15 characters.")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Last name is mandatory.")
    @Size(min = 2, max = 15, message = "Last name must be between 2 and 15 characters.")
    private String lastName;

    @Column(name = "relationship", nullable = false)
    @NotBlank(message = "Relationship is mandatory.")
    @Size(min = 3, max = 15, message = "Last name must be between 2 and 15 characters.")
    private String relationship;

    // Many dependents can be associated with one employee
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Dependent() {
    }

    public Dependent(int id, String firstName, String lastName, String relationship) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationship = relationship;
    }

    public int getId() {
        return id;
    }

    public void setDependentId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

}
