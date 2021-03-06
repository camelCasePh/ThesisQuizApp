package com.trialProjects.test100;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.trialProjects.test100.activities.Registration;

public class IntroPage extends AppCompatActivity {

    FirebaseAuth app_auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);

        app_auth = FirebaseAuth.getInstance();
        DbQuery.app_fireStore = FirebaseFirestore.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //if there is a logged in user, it will be directed to the homepage
                if(app_auth.getCurrentUser() != null){
                    signedInUser();
                }else{
                    //if not, straight to registration page
                    startActivity(new Intent(getApplicationContext(), Registration.class));
                    finish();
                }
            }
        }, 3000);
    }

    public void signedInUser(){

        startActivity(new Intent(getApplicationContext(), Homepage.class));
        finish();

    }


    /*private void signedInUser() {
        FirebaseUser userID = app_auth.getCurrentUser();
        DocumentReference userType = DbQuery.app_fireStore.collection("USERS").document(userID.getUid());
        userType.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: " + documentSnapshot.getData());
                if(documentSnapshot.getString("userType").equals("Teacher")) {
                    startActivity(new Intent(IntroPage.this, Homepage.class));
                    finish();
                }
                else if(documentSnapshot.getString("userType").equals("Student")) {
                    startActivity(new Intent(IntroPage.this, Homepage.class));
                    finish();
                }
            }
        });
    }*/
}