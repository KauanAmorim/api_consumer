package br.com.nauakavlis.persistence;

import br.com.nauakavlis.records.Titulo;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PersistenceFile {

    Gson gson;
    String filename;

    public PersistenceFile(String filename) {
        this.filename = filename;
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
    }

    public void save(List<Titulo> titulos) throws IOException {
        saveToFile(titulos);
    }

    public void save(Titulo titulo) throws IOException {
        saveToFile(titulo);
    }

    private void saveToFile(Object data) throws IOException {
        try (FileWriter escrita = new FileWriter(this.filename)) {
            escrita.write(this.gson.toJson(data));
        }
    }
}

