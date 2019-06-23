package com.github.iam20.proxy;

import static org.junit.Assert.assertEquals;

import com.github.iam20.proxy.model.*;
import com.github.iam20.proxy.util.CoreInfoParser;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.github.iam20.proxy.msg.Message.testJsonMsg;

@Slf4j
public class ParseCoreInformationTest {
	private List<MacAddress> macAddressList;
	private TempHumid tempHumid;

	@Before
	public void before() {
		macAddressList = Arrays.asList(
				new MacAddressBuilder()
						.macAddr("ff:ff:ff:ff:ff:ff")
						.vendor("apple inc")
						.build()
				,
				new MacAddressBuilder()
						.macAddr("fe:ff:ff:ff:ff:ff")
						.vendor("samsung electronics")
						.build()
		);

		tempHumid = new TempHumidBuilder()
						.celsius(10.0)
						.humid(0.0)
						.build();
	}

	@Test
	public void test() {
		JSONObject json = new JSONObject(testJsonMsg);
		CoreInformation coreInformation = CoreInfoParser.parse(json);
		assertEquals(macAddressList, coreInformation.getMacAddresses());
		assertEquals(tempHumid, coreInformation.getTempHumid());

		log.info(coreInformation.toString());
	}
}
