package com.mike.mobilelife.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mike.mobilelife.model.ConvertResponse;
import com.mike.mobilelife.service.CurrencyConvertService;

@RestController
@Validated
public class CurrencyConverterController {
	Logger logger = LoggerFactory.getLogger(CurrencyConverterController.class);
	
	@Autowired
	private CurrencyConvertService convertService;
	
	@RequestMapping(path = "/convert", produces = "application/json")
	public ResponseEntity<ConvertResponse> convertCurrency(
			@RequestParam(value="from")
			@NotBlank(message = "Missing param \"from\"")
				String from,
			@RequestParam(value="to")
			@NotBlank(message = "Missing param \"to\"")
				String to,
			@RequestParam(value="value")
			@Min(value = 0, message = "Value must be more than 0")
				double value
	) {		
		try {
			ConvertResponse result = convertService.convert(from, to, value);
			
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			logger.error("Failed to convert the currency: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}
}
