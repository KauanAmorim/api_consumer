package br.com.nauakavlis.apis;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class OmdbSearch implements SearchApi {
    String API_KEY = "bfb95c90";
    String BASE_URL = "http://www.omdbapi.com/";
    String URL;
    String textToSearch;

    public void execute() throws IOException, InterruptedException {
        String textToSearch = this.askUserInput();
        this.setTextToSearch(textToSearch);
        String searchUrl = this.generateSearchUrl();
        this.setSearchUrl(searchUrl);
        HttpResponse<String> response = this.requestSearch();
        System.out.println(response.body());
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
