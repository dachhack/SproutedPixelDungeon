/*
 * Pixel Dungeon
 * Copyright (C) 2012-2014  Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.github.dachhack.sprout.levels;

import com.watabou.utils.Random;

public class TownLayouts {
	
	//32X32
	private static final int W = Terrain.WALL;
	private static final int T = Terrain.SHRUB;
	private static final int Z = Terrain.HIGH_GRASS;
	private static final int D = Terrain.DOOR;
	private static final int L = Terrain.LOCKED_DOOR;
	private static final int _ = Terrain.EMPTY; //for readability

	private static final int E = Terrain.EMPTY;
	private static final int X = Terrain.EXIT;

	private static final int M = Terrain.WALL_DECO;
	private static final int P = Terrain.PEDESTAL;
	private static final int F = Terrain.EMPTY_DECO;
	private static final int O = Terrain.EMPTY_SP;
	private static final int A = Terrain.WELL;
	private static final int B = Terrain.BOOKSHELF;

    private static final int U = Terrain.STATUE;
    private static final int S = Terrain.SECRET_DOOR;
   

	
	
	public static final int[] TOWN_LAYOUT =	{     
		M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	_, 	M, 	T, 	M, 	M, 	M, 	M, 	M, 	_, 	_, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	_, 	M, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	W, 	S, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 	M, 	M, 	_, 	M, 	M, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	W, 	W, 	W, 	W, 	W, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	O, 	O, 	O, 	O, 	W, 	F, 	F, 	F, 	F, 	W, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	F, 	F, 	F, 	F, 	W, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	F, 	F, 	F, 	F, 	W, 	M, 	M, 	M, 	M, 
		M, 	M, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	B, 	F, 	F, 	F, 	F, 	W, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	U, 	U, 	U, 	O, 	W, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	B, 	F, 	F, 	F, 	F, 	W, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	U, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	D, 	O, 	O, 	O, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	U, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	_, 	_, 	W, 	W, 	W, 	D, 	W, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	D, 	W, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	O, 	O, 	W, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	E, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	W, 	O, 	O, 	U, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	D, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	W, 	O, 	O, 	U, 	U, 	U, 	U, 	U, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	B, 	B, 	B, 	W, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	W, 	W, 	W, 	W, 	W, 	D, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	O, 	O, 	O, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	D, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	P, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	O, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 
		M, 	M, 	W, 	W, 	W, 	W, 	W, 	W, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	O, 	O, 	O, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 
		M, 	M, 	W, 	F, 	F, 	F, 	F, 	W, 	T, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	O, 	O, 	O, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 
		M, 	M, 	W, 	F, 	F, 	F, 	F, 	T, 	T, 	T, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	B, 	B, 	B, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 
		M, 	M, 	W, 	F, 	F, 	F, 	F, 	T, 	T, 	T, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 
		M, 	M, 	W, 	F, 	F, 	F, 	F, 	W, 	W, 	W, 	W, 	W, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	M, 	M, 	M, 
		M, 	M, 	W, 	F, 	F, 	X, 	F, 	F, 	F, 	F, 	F, 	W, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	Z, 	Z, 	Z, 	Z, 	Z, 	Z, 	Z, 	W, 	M, 	M, 	M, 
		M, 	M, 	W, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	W, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	Z, 	Z, 	Z, 	Z, 	Z, 	Z, 	Z, 	W, 	M, 	M, 	M, 
		M, 	M, 	W, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	W, 	M, 	M, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	Z, 	Z, 	Z, 	Z, 	Z, 	Z, 	Z, 	W, 	M, 	M, 	M, 
		M, 	M, 	W, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	W, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	Z, 	Z, 	Z, 	Z, 	Z, 	Z, 	Z, 	W, 	M, 	M, 	M, 
		M, 	M, 	W, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	W, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	_, 	_, 	M, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	M, 	M, 	M, 
		M, 	M, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	_, 	_, 	_, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 
		M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M, 	M 

		
	};
	
}
