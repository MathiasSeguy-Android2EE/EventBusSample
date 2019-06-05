/**<ul>
 * <li>FragmentStaticTuto</li>
 * <li>com.android2ee.formation.librairies.eventbus.sample.model</li>
 * <li>7 août 2013</li>
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
package com.android2ee.formation.librairies.eventbus.sample.model;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 * This class aims to:
 * <ul><li></li></ul>
 */
public class MyEvents {
	
	public static class HumanDaoGetHumansEvent{}

	public static class HumanLoadedEvent {
		List<Human> humans;

		/**
		 * @param humans
		 */
		public HumanLoadedEvent(List<Human> humans) {
			super();
			this.humans = humans;
		}

		/**
		 * @return the humans
		 */
		public final List<Human> getHumans() {
			return humans;
		}

		/**
		 * @param humans
		 *            the humans to set
		 */
		public final void setHumans(List<Human> humans) {
			this.humans = humans;
		}

	}

	public static class ItemSelectedEvent {
		private int itemPosition;

		/**
		 * @param itemPosition
		 */
		public ItemSelectedEvent(int itemPosition) {
			super();
			this.itemPosition = itemPosition;
		}

		/**
		 * @return the itemPosition
		 */
		public final int getItemPosition() {
			return itemPosition;
		}

		/**
		 * @param itemPosition the itemPosition to set
		 */
		public final void setItemPosition(int itemPosition) {
			this.itemPosition = itemPosition;
		}
		
	}
}
