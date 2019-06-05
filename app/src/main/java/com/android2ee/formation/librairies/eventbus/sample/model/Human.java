/**<ul>
 * <li>FragmentStaticTuto</li>
 * <li>com.android2ee.formation.librairies.eventbus.sample.model</li>
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
package com.android2ee.formation.librairies.eventbus.sample.model;

/**
 * @author Mathias Seguy (Android2EE)
 * @goals
 * This class aims to represents an human
 */
public class Human {
	/**
	 * The id of the object
	 */
	private int id;
	/**
	 * The name
	 */
	private String name;
	/**
	 * The first name
	 */
	private String firstName;
	/**
	 * the nickName
	 */
	private String nickName;
	/**
	 * The picture id (link to ressource)
	 */
	private int pictureId;
	/**
	 * The age
	 */
	private int age;
	/******************************************************************************************/
	/** Constructors **************************************************************************/
	/******************************************************************************************/
	/**
	 * @param name
	 * @param firstName
	 * @param nickName
	 * @param pictureId
	 * @param age
	 */
	public Human(String name, String firstName, String nickName, int pictureId, int age,int id) {
		super();
		this.name = name;
		this.firstName = firstName;
		this.nickName = nickName;
		this.pictureId = pictureId;
		this.age = age;
	}
	/**
	 * Empty constructor
	 */
	public Human() {
		super();
	}
	/******************************************************************************************/
	/** Getters/setters **************************************************************************/
	/******************************************************************************************/
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the firstName
	 */
	public final String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the nickName
	 */
	public final String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public final void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the pictureId
	 */
	public final int getPictureId() {
		return pictureId;
	}
	/**
	 * @param pictureId the pictureId to set
	 */
	public final void setPictureId(int pictureId) {
		this.pictureId = pictureId;
	}
	/**
	 * @return the age
	 */
	public final int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public final void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}


}
