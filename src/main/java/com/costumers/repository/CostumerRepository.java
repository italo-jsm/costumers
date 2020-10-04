package com.costumers.repository;

import com.costumers.domain.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {

    public Optional<Costumer> findByCpf(String cpf);
    public List<Costumer> findByName(String name);

}
