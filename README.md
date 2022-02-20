# AndroidIotMqtt
## Table of Contents
1. [General Info](#general-info)
2. [List of IoT device](#list-of-iot-devices)
3. [Topics Publish](#topics-publish)
4. [Download](#download)
5. [Installation](#installation)
6. [Why am I open-sourcing it?](#why-am-i-open-sourcing-it)
7. [Links](#links)
## General Info
***
This is an open source code to build an Android application for IoT. It consists of creating a Mqtt client connected to a Broker in the same local network. We will be able to add from a predetermined list different IoT devices. In this list we have Tasmota and Zigbee2Mqtt devices. The configuration is very simple since the Topics are automatically created and added directly to a Dashboard.

### Screenshot
***
<div align="center">
    <img src="/AppMqttScreenCapture/Menu.png" width="200px"</img>
    <img src="/AppMqttScreenCapture/Relay.png" width="200px"</img>
    <img src="/AppMqttScreenCapture/AddDevice.png" width="200px"</img>
    <img src="/AppMqttScreenCapture/SettingConnection.png" width="200px"</img>
    <img src="/AppMqttScreenCapture/SettingTopic.png" width="200px"</img>
    <img src="/AppMqttScreenCapture/Temperature.png" width="200px"</img>
</div>    

## List of IoT devices
***
A list of IoT devices used within the project:
* Sonoff20 (Tasmota)
* TuyaZigbeeSensor (Zigbee2Mqtt)
* AqaraTemp (Zigbee2Mqtt)
* SonoffSNZB02 (Zigbee2Mqtt)
* XiaomiZNCZ04LM (Zigbee2Mqtt)

## Topics Publish
***
The publish topic is created automatically and the device has to be configured with this same topic. 
1. Sonoff20:
    + Example:
      + Configuration (App): Type device: **Sonoff20**; Number Device: **1**.
      + Topic (Tasmota): Dnomaid/Router_1/**Sonoff20**_**1**/Relay_1
2. XiaomiZNCZ04LM: 
   + Example:  
      + Configuration (App): Type device: **XiaomiZNCZ04LM**; Number Device: **2**.
      + Topic (Zigbee2mqtt): Dnomaid/mix/CC2531_1/**XiaomiZNCZ04LM**_**2**/RelaySensorClimate_1.
3. SonoffSNZB02: 
   + Example:  
      + Configuration (App): Type device: **SonoffSNZB02**; Number Device: **1**.
      + Topic (Zigbee2mqtt): Dnomaid/stat/CC2531_1/**SonoffSNZB02**_**1**/SensorClimate_1.	  
4. AqaraTemp: 
   + Example:  
      + Configuration (App): Type device: **AqaraTemp**; Number Device: **1**.
      + Topic (Zigbee2mqtt): Dnomaid/stat/CC2531_1/**AqaraTemp**_**1**/SensorClimate_1.	  
5. TuyaZigbeeSensor: 
   + Example:  
      + Configuration (App): Type device: **TuyaZigbeeSensor**; Number Device: **1**.
      + Topic (Zigbee2mqtt): Dnomaid/stat/CC2531_1/**TuyaZigbeeSensor**_**1**/SensorClimate_1.	  	  

## Download
***
Currently, the application is released and available here:
[Google Play](https://play.google.com/store/apps/details?id=com.dnomaid.mqtt&gl=ES).

<a href='https://play.google.com/store/apps/details?id=com.dnomaid.mqtt&gl=ES'><img alt='Get it on Google Play' src='https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png' height="80"/></a>

## Installation
***
Watch the video on youtube.

[![Watch the video](https://img.youtube.com/vi/_CStETtgtKI/0.jpg)](https://www.youtube.com/watch?v=_CStETtgtKI)

## Why am I open-sourcing it?
***
1. I got help all around from open-source community, and it is time for me to help back.
2. A big problem of hobby projects is they lack continuity. I believe open sourcing the app could allow this app to be kept improving.
3. The list of IoT devices is very small and the code is ready to add more devices in the future.

## Links
***
From this link you can see a demo:
* https://www.youtube.com/watch?v=_CStETtgtKI


If you are interested, but do not know where to start, I leave this link where there are videos that can help you:
* https://www.youtube.com/playlist?list=PLAfoA6cZ1I72s6Jc_N0iGBRDbZ6ubbuAH

Eclipse Kura project:
* https://github.com/DnomaidGit/KuraIotMqtt
