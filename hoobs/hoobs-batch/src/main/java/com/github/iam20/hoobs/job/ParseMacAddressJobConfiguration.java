package com.github.iam20.hoobs.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.iam20.hoobs.model.MacAddress;

@Slf4j
@Configuration
public class ParseMacAddressJobConfiguration {
	private StepBuilderFactory stepBuilderFactory;
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	public ParseMacAddressJobConfiguration(StepBuilderFactory stepBuilderFactory,
	                                       JobBuilderFactory jobBuilderFactory) {
		this.stepBuilderFactory = stepBuilderFactory;
		this.jobBuilderFactory = jobBuilderFactory;
	}

	@Bean
	public Job parseMacAddr(Step parseMac) {
		return jobBuilderFactory.get("parseMacAddr")
				.start(parseMac)
				.build();
	}

	@Bean
	public Step parseMac(VendorNameReader reader,
	                     VendorNameWriter writer) {
		return stepBuilderFactory.get("parseMac")
				.<MacAddress, MacAddress>chunk(Integer.MAX_VALUE)
				.reader(reader)
				.writer(writer)
				.build();
	}
}
