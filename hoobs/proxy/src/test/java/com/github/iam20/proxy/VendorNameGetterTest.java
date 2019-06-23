package com.github.iam20.proxy;

import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import com.github.iam20.proxy.config.ApplicationConfig;
import com.github.iam20.proxy.model.*;
import com.github.iam20.proxy.util.VendorNameGetter;

@Slf4j
public class VendorNameGetterTest {
	private List<MacAddress> macAddressList;
	private TempHumid tempHumid;

	@Before
	public void before() {
		macAddressList = Arrays.asList(
				new MacAddressBuilder()
						.macAddr("F8:E6:1A:08:C5:1A")
						.orgName("Unknown")
						.build()
				,
				new MacAddressBuilder()
						.macAddr("50:1A:C5:FA:43:21")
						.orgName("Unknown")
						.build()
		);

		tempHumid = new TempHumidBuilder()
				.celsius(10.0)
				.humid(0.0)
				.build();

		ApplicationConfig.init();
	}

	@Test
	public void test() {
		CoreInformation coreInformation = new CoreInformation(macAddressList, tempHumid);
		CoreInformation result = VendorNameGetter.getVendorName(coreInformation);
		assertNotEquals("Unknown", result.getMacAddresses().get(0).getVendor());
		log.info("TEST PASSED");
	}

}
