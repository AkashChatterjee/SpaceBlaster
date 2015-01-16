package com.mygdx.SpaceBlaster;

import com.badlogic.gdx.math.Rectangle;

public class Enemy {
	int x;
	int y;
	Rectangle r;
	boolean remove;
	
	public Enemy(int x,int y){
		this.x=x;
		this.y=y;
		r=new Rectangle(x,y,21,40);
		remove=false;
	}

}
