package com.db.trade.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.trade.dao.TradeRepo;
import com.db.trade.model.TradeStore;

@Service
public class TradeService {

	@Autowired
	private TradeRepo tradeRepo;

	public List<TradeStore> getTrade() throws Exception {
		return tradeRepo.getTrade();
	}

	public boolean storeTrade(TradeStore tradeStore) throws Exception {
		Map<String, TradeStore> tradeStoreMap = tradeRepo.generateTrade();
		
		/*
		 * Store should not allow the trade which has less maturity date then today date.
		 */
		if(tradeStore.getMaturityDate().before(new Date())) {
			throw new Exception("Trade date should not be lower the current Date!");
		}
		/*
		 * During transmission if the lower version is being received by the store it will reject the trade and
throw an exception. If the version is same it will override the existing record.
		 */
		if (tradeStoreMap.containsKey(tradeStore.getTradeId())) {
			TradeStore ts = tradeStoreMap.get(tradeStore.getTradeId());
			if (tradeStore.getVersion() < ts.getVersion()) {
				throw new Exception("Trade version should not be lower version!");
			} else if (tradeStore.getVersion().equals(ts.getVersion())) {
				tradeStoreMap.put(tradeStore.getTradeId(), tradeStore);
			}else {
				tradeStoreMap.put(tradeStore.getTradeId(), tradeStore);
				tradeRepo.storeTrade(tradeStore);
			}
		}else {
			tradeStoreMap.put(tradeStore.getTradeId(), tradeStore);
			tradeRepo.storeTrade(tradeStore);
		}
		return true;
	}

}
