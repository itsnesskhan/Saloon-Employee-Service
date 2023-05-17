package com.sloon.employee.payloads;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResponse {

	private Object data;
	
	private boolean status;
	
	private LocalDate timeStamp;
}
