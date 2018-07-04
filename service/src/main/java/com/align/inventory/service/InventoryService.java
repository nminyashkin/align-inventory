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

    List<Stock> search(String brand, String name);

    List<Stock> findLiftovers();

    Integer create(Stock stock);

    void update(Integer id, Stock stock) throws InventoryException;

    void delete(Integer id) throws InventoryException;
}
