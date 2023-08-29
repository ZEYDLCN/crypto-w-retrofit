package com.zeydalcan.retrofitjava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zeydalcan.retrofitjava.R;
import com.zeydalcan.retrofitjava.model.CryptoModel;

import java.util.ArrayList;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoHolder> {

    ArrayList<CryptoModel> ModelList;

    public CryptoAdapter(ArrayList<CryptoModel> modelList) {
        ModelList = modelList;
    }

    @NonNull
    @Override
    public CryptoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new CryptoHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoHolder holder, int position) {
        CryptoModel cryptoModel = ModelList.get(position);
        holder.bind(cryptoModel, holder.colors, position);
    }

    @Override
    public int getItemCount() {
        return ModelList.size();
    }

    public class CryptoHolder extends RecyclerView.ViewHolder{
        TextView textViewCurrency;
        TextView textViewPrice;

        private String[] colors = {"#a3ff00","#ff00aa","#b4a7d6","#a4c2f4","#8ee5ee","#cd950c","#f5f5f5","#f47932"};

        public CryptoHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(CryptoModel cryptoModel,String[] colors,Integer position)
        {
         itemView.setBackgroundColor(Color.parseColor(colors[position % 8] ));
         textViewCurrency=itemView.findViewById(R.id.text_name);
         textViewPrice=itemView.findViewById(R.id.text_price);
         textViewCurrency.setText(cryptoModel.currency);
         textViewPrice.setText(cryptoModel.price);


        }

    }

}
