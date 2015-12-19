# camel_cxfrs_spring_boot_java_dsl
An example project to show how to configure cxfrs route in apache camel Spring boot project.

#how to run this project
mvn spring-boot:run

Configured to run in 1.7 version of java. 

When we run this project runs in development profile as a default configuration.
An while we run this project a rest endpoint is exposed by camel @ <host:port>/monnitmysqltsdb

the above endpoint will expect request body of the following format (should be xml):


APIDataMessage MessageID="747950743" SensorID="NILM35DZ-01A8BBAC" MessageDate="2015-11-27T12:02:32.792Z" State="16" SignalStrength="10" Voltage="2.83" Battery="60" Data="60" DisplayData="67.1° F" PlotValue="67.1" MetNotificationRequirements="False" GatewayID="106558" DataValues="19.5" DataTypes="TemperatureData" PlotValues="67.1" PlotLabels="Fahrenheit" 

When the endpoint is been hit camel will multicast the request to other routes that is defined in MySpringBootRouter.

Note in multicasting we are converting the xml as json and that is been passed on to the cxfrs client endpoint.

#assumption
we are assuming that we have a rest service running in the clienthost which expects json request body listenning @ /api/measurementEvent & /api/opentsdb
