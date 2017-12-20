package com.align.inventory.service;

import com.align.inventory.model.Stock;
import com.align.inventory.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> findAll() {
        return stockRepository.findAll();
    }
}
