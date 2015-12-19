package com.samples.apache.camel.springboot.helpers;

import org.apache.camel.Converter;
import org.joda.time.DateTime;

@Converter
public class CustomConverter {

	@Converter
	public static MeasurementEventDTO toMeasurementEvent(MonnitData monnitdata) {
        MeasurementEventDTO me = new MeasurementEventDTO();

        me.setEventTime(DateTime.parse(monnitdata.MessageDate));
        me.setMeasurementReading(Double.parseDouble(monnitdata.Data));
        me.setSignalStrength(Double.parseDouble(monnitdata.SignalStrength));
        me.setBatteryStrength(Double.parseDouble(monnitdata.Battery));
        me.setExtEventRefId(monnitdata.SensorID);
        	
        return me;
    }
}
