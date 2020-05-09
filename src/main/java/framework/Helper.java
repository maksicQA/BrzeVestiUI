package framework;

public class Helper {

    public static int getRandomInteger(int range) {
        return (int) Math.random() * range;
    }
    
    public static String getRandomText() {
        return "nenad-" + (int) (Math.random() * 1000000);
    }
    
    
}
