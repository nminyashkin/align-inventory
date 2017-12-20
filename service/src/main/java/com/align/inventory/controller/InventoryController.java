package com.align.inventory.controller;

import com.align.inventory.model.Stock;
import com.align.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 18/12/17.
 */
@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<Stock> findAll() {
        List<Stock> all = inventoryService.findAll();
        return all;
    }


}
