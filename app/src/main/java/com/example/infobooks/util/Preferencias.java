package com.example.infobooks.util;

import android.content.Context;
import android.content.SharedPreferences;
public class Preferencias
{
    private SharedPreferences sharedPreferences;
    private static final String PREF_NAME = "AppLivrosPrefs";
    private static final String KEY_ULTIMA_CONSULTA = "ultima_consulta";
    private static final String KEY_LIVRO_FAVORITO = "livro_favorito";

    // Construtor
    public Preferencias(Context context)
    {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void salvarUltimaConsulta(String consulta)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ULTIMA_CONSULTA, consulta);
        editor.apply();
    }

    public String recuperarUltimaConsulta()
    {
        return sharedPreferences.getString(KEY_ULTIMA_CONSULTA, "Nenhuma consulta recente");
    }

    public void salvarLivroFavorito(String tituloLivro)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_LIVRO_FAVORITO, tituloLivro);
        editor.apply();
    }

    public String recuperarLivroFavorito()
    {
        return sharedPreferences.getString(KEY_LIVRO_FAVORITO, "Nenhum favorito");
    }
}