package demo;

import singleton.DatabaseSynchronized;


public class TestSingletonSynChronized implements Runnable
{
    Thread thread;
    public static void main(String[] args) {
        TestSingletonSynChronized t = new TestSingletonSynChronized();
    }
    
    public TestSingletonSynChronized() {
        DatabaseSynchronized database;
        database = DatabaseSynchronized.getInstance("products");
        
        thread = new Thread(this, "second");
        thread.start();
        System.out.println("this is the " + database.getName() + " database" );
        
    }
    public void run() {
        DatabaseSynchronized database = DatabaseSynchronized.getInstance("employee");
        System.out.println("this is the " + database.getName() + " database" );
    }
}
