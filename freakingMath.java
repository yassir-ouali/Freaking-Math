package com.fm2.game;



import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.TimeUtils;

public class freakingMath extends Game {
	//SpriteBatch batch;
	public static AssetManager assets;
	public static AdsController adsController;
	public static long adsTimer;
	public Loading loading;
	
	
	public freakingMath(AdsController adsController){
		if (adsController != null) {
            this.adsController = adsController;
        } else {
            this.adsController = new DummyAdsController();
        }
		
		
	}
	
	@Override
	public void create () {
		//batch = new SpriteBatch();
		assets=new AssetManager();
		adsTimer=TimeUtils.millis();
		loading=new Loading(this);
		
		setScreen(loading);
	}

	@Override
	public void render () {
		super.render();
	}
}
