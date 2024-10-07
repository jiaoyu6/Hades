package com.haders.repositories;

import com.haders.pojo.Staff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface StaffRepository extends CrudRepository<Staff,Integer> {
}
