// Module code: CSIT213
// Assignment name: Assignment 1
// Your UOW student number: Your_UOW_Student_Number
// Your full name: Your_Full_Name
// Your tutorial group: Your_Tutorial_Group

// This class provides comprehensive assertion-based testing for the Customer, Product, Order,
// and ECommerceManager classes, focusing strictly on the functions listed in the assignment document's
// class diagram.
public class ComprehensiveECommerceTest {

    // Helper method to check if assertions are enabled.
    // This method is crucial for ensuring that 'assert' statements actually run.
    private static boolean testAssertionEnabled() {
        boolean assertionsEnabled = false;
        // Intentional side effect: if assertions are enabled, this assert will execute
        // and change 'assertionsEnabled' to true.
        assert assertionsEnabled = true;

        if (assertionsEnabled) {
            System.out.println("Assertions are enabled. Running tests...");
            return true;
        } else {
            System.out.println("Assertions are NOT enabled. Please run with -ea (e.g., java -ea ComprehensiveECommerceTest).");
            return false;
        }
    }

    // --- Customer Class Tests ---
    public static void testCustomerClass() {
        System.out.println("\n--- Running Customer Class Tests ---");

        // Test Case 1: Constructor and Getters
        Customer customer1 = new Customer("C001", "Alice Smith", "alice.s@example.com");
        assert customer1.getId().equals("C001") : "Customer Test 1 Failed: ID mismatch";
        assert customer1.getName().equals("Alice Smith") : "Customer Test 1 Failed: Name mismatch";
        assert customer1.getEmail().equals("alice.s@example.com") : "Customer Test 1 Failed: Email mismatch";

        // Test Case 2: equals() method - same ID, different details
        Customer customer2 = new Customer("C001", "Alice J.", "alice.j@example.com");
        assert customer1.equals(customer2) : "Customer Test 2 Failed: Customers with same ID should be equal";

        // Test Case 3: equals() method - different ID
        Customer customer3 = new Customer("C002", "Bob Johnson", "bob.j@example.com");
        assert !customer1.equals(customer3) : "Customer Test 3 Failed: Customers with different IDs should not be equal";

        // Test Case 4: equals() method - null object
        assert !customer1.equals(null) : "Customer Test 4 Failed: Customer should not be equal to null";

        // Test Case 5: equals() method - different class type
        assert !customer1.equals("C001") : "Customer Test 5 Failed: Customer should not be equal to different object type";

        // Test Case 6: toString() method
        String expectedString = "Customer[id=C001, name=Alice Smith, email=alice.s@example.com]";
        assert customer1.toString().equals(expectedString) : "Customer Test 6 Failed: toString output mismatch";

        System.out.println("All Customer Class Tests Passed.");
    }

    // --- Product Class Tests ---
    public static void testProductClass() {
        System.out.println("\n--- Running Product Class Tests ---");

        // Test Case 1: Constructor and Getters
        Product product1 = new Product("P001", "Gaming Laptop", "Electronics", 2500.0, 15);
        assert product1.getProductId().equals("P001") : "Product Test 1 Failed: Product ID mismatch";
        assert product1.getProdDesc().equals("Gaming Laptop") : "Product Test 1 Failed: Description mismatch";
        assert product1.getCategory().equals("Electronics") : "Product Test 1 Failed: Category mismatch";
        assert product1.getPrice() == 2500.0 : "Product Test 1 Failed: Price mismatch";
        assert product1.getStockQty() == 15 : "Product Test 1 Failed: Stock quantity mismatch";

        // Test Case 2: reduceStock and its effect on getStockQty
        product1.reduceStock(5);
        assert product1.getStockQty() == 10 : "Product Test 2 Failed: Stock reduction failed (expected 10)";

        // Test Case 3: reduceStock with insufficient quantity (should not change stock)
        product1.reduceStock(12); // Try to reduce by more than available (10)
        assert product1.getStockQty() == 10 : "Product Test 3 Failed: Stock should not reduce if insufficient quantity (expected 10)";

        // Test Case 4: reduceStock to zero
        product1.reduceStock(10);
        assert product1.getStockQty() == 0 : "Product Test 4 Failed: Stock reduction to zero failed (expected 0)";

        // Test Case 5: equals() method - same ID, different details
        Product product2 = new Product("P001", "Old Laptop", "Laptops", 1000.0, 5);
        assert product1.equals(product2) : "Product Test 5 Failed: Products with same ID should be equal";

        // Test Case 6: equals() method - different ID
        Product product3 = new Product("P002", "Wireless Mouse", "Peripherals", 30.0, 100);
        assert !product1.equals(product3) : "Product Test 6 Failed: Products with different IDs should not be equal";

        // Test Case 7: equals() method - null object
        assert !product1.equals(null) : "Product Test 7 Failed: Product should not be equal to null";

        // Test Case 8: equals() method - different class type
        assert !product1.equals("P001") : "Product Test 8 Failed: Product should not be equal to different object type";

        // Test Case 9: toString() method
        String expectedString = "Product[id=P001, Description=Gaming Laptop, price=2500.0, stock=0]"; // Stock is 0 after previous tests
        assert product1.toString().equals(expectedString) : "Product Test 9 Failed: toString output mismatch";

        System.out.println("All Product Class Tests Passed.");
    }

    // --- Order Class Tests ---
    public static void testOrderClass() {
        System.out.println("\n--- Running Order Class Tests ---");

        Customer customer = new Customer("C001", "Alice Smith", "alice.s@example.com");
        Product product = new Product("P001", "Gaming Laptop", "Electronics", 2500.0, 15);

        // Test Case 1: Constructor and Getters
        Order order1 = new Order("ORD001", customer, product, 2, "2025-07-10");
        assert order1.getOrderId().equals("ORD001") : "Order Test 1 Failed: Order ID mismatch";
        assert order1.getCustomer().equals(customer) : "Order Test 1 Failed: Customer object mismatch";
        assert order1.getProduct().equals(product) : "Order Test 1 Failed: Product object mismatch";
        assert order1.getQuantity() == 2 : "Order Test 1 Failed: Quantity mismatch";
        assert order1.getOrderDate().equals("2025-07-10") : "Order Test 1 Failed: Order date mismatch";

        // Test Case 2: equals() method - same ID, different details
        Order order2 = new Order("ORD001", new Customer("C999", "Dummy", "dummy@example.com"), new Product("P999", "Dummy", "Dummy", 1.0, 1), 5, "2025-01-01");
        assert order1.equals(order2) : "Order Test 2 Failed: Orders with same ID should be equal";

        // Test Case 3: equals() method - different ID
        Order order3 = new Order("ORD002", customer, product, 1, "2025-07-11");
        assert !order1.equals(order3) : "Order Test 3 Failed: Orders with different IDs should not be equal";

        // Test Case 4: equals() method - null object
        assert !order1.equals(null) : "Order Test 4 Failed: Order should not be equal to null";

        // Test Case 5: equals() method - different class type
        assert !order1.equals(customer) : "Order Test 5 Failed: Order should not be equal to different object type";

        // Test Case 6: toString() method
        String expectedString = "Order[id=ORD001, product=Gaming Laptop, quantity=2, date=2025-07-10]";
        assert order1.toString().equals(expectedString) : "Order Test 6 Failed: toString output mismatch";

        System.out.println("All Order Class Tests Passed.");
    }

    // --- ECommerceManager Class Tests ---
    public static void testECommerceManagerClass() {
        System.out.println("\n--- Running ECommerceManager Class Tests ---");

        ECommerceManager manager = new ECommerceManager("GlobalTech Store");

        // Test Case 1: addCustomer - valid
        Customer c1 = new Customer("C001", "Alice", "alice@test.com");
        Customer c2 = new Customer("C002", "Bob", "bob@test.com");
        assert manager.addCustomer(c1) : "Manager Test 1 Failed: Failed to add C001";
        assert manager.addCustomer(c2) : "Manager Test 1 Failed: Failed to add C002";

        // Test Case 2: addCustomer - duplicate (should return false)
        assert !manager.addCustomer(c1) : "Manager Test 2 Failed: Should not add duplicate customer C001";

        // Test Case 3: getCustomer - existing
        assert manager.getCustomer("C001").equals(c1) : "Manager Test 3 Failed: getCustomer C001 failed";
        // Test Case 4: getCustomer - non-existent
        assert manager.getCustomer("C999") == null : "Manager Test 4 Failed: getCustomer C999 should be null";


        // Test Case 6: addProduct - valid
        Product p1 = new Product("P001", "Smartphone", "Electronics", 999.99, 10);
        Product p2 = new Product("P002", "Headphones", "Audio", 150.00, 20);
        assert manager.addProduct(p1) : "Manager Test 6 Failed: Failed to add P001";
        assert manager.addProduct(p2) : "Manager Test 6 Failed: Failed to add P002";

        // Test Case 7: addProduct - duplicate (should return false)
        assert !manager.addProduct(p1) : "Manager Test 7 Failed: Should not add duplicate product P001";

        // Test Case 8: getProduct - existing
        assert manager.getProduct("P001").equals(p1) : "Manager Test 8 Failed: getProduct P001 failed";
        // Test Case 9: getProduct - non-existent
        assert manager.getProduct("P999") == null : "Manager Test 9 Failed: getProduct P999 should be null";


        // Test Case 11: placeOrder - valid order (first order will be ORD1)
        assert manager.placeOrder("C001", "P001", 2) : "Manager Test 11 Failed: Failed to place order 1";
        // Check product stock via getProduct().getStockQty()
        assert manager.getProduct("P001").getStockQty() == 8 : "Manager Test 11 Failed: P001 stock not reduced as expected";

        // Test Case 12: placeOrder - another valid order (second order will be ORD2)
        assert manager.placeOrder("C002", "P002", 5) : "Manager Test 12 Failed: Failed to place order 2";
        assert manager.getProduct("P002").getStockQty() == 15 : "Manager Test 12 Failed: P002 stock not reduced as expected";

        // Test Case 13: placeOrder - invalid quantity (zero)
        assert !manager.placeOrder("C001", "P001", 0) : "Manager Test 13 Failed: Should not place order with 0 quantity";

        // Test Case 14: placeOrder - invalid quantity (exceeds MAX_QTY_PER_PRODUCT, which is 10)
        assert !manager.placeOrder("C001", "P001", 11) : "Manager Test 14 Failed: Should not place order with quantity > 10";

        // Test Case 15: placeOrder - insufficient stock
        assert !manager.placeOrder("C001", "P001", 9) : "Manager Test 15 Failed: Should not place order with insufficient stock"; // Only 8 left

        // Test Case 16: placeOrder - non-existent customer
        assert !manager.placeOrder("C999", "P001", 1) : "Manager Test 16 Failed: Should not place order for non-existent customer";

        // Test Case 17: placeOrder - non-existent product
        assert !manager.placeOrder("C001", "P999", 1) : "Manager Test 17 Failed: Should not place order for non-existent product";

        // Test Case 18: cancelOrder - valid cancellation (ORD1)
        assert manager.cancelOrder("ORD1") : "Manager Test 18 Failed: Failed to cancel ORD1";
        // Check product stock via getProduct().getStockQty() after cancellation
        assert manager.getProduct("P001").getStockQty() == 10 : "Manager Test 18 Failed: P001 stock not restored after cancellation"; // Original 10, ordered 2, cancelled, now 10 again

        // Test Case 19: cancelOrder - cancelling already cancelled order (should return false)
        assert !manager.cancelOrder("ORD1") : "Manager Test 19 Failed: Should not cancel already cancelled order ORD1";

        // Test Case 20: cancelOrder - non-existent order (should return false)
        assert !manager.cancelOrder("NONEXISTENT") : "Manager Test 20 Failed: Should not cancel non-existent order";

        // Test Case 21: searchProductsByCategory - existing category
        Product p3 = new Product("P003", "Java Book", "Books", 50.0, 30);
        manager.addProduct(p3); // Add a book to search for
        Product[] electronics = manager.searchProductsByCategory("Electronics");
        assert electronics.length == 1 : "Manager Test 21 Failed: Electronics category search failed (expected 1, got " + electronics.length + ")"; // P002 is still there
        // System.out.println(electronics[0].toString());
        // System.out.println(p1.toString());

        assert electronics[0].equals(p1) : "Manager Test 21 Failed: Electronics search content mismatch"; // Should be P002

        // Test Case 22: searchProductsByCategory - another existing category
        Product[] books = manager.searchProductsByCategory("Books");
        assert books.length == 1 : "Manager Test 22 Failed: Books category search failed";
        assert books[0].equals(p3) : "Manager Test 22 Failed: Books search content mismatch";

        // Test Case 23: searchProductsByCategory - non-existent category
        Product[] food = manager.searchProductsByCategory("Food");
        assert food.length == 0 : "Manager Test 23 Failed: Search for non-existent category should return empty array";

        // Test Case 24: toString method check (basic content presence)
        String output = manager.toString();
        System.out.println("\n--- ECommerceManager toString Output ---\n" + output);
        assert output.contains("=== Customers ===") : "Manager Test 24 Failed: Missing customers section in toString";
        assert output.contains("=== Products ===") : "Manager Test 24 Failed: Missing products section in toString";
        assert output.contains("=== Orders ===") : "Manager Test 24 Failed: Missing orders section in toString";
        assert output.contains("Customer[id=C001, name=Alice, email=alice@test.com]") : "Manager Test 24 Failed: toString missing C001";
        assert output.contains("Product[id=P001, Description=Smartphone, price=999.99, stock=10]") : "Manager Test 24 Failed: toString missing P001 or incorrect stock";
        assert output.contains("Order[id=ORD2, product=Headphones, quantity=5, date=") : "Manager Test 24 Failed: toString missing ORD2"; // ORD1 was cancelled

        System.out.println("All ECommerceManager Class Tests Passed.");
    }

    // Main method to run all tests
    public static void main(String[] args) {
        if (testAssertionEnabled()) {
            try {
                testCustomerClass();
            } catch (AssertionError ex) {
                System.out.println("Customer Class Test Failed: " + ex.getMessage());
            }

            try {
                testProductClass();
            } catch (AssertionError ex) {
                System.out.println("Product Class Test Failed: " + ex.getMessage());
            }

            try {
                testOrderClass();
            } catch (AssertionError ex) {
                System.out.println("Order Class Test Failed: " + ex.getMessage());
            }

            try {
                testECommerceManagerClass();
            } catch (AssertionError ex) {
                System.out.println("ECommerceManager Class Test Failed: " + ex.getMessage());
            }
            System.out.println("\n--- All Comprehensive Tests Attempted ---");
        }
    }
}
