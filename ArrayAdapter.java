package com.slarav.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.slarav.R;

/**
 * Created by Slarav 09 on 06/09/2017.
 */

public class ArrayAdapterAlertFrete extends ArrayAdapter<String> {

    Context context;


    public ArrayAdapterAlertFrete(Context ctx) {
        super(ctx, R.layout.alert_listview_default_row);
        this.context = ctx;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

	// carrega o layout
	LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	rowView = inflater.inflate(R.layout.alert_listview_default_row, parent, false);
      
	// recupera os elementos de tela
        ImageView ivImagem = (ImageView) rowView.findViewById(R.id.imvRowAlertDefault);
        TextView tvLabel = (TextView) rowView.findViewById(R.id.tvwRowAlertDefault);
        LinearLayout linearRowAlertDefault = (LinearLayout) rowView.findViewById(R.id.linearRowAlertDefault);

	// recupera a posição
        String texto = this.getItem(position);
	// define um valor para a linha
        tvLabel.setText(texto);

	// verifica se é par ou impar, para adicionar um fundo diferente a cada cor
        if(position % 2 == 0){
            linearRowAlertDefault.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            linearRowAlertDefault.setBackgroundColor(context.getResources().getColor(R.color.grey_100));
        }

        return rowView;
    }

    @Override
    public void add(String object) {
        super.add(object);
    }

    @Override
    public String getItem(int position) {
        return super.getItem(position);
    }
}
