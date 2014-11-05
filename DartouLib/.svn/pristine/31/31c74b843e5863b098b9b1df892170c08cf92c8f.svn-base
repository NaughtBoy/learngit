package com.dartou.lib.network.socket;


import java.io.IOException;
import java.io.InputStream;

import com.dartou.lib.network.socket.CConnection;
/**
 * byte工具类
 * @author cyn
 */
public class ByteUtil {

	
	
//	/**
//	 * int转byte数组
//	 * 
//	 * @param iSource
//	 * @return
//	 */
//	public static byte[] toByteArray(int n) {
//		byte[] bLocalArr = new byte[4];
////		for (int i = 0; i < 4; i++) {
////			bLocalArr[i] = (byte) (n >> 8 * i & 0xFF);
////		}
//		byte[] b = new byte[4];
//		b[0] = (byte) (n & 0xff);
//		b[1] = (byte) (n >> 8 & 0xff);
//		b[2] = (byte) (n >> 16 & 0xff);
//		b[3] = (byte) (n >> 24 & 0xff);
//		return bLocalArr;
//	}
//
//	/**
//	 * byte数组转int
//	 * 
//	 * @param bRefArr
//	 * @return
//	 */
//	public static int toInt(byte[] bRefArr) {
//		int iOutcome = 0;
//		byte bLoop;
//
//		for (int i = 0; i < bRefArr.length; i++) {
//			bLoop = bRefArr[i];
//			iOutcome += (bLoop & 0xFF) << (8 * i);
//		}
//		return iOutcome;
//	}
//
//	// /**
//	// * short转byte数组
//	// * @param iSource
//	// * @return
//	// */
//	// public static byte[] toByteArray(short iSource) {
//	// byte[] bLocalArr = new byte[2];
//	// for (int i = 0; i < 2; i++) {
//	// bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
//	// }
//	// return bLocalArr;
//	// }
//	//
//	// /**
//	// * byte数组转int
//	// * @param bRefArr
//	// * @return
//	// */
//	// public static short toShort(byte[] bRefArr) {
//	// short iOutcome = 0;
//	// byte bLoop;
//	//
//	// for (int i = 0; i < bRefArr.length; i++) {
//	// bLoop = bRefArr[i];
//	// iOutcome += (bLoop & 0xFF) << (8 * i);
//	// }
//	// return iOutcome;
//	// }
//
	/**
	 * 将byte[]转换为十六进制
	 * @param b
	 * @return
	 */
	public static String printHexString(byte[] b) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			result.append(hex.toUpperCase()+",");
		}
		return result.toString();
	}
	
	/**
	 * 从流中读取两字节为短整型
	 * @param bytes
	 * @param start
	 * @return
	 */
	public static short readShort(InputStream in) throws IOException{
		byte b_length[]  = new byte[2];
		in.read(b_length);
		short result = CConnection.iDian.bytesToShort(b_length);
		return result;
	}
	
}
