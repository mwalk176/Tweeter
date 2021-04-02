package edu.byu.cs.tweeter.client.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.view.asyncTasks.LoginTask;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

public class LoginActivity extends AppCompatActivity implements LoginTask.LoginObserver {

    final LoginTask.LoginObserver observer = this;

    EditText aliasField, passwordField;
    Button loginButton;
    TextView registerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        aliasField = (EditText) findViewById(R.id.aliasField);
        passwordField = (EditText) findViewById(R.id.passwordField);

        loginButton = (Button) findViewById(R.id.loginButton);

        registerText = (TextView) findViewById(R.id.registerQuestion);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = aliasField.getText().toString();
                String password = passwordField.getText().toString();


                Toast.makeText(getApplicationContext(), "Signing in....", Toast.LENGTH_SHORT).show();
                LoginRequest request = new LoginRequest(username, password);
                LoginTask loginTask = new LoginTask(observer);
                loginTask.execute(request);
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("clicked register");
                Intent intent = new Intent(v.getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void loginSuccessful(LoginResponse response) {
        System.out.println("login successful!");
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    @Override
    public void loginFailure(LoginResponse response) {
        Toast.makeText(getApplicationContext(), "Login Failed, " + response.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
