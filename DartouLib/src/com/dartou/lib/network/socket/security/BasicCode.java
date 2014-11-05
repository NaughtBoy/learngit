package com.dartou.lib.network.socket.security;

/**
 * 数据无加密和无解密
 * @author cyn
 *
 */
public class BasicCode implements ICode{

	@Override
	public byte[] code(byte[] bytes) {
		// TODO Auto-generated method stub
		return bytes;
	}

	@Override
	public byte[] uncode(byte[] bytes) {
		// TODO Auto-generated method stub
		return bytes;
	}

}
