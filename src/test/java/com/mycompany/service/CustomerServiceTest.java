package com.mycompany.service;

import com.mycompany.model.CustomerDto;
import com.mycompany.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("customerDto");

        CustomerDto customerTest = customerService.save(customerDto);

        Assert.assertNotNull(customerTest.getId());
        Assert.assertNotNull(customerTest.getHref());
        Assert.assertNotNull(customerTest.getName());
        Assert.assertNull(customerTest.getStatus());
        Assert.assertNull(customerTest.getStatusReason());
        Assert.assertNull(customerTest.getValidFor());
        Assert.assertEquals(customerDto, customerTest);
    }
}