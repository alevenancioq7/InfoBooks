package com.example.infobooks.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.infobooks.R;
import com.example.infobooks.adapter.LivroAdapter;
import com.example.infobooks.api.ApiService;
import com.example.infobooks.api.RetrofitClient;
import com.example.infobooks.model.Livro;
import com.example.infobooks.model.LivroResponse;
import com.example.infobooks.util.Preferencias;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private EditText editPesquisa;
    private Button btnPesquisar;
    private LivroAdapter adapter;
    private Preferencias preferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarViews();
        configurarRecyclerView();
        configurarCliques();
        carregarDadosIniciais();
    }

    private void inicializarViews()
    {
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        editPesquisa = findViewById(R.id.editPesquisa);
        btnPesquisar = findViewById(R.id.btnPesquisar);
        preferencias = new Preferencias(this);
    }

    private void configurarRecyclerView()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LivroAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);
    }

    private void configurarCliques()
    {
        btnPesquisar.setOnClickListener(v -> {
            String termo = editPesquisa.getText().toString().trim();
            if(!termo.isEmpty())
            {
                pesquisarLivros(termo);
            } else {
                Toast.makeText(this, "Digite um termo para pesquisar", Toast.LENGTH_SHORT).show();
            }
        });

        editPesquisa.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_SEARCH)
            {
                String termo = editPesquisa.getText().toString().trim();

                if(!termo.isEmpty())
                {
                    pesquisarLivros(termo);
                }
                return true;
            }
            return false;
        });
    }

    private void carregarDadosIniciais()
    {
        pesquisarLivros("programming");
    }

    private void pesquisarLivros(String termo)
    {
        progressBar.setVisibility(View.VISIBLE);

        ApiService service = RetrofitClient.getClient().create(ApiService.class);
        Call<LivroResponse> call = service.buscarLivros(termo, 20);

        call.enqueue(new Callback<LivroResponse>()
        {
            @Override
            public void onResponse(Call<LivroResponse> call, Response<LivroResponse> response)
            {
                progressBar.setVisibility(View.GONE);

                if(response.isSuccessful() && response.body() != null)
                {
                    List<Livro> livros = response.body().getDocs();

                    if(livros != null && !livros.isEmpty())
                    {
                        adapter.atualizarLista(livros);
                        preferencias.salvarUltimaConsulta("Livros sobre: " + termo);
                        Toast.makeText(MainActivity.this, "Encontrados " + livros.size() + " livros", Toast.LENGTH_SHORT).show();
                    } else {
                        adapter.atualizarLista(new ArrayList<>());
                        Toast.makeText(MainActivity.this, "Nenhum livro encontrado para: " + termo, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Erro ao carregar dados", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LivroResponse> call, Throwable t)
            {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Falha na conexão", Toast.LENGTH_SHORT).show();
            }
        });
    }
}