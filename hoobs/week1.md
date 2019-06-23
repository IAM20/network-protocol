# WEEK 1
-----------
### Completed jobs 
* REST Server --> spring-boot
* API Prototype 
  * http://106.10.54.51:18080/info
```json
{
    "macAddresses": [
        {
            "macAddr": "ff:ff:ff:ff:ff:ff",
            "orgName": "Apple Inc"
        },
        {
            "macAddr": "fe:ff:ff:ff:ff:ff",
            "orgName": "samsung electronics"
        }
    ],
    "tempHumid": {
        "celsius": 10,
        "humid": 0
    }
}
```

* Raspberry pi arp module
  * We need the MAC address to know device vendor
  * Ping to all
    * Make library to send ping for all same network users.
  * arp -a
    * commons-exec (apache)

```java
@Before
public void before() {
ApplicationConfig.init();
}

@Test
public void executeArpTest() {
    List<MacAddress> result = ArpExecutor.findAllDevice();
	for (MacAddress macAddress : result) {
		log.info("Device info :         {}", macAddress.getOrgName());
		log.info("Mac address info :    {}", macAddress.getMacAddr());
	}
}
``` 

```log
70750 [main] INFO  com.github.iam20.device.ArpExecutorTest  - Device info :         Unknown
70750 [main] INFO  com.github.iam20.device.ArpExecutorTest  - Mac address info :    f2:98:9d:f1:e:64
70750 [main] INFO  com.github.iam20.device.ArpExecutorTest  - Device info :         Unknown
70750 [main] INFO  com.github.iam20.device.ArpExecutorTest  - Mac address info :    8c:85:90:19:c5:17
```

### Changed...
* Vendor identifier (http://standards-oui.ieee.org/oui.txt)
  * 25000 or more vendor is enrolled.....
  * In raspberry pi... it's difficult to parse and save it..
  * So server does it.
  * Maybe in the server-side..

### Some feedbacks
* GET the information from AP
  * We cannot find the module which gets the mac address information from AP
* Proxy server --> cache
  * With some module for translation.
* Push --> TBD. We do not need the push.. not yet.

### Up next
* Raspberry pi module fin.
    * Temp humid module
    * Coap sender module
* Cache fin.
    * Coap receiver module
    * http sender module


### [**Codes**](https://github.com/iam20/hoobs)