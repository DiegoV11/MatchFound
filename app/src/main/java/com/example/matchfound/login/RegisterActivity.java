package com.example.matchfound.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matchfound.R;
import com.example.matchfound.dto.UsuarioDTO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();

        EditText username = findViewById(R.id.editUsername);
        EditText email = findViewById(R.id.editEmailRegister);
        EditText riottag = findViewById(R.id.editTag);
        EditText password = findViewById(R.id.editTextTextPassword);
        EditText passwordConfirm = findViewById(R.id.editTextTextPassword2);



        ((Button) findViewById(R.id.btnRegistrarse)).setOnClickListener(view -> {
            boolean bool = true;
            if(username.getText().toString().isEmpty()){
                username.setError("Ingrese su nombre de usuario");
                bool = false;
            }
            if(riottag.getText().toString().isEmpty()){
                riottag.setError("Ingrese su RIOT tag");
                bool = false;
            }
            if(email.getText().toString().isEmpty()){
                email.setError("Ingrese su email");
                bool = false;
            }

            if(password.getText().toString().isEmpty()){
                password.setError("Ingrese su contraseña");
                bool = false;
            }
            if(passwordConfirm.getText().toString().isEmpty()){
                passwordConfirm.setError("Ingrese nuevamente su contraseña");
                bool = false;
            }
            if(!password.getText().toString().equals(passwordConfirm.getText().toString())){
                passwordConfirm.setError("Las contraseñas no coinciden");
                bool = false;
            }
            if(bool){
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            UsuarioDTO usuarioDTO = new UsuarioDTO(username.getText().toString(),email.getText().toString(),riottag.getText().toString(),"-","-","ROL_USER");
                            firebaseDatabase.getReference().child("users").child(firebaseAuth.getCurrentUser().getUid()).setValue(usuarioDTO).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this,"Cuenta creada exitosamente, inicie sesión",Toast.LENGTH_SHORT).show();
                                        firebaseAuth.getCurrentUser().sendEmailVerification();
                                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                        firebaseAuth.signOut();
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(RegisterActivity.this,"Error al guardar datos de usuario",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterActivity.this,"Error al crear Usuario",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }else{
                Toast.makeText(RegisterActivity.this, "Verifique sus datos", Toast.LENGTH_SHORT).show();
            }
        });


    }
}