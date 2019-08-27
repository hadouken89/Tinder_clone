package com.jonas.tinderclone.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.jonas.tinderclone.R;
import com.jonas.tinderclone.services.IResponseListener;
import com.jonas.tinderclone.services.RegisterService;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegistrationActivity extends BaseActivity {

    @InjectView(R.id.edtEmail)
    EditText edtEmail;
    @InjectView(R.id.password)
    EditText edtPassword;
    @InjectView(R.id.tvErrorMessage)
    TextView tvErrorMessage;
    @InjectView(R.id.btnSubmit)
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ButterKnife.inject(this);
        initProperties();
    }

    @Override
    public void initProperties() {

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUserByEmail();
            }
        });
    }

    @Override
    public void configureActionBar() {}

    private void registerUserByEmail() {
        RegisterService registerService = new RegisterService(RegistrationActivity.this);
        registerService.registerUserEmailAccount(edtEmail.getText().toString(), edtPassword.getText().toString(), new IResponseListener() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                tvErrorMessage.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String errorMessage) {
                tvErrorMessage.setText(errorMessage);
                tvErrorMessage.setVisibility(View.VISIBLE);
            }
        });
    }



}
