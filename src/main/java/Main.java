import com.induccion.cow.endpoint.RestEndpoint;
import com.induccion.cow.utils.EndpointUtils;
import com.mercadopago.exceptions.MPException;

public class Main {

    public static void main(final String[] args) throws MPException {
        EndpointUtils.setUserCredentials();

        // Initialize the application
        new RestEndpoint().init();

        System.out.println("App is running");
    }
}
