package com.mike.mobilelife.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mike.mobilelife.config.FixerAPIConfig;
import com.mike.mobilelife.model.ConvertResponse;
import com.mike.mobilelife.model.FixerResponse;


@Service
public class CurrencyConvertServiceImpl implements CurrencyConvertService {
	Logger logger = LoggerFactory.getLogger(CurrencyConvertServiceImpl.class);
	
	private WebClient webClient = null;
	
	@Autowired
	private FixerAPIConfig config;
	
	@Autowired
	public CurrencyConvertServiceImpl() {
		this.webClient = WebClient.create("http://data.fixer.io/api");
	}

	@Override
	public ConvertResponse convert(String from, String to, double value) throws Exception {
		String accessKey = config.getAccessKey();
		
		// NOTE: Free API Key doesn't allow to set base currency and cannot use the other /convert API
		FixerResponse latestRates = this.webClient
			.get()
			.uri(uriBuilder -> uriBuilder
					.path("/latest")
					.queryParam("access_key", accessKey)
					.queryParam("symbols", to)
					.queryParam("format", 1)
					.build())
			.retrieve()
			.bodyToMono(FixerResponse.class)
			.block();		// Change to async way in future
		
		if (!latestRates.getSuccess()) {
			throw new Exception("Failed to convert currency");
		}
		
		Map<String, Double> rates = latestRates.getRates();
		
		return new ConvertResponse(rates.get(to) * value);
	}

}
