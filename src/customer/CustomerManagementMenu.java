package customer;

import javax.security.sasl.SaslClient;
import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Scanner;

public class CustomerManagementMenu {
    CustomerManagement customerManagement = CustomerManagement.getCustomerManagement();

    public void displayMenu() {
        System.out.println("_______________________________________");
        System.out.println("|                MENU                 |");
        System.out.println("---------------------------------------");
        System.out.println("|        Quản lý khách hàng           |");
        System.out.println("|1. Thêm khách hàng                   |");
        System.out.println("|2. Xóa khách hàng                    |");
        System.out.println("|3. Tìm theo ID khách hàng            |");
        System.out.println("|4. Tìm theo tên khách hàng           |");
        System.out.println("|5. Tìm theo số điện thoại khách hàng |");
        System.out.println("|6. Cập nhật khách hàng               |");
        System.out.println("|7. Hiển thị toàn bộ khách hàng       |");
        System.out.println("|0. Thoát                             |");
        System.out.println("---------------------------------------");
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
                case 3 -> searchById();
                case 4 -> searchByName();
                case 5 -> searchByTelephoneNumber();
                case 6 -> update();
                case 7 -> displayAll();
                default -> {
                }
            }
        }
    }

    private void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id khách hàng");
        String customerId = scanner.nextLine();
        System.out.println("Nhập tên khách hàng");
        String customerName = scanner.nextLine();
        System.out.println("Nhập số điện thoại khách hàng");
        String telephoneNumber = scanner.nextLine();

        Customer newCustomer = new Customer(customerId, customerName, telephoneNumber);
        customerManagement.add(newCustomer);
    }

    private void remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id khách hàng");
        String customerId = scanner.nextLine();
        if (customerManagement.removeById(customerId)) {
            System.out.println("Đã xóa");
        } else {
            System.out.println("Xóa thất bại");
        }
    }

    private void searchById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id khách hàng");
        String customerId = scanner.nextLine();
        Customer searchCustomerById = customerManagement.searchById(customerId);
        if (searchCustomerById != null) {
            System.out.println(searchCustomerById);
        } else {
            System.out.println("Không tìm thấy thông tin khách hàng");
        }
    }

    private void searchByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên khách hàng");
        String searchCustomerName = scanner.nextLine();
        List<Customer> customerList = customerManagement.searchByName(searchCustomerName);
        if (customerList.size() != 0) {
            for (Customer c : customerList) {
                System.out.println(c);
            }
        } else {
            System.out.println("Không tìm thấy thông tin khách hàng");
        }
    }

    private void searchByTelephoneNumber() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập số điện thoại");
        String searchTelephoneNumber = scanner.nextLine();
        Customer searchByTelephoneNumber = customerManagement.searchByTelephoneNumber(searchTelephoneNumber);
        if (searchByTelephoneNumber != null) {
            System.out.println(searchByTelephoneNumber);
        } else {
            System.out.println("Không tìm thấy thông tin khách hàng");
        }
    }

    private void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id khách hàng cần cập nhật");
        String customerId = scanner.nextLine();
        System.out.println("Nhập tên khách hàng mới");
        String customerName = scanner.nextLine();
        System.out.println("Nhập số điện thoại mới");
        String telephoneNumber = scanner.nextLine();

        Customer newCustomer = new Customer(customerId, customerName, telephoneNumber);
        customerManagement.updateCustomerById(newCustomer.getCustomerId(), newCustomer);
    }

    private void displayAll() {
        List<Customer> customerList = customerManagement.displayCustomer();
        for (Customer c : customerList) {
            System.out.println(c);
        }
    }
}
