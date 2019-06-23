package com.github.iam20.server;

import com.github.iam20.server.model.*;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ApiTest {
	@Autowired
	private MockMvc mvc;

	private CoreInformation testCoreInformation;
	private List<MacAddress> macAddresses;
	private TempHumid tempHumid;

	@Before
	public void before() {
		macAddresses = Arrays.asList(
				new MacAddressBuilder()
						.macAddr("ff:ff:ff:ff:ff:ff")
						.vendor("Apple Inc")
						.build(),
				new MacAddressBuilder()
						.macAddr("fe:ff:ff:ff:ff:ff")
						.vendor("samsung electronics")
						.build()
		);
		tempHumid = new TempHumidBuilder()
				.humid(0.0)
				.celsius(10.0)
				.build();

		testCoreInformation = new CoreInformation(macAddresses, tempHumid);
	}

	@Test
	public void getInfoTest() throws Exception {
		mvc.perform(get("/info")).andExpect(status().isOk());
		log.info("INFORMATION TEST PASSED");
	}

	@Test
	public void postPutInfoTest() throws Exception {
		String json = new Gson().toJson(testCoreInformation);
		mvc.perform(post("/info").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		mvc.perform(put("/info").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
		log.info("POST AND PUT INFORMATION TEST PASSED");
	}

	@Test
	public void postPutAirConditionTest() throws Exception {
		String json = new Gson().toJson(tempHumid);
		mvc.perform(put("/air-con").contentType(MediaType.APPLICATION_JSON).content(json))
			.andExpect(status().isOk());
		mvc.perform(post("/air-con").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		log.info("POST AND PUT AIR CONDITION TEST PASSED");
	}

	@Test
	public void postPutMacAddressesTest() throws Exception {
		String json = new Gson().toJson(macAddresses);
		mvc.perform(put("/mac").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
		mvc.perform(post("/mac").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
		log.info("POST AND PUT MAC ADDRESSES TEST PASSED");
	}
}
