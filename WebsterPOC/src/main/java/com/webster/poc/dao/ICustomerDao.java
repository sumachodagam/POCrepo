package com.webster.poc.dao;

import java.util.List;

import com.webster.poc.pojos.Customer;

public interface ICustomerDao {

	public List<Customer> fetchCustomers();
	public Customer fetchCustomerById(int customerId);
	public Customer addCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public void deleteCustomer(Customer customer) throws Exception;
}
