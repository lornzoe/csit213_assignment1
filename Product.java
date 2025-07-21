public class Product {
    private String productId;
    private String prodDesc;
    private String category;
    private double price;
    private int stockQty;
    
    public Product(String id, String desc, String category, double price, int qty) {
        this.productId = id;
        this.prodDesc = desc;
        this.category = category;
        this.price = price;
        this.stockQty = qty;
    }
    public String getProductId() {
        return this.productId;
    }
    public String getProdDesc() {
        return this.prodDesc;
    }
    public String getCategory() {
        return this.category;
    }
    public double getPrice() {
        return this.price;
    }
    public int getStockQty() {
        return this.stockQty;
    }
    public void reduceStock(int qty) {
        // stockQty shouldn't be able to go to negative
        if (this.stockQty < qty)
            return;
        this.stockQty -= qty;
    }
    
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Product))
            return false;
        if (this == obj)
           return true;
        Product product = (Product)obj;

        return this.productId.equals(product.productId);
    }

    public String toString() {
        return "Product[id=" + this.productId
                + ", Description=" + this.prodDesc
                + ", price=" + this.price
                + ", stock=" + this.stockQty + "]";
    }
}
