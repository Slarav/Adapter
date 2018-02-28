package com.slarav.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.andrei.myapplication.R;

import java.util.ArrayList;


/**
 * Created by Andrei Carniel 09 on 06/09/2017.
 */
public class RecyclerAdapterWithFilter extends RecyclerView.Adapter<RecyclerAdapterWithFilter.ViewHolder>
        implements Filterable {

    private Context context;

    private List<Pessoa> mArrayList;
    private List<Pessoa> mFilteredList;

    private ListView listClientes;

    private int position;
    private AdapterView.OnItemClickListener onItemClickListener;


    public RecyclerAdapterWithFilter(Context context, List<Pessoa> lista, double latitude,
                                           double longitude, AdapterView.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.mArrayList = lista;
        this.mFilteredList = lista;

        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(context).inflate(R.layout.row_localiza_cliente, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        this.position = position;

        holder.tvCodigo.setText(String.valueOf(mFilteredList.get(position).getCodigo()));
        holder.tvNome.setText(mFilteredList.get(position).getNome());

        // Recupera o endereço do cliente salvo no banco
        String endereco = PessoaEnderecoController.getReducedObject(mFilteredList.get(position).getCodigo());

        holder.tvDistancia.setVisibility(View.GONE);
        holder.tvEndereco.setText(endereco);

        // Adiciona fundo com seletor de clique diferente para linhas pares e ímpares
        if (position % 2 == 0) {
            holder.llRow.setBackground(context.getResources().getDrawable(R.drawable.linearlayout_selector_two));
        } else {
            holder.llRow.setBackground(context.getResources().getDrawable(R.drawable.linearlayout_selector_one));
        }
    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    //recupera o item selecionado
    public Object getItem(int position) {
        return mFilteredList.get(position);
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                // Verifica se o texto está vazio
                if (charString.isEmpty()) {
                    mFilteredList = mArrayList;

                } else {

                    List<Pessoa> filteredList = new ArrayList<>();

                    //adiciona os resultados que contém a String procurada
                    for (Pessoa Pessoa : mArrayList) {

                        if (Pessoa.getNome().toLowerCase().contains(charString)) {
                            filteredList.add(Pessoa);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                // Publica os resultados
                mFilteredList = (ArrayList<Pessoa>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    // Providencia um acesso direto as views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvCodigo;
        private TextView tvNome;
        private TextView tvEndereco;
        private TextView tvDistancia;
        private LinearLayout llRow;

        // Recupera as Viewa
        public ViewHolder(View itemView) {
            super(itemView);

            llRow = (LinearLayout) itemView.findViewById(R.id.llLocalizaClientes);
            // Adiciona click
            llRow.setOnClickListener(this);

            tvCodigo = (TextView) itemView.findViewById(R.id.tvwLocalizaCodigo);
            tvNome = (TextView) itemView.findViewById(R.id.tvwLocalizaNome);
            tvEndereco = (TextView) itemView.findViewById(R.id.tvwLocalizaEndereco);
            tvDistancia = (TextView) itemView.findViewById(R.id.tvwLocalizaDistancia);
        }

        @Override
        public void onClick(View view) {
            // cria o evento do click
            onItemClickListener.onItemClick(null, view, getAdapterPosition(), view.getId());
        }
    }

}
