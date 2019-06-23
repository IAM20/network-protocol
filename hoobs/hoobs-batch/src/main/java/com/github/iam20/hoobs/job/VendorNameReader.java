package com.github.iam20.hoobs.job;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.iam20.hoobs.model.MacAddress;
import com.github.iam20.hoobs.util.MacAddressParser;

@Slf4j
@Component
public class VendorNameReader implements ItemStreamReader<MacAddress> {
	private ExecutionContext executionContext;
	private String uri;
	private List<String> lines;
	private Iterator<String> lineIter;

	@Autowired
	public VendorNameReader(@Value("${ieee.macAddr.uri}")String uri) {
		this.uri = uri;
		log.info(uri);
	}

	@Override
	public MacAddress read() {
		if (lines == null || lines.size() == 0 || !lineIter.hasNext()) {
			return null;
		}

		String line = lineIter.next();
		MacAddress macAddress = MacAddressParser.parse(line);

		if (macAddress == null) {
			return read();
		}
		log.info(macAddress.getMacAddr());
		return macAddress;
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		this.executionContext = executionContext;
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders httpHeaders = new HttpHeaders();
		HttpEntity httpEntity = new HttpEntity(httpHeaders);
		String file = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, String.class).getBody();
		lines = Arrays.asList(file.split("\n"));
		lineIter = lines.iterator();
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		this.executionContext = executionContext;
	}

	@Override
	public void close() throws ItemStreamException {
	}
}
