package com.example.roomzadaniee;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonDodaj;
    EditText editTextNazwa, editTextWaga, editTextDlugosc, editTextPodOchrona;
    Spinner spinnerKategoria;
    ListView listView;
    List<Ryby> gryList;
    ArrayAdapter<Ryby> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RybyDataBase rybyDataBase = Room.databaseBuilder(
                        getApplicationContext(), RybyDataBase.class, "ryby_db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        rybyDataBase.zwrocRybyDao().wstawRybyDoBazy(
                new Ryby("karp", 10, 80, "nie", "slodkowodny")
        );

        buttonDodaj = findViewById(R.id.buttonDodaj);
        editTextNazwa = findViewById(R.id.editTextNazwa);
        editTextWaga = findViewById(R.id.editTextWaga);
        editTextDlugosc = findViewById(R.id.editTextDlugosc);
        editTextPodOchrona = findViewById(R.id.editTextPodOchrona);
        spinnerKategoria = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        buttonDodaj.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Ryby ryby = new Ryby(
                                editTextNazwa.getText().toString(),
                                Integer.parseInt(editTextWaga.getText().toString()),
                                Integer.parseInt(editTextDlugosc.getText().toString()),
                                editTextPodOchrona.getText().toString(),
                                spinnerKategoria.getSelectedItem().toString()
                        );
                        rybyDataBase.zwrocRybyDao().wstawRybyDoBazy(ryby);
                    }
                }
        );
        gryList = rybyDataBase.zwrocRybyDao().zwrocWszystkieRyby();
        arrayAdapter = new ArrayAdapter<>(
                MainActivity.this,
                android.R.layout.simple_list_item_1,
                gryList
        );
        listView.setAdapter(arrayAdapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                rybyDataBase.zwrocRybyDao().usunZBazy(gryList.get(i));
                gryList.remove(i);
                arrayAdapter.notifyDataSetChanged();

                return false;
            }
        });

    }
}