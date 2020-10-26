package com.imyc.SBAP.config.repositroy;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchCriteria {
	
    private String key;
    private String operation;
    private Object value;
	
	public SearchCriteria(String key, String operation, Object value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
}
