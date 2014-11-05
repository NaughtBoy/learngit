package com.dartou.lib.network.http;
/**
 * {@link BasicHttpClient}
 * BasicHttpClient请求结果接口
 * @author cyn
 */
public interface HttpResultListener {
	public void success(String result);
	public void faild();
}
