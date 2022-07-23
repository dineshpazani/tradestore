package com.db.trade.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.db.trade.model.TradeStore;

@Repository
public class TradeRepo {

	Map<String, TradeStore> LIST_TRADE_STORE = new HashMap<>();

	public List<TradeStore> getTrade() throws Exception {
		List<TradeStore> tradeList = new ArrayList<>();
		LIST_TRADE_STORE.forEach((k, v) -> {
			tradeList.add(v);
		});
		return tradeList;
	}

	public boolean storeTrade(TradeStore tradeStore) throws Exception {
		LIST_TRADE_STORE.put(tradeStore.getTradeId(), tradeStore);
		return true;
	}

	/**
	 * Store should automatically update the expire flag if in a store the trade crosses the maturity date.
	 * @return
	 * @throws Exception
	 */
	public Map<String, TradeStore> generateTrade() throws Exception {

		getTrade().stream()
				.forEach(tradeObj -> {
					if(tradeObj.getMaturityDate().before(tradeObj.getCreationDate())){
						tradeObj.setExpired("Y");
					}
					LIST_TRADE_STORE.put(tradeObj.getTradeId(), tradeObj);					
				});

		return LIST_TRADE_STORE;
	}

}
