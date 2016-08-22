package com.cloudcof.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by simon on 2016/8/22.
 */
public interface CofTypeRepository extends JpaRepository<CofType, Integer> {
    List<CofType> findByOwnerId(Integer ownerId, Pageable pageable);
}
