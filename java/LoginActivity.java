package info.itlance.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    AppCompatButton mappCompatButton;
    AppCompatButton mLoginButton;

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;

    TextInputLayout mEmailTextInputLayou;
    TextInputLayout mPasswordTextInputLayou;

    EditText mEmailEditText;
    EditText mPasswordEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button mSignUpButton;
        Button mLoginButton;

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();
        mEmailEditText=(EditText)findViewById(R.id.emaiEditTextView);
        mPasswordEditText=(EditText)findViewById(R.id.passwordEditTextView) ;
        mLoginButton=(Button)findViewById(R.id.button1);
        mSignUpButton=(Button)findViewById(R.id.button2);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email =mEmailEditText.getText().toString();
                String password=mPasswordEditText.getText().toString();
                email=email.trim();
                password=password.trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(LoginActivity.this,"Enter your registered email",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "Enter your password", Toast.LENGTH_SHORT).show();
                }else{
                    mFirebaseAuth.signInWithEmailAndPassword(email,password).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(LoginActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();

                        }
                    }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent=new Intent(LoginActivity.this,Main2Activity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"Sign in Failed",Toast.LENGTH_SHORT).show();
                            }
                        }});

                }






            }
        });
    }

}
