package com.align.inventory.controller;

import com.align.inventory.dto.InventoryExceptionDTO;
import com.align.inventory.dto.InventoryResponseDTO;
import com.align.inventory.exception.InventoryException;
import com.align.inventory.model.Stock;
import com.align.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */
@RestController
@RequestMapping("/stocks")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping()
    public List<Stock> findAll() {
        return inventoryService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search")
    public List<Stock> search(
            @RequestParam(value = "brand", required = false) String brand,
            @RequestParam(value = "name", required = false) String name
    ) {
        return inventoryService.search(brand, name);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/liftovers")
    public List<Stock> findLiftovers() {
        return inventoryService.findLiftovers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Integer create(@RequestBody Stock stock) {
        return inventoryService.create(stock);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public void update(@PathVariable Integer id, @RequestBody Stock updatedStock) throws InventoryException {
        inventoryService.update(id, updatedStock);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) throws InventoryException {
        inventoryService.delete(id);
    }
}

