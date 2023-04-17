package customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManagement {
    private List<Customer> customerList;
    private static final String FILE_PATH = "customer.csv";
    private static CustomerManagement customerManagement = new CustomerManagement();

    public static CustomerManagement getCustomerManagement() {
        return customerManagement;
    }

    private CustomerManagement() {
        customerList = new ArrayList<>();
        readFromFile();
    }

    public void add(Customer newCustomer) {
        customerList.add(newCustomer);
        saveFile();
    }

    public Customer searchById(String customerId) {
        for (Customer c : customerList) {
            if (c.getCustomerId().equals(customerId)) {
                return c;
            }
        }
        return null;
    }

    public List<Customer> searchByName(String customerName) {
        List<Customer> customerArrayList = new ArrayList<>();
        for (Customer c : customerList) {
            if (c.getCustomerName().contains(customerName)) {
                customerArrayList.add(c);
            }
        }
        return customerArrayList;
    }

    public Customer searchByTelephoneNumber(String telephoneNumber) {
        for (Customer c : customerList) {
            if (c.getTelephoneNumber().equals(telephoneNumber)) {
                return c;
            }
        }
        return null;
    }

    public boolean removeById(String customerId) {
        Customer c = searchById(customerId);
        if (c != null) {
            customerList.remove(c);
            saveFile();
            return true;
        }

        return false;
    }

    public List<Customer> displayCustomer() {
        return new ArrayList<>(customerList);
    }

    public void updateCustomerById(String customerId, Customer newCustomer) {
        Customer c = searchById(customerId);
        if (c != null) {
            c.setCustomerName(newCustomer.getCustomerName());
            c.setTelephoneNumber(newCustomer.getTelephoneNumber());
        }
        saveFile();
    }

    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Customer c : customerList) {
                bufferedWriter.write(c.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromFile() {
        customerList.clear();
        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                Customer customer = handleLine(line);
                customerList.add(customer);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer handleLine(String line) {
        String[] strings = line.split(",");
        return new Customer(strings[0], strings[1], strings[2]);
    }
}
