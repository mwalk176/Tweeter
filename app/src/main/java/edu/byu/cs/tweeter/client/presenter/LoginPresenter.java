package edu.byu.cs.tweeter.client.presenter;

import edu.byu.cs.tweeter.client.model.service.LoginServiceProxy;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

import java.io.IOException;

public class LoginPresenter extends Presenter {

    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public LoginPresenter(View view) {
        this.view = view;
    }

    public LoginResponse loginUser(LoginRequest request) throws IOException {
        LoginServiceProxy proxy = new LoginServiceProxy();
        return proxy.loginUser(request);
    }

}
