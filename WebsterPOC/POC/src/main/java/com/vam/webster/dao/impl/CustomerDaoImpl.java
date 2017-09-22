package com.vam.webster.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.vam.webster.dao.ICustomerDao;
import com.vam.webster.pojos.Customer;

@Repository
public class CustomerDaoImpl implements ICustomerDao {

	
	private final Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);

	
	@PersistenceContext	
	private EntityManager entityManager;
	
	
	@Override
	public List<Customer> fetchCustomers() {
		logger.info("CustomerDaoImpl::fetchCustomers::start");
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
		Root<Customer> root = criteriaQuery.from(Customer.class);
		criteriaQuery.select(root);
		List<Customer> customers = entityManager.createQuery(criteriaQuery).getResultList();
		logger.info("CustomerDaoImpl::fetchCustomers::end");
		return customers;
	}

	
	@Override
	public Customer fetchCustomerById(int customerId) {
		logger.info("CustomerDaoImpl::fetchCustomerById::start");
		Customer customer = null;
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
		Root<Customer> root = criteriaQuery.from(Customer.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("customerId"), customerId));
		List<Customer> customers = entityManager.createQuery(criteriaQuery).getResultList();
		if(null != customers && !customers.isEmpty()){
			customer = customers.get(0);
		}
		logger.info("CustomerDaoImpl::fetchCustomerById::end");
		return customer;
	}
	
	
	@Override
	public Customer addCustomer(Customer customer) {
		logger.info("CustomerDaoImpl::addCustomer::start");
		entityManager.persist(customer);
		logger.info("CustomerDaoImpl::addCustomer::end");
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		logger.info("CustomerDaoImpl::updateCustomer::start");
		entityManager.merge(customer);
		logger.info("CustomerDaoImpl::updateCustomer::end");
		return customer;
	}
	
	@Override
	public void deleteCustomer(Customer customer) throws Exception {
		logger.info("CustomerDaoImpl::deleteCustomer::start");
		entityManager.remove(entityManager.merge(customer));
		logger.info("CustomerDaoImpl::deleteCustomer::end");
	}

}
