import java.util.*;

enum OrderStatus{
    PLACED,
    PREPARING,
    OUT_FOR_DELIVERY,
    DELIVERED
}
public class Order {
    private int id;
    private User customer;
    private ArrayList<Fooditem> items;
    private double totalprice;
    OrderStatus status;
    public Order(int id,User customer,ArrayList<Fooditem> items,double totalprice,OrderStatus status){
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.totalprice = totalprice;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public ArrayList<Fooditem> getItems() {
        return items;
    }

    public void setItems(ArrayList<Fooditem> item) {
        this.items = item;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
