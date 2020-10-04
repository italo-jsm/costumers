package com.costumers.domain.dto;

import com.costumers.domain.Costumer;

import java.time.LocalDate;

public class CostumerDto {
    private Long id;
    private String name;
    private String cpf;
    private LocalDate birthDate;

    public Costumer toCostumer(){
        final Costumer c = new Costumer(name, cpf, birthDate);
        c.setId(id);
        return c;
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
