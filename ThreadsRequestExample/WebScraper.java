package ThreadsRequestExample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.Callable;

public class WebScraper {

    public static boolean procurarTermo(String termo, String url) {
        try {
            Document documento = Jsoup.connect(url).get();
            Elements elementos = documento.getElementsContainingOwnText(termo);
            return elementos != null && !elementos.isEmpty();
        } catch (IOException e) {
            System.err.println("Erro ao acessar: " + url);
            return false;
        }
    }
}