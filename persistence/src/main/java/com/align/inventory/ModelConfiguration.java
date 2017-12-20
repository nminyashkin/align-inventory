package com.align.inventory;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */
@Configuration
@EntityScan(value = "com.align.inventory.model")
public class ModelConfiguration {
}
