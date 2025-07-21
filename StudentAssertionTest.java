public class StudentAssertionTest {

	public static void testCustomer() {
		
		// Test 1: Constructor and Getters
		Customer customer1 = new Customer("C001", "Alice", "alice@example.com");

		assert customer1.getId().equals("C001") : "ID mismatch";
		assert customer1.getName().equals("Alice") : "Name mismatch";
		assert customer1.getEmail().equals("alice@example.com") : "Email mismatch";

		// Test 2: equals() method
		Customer customer2 = new Customer("C001", "Alice", "alice@example.com");
		Customer customer3 = new Customer("C002", "Bob", "bob@example.com");
		assert customer1.equals(customer2) : "Customers with same ID should be equal";

		// Test 3: toString() method
		String expectedString = "Customer[id=C001, name=Alice, email=alice@example.com]";
		assert customer1.toString().equals(expectedString) : "toString output mismatch";

		//add in more test cases on your own....
        try {
            testExtraCustomer();
        } catch (AssertionError e) {
            throw (e);
        }

		System.out.println("All Customer assertions passed.");
	}
	
	public static void testProduct() {
        Product product = new Product("P001", "Laptop", "Electronics", 1500.0, 10);

        // Test getters
        assert product.getProductId().equals("P001") : "Product ID mismatch";
        assert product.getProdDesc().equals("Laptop") : "Product description mismatch";
        assert product.getCategory().equals("Electronics") : "Category mismatch";
        assert product.getPrice() == 1500.0 : "Price mismatch";
        assert product.getStockQty() == 10 : "Stock quantity mismatch";

        // Test reduceStock
        product.reduceStock(3);
        assert product.getStockQty() == 7 : "Stock reduction failed";

        // Test equals
        Product sameProduct = new Product("P001", "Laptop", "Electronics", 1500.0, 7);
        Product differentProduct = new Product("P002", "Tablet", "Electronics", 800.0, 5);
        assert product.equals(sameProduct) : "Products with same ID should be equal";

        // Test toString
        String expected = "Product[id=P001, Description=Laptop, price=1500.0, stock=7]";
        assert product.toString().equals(expected) : "toString output mismatch";

		//add in more test cases on your own....
        try {
            testExtraProduct();
        } catch (AssertionError e) {
            throw (e);
        }

        System.out.println("All Product assertions passed.");
        
    }   
	
	public static void testOrder() {
        Customer customer = new Customer("C001", "Alice", "alice@example.com");
        Product product = new Product("P001", "Laptop", "Electronics", 1500.0, 10);
        Order order = new Order("O001", customer, product, 2, "2025-06-28");

        // Test getters
        assert order.getOrderId().equals("O001") : "Order ID mismatch";
        assert order.getCustomer().equals(customer) : "Customer mismatch";
        assert order.getProduct().equals(product) : "Product mismatch";
        assert order.getQuantity() == 2 : "Quantity mismatch";
        assert order.getOrderDate().equals("2025-06-28") : "Order date mismatch";

        // Test equals
        Order sameOrder = new Order("O001", customer, product, 2, "2025-06-28");
        Order differentOrder = new Order("O002", customer, product, 2, "2025-06-28");
        assert order.equals(sameOrder) : "Orders with same ID should be equal";

        // Test toString
        String expected = "Order[id=O001, product=Laptop, quantity=2, date=2025-06-28]";
        assert order.toString().equals(expected) : "toString output mismatch";

		//add in more test cases on your own....
				try {
            testExtraOrder();
        } catch (AssertionError e) {
            throw (e);
        }
		
        System.out.println("All Order assertions passed.");
    }
	
	
	public static void testECommerceManager() {
        ECommerceManager manager = new ECommerceManager("TechStore");

        // Create customers
        Customer c1 = new Customer("C001", "Alice", "alice@example.com");
        Customer c2 = new Customer("C002", "Bob", "bob@example.com");

        // Add customers
        assert manager.addCustomer(c1) : "Failed to add customer Alice";
        assert manager.addCustomer(c2) : "Failed to add customer Bob";

        // Create products
        Product p1 = new Product("P001", "Laptop", "Electronics", 1500.0, 5);
        Product p2 = new Product("P002", "Tablet", "Electronics", 800.0, 10);
        Product p3 = new Product("P003", "Book", "Books", 20.0, 50);

        // Add products
        assert manager.addProduct(p1) : "Failed to add product Laptop";
        assert manager.addProduct(p2) : "Failed to add product Tablet";
        assert manager.addProduct(p3) : "Failed to add product Book";

        // Place orders
        assert manager.placeOrder("C001", "P001", 2) : "Failed to place order for Laptop";
        assert manager.placeOrder("C002", "P002", 1) : "Failed to place order for Tablet";

        // Cancel order
        assert manager.cancelOrder("ORD1") : "Failed to cancel order ORD1";

        // Search by category
        Product[] electronics = manager.searchProductsByCategory("Electronics");
        assert electronics.length == 2 : "Electronics category search failed";

        Product[] books = manager.searchProductsByCategory("Books");
        assert books.length == 1 : "Books category search failed";

        // toString check
        String output = manager.toString();
        assert output.contains("=== Customers ===") : "Missing customers section";
        assert output.contains("=== Orders ===") : "Missing orders section";

		//add in more test cases on your own....
		try {
            testExtraECommerceManager();
        } catch (AssertionError e) {
            throw (e);
        }

        System.out.println("All ECommerceManager assertions passed.");
	}

	// Extra checks 
    public static void testExtraCustomer() {
        Customer customer1 = new Customer("C001", "Alice", "alice@example.com");

		// equals() -- null check
		assert !customer1.equals(null) : "(extra): Customer should not be equal to null";

		// equals() -- diff class type
		assert !customer1.equals("C001") : "(extra): Customer should not be equal to different object type";

		// equals() -- self test
		assert customer1.equals(customer1): "(extra): Product should be equal to itself";
	}
	
    public static void testExtraProduct() {
		Product product1 = new Product("P001", "Laptop", "Electronics", 1500.0, 10);

		// reduceStock -- insufficient quantity (should not change)
		product1.reduceStock(12);
        assert product1.getStockQty() == 10 : "(extra): Stock should not reduce if insufficient quantity";
    
		// reduceStock to 0
		product1.reduceStock(10);
		assert product1.getStockQty() == 0 : "(extra): Stock reduce to zero failed";

        // equals() -- null object
        assert !product1.equals(null) : "(extra): Product should not be equal to null";

        // equals() -- different class type
        assert !product1.equals("P001") : "(extra): Product should not be equal to different object type";

		// equals() -- self test
		assert product1.equals(product1) : "(extra): Product should be equal to itself";
	}

    public static void testExtraOrder() {
		Customer customer = new Customer("C001", "Alice", "alice@example.com");
        Product product = new Product("P001", "Laptop", "Electronics", 2500.0, 15);
        Order order1 = new Order("O001", customer, product, 2, "2025-06-28");

		// equals() -- null object
        assert !order1.equals(null) : "(extra): Order should not be equal to null";

        // equals() -- different class type
        assert !order1.equals(customer) : "(extra): Order should not be equal to different object type";

		// equals() -- self test
		assert order1.equals(order1) : "(extra): Order should be equal to itself";
    }

    public static void testExtraECommerceManager() {
		ECommerceManager manager = new ECommerceManager("ExtraTechStore");
		
		// addCustomer -- valid
        Customer c1 = new Customer("C001", "Alice", "alice@test.com");
        Customer c2 = new Customer("C002", "Bob", "bob@test.com");
        assert manager.addCustomer(c1) : "(extra): Failed to add C001";
        assert manager.addCustomer(c2) : "(extra): Failed to add C002";

        // addCustomer -- duplicate (should return false)
        assert !manager.addCustomer(c1) : "(extra): Should not add duplicate customer C001";

        // getCustomer -- existing
        assert manager.getCustomer("C001").equals(c1) : "(extra): getCustomer C001 failed";
        // Test Case 4: getCustomer - non-existent
        assert manager.getCustomer("C999") == null : "(extra): getCustomer C999 should be null";


        // addProduct -- valid
        Product p1 = new Product("P001", "Smartphone", "Electronics", 999.99, 10);
        Product p2 = new Product("P002", "Headphones", "Audio", 150.00, 20);
        assert manager.addProduct(p1) : "(extra): Failed to add P001";
        assert manager.addProduct(p2) : "(extra): Failed to add P002";

        // addProduct -- duplicate (should return false)
        assert !manager.addProduct(p1) : "(extra): Should not add duplicate product P001";

        // getProduct -- existing
        assert manager.getProduct("P001").equals(p1) : "(extra): getProduct P001 failed";
        //	getProduct -- non-existent
        assert manager.getProduct("P999") == null : "(extra): getProduct P999 should be null";


        // placeOrder -- valid order (first order will be ORD1)
        assert manager.placeOrder("C001", "P001", 2) : "(extra): Failed to place order 1";
        // Check product stock via getProduct().getStockQty()
        assert manager.getProduct("P001").getStockQty() == 8 : "(extra): P001 stock not reduced accordingly";

        // placeOrder - another valid order (second order will be ORD2)
        assert manager.placeOrder("C002", "P002", 5) : "(extra): Failed to place order 2";
        assert manager.getProduct("P002").getStockQty() == 15 : "(extra): P002 stock not reduced accordingly";

        // placeOrder -- invalid quantity (zero)
        assert !manager.placeOrder("C001", "P001", 0) : "(extra): Should not place order with 0 quantity";

        // placeOrder -- invalid quantity (negative)
        assert !manager.placeOrder("C001", "P001", -5) : "(extra): Should not place order with negative quantity";

        // placeOrder -- invalid quantity (exceeds maxItemsPerOrder, 10)
        assert !manager.placeOrder("C001", "P001", 11) : "(extra): Should not place order with quantity > 10";

        // placeOrder -- insufficient stock
        assert !manager.placeOrder("C001", "P001", 9) : "(extra): Should not place order with insufficient stock"; // Only 8 left

        // placeOrder -- non-existent customer
        assert !manager.placeOrder("C999", "P001", 1) : "(extra): Should not place order for non-existent customer";

        // placeOrder -- non-existent product
        assert !manager.placeOrder("C001", "P999", 1) : "(extra): Should not place order for non-existent product";

        // cancelOrder -- valid cancellation (ORD1)
        assert manager.cancelOrder("ORD1") : "(extra): Failed to cancel ORD1";
        // Check product stock after cancellation
        assert manager.getProduct("P001").getStockQty() == 10 : "(extra): P001 stock not restored after cancellation"; // Original 10, ordered 2, cancelled, now 10 again

        //cancelOrder -- cancelling already cancelled order (should return false)
        assert !manager.cancelOrder("ORD1") : "(extra): Should not cancel already cancelled order ORD1";

        // cancelOrder -- non-existent order (should return false)
        assert !manager.cancelOrder("doesnotexist") : "(extra): Should not cancel non-existent order";

        // placeOrder -- verify placeorder uniqueIDs
        // my placeOrder creates unique order numbers starting from orderCount + 1,
        // so this check relies on making sure ORD3 isn't created in this test and ORD6 is created instead
		Product p3 = new Product("P003", "Java Book", "Books", 50.0, 30);
        manager.addProduct(p3);
        assert manager.placeOrder("C001", "P003", 7) : "(extra): Book order failed"; // ORD3
        assert manager.placeOrder("C001", "P003", 8) : "(extra): Book order failed"; // ORD4
        assert manager.placeOrder("C001", "P003", 9) : "(extra): Book order failed"; // ORD5
        assert manager.cancelOrder("ORD3") : "(extra): Failed to cancel ORD3"; // Book order with quantity 7
        assert manager.placeOrder("C001", "P003", 7) : "(extra): Book order failed"; // ORD6
        String check = manager.toString();
        assert !check.contains("ORD3") : "(extra): ORD3 was not removed";
        assert check.contains("ORD6") : "(extra): ORD6 was not added";
        assert check.contains("quantity=7") : "(extra): ORD6 with quantity 7 was not added"; // ORD6 is the only one with quantity of 7

        // searchProductsByCategory -- existing category
        Product[] electronics = manager.searchProductsByCategory("Electronics");
        assert electronics.length == 1 : "(extra): Electronics category search failed (expected 1, got " + electronics.length + ")";
        assert electronics[0].equals(p1) : "(extra): Electronics search content mismatch"; // Should be P001
        
		// searchProductsByCategory -- another existing category
        Product[] books = manager.searchProductsByCategory("Books");
        assert books.length == 1 : "(extra): Books category search failed";
        assert books[0].equals(p3) : "(extra): Books search content mismatch";

        // searchProductsByCategory -- non-existent category
        Product[] food = manager.searchProductsByCategory("Food");
        assert food.length == 0 : "(extra): Search for non-existent category should return empty array";

        // toString check
		// *this contains an Orders section that was not in the initial tests 
        String output = manager.toString();
        assert output.contains("=== Customers ===") : "(extra): Missing customers section in toString";
        assert output.contains("=== Products ===") : "(extra): Missing products section in toString";
        assert output.contains("=== Orders ===") : "(extra): Missing orders section in toString";
        assert output.contains("Customer[id=C001, name=Alice, email=alice@test.com]") : "(extra): toString missing C001";
        assert output.contains("Product[id=P001, Description=Smartphone, price=999.99, stock=10]") : "(extra): toString missing P001";
        assert output.contains("Order[id=ORD2, product=Headphones, quantity=5, date=") : "(extra): toString missing ORD2";
	}

	private static void run() {
		
		if(testAssertionEnabled()) {
			try {
				testCustomer();
			}catch(AssertionError ex) {
				System.out.println("Customer test failed:"+ex);
			}
			
			try {
				testProduct();
			}catch(AssertionError ex) {
				System.out.println("Product test failed:"+ex);
			}
			
			try {
				testOrder();
			}catch(AssertionError ex) {
				System.out.println("Order test failed:"+ex);
			}
			
			try {
				testECommerceManager();
			}catch(AssertionError ex) {
				System.out.println("ECommerceManager test failed:"+ex);
			}
		}
	}
	
	private static boolean testAssertionEnabled() {
		boolean assertionsEnabled = false;
        assert assertionsEnabled = true; // Intentional side effect

        if (assertionsEnabled) {
            System.out.println("Assertions are enabled.");
            return true;
        } else {
            System.out.println("Assertions are NOT enabled. Use -ea to enable them.");
            return false;
        }
	}
	
	public static void main(String[] args) {
		run();
	}

}
