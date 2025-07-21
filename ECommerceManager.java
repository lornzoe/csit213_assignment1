import java.time.LocalDateTime;
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
        // if customer is already in, don't add
        for (int i = 0; i < customerCount; i++)
        {
            if (this.customers[i].equals(c))
                return false;
        }
        this.customers[customerCount] = c;
        customerCount++;
        return true;
    }

    public boolean addProduct(Product p) {
        if (productCount >= 100)
            return false;
        // if product is already in, don't add
        for (int i = 0; i < productCount; i++)
        {
            if (this.products[i].equals(p))
                return false;
        }
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
		// return a new array that only has products
		Product[] allProducts = new Product[productCount];
		for (int i = 0; i < productCount; i++)
		{
			allProducts[i] = products[i];
		}
		return allProducts;
    }

    public boolean placeOrder(String custId, String productId, int quantity) {
        if (orderCount >= 100)
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
            // if the current orderid somehow exists (e.g. we deleted a previous order & added this)
            // then incremement idNum and try again until we don't have a duplicate
            boolean dupeCheck = false;
            for (int i = 0; i < orderCount; i++)
            {
                if (orders[i].getOrderId().equals(id))
                {
                    dupeCheck = true;
                    break;
                }
            }
            if (dupeCheck)
                idNum += 1;
            else
                break;
        }
        this.orders[orderCount] = new Order(id, customer, product, quantity, date);
		product.reduceStock(quantity);
		orderCount++;
        return true;
    }

    public boolean cancelOrder(String orderId) {
		for (int i = 0; i < orderCount; i++)
		{
			if (orders[i].getOrderId().equals(orderId))
			{
				// return stock to product
				orders[i].getProduct().reduceStock(-orders[i].getQuantity());
				// shift up orders
				for (int j = i; j < orderCount - 1; j++)
				{
					orders[j] = orders[j + 1];
				}
				orders[orderCount - 1] = null;
				orderCount--;
				return true;
			}
		}
		return false;
    }
	
    public Product[] searchProductsByCategory(String category) {
		int count = 0;
		for (int i = 0; i < productCount; i++)
		{
			if (products[i].getCategory().equals(category))
				count++;
		}
		Product[] searchResult = new Product[count];
		int x = 0;
		for (int i = 0; i < productCount; i++)
		{
			if (products[i].getCategory().equals(category))
			{
				searchResult[x] = products[i];
				x++;
			}
		}
		return searchResult;
    }

    public String toString() {
		String output;
		output = "==== Start of ECommerceManager ====";
		output += "\n";

		output += "=== Customers ===";
		output += "\n";
		for (int i = 0; i < customerCount; i++)
		{
			output += customers[i].toString();
			output += "\n";
		}

		output += "=== Products ===";
		output += "\n";
		for (int i = 0; i < productCount; i++)
		{
			output += products[i].toString();
			output += "\n";
		}

		output += "=== Orders ===";
		output += "\n";
		for (int i = 0; i < orderCount; i++)
		{
			output += orders[i].toString();
			output += "\n";
		}

		output += "==== End of ECommerceManager ====";
		output += "\n";
		return output;
    }
}
