package com.christian.springdemo.service;

import java.util.List;

import com.christian.springdemo.entity.Customer;


public interface CustomerService {
	
	//public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theID);

	public void deleteCustomer(int theID);

	public List<Customer> searchCustomers(String theSearchName);

	public List<Customer> getCustomers(int theSortField);
}
