package com.dartou.lib.network.socket.dian;

import com.dartou.lib.utils.FormatTransferUtil;

/**
 * 大端
 * @author cyn
 *
 */
public class BigDian implements IDian{

	@Override
	public int bytesToInt(byte[] bytes) {
		return FormatTransferUtil.hBytesToInt(bytes);
	}

	@Override
	public long bytesToLong(byte[] bytes) {
		return FormatTransferUtil.hBytesToInt(bytes);
	}

	@Override
	public short bytesToShort(byte[] bytes) {
		return FormatTransferUtil.hBytesToShort(bytes);
	}

	@Override
	public float bytesToFloat(byte[] bytes) {
		return FormatTransferUtil.hBytesToFloat(bytes);
	}

	@Override
	public double bytesToDouble(byte[] bytes) {
		return FormatTransferUtil.hBytesToDouble(bytes);
	}

	@Override
	public byte[] intToBytes(int n) {
		return FormatTransferUtil.toHH(n);
	}

	@Override
	public byte[] longToBytes(long n) {
		return FormatTransferUtil.toHH(n);
	}

	@Override
	public byte[] shortToBytes(short n) {
		return FormatTransferUtil.toHH(n);
	}

	@Override
	public byte[] floatToBytes(float n) {
		return FormatTransferUtil.toHH(n);
	}

	@Override
	public byte[] doubleToBytes(double n) {
		return FormatTransferUtil.toHH(n);
	}

	
}
