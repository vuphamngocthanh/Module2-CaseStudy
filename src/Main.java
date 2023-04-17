import customer.CustomerManagementMenu;
import oder.OrderManagementMenu;
import product.ProductManagementMenu;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Quản lý khách hàng");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Quản lý đơn hàng");
        System.out.println("Nhập số");
        int choose = scanner.nextInt();
        scanner.nextLine();
        if (choose == 1 ) {
            new CustomerManagementMenu().menu();
        } else if (choose == 2) {
            new ProductManagementMenu().menu();
        } else if (choose == 3) {
            new OrderManagementMenu().menu();
        }
    }
}