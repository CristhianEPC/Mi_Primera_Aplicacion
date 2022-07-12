package com.cristhianpallmay.miprimeraaplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {


    private FirebaseAuth mAuth;

    private EditText correo;
    private EditText contra;

    //Button miBotonIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //enlace con la vista

        mAuth = FirebaseAuth.getInstance();

        correo=findViewById(R.id.txtUsuario);
        contra=findViewById(R.id.txtPassword);

        //miBotonIngresar=findViewById(R.id.btnIngresar);//Elazando elemeto
        //iniciarcontrol();
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void ingre(View view){

        mAuth.signInWithEmailAndPassword(correo.getText().toString(), contra.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(getApplicationContext(), "Creado", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MenuPrincipal.class);
                            startActivity(i);
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    public void regis(View view){
        Intent i = new Intent(this,Registrar.class);
        startActivity(i);
    }

    /*private void iniciarcontrol(){
        miBotonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Enlazar el texto de usuario
                EditText miTxtUsar=findViewById(R.id.txtUsuario);
                String usuario = miTxtUsar.getText().toString();
                Intent intent = new Intent(getApplicationContext(),MenuPrincipal.class);
                intent.putExtra( "usuarioNombre",usuario);
                startActivity(intent);
            }
        });
    }*/
}