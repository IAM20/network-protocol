package com.github.iam20.hoobs;

import static org.junit.Assert.assertNotNull;

import com.github.iam20.hoobs.model.MacAddress;
import com.github.iam20.hoobs.util.MacAddressParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

@Slf4j
public class MacAddressParserTest {
	private List<String> testPool = Arrays.asList(
		"E0-43-DB   (hex)\t\tShenzhen ViewAt Technology Co.,Ltd. ",
		"24-05-F5   (hex)\t\tIntegrated Device Technology (Malaysia) Sdn. Bhd."
	);

	@Test
	public void test() {
		for (String test : testPool) {
			MacAddress macAddress = MacAddressParser.parse(test);
			assertNotNull(macAddress);
			log.info("MAC ADDRESS : {}", macAddress.getMacAddr());
			log.info("Vendor name : {}", macAddress.getVendor());
		}
	}
}
