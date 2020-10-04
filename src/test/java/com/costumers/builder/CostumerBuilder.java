package com.costumers.builder;

import com.costumers.domain.Costumer;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CostumerBuilder {
    public static List<Costumer> buildClientList(){
        return Arrays.asList(
                new Costumer("name1", "cpf1", LocalDate.now()),
                new Costumer("name2", "cpf2", LocalDate.now()),
                new Costumer("name3", "cpf3", LocalDate.now()),
                new Costumer("name4", "cpf4", LocalDate.now())
        );
    }

    public static Costumer buildClient(){
        return new Costumer("name1", "cpf1", LocalDate.of(1987, 12, 3));
    }

    public static Costumer buildClientWithId() {
        final Costumer costumer = new Costumer("name1", "cpf1", LocalDate.now());
        costumer.setId(1L);
        return costumer;
    }

    public static Map<String, String> buildChanges() {
        final Map<String, String> changes = new HashMap<>();
        changes.put("name", "newName");
        changes.put("cpf", "newCpf");
        changes.put("birthDate", "1987-09-23");
        return changes;
    }
}
