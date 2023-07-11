package com.jessat.pizza.persistence.repository;

import com.jessat.pizza.persistence.entity.PizzaEntity;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableEqualsOrderByPrice(Integer available);

    Optional<PizzaEntity> findFirstByAvailableEqualsAndNameIgnoreCase(Integer available, String name);
    List<PizzaEntity> findAllByAvailableEqualsAndDescriptionContainingIgnoreCase(Integer available, String description);
    List<PizzaEntity> findAllByAvailableEqualsAndDescriptionNotContainingIgnoreCase(Integer available, String description);
    List<PizzaEntity> findTop3ByAvailableEqualsAndPriceLessThanEqualOrderByPriceAsc(Integer available, Double price);
    Integer countByVeganEquals(Integer available);
}
