package com.dartou.lib.network.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

import android.os.AsyncTask;
import android.text.TextUtils;

/**
 * http协议客户端连接
 * 
 * @author cyn
 * 
 */
public abstract class BasicHttpClient extends AsyncTask<Void, Void, String>
		implements HttpResultListener {
	public List<NameValuePair> nameValuePairs;
	public String url;

	public BasicHttpClient() {
		this.nameValuePairs = new ArrayList<NameValuePair>();
	}
	
	/**
	 * 设置地址
	 * @param url
	 */
	public void setUrl(String url){
		this.url = url;
	}
	
	/**
	 * 设置接口方法
	 * @param method
	 */
	public void SetMethod(String method){
		nameValuePairs.add(new BasicNameValuePair("a", method));
	}
	
	/**
	 * 设置参数
	 * @param params 如果为空，无请求参数
	 */
	public void SetParams(String params){
		if(TextUtils.isEmpty(params)){
			params = "[]";
		}
		nameValuePairs.add(new BasicNameValuePair("param", params));
	}

	@Override
	protected String doInBackground(Void... params) {
		HttpPost m_vHttpPost = new HttpPost(url);
		StringBuilder strResult = null;
		try {

			DefaultHttpClient m_vHttpClient = new DefaultHttpClient();

			// 超时请求
			m_vHttpClient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
			// 读取超时
			m_vHttpClient.getParams().setParameter(
					CoreConnectionPNames.SO_TIMEOUT, 10000);
			m_vHttpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
					"utf-8"));
			HttpResponse response = m_vHttpClient.execute(m_vHttpPost);

			int nCode = response.getStatusLine().getStatusCode();

			if (nCode == 200) {

				BufferedReader reader = new BufferedReader(
						new InputStreamReader(
								response.getEntity().getContent(), "UTF-8"));
				strResult = new StringBuilder();
				for (String s = reader.readLine(); s != null; s = reader
						.readLine()) {
					strResult.append(s);
				}
				reader.close();
				if (strResult != null) {
					return strResult.toString();
				}
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String strResult) {
		super.onPostExecute(strResult);
		if (strResult != null) {
			success(strResult.toString());
		} else {
			faild();
		}
	}

}
