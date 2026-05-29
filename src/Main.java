import java.util.*;

public class Main {
    public static void displayRestaurants(ArrayList<Restaurant> restaurants) {
        for (Restaurant r : restaurants) {
            System.out.println(r.getId() + ". " + r.getName());
        }
    }

    public static void displayMenu(Restaurant restaurant) {
        ArrayList<Fooditem> menu = restaurant.getMenu();
        System.out.println("This is the menu of the restaurant you want to order from:");
        for (Fooditem f : menu) {
            System.out.println(f.getId() + ". " + f.getName() + " - " + f.getPrice());
        }
    }

    public static double displayCartTotal(ArrayList<Fooditem> Cart) {
        double total = 0;
        System.out.println("Your Cart:");
        for (Fooditem f : Cart) {

            System.out.println(f.getId() + ". " + f.getName() + "- " + f.getPrice());
            total += f.getPrice();
        }
        System.out.println("Total Price - " + total);
        return total;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User currentuser = null;
        int userid=0;
        int orderid=0;
        ArrayList<Order> orders = new ArrayList<>();
        Fooditem biryani = new Fooditem(1, "chicken biryani", 200.00, true);
        Fooditem starter = new Fooditem(2, "chicken manchuriya", 250.00, true);
        Fooditem dessert = new Fooditem(3, "gulab jamun", 250.00, true);
        ArrayList<Fooditem> mehfilmenu = new ArrayList<>();
        mehfilmenu.add(biryani);
        mehfilmenu.add(starter);
        mehfilmenu.add(dessert);
        Restaurant mehfil = new Restaurant(1, "Mehfil", "Dilsukhnagar", mehfilmenu);
        Fooditem specialbiryani = new Fooditem(1, "special biryani", 200.00, true);
        Fooditem mutton_ghost = new Fooditem(2, "mutton ghost", 250.00, true);
        Fooditem haleem = new Fooditem(3, "haleem", 250.00, true);
        ArrayList<Fooditem> luckysmenu = new ArrayList<>();
        luckysmenu.add(specialbiryani);
        luckysmenu.add(mutton_ghost);
        luckysmenu.add(haleem);
        Restaurant luckys = new Restaurant(2, "luckys", "Kukatpally", luckysmenu);
        User Mukund = new User(++userid, "Mukund", "Mukund81", "12345", Role.CUSTOMER);
        User admin = new User(++userid,"admin","Admin","admin123",Role.ADMIN);
        ArrayList<User> users = new ArrayList<>();
        users.add(Mukund);
        users.add(admin);
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(mehfil);
        restaurants.add(luckys);
        System.out.println("Welcome to fooddelivery");
        while(true) {
            System.out.printf("please choose one option:\n1.Register \n2.Login or -1 to exit");
            int option = sc.nextInt();
            if(option == -1) return;
            if (option == 1) {
                System.out.println("Enter username and password seperated by space ");
                while(true) {
                    //Register logic
                    String username = sc.next();
                    String password = sc.next();
                    boolean flag = true;
                    for (User x : users) {
                        if (username.equalsIgnoreCase(x.getUsername())) {
                            flag = false;
                            System.out.println("the username entered already exists try to enter the username and password again");
                            break;
                        }
                    }
                    if(flag){
                        System.out.println("please enter your name");
                        String name = sc.next();
                        currentuser = new User(++userid,name,username,password,Role.CUSTOMER);
                        users.add(currentuser);
                        break;
                    }
                }
                break;
            } else if(option==2){
                System.out.println("Enter your new username and passowrd");
                while(true){
                    boolean flag = false;
                    String newusername = sc.next();
                    String newpassword = sc.next();
                    for(User x : users){
                        if(newusername.equalsIgnoreCase(x.getUsername())&&newpassword.equalsIgnoreCase(x.getPassword())) {
                            flag = true;
                            System.out.println("Welcome back!! " + newusername);
                            currentuser = x;
                            break;
                        }
                    }
                    if(!flag) System.out.println("Enter valid username and password");
                    if(flag){
                        break;
                    }
                }
            }
            else{
                System.out.println("enter valid option");
            }
        }
        if(currentuser.getRole()==Role.ADMIN){
            
        }
        displayRestaurants(restaurants);
        System.out.println("Enter the id of the restaurant you like to order from enter -1 if you want to exit");
        int userchoice=0;
        while(true){
            userchoice = sc.nextInt();
            if(userchoice==-1) return;
            if (userchoice >= 1 && userchoice <= restaurants.size()) {
                displayMenu(restaurants.get(userchoice - 1));
                break;
            }
            else {
                System.out.println("Invalid choice enter a correct id");
            }
        }
        Restaurant selectedrestaurant = restaurants.get(userchoice - 1);
        ArrayList<Fooditem> Cart = new ArrayList<>();
        System.out.println("Enter the ids of the food item you want and input -1 if you want to stop");
        ArrayList<Integer> useritemlist = new ArrayList<>();
        while (true) {
            int id = sc.nextInt();
            if (id == -1) break;
            if(id >= 1 && id <= (selectedrestaurant.getMenu().size())){
                useritemlist.add(id);
            }
            else{
                System.out.println("you have entered a  InValid id please input a correct one");
                continue;
            }

        }
        for (int useritem : useritemlist) {
                ArrayList<Fooditem> menu = selectedrestaurant.getMenu();
                for (Fooditem f : menu) {
                    if (f.getId() == useritem) {
                        Cart.add(f);
                    }
                }
            }
        double total = displayCartTotal(Cart);
        System.out.println("Do you want to place the order??");
        String OrderChoice = sc.next();
        if(OrderChoice.equalsIgnoreCase("Yes")){
            System.out.println("Your order is placed");
            ++orderid;
            Order currentorder = new Order(orderid,currentuser,Cart,total,OrderStatus.PLACED);
            orders.add(currentorder);
        }
    }
}
