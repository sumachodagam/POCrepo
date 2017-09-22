package com.vam.webster.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vam.webster.config.Receiver;
import com.vam.webster.dao.ICustomerDao;
import com.vam.webster.pojos.Customer;
import com.vam.webster.pojos.CustomerStatus;
import com.vam.webster.service.ICustomerService;

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
