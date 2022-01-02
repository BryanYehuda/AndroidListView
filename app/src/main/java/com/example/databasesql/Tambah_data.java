package com.example.databasesql;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Tambah_data extends AppCompatActivity {

    KasirTbl kasirTbl;
    EditText nama, produk, jumlah_produk, harga;
    Button simpan;
    Integer jumlah_produk_integer, harga_integer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tambah_data);

        nama=findViewById(R.id.nama);
        produk=findViewById(R.id.produk);
        jumlah_produk=findViewById(R.id.jumlah_produk);
        harga=findViewById(R.id.harga);
        simpan=findViewById(R.id.simpan_data);

        kasirTbl = new KasirTbl(getApplicationContext());
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpan_data();
                finish();
            }
        });

    }

    void simpan_data()
    {
        jumlah_produk_integer = Integer.parseInt(jumlah_produk.getText().toString());
        harga_integer = Integer.parseInt(harga.getText().toString());

        kasirTbl.simpan_data(
                nama.getText().toString(),
                produk.getText().toString(),
                jumlah_produk_integer,
                harga_integer

        );
    }
}