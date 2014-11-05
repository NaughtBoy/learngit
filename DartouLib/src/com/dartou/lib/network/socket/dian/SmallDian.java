package com.dartou.lib.network.socket.dian;

import com.dartou.lib.utils.FormatTransferUtil;

/**
 * 小端
 * @author cyn
 *
 */
public class SmallDian implements IDian{

	@Override
	public int bytesToInt(byte[] bytes) {
		// TODO Auto-generated method stub
		return FormatTransferUtil.lBytesToInt(bytes);
	}

	@Override
	public long bytesToLong(byte[] bytes) {
		// TODO Auto-generated method stub
		return FormatTransferUtil.lBytesToLong(bytes);
	}

	@Override
	public short bytesToShort(byte[] bytes) {
		// TODO Auto-generated method stub
		return FormatTransferUtil.lBytesToShort(bytes);
	}

	@Override
	public float bytesToFloat(byte[] bytes) {
		// TODO Auto-generated method stub
		return FormatTransferUtil.lBytesToFloat(bytes);
	}

	@Override
	public double bytesToDouble(byte[] bytes) {
		// TODO Auto-generated method stub
		return FormatTransferUtil.lBytesToDouble(bytes);
	}

	@Override
	public byte[] intToBytes(int n) {
		// TODO Auto-generated method stub
		return FormatTransferUtil.toLH(n);
	}

	@Override
	public byte[] longToBytes(long n) {
		// TODO Auto-generated method stub
		return FormatTransferUtil.toLH(n);
	}

	@Override
	public byte[] shortToBytes(short n) {
		// TODO Auto-generated method stub
		return FormatTransferUtil.toLH(n);
	}

	@Override
	public byte[] floatToBytes(float n) {
		// TODO Auto-generated method stub
		return FormatTransferUtil.toLH(n);
	}

	@Override
	public byte[] doubleToBytes(double n) {
		// TODO Auto-generated method stub
		return FormatTransferUtil.toLH(n);
	}

}
