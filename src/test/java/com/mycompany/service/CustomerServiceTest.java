package com.mycompany.service;

import com.mycompany.repository.Customer;
import com.mycompany.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;


    @Test
    void save() {
        Customer customer = new Customer();
        customer.setName("customer");

        Customer customer1 = customerService.save(customer);

        Customer customer2 = new Customer();
        customer2.setName("new customer");

        Assert.assertNotNull(customer1.getId());
        Assert.assertNotNull(customer1.getHref());
        Assert.assertNotNull(customer1.getName());
        Assert.assertNull(customer1.getStatus());
        Assert.assertNull(customer1.getStatusReason());
        Assert.assertNull(customer1.getValidFor());
        Assert.assertEquals(customer, customer1);
        Assert.assertNotEquals(customer2, customer1);


        Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
    }

    @Test
    void update() {
        Customer customer = new Customer();
        customer.setName("customer");
        customer = customerService.save(customer);

        Customer customerUpdate = new Customer();
        customerUpdate.setName("new customer");

        customer.setName(customerUpdate.getName());

        Customer customer1 = customerService.update(customer);

        Assert.assertNotNull(customer1.getId());
        Assert.assertNotNull(customer1.getHref());
        Assert.assertNotNull(customer1.getName());
        Assert.assertNull(customer1.getStatus());
        Assert.assertNull(customer1.getStatusReason());
        Assert.assertNull(customer1.getValidFor());
        Assert.assertEquals(customer, customer1);
        Assert.assertEquals("new customer", customer1.getName());


        Mockito.verify(customerRepository, Mockito.times(1)).updateCustomerById(customer);
        Mockito.verify(customerRepository, Mockito.times(1)).save(customer);
    }
}