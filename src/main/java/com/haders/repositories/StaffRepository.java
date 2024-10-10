package com.haders.repositories;

import com.haders.pojo.Staff;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

public interface StaffRepository extends CrudRepository<Staff,Integer>, QuerydslPredicateExecutor<Staff> {
}
