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
package com.android2ee.formation.librairies.eventbus.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android2ee.formation.librairies.eventbus.sample.view.honeycomb.main.MainActivityHC;
import com.android2ee.formation.librairies.eventbus.sample.view.legacy.main.MainActivityLegacy;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to implements the parallel activity pattern
 */
public class MainActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Then do the rest
		Intent startActivityIntent = null;
		// find the version :
		boolean postHC = getResources().getBoolean(R.bool.postHC);
		// First test if the user exists

		if (postHC) {
			Log.e("MainActivity", "MainActivityHC Launched");
			startActivityIntent = new Intent(this, MainActivityHC.class);
		} else {
			Log.e("MainActivity", "MainActivityLegacy Launched");
			startActivityIntent = new Intent(this, MainActivityLegacy.class);
		}

		startActivity(startActivityIntent);
		finish();
	}

}
