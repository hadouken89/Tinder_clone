package com.jonas.tinderclone.services;

import android.support.annotation.NonNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterService {

    private FirebaseAuth mAuth;

    public RegisterService() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void registerUserEmailAccount(String email, String password, final IResponseListener listener ){

        mAuth.createUserWithEmailAndPassword(email,password);

       // .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
       //     @Override
       //     public void onComplete(@NonNull Task<AuthResult> task) {
       //         String toastMessage = task.isSuccessful() ? "Successful registration!" : "Error in registration";

       //         Toast.makeText(context, toastMessage, Toast.LENGTH_LONG);
       //     }
       // });

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
                if(user != null )
                    listener.onSuccess();
                else
                    listener.onFailure();
            }
        });

    }


}
