/**
 * @file Generateur.java
 * @author FDU - MSE
 * @par CREATION
 * @date 
 * @version 1.0 
 * @brief
 */
package com.android2ee.formation.librairies.eventbus.sample.dao;

import java.util.Random;

/**
 * @class Generateur
 * @brief Cette classe nous permet de generer les
 * jeux de donnees.
 * @par DESCRIPTION
 * @author FDU - MSE
 * 
 */
public class Generateur {
  
  /**
   * Cette methode nous permet de generer
   * un int entre 0 et max-1
   * @param max
   * @return int
   */
  public static int generationInt(int max) {
    Random rand = new Random();
    return rand.nextInt(max);
  }
  
  /**
   * Cette methode nous permet de generer 
   * un char compris entres [A..Z]
   * @return char
   */
  public static char generationChar() {
    int nb = generationInt(26);
    char retour;
    nb=nb+65;
    retour=(char)nb;
    return retour;
  }
  
  /**
   * Cette methode nous permet de generer
   * un mot de numLettre.
   * @param numLettre
   * @return String
   */
  public static String generationMot(int numLettre) {
    StringBuilder sb = new StringBuilder();
    //generation de char
    for(int i=0; i<numLettre; i++) {
      sb.append(generationChar());
    }
    return sb.toString();
  }
  /**
   * Cette methode nous permet de generer
   * un mot de numLettre.
	 * @param minNumLettre
	 * @param maxnumLettre
	 * @return
 */
public static String generationMot(int minNumLettre,int maxnumLettre) {
    StringBuilder sb = new StringBuilder();
    if(maxnumLettre<minNumLettre) {
    	maxnumLettre=minNumLettre;
    }
    //generation de char
    for(int i=0; i<minNumLettre; i++) {
      sb.append(generationChar());
    }
    for(int i=minNumLettre; i<maxnumLettre; i++) {
        sb.append(generationChar());
      }
    return sb.toString();
  }
  
  /**
   * Cette methode retourne un mot
   * compose d'un nombre et d'une chaine de
   * caracteres.
   * @param max
   * @param numLettre
   * @return String
   */
  public static String generationMixteIntChar(int max, int numLettre) {
    String retour = new String();
    Integer num = new Integer(generationInt(max));
    /*
     * on fait ce test sur la valeur pour afficher des entiers
     * a deux chiffres
     */
    if (num.compareTo(10) < 0) {
      retour = retour.concat("0"+num);
    }
    else{
      retour = retour.concat(""+num);
    }
    retour = retour.concat(generationMot(numLettre));
    return retour;
  }
  
  /**
   * Cette methode retourne un mot
   * compose d'une chaine de caracteres
   * et d'un nombre.
   * @param max
   * @param numLettre
   * @return String
   */
  public static String generationMixteCharInt(int max, int numLettre) {
    String retour = new String();
    retour = retour.concat(generationMot(numLettre));
    Integer num = new Integer(generationInt(max));
    /*
     * on fait ce test sur la valeur pour afficher des entiers
     * a deux chiffres
     */
    if (num.compareTo(10) < 0) {
      retour = retour.concat("0"+num);
    }
    else{
      retour = retour.concat(""+num);
    }
    return retour;
  }
  
  /**
   * Cette methode nous permet de generer
   * un Boolean.
   * @return boolean
   */
  public static Boolean generationBoolean() {
    int choix = generationInt(2);
    Boolean retour = false;
    if (choix==1) {
      retour = true;
    }
    return retour;
  }
  
  /**
   * Cette methode nous permet de basculer le
   * type primitif int en String.
   * @param var
   * @return String
   */
  public static String intToString(int var) {
    String retour = new String();
    retour = retour.concat(""+var);
    return retour;
  }
  
}
