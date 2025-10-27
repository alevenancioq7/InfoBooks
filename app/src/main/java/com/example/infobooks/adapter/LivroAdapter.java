package com.example.infobooks.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.infobooks.R;
import com.example.infobooks.activity.DetalhesActivity;
import com.example.infobooks.model.Livro;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class LivroAdapter extends RecyclerView.Adapter<LivroAdapter.ViewHolder>
{
    private List<Livro> listaLivros;

    public LivroAdapter(List<Livro> listaLivros)
    {
        this.listaLivros = (listaLivros != null) ? listaLivros : new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_livro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Livro livro = listaLivros.get(position);
        holder.textTitulo.setText(livro.getTitulo());
        holder.textAutores.setText(livro.getAutores());

        String coverUrl = livro.getCoverUrl();
        if(coverUrl != null)
        {
            Picasso.get().load(coverUrl).placeholder(R.drawable.livro_capa).error(R.drawable.livro_capa).into(holder.imageCapa);
        } else {
            holder.imageCapa.setImageResource(R.drawable.livro_capa);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetalhesActivity.class);
            intent.putExtra("LIVRO", livro);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount()
    {
        return listaLivros != null ? listaLivros.size() : 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imageCapa;
        public TextView textTitulo;
        public TextView textAutores;

        public ViewHolder(View view)
        {
            super(view);
            imageCapa = view.findViewById(R.id.imageCapa);
            textTitulo = view.findViewById(R.id.textTitulo);
            textAutores = view.findViewById(R.id.textAutores);
        }
    }
    public void atualizarLista(List<Livro> novaLista)
    {
        this.listaLivros.clear();
        if (novaLista != null) {
            this.listaLivros.addAll(novaLista);
        }
        notifyDataSetChanged();
    }
}