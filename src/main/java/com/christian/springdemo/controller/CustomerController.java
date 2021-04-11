package com.christian.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		Customer theCustomer= new Customer();
		
		theModel.addAttribute("customer",theCustomer);
		return "customer-form";
	}
	
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		
		//System.out.println("Hello from /savecustomer");
		
		//save customer using service
		
		customerService.saveCustomer(theCustomer);
		
		
		return "redirect:/customer/list";
	}
	
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theID,Model theModel) {
		
		//get the customer from the service
		Customer theCustomer = customerService.getCustomer(theID);
		
		
		//set customer as model attribute to prepopulate form
		theModel.addAttribute("customer",theCustomer);
		

		//send over to our form
		return "customer-form";
		
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam ("customerid") int theID) {
		//delete the customer
		 customerService.deleteCustomer(theID);
		
		return "redirect:/customer/list";
	}
	

}
