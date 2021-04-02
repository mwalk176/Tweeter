package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.RegisterServiceProxy;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

import java.io.IOException;

public class RegisterPresenter extends Presenter{

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public RegisterPresenter(View view) {
        this.view = view;
    }

    public RegisterResponse registerUser(RegisterRequest request) throws IOException {
        RegisterServiceProxy proxy = new RegisterServiceProxy();
        return proxy.registerUser(request);
    }


}


