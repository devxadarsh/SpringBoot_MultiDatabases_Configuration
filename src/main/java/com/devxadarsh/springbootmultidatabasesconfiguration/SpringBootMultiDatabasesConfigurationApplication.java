package com.devxadarsh.springbootmultidatabasesconfiguration;

import com.devxadarsh.springbootmultidatabasesconfiguration.entity.postgresentity.Order;
import com.devxadarsh.springbootmultidatabasesconfiguration.entity.mysqlentity.Product;
import com.devxadarsh.springbootmultidatabasesconfiguration.entity.Student;
import com.devxadarsh.springbootmultidatabasesconfiguration.repository.postgresrepo.OrderRepositoryDAO;
import com.devxadarsh.springbootmultidatabasesconfiguration.repository.mysqlrepo.ProductRepositoryDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class SpringBootMultiDatabasesConfigurationApplication implements CommandLineRunner {

	@Autowired
	private ProductRepositoryDAO productRepositoryDAO;

	@Autowired
	private OrderRepositoryDAO orderRepositoryDAO;

	@Autowired
	Student student;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultiDatabasesConfigurationApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Product product = new Product(1,"laptop",200000);
		productRepositoryDAO.save(product);

		Order order = new Order(101, "Adarsh Pandey", LocalDate.now());
		orderRepositoryDAO.save(order);
	}
}
