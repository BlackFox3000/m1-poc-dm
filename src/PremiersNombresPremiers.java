import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Vector;

public class PremiersNombresPremiers extends Thread{
    //possède un synchronized par défault
    static final Vector<Long> premiersNombresPremiers = new Vector<>();
    static final long borne = 14_000_000;
    static int nbThreads = 4; //nombre de thread
    long part;
    int numPart;

    public static void main(String[] args){
        final long start = System.nanoTime();
        PremiersNombresPremiers[] T = new PremiersNombresPremiers[nbThreads];
        for (int i = 0; i < nbThreads; i++) {
            T[i] = new PremiersNombresPremiers(borne/nbThreads,i+1);
            T[i].start();
        }
        for (int i = 0; i < nbThreads; i++) {
            try {
                T[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        final long end = System.nanoTime();
        final long time = (end - start) / 1_000_000;
        System.out.println("Nombre de nombre premiers inférieurs à " + borne +" :");
        System.out.println(premiersNombresPremiers.size());
        System.out.format("Durée du calcul: %.3f s.%n", (double) time/1000);
        System.out.println("nombre de processeur: "+ Runtime.getRuntime().availableProcessors());
    }

    public PremiersNombresPremiers(long part, int numPart){
        this.part = part;
        this.numPart = numPart;
    }

    public void run(){
        for(long i = (numPart-1)*part; i < part*numPart; i++){
            if(BigInteger.valueOf(i).isProbablePrime(50)) {
                premiersNombresPremiers.add(i);
               // System.out.println(i);
            }
        }
    }
}
