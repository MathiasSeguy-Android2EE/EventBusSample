/**<ul>
 * <li>FragmentStaticTuto</li>
 * <li>com.android2ee.formation.librairies.eventbus.sample.dao</li>
 * <li>17 oct. 2012</li>
 * 
 * <li>======================================================</li>
 *
 * <li>Projet : Mathias Seguy Project</li>
 * <li>Produit par MSE.</li>
 *
 /**
 * <ul>
 * Android Tutorial, An <strong>Android2EE</strong>'s project.</br> 
 * Produced by <strong>Dr. Mathias SEGUY</strong>.</br>
 * Delivered by <strong>http://android2ee.com/</strong></br>
 *  Belongs to <strong>Mathias Seguy</strong></br>
 ****************************************************************************************************************</br>
 * This code is free for any usage except training and can't be distribute.</br>
 * The distribution is reserved to the site <strong>http://android2ee.com</strong>.</br>
 * The intelectual property belongs to <strong>Mathias Seguy</strong>.</br>
 * <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * 
 * *****************************************************************************************************************</br>
 *  Ce code est libre de toute utilisation mais n'est pas distribuable.</br>
 *  Sa distribution est reservée au site <strong>http://android2ee.com</strong>.</br> 
 *  Sa propriété intellectuelle appartient à <strong>Mathias Seguy</strong>.</br>
 *  <em>http://mathias-seguy.developpez.com/</em></br> </br>
 * *****************************************************************************************************************</br>
 */
package com.android2ee.formation.librairies.eventbus.sample.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.util.SparseArray;

import com.android2ee.formation.librairies.eventbus.sample.model.Human;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 * This class aims to generate human dummy data 
 */
public class DummyHumanFactory {
	/**
	 * The id of the current human create
	 */
	private static int id=0;
	/**
	 * The list of humans
	 */
	private static List<Human> dumHum;
	/**
	 * The map that link humanId and human
	 */
	private static SparseArray<Human> mapIdToHuman;
	
	/**
	 * @return a dummy list of human
	 */
	public static List<Human> getHumans(){
		int lenght=Generateur.generationInt(72)+5;
		dumHum=new ArrayList<Human>(lenght);
		mapIdToHuman=new SparseArray<Human>(lenght);
		Human current;
		for(int i=0;i<lenght;i++) {
			current=generateHuman();
			dumHum.add(current);
			mapIdToHuman.put(current.getId(), current);
		}
		return dumHum;
	}
	
	/**
	 * @return the mapIdToHuman
	 */
	public static final SparseArray<Human> getMapIdToHuman() {
		return mapIdToHuman;
	}

	
	/**
	 * @return an human
	 */
	private static Human generateHuman() {
		Human hum=new Human();
		hum.setId(id);
		hum.setName(Generateur.generationMot(5, 8));
		hum.setFirstName(Generateur.generationMot(2, 7));
		hum.setNickName(Generateur.generationMot(5, 15));
		hum.setAge(Generateur.generationInt(65));
		hum.setPictureId(Generateur.generationInt(4)+1);
		id++;
		return hum;
	}
}
