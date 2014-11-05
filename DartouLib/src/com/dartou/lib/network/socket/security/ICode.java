package com.dartou.lib.network.socket.security;

/**
 * 加密和解密接口
 * @author cyn
 *
 */
public interface ICode {
	/**
	 * 加密
	 * @param bytes
	 * @return
	 */
	public byte[] code(byte[] bytes);
	
	/**
	 * 解密
	 * @param bytes
	 * @return
	 */
	public byte[] uncode(byte[] bytes);
}
