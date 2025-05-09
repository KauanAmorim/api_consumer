package br.com.nauakavlis;

import br.com.nauakavlis.apis.OmdbSearch;
import br.com.nauakavlis.persistence.PersistenceFile;
import br.com.nauakavlis.records.Titulo;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        List<Titulo> titulos = new OmdbSearch().executeMultiSearch();
        new PersistenceFile("titulos.json").save(titulos);


        // TODO: Google Books API - Pesquisar Livros
        // TODO: API CoinGecko - Pesquisar criptomoedas
        // TODO: API TheMealDB - Pesquisar receitas
    }
}