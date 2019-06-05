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

import java.io.IOException;
import java.util.List;

import android.util.Log;
import android.util.SparseArray;

import com.android2ee.formation.librairies.eventbus.sample.model.Human;
import com.android2ee.formation.librairies.eventbus.sample.model.MyEvents.HumanLoadedEvent;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.util.AsyncExecutor;
import de.greenrobot.event.util.AsyncExecutor.RunnableEx;
import de.greenrobot.event.util.ThrowableFailureEvent;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to manage Acces to Human objects
 */
public enum HumanDao {

	/**
	 * the singleton
	 */
	instance;
	/**
	 * The human list managed by the Dao
	 */
	private List<Human> humans;
	/**
	 * The map that link humanId and human
	 */
	private SparseArray<Human> mapIdToHuman;

	/**
	 * private constructor
	 */
	private HumanDao() {
		humans = DummyHumanFactory.getHumans();
		mapIdToHuman = DummyHumanFactory.getMapIdToHuman();
	}

	/**
	 * Return the Human with id id
	 * 
	 * @param id
	 * @return
	 */
	public Human getHumanById(int id) {
		return mapIdToHuman.get(Integer.valueOf(id));
	}

	/**
	 * @return the HumanList
	 */
	public List<Human> getHumans() {
		return humans;
	}

	/**
	 * post the list of HumanList
	 */
	public void postHumans() {
		Log.e("HumanDao", "postHumans");
		EventBus.getDefault().postSticky(new HumanLoadedEvent(humans));
		AsyncExecutor.create().execute(new RunnableEx() {
			@Override
			public void run() throws Exception {
				//Does nothing when running on HoneyComb and+
				EventBus.getDefault().post(new ThrowableFailureEvent(new IOException()));
				// throw do not work when running on HoneyComb and+
				 throw new IOException();
				// Do a Remote stuff
				// then post the result
//				EventBus.getDefault().post(new HumanLoadedEvent(humans));
			}
		});
	}

}
