package R_concurrent;

// Podemos unir vários CompletableFutures (tarefas) em um único, utilizando os
// métodos `allOf` e `anyOf` para criar a instância com os CompletableFutures
// e tomar algumas ações por meio deles, bastante util quando se quer tomar
// alguma abordagem quando TODAS as tarefas forem concluídas ou quando
// pelomenos alguma foi.
// Exemplo: Várias requisições para API's distintas com o mesmo retorno, assim
// podemos dar continuidade no fluxo de execução assim que pelomenos uma responder.

import R_concurrent.domain.Quote;
import R_concurrent.service.StoreServiceWithDiscount;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Aula245Concurrent_CompletableFuture_allOf_and_anyOf_verificaQuandoAlgumaTarefaFinalizaOuTodasEmTempoRealUnindoVariosCompletableFutureEmUm {
    private static List<String> storesList = List.of("Store 1", "Store 2", "Store 3", "Store 4");
    private static long start; // time para calcular ms da execução
    private static long end;
    public static void main(String[] args) {
        searchPricesWithDiscountWithAsync();
    }

    private static void searchPricesWithDiscountWithAsync() {

        start = System.currentTimeMillis();

        var completableFutures = storesList.stream()
                // Getting the price async of pattern storeName:price:discountCode
                .map(store -> CompletableFuture.supplyAsync(() -> StoreServiceWithDiscount.getPricesSync(store)))
                // Instantiating new Quote object sync from the string generated by getPricesSync(store)
                .map(cf -> cf.thenApply(Quote::newQuote))
                // Applying the discount async
                .map(cf -> cf.thenCompose(quote -> CompletableFuture.supplyAsync(() -> StoreServiceWithDiscount.applyDiscount(quote))))
                .map(cf -> cf.thenAccept(store -> System.out.printf("%s finished in %dms%n", store, System.currentTimeMillis() - start)))
                .toArray(CompletableFuture[]::new);// retorna em array nativo pois vamos passar para um varArgs

        // Finalmente cria o CompletableFuture com os CompletableFutures anteriores:

        // Com allOf todos devem respeitar a regra dos métodos, exemplo:
        // isDone (É finalizado?) se le: TODOS é finalizado?

        // Com anyOf pelomenos um deve deve respeitar a regra dos métodos, exemplo:
        // isDone (É finalizado?) se le: PELOMENOS algum finalizou?

//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(completableFutures);
        CompletableFuture<Object> voidCompletableFuture = CompletableFuture.anyOf(completableFutures);
        voidCompletableFuture.join();
//        boolean isDoneAllCompletableFutures = ;
        System.out.printf("Finished? %b%n", voidCompletableFuture.isDone());

        end = System.currentTimeMillis();
        System.out.printf("Time passed to all executions of CompletableFutures in CompletableFuture: %dms%n", end - start);
    }
}
