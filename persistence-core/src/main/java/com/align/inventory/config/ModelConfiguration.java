package com.align.inventory.config;

import com.align.inventory.model.ModelPackageMarker;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */
@Configuration
@EntityScan(basePackageClasses = ModelPackageMarker.class)
public class ModelConfiguration {
}
