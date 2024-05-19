package com.devxadarsh.springbootmultidatabasesconfiguration.repository.postgresrepo;

import com.devxadarsh.springbootmultidatabasesconfiguration.entity.postgresentity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepositoryDAO extends CrudRepository<Order, Integer> {
}
