package com.niswarth.product_scanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class reccycler_and_card extends AppCompatActivity {

    private RecyclerView prodlist;
    private DatabaseReference dataprod;
    private DatabaseReference delref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productlist);
        dataprod= FirebaseDatabase.getInstance().getReference().child("Products");
        delref=FirebaseDatabase.getInstance().getReference("Products");
        dataprod.keepSynced(true);

        prodlist=findViewById(R.id.prodlist);
        prodlist.setHasFixedSize(true);
        prodlist.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Retrivedata,RetrivedataViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Retrivedata, RetrivedataViewHolder>
                (Retrivedata.class,R.layout.productcard,RetrivedataViewHolder.class,dataprod) {
            @Override
            protected void populateViewHolder(final RetrivedataViewHolder retrivedataViewHolder, final Retrivedata retrivedata, int i) {
                retrivedataViewHolder.setBarcode(retrivedata.getBarcode());
                retrivedataViewHolder.setname(retrivedata.getProdname());
                retrivedataViewHolder.setcate(retrivedata.getProdcat());
                retrivedataViewHolder.setmrp(retrivedata.getProdmrp());
                retrivedataViewHolder.setexp(retrivedata.getProdexp());

                retrivedataViewHolder.deleteIV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataprod.child(retrivedata.getBarcode()).removeValue();
                        Toast.makeText(reccycler_and_card.this, "deleted", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        prodlist.setAdapter(firebaseRecyclerAdapter);

    }

    public static class RetrivedataViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        CardView cardlist;
        ImageView deleteIV;
        public RetrivedataViewHolder(View itemView)
        {
            super(itemView);
            mView=itemView;
            cardlist=itemView.findViewById(R.id.cardView);
            deleteIV=itemView.findViewById(R.id.deleteIV);
        }
        public void setBarcode(String barcode)
        {
            TextView bar=(TextView)mView.findViewById(R.id.barcodeTV);
            bar.setText(barcode);
        }
        public void setname(String prodname)
        {
            TextView pname=(TextView)mView.findViewById(R.id.nameTV);
            pname.setText(prodname);
        }
        public void setcate(String prodcat)
        {
            TextView pcat=(TextView)mView.findViewById(R.id.cateTV);
            pcat.setText(prodcat);
        }
        public void setmrp(String prodmrp)
        {
            TextView pmrp=(TextView)mView.findViewById(R.id.mrpTV);
            pmrp.setText(prodmrp);
        }
        public void setexp(String prodexp)
        {
            TextView pexp=(TextView)mView.findViewById(R.id.expiTV);
            pexp.setText(prodexp);
        }
    }
}
