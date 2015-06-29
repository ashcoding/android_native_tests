package com.example.test;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends Activity implements SurfaceHolder.Callback, OnPreparedListener{

	private MediaPlayer mediaPlayer;
	private SurfaceHolder vidHolder;
	private SurfaceView vidSurface;
	
	//TIMOB-18888, Error on 5.1.1 tisdk. Native ok.
	//String vidAddress = "http://s3-eu-west-1.amazonaws.com/cf-templates-k6ohn88yswx5-eu-west-1/videos/4fbc88e7e4b0b0896e877764/4fbfd5ece4b0932236fc234d.mp4";
	
	//TIMOB-18913
	String vidAddress = "http://twit.cachefly.net/video/aaa/aaa0033/aaa0033_h264b_640x368_256.mp4";
	
	//TIMOB-18372, Error -1004. I think this link is down.
	//String vidAddress = "http://www.nasa.gov/multimedia/nasatv/NTV-Public-IPS.m3u8";
	//Alternative video below
	//https://code.google.com/p/android-developer-preview/issues/detail?id=1820 m3u file
	//String vidAddress = "http://dailyburn-f.akamaihd.net/i/series300/workouts/HR_316/v7_,1390K,895K,555K,310K,155K,.mp4.csmil/master.m3u8?__b__=555K";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		vidSurface = (SurfaceView) findViewById(R.id.surfView);
		vidHolder = vidSurface.getHolder();
		vidHolder.addCallback(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mediaPlayer.start();
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try {
		    mediaPlayer = new MediaPlayer();
		    mediaPlayer.setDisplay(vidHolder);
		    mediaPlayer.setDataSource(vidAddress);
		    mediaPlayer.prepare();
		    mediaPlayer.setOnPreparedListener(this);
		    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		} 
		catch(Exception e){
		    e.printStackTrace();
		}
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
}
