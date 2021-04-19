package org.example;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicReference;

public class App {

    /**
     * Calcule le chiffre le plus proche de 0 et prendre en priorité le nombre
     * positif si l'écart est le même
     */
    public static void procheZero(int[] tabInt) {
        AtomicReference<OptionalInt> result = new AtomicReference<>(OptionalInt.empty());
        AtomicReference<OptionalInt> ecart = new AtomicReference<>(OptionalInt.empty());
        Arrays.stream(tabInt)
                .distinct()
                .forEach(value -> {
                    int ecartValue = value < 0 ? 0 - value : value;
                    if (ecart.get().isEmpty() || result.get().isEmpty() || ecart.get().getAsInt() > ecartValue || (result.get().getAsInt() < value && (ecart.get().getAsInt() == ecartValue))) {
                        result.set(OptionalInt.of(value));
                        ecart.set(OptionalInt.of(ecartValue));
                    }
                });
        if (result.get().isPresent()) {
            System.out.println("Le chiffre le plus proche de 0 est " + result.get().getAsInt());
        } else {
            System.out.println("il n'y a pas de chiffre");
        }
    }

    public static void plusPetit(int[] tab){
        OptionalInt result = Arrays.stream(tab)
                .min();
        if(result.isPresent()){
            System.out.println("Le plus petit est "+ result.getAsInt());
        } else {
            System.out.println("Pas de nombre dans le tableau");
        }
    }

    public static void plusGrand(int[] tab){
        OptionalInt result = Arrays.stream(tab)
                .max();
        if(result.isPresent()){
            System.out.println("Le plus grand est "+ result.getAsInt());
        } else {
            System.out.println("Pas de nombre dans le tableau");
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        int[] tab = {2, 1, 3, -2, -1, 3, 1};
        procheZero(tab); // result 1
        plusPetit(tab); // result -2
        plusGrand(tab); // result 3
    }
}
