package com.cloudcof.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by simon on 2016/8/25.
 */
public interface BaristaRepository extends JpaRepository<Barista,Integer>{
    Barista findByName(String name);
}
