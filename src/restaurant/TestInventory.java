
package restaurant;

import java.util.List;



public class TestInventory {

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        try {
            Food pizza = new Food("Pizza", 500, 20,"fastfood");
            Food burger = new Food("Burger", 200, 15,"fastfood");
            Food pasta = new Food("Pasta", 300, 10,"fastfood");
            //Food ramen = new Food("Ramen", -300, 10);
            inventory.addFood(pizza);
            inventory.addFood(burger);
            inventory.addFood(pasta);
            //inventory.addFood(ramen);
       

        System.out.println("Showing all foods in the inventory:");
        inventory.displayFoods(inventory.getFoods());

        // Removing items which is present in inventory.
        System.out.println("\nRemoving Burger by ID : ");
        if (inventory.removeFoodById(burger.getFoodId())) {
            System.out.println("Burger removed successfully.");
        } else {
            System.out.println("Burger not found.");
        }

        // Removing items which is not present in inventory.
        System.out.println("\nRemoving Burger by ID : ");
        if (inventory.removeFoodById(burger.getFoodId())) {
            System.out.println("Burger removed successfully.");
        } else {
            System.out.println("Burger not found.");
        }

        // All foods after removing a food item.
        System.out.println("\nInventory after removing Burger:");
        inventory.displayFoods(inventory.getFoods());

        // Find food by name which exists.
        System.out.println("\nFinding food by name : Pizza. ");
        Food f1 = inventory.findFoodByName("Pizza");
        if (f1 != null) {
            System.out.println("Found: " + f1);
        } else {
            System.out.println("Food not found.");
        }

        // Find food by name which not exists.
        System.out.println("\nFinding food by name : Burger. ");
        Food f2 = inventory.findFoodByName("Burger");
        if (f2 != null) {
            System.out.println("Found: " + f2);
        } else {
            System.out.println("Food not found.");
        }

        // Updating food quantity for valid input.  
        System.out.println("\nUpdating quantity for Pizza to 30.");
        if (inventory.updateFoodQuantity(pizza.getFoodId(), 30)) {
            System.out.println("Quantity updated successfully.");
        } else {
            System.out.println("Failed to update quantity.");
        }

        // Updating food quantity for negative input.
        System.out.println("\nUpdating quantity for Pizza to -30.");
        if (inventory.updateFoodQuantity(pizza.getFoodId(), -30)) {
            System.out.println("Quantity updated successfully.");
        } else {
            System.out.println("Failed to update quantity.");
        }

        // All foods after quantity update
        System.out.println("\nInventory after updating quantity:");
        inventory.displayFoods(inventory.getFoods());

        // Get foods in a price range
        System.out.println("\nFoods in price range frpom 100 to 300 Taka:");
        List<Food> foodsInRange = inventory.getFoodsByPriceRange(100, 300);
        inventory.displayFoods(foodsInRange);

        // Total inventory value
        System.out.println("\nTotal Inventory Value: $" + inventory.getTotalInventoryValue());

        // Check food availability
        System.out.println("\nIs 25 Pizza available?");
        if (inventory.checkFoodAvailability(pizza.getFoodId(), 25)) {
            System.out.println("Pizza is available.");
        } else {
            System.out.println("Pizza is not available.");
        }

        System.out.println("\nIs 25 pasta available?");
        if (inventory.checkFoodAvailability(pasta.getFoodId(), 25)) {
            System.out.println("Pizza is available.");
        } else {
            System.out.println("Pizza is not available.");
        }

        // Get unassigned food IDs
        System.out.println("\nUnassigned Food IDs:");
        List<Integer> unassignedIds = inventory.getUnassignedFoodIds(10);
        System.out.println(unassignedIds);
        
         } catch (IllegalArgumentException e) {
            System.out.println("Quantity and Price cannot be negative.");
        }
    }

}

