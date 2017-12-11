import static spark.Spark.get;
import static spark.Spark.port;

public class Klix implements Runnable {
    public void run() {
        String portValue = System.getProperty("PORT");
        if (portValue == null || portValue.equals("")){
            portValue = "443";
        }
        int port = Integer.valueOf(System.getProperty("PORT"));

        port(port);
        get("/hello", (req, res) -> "Hello World");
    }

}
