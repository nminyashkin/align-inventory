package com.align.inventory.repository;

import com.align.inventory.model.Principal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Nikolay Minyashkin (nminyashkin@mail.ru) Created on 21/12/17.
 */
public interface PrincipalRepository extends JpaRepository<Principal, String>{

}
