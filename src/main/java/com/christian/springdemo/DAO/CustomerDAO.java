package com.christian.springdemo.DAO;

import java.util.List;
import com.christian.springdemo.entity.Customer;


public interface CustomerDAO {
	
	public List<Customer> getCustomers();
}