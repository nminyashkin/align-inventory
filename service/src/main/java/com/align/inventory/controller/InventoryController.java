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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/findAll")
    public List<Stock> findAll() {
        return inventoryService.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/findByBrand/{brand}")
    public List<Stock> findByBrand(@PathVariable String brand) {
        return inventoryService.findByBrand(brand);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/findByName/{name}")
    public List<Stock> findByName(@PathVariable String name) {
        return inventoryService.findByName(name);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/findByBrandAndName/{brand}/{name}")
    public List<Stock> findByBrandAndName(@PathVariable String brand, @PathVariable String name) {
        return inventoryService.findByBrandAndName(brand, name);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/findLiftovers")
    public List<Stock> findLiftovers() {
        return inventoryService.findLiftovers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/add/{brand}/{name}/{quantity}")
    public Stock add(
            @PathVariable String brand,
            @PathVariable String name,
            @PathVariable Integer quantity
    ) {
        return inventoryService.add(brand, name, quantity);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/update/{id}/{brand}/{name}/{quantity}")
    public ResponseEntity update(
            @PathVariable Integer id,
            @PathVariable String brand,
            @PathVariable String name,
            @PathVariable Integer quantity
    ) {

        try {
            inventoryService.update(id, brand, name, quantity);
            return getResponseEntity("Stock has been updated");

        } catch (InventoryException e) {
            return getErrorEntity(e);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {

        try {
            inventoryService.delete(id);
            return getResponseEntity("Stock has been deleted");

        } catch (InventoryException e) {
            return getErrorEntity(e);
        }
    }

    private ResponseEntity getResponseEntity(String s) {
        InventoryResponseDTO responseDTO = new InventoryResponseDTO(s);
        return ResponseEntity.ok().body(responseDTO);
    }

    private ResponseEntity getErrorEntity(InventoryException e) {
        InventoryExceptionDTO exceptionDTO = new InventoryExceptionDTO(e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionDTO);
    }

}

