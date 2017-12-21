package com.align.inventory.test;

import com.align.inventory.model.Stock;

import java.util.Arrays;
import java.util.List;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 21/12/17.
 */
public class TestStocks {
    public static Stock stock1 = new Stock("Philips", "TV 1000", 2);
    public static Stock stock2 = new Stock("Philips", "TV 1500", 3);
    public static Stock stock3 = new Stock("Panasonic", "TV 1000", 5);
    public static Stock stock4 = new Stock("Panasonic", "Cool air cleaner", 6);
    public static Stock stock5 = new Stock("Toshiba", "Other air cleaner", 1);

    public static List<Stock> expectedFoundByBrand = Arrays.asList(stock1, stock2);
    public static List<Stock> expectedFoundByName = Arrays.asList(stock1, stock3);
    public static List<Stock> expectedFoundByBrandAndName = Arrays.asList(stock3);
    public static List<Stock> expectedLiftovers = Arrays.asList(stock1, stock2, stock5);
}
