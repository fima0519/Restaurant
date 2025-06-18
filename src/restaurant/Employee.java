
package restaurant;

public class Employee extends Person {
    private static int nextId = 1;
    private int Id;
    private String jobTitle; 
    private double salary;

    public Employee(String name, String contact, String jobTitle, double salary) {
        super(name, contact);
        this.Id = nextId++;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    @Override
    public void displayInfo() {
        System.out.println( ", Name: " + getName() +
                           ", Contact: " + getContact() + ", Job Title: " + jobTitle +
                           ", Salary: " + salary);
    }
    
     public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }
    

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
