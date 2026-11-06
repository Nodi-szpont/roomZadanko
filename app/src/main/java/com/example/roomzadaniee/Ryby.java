package com.example.roomzadaniee;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "ryby")
public class Ryby {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nazwa;
    public int waga;
    public int dlugosc;
    public String podOchrona;
    public String kategoriaNazwa;

    public Ryby(String nazwa, int waga, int dlugosc, String podOchrona, String kategoriaNazwa) {
        this.nazwa = nazwa;
        this.waga = waga;
        this.dlugosc = dlugosc;
        this.podOchrona = podOchrona;
        this.kategoriaNazwa = kategoriaNazwa;
    }
}