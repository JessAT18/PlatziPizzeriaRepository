package com.jessat.pizza.persistence.repository;

import com.jessat.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableEqualsOrderByPrice(Integer available);

    PizzaEntity findAllByAvailableEqualsAndNameIgnoreCase(Integer available, String name);
    List<PizzaEntity> findAllByAvailableEqualsAndDescriptionContainingIgnoreCase(Integer available, String description);
    List<PizzaEntity> findAllByAvailableEqualsAndDescriptionNotContainingIgnoreCase(Integer available, String description);
    Integer countByVeganEquals(Integer available);
}
