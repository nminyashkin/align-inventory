package com.align.inventory.service;

import com.align.inventory.exception.InventoryException;
import com.align.inventory.model.Stock;
import com.align.inventory.repository.StockRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
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

    @Override
    public List<Stock> findByBrand(String brand) {
        return stockRepository.findByBrand(brand);
    }

    @Override
    public List<Stock> findByName(String name) {
        return stockRepository.findByName(name);
    }

    @Override
    public List<Stock> search(String brand, String name) {

        if (StringUtils.isBlank(brand)) {
            return stockRepository.findByName(name);
        } else if (StringUtils.isBlank(name)) {
            return stockRepository.findByBrand(brand);
        } else {
            return stockRepository.findByBrandAndName(brand, name);
        }
    }

    @Override
    public List<Stock> findLiftovers() {
        return stockRepository.findLiftovers();
    }

    @Override
    public Integer create(Stock stock) {
        Stock savedStock = stockRepository.save(stock);
        return savedStock.getId();
    }

    @Override
    public void update(Integer id, Stock updatedStock) throws InventoryException {
        Stock stock = stockRepository.findOne(id);

        verifyStockExists(id, stock);

        stock.setBrand(updatedStock.getBrand());
        stock.setName(updatedStock.getName());
        stock.setQuantity(updatedStock.getQuantity());

        stockRepository.save(stock);
    }

    @Override
    public void delete(Integer id) throws InventoryException {
        Stock stock = stockRepository.findOne(id);
        verifyStockExists(id, stock);
        stockRepository.delete(id);
    }

    private void verifyStockExists(Integer id, Stock stock) throws InventoryException {
        if (stock == null) {
            throw new InventoryException(MessageFormat.format("Stock with id {0} not found", id));
        }
    }
}
