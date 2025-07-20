
import java.lang.classfile.instruction.ThrowInstruction;
import java.time.LocalDateTime; // for date
import java.time.format.DateTimeFormatter;

public class ECommerceManager {
    private String storeName;
    private Customer customers[];
    private Product products[];
    private Order orders[];
    private int customerCount;
    private int productCount;
    private int orderCount;
    private int maxItemsPerOrder;

    public ECommerceManager(String name) {
        this.storeName = name;
        this.customers = new Customer[100];
        this.products = new Product[100];
        this.orders = new Order[100];
        this.customerCount = 0;
        this.productCount = 0;
        this.orderCount = 0;
        this.maxItemsPerOrder = 10;
    }

    public boolean addCustomer(Customer c) {
        if (customerCount >= 100)
            return false;
        this.customers[customerCount] = c;
        customerCount++;
        return true;
    }

    public boolean addProduct(Product p) {
        if (productCount >= 100)
            return false;
        this.products[productCount] = p;
        productCount++;
        return true;
    }

    public Customer getCustomer(String custId) {
        for (int i = 0; i < this.customerCount; i++)
        {
            if (this.customers[i].getId().equals(custId))
                return this.customers[i];
        }
        return null;
    }

    public Product getProduct(String prodId) {
        for (int i = 0; i < this.productCount; i++)
        {
            if (this.products[i].getProductId().equals(prodId))
                return this.products[i];
        }
        return null;
    }

    public Product[] getAllProduct() {

        return this.products;
    }

    public boolean placeOrder(String custId, String productId, int quantity) {
        if (orderCount <= 100)
            return false;
        if (quantity <= 0 || quantity > maxItemsPerOrder)
            return false;
        Customer customer = getCustomer(custId);
        Product product = getProduct(productId);
        if (customer == null || product == null)
            return false;
        if (product.getStockQty() < quantity)
            return false;

        // making date string
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = now.format(formatter);

        // making id string
        String id;
        int idNum = orderCount + 1;
        while (true)
        {
            id = "ORD" + idNum;
            if (orderCount == 0)
                break;
            // if the current orderid somehow exists (e.g. we deleted a previous order)
            // then incremement idNum and try again until we don't have a duplicate
            if (orders[orderCount - 1].getOrderId().equals(id))
                idNum += 1;
            else
                break;
        }
        this.orders[orderCount] = new Order(id, customer, product, quantity, date);
        return true;
    }
    public boolean cancelOrder(String orderId) {

    }
    public Product[] searchProductsByCategory(String category) {

    }
    public String toString() {

    }
}
