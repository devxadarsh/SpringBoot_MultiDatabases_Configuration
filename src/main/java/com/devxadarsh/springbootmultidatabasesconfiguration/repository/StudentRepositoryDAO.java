package com.devxadarsh.springbootmultidatabasesconfiguration.repository;

import com.devxadarsh.springbootmultidatabasesconfiguration.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface StudentRepositoryDAO extends CrudRepository<Student, Integer> {
}
