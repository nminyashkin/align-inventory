package com.align.inventory.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 21/12/17.
 */

@Entity
@Data
public class Principal {

    @Id
    private String user;

    private String password;

    private boolean isAdmin;
}
