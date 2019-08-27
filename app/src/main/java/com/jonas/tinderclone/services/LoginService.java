package com.jonas.tinderclone.services;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginService {

    private Activity activity;
    private FirebaseAuth mAuth;

    public LoginService(Activity activity) {
        this.activity = activity;
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void signInUser(String email, String password, final IResponseListener listener){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    listener.onSuccess();
                }else {
                    String errorMessage = task.getException().getMessage();
                    listener.onFailure(errorMessage);
                }
            }
        });
    }
}
