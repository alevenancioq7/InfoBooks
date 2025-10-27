package com.example.infobooks.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.infobooks.R;
import com.example.infobooks.model.Livro;
import com.squareup.picasso.Picasso;

public class DetalhesActivity extends AppCompatActivity
{
    private ImageView imageCapa;
    private TextView textTitulo, textAutores, textEditora, textAno, textPaginas, textDescricao, textIsbn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        inicializarViews();
        exibirDetalhesLivro();
    }

    private void inicializarViews()
    {
        imageCapa = findViewById(R.id.imageDetalheCapa);
        textTitulo = findViewById(R.id.textDetalheTitulo);
        textAutores = findViewById(R.id.textDetalheAutores);
        textEditora = findViewById(R.id.textDetalheEditora);
        textAno = findViewById(R.id.textDetalheAno);
        textPaginas = findViewById(R.id.textDetalhePaginas);
        textDescricao = findViewById(R.id.textDetalheDescricao);
        textIsbn = findViewById(R.id.textDetalheIsbn);
    }

    private void exibirDetalhesLivro()
    {
        if(getIntent() != null && getIntent().getExtras() != null)
        {
            Livro livro = (Livro) getIntent().getSerializableExtra("LIVRO");

            if(livro != null)
            {
                textTitulo.setText(livro.getTitulo());
                textAutores.setText("Autores: " + livro.getAutores());
                textEditora.setText("Editora: " + livro.getEditora());
                textAno.setText("Ano de publicação: " + livro.getAno());
                textPaginas.setText("Páginas: " + livro.getPaginas());
                textIsbn.setText("ISBN: " + livro.getIsbnFormatado());
                textDescricao.setText(livro.getDescricao());

                String coverUrl = livro.getCoverUrl();
                if(coverUrl != null)
                {
                    Picasso.get().load(coverUrl).placeholder(R.drawable.livro_capa)
                            .error(R.drawable.livro_capa).into(imageCapa);
                } else {
                    imageCapa.setImageResource(R.drawable.livro_capa);
                }
            }
        }
    }
}