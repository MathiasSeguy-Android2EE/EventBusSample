/**<ul>
 * <li>CocoaHeadsSimple</li>
 * <li>com.android2ee.tuto.simple.cocoaheads</li>
 * <li>3 mai 2012</li>
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
package com.android2ee.formation.librairies.eventbus.sample.view.adapters;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.librairies.eventbus.sample.R;
import com.android2ee.formation.librairies.eventbus.sample.model.Human;
import com.android2ee.formation.librairies.eventbus.sample.model.MyEvents.HumanLoadedEvent;
import com.android2ee.formation.librairies.eventbus.sample.view.honeycomb.detail.DetailActivityHC;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to:
 *        <ul>
 *        <li>show a list view where even and odd lines are differents</li>
 *        </ul>
 */
public class HumanArrayAdapter extends ArrayAdapter<Human> {

	/**
	 * Even position for item
	 */
	private static final int TYPE_ITEM = 1;
	/**
	 * Odd position for item
	 */
	private static final int TYPE_ITEM_ODD = 0;
	/**
	 * The inflater
	 */
	private LayoutInflater mInflater;
	/**
	 * The color of the row
	 */
	private int evenColor, oddColor, selectedColor;
	/**
	 * the selected item position
	 */
	private int selectedPosition = DetailActivityHC.ITEM_ID_DEFAULT_VALUE;

	/**
	 * The reference to the picture
	 */
	public int picture1 = R.drawable.picture_human1, picture2 = R.drawable.picture_human2,
			picture3 = R.drawable.picture_human3, picture4 = R.drawable.picture_human4;
	
	boolean postHoneyComb;

	/******************************************************************************************/
	/** The View Holder **************************************************************************/
	/******************************************************************************************/

	// static to save the reference to the outer class and to avoid access to
	// any members of the containing class
	static class ViewHolder {
		public TextView name = null;
		public TextView firstname = null;
		public ImageView picture = null;
	}

	/******************************************************************************************/
	/** Constructors **************************************************************************/
	/******************************************************************************************/
	/**
	 * Constructor
	 * 
	 * @param context
	 * @param objects
	 */
	public HumanArrayAdapter(Context context, List<Human> objects) {
		super(context, R.layout.array_list, objects);
		// Cache the LayoutInflate to avoid asking for a new one each time.
		mInflater = LayoutInflater.from(context);
		// retrieve the color
		evenColor = context.getResources().getColor(R.color.list_background);
		oddColor = context.getResources().getColor(R.color.list_odd_background);
		selectedColor = context.getResources().getColor(R.color.list_selected_background);
		postHoneyComb=context.getResources().getBoolean(R.bool.postHC);
	}
	
	/**
	 * This method has subscribe to the event HumanLoadedEvent
	 * So it receive such event that carries the humanList to display
	 */
	public void onEventMainThread(HumanLoadedEvent event ) {
		Log.e("HumanArrayAdapter","onEventMainThread HumanLoadedEvent");
		clear();
		if(postHoneyComb) {
			addAll(event.getHumans());
		}else {
			for(Human hum:event.getHumans()) {
				add(hum);
			}
		}
		notifyDataSetChanged();
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Find the human to display
		final Human human = getItem(position);
		// Recycle existing view if passed as parameter
		// This will save memory and time on Android
		// This only works if the base layout for all classes are the same
		View rowView = convertView;
		if (rowView == null) {
			// inflate the view
			rowView = mInflater.inflate(R.layout.array_list, null);
			// Create the holder
			ViewHolder viewHolder = new ViewHolder();
			// initialize it
			viewHolder.name = (TextView) rowView.findViewById(R.id.cell_name);
			viewHolder.firstname = (TextView) rowView.findViewById(R.id.cell_firstname);
			viewHolder.picture = (ImageView) rowView.findViewById(R.id.cell_picture);
			// link view and holder
			rowView.setTag(viewHolder);

		}
		// then update the holder (and doing so the view)
		final ViewHolder holder = (ViewHolder) rowView.getTag();
		holder.name.setText(human.getName());
		holder.firstname.setText(human.getFirstName());
		int pictId;
		switch (human.getPictureId()) {
		case 1:
			pictId = picture1;
			break;
		case 2:
			pictId = picture2;
			break;
		case 3:
			pictId = picture3;
			break;
		case 4:
			pictId = picture4;
			break;
		default:
			pictId = picture1;
			break;
		}
		holder.picture.setBackgroundResource(pictId);
		// Set its background according to its position
		if (position == selectedPosition) {
			rowView.setBackgroundColor(selectedColor);
		} else {
			switch (getItemViewType(position)) {
			case TYPE_ITEM:
				rowView.setBackgroundColor(evenColor);
				break;
			case TYPE_ITEM_ODD:
				rowView.setBackgroundColor(oddColor);
				break;
			}
		}
		return rowView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.BaseAdapter#getItemViewType(int)
	 */
	@Override
	public int getItemViewType(int position) {
		return (position % 2 == 0) ? TYPE_ITEM : TYPE_ITEM_ODD;
	}

	/**
	 * Set the position of the selected item in the list to highlight it
	 * 
	 * @param position
	 */
	public void setSelectedItem(int position) {
		// set the position of the selected element
		selectedPosition = position;
		// and ask gently to rebuild the view
		notifyDataSetChanged();
	}
}
