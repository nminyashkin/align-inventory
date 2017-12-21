package com.align.inventory.dto;

import lombok.Data;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 21/12/17.
 */

@Data
public class InventoryResponseDTO {

    private String message;

    public InventoryResponseDTO(String message) {
        this.message = message;
    }
}
