package threadpool;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PremiersNombresPremiers3 extends Thread{

    //possède un synchronized par défault
    static final Vector<Long> premiersNombresPremiers = new Vector<>();
    static final long borne = 15_000_000;
    static int nbThreads = 4; //nombre de thread/


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        final long start = System.nanoTime();

        ExecutorService executeur = Executors.newFixedThreadPool(nbThreads);
        ExecutorCompletionService<List<Long>> ecs = new ExecutorCompletionService<>(executeur);

        for(int i = 0; i < nbThreads; i++) {
            ecs.submit(new TiragesPremiers((int) (borne/nbThreads),i));
        }



        for(int i = 0; i < nbThreads; i++) {
            premiersNombresPremiers.addAll(ecs.take().get());
        }

        executeur.shutdown();

        final long end = System.nanoTime();
        final long time = (end - start) / 1_000_000;
        System.out.println("Nombre de nombre premiers inférieurs à " + borne +" :");
        System.out.println(premiersNombresPremiers.size());
        System.out.format("Durée du calcul: %.3f s.%n", (double) time/1000);
        System.out.println("nombre de processeur: "+ Runtime.getRuntime().availableProcessors());


    }


}
