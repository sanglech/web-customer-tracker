package com.christian.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.christian.springdemo.DAO.CustomerDAO;
import com.christian.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {
	//need to inject cutomer DAO
	
	@Autowired
	private CustomerDAO customerDAO;
	
	
/*	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return customerDAO.getCustomers();
	}
*/

	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {
		customerDAO.saveCustomer(theCustomer);
		
	}


	@Override
	@Transactional
	public Customer getCustomer(int theID) {
		
		return customerDAO.getCustomer(theID);
	}


	@Override
	@Transactional
	public void deleteCustomer(int theID) {
		customerDAO.deleteCustomer(theID);
		
	}


	@Override
	@Transactional
	public List<Customer> searchCustomers(String theSearchName) {
		return customerDAO.searchCustomers(theSearchName);
	}


	@Override
	@Transactional
	public List<Customer> getCustomers(int theSortField) {
		return customerDAO.getCustomers(theSortField);
	}

}
