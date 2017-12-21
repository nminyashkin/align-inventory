package com.align.inventory.test;

import com.align.inventory.model.Stock;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.junit.MatcherAssert.assertThat;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 21/12/17.
 */
public class InventoryTestUtils {
    public static void verifyStocks(List<Stock> stocks, List<Stock> expectedFoundByBrand) {
        assertThat(stocks, is(notNullValue()));
        stocks.sort(Comparator.comparing(stock -> stock.getId()));
        assertThat(stocks, is(expectedFoundByBrand));
    }

}
