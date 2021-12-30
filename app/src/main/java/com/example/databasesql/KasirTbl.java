package com.example.databasesql;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Random;

public class KasirTbl extends SQLiteOpenHelper {

    Context context;
    Cursor cursor;
    SQLiteDatabase database;

    public static String nama_database="data";
    public static String nama_table="kasir";

    public KasirTbl(@Nullable Context context) {
        super(context, nama_database, null, 3);
        this.context=context;
        database=getReadableDatabase();
        database=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query="CREATE TABLE IF NOT EXISTS "+nama_table+" (id varchar(50), nama varchar(50), produk varchar(100), jumlah_produk float(5), harga float(10))";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    String random()
    {
        // angka random 100-999
        int acak = new Random().nextInt(888+1)+100;
        return String.valueOf(acak);
    }

    void simpan_data(String nama, String produk, Integer jumlah_produk, Integer harga)
    {
        database.execSQL("INSERT INTO "+nama_table+" values"+ "('"+random()+"', '"+nama+"','"+produk+"','"+jumlah_produk+"','"+harga+"')");
        Toast.makeText(context, "Data Tersimpan", Toast.LENGTH_SHORT).show();
    }

    void update_data(String id, String nama, String produk, Integer jumlah_produk, Integer harga)
    {
        database.execSQL("UPDATE "+nama_table +
                " SET nama='"+nama+"', produk='"+produk+"', jumlah_produk='"+jumlah_produk+"', harga='"+harga+"'" +
                " WHERE id='"+id+"'");
        Toast.makeText(context, "Data Berhasil di Update", Toast.LENGTH_SHORT).show();
    }

    void delete (String id)
    {
        database.execSQL("DELETE FROM "+nama_table+" WHERE id='"+id+"'");
        Toast.makeText(context, "Data Berhasil di Hapus", Toast.LENGTH_SHORT).show();
    }

    Cursor tampil_data()
    {
        return database.rawQuery("SELECT * FROM "+nama_table, null);
    }


}
