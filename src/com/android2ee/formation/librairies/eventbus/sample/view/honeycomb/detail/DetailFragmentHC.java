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
package com.android2ee.formation.librairies.eventbus.sample.view.honeycomb.detail;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android2ee.formation.librairies.eventbus.sample.R;
import com.android2ee.formation.librairies.eventbus.sample.dao.HumanDao;
import com.android2ee.formation.librairies.eventbus.sample.model.Human;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 *        This class aims to display an human using a fragment
 */
@SuppressLint("NewApi")
public class DetailFragmentHC extends Fragment {

	/**
	 * The constant to pass parameter to the activity
	 */
	public static final String ITEM_ID = "selected_item_id";
	/**
	 * The reference to the picture
	 */
	public int picture1 = R.drawable.picture_human1, picture2 = R.drawable.picture_human2,
			picture3 = R.drawable.picture_human3, picture4 = R.drawable.picture_human4;
	/**
	 * The id of the displayed item
	 */
	public int itemId = DetailActivityHC.ITEM_ID_DEFAULT_VALUE;

	/******************************************************************************************/
	/** LifeCycle **************************************************************************/
	/******************************************************************************************/

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.w("DetailFragmentHC updateData", "updateData:getView :" + getView());
		// as usual, build the view and return it
		View view = inflater.inflate(R.layout.fragment_detail, container, false);
		return view;
	}

	/******************************************************************************************/
	/** Methods **************************************************************************/
	/******************************************************************************************/

	/**
	 * Update the data according to the item ID
	 * 
	 * @param itemId
	 */
	public void updateData(int itemId) {
		Log.w("DetailFragmentHC updateData", "updateData:getView :" + getView());
		View view = getView();
		// check if the view is not null (can occurs when other developpers of the team make
		// nonsense. This avoir nullPointerException
		// Alos check if there is a selected item
		if (null != view && itemId != DetailActivityHC.ITEM_ID_DEFAULT_VALUE) {
			// then retrieve the data to display
			Human hum = HumanDao.instance.getHumanById(itemId);
			// update the view
			((TextView) view.findViewById(R.id.fd_name)).setText(hum.getName());
			((TextView) view.findViewById(R.id.fd_firstName)).setText(hum.getFirstName());
			((TextView) view.findViewById(R.id.fd_nickName)).setText(hum.getNickName());
			((TextView) view.findViewById(R.id.fd_age)).setText("" + hum.getAge());
			int pictId;
			switch (hum.getPictureId()) {
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
			((ImageView) view.findViewById(R.id.fd_picture)).setBackgroundResource(pictId);
		}
	}
}
