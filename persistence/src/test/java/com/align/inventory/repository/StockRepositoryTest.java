package com.align.inventory.repository;

import com.align.inventory.config.RepositoryConfiguration;
import com.align.inventory.model.Stock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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

    private static class Stocks {
        private static Stock stock1 = new Stock("Philips", "TV 1000", 2);
        private static Stock stock2 = new Stock("Philips", "TV 1500", 3);
        private static Stock stock3 = new Stock( "Panasonic", "TV 1000", 5);
        private static Stock stock4 = new Stock( "Panasonic", "Cool air cleaner", 6);
        private static Stock stock5 = new Stock( "Toshiba", "Other air cleaner", 1);

        private static List<Stock> expectedFoundByBrand = Arrays.asList(stock1, stock2);
        private static List<Stock> expectedFoundByName = Arrays.asList(stock1, stock3);
        private static List<Stock> expectedFoundByBrandAndName = Arrays.asList(stock3);
        private static List<Stock> expectedLiftovers = Arrays.asList(stock1, stock2, stock5);

        private static Example<Stock> stock1Example = Example.of(Stocks.stock1);
    }

    @Before
    public void prepareTestData() {
        stockRepository.deleteAll();
        System.out.println("size: " + stockRepository.findAll().size());

        stockRepository.save(Stocks.stock1);
        stockRepository.save(Stocks.stock2);
        stockRepository.save(Stocks.stock3);
        stockRepository.save(Stocks.stock4);
        stockRepository.save(Stocks.stock5);
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
        Stock stock = stockRepository.findByExample(Stocks.stock1);
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
        Stock stock = stockRepository.findByExample(Stocks.stock1);
        int stockId = stock.getId();
        stockRepository.delete(stock);

        Stock foundStock = stockRepository.findOne(stockId);
        assertThat(foundStock, is(nullValue()));
    }

    @Test
    public void testFindByBrand() {
        List<Stock> stocks = stockRepository.findByBrand("Philips");
        verifyStocks(stocks, Stocks.expectedFoundByBrand);
    }

    @Test
    public void testFindByName() {
        List<Stock> stocks = stockRepository.findByName("TV 1000");
        verifyStocks(stocks, Stocks.expectedFoundByName);
    }

    @Test
    public void testFindByBrandAndName() {
        List<Stock> stocks = stockRepository.findByBrandAndName("Panasonic", "TV 1000");
        verifyStocks(stocks, Stocks.expectedFoundByBrandAndName);
    }

    @Test
    public void testFindLiftovers() {
        List<Stock> stocks = stockRepository.findLiftovers();
        verifyStocks(stocks, Stocks.expectedLiftovers);

    }

    private void verifyStocks(List<Stock> stocks, List<Stock> expectedFoundByBrand) {
        assertThat(stocks, is(notNullValue()));
        stocks.sort(Comparator.comparing(stock -> stock.getId()));
        assertThat(stocks, is(expectedFoundByBrand));
    }

}
