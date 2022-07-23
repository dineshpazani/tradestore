package com.db.trade.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.db.trade.dao.TradeRepo;
import com.db.trade.model.TradeStore;

@ExtendWith(MockitoExtension.class)
public class TradeServiceUnitTest {

	@Mock
	TradeRepo tradeRepoMock;

    @InjectMocks
	TradeService tradeService;


	@Test
	public void testGetTrade() throws Exception {
		when(tradeRepoMock.getTrade())
				.thenReturn(List.of(new TradeStore("T1", 6, "CP-1", "B5", getFutureDate(), new Date(), "N")));
		List<TradeStore> ts = tradeService.getTrade();
		assertEquals(1, ts.size());
		assertEquals("B5", ts.get(0).getBookId());
	}
	
	@Test
	public void testStoreTrade_success() throws Exception {
		Boolean result = tradeService.storeTrade(new TradeStore("T1", 6, "CP-1", "B5", getFutureDate(), new Date(), "N"));
		assertEquals(true, result);
		
	}
	
	@Test
	public void testStoreTrade_same_trade_and_same_version() throws Exception {
		Boolean result = tradeService.storeTrade(new TradeStore("T1", 6, "CP-1", "B5",  getFutureDate(), new Date(), "N"));
		assertEquals(true, result);
		
	}
	
	@Test
	public void testStoreTrade_same_trade_pastDate() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> tradeService.storeTrade(new TradeStore("T1", 6, "CP-1", "B5",  getPastDate(), new Date(), "N")));
		assertEquals("Trade date should not be lower the current Date!", exception.getMessage());
		
	}
	
	@Test
	public void testStoreTrade_lower_version() throws Exception {
		Boolean result = tradeService.storeTrade(new TradeStore("T1", 6, "CP-1", "B5", getFutureDate(), new Date(), "N"));
		assertEquals(true, result);		
	}
	
	@Test
	public void testStoreTrade_Update_flog() throws Exception {
		Boolean result = tradeService.storeTrade(new TradeStore("T1", 6, "CP-1", "B5", getFutureDate(), new Date(), "N"));
		assertEquals(true, result);		
	}
	
	private Date getFutureDate() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return format.parse("26/10/2024");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Date getPastDate() {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return format.parse("26/10/2020");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


}
