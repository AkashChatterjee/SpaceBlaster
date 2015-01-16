package com.mygdx.SpaceBlaster;

import com.badlogic.gdx.math.Rectangle;



public class Bullets {
	float x;
	float y;
	Rectangle r;
	boolean remove;
	
	Bullets(float x, float y)
	{
		this.x=x;
		this.y=y;
		r=new Rectangle(x,y,9,13);
		remove=false;
	}

}
