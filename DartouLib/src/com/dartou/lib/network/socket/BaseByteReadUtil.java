package com.dartou.lib.network.socket;

import java.io.UnsupportedEncodingException;

import com.dartou.lib.network.socket.CConnection;
import com.dartou.lib.network.socket.security.ICode;
/**
 * byte[]数组读取类
 * @author cyn
 */
public abstract class BaseByteReadUtil {
	private int index;
	private byte[] mBuffer;
	public BaseByteReadUtil(byte[] buffer){
		ICode iCode = getICode();
		if(iCode!=null){
			this.mBuffer =iCode.uncode(buffer);
		}else{
			this.mBuffer =buffer;
		}
		
	}
	
	public abstract ICode getICode();
	
	
	/**
	 * 从bytes数组中读取四字节的整型
	 * @param bytes
	 * @param start
	 * @return
	 */
	public  int readInt(){
		byte[] temp = new byte[4];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = mBuffer[index+i];
		}
		index += 4;
		return CConnection.iDian.bytesToInt(temp);
	}
	
	/**
	 * 读取字符串
	 * @return
	 */
	public  String readString(){
		int length = readInt();
		String result = "";
		try {
			result = new String(mBuffer, index, length,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index += length;
		return result;
	}
	
	
	/**
	 * 读取一个字节
	 * @return
	 */
	public byte readByte(){
		index++;
		return mBuffer[index-1];
	}
	
	/**
	 * 读取字节数组
	 * @return
	 */
	public void readBytes(byte bytes[]){
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = mBuffer[index+i];
		}
		index += bytes.length;
	}
	
	/**
//	 * 从bytes数组中读取八字节的长整型
//	 * @param bytes
//	 * @param start
//	 * @return
//	 */
	public  long readLong(){
		byte[] temp = new byte[8];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = mBuffer[index+i];
		}
		index +=8;
		return CConnection.iDian.bytesToLong(temp);
	}
	
	/**
	 * 读取两字节为短整型
	 * @param bytes
	 * @param start
	 * @return
	 */
	public  short readShort(){
		byte[] temp = new byte[2];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = mBuffer[index+i];
		}
		index +=2;
		return CConnection.iDian.bytesToShort(temp);
	}
}
