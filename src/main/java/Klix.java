import static spark.Spark.get;

public class Klix implements Runnable {
    public void run() {
        get("/hello", (req, res) -> "Hello World");
    }

}
