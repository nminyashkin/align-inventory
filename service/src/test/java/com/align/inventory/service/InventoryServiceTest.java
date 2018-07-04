package com.align.inventory.service;

import com.align.inventory.exception.InventoryException;
import com.align.inventory.model.Stock;
import com.align.inventory.repository.StockRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.align.inventory.test.InventoryTestUtils.verifyStocks;
import static com.align.inventory.test.TestStocks.expectedFoundByBrand;
import static com.align.inventory.test.TestStocks.expectedFoundByBrandAndName;
import static com.align.inventory.test.TestStocks.expectedFoundByName;
import static com.align.inventory.test.TestStocks.expectedLiftovers;
import static com.align.inventory.test.TestStocks.stock1;
import static com.align.inventory.test.TestStocks.stock2;
import static com.align.inventory.test.TestStocks.stock3;
import static com.align.inventory.test.TestStocks.stock4;
import static com.align.inventory.test.TestStocks.stock5;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 21/12/17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        InventoryServiceTestConfiguration.class
})
public class InventoryServiceTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private InventoryService inventoryService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void prepareTestData() {
        stockRepository.deleteAll();

        stockRepository.save(stock1);
        stockRepository.save(stock2);
        stockRepository.save(stock3);
        stockRepository.save(stock4);
        stockRepository.save(stock5);
    }

    @Test
    public void testFindByBrand() {
        List<Stock> stocks = inventoryService.findByBrand("Philips");
        verifyStocks(stocks, expectedFoundByBrand);
    }

    @Test
    public void testFindByName() {
        List<Stock> stocks = inventoryService.findByName("TV 1000");
        verifyStocks(stocks, expectedFoundByName);
    }

    @Test
    public void testFindByBrandAndName() {
        List<Stock> stocks = inventoryService.search("Panasonic", "TV 1000");
        verifyStocks(stocks, expectedFoundByBrandAndName);
    }

    @Test
    public void testFindLiftovers() {
        List<Stock> stocks = inventoryService.findLiftovers();
        verifyStocks(stocks, expectedLiftovers);
    }

    @Test
    public void testAdd() {
        Stock newStock = inventoryService.create("New brand", "New name", 15);
        Stock foundStock = stockRepository.findOne(newStock.getId());
        assertThat(foundStock.getBrand(), is("New brand"));
        assertThat(foundStock.getName(), is("New name"));
        assertThat(foundStock.getQuantity(), is(15));
    }

    @Test
    public void testUpdateExisted() throws InventoryException {
        Stock stock = stockRepository.findByExample(stock1);
        int stockId = stock.getId();
        inventoryService.update(stockId, "Updated brand", "Updated name", 100);

        Stock foundUpdatedStock = stockRepository.findOne(stockId);
        assertThat(foundUpdatedStock.getBrand(), is("Updated brand"));
        assertThat(foundUpdatedStock.getName(), is("Updated name"));
        assertThat(foundUpdatedStock.getQuantity(), is(100));
    }

    @Test
    public void testUpdateNotExisted() throws InventoryException {
        thrown.expect(InventoryException.class);
        thrown.expectMessage(containsString(" not found"));
        inventoryService.update(-1, "Updated brand", "Updated name", 100);
    }

    @Test
    public void testDeleteExisted() throws InventoryException {
        Stock stock = stockRepository.findByExample(stock1);
        int stockId = stock.getId();
        inventoryService.delete(stockId);

        Stock foundStock = stockRepository.findOne(stockId);
        assertThat(foundStock, is(nullValue()));
    }

    @Test
    public void testDeleteNotExisted() throws InventoryException {
        thrown.expect(InventoryException.class);
        thrown.expectMessage(containsString(" not found"));
        inventoryService.delete(-1);
    }
}
