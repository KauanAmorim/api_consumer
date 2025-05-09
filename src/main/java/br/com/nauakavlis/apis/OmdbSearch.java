package br.com.nauakavlis.apis;

import br.com.nauakavlis.interfaces.SearchApi;
import br.com.nauakavlis.records.Titulo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OmdbSearch implements SearchApi {
    String API_KEY = "bfb95c90";
    String BASE_URL = "http://www.omdbapi.com/";
    String URL;
    String textToSearch;
    Gson gson;

    public OmdbSearch() {
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
    }

    public Titulo executeSearch() throws IOException, InterruptedException {
        String textToSearch = this.askUserInput();
        return this.searchUserInput(textToSearch);
    }

    public List<Titulo> executeMultiSearch() throws IOException, InterruptedException {
        String userInput = "";
        List<Titulo> titulos = new ArrayList<>();
        while(!userInput.equalsIgnoreCase("Sair")) {
            userInput = this.askUserInput();
            boolean userWantsToStopTheSearch = userInput.equalsIgnoreCase("Sair");
            if(userWantsToStopTheSearch) {
                continue;
            }

            Titulo titulo = this.searchUserInput(userInput);
            titulos.add(titulo);
        }
        return titulos;
    }

    public Titulo searchUserInput(String userInput) throws IOException, InterruptedException {
        this.setTextToSearch(userInput);
        String searchUrl = this.generateSearchUrl();
        this.setSearchUrl(searchUrl);
        HttpResponse<String> response = this.requestSearch();
        return this.gson.fromJson(response.body(), Titulo.class);
    }

    @Override
    public String askUserInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o nome do filme para obter informações: ");
        return scanner.nextLine();
    }

    @Override
    public void setTextToSearch(String textToSearch) {
        this.textToSearch = textToSearch;
    }

    @Override
    public String generateSearchUrl() {
        this.textToSearch = this.textToSearch.replace(" ", "+");
        return BASE_URL + "?t=" + this.textToSearch + "&apikey=" + API_KEY;
    }

    @Override
    public void setSearchUrl(String url) {
        this.URL = url;
    }

    @Override
    public HttpResponse<String> requestSearch() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(this.URL)).build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
