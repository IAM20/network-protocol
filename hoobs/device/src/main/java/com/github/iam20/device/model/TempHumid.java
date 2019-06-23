package com.github.iam20.device.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempHumid {
	double celsius;
	double humid;
}
