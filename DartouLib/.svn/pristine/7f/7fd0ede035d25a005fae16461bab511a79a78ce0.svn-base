package com.dartou.lib.network.socket;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * 写入byte[]数组类
 * @author cyn
 */
public abstract class BaseByteWriteUtil {

	public static byte checkCode = 0x00;
	protected ByteArrayOutputStream mByteOut = null;
	protected DataOutputStream mDataOut = null;
	protected short mCmd;

	public BaseByteWriteUtil(short cmd) {
		this.mCmd = cmd;
		mByteOut = new ByteArrayOutputStream();
		mDataOut = new DataOutputStream(mByteOut);
	}


	public BaseByteWriteUtil writeIn(int data) throws IOException {
		mDataOut.write(CConnection.iDian.intToBytes(data));
		return this;
	}

	public BaseByteWriteUtil writeByte(byte[] bytes) throws IOException {
		mDataOut.write(bytes);
		return this;
	}
	
	public BaseByteWriteUtil writeByte(byte data) throws IOException {
		mDataOut.write(new byte[]{data});
		return this;
	}

	public BaseByteWriteUtil writeLong(long v) throws IOException {
		mDataOut.write(CConnection.iDian.longToBytes(v));
		return this;
	}


	public BaseByteWriteUtil writeDouble(double v) throws IOException {
		mDataOut.write(CConnection.iDian.doubleToBytes(v));
		return this;
	}

	public BaseByteWriteUtil writeFloat(float v) throws IOException {
		mDataOut.write(CConnection.iDian.floatToBytes(v));
		return this;
	}

	public BaseByteWriteUtil writeShort(short v) throws IOException {
		mDataOut.write(CConnection.iDian.shortToBytes(v));
		return this;
	}

	public BaseByteWriteUtil writeString(String string) throws IOException {
		byte bytes[] = string.getBytes();
		writeIn(bytes.length);
		mDataOut.write(bytes);
		return this;
	}

	/**
	 * 最后得到的完整数据格式
	 * 
	 * @return
	 */
	public abstract byte[] getBytes();
}