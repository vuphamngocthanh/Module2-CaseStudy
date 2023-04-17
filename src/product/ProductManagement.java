package product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManagement {
    private List<Product> productList;
    private static final String FILE_PATH = "product.csv";
    private static  ProductManagement productManagement= new ProductManagement();
    public static ProductManagement getProductManagement() {
        return productManagement;
    }

    private ProductManagement() {
        productList = new ArrayList<>();
        readFromFile();
    }

    public void add(Product newProduct) {
        productList.add(newProduct);
        saveFile();
    }

    public Product searchById(String productId) {
        for (Product p : productList) {
            if (p.getProductId().equals(productId)) {
                return p;
            }
        }
        return null;
    }

    public List<Product> searchByName(String productName) {
        List<Product> productArrayList = new ArrayList<>();
        for (Product p : productList) {
            if (p.getProductName().contains(productName)) {
                productArrayList.add(p);
            }
        }
        return productArrayList;
    }

    public List<Product> searchByType(String productType) {
        List<Product> productArrayList = new ArrayList<>();
        for (Product p : productList) {
            if (p.getProductType().contains(productType)){
                productArrayList.add(p);
            }
        }
        return productArrayList;
    }

    public boolean removeById(String productId) {
        Product p = searchById(productId);
        if (p != null) {
            productList.remove(p);
            saveFile();
            return true;
        }
        return false;
    }

    public List<Product> displayProduct() {
        return new ArrayList<>(productList);
    }

    public void updateProductById(String productId, Product newProduct) {
        Product p = searchById(productId);
        if (p != null) {
            p.setProductName(newProduct.getProductName());
            p.setStock(newProduct.getStock());
            p.setProductPrice(newProduct.getProductPrice());
        }
        saveFile();
    }

    public List<Product> outOfStock() {
        List<Product> outOfStock = new ArrayList<>();
        for (Product p : productList) {
            if (!p.isStock()) {
                outOfStock.add(p);
            }
        }
        readFromFile();
        return outOfStock;
    }

    public List<Product> inStock() {
        List<Product> inStock = new ArrayList<>();
        for (Product p : productList) {
            if (p.isStock()) {
                inStock.add(p);
            }
        }
        readFromFile();
        return inStock;
    }

    public void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (Product p : productList) {
                bufferedWriter.write(p.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void readFromFile() {
        productList.clear();
        try {
            FileReader fileReader = new FileReader(FILE_PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                Product product = handleLine(line);
                productList.add(product);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Product handleLine(String line) {
        String[] strings = line.split(",");
        return new Product(strings[0], strings[1], strings[2],
                Double.parseDouble(strings[3]), Boolean.parseBoolean(strings[4]), Integer.parseInt(strings[5]));
    }
}
