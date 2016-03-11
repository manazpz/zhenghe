package com.example.jsData;

import java.io.UnsupportedEncodingException;

import com.example.datasave.contsData;
import com.example.utils.Myutils;

public class HuoQuHq {
	public static Struct struct;
	public Struct gethq(byte[] result) {
		try {
			char[] tradingDay = Myutils.bytesTochar(result, 0, 8);
			char[] instrumentID = Myutils.bytesTochar(result, 9, 31);
			String exchangeID = new String(result, 40, 9, "GBK");
			char[] exchangeInstID = Myutils.bytesTochar(result, 49, 31);
			double lastPrice = Myutils.ArryToDouble(result, 80);
			double preSettlementPrice = Myutils.ArryToDouble(result, 88);
			double preClosePrice = Myutils.ArryToDouble(result, 96);
			double preOpenInterest = Myutils.ArryToDouble(result, 104);
			double openPrice = Myutils.ArryToDouble(result, 112);
			double highestPrice = Myutils.ArryToDouble(result, 120);
			double lowestPrice = Myutils.ArryToDouble(result, 128);
			int volume = Myutils.bytesToInt(result, 136);
			double turnover = Myutils.ArryToDouble(result, 140);
			double openInterest = Myutils.ArryToDouble(result, 148);
			double closePrice = Myutils.ArryToDouble(result, 156);
			double settlementPrice = Myutils.ArryToDouble(result, 164);
			double upperLimitPrice = Myutils.ArryToDouble(result, 172);
			double lowerLimitPrice = Myutils.ArryToDouble(result, 180);
			double preDelta = Myutils.ArryToDouble(result, 188);
			double currDelta = Myutils.ArryToDouble(result, 196);

			char[] updateTime = Myutils.bytesTochar(result, 204, 12);
			int updateMillisec = Myutils.bytesToInt(result, 216);
////////////////////////////////////////////////////////////////////////////////
			double bidPrice1 = Myutils.ArryToDouble(result, 220);
			int bidVolume1 = Myutils.bytesToInt(result, 228);
			double askPrice1 = Myutils.ArryToDouble(result, 232);
			int askVolume1 = Myutils.bytesToInt(result, 240);

			double bidPrice2 = Myutils.ArryToDouble(result, 244);
			int bidVolume2 = Myutils.bytesToInt(result, 252);
			double askPrice2 = Myutils.ArryToDouble(result, 256);
			int askVolume2 = Myutils.bytesToInt(result, 264);

			double bidPrice3 = Myutils.ArryToDouble(result, 268);
			int bidVolume3 = Myutils.bytesToInt(result, 276);
			double askPrice3 = Myutils.ArryToDouble(result, 280);
			int askVolume3 = Myutils.bytesToInt(result, 288);

			double bidPrice4 = Myutils.ArryToDouble(result, 292);
			int bidVolume4 = Myutils.bytesToInt(result, 300);
			double askPrice4 = Myutils.ArryToDouble(result, 304);
			int askVolume4 = Myutils.bytesToInt(result, 312);

			double bidPrice5 = Myutils.ArryToDouble(result, 316);
			int bidVolume5 = Myutils.bytesToInt(result, 324);
			double askPrice5 = Myutils.ArryToDouble(result, 328);
			int askVolume5 = Myutils.bytesToInt(result, 336);
			double averagePrice = Myutils.ArryToDouble(result, 340);
			struct = new Struct(tradingDay, instrumentID, exchangeID, exchangeInstID, 
					lastPrice, preSettlementPrice, preClosePrice, preOpenInterest, openPrice, 
					highestPrice, lowestPrice, volume, turnover, openInterest, closePrice, 
					settlementPrice, upperLimitPrice, lowerLimitPrice, preDelta, currDelta, updateTime, 
					updateMillisec, bidPrice1, bidVolume1, askPrice1, askVolume1, bidPrice2, bidVolume2, 
					askPrice2, askVolume2, bidPrice3, bidVolume3, askPrice3, askVolume3, 
					bidPrice4, bidVolume4, askPrice4, askVolume4, bidPrice5, bidVolume5, 
					askPrice5, askVolume5, averagePrice);
			return struct;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 
	}

}
