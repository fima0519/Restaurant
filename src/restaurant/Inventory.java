package restaurant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Food> foods;

    public Inventory() {
        this.foods = new ArrayList<>();
    }

    public void addFood(Food food) {
        this.foods.add(food);
    }

    public boolean removeFoodById(int foodId) {
        for (Food food : foods) {
            if (food.getFoodId() == foodId) {
                foods.remove(food);
                return true;
            }
        }
        return false;
    }

    public boolean removeFoodByName(String foodName) {
        for (Food food : foods) {
            if (food.getName() == foodName) {
                foods.remove(food);
                return true;
            }
        }
        return false;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public Food findFoodById(int foodId) {
        for (Food food : foods) {
            if (food.getFoodId() == foodId) {
                return food;
            }
        }
        return null;
    }

    public Food findFoodByName(String name) {
        for (Food food : foods) {
            if (food.getName().equalsIgnoreCase(name)) {
                return food;
            }
        }
        return null;
    }

    public List<Food> getFoodsByPriceRange(double minPrice, double maxPrice) {
        List<Food> foodsInRange = new ArrayList<>();
        for (Food food : foods) {
            if (food.getPrice() >= minPrice && food.getPrice() <= maxPrice) {
                foodsInRange.add(food);
            }
        }
        return foodsInRange;
    }

    public double getTotalInventoryValue() {
        double totalValue = 0.0;
        for (Food food : foods) {
            totalValue += food.getPrice() * food.getQuantity();
        }
        return totalValue;
    }

    public boolean checkFoodAvailability(int foodId, int quantity) {
        for (Food food : foods) {
            if (food.getFoodId() == foodId && food.getQuantity() >= quantity) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean updateFoodQuantity(int foodId, int newQuantity) {
        for (Food food : foods) {
            if (food.getFoodId() == foodId) {
                try {
                    food.setQuantity(newQuantity);
                    return true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Quantity cannot be negative.");
                }

            }
        }
        return false;
    }

    public List<Integer> getUnassignedFoodIds(int maxId) {
        List<Integer> assignedIds = new ArrayList<>();
        for (Food food : foods) {
            assignedIds.add(food.getFoodId());
        }
        List<Integer> unassignedIds = new ArrayList<>();
        for (int i = 1; i <= maxId; i++) {
            if (!assignedIds.contains(i)) {
                unassignedIds.add(i);
            }
        }
        return unassignedIds;
    }

    public void displayFoods(List<Food> foods) {
        if (foods.isEmpty()) {
            System.out.println("No food items in the inventory.");
        } else {
            for (Food food : foods) {
                System.out.println(food);
            }
        }
    }

    public void addFoodsFromFile(String filePath) {
        try ( BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try {
                        String name = parts[0].trim();
                        double price = Double.parseDouble(parts[1].trim());
                        int quantity = Integer.parseInt(parts[2].trim());
                        String type = parts[3].trim();
                        addFood(new Food(name, price, quantity, type));
                        //System.out.println("Food added.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error parsing line: " + line + ". Skipping.");
                    }
                } else {
                    System.out.println("Invalid format: " + line + ". Skipping.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

}
