package com.align.inventory.service;

import com.align.inventory.exception.InventoryException;
import com.align.inventory.model.Stock;

import java.util.List;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */
public interface InventoryService {
    List<Stock> findAll();

    List<Stock> findByBrand(String brand);

    List<Stock> findByName(String name);

    List<Stock> findByBrandAndName(String brand, String name);

    List<Stock> findLiftovers();

    // We must return created stock as client might need it's ID
    Stock add(String brand, String name, Integer quantity);

    void update(Integer id, String brand, String name, Integer quantity) throws InventoryException;

    void delete(Integer id) throws InventoryException;

}
