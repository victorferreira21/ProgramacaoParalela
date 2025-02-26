package ThreadsRequestExample;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class TestadorScraping {

    public static void executarBusca() {
        List<String> urls = Arrays.asList(
                "https://www.globo.com",
                "https://www.uol.com.br",
                "https://www.poder360.com.br"
        );

        String termoBusca = "Vasco";
        ExecutorService executor = Executors.newFixedThreadPool(5);
        int totalEncontradas = 0;

        try {
            for (String url : urls) {
                Future<Boolean> resultado = executor.submit(new Garimpador(termoBusca, url));
                if (resultado.get()) {
                    totalEncontradas++;
                    System.out.println("[Encontrado] " + url + " - 100%");
                } else {
                    System.out.println("[Não encontrado] " + url + " - 0%");
                }
            }

            double percentual = ((double) totalEncontradas / urls.size()) * 100;
            System.out.println("Percentual de páginas que contêm o termo: " + percentual + "%");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }
}
