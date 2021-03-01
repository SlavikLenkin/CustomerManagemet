package com.mycompany.transfomer;

import com.mycompany.model.CustomerDto;
import com.mycompany.repository.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerTransformer {

    public Customer transform(CustomerDto customerDto) {
        Customer target = new Customer();
        target.setHref(customerDto.getHref());
        target.setId(customerDto.getId());
        target.setName(customerDto.getName());
        target.setStatus(customerDto.getStatus());
        target.setStatusReason(customerDto.getStatusReason());
        target.setValidFor(customerDto.getValidFor());
        return target;
    }


}
