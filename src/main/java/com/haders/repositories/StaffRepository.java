package com.haders.repositories;

import com.haders.entity.StaffEntity;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<StaffEntity,Integer>, QuerydslPredicateExecutor<StaffEntity>,ExcelRepository {
}
