package com.example.databasesql;

public class Objek {
    String id, nama, produk;
    Integer jumlah_produk, harga;

    Objek(String id, String nama, String produk, Integer jumlah_produk, Integer harga)
    {
        this.id=id;
        this.nama=nama;
        this.produk=produk;
        this.jumlah_produk=jumlah_produk;
        this.harga=harga;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getProduk() {
        return produk;
    }

    public Integer getJumlahProduk() {
        return jumlah_produk;
    }

    public Integer getHarga() {
        return harga;
    }
}
