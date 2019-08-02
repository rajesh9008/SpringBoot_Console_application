package com.college.medicare.domin;

import javax.persistence.*;

@Entity
@Table(name = "DOCTOR")
public class Doctor {

    public Doctor(Long id ,String name, boolean isAvailable) {
        this.name = name;
        this.id = id;
        this.isAvailable = isAvailable;
    }

    public Doctor() {
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_available")
    private boolean isAvailable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
