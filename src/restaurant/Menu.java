package restaurant;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    protected List<Food> foodItem;
    String name;
    LocalTime Start;
    LocalTime End;

    public Menu(String name, LocalTime Start, LocalTime End) {
        this.name = name;
        this.foodItem = new ArrayList<>();
        this.Start = Start;
        this.End = End;
    }

    public Menu(String name) {
        this.name = name;
        this.foodItem = new ArrayList<>();
    }

    public void addFood(Food food) {
        foodItem.add(food);
    }

    public void showMenu() {
        System.out.println(name.toUpperCase());
        for (Food item : foodItem) {
            System.out.println(item.getFoodId() + ". " + item.getName() + " - " + item.getPrice() + "tk");
        }
        System.out.println("----------------------------");
    }

    public Food getMenuItemByName(String name) {
        for (Food item : foodItem) {
            if (item.getName().equalsIgnoreCase(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean showMenuTime() {

        ZoneId dhaka = ZoneId.of("Asia/Dhaka");
        ZonedDateTime currentTimeInDhaka = ZonedDateTime.now(dhaka);
        LocalTime currentLocalTime = currentTimeInDhaka.toLocalTime();

        if (Start == null || End == null) {
            return true;
        }

        if ((currentLocalTime.isAfter(Start) || currentLocalTime.equals(Start)) && currentLocalTime.isBefore(End)) {
            return true;
        }

        return false;

    }


    public static void showFoodsByMenuTime(Inventory inventory) {

        Menu breakfast = new Menu("Breakfast", LocalTime.of(8, 0), LocalTime.of(12, 0));
        Menu lunch = new Menu("Lunch", LocalTime.of(12, 0), LocalTime.of(17, 0));
        Menu dinner = new Menu("Dinner", LocalTime.of(17, 0), LocalTime.of(23, 59));
        Menu fastFood = new Menu("Fast Food");
        Menu dessert = new Menu("Dessert");
        Menu beverages = new Menu("Beverages");

        for (Food food : inventory.getFoods()) {
            switch (food.getType().toLowerCase()) {
                case "breakfast":
                    breakfast.addFood(food);
                    break;
                case "lunch":
                    lunch.addFood(food);
                    break;
                case "dinner":
                    dinner.addFood(food);
                    break;
                case "fast food":
                    fastFood.addFood(food);
                    break;
                case "dessert":
                    dessert.addFood(food);
                    break;
                case "beverages":
                    beverages.addFood(food);
                    break;
                default:
                    System.out.println("Unrecognized food type: " + food.getType());
            }
        }

        System.out.println("\n--- Available Menus ---");
        if (breakfast.showMenuTime()) {
            breakfast.showMenu();
        }
        if (lunch.showMenuTime()) {
            lunch.showMenu();
        }
        if (dinner.showMenuTime()) {
            dinner.showMenu();
        }
        fastFood.showMenu();
        dessert.showMenu();
        beverages.showMenu();
    }

}
