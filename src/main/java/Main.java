import com.induccion.cow.endpoint.RestEndpoint;

public class Main {

    public static void main(final String[] args) throws Exception {
        // Initialize the application
        new RestEndpoint().init();

        System.out.println("App is running");
    }
}
