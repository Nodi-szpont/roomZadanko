package com.example.roomzadaniee;


import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface RybyDAO {
    @Insert
    public void wstawRybyDoBazy(Ryby ryby);

    @Insert
    public void wstawRybyDoBazy(Ryby ... ryby);

    @Delete
    public void usunZBazy(Ryby ryby);

    @Update
    public void aktualizujRybe(Ryby ryby);

    @Query("SELECT * from ryby")
    public List<Ryby> zwrocWszystkieRyby();

    @Query("SELECT nazwa from ryby where waga = :ile")
    public List<String> zwrocNazwyRybDlaWagi(int ile);

}