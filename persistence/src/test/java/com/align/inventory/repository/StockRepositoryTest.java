package com.align.inventory.repository;

import com.align.inventory.config.RepositoryConfiguration;
import com.align.inventory.model.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
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
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {
        RepositoryConfiguration.class
})
public class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

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
        List<Stock> stocks = stockRepository.findByBrand("Philips");
        verifyStocks(stocks, expectedFoundByBrand);
    }

    @Test
    public void testFindByName() {
        List<Stock> stocks = stockRepository.findByName("TV 1000");
        verifyStocks(stocks, expectedFoundByName);
    }

    @Test
    public void testFindByBrandAndName() {
        List<Stock> stocks = stockRepository.findByBrandAndName("Panasonic", "TV 1000");
        verifyStocks(stocks, expectedFoundByBrandAndName);
    }

    @Test
    public void testFindLiftovers() {
        List<Stock> stocks = stockRepository.findLiftovers();
        verifyStocks(stocks, expectedLiftovers);
    }

    @Test
    public void testSave() {
        Stock newStock = new Stock("New brand", "New name", 15);
        stockRepository.save(newStock);
        List<Stock> stocks = stockRepository.findByBrandAndName("New brand", "New name");
        verifyStocks(stocks, Arrays.asList(newStock));
    }

    @Test
    public void testUpdate() {
        Stock stock = stockRepository.findByExample(stock1);
        int stockId = stock.getId();
        stock.setBrand("Updated brand");
        stock.setName("Updated name");
        stock.setQuantity(100);
        stockRepository.save(stock);

        Stock foundUpdatedStock = stockRepository.findOne(stockId);
        assertThat(foundUpdatedStock, is(stock));
    }

    // Actually this test is not necessary as we're testing Spring JPA repository but not our functionality
    @Test
    public void testDelete() {
        Stock stock = stockRepository.findByExample(stock1);
        int stockId = stock.getId();
        stockRepository.delete(stock);

        Stock foundStock = stockRepository.findOne(stockId);
        assertThat(foundStock, is(nullValue()));
    }
}
