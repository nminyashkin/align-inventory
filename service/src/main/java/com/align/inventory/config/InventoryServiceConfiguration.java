package com.align.inventory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */

@Configuration
@Import({
        RepositoryConfiguration.class
})
public class InventoryServiceConfiguration {

}
