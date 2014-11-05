package com.dartou.lib.network.socket.dian;
/**
 * 大小端需要实现的接口
 * @author cyn
 */
public interface IDian {

	public int bytesToInt(byte bytes[]);
	public long bytesToLong(byte bytes[]);
	public short bytesToShort(byte bytes[]);
	public float bytesToFloat(byte bytes[]);
	public double bytesToDouble(byte bytes[]);
	
	
	public byte[] intToBytes(int n);
	public byte[] longToBytes(long n);
	public byte[] shortToBytes(short n);
	public byte[] floatToBytes(float n);
	public byte[] doubleToBytes(double n);
	
}
