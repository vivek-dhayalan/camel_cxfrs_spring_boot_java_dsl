package com.samples.apache.camel.springboot.helpers;

import org.joda.time.DateTime;

public class MeasurementEventDTO {

	private DateTime eventTime;
    private Double measurementReading;
	private Double signalStrength;
	private Double batteryStrength;
	private String extEventRefId;
	
    public DateTime getEventTime() {
		return eventTime;
	}
	public void setEventTime(DateTime eventTime) {
		this.eventTime = eventTime;
	}
	public Double getMeasurementReading() {
		return measurementReading;
	}
	public void setMeasurementReading(Double measurementReading) {
		this.measurementReading = measurementReading;
	}
	public Double getSignalStrength() {
		return signalStrength;
	}
	public void setSignalStrength(Double signalStrength) {
		this.signalStrength = signalStrength;
	}
	public Double getBatteryStrength() {
		return batteryStrength;
	}
	public void setBatteryStrength(Double batteryStrength) {
		this.batteryStrength = batteryStrength;
	}
	public String getExtEventRefId() {
		return extEventRefId;
	}
	public void setExtEventRefId(String extEventRefId) {
		this.extEventRefId = extEventRefId;
	}	
}	