package com.mygdx.SpaceBlaster;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class EndScreen implements Screen{
	
	MyGame game;
	BitmapFont font;
	SpriteBatch batch;
	Texture back;
	SpaceBlaster s;
	
	
    public EndScreen(MyGame game)
    {
        this.game=game;
        init();
    }
    public void init()
    {
    	batch=new SpriteBatch();
    	font=new BitmapFont();
    	font.setColor(Color.WHITE);
    	font.setScale(2);
    	back=new Texture(Gdx.files.internal("assets/background.jpg"));   
    	
    	
    }

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		batch.begin();
			batch.draw(back, 0, 0);
			font.draw(batch, "Game Over", 315, 350);
			font.draw(batch, "Your Score: "+MyGame.score, 300, 300);
		batch.end();
		
		
		
		
		
		
	}

	public boolean keyDown(int keycode) 
	{
		if(keycode == Input.Keys.SPACE)
			game.setScreen(game.s);
		
		return false;
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	
	public void dispose() {
		batch.dispose();
		
		game.dispose();
	
		
	}
	
}
