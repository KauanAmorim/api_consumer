package br.com.nauakavlis;

import br.com.nauakavlis.apis.OmdbSearch;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        new OmdbSearch().execute();
        // TODO: Google Books API - Pesquisar Livros
        // TODO: API CoinGecko - Pesquisar criptomoedas
        // TODO: API TheMealDB - Pesquisar receitas
    }
}