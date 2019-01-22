package info.itlance.quizapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout mNameTextInputLayout;
    TextInputLayout mEmailTextInputLayout;
    TextInputLayout mPasswordTextInputLayout;

    EditText mNameEdiTextView;
    EditText mPasswordEditTextView;
    EditText mEmailEditTextView;

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    AppCompatButton mappCompatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        mNameTextInputLayout=(TextInputLayout)findViewById(R.id.nameTextInputLayout);
        mEmailTextInputLayout=(TextInputLayout)findViewById(R.id.emailTextInputLayout);
        mPasswordTextInputLayout=(TextInputLayout)findViewById(R.id.passwordTextInputLayout);

        mNameEdiTextView=(EditText)findViewById(R.id.nameTextInputEditText);
        mPasswordEditTextView=(EditText)findViewById(R.id.passwordTextInputEditText);
        mEmailEditTextView=(EditText)findViewById(R.id.emailTextInputEditText);

        mappCompatButton=(AppCompatButton)findViewById(R.id.signup);

        mappCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmailEditTextView.getText().toString().trim();
                String password=mPasswordEditTextView.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(SignUpActivity.this,"Enter your email",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUpActivity.this, "Enter new password", Toast.LENGTH_SHORT).show();
                }else {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SignUpActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();

                        }
                    }).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent=new Intent(SignUpActivity.this,Main2Activity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(SignUpActivity.this,"Sign up Failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });

    }
    public void onBackPressed(){
        Intent intent= new Intent(SignUpActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();

    }
}
