package com.fm2.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.parallel;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MyGame implements Screen {

	public static final String FONT_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;,{}\"´`'<>";
	freakingMath fm2;
	Stage stage;
	BitmapFont font,font2;
	int score=0;
	enum Level{LEVEL1,LEVEL2,LEVEL3};
	Level level;
	float timer=0;
	Skin skin;
	private boolean sound=true,answer,stop,start;
	private long startTime;
	private int highScore,colorId;
	Sound win,fail;
	HudGame hudGame;
	HudOver hudOver;
	Menu menu;
	ShapeRenderer shapeRenderer;
	
	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	public void setShapeRenderer(ShapeRenderer shapeRenderer) {
		this.shapeRenderer = shapeRenderer;
	}

	public freakingMath getFm2() {
		return fm2;
	}

	public void setFm2(freakingMath fm2) {
		this.fm2 = fm2;
	}
	
	public MyGame(freakingMath fm2) {
		// TODO Auto-generated constructor stub
		this.fm2=fm2;
		skin=new Skin();
		level=Level.LEVEL1;
		stage=new Stage(new StretchViewport(conf.width,conf.height));
		start=false;
		timer=conf.time;
		colorId=MathUtils.random(conf.color1.length-1);

		fail=Gdx.audio.newSound(Gdx.files.internal("fail.ogg"));
		win= Gdx.audio.newSound(Gdx.files.internal("scored.ogg"));
		readSound();
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("relay-black.ttf"));
	     FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	     parameter.magFilter= TextureFilter.Linear;
	     parameter.minFilter= TextureFilter.Linear;  
	     parameter.size = 80;
	     font=new BitmapFont();
	     font = generator.generateFont(parameter); // font size 12 pixels
	     font2=new BitmapFont();
	     parameter.size = 40;
	     font2= generator.generateFont(parameter); 
	     generator.dispose(); // don't forget to dispose to avoid memory leaks!
	     
	    
	     
	    
	     
	   //-----------Rectangle-------------------
	     shapeRenderer=new ShapeRenderer();
	     stage.addActor(new Actor(){
	    	 @Override
	    	public void draw(Batch batch, float parentAlpha) {
	    		// TODO Auto-generated method stub
	    		super.draw(batch, parentAlpha);
	    		batch.end();
	            
				shapeRenderer.begin(ShapeType.Filled);
	            shapeRenderer.setColor(Color.WHITE);
	            shapeRenderer.rect(0,Gdx.graphics.getHeight()-5, Gdx.graphics.getWidth()*timer/conf.time, 10);
	            shapeRenderer.end();
	            batch.begin();
	    	}
	     });
	     
	     initSkin();
	     

		 hudGame=new HudGame(this);
	     hudGame.changeNumbers();
	     
	     hudOver=new HudOver(this,fm2);
	     
	     menu=new Menu(this,fm2);
	     
	     Gdx.input.setInputProcessor(menu.getStage());
	     
	}

	void initSkin()
	{
		skin.add("play_select", fm2.assets.get("play_select.png",Texture.class), Texture.class);
	    skin.add("play", fm2.assets.get("play.png",Texture.class), Texture.class);
	    skin.add("rate_select", fm2.assets.get("rate_select.png",Texture.class), Texture.class);
	    skin.add("rate", fm2.assets.get("rate.png",Texture.class), Texture.class);
	    skin.add("menu", fm2.assets.get("menu.png",Texture.class), Texture.class);
		skin.add("menu_select", fm2.assets.get("menu_select.png",Texture.class), Texture.class);
		skin.add("sound_on", fm2.assets.get("sound_on.png",Texture.class), Texture.class);
	    skin.add("sound_off", fm2.assets.get("sound_off.png",Texture.class), Texture.class);
	    skin.add("true_select", fm2.assets.get("true_select.png",Texture.class), Texture.class);
	    skin.add("true", fm2.assets.get("true.png",Texture.class), Texture.class);
	    skin.add("false_select", fm2.assets.get("false_select.png",Texture.class), Texture.class);
	    skin.add("false", fm2.assets.get("false.png",Texture.class), Texture.class);
	      
	     
	}
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public boolean isAnswer() {
		return answer;
	}

	public void setAnswer(boolean answer) {
		this.answer = answer;
	}

	public boolean isStart() {
		return start;
	}

	public void setStart(boolean start) {
		this.start = start;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public Sound getWin() {
		return win;
	}

	public void setWin(Sound win) {
		this.win = win;
	}

	public Sound getFail() {
		return fail;
	}

	public void setFail(Sound fail) {
		this.fail = fail;
	}

	public BitmapFont getFont() {
		return font;
	}

	public void setFont(BitmapFont font) {
		this.font = font;
	}

	public BitmapFont getFont2() {
		return font2;
	}

	public void setFont2(BitmapFont font2) {
		this.font2 = font2;
	}

	public boolean isSound() {
		return sound;
	}

	public void setSound(boolean sound) {
		this.sound = sound;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	void data()
	{
		//get a preferences instance
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		
		highScore=prefs.getInteger("score", 0);
		
		if(score>highScore)
		{
			//put some Integer
			prefs.putInteger("score", score);
			//persist preferences
			prefs.flush();
		}
		
		//get Integer from preferences, 0 is the default value.
		highScore=prefs.getInteger("score", 0);

	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
 		Gdx.gl.glClearColor(conf.color1[colorId][0]/255, conf.color1[colorId][1]/255, conf.color1[colorId][2]/255, 1.0f);
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(start && !stop)
			timer=conf.time-(TimeUtils.millis()-startTime);
		
		if(timer<=0 && !stop)
		{
			makeTimeOut();
		}
		
		hudGame.getStage().act(delta);
		hudGame.getStage().draw();
		
		hudGame.getLabelGroupe().act(delta);
		
		
		stage.act(delta);
		stage.draw();
		
		hudOver.getStage().act(delta);
		hudOver.getStage().draw();
		
		menu.getStage().act(delta);
		menu.getStage().draw();
		
		
		
	}

	
	public float getTimer() {
		return timer;
	}

	public void setTimer(float timer) {
		this.timer = timer;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	void makeGameOver()
	{
		
		if(sound)
			fail.play();
		stop=true;
		data();
		

		hudOver.getHighL().setText(highScore+"");
		hudOver.getScoreL().setText(score+"");
		
		Gdx.input.setInputProcessor(hudOver.getStage());
		
	    hudOver.getStage().addAction(moveTo(conf.width/2-hudOver.getGameOverI().getWidth()/2,conf.height/2, 0.5f));
	    
	    if(TimeUtils.millis()-fm2.adsTimer>1000*60 && fm2.adsController.isWifiConnected()){
	    	fm2.adsTimer=TimeUtils.millis();
	    	fm2.adsController.showInterstitialAd(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Gdx.app.exit();
				}
			});

	    }
	}
	
	

	void makeTimeOut()
	{
		if(sound)
			fail.play();
		stop=true;
		data();
	
		hudOver.getHighL().setText(highScore+"");
		hudOver.getScoreL().setText(score+"");

		Gdx.input.setInputProcessor(hudOver.getStage());
		
	    hudOver.getStage().addAction(moveTo(conf.width/2-hudOver.getGameOverI().getWidth()/2,conf.height/2, 0.5f));
	    
	    if(TimeUtils.millis()-fm2.adsTimer>1000*60 && fm2.adsController.isWifiConnected()){
	    	fm2.adsTimer=TimeUtils.millis();
	    	fm2.adsController.showInterstitialAd(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Gdx.app.exit();
				}
			});

	    }
	    
	}
	void winAnim(){
		
		
	}
	void win()
	{
		float x1=hudGame.getXyL().getX();
		
		float y1=hudGame.getXyL().getY();
		float y2=hudGame.getEqL().getY();
		float y3=hudGame.getzL().getY();
		Action completeAction = new Action(){
		    public boolean act( float delta ) {
		    	score++;
				
				hudGame.getScoreL().setText(score+"");
				hudGame.changeNumbers();
				
		        return true;
		    }
		};
		
		hudGame.getXyL().addAction(sequence(
				moveTo(0,hudGame.getXyL().getY(),0.2f),
				parallel(moveTo(conf.width*2+hudGame.getXyL().getWidth(),hudGame.getXyL().getY(),0.0f),completeAction)));
				//moveTo(x1,y1,0.2f)));
		hudGame.getEqL().addAction(sequence(
				moveTo(0,hudGame.getEqL().getY(),0.2f),
				moveTo(conf.width*2+hudGame.getEqL().getWidth(),hudGame.getEqL().getY(),0.0f)));
			//	moveTo(x1,y2,0.2f)));
		hudGame.getzL().addAction(sequence(
				moveTo(0,hudGame.getzL().getY(),0.2f),
				moveTo(conf.width*2+hudGame.getzL().getWidth(),hudGame.getzL().getY(),0.0f)));
			//	moveTo(x1,y3,0.2f)));
		
		
	}
	void saveSoundStatut()
	{
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		
			//put some Integer
			prefs.putBoolean("sound",sound);
			//persist preferences
			prefs.flush();
			
			
	}
	void readSound()
	{
		Preferences prefs = Gdx.app.getPreferences("My Preferences");
		sound=prefs.getBoolean("sound",true);
		
		prefs.flush();
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		stage.getViewport().update(width, height);
		hudGame.getStage().getViewport().update(width, height);
		hudOver.getStage().getViewport().update(width, height);
		menu.getStage().getViewport().update(width, height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}
	
	public Skin getSkin() {
		return skin;
	}

	public void setSkin(Skin skin) {
		this.skin = skin;
	}

	public HudGame getHudGame() {
		return hudGame;
	}

	public void setHudGame(HudGame hudGame) {
		this.hudGame = hudGame;
	}

	public HudOver getHudOver() {
		return hudOver;
	}

	public void setHudOver(HudOver hudOver) {
		this.hudOver = hudOver;
	}

	public int getColorId() {
		return colorId;
	}

	public void setColorId(int colorId) {
		this.colorId = colorId;
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
		stage.dispose();
		font.dispose();
		font2.dispose();
		win.dispose();
		fail.dispose();
		hudGame.getStage().dispose();
		hudOver.getStage().dispose();
	}

}
