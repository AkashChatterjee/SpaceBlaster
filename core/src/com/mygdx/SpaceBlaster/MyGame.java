package com.mygdx.SpaceBlaster;

import com.badlogic.gdx.Game;

public class MyGame extends Game{

	SpaceBlaster s;
	EndScreen end;
	static int score;
	
	
	public void create() 
	{
		s = new SpaceBlaster(this);
		end = new EndScreen(this);
		setScreen(s);
		
	}

}
