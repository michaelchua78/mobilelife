package com.mike.mobilelife.service;

import com.mike.mobilelife.model.ConvertResponse;

public interface CurrencyConvertService {
	public ConvertResponse convert(String from, String to, double value) throws Exception;
}
