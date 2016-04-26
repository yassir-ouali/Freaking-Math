package com.fm2.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class Menu {

	freakingMath fm2;
	private Stage stage;
	private Image background;
	ImageButton play,rate;
	MyGame game;
	Actor rect;
	Rectangle r;
	Image a;
	public freakingMath getFm2() {
		return fm2;
	}

	public void setFm2(freakingMath fm2) {
		this.fm2 = fm2;
	}

	public MyGame getGame() {
		return game;
	}

	public void setGame(MyGame game) {
		this.game = game;
	}

	public Menu(MyGame game,freakingMath fm2) {
		// TODO Auto-generated constructor stub
		this.fm2=fm2;
		this.game=game;
		float width=0;
		float height=0;
		stage=new Stage(new StretchViewport(conf.width,conf.height,new OrthographicCamera()));
	    
	     background=new Image(fm2.assets.get("logo_home.png",Texture.class));
	     width=conf.width-conf.width/4;
	     height=150;
	     background.setBounds(conf.width/2-width/2, conf.height-300, width,height);
	     
	     ImageButtonStyle style=new ImageButtonStyle();
	     style.down=game.getSkin().getDrawable("play_select");
	     style.up=game.getSkin().getDrawable("play");
	     
	     width=conf.width/3;
	     height=70;
	     
	     play=new ImageButton(style);
	     play.setBounds(conf.width/2-width/2,180, width, height);
	     play.addListener(new ClickListener(){
	    	 @Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		// TODO Auto-generated method stub
	    		super.clicked(event, x, y);
	    		if(getFm2().adsController.isWifiConnected())
	    			getFm2().adsController.hideBannerAd();
	    		play();
	    	}
	    	
	     });
	     
	     
	     ImageButtonStyle style2=new ImageButtonStyle();
	     style2.up=game.getSkin().getDrawable("rate");
	     style2.down=game.getSkin().getDrawable("rate_select");
	     rate=new ImageButton(style2);
	     //width=conf.width/3;
	     //height=50;
	     rate.setBounds(conf.width/2-width/2,100, width, height);
	     rate.addListener(new ClickListener(){
	    	 @Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		// TODO Auto-generated method stub
	    		super.clicked(event, x, y);
	    		Gdx.net.openURI("https://play.google.com/store/apps/details?id=com.fm2.game.android");
	    	}
	     });
	     
	   //-----------Rectangle-------------------

		a=new  Image(game.getFm2().assets.get("bg.png",Texture.class));
		a.setSize(conf.width,conf.height);
		
	     stage.addActor(a);
	     stage.addActor(background);
	     stage.addActor(play);
	     stage.addActor(rate);

	     if(fm2.adsController.isWifiConnected())
	    	 fm2.adsController.showBannerAd();
	}

	public void play(){
		stage.addAction(moveTo(0,-conf.height,0.5f));
		game.getHudGame().getScoreL().setText("0");
		Gdx.input.setInputProcessor(game.getHudGame().getStage());
	}
	//@Override

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Image getBackground() {
		return background;
	}

	public void setBackground(Image background) {
		this.background = background;
	}

}
