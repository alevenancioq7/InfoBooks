package com.example.infobooks.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class LivroResponse
{
    @SerializedName("docs")
    private List<Livro> docs;
    @SerializedName("numFound")
    private Integer numFound;

    public List<Livro> getDocs()
    {
        return docs;
    }

    public void setDocs(List<Livro> docs)
    {
        this.docs = docs;
    }

    public Integer getNumFound()
    {
        return numFound;
    }

    public void setNumFound(Integer numFound)
    {
        this.numFound = numFound;
    }
}