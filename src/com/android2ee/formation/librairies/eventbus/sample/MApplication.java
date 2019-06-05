/**<ul>
 * <li>FragmentStaticTuto</li>
 * <li>com.android2ee.formation.librairies.eventbus.sample</li>
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
package com.android2ee.formation.librairies.eventbus.sample;

import java.io.IOException;

import com.android2ee.formation.librairies.eventbus.sample.view.honeycomb.detail.DetailActivityHC;

import de.greenrobot.event.util.ErrorDialogConfig;
import de.greenrobot.event.util.ErrorDialogFragmentFactory;
import de.greenrobot.event.util.ErrorDialogManager;
import android.app.Application;
import android.util.Log;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 * This class aims to keep the application state when activities die
 * Here it keeps the item id selected
 */
public class MApplication extends Application {
	/**
	 * The selectedItem
	 */
	private int selectedItem=DetailActivityHC.ITEM_ID_DEFAULT_VALUE;
	
	/******************************************************************************************/
	/** Access Every Where **************************************************************************/
	/******************************************************************************************/
	/**
	 * instance of this
	 */
	private static MApplication instance;

	/**
	 * @return The instance of the application
	 */
	public static MApplication getInstance() {
		return instance;
	}
	/******************************************************************************************/
	/** Managing LifeCycle **************************************************************************/
	/******************************************************************************************/

	@Override
	public void onCreate() {
		super.onCreate();
		Log.e("MApplication:onCreate","Application is create");
		instance = this;
		
		//Manage Configuration for ErrorDialog
		ErrorDialogConfig config=new ErrorDialogConfig(getResources(), R.string.default_eventbus_config_error_title, R.string.default_eventbus_config_error_message);
		//Add the Mapping between raised exception and the message to display (you can use string with parameters)
		config.addMapping(IOException.class, R.string.ioexception_eventbus_config_error_message);
		ErrorDialogManager.factory=new ErrorDialogFragmentFactory.Support(config);
	}
	/******************************************************************************************/
	/** Getters/setters **************************************************************************/
	/******************************************************************************************/

	/**
	 * @return the selectedItem
	 */
	public final int getSelectedItem() {
		return selectedItem;
	}
	/**
	 * @param selectedItem the selectedItem to set
	 */
	public final void setSelectedItem(int selectedItem) {
		this.selectedItem = selectedItem;
	}
}
