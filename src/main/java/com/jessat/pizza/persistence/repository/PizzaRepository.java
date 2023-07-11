package com.jessat.pizza.persistence.repository;

import com.jessat.pizza.persistence.entity.PizzaEntity;
import com.jessat.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {
    List<PizzaEntity> findAllByAvailableEqualsOrderByPrice(Integer available);

    Optional<PizzaEntity> findFirstByAvailableEqualsAndNameIgnoreCase(Integer available, String name);
    List<PizzaEntity> findAllByAvailableEqualsAndDescriptionContainingIgnoreCase(Integer available, String description);
    List<PizzaEntity> findAllByAvailableEqualsAndDescriptionNotContainingIgnoreCase(Integer available, String description);
    List<PizzaEntity> findTop3ByAvailableEqualsAndPriceLessThanEqualOrderByPriceAsc(Integer available, Double price);
    Integer countByVeganEquals(Integer available);
//    @Query(value =
//            "UPDATE pizza " +
//            "SET price = :newPrice " +
//            "WHERE id_pizza = :idPizza", nativeQuery = true)
//    void updatePrice(@Param("idPizza") Integer idPizza, @Param("newPrice") Double newPrice);

    @Query(value =
            "UPDATE pizza " +
            "SET price = :#{#newPizzaPrice.newPrice} " +
            "WHERE id_pizza = :#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    @Modifying
    void updatePrice(@Param("newPizzaPrice") UpdatePizzaPriceDto newPizzaPrice);
}
