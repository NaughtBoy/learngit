package com.dartou.lib.network.socket.infa;

import java.io.DataInputStream;
import java.io.IOException;

import com.dartou.lib.network.socket.Packet;
/**
 * 网络解析接口
 * @author cyn
 */
public interface IParser {
	public void networkBroken();//网络异常
	public Packet body(DataInputStream in) throws IOException;//如何解析出一个完整的包
	public void parse(int cmd,byte[] body);//解析命令后
}
