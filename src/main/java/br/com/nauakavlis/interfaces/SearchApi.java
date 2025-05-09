package br.com.nauakavlis.interfaces;

import java.io.IOException;
import java.net.http.HttpResponse;

public interface SearchApi {
    String askUserInput();
    void setTextToSearch(String textToSearch);
    String generateSearchUrl();
    void setSearchUrl(String url);
    HttpResponse<String> requestSearch() throws IOException, InterruptedException;
}
