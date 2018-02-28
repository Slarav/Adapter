package com.example.andrei.mycaaapplication;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.andrei.mycaaapplication.utils.ImageItem;

import java.util.ArrayList;

/**
 * Created by Andrei on 08/12/2016.
 */

public class MainBaseAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<ImageItem> mLista;
    private int mHeight;

    public MainBaseAdapter(Context context, ArrayList<ImageItem> lista, int height){
        this.mContext = context;
        this.mLista = lista;
        this.mHeight = height;
    }

    @Override
    public int getCount() {
        // Retorna o tamanho da lista
        return mLista.size();
    }

    @Override
    public Object getItem(int position) {
        // Retorna o item selecionado
        return mLista.get(position);
    }

    @Override
    public long getItemId(int position) {
        // Retorna o id do item selecionado
        return position;
    }

    @Override
    public View getView(int position, final View view, ViewGroup viewGroup) {

        // Recupera o item selecionado 
        ImageItem iiAux = mLista.get(position);

        // Cria o layout a ser adicionado na tela
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.layout_choosed_image_item, null);

        // Recuperando os elementos do layout de image_item
        ImageView ivImageCI = (ImageView) layuot.findViewById(R.id.ivImageCI);
		// Define uma imagem por meio de uma URI
		ivImageCI.setImageURI(Uri.parse(mLista.get(position).getmImage()));
       
		// Define o tamanho da Imagem
        ivImageCI.getLayoutParams().height = mHeight;
		
		// Recupera objetos de texto
        TextView tvDescriptionCI = (TextView) layuot.findViewById(R.id.tvDescriptionCI);
        tvDescriptionCI.setText(mLista.get(position).getmDescription());

        return layout;
    }
}
