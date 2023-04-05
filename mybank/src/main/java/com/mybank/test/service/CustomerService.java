package com.mybank.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mybank.test.dao.CustomerRepository;

import com.mybank.test.model.Customer;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public List<Customer> getAllCustomer() {
		List<Customer> list = (List<Customer>) this.customerRepository.findAll();
		return list;

	}

	public Customer getCustomerById(Integer accountId) {
		
//		try {
//			customer = this.customerRepository.findById(accountId);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return customerRepository.findByaccountId(accountId);
		
	}
//		public Customer getMoneyCheckById(Integer accountId) {
//			return customerRepository.findByaccountId(accountId);
//		}

	

	public Customer addCustomer(Customer c) {
		Customer res = customerRepository.save(c);
		return res;

	}

	public void deleteCustomer(int accountId) {
		customerRepository.deleteById(accountId);
	}

	public void updateCustomer(Customer customer, int accountId) {
		customer.setAccountId(accountId);
		customerRepository.save(customer);
	}

	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public Customer addStudent(Customer customer) {
		Customer res = customerRepository.save(customer);
		return res;
	}

	public Customer fetchUserByEmailId(String email) {
		return customerRepository.findByEmailId(email);

	}

	public Customer fetchUserByEmailIdAndPassword(String email, String password) {
		return customerRepository.findByEmailIdAndPassword(email, password);

	}

	public void deposit(Customer customerData) {

		try {
			Customer customer = customerRepository.findByaccountId(customerData.getAccountId());
			if (customer == null && customerData.getDepositAmount()>20000) 
			{
				throw new RuntimeException("PLEASE ENTER UNDER 20000 FUNDS");
			}
			customer.setDepositAmount(customer.getDepositAmount() + (customerData.getDepositAmount()));
			customerRepository.save(customer);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void withdraw(Customer customerData) {
		try {
			Customer customer = customerRepository.findByaccountId(customerData.getAccountId());

			if (customer.getDepositAmount() < customerData.getDepositAmount())
			{
				throw new RuntimeException("INSUFFICIENT FUNDS");
			}	
				customer.setDepositAmount(customer.getDepositAmount() - (customerData.getDepositAmount()));
				customerRepository.save(customer);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Customer getMoneyCheckById(Integer accountId) {
		return customerRepository.findByaccountId(accountId);
	}

	
	public boolean transferMoney(int fromAccountId, int toAccountId, Double amount) {
	
	Customer fromCustomer = customerRepository.findById(fromAccountId);
    Customer toCustomer = customerRepository.findById(toAccountId);
    
    
   
// 	if (!fromCustomer.getPassword().equals(passwords)) 
//    {
//		throw new RuntimeException("Invalid password");
//	}

	if (fromCustomer.getDepositAmount() < amount) {
		throw new RuntimeException("Insufficient balance");
	}
    
    
    
	
    fromCustomer.setDepositAmount(fromCustomer.getDepositAmount() - amount);
    customerRepository.save(fromCustomer);
    
    
	toCustomer.setDepositAmount(toCustomer.getDepositAmount() + amount);
	customerRepository.save(toCustomer);
	return true;
	}

	    
    
   
	public Customer getMoneyCheckById(Integer accountId, String password) {
	        return customerRepository.findByaccountIdAndPassword(accountId, password);
	       
	          
	        
	}}
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//	public void transferMoney(Integer fromaccountId1, Integer toaccountId2, String password, Double depositAmount) {
//
//		Customer fromCustomer = customerRepository.findByaccountId(fromaccountId1);
//
//		Customer toCustomer = customerRepository.findByaccountId(toaccountId2);
//
//		if (!fromCustomer.getPassword().equals(password)) {
//			throw new RuntimeException("Invalid password");
//		}
//
//		if (fromCustomer.getDepositAmount() < depositAmount) {
//			throw new RuntimeException("Insufficient balance");
//		}
//
//		fromCustomer.setDepositAmount(fromCustomer.getDepositAmount() - depositAmount);
//		toCustomer.setDepositAmount(toCustomer.getDepositAmount() + depositAmount);
//
//		customerRepository.save(fromCustomer);
//		customerRepository.save(toCustomer);
//	}
	
//	@Transactional
//    public boolean transferMoney(int fromAccountId, int toAccountId, Double amount) {
//        // fetch accounts from database
//        Optional<Customer> fromAccountOptional = customerRepository.findById(fromAccountId);
//        Optional<Customer> toAccountOptional = customerRepository.findById(toAccountId);
//        
//        if (fromAccountOptional.isEmpty() || toAccountOptional.isEmpty()) {
//            return false;
//        }
//        
//        Customer fromCustomer = fromAccountOptional.get();
//        Customer toCustomer = toAccountOptional.get();
//        
//        // check if from account has sufficient balance
//        if (fromAccount.get().compareTo(amount) < 0) {
//            return false;
//        }
//        
//        // transfer money
//        fromAccount.setDepositAmount(fromAccount.getDepositAmount()-(amount));
//        toAccount.setDepositAmount(toAccount.getDepositAmount()+(amount));
//        
//        customerRepository.save(fromAccount);
//        customerRepository.save(toAccount);
//        
//        return true;
//    }
//    
//    public Double getBalance(int accountId) {
//        Optional<Customer> accountOptional = customerRepository.findById(accountId);
//        return accountOptional.map(Customer::getBalance).orElse(null);
//    }

