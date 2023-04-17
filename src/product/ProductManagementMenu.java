package product;

import java.util.List;
import java.util.Scanner;

public class ProductManagementMenu {
    ProductManagement productManagement = ProductManagement.getProductManagement();

    public void displayMenu() {
        System.out.println("_________________________________");
        System.out.println("|               MENU            |");
        System.out.println("---------------------------------");
        System.out.println("|       Quản lý sản phẩm        |");
        System.out.println("|1. Thêm sản phẩm               |");
        System.out.println("|2. Xóa sản phẩm                |");
        System.out.println("|3. Tìm theo ID sản phẩm        |");
        System.out.println("|4. Tìm theo tên sản phẩm       |");
        System.out.println("|5. Tìm theo loại sản phẩm      |");
        System.out.println("|6. Cập nhật sản phẩm           |");
        System.out.println("|7. Sản phẩm hết hàng           |");
        System.out.println("|8. Sản phẩm còn hàng           |");
        System.out.println("|9. Hiển thị toàn bộ sản phẩm   |");
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
                case 3 -> searchById();
                case 4 -> searchByName();
                case 5 -> searchByType();
                case 6 -> update();
                case 7 -> outOfStock();
                case 8 -> inStock();
                case 9 -> System.exit(0);
                default -> {
                }
            }
        }

    }

    private void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id sản phẩm");
        String productId = scanner.nextLine();
        System.out.println("Nhập tên sản phẩm");
        String productName = scanner.nextLine();
        if (productName.length() < 19) {
            for (int i = productName.length(); i < 20; i++ ) {
                productName += ' ';
            }
        }
        System.out.println("Nhập loại sản phẩm");
        String productType = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm");
        double productPrice = scanner.nextDouble();
        System.out.println("Nhập số lượng sản phẩm");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Product newProduct = new Product(productId, productName, productType, productPrice, quantity);
        productManagement.add(newProduct);
    }

    private void remove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id sản phẩm");
        String productId = scanner.nextLine();
        if (productManagement.removeById(productId)) {
            System.out.println("Đã xóa");
        } else {
            System.out.println("Xóa thất bại");
        }
    }

    private void searchById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id sản phẩm");
        String productId = scanner.nextLine();
        Product searchProductById = productManagement.searchById(productId);
        if (searchProductById != null) {
            System.out.println(searchProductById);
        } else {
            System.out.println("Không tìm thấy sản phẩm");
        }
    }

    private void searchByName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập tên sản phẩm");
        String searchProductName = scanner.nextLine();
        List<Product> productNameList = productManagement.searchByName(searchProductName);
        if (productNameList.size() != 0) {
            for (Product p : productNameList) {
                System.out.println(p);
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm");
        }

    }

    private void searchByType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập loại sản phẩm");
        String searchProductType = scanner.nextLine();
        List<Product> productTypeList = productManagement.searchByType(searchProductType);
        if (productTypeList.size() != 0) {
            for (Product p : productTypeList) {
                System.out.println(p);
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm");
        }

    }

    private void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập id sản phẩm cần cập nhật");
        String productId = scanner.nextLine();
        System.out.println("Nhập tên sản phẩm mới");
        String productName = scanner.nextLine();
        System.out.println("Nhập loại sản phẩm mới");
        String productType = scanner.nextLine();
        System.out.println("Nhập giá sản phẩm mới");
        double productPrice = scanner.nextDouble();
        System.out.println("Nhập số lượng sản phẩm mới");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        Product productUpdate = new Product(productId, productName, productType, productPrice, quantity);
        productManagement.updateProductById(productUpdate.getProductId(), productUpdate);
    }
    private void outOfStock() {
        List<Product> productOutOfStockList = productManagement.outOfStock();
        for (Product p : productOutOfStockList) {
            System.out.println(p);
        }
    }

    private void inStock() {
        List<Product> productInStockList = productManagement.inStock();
        for (Product p : productInStockList) {
            System.out.println(p);
        }
    }

    private void displayAll() {
        List<Product> productList = productManagement.displayProduct();
        for (Product p : productList) {
            System.out.println(p);
        }
    }

}
