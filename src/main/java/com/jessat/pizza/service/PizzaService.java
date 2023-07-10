package com.jessat.pizza.service;

import com.jessat.pizza.persistence.repository.PizzaRepository;
import com.jessat.pizza.persistence.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public List<PizzaEntity> getAll() {
        return this.pizzaRepository.findAll();
    }

    public List<PizzaEntity> getAvailable() {
        return this.pizzaRepository.findAllByAvailableEqualsOrderByPrice(1);
    }

    public PizzaEntity getByName(String name) {
        return this.pizzaRepository.findAllByAvailableEqualsAndNameIgnoreCase(1, name);
    }

    public List<PizzaEntity> getWith(String ingredient) {
        return this.pizzaRepository.findAllByAvailableEqualsAndDescriptionContainingIgnoreCase(1, ingredient);
    }
    public List<PizzaEntity> getWithout(String ingredient) {
        return this.pizzaRepository.findAllByAvailableEqualsAndDescriptionNotContainingIgnoreCase(1, ingredient);
    }
    public PizzaEntity get(Integer idPizza) {
        return this.pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return this.pizzaRepository.save(pizza);
    }

    public void delete(int idPizza) {
        this.pizzaRepository.deleteById(idPizza);
    }

    public Boolean exists(int idPizza) {
        return this.pizzaRepository.existsById(idPizza);
    }
}
