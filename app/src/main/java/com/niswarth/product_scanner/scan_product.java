package com.niswarth.product_scanner;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class scan_product extends AppCompatActivity {
    Button scanbtn;
    Button addprod;
    Button clearbtn;
    EditText barcoderes;
    EditText prodname;
    EditText prodcat;
    EditText prodmrp;
    EditText prodexp;
    //qr code scanner object
    private IntentIntegrator qrScan;
    DatabaseReference dataproduct;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
        scanbtn=findViewById(R.id.prodscan);
        barcoderes=findViewById(R.id.scanid);
        prodname=findViewById(R.id.prodname);
        prodcat=findViewById(R.id.prodcat);
        prodmrp=findViewById(R.id.prodmrp);
        prodexp=findViewById(R.id.prodexp);
        addprod=findViewById(R.id.adddata);
        clearbtn=findViewById(R.id.clearbtn);
        dataproduct= FirebaseDatabase.getInstance().getReference("Products");

        //intializing scan object
        qrScan = new IntentIntegrator(this);
        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.initiateScan();

            }
        });
        addprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addproduct();
            }
        });
        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barcoderes.setText("");
                prodname.setText("");
                prodcat.setText("");
                prodmrp.setText("");
                prodexp.setText("");
            }
        });


    }
    //Getting the scan results
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null)
        {
            if (result.getContents() == null)
            {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            }
            else
                {
                    barcoderes.setText(result.getContents());
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        super.onActivityResult(requestCode, resultCode, data);
        }

        private void addproduct()
        {
            String barcode= barcoderes.getText().toString().trim();
            String productname=prodname.getText().toString().trim();
            String productcat=prodcat.getText().toString().trim();
            String productmrp=prodmrp.getText().toString().trim();
            String productexp=prodexp.getText().toString().trim();

            if(TextUtils.isEmpty(barcode)|TextUtils.isEmpty(productname)| TextUtils.isEmpty(productcat)|TextUtils.isEmpty(productmrp)|TextUtils.isEmpty(productexp))
            {
                Toast.makeText(this,"Please enter the detail",Toast.LENGTH_SHORT).show();
            }
            else
            {
                store_product store= new store_product(barcode,productname,productcat,productmrp,productexp);
                dataproduct.child(barcode).setValue(store);
                Toast.makeText(this,"Product added",Toast.LENGTH_SHORT).show();

            }
        }

    }


