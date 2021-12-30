package com.example.databasesql;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button tambah_data;
    ArrayList<Objek> list;
    SQLiteDatabase database;
    Cursor cursor;
    KasirTbl kasirTbl;
    Custom_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        tambah_data = findViewById(R.id.tambah_data);
        kasirTbl=new KasirTbl(getApplicationContext());

        tambah_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Tambah_data.class));
            }
        });
        ambil_data();
    }
    void ambil_data()
    {
        list= new ArrayList<Objek>();
        cursor=kasirTbl.tampil_data();
        if(cursor!=null && cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                String id=cursor.getString(0);
                String nama=cursor.getString(1);
                String produk=cursor.getString(2);
                Integer jumlah_produk=cursor.getInt(3);
                Integer harga=cursor.getInt(4);
                list.add(new Objek(
                        id,
                        nama,
                        produk,
                        jumlah_produk,
                        harga
                ));
            }
            adapter=new Custom_adapter(getApplicationContext(), list, MainActivity.this);
            listView.setAdapter(adapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ambil_data();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}

class Custom_adapter extends BaseAdapter {

    Activity activity;
    KasirTbl kasirTbl;
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Objek> model;
    Custom_adapter(Context context, ArrayList<Objek> list, Activity activity)
    {
        this.context=context;
        this.model=list;
        this.activity=activity;
        layoutInflater=LayoutInflater.from(context);
        kasirTbl=new KasirTbl(context);
    }

    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    TextView nama, produk, jumlah_produk, harga;
    Button edit, hapus;

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        View view1=layoutInflater.inflate(R.layout.list_data, viewGroup, false);

        nama=view1.findViewById(R.id.nama);
        produk=view1.findViewById(R.id.produk);
        jumlah_produk=view1.findViewById(R.id.jumlah_produk);
        harga=view1.findViewById(R.id.harga);

        nama.setText(model.get(position).getNama());
        produk.setText(model.get(position).getProduk());

        jumlah_produk.setText(String.valueOf(model.get(position).getJumlahProduk()));
        harga.setText(String.valueOf(model.get(position).getHarga()));

        edit=view1.findViewById(R.id.edit);
        hapus=view1.findViewById(R.id.hapus);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(context, Edit_data.class);
                    intent.putExtra("nama", model.get(position).getNama());
                    intent.putExtra("produk", model.get(position).getProduk());
                    intent.putExtra("jumlah_produk", model.get(position).getJumlahProduk().toString());
                    intent.putExtra("harga", model.get(position).getHarga().toString());
                    intent.putExtra("id", model.get(position).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(activity);
                builder.setTitle("Tanya");
                builder.setMessage("Apakah anda yakin ingin menghapus data ini?");
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        kasirTbl.delete(model.get(position).getId());
                        Intent intent=new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        //((Activity)context).finish();
                    }
                });
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
        
        return view1;
    }
}