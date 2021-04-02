package edu.byu.cs.tweeter.client.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.client.view.asyncTasks.RegisterTask;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;

public class RegisterActivity extends AppCompatActivity implements RegisterTask.RegisterObserver {

    final RegisterTask.RegisterObserver observer = this;

    EditText firstNameField, lastNameField, aliasField, passwordField;
    Button registerButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        firstNameField = (EditText) findViewById(R.id.firstNameField);
        lastNameField = (EditText) findViewById(R.id.lastNameField);
        aliasField = (EditText) findViewById(R.id.registerAliasField);
        passwordField = (EditText) findViewById(R.id.registerPasswordField);

        registerButton = (Button) findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameField.getText().toString();
                String lastName = lastNameField.getText().toString();
                String alias = aliasField.getText().toString();
                String password = passwordField.getText().toString();

                Toast.makeText(getApplicationContext(), "Registering....", Toast.LENGTH_SHORT).show();
                RegisterRequest request = new RegisterRequest(firstName, lastName, alias, password);
                RegisterTask registerTask = new RegisterTask(observer);
                registerTask.execute(request);


            }
        });

    }

    @Override
    public void registerSuccessful(RegisterResponse response) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void registerFailure(RegisterResponse response) {
        Toast.makeText(getApplicationContext(), "Register failed, " + response.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
