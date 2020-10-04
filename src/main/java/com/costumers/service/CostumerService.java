package com.costumers.service;

import com.costumers.domain.Costumer;
import com.costumers.exception.CostumerNotFoundException;
import com.costumers.repository.CostumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CostumerService {

    @Autowired
    private CostumerRepository costumerRepository;

    public List<Costumer> getAllClients(){
        return costumerRepository.findAll();
    }

    public Optional<Costumer> getOneCostumer(final Long id) {
        return costumerRepository.findById(id);
    }

    public Optional<Costumer> fullUpdateCostumer(final Costumer costumer) {
        if(costumer.getId() == null || !costumerRepository.findById(costumer.getId()).isPresent()){
            throw new CostumerNotFoundException();
        }
        return Optional.of(costumerRepository.save(costumer));
    }

    public Optional<Costumer> partialUpdateCostumer(final Long costumerId, final Map<String, String> changes){
        final Optional<Costumer> toUpdate = costumerRepository.findById(costumerId);
        if(!toUpdate.isPresent()) {
            throw new CostumerNotFoundException();
        }
        final Costumer costumerToUpdate = toUpdate.get();
        changes.forEach((key, value) -> {
            switch (key){
                case "name": toUpdate.get().setName(value); break;
                case "cpf": toUpdate.get().setCpf(value); break;
                case "birthDate": costumerToUpdate.setBirthDate(LocalDate.parse(value)); break;
                default:
            }
        });
        return Optional.of(costumerRepository.save(costumerToUpdate));
    }

    public Optional<Costumer> saveNewCostumer(final Costumer toSave) {
        return Optional.of(costumerRepository.save(toSave));
    }

    public void deleteCostumer(final Long costumerId) {
        final Optional<Costumer> costumerToDelete = costumerRepository.findById(costumerId);
        if(!costumerToDelete.isPresent()){
            throw new CostumerNotFoundException();
        }
        costumerRepository.delete(costumerToDelete.get());
    }

    public Optional<Costumer> findByCpf(final String cpf) {
        return costumerRepository.findByCpf(cpf);
    }

    public List<Costumer> findByName(final String name) {
        return costumerRepository.findByName(name);
    }

}
