package codegym.service.impl;

import codegym.model.Customer;
import codegym.model.Province;
import codegym.repo.CustomerRepository;
import codegym.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;

public class CustomerServiecImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }


    @Override
    public void remove(Long id) {
        customerRepository.delete(id);
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return customerRepository.findAllByProvince(province);
    }
}