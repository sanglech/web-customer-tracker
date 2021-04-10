package com.christian.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.christian.springdemo.entity.Customer;
import com.christian.springdemo.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//need to inject the customer dao
	
	//@Autowired
	//private CustomerDAO customerDAO;
	
	/*Inject customer service INSTEAD of DAO directly
	 ( controller talks to service, service talks to DAO)*/
	@Autowired
	private CustomerService customerService;
	
	
	@GetMapping("/list")
	public String listCustomers(Model theModel){
		
		//get customers from the DAO
		
		List<Customer> theCustomers=customerService.getCustomers();
		
		
		//add the customers to the model
		theModel.addAttribute("customers",theCustomers);
		
		
		
		return "list-customers";
	}

}
