package com.align.inventory.service;

import com.align.inventory.model.Stock;

import java.util.List;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */
public interface InventoryService {
    List<Stock> findAll();
}
