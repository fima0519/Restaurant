package restaurant;

class Food {

    private static int nextFoodId = 1;
    private int foodId;
    private String name;
    private double price;
    private int quantity;
    private String type; 

    public Food(String name, double price, int quantity, String type) {
        this.foodId = nextFoodId++;
        this.name = name;
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.type = type;
    }

    public int getFoodId() {
        return foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price >= 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Food ID: " + foodId +
                ", Name: " + name +
                ", Price: " + price +
                ", Quantity: " + quantity +
                ", Type: " + type;
    }
}
