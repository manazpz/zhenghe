package com.example.jsData;

import java.util.Arrays;

public class Struct {
	/// 交易日
	private char[] TradingDay;
	/// 合约代码
	private char[] InstrumentID;
	/// 交易所代码
	private String ExchangeID;
	/// 合约在交易所的代码
	private char[] ExchangeInstID;
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
	private char[] UpdateTime;
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
	
	
	
	public char[] getTradingDay() {
		return TradingDay;
	}

	public void setTradingDay(char[] tradingDay) {
		TradingDay = tradingDay;
	}

	public char[] getInstrumentID() {
		return InstrumentID;
	}

	public void setInstrumentID(char[] instrumentID) {
		InstrumentID = instrumentID;
	}

	public String getExchangeID() {
		return ExchangeID;
	}

	public void setExchangeID(String exchangeID) {
		ExchangeID = exchangeID;
	}

	public char[] getExchangeInstID() {
		return ExchangeInstID;
	}

	public void setExchangeInstID(char[] exchangeInstID) {
		ExchangeInstID = exchangeInstID;
	}

	public double getLastPrice() {
		return LastPrice;
	}

	public void setLastPrice(double lastPrice) {
		LastPrice = lastPrice;
	}

	public double getPreSettlementPrice() {
		return PreSettlementPrice;
	}

	public void setPreSettlementPrice(double preSettlementPrice) {
		PreSettlementPrice = preSettlementPrice;
	}

	public double getPreClosePrice() {
		return PreClosePrice;
	}

	public void setPreClosePrice(double preClosePrice) {
		PreClosePrice = preClosePrice;
	}

	public double getPreOpenInterest() {
		return PreOpenInterest;
	}

	public void setPreOpenInterest(double preOpenInterest) {
		PreOpenInterest = preOpenInterest;
	}

	public double getOpenPrice() {
		return OpenPrice;
	}

	public void setOpenPrice(double openPrice) {
		OpenPrice = openPrice;
	}

	public double getHighestPrice() {
		return HighestPrice;
	}

	public void setHighestPrice(double highestPrice) {
		HighestPrice = highestPrice;
	}

	public double getLowestPrice() {
		return LowestPrice;
	}

	public void setLowestPrice(double lowestPrice) {
		LowestPrice = lowestPrice;
	}

	public int getVolume() {
		return Volume;
	}

	public void setVolume(int volume) {
		Volume = volume;
	}

	public double getTurnover() {
		return Turnover;
	}

	public void setTurnover(double turnover) {
		Turnover = turnover;
	}

	public double getOpenInterest() {
		return OpenInterest;
	}

	public void setOpenInterest(double openInterest) {
		OpenInterest = openInterest;
	}

	public double getClosePrice() {
		return ClosePrice;
	}

	public void setClosePrice(double closePrice) {
		ClosePrice = closePrice;
	}

	public double getSettlementPrice() {
		return SettlementPrice;
	}

	public void setSettlementPrice(double settlementPrice) {
		SettlementPrice = settlementPrice;
	}

	public double getUpperLimitPrice() {
		return UpperLimitPrice;
	}

	public void setUpperLimitPrice(double upperLimitPrice) {
		UpperLimitPrice = upperLimitPrice;
	}

	public double getLowerLimitPrice() {
		return LowerLimitPrice;
	}

	public void setLowerLimitPrice(double lowerLimitPrice) {
		LowerLimitPrice = lowerLimitPrice;
	}

	public double getPreDelta() {
		return PreDelta;
	}

	public void setPreDelta(double preDelta) {
		PreDelta = preDelta;
	}

	public double getCurrDelta() {
		return CurrDelta;
	}

	public void setCurrDelta(double currDelta) {
		CurrDelta = currDelta;
	}

	public char[] getUpdateTime() {
		return UpdateTime;
	}

	public void setUpdateTime(char[] updateTime) {
		UpdateTime = updateTime;
	}

	public int getUpdateMillisec() {
		return UpdateMillisec;
	}

	public void setUpdateMillisec(int updateMillisec) {
		UpdateMillisec = updateMillisec;
	}

	public double getBidPrice1() {
		return BidPrice1;
	}

	public void setBidPrice1(double bidPrice1) {
		BidPrice1 = bidPrice1;
	}

	public int getBidVolume1() {
		return BidVolume1;
	}

	public void setBidVolume1(int bidVolume1) {
		BidVolume1 = bidVolume1;
	}

	public double getAskPrice1() {
		return AskPrice1;
	}

	public void setAskPrice1(double askPrice1) {
		AskPrice1 = askPrice1;
	}

	public int getAskVolume1() {
		return AskVolume1;
	}

	public void setAskVolume1(int askVolume1) {
		AskVolume1 = askVolume1;
	}

	public double getBidPrice2() {
		return BidPrice2;
	}

	public void setBidPrice2(double bidPrice2) {
		BidPrice2 = bidPrice2;
	}

	public int getBidVolume2() {
		return BidVolume2;
	}

	public void setBidVolume2(int bidVolume2) {
		BidVolume2 = bidVolume2;
	}

	public double getAskPrice2() {
		return AskPrice2;
	}

	public void setAskPrice2(double askPrice2) {
		AskPrice2 = askPrice2;
	}

	public int getAskVolume2() {
		return AskVolume2;
	}

	public void setAskVolume2(int askVolume2) {
		AskVolume2 = askVolume2;
	}

	public double getBidPrice3() {
		return BidPrice3;
	}

	public void setBidPrice3(double bidPrice3) {
		BidPrice3 = bidPrice3;
	}

	public int getBidVolume3() {
		return BidVolume3;
	}

	public void setBidVolume3(int bidVolume3) {
		BidVolume3 = bidVolume3;
	}

	public double getAskPrice3() {
		return AskPrice3;
	}

	public void setAskPrice3(double askPrice3) {
		AskPrice3 = askPrice3;
	}

	public int getAskVolume3() {
		return AskVolume3;
	}

	public void setAskVolume3(int askVolume3) {
		AskVolume3 = askVolume3;
	}

	public double getBidPrice4() {
		return BidPrice4;
	}

	public void setBidPrice4(double bidPrice4) {
		BidPrice4 = bidPrice4;
	}

	public int getBidVolume4() {
		return BidVolume4;
	}

	public void setBidVolume4(int bidVolume4) {
		BidVolume4 = bidVolume4;
	}

	public double getAskPrice4() {
		return AskPrice4;
	}

	public void setAskPrice4(double askPrice4) {
		AskPrice4 = askPrice4;
	}

	public int getAskVolume4() {
		return AskVolume4;
	}

	public void setAskVolume4(int askVolume4) {
		AskVolume4 = askVolume4;
	}

	public double getBidPrice5() {
		return BidPrice5;
	}

	public void setBidPrice5(double bidPrice5) {
		BidPrice5 = bidPrice5;
	}

	public int getBidVolume5() {
		return BidVolume5;
	}

	public void setBidVolume5(int bidVolume5) {
		BidVolume5 = bidVolume5;
	}

	public double getAskPrice5() {
		return AskPrice5;
	}

	public void setAskPrice5(double askPrice5) {
		AskPrice5 = askPrice5;
	}

	public int getAskVolume5() {
		return AskVolume5;
	}

	public void setAskVolume5(int askVolume5) {
		AskVolume5 = askVolume5;
	}

	public double getAveragePrice() {
		return AveragePrice;
	}

	public void setAveragePrice(double averagePrice) {
		AveragePrice = averagePrice;
	}

	public Struct(char[] tradingDay, char[] instrumentID, String exchangeID, char[] exchangeInstID, double lastPrice,
			double preSettlementPrice, double preClosePrice, double preOpenInterest, double openPrice,
			double highestPrice, double lowestPrice, int volume, double turnover, double openInterest,
			double closePrice, double settlementPrice, double upperLimitPrice, double lowerLimitPrice, double preDelta,
			double currDelta, char[] updateTime, int updateMillisec, double bidPrice1, int bidVolume1, double askPrice1,
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
		return "Struct [TradingDay=" + Arrays.toString(TradingDay) + ", InstrumentID=" + Arrays.toString(InstrumentID)
				+ ", ExchangeID=" + ExchangeID + ", ExchangeInstID=" + Arrays.toString(ExchangeInstID) + ", LastPrice="
				+ LastPrice + ", PreSettlementPrice=" + PreSettlementPrice + ", PreClosePrice=" + PreClosePrice
				+ ", PreOpenInterest=" + PreOpenInterest + ", OpenPrice=" + OpenPrice + ", HighestPrice=" + HighestPrice
				+ ", LowestPrice=" + LowestPrice + ", Volume=" + Volume + ", Turnover=" + Turnover + ", OpenInterest="
				+ OpenInterest + ", ClosePrice=" + ClosePrice + ", SettlementPrice=" + SettlementPrice
				+ ", UpperLimitPrice=" + UpperLimitPrice + ", LowerLimitPrice=" + LowerLimitPrice + ", PreDelta="
				+ PreDelta + ", CurrDelta=" + CurrDelta + ", UpdateTime=" + Arrays.toString(UpdateTime)
				+ ", UpdateMillisec=" + UpdateMillisec + ", BidPrice1=" + BidPrice1 + ", BidVolume1=" + BidVolume1
				+ ", AskPrice1=" + AskPrice1 + ", AskVolume1=" + AskVolume1 + ", BidPrice2=" + BidPrice2
				+ ", BidVolume2=" + BidVolume2 + ", AskPrice2=" + AskPrice2 + ", AskVolume2=" + AskVolume2
				+ ", BidPrice3=" + BidPrice3 + ", BidVolume3=" + BidVolume3 + ", AskPrice3=" + AskPrice3
				+ ", AskVolume3=" + AskVolume3 + ", BidPrice4=" + BidPrice4 + ", BidVolume4=" + BidVolume4
				+ ", AskPrice4=" + AskPrice4 + ", AskVolume4=" + AskVolume4 + ", BidPrice5=" + BidPrice5
				+ ", BidVolume5=" + BidVolume5 + ", AskPrice5=" + AskPrice5 + ", AskVolume5=" + AskVolume5
				+ ", AveragePrice=" + AveragePrice + "]";
	}

	
	
	
}
