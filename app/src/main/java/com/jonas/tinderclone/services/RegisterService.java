package com.jonas.tinderclone.services;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jonas.tinderclone.models.User;

public class RegisterService {

    private Activity activity;
    private FirebaseAuth mAuth;

    public RegisterService(Activity activity) {
        this.activity = activity;
        this.mAuth = FirebaseAuth.getInstance();
    }

    public void registerUserEmailAccount(final User userData, final IResponseListener listener) {

        final String email = userData.getUserCredentials().getEmail();
        final String password = userData.getUserCredentials().getPassword();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                            String id = databaseReference.push().getKey();
                            databaseReference.child(id).setValue(userData);

                            listener.onSuccess();

                        } else {
                            String errorMessage = task.getException().getMessage();
                            listener.onFailure(errorMessage);
                        }
                    }

                });

    }

}
