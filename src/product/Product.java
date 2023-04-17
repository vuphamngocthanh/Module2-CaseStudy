package product;


public class Product {
    private String productId;
    private String productName;

    private String productType;
    private double productPrice;
    private boolean isStock = false;
    private int stock;

    public Product() {
    }

    public Product(String productId, String productName, String productType, double productPrice, boolean isStock, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
        this.isStock = isStock;
        this.stock = stock;
    }

    public Product(String productId, String productName, String productType, double productPrice, int stock) {

        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productPrice = productPrice;
        this.stock = stock;
        if (stock > 0) {
            this.isStock = true;
        }
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public boolean isStock() {
        return isStock;
    }

    public void setStock(boolean stock) {
        isStock = stock;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return productId +
                "," +
                productName +
                "," +
                productType +
                "," +
                productPrice +
                "," +
                isStock +
                "," +
                stock;
    }
}
