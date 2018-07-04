package com.align.inventory.repository;

import com.align.inventory.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */
@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    @Transactional(readOnly = true)
    List<Stock> findByBrand(String brand);

    @Transactional(readOnly = true)
    List<Stock> findByName(String name);

    @Transactional(readOnly = true)
    List<Stock> findByBrandAndName(String brand, String name);

    @Transactional(readOnly = true)
    @Query("SELECT s FROM Stock s where s.quantity < 5")
    List<Stock> findLiftovers();

    // we use next two methods for testing purposes only
    @Transactional(readOnly = true)
    Stock findByBrandAndNameAndQuantity(String brand, String name, int quantity);

    // we develop this method as findOne(Example<> example) consider entity id
    @Transactional(readOnly = true)
    default Stock findByExample(Stock example) {
        return findByBrandAndNameAndQuantity(example.getBrand(), example.getName(), example.getQuantity());
    }
}
