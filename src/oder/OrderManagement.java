package oder;

import customer.Customer;
import customer.CustomerManagement;
import product.Product;
import product.ProductManagement;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderManagement {
    private static final String FILE_PATH = "order.csv";
    private  static OrderManagement orderManagement = new OrderManagement();
    public static OrderManagement getOderManagement() {
        return orderManagement;
    }

    ProductManagement productManagement = ProductManagement.getProductManagement();
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    private List<Order> orderList;

    private OrderManagement() {
        orderList = new ArrayList<>();
        readFromFile();
    }

    public void add(Order newOrder) {
        newOrder.setTotal();
        orderList.add(newOrder);
        updateQuantity(newOrder);
        updateCustomer(newOrder);
        saveFile();
    }

    public Order searchByOrderId(UUID orderId) {
        for (Order o : orderList) {
            if (o.getOrderId().equals(orderId)); {
                return o;
            }
        }
        return null;
    }

    public List<Order> searchByCustomerName(String customerName) {
        List<Order> orderArrayList = new ArrayList<>();
        for (Order o : orderList) {
            if (o.getCustomerName().contains(customerName)) {
                orderArrayList.add(o);
            }
        }
        return orderArrayList;
    }

    public boolean removeByOderId(UUID orderId) {
        Order o = searchByOrderId(orderId);
        if (o != null) {
            orderList.remove(o);
            saveFile();
            return true;
        }
        return false;
    }

    public String printOrder() {

        String string = "";
        for (Order o : orderList) {
            string += o.toString();
        }
        readFromFile();
        return string;
    }

    public void updateQuantity(Order newOrder) {
        HashMap<String, Integer> newHashMap = newOrder.getHashMap();
        for (Map.Entry<String, Integer>  e : newHashMap.entrySet()) {
            Product product =  productManagement.searchById(e.getKey());
            product.setStock(product.getStock() - e.getValue());
            if (product.getStock() > 0) {
                productManagement.saveFile();
            }
        }
    }

    public void updateCustomer(Order newOrder) {
        Customer c = customerManagement.searchById(newOrder.getCustomerId());
        if (c != null) {
            c.setCustomerId(newOrder.getCustomerId());
            c.setCustomerName(newOrder.getCustomerName());
            c.setTelephoneNumber(newOrder.getTelephoneNumber());
        } else {
            Customer newCustomer = new Customer();
            newCustomer.setCustomerId(newOrder.getCustomerId());
            newCustomer.setCustomerName(newOrder.getCustomerName());
            newCustomer.setTelephoneNumber(newOrder.getTelephoneNumber());
            customerManagement.add(newCustomer);
        }
        customerManagement.saveFile();
    }

    public List<Product> inStockList() {
        return productManagement.inStock();
    }

    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Order o : orderList) {
                bufferedWriter.write(o.toFile());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromFile() {
        orderList.clear();
        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                Order order = handleLine(line);
                orderList.add(order);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Order handleLine(String line) {
        Order o;
        String[] strings = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate stringsDate = LocalDate.parse(strings[1], formatter);

        o = new Order(UUID.fromString(strings[0]), stringsDate, strings[2], strings[3], Double.parseDouble(strings[4]), strings[5]);
        for (int i = 6; i < strings.length; i += 2) {
            o.addProduct(strings[i], Integer.parseInt(strings[i + 1]));
            System.out.println("====================================================");
            System.out.println(strings[i]);
        }
        return o;
    }
}