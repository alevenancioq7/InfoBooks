package com.example.infobooks.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;

public class Livro implements Serializable
{
    @SerializedName("key")
    private String key;
    @SerializedName("title")
    private String title;
    @SerializedName("author_name")
    private List<String> authorName;
    @SerializedName("publisher")
    private List<String> publisher;
    @SerializedName("first_publish_year")
    private Integer firstPublishYear;
    @SerializedName("isbn")
    private List<String> isbn;
    @SerializedName("number_of_pages_median")
    private Integer numberOfPagesMedian;
    @SerializedName("cover_i")
    private Integer coverI;

    public String getKey()
    {
        return key;
    }
    public void setKey(String key)
    {
        this.key = key;
    }
    public String getTitle()
    {
        return title;
    }
    public void setTitle(String title)
    {
        this.title = title;
    }
    public List<String> getAuthorName()
    {
        return authorName;
    }
    public void setAuthorName(List<String> authorName)
    {
        this.authorName = authorName;
    }
    public List<String> getPublisher()
    {
        return publisher;
    }
    public void setPublisher(List<String> publisher)
    {
        this.publisher = publisher;
    }
    public Integer getFirstPublishYear()
    {
        return firstPublishYear;
    }
    public void setFirstPublishYear(Integer firstPublishYear)
    {
        this.firstPublishYear = firstPublishYear;
    }
    public List<String> getIsbn()
    {
        return isbn;
    }
    public void setIsbn(List<String> isbn)
    {
        this.isbn = isbn;
    }
    public Integer getNumberOfPagesMedian()
    {
        return numberOfPagesMedian;
    }
    public void setNumberOfPagesMedian(Integer numberOfPagesMedian)
    {
        this.numberOfPagesMedian = numberOfPagesMedian;
    }
    public Integer getCoverI()
    {
        return coverI;
    }
    public void setCoverI(Integer coverI)
    {
        this.coverI = coverI;
    }

    public String getCoverUrl()
    {
        if(coverI != null && coverI > 0)
        {
            return "https://covers.openlibrary.org/b/id/" + coverI + "-M.jpg";
        }
        return null;
    }

    public String getTitulo()
    {
        return title != null ? title : "Título não disponível :(";
    }

    public String getAutores()
    {
        if(authorName != null && !authorName.isEmpty())
        {
            return String.join(", ", authorName);
        }
        return "Autor desconhecido :(";
    }

    public String getEditora()
    {
        if(publisher != null && !publisher.isEmpty())
        {
            return String.join(", ", publisher);
        }
        return "Editora não informada :(";
    }

    public String getAno()
    {
        return firstPublishYear != null ? firstPublishYear.toString() : "Ano de publicação não informado :(";
    }

    public String getPaginas()
    {
        return numberOfPagesMedian != null ? numberOfPagesMedian.toString() : "Média de páginas não informada :(";
    }

    public String getDescricao()
    {
        return "Descrição não disponível na busca inicial.";
    }

    public String getIsbnFormatado()
    {
        if(isbn != null && !isbn.isEmpty())
        {
            return String.join(", ", isbn);
        }
        return "Não disponível";
    }
}