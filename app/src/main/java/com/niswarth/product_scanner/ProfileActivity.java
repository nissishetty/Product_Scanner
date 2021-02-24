package com.niswarth.product_scanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity {
    Button addprod;
    Button shop;
    String CurrentUser;
    Button aboutus;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        CurrentUser=user.getEmail();
        addprod=findViewById(R.id.prodscan);
        shop=findViewById(R.id.prodview);
        aboutus=findViewById(R.id.aboutus);

        addprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (CurrentUser.equals("niswarthshetty@gmail.com")) {
                    finish();
                    startActivity(new Intent(ProfileActivity.this,scan_product.class));
               }
                else{

                   Toast.makeText(ProfileActivity.this,
                           "Not authorized", Toast.LENGTH_LONG).show();
               }
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,reccycler_and_card.class));
            }
        });

        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,aboutapp1.class));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.logout:

                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
        return true;
    }
}
