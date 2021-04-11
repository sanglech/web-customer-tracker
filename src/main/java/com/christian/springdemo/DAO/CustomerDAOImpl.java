package com.christian.springdemo.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.christian.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		//get current hibernate session
		Session currentSession= sessionFactory.getCurrentSession();
		
		//create query
		Query <Customer> theQuery = currentSession.createQuery("from Customer",Customer.class);
		
		//execute query
		List<Customer> customers= theQuery.getResultList();
		
		//return result list
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session currentSession =sessionFactory.getCurrentSession();
		
		//save customer to db
		currentSession.save(theCustomer);
	}


}
