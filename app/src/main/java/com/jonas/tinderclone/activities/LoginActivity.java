package com.jonas.tinderclone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jonas.tinderclone.R;
import com.jonas.tinderclone.services.IResponseListener;
import com.jonas.tinderclone.services.LoginService;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class LoginActivity extends BaseActivity {

    @InjectView(R.id.btnLogin)
    Button btnLogin;
    @InjectView(R.id.edtEmail)
    EditText edtEmail;
    @InjectView(R.id.password)
    EditText edtPassword;
    @InjectView(R.id.tvLoginErrorMessage)
    TextView tvLoginErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.inject(this);
        initProperties();
    }

    @Override
    public void initProperties() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigInUser();
            }
        });
    }

    private void sigInUser() {
        LoginService loginService = new LoginService(LoginActivity.this);
        loginService.signInUser(edtEmail.getText().toString(), edtPassword.getText().toString(), new IResponseListener() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                tvLoginErrorMessage.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String errorMessage) {
                tvLoginErrorMessage.setText(errorMessage);
                tvLoginErrorMessage.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void configureActionBar() {}
}
