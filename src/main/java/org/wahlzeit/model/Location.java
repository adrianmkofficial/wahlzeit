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

import java.util.Objects;

import org.wahlzeit.model.Coordinate;

public class Location {
	
	private Coordinate coordinate;

	/**
	 * @methodtype constructor
	 */
	public Location(Coordinate coordinate) {
		this.coordinate = coordinate;
	}
	
	/**
	 * @methodtype get
	 */
	public Coordinate getCoordinate() {
		return this.coordinate;
	}
	
	/**
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate c) {
		this.coordinate = c;
	}
	
	@Override
	public boolean equals(Object inputLocation) {
		if(!(inputLocation instanceof Location)) {
			return false;
		}
		Location loc = (Location)inputLocation;
		return this.coordinate.equals(loc.coordinate);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.coordinate);
	}

	
}
