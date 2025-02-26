package ThreadsRequestExample;

import java.util.concurrent.Callable;

public class Garimpador implements Callable<Boolean> {
    private final String termo;
    private final String url;

    public Garimpador(String termo, String url) {
        this.termo = termo;
        this.url = url;
    }

    @Override
    public Boolean call() {
        return WebScraper.procurarTermo(termo, url);
    }
}
