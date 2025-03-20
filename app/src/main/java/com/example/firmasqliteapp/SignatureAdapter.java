package com.example.firmasqliteapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SignatureAdapter extends RecyclerView.Adapter<SignatureAdapter.ViewHolder> {
    private List<Signature> signatureList;
    private Context context;

    public SignatureAdapter(Context context, List<Signature> signatureList) {
        this.context = context;
        this.signatureList = signatureList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_signature, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Signature signature = signatureList.get(position);
        holder.tvDescription.setText(signature.getDescription());

        Bitmap bitmap = BitmapFactory.decodeByteArray(signature.getImage(), 0, signature.getImage().length);
        holder.imgSignature.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return signatureList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescription;
        ImageView imgSignature;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            imgSignature = itemView.findViewById(R.id.imgSignature);
        }
    }
}
