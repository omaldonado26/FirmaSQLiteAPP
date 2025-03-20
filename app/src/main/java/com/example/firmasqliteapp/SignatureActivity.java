package com.example.firmasqliteapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.gcacace.signaturepad.views.SignaturePad;

import java.io.ByteArrayOutputStream;

public class SignatureActivity extends AppCompatActivity {
    private SignaturePad signaturePad;
    private EditText etDescription;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature);

        signaturePad = findViewById(R.id.signaturePad);
        etDescription = findViewById(R.id.etDescription);
        Button btnSave = findViewById(R.id.btnSave);
        Button btnClear = findViewById(R.id.btnClear);
        databaseHelper = new DatabaseHelper(this);

        btnClear.setOnClickListener(v -> signaturePad.clear());

        btnSave.setOnClickListener(v -> {
            if (signaturePad.isEmpty()) {
                Toast.makeText(SignatureActivity.this, "Por favor, firme antes de guardar.", Toast.LENGTH_SHORT).show();
                return;
            }

            Bitmap signatureBitmap = getBitmapFromView(signaturePad);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            signatureBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] signatureBytes = byteArrayOutputStream.toByteArray();

            databaseHelper.insertSignature(etDescription.getText().toString(), signatureBytes);

            Toast.makeText(SignatureActivity.this, "Firma guardada con éxito", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(SignatureActivity.this, MainActivity.class));
            finish();
        });
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }
}

