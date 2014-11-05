package com.dartou.lib.music;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;

import com.dartou.lib.R;

/**
 * 音乐播放类
 * 
 * @author cyn
 * 
 */
public class MusicPlayer {

	private static MusicPlayer m_vCMusicPlayer = null;

	private boolean isPlay = true;

	private MediaPlayer mMediaPlayer;

	private MusicPlayer() {

	}

	public static MusicPlayer getInstance() {
		if (m_vCMusicPlayer == null)
			m_vCMusicPlayer = new MusicPlayer();
		return m_vCMusicPlayer;
	}

	/**
	 * 播放背景音乐
	 */
	public void PlayMusic(Context context, int id, boolean isLooping) {
		if (!isPlay) {
			return;
		}
		
		// 判断声音是否正在播放，如果没有播放则开始播放游戏音乐。
		if (mMediaPlayer == null||!mMediaPlayer.isPlaying()) {
			mMediaPlayer = MediaPlayer.create(context, id);
			mMediaPlayer.setLooping(isLooping);
			SetMusicVolume(0.5f);
			try {
				mMediaPlayer.prepare();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mMediaPlayer.start();
		}

	}

	/**
	 * 关闭背景音乐
	 */
	public void StopMusic() {
		/** 关闭音乐 **/
		if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
			mMediaPlayer.stop();
		}
	}

	/**
	 * 销毁音乐
	 * 
	 */
	public void Destroy() {
		if (mMediaPlayer != null)
			mMediaPlayer.release();
	}

	/**
	 * 设置音乐的播放状态
	 * 
	 * @param flag
	 */
	public void SetPlay(boolean flag) {
		isPlay = flag;
	}

	public void SetMusicVolume(float f) {
		mMediaPlayer.setVolume(f, f);
	}
}
