package com.db.trade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.trade.model.TradeStore;
import com.db.trade.service.TradeService;

@RestController
public class TradeController {

	@Autowired private TradeService tradeService;
	
	@GetMapping("/trade/get/all")
	public ResponseEntity<Object> getTrade() throws Exception{		
		try {
			return ResponseEntity.status(HttpStatus.OK)
			        .body(tradeService.getTrade());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			        .body("Internal Server Error!");
		}
	}
	
	@PostMapping("/trade/add")
	public  ResponseEntity<Object>  storeTrade(@RequestBody TradeStore tradeStore) throws Exception{
		try {
			if(tradeService.storeTrade(tradeStore)) {
				return ResponseEntity.status(HttpStatus.CREATED)
				        .body(tradeStore);
			}
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			        .body("Internal Server Error!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			        .body("Exception! "+e.getMessage());
		}
	}
}
