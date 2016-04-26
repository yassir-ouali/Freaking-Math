package com.fm2.game;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveTo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
public class HudOver {

	MyGame game;
	Image gameOverI;
	ImageButton playI,MenuI;
	Label scoreL,highL;
	Stage stage;
	Viewport viewport;
	boolean hoverMenu,hoverPlay;
	freakingMath fm2;
	public HudOver(MyGame game, freakingMath fm2){
	
		this.game=game;
		this.fm2=fm2;
		viewport=new FitViewport(conf.width,conf.height,new OrthographicCamera());
		stage=new Stage(viewport);
		
		initLabels();
		initImages();
		
		
		stage.addActor(gameOverI);
		stage.addActor(scoreL);
		stage.addActor(highL);
		stage.addActor(MenuI);
		stage.addActor(playI);
		stage.addAction(moveTo(conf.width/2-gameOverI.getWidth()/2,conf.height*3/2));
	}
	
	void initLabels(){
		//------------labeel---------
	    LabelStyle style = new LabelStyle();
	    style.font = game.getFont2();
	    scoreL=new Label(game.getScore()+"", style);
	    scoreL.setBounds(150 ,115,10,10);
	    
	    highL=new Label(game.getHighScore()+"", style); 
	    highL.setBounds(150,55,10,10);
	    
	}
	
	void initImages(){
		gameOverI=new Image(game.getFm2().assets.get("game_over_bg.png",Texture.class));
		float width2=conf.width*4/5;
		float height2=width2*3/4;
		gameOverI.setBounds(0,0,width2, height2);
		
		
		ImageButtonStyle style=new ImageButtonStyle();
	    style.down=game.getSkin().getDrawable("play_select");
	    style.up=game.getSkin().getDrawable("play");
	     
		playI=new ImageButton(style);
		width2=90;
		height2=60;
		playI.setBounds(50,-80, width2, height2);

	   playI.addListener(new ClickListener(){
	    	 
			@Override
	    	public void clicked(InputEvent event, float x, float y){
	    		// TODO Auto-generated method stub
	    		super.clicked(event, x, y);
	    		play();
	    	}	    	
	     });
		
	   	ImageButtonStyle style2=new ImageButtonStyle();
	    style2.down=game.getSkin().getDrawable("menu_select");
	    style2.up=game.getSkin().getDrawable("menu");
	     
		MenuI=new ImageButton(style2);
		MenuI.setBounds(145, -80, width2, height2);
		MenuI.addListener(new ClickListener(){
	    	 
			@Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		// TODO Auto-generated method stub
	    		super.clicked(event, x, y);
	    		if(fm2.adsController.isWifiConnected())
	    			fm2.adsController.showBannerAd();	
	    		menu();
	    		
			}
	     });
	}
	
	public freakingMath getFm2() {
		return fm2;
	}

	public void setFm2(freakingMath fm2) {
		this.fm2 = fm2;
	}

	public void menu(){
		game.getMenu().getStage().addAction(moveTo(0,0));
		stage.addAction(moveTo(conf.width/2-gameOverI.getWidth()/2,conf.height*3/2));
		game.getHudGame().changeNumbers();
		game.setScore(0);
		game.getHudGame().getScoreL().setText("0");
		game.setTimer(conf.time);
		game.setStop(false);
		game.setStart(false);
		game.setStartTime(TimeUtils.millis());
		Gdx.input.setInputProcessor(game.getMenu().getStage());
		game.setColorId(MathUtils.random(conf.color1.length-1));
	}
	public void play()
	{
		stage.addAction(moveTo(conf.width/2-gameOverI.getWidth()/2,conf.height*3/2));
		game.getHudGame().changeNumbers();
		game.setScore(0);
		game.getHudGame().getScoreL().setText("0");
		game.setTimer(conf.time);
		game.setStop(false);
		game.setStart(false);
		game.setStartTime(TimeUtils.millis());
		Gdx.input.setInputProcessor(game.getHudGame().getStage());
		
		game.setColorId(MathUtils.random(conf.color1.length-1));
	}
	public MyGame getGame() {
		return game;
	}

	public void setGame(MyGame game) {
		this.game = game;
	}

	public Image getGameOverI() {
		return gameOverI;
	}

	public void setGameOverI(Image gameOverI) {
		this.gameOverI = gameOverI;
	}

	public void setScoreL(Label scoreL) {
		this.scoreL = scoreL;
	}

	public Label getHighL() {
		return highL;
	}

	public void setHighL(Label highL) {
		this.highL = highL;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Viewport getViewport() {
		return viewport;
	}

	public void setViewport(Viewport viewport) {
		this.viewport = viewport;
	}

	public boolean isHoverMenu() {
		return hoverMenu;
	}

	public void setHoverMenu(boolean hoverMenu) {
		this.hoverMenu = hoverMenu;
	}

	public boolean isHoverPlay() {
		return hoverPlay;
	}

	public void setHoverPlay(boolean hoverPlay) {
		this.hoverPlay = hoverPlay;
	}

	public ImageButton getPlayI() {
		return playI;
	}

	public void setPlayI(ImageButton playI) {
		this.playI = playI;
	}

	public ImageButton getMenuI() {
		return MenuI;
	}

	public void setMenuI(ImageButton menuI) {
		MenuI = menuI;
	}

	public Label getScoreL() {
		return scoreL;
	}
	
	
}
