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
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android2ee.formation.librairies.eventbus.sample.MApplication;
import com.android2ee.formation.librairies.eventbus.sample.R;
import com.android2ee.formation.librairies.eventbus.sample.dao.HumanDao;
import com.android2ee.formation.librairies.eventbus.sample.model.Human;
import com.android2ee.formation.librairies.eventbus.sample.model.MyEvents.HumanDaoGetHumansEvent;
import com.android2ee.formation.librairies.eventbus.sample.model.MyEvents.HumanLoadedEvent;
import com.android2ee.formation.librairies.eventbus.sample.model.MyEvents.ItemSelectedEvent;
import com.android2ee.formation.librairies.eventbus.sample.view.adapters.HumanArrayAdapter;
import com.android2ee.formation.librairies.eventbus.sample.view.honeycomb.detail.DetailActivityHC;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li></li>
 *        </ul>
 */
@SuppressLint("NewApi")
public class MainFragmentHC extends Fragment {
	/**
	 * The ListView
	 */
	private ListView listView;
	/**
	 * The arrayAdapter
	 */
	private HumanArrayAdapter arrayAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.w("SpeakerDetailFragment onCreateView", "getView :" + getView());
		// inflate the view
		View view = inflater.inflate(R.layout.fragment_list, container, false);
		// Retrieve the ListView, EditText and Button
		listView = (ListView) view.findViewById(R.id.humanListView);
		// Define the list of human to be managed by the adapter
		List<Human> list;
		// Register the array within EventBus
//		EventBus.getDefault().register(this);
		EventBus.getDefault().registerSticky(this);
		HumanLoadedEvent event = (HumanLoadedEvent) EventBus.getDefault().getStickyEvent(HumanLoadedEvent.class);
		if (event == null || event.getHumans() == null) {
			Log.e("MainFragmentHC", "Using Normal Events");
			// Create the list
			list = new ArrayList<Human>();
		} else {
			Log.e("MainFragmentHC", "Using StickyEvents");
			// create the list
			list = event.getHumans();
		}
		// then create the Adapter
		arrayAdapter = new HumanArrayAdapter(getActivity(), list);
		// And register it within EventBus
		EventBus.getDefault().register(arrayAdapter);
		if (event == null || event.getHumans() == null) {
			// Ask for loading data-> handle in this class by onEvent(HumanDaoGetHumansEvent event)
			EventBus.getDefault().post(new HumanDaoGetHumansEvent());
		} else {
			// and remove the sticky event from memory if not usefull anymore
			// here it's a bad exemple
			EventBus.getDefault().removeStickyEvent(HumanLoadedEvent.class);
		}
		// bind the listView and its adapter.
		listView.setAdapter(arrayAdapter);
		// Add a listener on the listView
		listView.setClickable(true);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onItemSelected(position);
			}
		});
		return view;
	}

	/******************************************************************************************/
	/** Managing EventBus **************************************************************************/
	/******************************************************************************************/

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onDestroyView()
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		EventBus.getDefault().unregister(arrayAdapter);
		EventBus.getDefault().unregister(this);
	}

	/**
	 * Ask the Dao to post Humans to subscriber (the array adapter)
	 * 
	 * @param event
	 */
	public void onEventBackgroundThread(HumanDaoGetHumansEvent event) {
		Log.e("MainFragmentHC", "onEventBackgroundThread HumanDaoGetHumansEvent");
		HumanDao.instance.postHumans();
	}

	/******************************************************************************************/
	/** Managing ItemSelection **************************************************************************/
	/******************************************************************************************/
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onResume()
	 */
	@Override
	public void onResume() {
		super.onResume();
		// Update the selected item (the one that has been stored in the application object when the
		// user has selected an item before the activity has been destroyed and recreated due to a
		// Configuration change)
		// first find if it a two pane configuration or not
		Boolean twoPanes = getResources().getBoolean(R.bool.twoPane);
		// then retrieve the selected item
		int selectedItem = MApplication.getInstance().getSelectedItem();
		if (twoPanes) {
			// then make a full update, it will update the DetailFragmentHC and the arrayAdapter
			if (selectedItem != DetailActivityHC.ITEM_ID_DEFAULT_VALUE) {
				onItemSelected(selectedItem);
			}
		} else {
			// then do a gentle update (if we update the parent activity, it won't update the
			// DetailFragmentHC that is not displayed, but launch the DetailActivity and it's not
			// the
			// goal here)
			if (selectedItem != DetailActivityHC.ITEM_ID_DEFAULT_VALUE) {
				onItemSelectedSimple(selectedItem);
			}
		}
	}

	/**
	 * An item has been updated, notify the change to every body
	 * 
	 * @param position
	 *            of the item
	 */
	public void onItemSelected(int position) {
		// Notify the parent that a new item has been selected
		EventBus.getDefault().post(new ItemSelectedEvent(position));
		// notify the arrayAdapter that an item has been selected
		arrayAdapter.setSelectedItem(position);
		// and notify the Application object too, it will persist the information when activity will
		// be destroyed and recreated
		MApplication.getInstance().setSelectedItem(position);
	}

	/**
	 * An item has been updated, notify only the adapter of the change
	 * 
	 * @param position
	 *            of the item
	 */
	public void onItemSelectedSimple(int position) {
		arrayAdapter.setSelectedItem(position);
	}
}
