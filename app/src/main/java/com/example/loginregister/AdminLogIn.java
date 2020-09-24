package com.example.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class AdminLogIn extends AppCompatActivity {

    TextInputEditText textInputEditTextusername, textInputEditTextPassword ;
    Button buttonLogin;
    TextView textviewSignUp;
    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_log_in);

        textInputEditTextusername = findViewById(R.id.username);
        textInputEditTextPassword = findViewById(R.id.password);

        buttonLogin = findViewById(R.id.buttonLogin);
        textviewSignUp = findViewById(R.id.signUpText);
        progressbar = findViewById(R.id.progress);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username,password;
                username = String.valueOf(textInputEditTextusername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());

                if( !username.equals("") && !password.equals("") ) {
                    progressbar.setVisibility(View.VISIBLE);

                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {


                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "password";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = username;
                            data[1] = password;
                            PutData putData = new PutData(URL.getURL()+ "LoginRegister/loginAdmin.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressbar.setVisibility(View.GONE);
                                    String result = putData.getResult();

                                    if(result.equals("Login Success")){
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                        Intent intent = new Intent(getApplicationContext(),SignUp.class);
                                        startActivity(intent);
                                        finish();;

                                    }else
                                    {
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();

                                    }

                                    Log.i("PutData", result);
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                else {
                    Toast.makeText(getApplicationContext(),"All filds are required", Toast.LENGTH_SHORT).show();

                }


            }
        });
    }
}