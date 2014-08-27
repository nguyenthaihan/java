package singleton;

public class Database
{
    private int record;
    private String name;
    private static Database singleObject;

    private Database (String n) {
        name = n;
        record = 0;
    }
    
    public static Database getInstance(String n) {
        if(singleObject == null){
            singleObject = new Database(n);
        }
        return singleObject;
    }
    
    public void editRecord (String operation) {
        System.out.println("Performing a " + operation + " operation on record " + record + " in database " + name);
    }
    
    public String getName() {
        return name;
    }
}
