package com.slarav.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andrei.myapplication.R;

import java.util.ArrayList;

/**
 * Created by Desenvolvimento 09 on 06/09/2017.
 */

public class BaseAdapterWithFilter extends BaseAdapter implements Filterable{

    private Context context;
    private ArrayList<ProdutoVenda> lista;
    private Filter filter = null;
    private ListView listProdutos;

    private LinearLayout llRow;
    private TextView tvwCodigo;
    private TextView tvwNome;
    private TextView tvwValor;
    private TextView tvwQuantidade;
    private TextView tvwTotal;
    private TextView tvwDescAcre;

    public BaseAdapterWithFilter(Context context, ArrayList<ProdutoVenda> lista, ListView listProdutos){
        this.context = context;
        this.lista = lista;
        this.listProdutos = listProdutos;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Cria uma instância do layout XML
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_lista_produtos_selecionados, null);

        // Recupera os elementos do xml
        llRow = (LinearLayout) rowView.findViewById(R.id.llListaProd);
        tvwCodigo = (TextView) rowView.findViewById(R.id.tvwListaProdCodigo);
        tvwNome = (TextView) rowView.findViewById(R.id.tvwListaProdNome);
        tvwValor = (TextView) rowView.findViewById(R.id.tvwListaProdValor);
        tvwQuantidade = (TextView) rowView.findViewById(R.id.tvwListaProdQuantidade);
        tvwTotal = (TextView) rowView.findViewById(R.id.tvwListaProdTotal);
        tvwDescAcre = (TextView) rowView.findViewById(R.id.tvwListaProdDescAcre);


        //recupera o produto
        ProdutoVenda pv = lista.get(position);


        //cor de fundo
        if(position % 2 == 0){
            llRow.setBackgroundColor(context.getResources().getColor(R.color.red));
        } else {
            llRow.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        //apresenta os dados na tela
        tvwCodigo.setText(pv.getCodigo() + ".");
        tvwNome.setText(pv.getNome());
        tvwValor.setText("R$ " + pv.getValorUnitario());
        tvwQuantidade.setText(pv.getQuantidade());
        tvwTotal.setText("R$ " + pv.getValorTotal());

        //verifica se tem desconto ou acréscimo, caso não tenha, omite o campo
        if(pv.getDescontoAcrescimo() > 0){
            tvwDescAcre.setText("Acréscimo: " + pv.getValorDesconto() + "%");
            tvwDescAcre.setVisibility(View.VISIBLE);
        } else if(pv.getDescontoAcrescimo() < 0){
            tvwDescAcre.setText("Desconto: " + (pv.getValorDesconto() * (-1)) + "%");
            tvwDescAcre.setVisibility(View.VISIBLE);
        } else {
            tvwDescAcre.setVisibility(View.GONE);
        }

        return rowView;
    }


    @Override
    public Filter getFilter() {
        if(filter == null)
            filter = new CheeseFilter();
        return filter;
    }

    public class CheeseFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // TODO Auto-generated method stub

            // Recupera a sequenco digitada
            constraint = constraint.toString().toLowerCase();
            FilterResults newFilterResults = new FilterResults();

            // Verifica se não está nul
            if (constraint != null && constraint.length() > 0) {

                ArrayList<ProdutoVenda> auxData = new ArrayList<>();
                // Adiciona os resultados que possuem o nome semelhante
                for (int i = 0; i < lista.size(); i++) {
                    if (lista.get(i).getNome().toLowerCase().contains(constraint))
                        auxData.add(lista.get(i));
                }

                newFilterResults.count = auxData.size();
                newFilterResults.values = auxData;

            } else {
                newFilterResults.count = lista.size();
                newFilterResults.values = lista;
            }

            return newFilterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // Publica os resultados
            ArrayList<ProdutoVenda> resultData = new ArrayList<>();
            resultData = (ArrayList<ProdutoVenda>) results.values;

            BaseAdapterWithFilter adapter = new BaseAdapterWithFilter(context, resultData, listProdutos);
            listProdutos.setAdapter(adapter);

        }
    }


}
