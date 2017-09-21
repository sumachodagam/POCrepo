package com.webster.poc.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webster.poc.config.Receiver;
import com.webster.poc.dao.ICustomerDao;
import com.webster.poc.pojos.Customer;
import com.webster.poc.pojos.CustomerStatus;
import com.webster.poc.service.ICustomerService;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDao customerDao;

	@Autowired
	private Receiver receiver;
	
	@Transactional(value=Transactional.TxType.NOT_SUPPORTED)
	@Override
	public List<Customer> fetchCustomers() {
		return customerDao.fetchCustomers();
	}

	@Transactional(value=Transactional.TxType.NOT_SUPPORTED)
	@Override
	public Customer fetchCustomerById(int customerId) {
		return customerDao.fetchCustomerById(customerId);
	}

	@Transactional
	@Override
	public Customer addCustomer(Customer customer) {
		return customerDao.addCustomer(customer);
	}
	
	@Transactional
	@Override
	public Customer updateCustomer(Customer customer) {
		return customerDao.updateCustomer(customer);
	}

	@Transactional
	@Override
	public void deleteCustomer(Customer customer) throws Exception {
		customerDao.deleteCustomer(customer);
	}
	
	@Override
	public CustomerStatus fetchCustomerStatus(int customerId) {
		return receiver.getCustomerStatus(customerId);
	}
}
