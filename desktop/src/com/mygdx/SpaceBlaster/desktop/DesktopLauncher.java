package com.mygdx.SpaceBlaster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.SpaceBlaster.MyGame;

public class DesktopLauncher {
	public static void main (String[] arg) 
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=800;
		config.height=500;
		config.title="Space Blaster";
		config.resizable=false;
		new LwjglApplication(new MyGame(), config);
	}
}
