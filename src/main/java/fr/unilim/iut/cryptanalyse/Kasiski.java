package fr.unilim.iut.cryptanalyse;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Kasiski
 */
public class Kasiski {

    public static HashMap<String, ArrayList> findRepetitions(String encryptedText){
        HashMap<String, ArrayList> listRepetitions = new HashMap<>();
        int j = encryptedText.length() - 1;
        int fromIndex;
        String sequence;

        while (j > 0) {
            int i = 0;

            while (i + j < encryptedText.length()) {
                sequence = encryptedText.substring(i, i + j);
                fromIndex = 0;
                listRepetitions.put(sequence, new ArrayList<Integer>());

                while ((fromIndex = encryptedText.indexOf(sequence, fromIndex)) != -1) {

                    System.out.println("Found at index: " + fromIndex);
                    System.out.println("Searching : " + sequence);

                    listRepetitions.get(sequence).add(fromIndex);

                    fromIndex++;

                }

                // On ne s'embête pas avec ceux qui sont seuls
                if (listRepetitions.get(sequence).size() == 1) {
                    listRepetitions.remove(sequence);
                }

                i += 1;
            }
            j -= 1;
        }

        return listRepetitions;
    }

    public static int estimateKeySize(String encryptedText) {

        // On récupère la liste des répétitions de caractères
        HashMap<String, ArrayList> listRepetitions = findRepetitions(encryptedText);

        // On cherche la plus grande
        int length = 0;
        for (String str : listRepetitions.keySet()) {
            if (str.length() > length) {
                length = str.length();
            }
        }

        for (String str : listRepetitions.keySet()) {
            if (str.length() == length) {
                ArrayList<Integer> list = new ArrayList<>();
                for (Object position : listRepetitions.get(str)) {
                    list.add((int) position);
                    System.out.println(position);
                }
                System.out.println("PPCM : " + findGCD(list.toArray()));
            }
        }
        

        System.out.println(encryptedText);
        System.out.println(listRepetitions.toString());

        return 0;

    }

    /**
     * Méthode pour obtenir le plus grand commun diviseur (PGCD) de deux nombres
     * @param a
     * @param b
     * @return : PGCD(a,b)
     */
    private static int gcd(int a, int b) 
    { 
        if (a == 0) 
            return b; 
        return gcd(b % a, a); 
    } 
    
    /**
     * Méthode pour obtenir le PGCD d'une liste d'objets
     * @param arr : doivent être typecastable en entier
     * @return : PGCD(0, 1, ..., n)
     */
    private static int findGCD(Object arr[]) 
    { 
        int result = (int) arr[0]; 
        for (int i = 1; i < arr.length; i++) 
        { 
            result = gcd((int) arr[i], result); 
    
            if(result == 1) 
            { 
                return 1; 
            } 
        } 
        return result; 
    } 
}