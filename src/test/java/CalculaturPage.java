import org.openqa.selenium.By;

public class CalculaturPage {
    public static void pressOne(){
        DriverSingleton.getDriverInstance().findElement(By.id("one")).click();
    }

    public static void pressZero(){
        DriverSingleton.getDriverInstance().findElement(By.id("zero")).click();
    }

    public static void pressFour(){
        DriverSingleton.getDriverInstance().findElement(By.id("four")).click();
    }

    public static void pressDivide(){
        DriverSingleton.getDriverInstance().findElement(By.id("divide")).click();
    }

    public static void pressEqual(){
        DriverSingleton.getDriverInstance().findElement(By.id("equal")).click();
    }

    public static String getAnswer(){
        return DriverSingleton.getDriverInstance().findElement(By.id("screen")).getText();
    }
}
