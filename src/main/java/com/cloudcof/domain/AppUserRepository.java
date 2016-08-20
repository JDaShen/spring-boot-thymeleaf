package com.cloudcof.domain;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by simon on 2016/8/20.
 */
public interface AppUserRepository extends PagingAndSortingRepository<AppUser, String> {
}
