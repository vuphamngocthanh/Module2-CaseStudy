package oder;

import customer.CustomerManagement;
import product.Product;
import product.ProductManagement;


import java.time.LocalDate;
import java.util.*;

public class Order {
    private UUID orderId;
    private LocalDate purchaseDate;
    private String customerId;
    private String customerName;
    private int quantity = 0;
    private double subTotal;
    private double total;
    private String telephoneNumber;
    private HashMap<String, Integer> hashMap;
    ProductManagement productManagement = ProductManagement.getProductManagement();

    public Order() {
    }
    public Order(UUID orderId, LocalDate purchaseDate, String customerId,
                 String customerName, double total, String telephoneNumber) {
        this.orderId = orderId;
        this.purchaseDate = purchaseDate;
        this.customerId = customerId;
        this.customerName = customerName;
        this.total = total;
        this.telephoneNumber = telephoneNumber;
        this.hashMap = new HashMap<>();
    }

    public Order(String customerId, String customerName, String telephoneNumber) {
        this.orderId = UUID.randomUUID();
        this.purchaseDate = LocalDate.now();
        this.customerId = customerId;
        this.customerName = customerName;
        this.telephoneNumber = telephoneNumber;
        this.hashMap = new HashMap<>();
    }

    public HashMap<String, Integer> getHashMap() {
        return hashMap;
    }

    public void addProduct(String productId, int quantity) {
        getHashMap().put(productId, quantity);
    }

    public double getSubTotal(String productId, int quantity) {
        double sub = 0;
        Product p = productManagement.searchById(productId);
        sub = p.getProductPrice() * quantity;
        this.subTotal = sub;
        return sub;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal() {
        double total = 0;
        for (String key : hashMap.keySet()) {
            total += getSubTotal(key, hashMap.get(key));
        }
        this.total = total;
    }
    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        String out = "";
        for (Map.Entry<String, Integer> h : getHashMap().entrySet()) {
            Product p = productManagement.searchById(h.getKey());
            out += " " + h.getKey() + "\t\t\t\t" + p.getProductName() + "\t\t\t" + p.getProductPrice() + "\t\t\t" + h.getValue() + "\t\t\t\t\t" + getSubTotal(h.getKey(), h.getValue()) + "\n";

        }

        return
                "------------------------------------------------------------------------------------------------------\n" +
                "|                                            Hóa đơn siêu thị mini                                   |\n" +
                "------------------------------------------------------------------------------------------------------\n" +
                "ID hóa đơn: " + orderId + "\n" +
                "Ngày mua: " + purchaseDate + "\n" +
                "Tên khách hàng: " + customerName + "\n" +
                "SĐT khách hàng: " + telephoneNumber + "\n" +
                "------------------------------------------------------------------------------------------------------\n" +
                "|ID sản phẩm \t\t" + "Tên sản phẩm \t\t\t\t" + "Giá \t\t\t" + "Số lượng \t\t\t" + "Thành tiền\t\t | \n" +
                "------------------------------------------------------------------------------------------------------\n" +
                out + "\n" +
                "Tổng tiền: " + getTotal() + "\n" +
                "                                          -----Cảm ơn quý khách-----                                  \n\n";
    }

    public String toFile() {
        String out = "";
        out += orderId + "," + purchaseDate + "," + customerId + "," + customerName + "," + total + "," + telephoneNumber;
        for (String pId : hashMap.keySet()) {
            out += "," + pId + "," + hashMap.get(pId);
        }
        return out;
    }


}
