# geoip4j
A simple helper to get the country name, latitude, longtude, etc. given an IP address.

You can use It as:

```java
GeoIp4J geoIp4J = new GeoIp4J(GeoIp4J.WS_FREEGEOIP_NET);
FreeGeoIpResponse info = geoIp4J.getGeoInfo("167.60.171.196");
System.out.println("Country name: " + info.getCountryName());
```
