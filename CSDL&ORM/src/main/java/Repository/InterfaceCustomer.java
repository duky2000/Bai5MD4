package Repository;

import Model.Customer;

import java.awt.*;
import java.util.List;

public interface InterfaceCustomer {
    Customer save(Customer customer);

    List<Customer> findAll();

    void delete(Customer customer);

    void edit(Customer customer);
}
