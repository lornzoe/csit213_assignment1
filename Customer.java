/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class Customer {
    private String id;
    private String name;
    private String email;
    
    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    
    public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Customer))
            return false;
        if (this == obj)
            return true;
        Customer customer = (Customer)obj;
        return this.id.equals(customer.id);
    }
    
    public String toString() {
        return "Customer[id=" + this.id 
                + ", name=" + this.name
                + ", email=" + this.email + "]";
    }
}
