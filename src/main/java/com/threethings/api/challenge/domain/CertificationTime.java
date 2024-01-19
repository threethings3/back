package com.threethings.api.challenge.domain;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CertificationTime {
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private LocalTime startTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
	private LocalTime endTime;

	public boolean canCertification(TimeProvider timeProvider) {
		LocalTime currentTime = timeProvider.getTime();
		return !(currentTime.isBefore(startTime) || currentTime.isAfter(endTime));
	}
}
