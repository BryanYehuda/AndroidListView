package com.example.databasesql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Edit_data extends AppCompatActivity {

    KasirTbl kasirTbl;
    EditText nama, produk, jumlah_produk, harga;
    Button update;
    Integer jumlah_produk_integer, harga_integer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);

        nama=findViewById(R.id.nama);
        produk=findViewById(R.id.produk);
        jumlah_produk=findViewById(R.id.jumlah_produk);
        harga=findViewById(R.id.harga);
        update=findViewById(R.id.update_data);
        getSupportActionBar().setTitle("Edit Data "+getIntent().getStringExtra("nama"));
        kasirTbl= new KasirTbl(getApplicationContext());

        //Get Sent Data
        nama.setText(getIntent().getStringExtra("nama"));
        produk.setText(getIntent().getStringExtra("produk"));
        jumlah_produk.setText(getIntent().getStringExtra("jumlah_produk"));
        harga.setText(getIntent().getStringExtra("harga"));

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jumlah_produk_integer = Integer.parseInt(jumlah_produk.getText().toString());
                harga_integer = Integer.parseInt(harga.getText().toString());

                kasirTbl.update_data(
                        getIntent().getStringExtra("id"),
                        nama.getText().toString(),
                        produk.getText().toString(),
                        jumlah_produk_integer,
                        harga_integer
                );
                finish();
            }
        });
    }
}