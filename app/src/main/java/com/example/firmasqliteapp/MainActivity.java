package com.example.firmasqliteapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private SignatureAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recyclerViewSignatures);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Signature> signatureList = databaseHelper.getAllSignatures();
        adapter = new SignatureAdapter(this, signatureList);
        recyclerView.setAdapter(adapter);

        Button btnAddSignature = findViewById(R.id.btnAddSignature);
        btnAddSignature.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SignatureActivity.class)));
    }
}

