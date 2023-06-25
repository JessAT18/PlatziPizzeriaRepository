package com.jessat.pizza.web.controller;

import com.jessat.pizza.persistence.entity.PizzaEntity;
import com.jessat.pizza.service.PizzaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@Slf4j
public class PizzaController {
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        return ResponseEntity.ok(this.pizzaService.getAll());
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> get(@PathVariable int idPizza) {
        return ResponseEntity.ok(this.pizzaService.get(idPizza));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody PizzaEntity t){
        try {
            if (t.getIdPizza() == null || !this.pizzaService.exists(t.getIdPizza())) {
                return ResponseEntity.ok(this.pizzaService.save(t));
            }
            // HTTP CODE: 400
            // return ResponseEntity.badRequest().build();
            return ResponseEntity.status(HttpStatus.CONFLICT).body("La Pizza ya existe!");
        } catch (Exception e) {
            log.error("PizzaController: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody PizzaEntity t){
        try {
            if (t.getIdPizza() != null || this.pizzaService.exists(t.getIdPizza())) {
                return ResponseEntity.ok(this.pizzaService.save(t));
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La Pizza no existe!");
        } catch (Exception e) {
            log.error("PizzaController: " + e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza) {
        if (this.pizzaService.exists(idPizza)) {
            this.pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
