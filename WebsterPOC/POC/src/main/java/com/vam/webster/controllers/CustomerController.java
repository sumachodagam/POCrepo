package com.vam.webster.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vam.webster.pojos.Customer;
import com.vam.webster.pojos.CustomerStatus;
import com.vam.webster.service.ICustomerService;

@RestController
public class CustomerController {

	@Autowired
	private ICustomerService customerService;
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET, produces="application/json")
	public List<Customer> fetchAllCustomers(){
		return customerService.fetchCustomers();
	}
	@RequestMapping(value = "/customer", method = RequestMethod.POST,
			consumes="application/json", produces="application/json")
	public Customer addCustomers(@RequestBody Customer customer){
		return customerService.addCustomer(customer);
	}
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public Customer customers(@PathVariable("id") int customerId){
		return customerService.fetchCustomerById(customerId);
	}
	
	@RequestMapping(value = "/customer/status/{id}", method = RequestMethod.GET,
			produces = "application/json")
	public CustomerStatus customerStatus(@PathVariable("id") int customerId){
		return customerService.fetchCustomerStatus(customerId);
	}
	
	@RequestMapping(value = "/customer/update", method = RequestMethod.POST,
			consumes="application/json", produces="application/json")
	public Customer updateCustomers(@RequestBody Customer customer){
		return customerService.updateCustomer(customer);
	}
	
	@RequestMapping(value = "/customer/delete", method = RequestMethod.POST, 
			consumes="application/json", produces="application/text")
	public String deleteCustomers(@RequestBody Customer customer) {
		try {
			customerService.deleteCustomer(customer);
			return "Customer "+String.valueOf(customer.getCustomerId())+" deleted successfully";
		} catch (Exception e) {
			return e.getMessage();
		}
		
	}
	
	
}
