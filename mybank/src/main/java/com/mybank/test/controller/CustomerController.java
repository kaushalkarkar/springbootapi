package com.mybank.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.mybank.test.model.Customer;
import com.mybank.test.model.TransferRequest;
import com.mybank.test.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/customer")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> list = customerService.getAllCustomer();
		if (list.size() <= 0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(list));

	}

	@GetMapping("{accountId}")
	public ResponseEntity<Customer> getMoneyCheckById(@PathVariable(value = "accountId") Integer accountId) {
		Customer moneyCheck = customerService.getMoneyCheckById(accountId);
		return ResponseEntity.ok().body(moneyCheck);
	}

	@PostMapping("/register/customer")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		Customer c = null;
//		try {
		c = this.customerService.addCustomer(customer);
		System.out.println(customer);
		return ResponseEntity.of(Optional.of(c));
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//
//		}
	}

	@DeleteMapping("/customer/{accountId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("accountId") int accountId) {
		try {
			this.customerService.deleteCustomer(accountId);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

	@PutMapping("/customer/{accountId}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer,
			@PathVariable("accountId") int accountId) {
		try {
			this.customerService.updateCustomer(customer, accountId);

			return ResponseEntity.ok().body(customer);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}

	}

	@PostMapping("/login")
	@CrossOrigin(origins = "http://localhost:4200")
	public Customer loginCustomer(@RequestBody Customer customer) throws Exception {
		String tempEmailId = customer.getEmailId();
		String tempPass = customer.getPassword();
		Customer c = null;
		if (tempEmailId != null && tempPass != null) {
			c = customerService.fetchUserByEmailIdAndPassword(tempEmailId, tempPass);
		}
		if (c == null) {
			throw new Exception("bad request");
		}
		return c;

	}

	@PostMapping("/registeruser")
	@CrossOrigin(origins = "http://localhost:4200")
	public Customer registerUser(@RequestBody Customer customer) throws Exception {
		String tempEmailId = customer.getEmailId();
		if (tempEmailId != null && !"".equals(tempEmailId)) {
			Customer userobj = customerService.fetchUserByEmailId(tempEmailId);
			if (userobj != null) {
				throw new Exception("user with" + tempEmailId + "is already");
			}

		}
		Customer userobj = null;
		userobj = customerService.addCustomer(customer);
		return userobj;
	}

	@PostMapping("/deposit")
	@CrossOrigin(origins = "http://localhost:4200")

	public ResponseEntity<String> deposit(@RequestBody Customer customer) {

		customerService.deposit(customer);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/withdraw")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String> withdraw(@RequestBody Customer customer) {
		customerService.withdraw(customer);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/transfer")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<String> transferMoney(@RequestBody TransferRequest transferRequest) {

//		// validate transfer request
		if (transferRequest.getAmount() == null) {
			return ResponseEntity.badRequest().body("Invalid transfer request");
		}

		boolean success = customerService.transferMoney(transferRequest.getFromAccountId(),
				transferRequest.getToAccountId(), transferRequest.getAmount());
		if (success) {
			return ResponseEntity.ok("Money transferred successfully");
		} else {
			return ResponseEntity.badRequest().body("Error transferring money");
		}
	}

	@GetMapping("/{accountId}/{password}")
	public Customer getMoneyCheckById(@PathVariable(value = "accountId") Integer accountId,
			@PathVariable("password") String password) {
		Customer customer = customerService.getMoneyCheckById(accountId, password);
		return customer;
	}

}

//@GetMapping("/customer/{Accountid}")
//@CrossOrigin(origins = "http://localhost:4200")

//public ResponseEntity<Customer> getCustomerById(@PathVariable("accountId") int accountId) {
//	Customer customer = customerService.getCustomerById(accountId);
////	if (customer == null) {
////		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
////	}
//	return ResponseEntity.of(Optional.of(customer));
//
//}
