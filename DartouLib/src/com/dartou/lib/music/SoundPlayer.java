package com.dartou.lib.music;

import java.io.IOException;
import java.util.HashMap;

import android.content.Context;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * 音效播放类
 * @author cyn
 *
 */
public class SoundPlayer {
	private SoundPool mSoundPool;
	private Context mContext;
	private HashMap<Integer, Integer> map;
	private HashMap<String, Integer> mapName;
	private float volume = 0.5f;
	private boolean isPlay = true;
	
	AssetManager as;
	public SoundPlayer(Context context){
		// 参数1为声音池同时播放的流的最大数量
		// 参数2为播放流的类型
		// 参数3为音乐播放效果
		mSoundPool = new SoundPool(50, AudioManager.STREAM_MUSIC, 100);
		mContext = context;
		map = new HashMap<Integer, Integer>();
	}
	
	/**
	 * 添加音效
	 * @param id
	 */
	public void add(int id){
		map.put(id, mSoundPool.load(mContext, id, 0));
	}
	
	/**
	 * 添加音效
	 * @param path
	 */
	public void add(String path){
		if(mapName==null){
			mapName = new HashMap<String, Integer>();
		}
		if(as==null){
			as = mContext.getAssets();
		}
		
		try {
			mapName.put(path, mSoundPool.load(as.openFd(path), 1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 播放音效
	 * @param id
	 * @param loop 循环次数 -1为永久循环 0为不循环
	 */
	public void play(int id,int loop){
		if(!isPlay){
			return;
		}
		
		int streamId = map.get(id);
		if(streamId!=0){
			mSoundPool.play(map.get(id), volume, volume, 1, loop, 1);
		}else{
			add(id);
			mSoundPool.play(map.get(id), volume, volume, 1, loop, 1);
		}
	}
	
	/**
	 * 播放音效
	 * @param path
	 * @param loop 循环次数 -1为永久循环 0为不循环
	 */
	public void play(String path,int loop){
		if(!isPlay){
			return;
		}
		if(mapName==null){
			mapName = new HashMap<String, Integer>();
		}
		try {
			int streamId = mapName.get(path);
			if(streamId!=0){
				mSoundPool.play(streamId, volume, volume, 1, loop, 1);
			}else{
				add(path);
				streamId = mapName.get(path);
				mSoundPool.play(streamId, volume, volume, 1, loop, 1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 删除某音效
	 * @param id
	 */
	public void remove(int id){
		mSoundPool.unload(id);
		map.put(id, null);
	}
	
	
	/**
	 * 设置音量大小
	 * @param volume
	 */
	public void setVolume(float volume){
		this.volume = volume;
	}
	
	/**
	 * 设置音乐的播放状态
	 * 
	 * @param flag
	 */
	public void SetPlay(boolean flag) {
		isPlay = flag;
	}
}
