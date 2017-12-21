package com.align.inventory.dto;

import lombok.Data;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 21/12/17.
 */

@Data
public class InventoryExceptionDTO {

    private String errorMessage;

    public InventoryExceptionDTO(Exception e) {
        this.errorMessage = e.getMessage();
    }
}
