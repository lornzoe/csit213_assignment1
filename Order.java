public class Order {
    private String orderId;
    private Customer customer;
    private Product product;
    private int quantity;
    private String orderDate;
    
    public Order(String id, Customer cust, Product prod, int quant, String date) {
        this.orderId = id;
        this.customer = cust;
        this.product = prod;
        this.quantity = quant;
        this.orderDate = date;
    }

    public String getOrderId() {
        return this.orderId;
    }
    public Customer getCustomer() {
        return this.customer;
    }
    public Product getProduct() {
        return this.product;
    }
    public int getQuantity() {
        return this.quantity;
    }
    public String getOrderDate() {
        return this.orderDate;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        Order order = (Order)obj;
        
        return this.orderId.equals(order.getOrderId());
    }
    public String toString() {
        // Order[id=O001, product=Laptop, quantity=2, date=2025-06-28]
        return "Order[id=" + this.orderId
                + ", product=" + this.product.getProdDesc()
                + ", quantity=" + this.quantity
                + ", date=" + this.orderDate + "]";
    }
}
