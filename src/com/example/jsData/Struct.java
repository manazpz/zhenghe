package com.example.jsData;

import java.util.Arrays;

public class Struct {
	/// 交易日
	private String TradingDay;
	/// 合约代码
	private String InstrumentID;
	/// 交易所代码
	private String ExchangeID;
	/// 合约在交易所的代码
	private String ExchangeInstID;
	/// 最新价
	private double LastPrice;
	/// 上次结算价
	private double PreSettlementPrice;
	/// 昨收盘
	private double PreClosePrice;
	/// 昨持仓量
	private double PreOpenInterest;
	/// 今开盘
	private double OpenPrice;
	/// 最高价
	private double HighestPrice;
	/// 最低价
	private double LowestPrice;
	/// 数量
	private int Volume;
	/// 成交金额
	private double Turnover;
	/// 持仓量
	private double OpenInterest;
	/// 今收盘
	private double ClosePrice;
	/// 本次结算价
	private double SettlementPrice;
	/// 涨停板价
	private double UpperLimitPrice;
	/// 跌停板价
	private double LowerLimitPrice;
	/// 昨虚实度
	private double PreDelta;
	/// 今虚实度
	private double CurrDelta;
	/// 最后修改时间
	private String UpdateTime;
	/// 最后修改毫秒
	private int UpdateMillisec;

	/// 申买价一
	private double BidPrice1;
	/// 申买量一
	private int BidVolume1;
	/// 申卖价一
	private double AskPrice1;
	/// 申卖量一
	private int AskVolume1;
	/// 申买价二
	private double BidPrice2;
	/// 申买量二
	private int BidVolume2;
	/// 申卖价二
	private double AskPrice2;
	/// 申卖量二
	private int AskVolume2;
	/// 申买价三
	private double BidPrice3;
	/// 申买量三
	private int BidVolume3;
	/// 申卖价三
	private double AskPrice3;
	/// 申卖量三
	private int AskVolume3;
	/// 申买价四
	private double BidPrice4;
	/// 申买量四
	private int BidVolume4;
	/// 申卖价四
	private double AskPrice4;
	/// 申卖量四
	private int AskVolume4;
	/// 申买价五
	private double BidPrice5;
	/// 申买量五
	private int BidVolume5;
	/// 申卖价五
	private double AskPrice5;
	/// 申卖量五
	private int AskVolume5;
	/// 当日均价
	private double AveragePrice;
	
	
	public Struct(String tradingDay, String instrumentID, String exchangeID, String exchangeInstID, double lastPrice,
			double preSettlementPrice, double preClosePrice, double preOpenInterest, double openPrice,
			double highestPrice, double lowestPrice, int volume, double turnover, double openInterest,
			double closePrice, double settlementPrice, double upperLimitPrice, double lowerLimitPrice, double preDelta,
			double currDelta, String updateTime, int updateMillisec, double bidPrice1, int bidVolume1, double askPrice1,
			int askVolume1, double bidPrice2, int bidVolume2, double askPrice2, int askVolume2, double bidPrice3,
			int bidVolume3, double askPrice3, int askVolume3, double bidPrice4, int bidVolume4, double askPrice4,
			int askVolume4, double bidPrice5, int bidVolume5, double askPrice5, int askVolume5, double averagePrice) {
		super();
		TradingDay = tradingDay;
		InstrumentID = instrumentID;
		ExchangeID = exchangeID;
		ExchangeInstID = exchangeInstID;
		LastPrice = lastPrice;
		PreSettlementPrice = preSettlementPrice;
		PreClosePrice = preClosePrice;
		PreOpenInterest = preOpenInterest;
		OpenPrice = openPrice;
		HighestPrice = highestPrice;
		LowestPrice = lowestPrice;
		Volume = volume;
		Turnover = turnover;
		OpenInterest = openInterest;
		ClosePrice = closePrice;
		SettlementPrice = settlementPrice;
		UpperLimitPrice = upperLimitPrice;
		LowerLimitPrice = lowerLimitPrice;
		PreDelta = preDelta;
		CurrDelta = currDelta;
		UpdateTime = updateTime;
		UpdateMillisec = updateMillisec;
		BidPrice1 = bidPrice1;
		BidVolume1 = bidVolume1;
		AskPrice1 = askPrice1;
		AskVolume1 = askVolume1;
		BidPrice2 = bidPrice2;
		BidVolume2 = bidVolume2;
		AskPrice2 = askPrice2;
		AskVolume2 = askVolume2;
		BidPrice3 = bidPrice3;
		BidVolume3 = bidVolume3;
		AskPrice3 = askPrice3;
		AskVolume3 = askVolume3;
		BidPrice4 = bidPrice4;
		BidVolume4 = bidVolume4;
		AskPrice4 = askPrice4;
		AskVolume4 = askVolume4;
		BidPrice5 = bidPrice5;
		BidVolume5 = bidVolume5;
		AskPrice5 = askPrice5;
		AskVolume5 = askVolume5;
		AveragePrice = averagePrice;
	}


	@Override
	public String toString() {
		return "Struct [TradingDay=" + TradingDay + ", InstrumentID=" + InstrumentID + ", ExchangeID=" + ExchangeID
				+ ", ExchangeInstID=" + ExchangeInstID + ", LastPrice=" + LastPrice + ", PreSettlementPrice="
				+ PreSettlementPrice + ", PreClosePrice=" + PreClosePrice + ", PreOpenInterest=" + PreOpenInterest
				+ ", OpenPrice=" + OpenPrice + ", HighestPrice=" + HighestPrice + ", LowestPrice=" + LowestPrice
				+ ", Volume=" + Volume + ", Turnover=" + Turnover + ", OpenInterest=" + OpenInterest + ", ClosePrice="
				+ ClosePrice + ", SettlementPrice=" + SettlementPrice + ", UpperLimitPrice=" + UpperLimitPrice
				+ ", LowerLimitPrice=" + LowerLimitPrice + ", PreDelta=" + PreDelta + ", CurrDelta=" + CurrDelta
				+ ", UpdateTime=" + UpdateTime + ", UpdateMillisec=" + UpdateMillisec + ", BidPrice1=" + BidPrice1
				+ ", BidVolume1=" + BidVolume1 + ", AskPrice1=" + AskPrice1 + ", AskVolume1=" + AskVolume1
				+ ", BidPrice2=" + BidPrice2 + ", BidVolume2=" + BidVolume2 + ", AskPrice2=" + AskPrice2
				+ ", AskVolume2=" + AskVolume2 + ", BidPrice3=" + BidPrice3 + ", BidVolume3=" + BidVolume3
				+ ", AskPrice3=" + AskPrice3 + ", AskVolume3=" + AskVolume3 + ", BidPrice4=" + BidPrice4
				+ ", BidVolume4=" + BidVolume4 + ", AskPrice4=" + AskPrice4 + ", AskVolume4=" + AskVolume4
				+ ", BidPrice5=" + BidPrice5 + ", BidVolume5=" + BidVolume5 + ", AskPrice5=" + AskPrice5
				+ ", AskVolume5=" + AskVolume5 + ", AveragePrice=" + AveragePrice + "]";
	}
	

	
}
