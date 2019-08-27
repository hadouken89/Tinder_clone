package com.jonas.tinderclone.services;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterService {

    private Activity activity;
    private FirebaseAuth mAuth;

    public RegisterService(Activity activity) {
        this.activity = activity;
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void registerUserEmailAccount(String email, String password, final IResponseListener listener ){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String toastMessage = task.isSuccessful() ? "Successful registration!" : "Error in registration";

                        Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG);
                        if(task.isSuccessful() ){
                            listener.onSuccess();
                        }
                        else{
                            String errorMessage = task.getException().getMessage();
                            listener.onFailure(errorMessage);
                        }

                    }
                });

    //    mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
    //        @Override
    //        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
    //            final FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
    //            if(user != null )
    //                listener.onSuccess();
    //            else
    //                listener.onFailure();
    //        }
    //    });

    }


}
