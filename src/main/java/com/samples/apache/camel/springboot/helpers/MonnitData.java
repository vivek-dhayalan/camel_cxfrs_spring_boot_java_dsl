package com.samples.apache.camel.springboot.helpers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="APIDataMessage")
@XmlAccessorType(XmlAccessType.FIELD)
public class MonnitData {


    @XmlAttribute(name ="MessageID")
    public String MessageID;

    @XmlAttribute(name ="SensorID")
    public String SensorID;

 	@XmlAttribute(name ="MessageDate")
    public String MessageDate;

 	@XmlAttribute(name ="State")
    public String State;

 	@XmlAttribute(name ="SignalStrength")
    public String SignalStrength;

 	@XmlAttribute(name ="Voltage")
    public String Voltage;

	@XmlAttribute(name ="Battery")
    public String Battery;

 	@XmlAttribute(name ="Data")
    public String Data;

 	@XmlAttribute(name ="DisplayData")
    public String DisplayData;

	@XmlAttribute(name ="PlotValue")
    public String PlotValue;	

	@XmlAttribute(name ="MetNotificationRequirements")
    public String MetNotificationRequirements;

	@XmlAttribute(name ="GatewayID")
    public String GatewayID;

	@XmlAttribute(name ="DataValues")
    public String DataValues;

	@XmlAttribute(name ="DataTypes")
    public String DataTypes;

	@XmlAttribute(name ="PlotValues")
    public String PlotValues;

	@XmlAttribute(name ="PlotLabels")
    public String PlotLabels;	
}
