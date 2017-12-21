package com.align.inventory.service;

import com.align.inventory.config.InventoryServiceConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 21/12/17.
 */
@Configuration
@Import({
        InventoryServiceConfiguration.class
})
public class InventoryServiceTestConfiguration {

    @Bean
    public InventoryService inventoryService() {
        return new InventoryServiceImpl();
    }
}
