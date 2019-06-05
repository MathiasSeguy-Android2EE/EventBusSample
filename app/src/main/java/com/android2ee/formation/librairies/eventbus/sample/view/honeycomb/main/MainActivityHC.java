/**<ul>
 * <li>FragmentStaticTuto</li>
 * <li>com.android2ee.formation.librairies.eventbus.sample.view.honeycomb</li>
 * <li>16 oct. 2012</li>
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
package com.android2ee.formation.librairies.eventbus.sample.view.honeycomb.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.android2ee.formation.librairies.eventbus.sample.R;
import com.android2ee.formation.librairies.eventbus.sample.model.MyEvents.ItemSelectedEvent;
import com.android2ee.formation.librairies.eventbus.sample.view.honeycomb.detail.DetailActivityHC;
import com.android2ee.formation.librairies.eventbus.sample.view.honeycomb.detail.DetailFragmentHC;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.util.ErrorDialogManager;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to display either a human list and an human detail panel or just the
 *        humanList
 */
@SuppressLint({ "NewApi" })
public class MainActivityHC extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ok build the view, nothing more, every thing is handled by the fragment which displays
		// the data
		setContentView(R.layout.activity_main);
		// Managing ErrorDialog associated to AsyncExecutor of EventBus
		ErrorDialogManager.attachTo(this);
	}

	/******************************************************************************************/
	/** Using EventBus **************************************************************************/
	/******************************************************************************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onResume()
	 */
	@Override
	protected void onResume() {
		super.onResume();
		// register this for the EventBus
		EventBus.getDefault().register(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPause()
	 */
	@Override
	protected void onPause() {
		super.onPause();
		// unregister this for the EventBus
		EventBus.getDefault().unregister(this);
	}

	/**
	 * This method receive the ItemSelectedEvent event
	 * And then call the onItemSelected
	 * 
	 * @param event
	 */
	public void onEventMainThread(ItemSelectedEvent event) {
		onItemSelected(event.getItemPosition());
	}

	/******************************************************************************************/
	/** Managing ItemSelection **************************************************************************/
	/******************************************************************************************/

	/**
	 * An item has been selected within the fragment
	 * As a callback of the fragment you should do something with that information
	 * 
	 * @param itemId
	 */
	public void onItemSelected(int itemId) {
		// Depending on the number of pane we either update the detailFragment either launch the
		// DetailActivity
		boolean twoPane = getResources().getBoolean(R.bool.twoPane);
		if (twoPane) {
			// find the fragment manager
			FragmentManager fm = getFragmentManager();
			// retrieve the DetailFragmentHC
			DetailFragmentHC detailFragmentHC = (DetailFragmentHC) fm.findFragmentById(R.id.detail_fragment);
			if (null == detailFragmentHC) {
				// should not be
				detailFragmentHC = new DetailFragmentHC();
			}
			// and update it
			detailFragmentHC.updateData(itemId);
		} else {
			// Launch the second activity
			Intent detailIntent = new Intent(this, DetailActivityHC.class);
			// add parameter to the intent to pass it to the activity
			detailIntent.putExtra(DetailFragmentHC.ITEM_ID, itemId);
			// launch the intent and start other activity
			startActivity(detailIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// inflate the menu
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
