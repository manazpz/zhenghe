package com.example.utils;

public class Myutils {
	public static double ArryToDouble(byte[] Array, int Pos) {
		long accum = 0;
		accum = Array[Pos + 0] & 0xFF;
		accum |= (long) (Array[Pos + 1] & 0xFF) << 8;
		accum |= (long) (Array[Pos + 2] & 0xFF) << 16;
		accum |= (long) (Array[Pos + 3] & 0xFF) << 24;
		accum |= (long) (Array[Pos + 4] & 0xFF) << 32;
		accum |= (long) (Array[Pos + 5] & 0xFF) << 40;
		accum |= (long) (Array[Pos + 6] & 0xFF) << 48;
		accum |= (long) (Array[Pos + 7] & 0xFF) << 56;
		return Double.longBitsToDouble(accum);
	}

	public static int bytesToInt(byte[] src, int offset) {
		int value;
		value = (int) ((src[offset] & 0xFF) | ((src[offset + 1] & 0xFF) << 8) | ((src[offset + 2] & 0xFF) << 16)
				| ((src[offset + 3] & 0xFF) << 24));
		return value;
	}

}
