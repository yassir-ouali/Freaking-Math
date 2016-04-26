package com.fm2.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class Loading implements Screen{

	freakingMath fm2;
	public MyGame game;
	public Loading(freakingMath freakingMath) {
		// TODO Auto-generated constructor stub
		fm2=freakingMath;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		freakingMath.assets.load("false_select.png",Texture.class);
		freakingMath.assets.load("false.png",Texture.class);
		freakingMath.assets.load("game_over_bg.png",Texture.class);
		freakingMath.assets.load("game_over_timer_bg.png",Texture.class);
		freakingMath.assets.load("logo_home.png",Texture.class);
		freakingMath.assets.load("menu_select.png",Texture.class);
		freakingMath.assets.load("menu.png",Texture.class);
		freakingMath.assets.load("menu.png",Texture.class);
		freakingMath.assets.load("play.png",Texture.class);
		freakingMath.assets.load("play_select.png",Texture.class);
		freakingMath.assets.load("rate_select.png",Texture.class);
		freakingMath.assets.load("rate.png",Texture.class);
		freakingMath.assets.load("sound_off.png",Texture.class);
		freakingMath.assets.load("sound_on.png",Texture.class);
		freakingMath.assets.load("true_select.png",Texture.class);
		freakingMath.assets.load("true.png",Texture.class);
		freakingMath.assets.load("bg.png",Texture.class);
		
		freakingMath.assets.finishLoading();
		
		game=new MyGame(fm2);
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor((float)0.2039,(float) 0.2862,(float) 0.3686, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(freakingMath.assets.update())
		{
			fm2.setScreen(game);
		}
	}

	@Override
	public void resize(int width, int height) {
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

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
