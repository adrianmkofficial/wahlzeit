/*
 * Copyright (c) 2017 Adrian MK Fürst
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Serialize;
import com.googlecode.objectify.annotation.Subclass;

/**
 * SodasPhoto represents a user-provided (uploaded) photo of a soda.
 */
@Subclass
public class SodaPhoto extends Photo {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Serialize
	private Soda soda;

	public SodaPhoto() {
		super();
	}

	public SodaPhoto(PhotoId photoId) {
		super(photoId);
	}

	/**
	 * @methodtype constructor
	 * @param soda
	 */
	public SodaPhoto(Soda soda) throws IllegalArgumentException {
		super();
		if (soda == null)
		{
			throw new IllegalArgumentException( "Soda can not be null!");
		}
		this.soda = soda;
	}

	/**
	 * @methodtype get
	 */
	public Soda getSoda() {
		return soda;
	}

	/**
	 * @methodtype set
	 */
	public void setSoda(Soda soda) {
		if (soda == null)
		{
			throw new IllegalArgumentException( "Soda can not be null!");
		}
		this.soda = soda;
	}
}