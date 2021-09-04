package Service;

import Model.Customer;
import Repository.InterfaceCustomer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    @Autowired
    InterfaceCustomer interfaceCustomer;

    public List<Customer> list = new ArrayList<>();
    public void add(Customer customer) {
        interfaceCustomer.save(customer);
    }

    public List<Customer> findAll() {
        list = interfaceCustomer.findAll();
        return list;
    }

    public void edit(Customer customer) {
        interfaceCustomer.edit(customer);
    }

    public void delete(int index) {
        interfaceCustomer.delete(list.get(index));
        list.remove(index);
    }

}
