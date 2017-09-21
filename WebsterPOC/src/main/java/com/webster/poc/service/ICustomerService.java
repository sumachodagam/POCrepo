package com.webster.poc.service;

import java.util.List;

import com.webster.poc.pojos.Customer;
import com.webster.poc.pojos.CustomerStatus;

public interface ICustomerService {

	public List<Customer> fetchCustomers();
	public Customer fetchCustomerById(int customerId);
	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public void deleteCustomer(Customer customer) throws Exception;
	public CustomerStatus fetchCustomerStatus(int customerId);
}
