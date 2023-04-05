package com.mybank.test.dao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mybank.test.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

	public Customer findById(int accountId);
	
	

	public Customer findByEmailId(String emailId);

	public Customer findByEmailIdAndPassword(String emailId, String password);
	public  Customer findByaccountIdAndPassword(Integer accountId, String password);

	public Customer findByaccountId(Integer accountId);
}
