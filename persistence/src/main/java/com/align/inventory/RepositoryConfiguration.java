package com.align.inventory;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories("com.align.inventory.repository")
@Import({
        ModelConfiguration.class
})
public class RepositoryConfiguration {
}
