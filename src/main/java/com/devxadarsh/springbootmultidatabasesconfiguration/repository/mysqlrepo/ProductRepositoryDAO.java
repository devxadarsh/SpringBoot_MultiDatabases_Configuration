package com.devxadarsh.springbootmultidatabasesconfiguration.repository.mysqlrepo;

import com.devxadarsh.springbootmultidatabasesconfiguration.entity.mysqlentity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepositoryDAO extends CrudRepository<Product, Integer> {
}
