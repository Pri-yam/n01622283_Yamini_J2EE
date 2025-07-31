package com.demo.empMgmtSys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required.")
    @Size(min = 5, max = 20, message = "First name must be between 5 and 20 characters.")
    @Column(nullable = false, length = 20)
    private String firstName;

    @NotBlank(message = "Last name is required.")
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Email is required.")
    @Email(message = "Please enter a valid email address.")
    @Column(nullable = false, unique = true, length = 120)
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
