package com.mygdx.SpaceBlaster;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class SpaceBlaster implements InputProcessor,Screen{
	int x,y,flag,moveX,sf,check;
	long time,at,st,st2;
	
	SpriteBatch batch;
	Texture ship,bullet,enemy,background,blast1,blast2,blast3;
	Sprite s1;
	Rectangle r;
	BitmapFont disscore;
	Enemy e;
	Bullets b;
	
	ArrayList<Bullets> bs;
	ArrayList<Enemy> enemies;
	
	Sound blast = Gdx.audio.newSound(Gdx.files.internal("sounds/blast.mp3"));
	
	EndScreen end;
	MyGame game;
	
	public SpaceBlaster(MyGame g)
	{
		game = g;
		init();
	}
	
	
	public void init () 
	{
		x=380; y=10;
		
		flag=0;
		moveX=0;
		sf=0;
		check = 0;
		at=0;
		st=0;
		MyGame.score=0;
		
		Sound backing = Gdx.audio.newSound(Gdx.files.internal("sounds/backing.mp3"));
		backing.play(0.25f);
		
		
		
		
		time= TimeUtils.nanoTime();
		
		batch = new SpriteBatch();
		
		background=new Texture(Gdx.files.internal("assets/background.jpg"));
		
		disscore=new BitmapFont();
		disscore.setColor(Color.CYAN);
		
		ship = new Texture(Gdx.files.internal("assets/ship.png"));
		s1=new Sprite(ship);
		r = new Rectangle(x, y, 61, 80);
		
		bullet=new Texture(Gdx.files.internal("assets/bullet.png"));
		bs=new ArrayList<Bullets>();
		
		enemy= new Texture(Gdx.files.internal("assets/enemy.png"));
		enemies=new ArrayList<Enemy>();
		
		blast1=new Texture(Gdx.files.internal("assets/blast1.png"));
		blast2=new Texture(Gdx.files.internal("assets/blast2.png"));
		blast3=new Texture(Gdx.files.internal("assets/blast3.png"));
		
		s1.setPosition(x, y);
		
		Gdx.input.setInputProcessor(this);
	}
	
	void spawnBullet(float x,float y)
	{
		Bullets b=new Bullets(x,y);
		bs.add(b);
	}
	void spawnEnemies()
	{
		int x,y;
		y=510;
		x=MathUtils.random(1, 38)*20;
		
		Enemy e = new Enemy(x,y);
		enemies.add(e);
	}
	
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		at+=Gdx.graphics.getDeltaTime();
		
		if(moveX == 1)
		{
			r.x+=3;
			x+=3;
		}
		else if(moveX==-1)
		{	
			x-=3;
			r.x-=3;
		}
		
		if(x<0)
			x=0;
		else if(x>739)
			x=739;
		
		s1.setPosition(x, y);
		
		batch.begin();
			batch.draw(background, 0, 0);
		
			disscore.draw(batch, "Score: "+MyGame.score, 10, 480);
		
			s1.draw(batch);
		
			for(int i=0;i<enemies.size();i++)
			{	
				e=enemies.get(i);
				if(e.remove)
				{
					batch.draw(blast1, e.x, e.y);
					batch.draw(blast2, e.x, e.y);
					batch.draw(blast3, e.x, e.y);
					
					blast.play(5.0f);
					enemies.remove(i);
				}
			}
			for(int i=0;i<bs.size();i++)
			{
				b=bs.get(i);
				if(b.remove)
					bs.remove(i);
			}
		
			for(Bullets b:bs)
			{
				batch.draw(bullet, b.x, b.y);
				b.y+=2;
				b.r.y+=2;
			}
			for(Enemy e:enemies)
			{
				batch.draw(enemy, e.x, e.y);
				e.y-=1;
				e.r.y-=1;
			}
		batch.end();
		
		for(Bullets b:bs)
		{
			for(Enemy e:enemies)
			{
				if(b.r.overlaps(e.r))
				{
					b.remove=true;
					e.remove=true;
					MyGame.score++;
				}
			}
		}
		
		for(Bullets b:bs)
		{
			if(b.y>=500)
				b.remove=true;
		}
		
		if(TimeUtils.timeSinceNanos(st) > 2000000000)
		{
			spawnEnemies();
			st=TimeUtils.nanoTime();
		
		}
		for(Enemy e:enemies)
		{
			if(e.y<=0 || e.r.overlaps(r))
				game.setScreen(game.end);
		}
	}
	public boolean keyDown(int keycode) 
	{
		if(keycode==Input.Keys.RIGHT)
			moveX=1;
		if(keycode==Input.Keys.LEFT)
			moveX=-1;
		
		if(keycode == Input.Keys.SPACE)
			spawnBullet(s1.getX()+30.5f,s1.getY()+80);
		return false;
	}

	public boolean keyUp(int keycode) 
	{
		if(keycode == Input.Keys.RIGHT || keycode == Input.Keys.LEFT)
			moveX = 0;
		return false;
	}
	public void dispose() 
	{
		batch.dispose();
		disscore.dispose();
		bs.clear();
		enemies.clear();
		game.dispose();
		end.dispose();
	}
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
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
}
