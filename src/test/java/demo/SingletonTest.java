package demo;

import singleton.Database;

public class SingletonTest
{
    public static void main(String[] args) {
        Database database;
        database = Database.getInstance("products");
        System.out.println("This is the " + database.getName() + " database.");
        
        database = Database.getInstance("employee");
        System.out.println("This is the " + database.getName() + " database.");
    }
}
