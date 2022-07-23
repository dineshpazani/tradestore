package com.db.trade.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.db.trade.model.TradeStore;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TradeControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetTrade() throws Exception {
		this.mockMvc.perform(get("/trade/get/all")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testStoreTrade() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/trade/add").content(asJsonString(getTrade()))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.countryPartyId").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.tradeId").value("T1"));
	}

	public TradeStore getTrade() throws Exception {
		return new TradeStore("T1", 1, "CP-1", "B1", new Date(), new Date(), "N");

	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
