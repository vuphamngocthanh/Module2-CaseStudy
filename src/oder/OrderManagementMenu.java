package oder;

import customer.Customer;
import customer.CustomerManagement;
import product.Product;
import product.ProductManagement;

import javax.security.sasl.SaslClient;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class OrderManagementMenu {
    OrderManagement orderManagement = OrderManagement.getOderManagement();
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();
    ProductManagement productManagement = ProductManagement.getProductManagement();
    public void displayMenu() {
        System.out.println("_________________________________");
        System.out.println("|               MENU            |");
        System.out.println("---------------------------------");
        System.out.println("|          Quả lý hóa đơn       |");
        System.out.println("|1. Thêm hóa đơn                |");
        System.out.println("|2. Xóa hóa đơn                 |");
        System.out.println("|3. Tìm theo ID hóa đơn         |");
        System.out.println("|4. Tìm theo tên khách hàng     |");
        System.out.println("|5. In hóa đơn                  |");
        System.out.println("|0. Thoát                       |");
        System.out.println("---------------------------------");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        int choose = -1;
        while (choose != 0) {
            displayMenu();
            System.out.println("Nhập số");
            choose = scanner.nextInt();
            scanner.nextLine();
            switch (choose) {
                case 1 -> add();
                case 2 -> remove();
                case 3 -> searchByOrderId();
                case 4 -> searchByCustomerName();
                case 5 -> printOrder();
                default -> {
                }
            }
        }
    }


    private void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Danh sách sản phẩm còn hàng");
        List<Product> productList = productManagement.inStock();
        for (Product p : productList) {
            System.out.println(p);
        }
        System.out.println("Nhập id khách hàng");
        String customerId = scanner.nextLine();
        System.out.println("Tên khách hàng");
        String customerName = scanner.nextLine();
        System.out.println("Nhập số điện thoại khách hàng");
        String telephoneNumber = scanner.nextLine();
        Order newOrder = new Order(customerId, customerName, telephoneNumber);
        do {
            System.out.println("1. Thêm sản phẩm");
            System.out.println("0. Dừng thêm sản phẩm");
            int choose = scanner.nextInt();
            scanner.nextLine();
            if (choose == 1) {
                System.out.println("Nhập id sản phẩm");
                String productId = scanner.nextLine();
                do {
                    System.out.println("Nhập số lượng mua");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();
                    Product c = productManagement.searchById(productId);
                    if (quantity > c.getStock()) {
                        System.out.println("Sản phẩm trong kho không còn đủ");
                        System.out.println(c.getProductName() + " còn " + c.getStock());
                        orderManagement.removeByOderId(newOrder.getOrderId());
                        break;
                    } else {
                        newOrder.addProduct(productId, quantity);
                    }
                } while (false);
            } else {
                break;
            }
        } while (true);

        orderManagement.add(newOrder);
    }

    private void remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id đơn hàng cần xóa");
        UUID orderId = UUID.fromString(scanner.nextLine());
        if (orderManagement.removeByOderId(orderId)) {
            System.out.println("Đã xóa");
        } else {
            System.out.println("Xóa thất bại");
        }
    }

    private void searchByOrderId() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id đơn hàng");
        UUID orderId = UUID.fromString(scanner.nextLine());
        Order searchByOrderId = orderManagement.searchByOrderId(orderId);
        if (searchByOrderId != null) {
            System.out.println(searchByOrderId);
        } else {
            System.out.println("Không tìm thấy id hóa đơn");
        }
    }

    private void searchByCustomerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên khách hàng");
        String customerName = scanner.nextLine();
        List<Order> searchByCustomerName = orderManagement.searchByCustomerName(customerName);
        if (searchByCustomerName.size() != 0) {
            for (Order o : searchByCustomerName) {
                System.out.println(o);
            }
        } else {
            System.out.println("Không tìm thấy khách hàng");
        }
    }

    private void printOrder() {
        System.out.println(orderManagement.printOrder());
    }
}
