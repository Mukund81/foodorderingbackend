import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static User currentuser = null;
    static int userid = 0;
    static int orderid = 0;
    static int restaurantId = 0;
    static int fooditemid = 3;
    static ArrayList<Order> orders = new ArrayList<>();
    static ArrayList<Restaurant> restaurants = new ArrayList<>();

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

    public static void adminactions() {
        System.out.println("Welcome Admin!!");
        while (true) {
            System.out.println("choose the id of action you want to perform or -1 if you want to exit");
            System.out.println("1. Add Restaurant\n" +
                    "2. Add Food Item\n" +
                    "3. Update Food Price\n" +
                    "4. Remove Food Item\n" +
                    "5. Exit");
            int choice = sc.nextInt();
            if (choice == -1) return;
            switch (choice) {
                case 1:
                    System.out.println("please enter the name,location seperated by space ");
                    String restaurantName = sc.next();
                    System.out.println("enter locaton");
                    String location = sc.next();
                    Restaurant newRestaurant = new Restaurant(++restaurantId, restaurantName, location, new ArrayList<>());
                    restaurants.add(newRestaurant);
                    System.out.println("New restaurant has been added");
                    break;
                case 2:
                    System.out.println("please select the restaurant to add fooditem to its menu by selecting the id");
                    for (Restaurant r : restaurants) {
                        System.out.println(r.getId() + " " + r.getName());
                    }
                    int sid = sc.nextInt();
                    Restaurant selectedRestaurant = null;
                    for (Restaurant r : restaurants) {
                        if (r.getId() == sid) {
                            selectedRestaurant = r;
                        }
                    }
                    if (selectedRestaurant == null) {
                        System.out.println("enter valid ID");
                        break;
                    }
                    System.out.println("enter the name,price seperated by space");
                    String ItemName = sc.next();
                    double ItemPrice = sc.nextDouble();
                    Fooditem newFood = new Fooditem(++fooditemid, ItemName, ItemPrice, true);
                    selectedRestaurant.getMenu().add(newFood);
                    break;
                case 3:
                    System.out.println("please select the restaurant to update itemprice");
                    for (Restaurant r : restaurants) {
                        System.out.println(r.getId() + " " + r.getName());
                    }
                    int rid = sc.nextInt();
                    Restaurant choosenRestaurant = restaurants.get(rid - 1);
                    System.out.println("select the id of item you want to update");
                    for (Fooditem f : choosenRestaurant.getMenu()) {
                        System.out.println(f.getId() + " " + f.getName());
                    }
                    int fid = sc.nextInt();
                    ArrayList<Fooditem> selectedfoodmenu = choosenRestaurant.getMenu();
                    for (Fooditem f : selectedfoodmenu) {
                        if (f.getId() == fid) {
                            System.out.println("current price " + f.getPrice() + " enter the new price");
                            double newprice = sc.nextDouble();
                            f.setPrice(newprice);
                        }
                    }
                    break;
                case 4:
                    System.out.println("please select the restaurant to delete fooditem");
                    for (Restaurant r : restaurants) {
                        System.out.println(r.getId() + " " + r.getName());
                    }
                    int resid = sc.nextInt();
                    Restaurant choosenRestaurant1 = null;
                    for (Restaurant r : restaurants) {
                        if (r.getId() == resid) {
                            choosenRestaurant1 = r;
                        }
                    }
                    System.out.println("select the id of item you want to delete");
                    for (Fooditem f : choosenRestaurant1.getMenu()) {
                        System.out.println(f.getId() + " " + f.getName());
                    }
                    int fid1 = sc.nextInt();
                    ArrayList<Fooditem> selectedfoodmenu1 = choosenRestaurant1.getMenu();
                    Iterator<Fooditem> it = selectedfoodmenu1.iterator();

                    while (it.hasNext()) {
                        Fooditem f = it.next();

                        if (f.getId() == fid1) {
                            it.remove();
                            break;
                        }
                    }
                case 5:
                    return;
                default:
                    System.out.println("enter valid option");
                    break;
            }
        }
    }

    public static void customeractions() {
        displayRestaurants(restaurants);
        System.out.println("Enter the id of the restaurant you like to order from enter -1 if you want to exit");
        int userchoice = 0;
        while (true) {
            userchoice = sc.nextInt();
            if (userchoice == -1) return;
            if (userchoice >= 1 && userchoice <= restaurants.size()) {
                displayMenu(restaurants.get(userchoice - 1));
                break;
            } else {
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
            if (id >= 1 && id <= (selectedrestaurant.getMenu().size())) {
                useritemlist.add(id);
            } else {
                System.out.println("you have entered a  InValid id please input a correct one");
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
        if (OrderChoice.equalsIgnoreCase("Yes")) {
            System.out.println("Your order is placed");
            ++orderid;
            Order currentorder = new Order(orderid, currentuser, Cart, total, OrderStatus.PLACED);
            orders.add(currentorder);
        }
    }

    public static void main(String[] args) {

        Fooditem biryani = new Fooditem(1, "chicken biryani", 200.00, true);
        Fooditem starter = new Fooditem(2, "chicken manchuriya", 250.00, true);
        Fooditem dessert = new Fooditem(3, "gulab jamun", 250.00, true);
        ArrayList<Fooditem> mehfilmenu = new ArrayList<>();
        mehfilmenu.add(biryani);
        mehfilmenu.add(starter);
        mehfilmenu.add(dessert);
        Restaurant mehfil = new Restaurant(++restaurantId, "Mehfil", "Dilsukhnagar", mehfilmenu);
        Fooditem specialbiryani = new Fooditem(1, "special biryani", 200.00, true);
        Fooditem mutton_ghost = new Fooditem(2, "mutton ghost", 250.00, true);
        Fooditem haleem = new Fooditem(3, "haleem", 250.00, true);
        ArrayList<Fooditem> luckysmenu = new ArrayList<>();
        luckysmenu.add(specialbiryani);
        luckysmenu.add(mutton_ghost);
        luckysmenu.add(haleem);
        Restaurant luckys = new Restaurant(++restaurantId, "luckys", "Kukatpally", luckysmenu);
        User Mukund = new User(++userid, "Mukund", "Mukund81", "12345", Role.USER);
        User admin = new User(++userid, "admin", "Admin", "admin123", Role.ADMIN);
        ArrayList<User> users = new ArrayList<>();
        users.add(Mukund);
        users.add(admin);

        restaurants.add(mehfil);
        restaurants.add(luckys);
        System.out.println("Welcome to fooddelivery");
        while (true) {
            System.out.println("please choose one option:\n1.Register \n2.Login or -1 to exit");
            int option = sc.nextInt();
            if (option == -1) return;
            if (option == 1) {
                System.out.println("Enter username and password seperated by space ");
                while (true) {
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
                    if (flag) {
                        System.out.println("please enter your name");
                        String name = sc.next();
                        currentuser = new User(++userid, name, username, password, Role.USER);
                        users.add(currentuser);
                        System.out.println("Registration successfull!!");
                        break;
                    }
                }
                break;
            } else if (option == 2) {
                System.out.println("Enter your new username and passowrd");
                while (true) {
                    boolean flag = false;
                    String newusername = sc.next();
                    String newpassword = sc.next();
                    for (User x : users) {
                        if (newusername.equalsIgnoreCase(x.getUsername()) && newpassword.equalsIgnoreCase(x.getPassword())) {
                            flag = true;
                            System.out.println("Welcome back!! " + newusername);
                            currentuser = x;
                            break;
                        }
                    }
                    if (!flag) System.out.println("Enter valid username and password");
                    if (flag) {
                        break;
                    }
                }
            } else {
                System.out.println("enter valid option");
            }
        }
        if (currentuser.getRole() == Role.ADMIN) {
            adminactions();
        } else {
            customeractions();
        }
    }
}
