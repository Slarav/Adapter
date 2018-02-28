package com.example.andrei.mycaaapplication;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Slarav on 31/01/2017.
 */

public class MainCursorAdapter extends CursorAdapter {

    private Cursor mCursor;
    private Context mContext;
    private final LayoutInflater mInflater;
    private String layout;

    public MainCursorAdapter(Context context, Cursor cursor, String layout) {
        super(context, cursor);

        this.mCursor = cursor;
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
		// Define qual o tipo de layout a ser utilizado
        this.layout = layout;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

	// Conforme o valor passado por parâmetro, é definido o layout a ser utilizado
        if(layout.equalsIgnoreCase("pequena")){
	    // Tamanho pequeno
    	    return mInflater.inflate(R.layout.layout_image_item_pequena, viewGroup, false);

        } else if (layout.equalsIgnoreCase("grande")){
	    // Tamanho grande
            return mInflater.inflate(R.layout.layout_image_item_grande, viewGroup, false);

        } else {
            // Tamanho médio
            return mInflater.inflate(R.layout.layout_image_item, viewGroup, false);
        }

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // Recuperando os elementos do layout 
        ImageView ivImage = (ImageView) view.findViewById(R.id.ivImage);
	// Definindo uma imagem por meio de um caminho
        ivImage.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndex("caminho"))));

	// Recuperando os elementos do layout 
        TextView tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        // Definindo um texto
        tvDescription.setText(cursor.getString(cursor.getColumnIndex("nome")));
    }
}
