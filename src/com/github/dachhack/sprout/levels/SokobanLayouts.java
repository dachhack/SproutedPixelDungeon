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

public class SokobanLayouts {
	
	//32X32
	private static final int W = Terrain.WALL;
	private static final int T = Terrain.SHRUB;
	private static final int Z = Terrain.HIGH_GRASS;
	private static final int D = Terrain.DOOR;
	private static final int L = Terrain.LOCKED_DOOR;
	private static final int _ = Terrain.EMPTY; //for readability

	//private static final int T = Terrain.INACTIVE_TRAP;

	private static final int E = Terrain.EMPTY;
	//private static final int X = Terrain.EXIT;

	//private static final int M = Terrain.WALL_DECO;
	//private static final int P = Terrain.PEDESTAL;
	
	private static final int A = Terrain.SOKOBAN_SHEEP;
	private static final int X = Terrain.CORNER_SOKOBAN_SHEEP;
	private static final int C = Terrain.SWITCH_SOKOBAN_SHEEP;
	private static final int B = Terrain.BLACK_SOKOBAN_SHEEP;
	private static final int H = Terrain.SOKOBAN_HEAP;
    private static final int I = Terrain.SOKOBAN_ITEM_REVEAL;
    private static final int F = Terrain.FLEECING_TRAP;
    private static final int U = Terrain.STATUE;
    private static final int G = Terrain.CHANGE_SHEEP_TRAP;
    private static final int S = Terrain.SECRET_DOOR;
    private static final int R = Terrain.PORT_WELL;
    private static final int V = Terrain.SOKOBAN_PORT_SWITCH;

	public static final int[] SOKOBAN_INTRO_LEVEL =	{      
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	E, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	A, 	U, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	S, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	F, 	U, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	_, 	W, 	_, 	C, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	U, 	_, 	D, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	G, 	_, 	_, 	_, 	G, 	_, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	U, 	A, 	U, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	_, 	_, 	S, 	_, 	_, 	_, 	_, 	D, 	F, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	H, 	W, 	W, 	U, 	U, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	F, 	U, 	_, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	F, 	A, 	_, 	W, 	_, 	_, 	_, 	_, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	F, 	U, 	_, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	U, 	X, 	U, 	W, 	_, 	_, 	_, 	_, 	W, 	_, 	C, 	_, 	_, 	U, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	F, 	U, 	_, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	U, 	U, 	F, 	D, 	_, 	_, 	C, 	_, 	W, 	_, 	G, 	_, 	_, 	F, 	D, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	G, 	U, 	F, 	U, 	G, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	S, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	_, 	_, 	_, 	_, 	U, 	W, 	C, 	G, 	G, 	_, 	W, 	W, 	W, 	W, 	W, 	C, 	U, 	H, 	U, 	C, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	D, 	_, 	_, 	_, 	_, 	_, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	S, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	U, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	S, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	U, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	_, 	B, 	_, 	_, 	_, 	D, 	F, 	_, 	_, 	_, 	D, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	D, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	U, 	U, 	U, 	_, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	_, 	_, 	R, 	_, 	_, 	W, 	B, 	B, 	_, 	_, 	W, 	_, 	G, 	_, 	_, 	W, 	_, 	_, 	_, 	_, 	W, 	_, 	H, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	B, 	W, 	_, 	F, 	_, 	A, 	I, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	U, 	U, 	U, 	_, 	W, 	_, 	G, 	_, 	_, 	W, 	_, 	_, 	_, 	_, 	D, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	H, 	F, 	_, 	_, 	W, 	_, 	G, 	_, 	_, 	W, 	_, 	I, 	A, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	D, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	C, 	_, 	_, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	L, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	_, 	_, 	X, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	_, 	_, 	I, 	V, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W 

	};

	public static final int[] SOKOBAN_CASTLE =	{      
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	I, 	T, 	I, 	T, 	I, 	T, 	I, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	W, 
		W, 	T, 	T, 	W, 	S, 	W, 	W, 	W, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	W, 	W, 	W, 	W, 	W, 	T, 	T, 	W, 
		W, 	T, 	T, 	W, 	R, 	U, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	F, 	H, 	W, 	T, 	T, 	W, 
		W, 	T, 	T, 	W, 	U, 	U, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	F, 	F, 	W, 	T, 	T, 	W, 
		W, 	T, 	T, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	T, 	T, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	W, 	H, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	T, 	T, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	G, 	_, 	W, 	F, 	G, 	C, 	F, 	_, 	_, 	F, 	_, 	B, 	W, 	F, 	W, 	F, 	W, 	F, 	F, 	H, 	F, 	H, 	F, 	F, 	F, 	F, 	W, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	H, 	_, 	S, 	F, 	F, 	X, 	F, 	_, 	C, 	U, 	_, 	_, 	W, 	_, 	W, 	_, 	W, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	S, 	_, 	X, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	_, 	W, 	F, 	F, 	X, 	F, 	G, 	C, 	U, 	_, 	_, 	W, 	_, 	W, 	_, 	W, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	W, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	F, 	F, 	W, 	H, 	F, 	_, 	F, 	G, 	H, 	U, 	_, 	_, 	W, 	_, 	W, 	_, 	W, 	_, 	X, 	X, 	X, 	X, 	X, 	_, 	_, 	_, 	W, 	F, 	F, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	L, 	W, 	W, 	W, 	W, 	_, 	B, 	_, 	W, 	W, 	W, 	W, 	D, 	W, 	W, 	W, 	W, 	S, 	W, 	W, 	S, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	F, 	F, 	F, 	_, 	W, 	W, 	_, 	W, 	_, 	W, 	W, 	H, 	F, 	U, 	F, 	H, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	F, 	U, 	F, 	U, 	W, 	W, 	_, 	W, 	_, 	W, 	W, 	U, 	U, 	_, 	U, 	U, 	W, 	W, 	W, 	W, 	W, 	U, 	_, 	H, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	X, 	G, 	X, 	_, 	W, 	W, 	_, 	W, 	_, 	W, 	W, 	_, 	G, 	_, 	G, 	_, 	W, 	W, 	W, 	W, 	W, 	S, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	C, 	G, 	C, 	_, 	W, 	W, 	_, 	W, 	_, 	W, 	W, 	C, 	G, 	_, 	G, 	C, 	W, 	W, 	H, 	H, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	D, 	W, 	W, 	W, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	S, 	W, 	W, 	W, 	W, 	_, 	_, 	X, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	U, 	U, 	U, 	C, 	L, 	_, 	W, 	W, 	W, 	F, 	W, 	W, 	W, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	F, 	W, 	W, 	W, 	W, 	U, 	_, 	U, 	U, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	H, 	F, 	G, 	_, 	W, 	_, 	W, 	W, 	W, 	F, 	W, 	W, 	W, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	F, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	U, 	U, 	U, 	B, 	W, 	_, 	W, 	W, 	W, 	G, 	G, 	I, 	W, 	W, 	_, 	W, 	_, 	W, 	W, 	_, 	G, 	F, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	X, 	_, 	C, 	W, 	W, 	G, 	W, 	G,  W, 	W, 	C, 	A, 	G, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	_, 	U, 	U, 	F, 	D, 	_, 	W, 	W, 	W, 	_, 	_, 	C, 	_, 	G, 	X, 	C, 	X, 	G, 	_, 	_, 	C, 	_, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	I, 	C, 	_, 	F, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	E, 	C, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	G, 	X, 	_, 	_, 	W, 	X, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	G, 	X, 	C, 	X, 	G, 	_, 	_, 	_, 	_, 	_, 	X, 	_, 	W, 	W, 	W, 	L, 	W, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	_, 	U, 	U, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	G, 	W, 	G, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	F, 	F, 	F, 	F, 	F, 	U, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	U, 	U, 	_, 	U, 	U, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	U, 	U, 	_, 	_, 	W, 	_, 	W, 	F, 	F, 	F, 	F, 	F, 	U, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	_, 	G, 	X, 	G, 	I, 	S, 	S, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	_, 	T, 	W, 	W, 	_, 	W, 	R, 	L, 	F, 	F, 	X, 	_, 	W, 	_, 	W, 	F, 	F, 	H, 	F, 	F, 	U, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	U, 	F, 	_, 	F, 	U, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	H, 	U, 	G, 	G, 	D, 	_, 	W, 	F, 	F, 	F, 	F, 	F, 	U, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	X, 	_, 	_, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	F, 	U, 	_, 	C, 	W, 	_, 	W, 	F, 	F, 	F, 	F, 	F, 	U, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	W, 	W, 	U, 	_, 	X, 	_, 	U, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	S, 	W, 	W, 	W, 	W, 	_, 	W, 	_, 	X, 	X, 	_, 	_, 	_, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	A, 	W, 	W, 	W, 	W, 	D, 	W, 	W, 	W, 	W, 	L, 	W, 	W, 	W, 	_, 	W, 	_, 	W, 	W, 	H, 	S, 	I, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	G, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	G, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	L, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	U, 	_, 	U, 	_, 	F, 	X, 	U, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	U, 	_, 	U, 	S, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	U, 	F, 	U, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	X, 	G, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	X, 	_, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	I, 	H, 	C, 	W, 	W, 	W, 	_, 	U, 	_, 	U, 	_, 	U, 	_, 	U, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	R, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	_, 	U, 	_, 	U, 	_, 	U, 	W, 	_, 	B, 	_, 	W, 	W, 	W, 	W, 	X, 	X, 	X, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	_, 	X, 	_, 	_, 	_, 	_, 	B, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	U, 	_, 	U, 	_, 	U, 	F, 	U, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	X, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	X, 	X, 	X, 	W, 	W, 	W, 	_, 	U, 	_, 	U, 	F, 	U, 	F, 	U, 	W, 	_, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	I, 	H, 	I, 	W, 	W, 	W, 	_, 	_, 	C, 	G, 	F, 	F, 	H, 	I, 	W, 	F, 	W, 	F, 	W, 	W, 	W, 	W, 	_, 	H, 	V, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	T, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	W, 	H, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	T, 	W, 	W, 
		W, 	T, 	_, 	W, 	F, 	F, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	F, 	F, 	W, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	H, 	F, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	F, 	H, 	S, 	T, 	W, 	W, 
		W, 	T, 	T, 	W, 	W, 	W, 	W, 	W, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	W, 	W, 	W, 	W, 	W, 	T, 	W, 	W, 
		W, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	T, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W

	};
	
	public static final int[] SOKOBAN_TELEPORT_LEVEL =	{      
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	R, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	F, 	H, 	H, 	F, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	_, 	X, 	H, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	F, 	F, 	F, 	F, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	B, 	F, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	G, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	X, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	R, 	S, 	_, 	L, 	B, 	W, 	F, 	R, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	R, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	I, 	F, 	R, 	W, 	W, 	W, 	W, 	F, 	H, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	F, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	A, 	X, 	_, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	_, 	F, 	F, 	W, 	W, 	W, 	W, 	F, 	F, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	F, 	F, 	_, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	_, 	X, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	R, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	F, 	F, 	R, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	_, 	_, 	H, 	W, 	W, 	W, 	W, 	_, 	R, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	R, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	G, 	G, 	G, 	G, 	F, 	F, 	F, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	X, 	A, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	V, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	X, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	_, 	_, 	_, 	I, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	E, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	C, 	W, 	_, 	V, 	X, 	_, 	W, 	W, 	_, 	X, 	V, 	_, 	W, 	W, 	W, 	W, 
		W, 	W, 	_, 	R, 	W, 	W, 	W, 	_, 	R, 	X, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	R, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	G, 	W, 	W, 	_, 	_, 	_, 	R, 	W, 	W, 	R, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 
		W, 	W, 	_, 	X, 	W, 	W, 	W, 	_, 	_, 	I, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	X, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	C, 	G, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	F, 	F, 	F, 	W, 	W, 	W, 	W, 	H, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	C, 	G, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	H, 	_, 	W, 	W, 	W, 	W, 	S, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	_, 	_, 	_, 	R, 	W, 	W, 	R, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 
		W, 	W, 	C, 	G, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	R, 	_, 	U, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	_, 	V, 	X, 	_, 	W, 	W, 	_, 	X, 	V, 	_, 	W, 	W, 	W, 	W, 
		W, 	W, 	U, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	A, 	R, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	I, 	_, 	_, 	_, 	W, 	W, 	_, 	_, 	_, 	H, 	W, 	W, 	W, 	W, 
		W, 	W, 	U, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	U, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	C, 	R, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	U, 	_, 	_, 	_, 	_, 	_, 	W, 	_, 	_, 	W, 	W, 	_, 	X, 	_, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	U, 	_, 	U, 	_, 	_, 	_, 	L, 	_, 	H, 	W, 	W, 	G, 	G, 	G, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	U, 	I, 	U, 	I, 	_, 	_, 	W, 	_, 	_, 	W, 	W, 	_, 	_, 	I, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	L, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	L, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	_, 	_, 	_, 	_, 	F, 	F, 	F, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	R, 	H, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	I, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	_, 	_, 	_, 	U, 	_, 	U, 	_, 	_, 	U, 	_, 	U, 	_, 	_, 	_, 	_, 	_, 	_, 	U, 	_, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	U, 	_, 	U, 	_, 	_, 	U, 	_, 	U, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	_, 	U, 	U, 	U, 	_, 	U, 	F, 	F, 	U, 	_, 	U, 	U, 	U, 	_, 	_, 	_, 	_, 	U, 	_, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	L, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	G, 	U, 	F, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	F, 	U, 	G, 	_, 	_, 	_, 	U, 	I, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	F, 	F, 	F, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	G, 	F, 	_, 	_, 	X, 	_, 	X, 	X, 	_, 	X, 	_, 	_, 	F, 	G, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	G, 	U, 	F, 	V, 	_, 	V, 	_, 	_, 	V, 	_, 	V, 	F, 	U, 	G, 	_, 	_, 	_, 	U, 	_, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	H, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	F, 	F, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	F, 	F, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	_, 	U, 	U, 	U, 	U, 	U, 	U, 	U, 	U, 	U, 	U, 	U, 	U, 	_, 	_, 	_, 	_, 	U, 	_, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	X, 	_, 	_, 	X, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	I, 	U, 	_, 	_, 	_, 	_, 	_, 	X, 	_, 	_, 	X, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	U, 	I, 	W, 	W, 
		W, 	W, 	W, 	_, 	_, 	H, 	I, 	H, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	V, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 
		W, 	W, 	W, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	U, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	U, 	_, 	W, 	W, 
		W, 	W, 	W, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	_, 	W, 	W, 	W, 	W, 	U, 	U, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 
		W, 	W, 	W, 	_, 	X, 	X, 	R, 	C, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	_, 	_, 	_, 	R, 	S, 	_, 	U, 	_, 	U, 	_, 	U, 	_, 	U, 	_, 	U, 	_, 	U, 	_, 	U, 	_, 	U, 	_, 	U, 	_, 	U, 	_, 	W, 	W, 
		W, 	W, 	W, 	_, 	_, 	G, 	G, 	G, 	I, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	_, 	W, 	W, 	W, 	W, 	H, 	U, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	H, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W 


	};

	public static final int[] SOKOBAN_PUZZLE_LEVEL =	{ 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	R, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	_, 	_, 	_, 	_, 	U, 	S, 	R, 	W, 	W, 	W, 	_, 	A, 	_, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	C, 	U, 	H, 	U, 	C, 	C, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	H, 	H, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	U, 	I, 	U, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	G, 	G, 	U, 	F, 	U, 	G, 	G, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	H, 	H, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	U, 	I, 	U, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	S, 	_, 	G, 	G, 	U, 	F, 	U, 	G, 	G, 	V, 	S, 	_, 	_, 	U, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	_, 	_, 	_, 	_, 	U, 	W, 	W, 	W, 	W, 	W, 	U, 	I, 	U, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	G, 	U, 	W, 	W, 	C, 	I, 	U, 	F, 	U, 	I, 	C, 	W, 	W, 	W, 	W, 	G, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	I, 	U, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	V, 	W, 	W, 	G, 	G, 	U, 	F, 	U, 	G, 	G, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	I, 	U, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	C, 	_, 	U, 	F, 	U, 	_, 	C, 	W, 	W, 	W, 	W, 	G, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	I, 	U, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	S, 	R, 	G, 	G, 	U, 	F, 	U, 	G, 	G, 	V, 	S, 	_, 	_, 	U, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	I, 	U, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	A, 	E, 	A, 	_, 	I, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	I, 	U, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	G, 	C, 	U, 	C, 	G, 	U, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	H, 	U, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	L, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	_, 	_, 	_, 	W, 	B, 	_, 	_, 	_, 	B, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	F, 	F, 	F, 	W, 	G, 	G, 	_, 	G, 	G, 	W, 	_, 	_, 	F, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	R, 	W, 	W, 	W, 	_, 	_, 	U, 	_, 	H, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	_, 	_, 	_, 	S, 	_, 	_, 	G, 	_, 	_, 	S, 	_, 	G, 	F, 	H, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	H, 	_, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	H, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	F, 	F, 	F, 	W, 	_, 	F, 	F, 	F, 	_, 	W, 	_, 	_, 	F, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	_, 	_, 	U, 	_, 	H, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	_, 	_, 	_, 	W, 	_, 	F, 	H, 	F, 	_, 	W, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	R, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	L, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	C, 	G, 	C, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	X, 	G, 	X, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	C, 	C, 	C, 	C, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	I, 	_, 	G, 	G, 	G, 	G, 	G, 	_, 	I, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	F, 	H, 	H, 	_, 	L, 	_, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	U, 	U, 	U, 	U, 	U, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	H, 	_, 	W, 	F, 	U, 	U, 	U, 	U, 	W, 	T, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	G, 	U, 	F, 	_, 	_, 	_, 	F, 	U, 	G, 	C, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	A, 	X, 	B, 	I, 	_, 	W, 	T, 	W, 	W, 	W, 	_, 	_, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	G, 	U, 	_, 	F, 	F, 	F, 	_, 	U, 	G, 	C, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	T, 	W, 	W, 	W, 	U, 	A, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	G, 	U, 	_, 	U, 	F, 	U, 	_, 	U, 	G, 	C, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	W, 	T, 	W, 	W, 	W, 	T, 	T, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	G, 	U, 	_, 	U, 	F, 	U, 	_, 	U, 	G, 	C, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	L, 	W, 	W, 	W, 	W, 	W, 	T, 	W, 	W, 	W, 	T, 	F, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	G, 	U, 	F, 	U, 	H, 	U, 	F, 	U, 	G, 	C, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	A, 	F, 	F, 	_, 	W, 	W, 	W, 	T, 	W, 	W, 	W, 	T, 	F, 	R, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	F, 	U, 	U, 	U, 	U, 	U, 	F, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	G, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	T, 	W, 	W, 	W, 	T, 	F, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	H, 	F, 	S, 	_, 	B, 	_, 	B, 	_, 	B, 	_, 	S, 	F, 	H, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	T, 	W, 	W, 	W, 	T, 	T, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	L, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	T, 	W, 	W, 	W, 	T, 	T, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	G, 	V, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	T, 	T, 	T, 	T, 	T, 	T, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	B, 	G, 	G, 	G, 	G, 	G, 	F, 	F, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	C, 	G, 	C, 	C, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	U, 	G, 	U, 	U, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	R, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	C, 	C, 	_, 	C, 	C, 	W, 	W, 	W, 	W, 	W, 	X, 	_, 	_, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	G, 	_, 	G, 	U, 	W, 	W, 	W, 	W, 	W, 	F, 	_, 	_, 	W, 	W, 	I, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	B, 	C, 	_, 	C, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	U, 	U, 	_, 	U, 	U, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	G, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	B, 	F, 	F, 	F, 	F, 	F, 	F, 	F, 	L, 	_, 	B, 	_, 	F, 	H, 	D, 	C, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	_, 	_, 	_, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
		W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 	W, 
      };	

	
}
