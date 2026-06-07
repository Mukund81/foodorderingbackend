import java.util.ArrayList;

public class Restaurant {
    private int id;
    private String name;
    private String location;
    private ArrayList<Fooditem> menu;
    public Restaurant(int id,String name,String location,ArrayList<Fooditem> menu){
        this.id = id;
        this.name = name;
        this.location = location;
        this.menu = menu;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ArrayList<Fooditem> getMenu() {
        return menu;
    }

    public void setMenu(ArrayList<Fooditem> menu) {
        this.menu = menu;
    }
}
