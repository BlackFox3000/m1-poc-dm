package threadpool;

import javax.swing.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class TiragesPremiers implements Callable<List<Long>> {
    final long nbTirages;
    final long iterator;
    ArrayList<Long> premiersNombresPremiers = new ArrayList<>();

    //ici on configure le nombre de boucle à faire
    public TiragesPremiers(int nbTirage, int iterateur) {
        nbTirages = nbTirage;
        iterator = iterateur;
    }

    @Override
    public List<Long> call(){
        System.out.println("nbTirage:"+nbTirages*iterator+" to "+nbTirages*(iterator+1)+" iterator:"+iterator);
        //ici on chercher les nombres premiers
        for (long i=nbTirages*iterator; i<nbTirages*(iterator+1) ; i++){
            if(BigInteger.valueOf(i).isProbablePrime(50))
                premiersNombresPremiers.add(i);
        }
        //on retourne une liste de ombre premeir
        return premiersNombresPremiers;

    }


}
