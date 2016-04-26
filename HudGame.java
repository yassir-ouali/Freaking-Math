package com.fm2.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.fm2.game.MyGame.Level;

public class HudGame {

	Label scoreL,xyL,zL,eqL;
	Image soundI,trueI,falseI;
	//ImageButton trueI,falseI;
	boolean hoverTrue,hoverFalse;
	Stage stage;
	Viewport viewport;
	int x,y,z;
	MyGame game;
	Group labelGroupe;
	public HudGame(MyGame game){
	
		this.game=game;
		viewport=new StretchViewport(conf.width, conf.height,new OrthographicCamera());
		stage=new Stage(viewport);
		
		initLabels();
		initImages();
		labelGroupe=new Group();
		labelGroupe.addActor(eqL);
		labelGroupe.addActor(zL);
		labelGroupe.addActor(xyL);
		Table table=new Table();
		table.top();
		table.setFillParent(true);
		
		table.add(soundI).expandX().align(Align.left).size(soundI.getWidth(),soundI.getHeight()).pad(10);
		table.add().size(0);
		table.add(scoreL).expandX().align(Align.right).size(soundI.getWidth(),soundI.getHeight()).pad(10);

	
		
		table.row();
		table.add();
		table.add(xyL).size(0,xyL.getHeight()).align(Align.center).padTop(100);
		table.add();
		
		table.row();
		table.add();
		table.add(eqL).align(Align.center).size(0).pad(10);
		table.add();
		
		table.row();
		table.add();
		table.add(zL).align(Align.center).size(0,zL.getHeight()).padBottom(100);
		table.add();
		
		table.row();
		table.add(trueI).expand(true, true).align(Align.left).pad(10).align(Align.bottom)/*.size(conf.width/2, conf.width/2)*/;
		table.add().size(0);
		table.add(falseI).expand(true, true).align(Align.right).pad(10).align(Align.bottom);
		
		stage.addActor(labelGroupe);
		stage.addActor(table);
//		stage.addActor(trueI);
//		stage.addActor(falseI);
	}
	
	void initLabels()
	{
		//------------labeel---------
	     LabelStyle style = new LabelStyle();
	     style.font = game.getFont2();
	     scoreL=new Label(game.getScore()+"", style);
	     scoreL.setAlignment(Align.right);
	     style.font=game.getFont();
	     xyL=new Label(x+"x"+y,style);
	     xyL.setAlignment(Align.center);
	     eqL=new Label("=",style);
	     eqL.setAlignment(Align.center);
	     zL=new Label(z+"",style);
	     zL.setAlignment(Align.center);
	}
	
	void initImages()
	{
		//------------------Sound----------------
	     if(game.isSound())
	    	 soundI=new Image(game.getFm2().assets.get("sound_on.png",Texture.class));
	     else
		     soundI=new Image(game.getFm2().assets.get("sound_off.png",Texture.class));
	     float width=50;
	     float height=50;
	     soundI.setBounds(0,Gdx.graphics.getHeight()-height, width, height);
	     soundI.addListener(new ClickListener(){
	    	 
			@Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		// TODO Auto-generated method stub
	    		super.clicked(event, x, y);
	    		game.setSound(!game.isSound());
	    		if(game.isSound())
	    			soundI.setDrawable(game.getSkin(), "sound_on");
	    		else
	    			soundI.setDrawable(game.getSkin(), "sound_off");
	    		//game.saveSoundStatut();
	    	}
	     });
	     
	     //------------------True----------------
	     trueI=new Image(game.getFm2().assets.get("true.png",Texture.class));
	     width=conf.width*3/8;
	     height=width;
//	     trueI.setAlign(Align.left);
//	     trueI.setSize(width, height);
	     //trueI.setBounds(Gdx.graphics.getWidth()/16,0, width, height);
	     trueI.addListener(new ClickListener(){
	    	 
			@Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		// TODO Auto-generated method stub
	    		super.clicked(event, x, y);
	    		game.setStartTime(TimeUtils.millis());
	    		game.setStart(true);
	    		if(game.isAnswer()){
	    			game.win();
	    			if(game.isSound())
	    				game.getWin().play();
	    		}else{
	    			game.makeGameOver();
	    		}
	    	}
	    	 @Override
	    	public void exit(InputEvent event, float x, float y, int pointer,
	    			Actor toActor) {
	    		// TODO Auto-generated method stub
	    		super.exit(event, x, y, pointer, toActor);
	    		trueI.setDrawable(game.getSkin(), "true");
	    	}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				trueI.setDrawable(game.getSkin(), "true_select");
				return super.touchDown(event, x, y, pointer, button);
			}
	    	
	     });
	    
	   //------------False--------------------
	     falseI=new Image(game.getFm2().assets.get("false.png",Texture.class));
	    
//	    falseI.setSize(width, height);
//	    falseI.setAlign(Align.right);
	    // falseI.setBounds(Gdx.graphics.getWidth()/2+Gdx.graphics.getWidth()/16,0, width, height);
	     falseI.addListener(new ClickListener(){
	    	 
			@Override
	    	public void clicked(InputEvent event, float x, float y) {
	    		// TODO Auto-generated method stub
	    		super.clicked(event, x, y);
	    		game.setStartTime(TimeUtils.millis());
	    		game.setStart(true);
	    		if(game.isAnswer()){
	    			game.makeGameOver();
	    		}else{
	    			game.win();
	    			if(game.isSound())
	    				game.getWin().play();
	    		}
	    	}
	    	 @Override
	    	public void exit(InputEvent event, float x, float y, int pointer,
	    			Actor toActor) {
	    		// TODO Auto-generated method stub
	    		super.exit(event, x, y, pointer, toActor);
	    		falseI.setDrawable(game.getSkin(), "false");
	    	}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				falseI.setDrawable(game.getSkin(), "false_select");
				return super.touchDown(event, x, y, pointer, button);
			}
	    	
	     });
	}

	void changeNumbers()
	{
		if(game.level==Level.LEVEL1)
		{
			x=MathUtils.random(8)+2;
			y=MathUtils.random(9)+1;
			int tmp=MathUtils.random(3);
			if(tmp==0 || tmp==2)
			{
				z=x*y+MathUtils.random(7)+1;
				game.setAnswer(false);
			}else{
				z=x*y;
				game.setAnswer(true);
			}
		}
		
		xyL.setText(x+"x"+y);
		zL.setText(z+"");
	}
	public Label getScoreL() {
		return scoreL;
	}

	public void setScoreL(Label scoreL) {
		this.scoreL = scoreL;
	}

	public Group getLabelGroupe() {
		return labelGroupe;
	}

	public void setLabelGroupe(Group labelGroupe) {
		this.labelGroupe = labelGroupe;
	}

	public Label getXyL() {
		return xyL;
	}

	public void setXyL(Label xyL) {
		this.xyL = xyL;
	}

	public Label getzL() {
		return zL;
	}

	public void setzL(Label zL) {
		this.zL = zL;
	}

	public Label getEqL() {
		return eqL;
	}

	public void setEqL(Label eqL) {
		this.eqL = eqL;
	}

	public Image getSoundI() {
		return soundI;
	}

	public void setSoundI(Image soundI) {
		this.soundI = soundI;
	}

	public boolean isHoverTrue() {
		return hoverTrue;
	}

	public void setHoverTrue(boolean hoverTrue) {
		this.hoverTrue = hoverTrue;
	}

	public boolean isHoverFalse() {
		return hoverFalse;
	}

	public void setHoverFalse(boolean hoverFalse) {
		this.hoverFalse = hoverFalse;
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

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public MyGame getGame() {
		return game;
	}

	public void setGame(MyGame game) {
		this.game = game;
	}

	
	
}
