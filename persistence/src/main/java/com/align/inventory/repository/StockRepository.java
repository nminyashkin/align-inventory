package com.align.inventory.repository;

import com.align.inventory.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */

public interface StockRepository extends JpaRepository<Stock, Integer> {
    List<Stock> findByBrand(String brand);

    List<Stock> findByName(String name);

    List<Stock> findByBrandAndName(String brand, String name);

    @Query("SELECT s FROM Stock s where s.quantity < 5")
    List<Stock> findLiftovers();

    // we use next two methods for testing purposes only
    Stock findByBrandAndNameAndQuantity(String brand, String name, int quantity);

    // we develop this method as findOne(Example<> example) consider entity id
    default Stock findByExample(Stock example) {
        return findByBrandAndNameAndQuantity(example.getBrand(), example.getName(), example.getQuantity());
    }


}
