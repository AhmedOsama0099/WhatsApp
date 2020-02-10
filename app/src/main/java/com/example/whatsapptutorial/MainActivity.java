package com.example.whatsapptutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private EditText mPhoneNumber,mCode;
    private Button mVerifiy;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    String mVerfivationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);
        userIsLoggedIn();
        mPhoneNumber=findViewById(R.id.phoneNumber);
        mCode=findViewById(R.id.code);
        mVerifiy=findViewById(R.id.send);

        mVerifiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mVerfivationId!=null){
                    verifiyPhoneNumberWithCode();
                }
                else
                    startPhoneNumberVerification();
            }
        });
        mCallbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                mVerfivationId=s;
                mVerifiy.setText("verify Code");

            }
        };
    }
    private void verifiyPhoneNumberWithCode(){
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(mVerfivationId,mCode.getText().toString());
        signInWithPhoneAuthCredential(credential);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    userIsLoggedIn();
                }
            }
        });
    }

    private void userIsLoggedIn() {
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();

        if(user!=null){
            Intent intent=new Intent(MainActivity.this,HomePage.class);
            startActivity(intent);
            finish();
            return;
        }

    }

    private void startPhoneNumberVerification() {
        if(!mPhoneNumber.getText().toString().isEmpty())
            PhoneAuthProvider.getInstance().verifyPhoneNumber(

                    mPhoneNumber.getText().toString(),
                    60,
                    TimeUnit.SECONDS,
                    this,
                    mCallbacks
            );
        else
            Toast.makeText(getBaseContext(),"Enter Ur Phone!",Toast.LENGTH_SHORT).show();
    }
}
