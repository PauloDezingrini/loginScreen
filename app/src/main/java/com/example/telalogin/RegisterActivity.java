package com.example.telalogin;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    @BindView (R.id.registerButton)
    Button registerButton;

    @BindView(R.id.registerEmail)
    EditText etRegisterEmail;

    @BindView(R.id.registerPassword)
    EditText etRegisterPassword;

    @BindView(R.id.registerUsername)
    EditText etRegisterUsername;

    @BindView(R.id.confirmPassword)
    EditText etConfirmPassword;

    private FirebaseAuth mAuth;

    FirebaseDatabase firebaseDatabase;

    DatabaseReference myRef;

    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        firebaseDatabase = FirebaseDatabase.getInstance();

        myRef = firebaseDatabase.getReference();

        register();
    }

    private void register() {

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etRegisterUsername.getText().toString();
                final String email = etRegisterEmail.getText().toString();
                String password = etRegisterPassword.getText().toString();
                String confirmPassword = etConfirmPassword.getText().toString();

                if ( !isStringEmpty(email) && !isStringEmpty(password) && !isStringEmpty(username) && (password.equals(confirmPassword))) {

                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification();

                                startActivity(new Intent(RegisterActivity.this , LoginActivity.class));

                                createUserDatabase(username , email ) ;
                                Toast.makeText(RegisterActivity.this, "Registrado com sucesso", Toast.LENGTH_SHORT).show();
                            } else {
                                Log.d (TAG , "OnComplete: error " + task.getException().getMessage());
                                Toast.makeText(RegisterActivity.this, "O registro falhou ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "Preencha todos os campos ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void createUserDatabase(String name , String email){

        String userID = mAuth.getCurrentUser().getUid();

        User user = new User(name , email);

        myRef.child("user").child(userID).setValue(user);
    }

    private boolean isStringEmpty (String inputString) {
        if (inputString.isEmpty() || inputString == null) {
            return true;
        } else {
            return false;
        }
    }
}
