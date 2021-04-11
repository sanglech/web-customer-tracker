package com.christian.springdemo.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.christian.springdemo.entity.Customer;
import com.christian.springdemo.utils.SortUtils;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	/*
	@Override
	public List<Customer> getCustomers() {
		
		//get current hibernate session
		Session currentSession= sessionFactory.getCurrentSession();
		
		//create query sort by last name
		Query <Customer> theQuery = currentSession.createQuery("from Customer order by lastName",
				Customer.class);
		
		//execute query
		List<Customer> customers= theQuery.getResultList();
		
		//return result list
		return customers;
	}
*/
	@Override
	public void saveCustomer(Customer theCustomer) {
		//get current hibernate session
		Session currentSession =sessionFactory.getCurrentSession();
		
		//save customer to db
		//currentSession.save(theCustomer);
		currentSession.saveOrUpdate(theCustomer);
	}

	@Override
	public Customer getCustomer(int theID) {
		//get current hibernate session
		Session currentSession= sessionFactory.getCurrentSession();
		
		//get based off of id
		Customer theCustomer = currentSession.get(Customer.class, theID);
		
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theID) {
		//get current hibernate session
		Session currentSession= sessionFactory.getCurrentSession();
		
		//delete obj with primary key
		Query delQuery=currentSession.createQuery("delete from Customer where id=:theCustomerId");
		delQuery.setParameter("theCustomerId", theID);
		
		delQuery.executeUpdate();
		
		
	}

	@Override
	public List<Customer> searchCustomers(String theSearchName) {
		 // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
        
        Query theQuery = null;
        
        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);            
        }
        
        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();
                
        // return the results        
        return customers;
	}

	@Override
	public List<Customer> getCustomers(int theSortField) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// determine sort field
		String theFieldName = null;
		
		switch (theSortField) {
			case SortUtils.FIRST_NAME: 
				theFieldName = "firstName";
				break;
			case SortUtils.LAST_NAME:
				theFieldName = "lastName";
				break;
			case SortUtils.EMAIL:
				theFieldName = "email";
				break;
			default:
				// if nothing matches the default to sort by lastName
				theFieldName = "lastName";
		}
		
		// create a query  
		String queryString = "from Customer order by " + theFieldName;
		Query<Customer> theQuery = 
				currentSession.createQuery(queryString, Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}


}
