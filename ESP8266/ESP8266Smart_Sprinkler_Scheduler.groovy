/**
*  
* 
*  Irrigation Scheduler SmartApp Smarter Lawn Contoller
*  Compatible with up to 24 Zones
*
*  Author: Stan Dotson (stan@dotson.info) and Matthew Nichols (matt@nichols.name)
*  Date: 2014-06-16
*
*  Copyright 2014 Stan Dotson and Matthew Nichols
*
*  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
*  in compliance with the License. You may obtain a copy of the License at:
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
*  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
*  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
*  for the specific language governing permissions and limitations under the License.
*
*
**/

definition(
    name: "Irrigation Scheduler v3.0.5",
    namespace: "d8adrvn/smart_sprinkler",
    author: "matt@nichols.name and stan@dotson.info",
    description: "Schedule sprinklers to run unless there is rain.",
    version: "3.0.5",
    iconUrl: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture.png",
    iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Meta/water_moisture@2x.png"
)

preferences {
	page(name: "schedulePage", title: "Create An Irrigation Schedule", nextPage: "sprinklerPage", uninstall: true) {
        
        section("Preferences") {
        	label name: "title", title: "Name this irrigation schedule...", required: false, multiple: false, defaultValue: "Irrigation Scheduler"
            input "isScheduleEnabled", "bool", title: "Enable Schedule", description: "Do You Want To Enable This Schedule?", defaultValue: true, required: false
        	input "sendPushMessageIrrigationStart", "capability.notification", title: "Send Notification When Irrigation Starts?", multiple: true, required: false
        	input "isRainGuageNotificationEnabled", "bool", title: "Send Notification With Rain Guage Report", description: "Do You Want To Receive Notifications?", defaultValue: false, required: false
        }
           
        section {
            input (
            name: "wateringDays",
            type: "enum",
            title: "Water on which days?",
            required: false,
            multiple: true, // This must be changed to false for development (known ST IDE bug)
            options: ["Monday" : "Monday", "Tuesday" : "Tuesday", "Wednesday" : "Wednesday", "Thursday" : "Thursday", "Friday" : "Friday", "Saturday" : "Saturday", "Sunday" : "Sunday"])
        }

        section("Minimum interval between waterings...") {
            input "days", "number", title: "Days?", description: "minimum # days between watering", defaultValue: "0", required: false
        }

        section("Start watering at what times...") {
            input name: "waterTimeOne",  type: "time", required: true, title: "Turn them on at..."
            input name: "waterTimeTwo",  type: "time", required: false, title: "and again at..."
            input name: "waterTimeThree",  type: "time", required: false, title: "and again at..."
        }
    }

    page(name: "sprinklerPage", title: "Sprinkler Controller Setup", nextPage: "weatherPage", uninstall: true) {
        section("Select your sprinkler controller...") {
            input "switches", "capability.switch", multiple: false, required: true
        }

        section("Zone Times...") {
            input "zone1", "string", title: "Zone 1 Time", description: "minutes", multiple: false, required: false
            input "zone2", "string", title: "Zone 2 Time", description: "minutes", multiple: false, required: false
            input "zone3", "string", title: "Zone 3 Time", description: "minutes", multiple: false, required: false
            input "zone4", "string", title: "Zone 4 Time", description: "minutes", multiple: false, required: false
            input "zone5", "string", title: "Zone 5 Time", description: "minutes", multiple: false, required: false
            input "zone6", "string", title: "Zone 6 Time", description: "minutes", multiple: false, required: false
            input "zone7", "string", title: "Zone 7 Time", description: "minutes", multiple: false, required: false
            input "zone8", "string", title: "Zone 8 Time", description: "minutes", multiple: false, required: false
            input "zone9", "string", title: "Zone 9 Time", description: "minutes", multiple: false, required: false
            input "zone10", "string", title: "Zone 10 Time", description: "minutes", multiple: false, required: false
            input "zone11", "string", title: "Zone 11 Time", description: "minutes", multiple: false, required: false
            input "zone12", "string", title: "Zone 12 Time", description: "minutes", multiple: false, required: false
            input "zone13", "string", title: "Zone 13 Time", description: "minutes", multiple: false, required: false
            input "zone14", "string", title: "Zone 14 Time", description: "minutes", multiple: false, required: false
            input "zone15", "string", title: "Zone 15 Time", description: "minutes", multiple: false, required: false
            input "zone16", "string", title: "Zone 16 Time", description: "minutes", multiple: false, required: false
            input "zone17", "string", title: "Zone 17 Time", description: "minutes", multiple: false, required: false
            input "zone18", "string", title: "Zone 18 Time", description: "minutes", multiple: false, required: false
            input "zone19", "string", title: "Zone 19 Time", description: "minutes", multiple: false, required: false
            input "zone20", "string", title: "Zone 20 Time", description: "minutes", multiple: false, required: false
            input "zone21", "string", title: "Zone 21 Time", description: "minutes", multiple: false, required: false
            input "zone22", "string", title: "Zone 22 Time", description: "minutes", multiple: false, required: false
            input "zone23", "string", title: "Zone 23 Time", description: "minutes", multiple: false, required: false
            input "zone24", "string", title: "Zone 24 Time", description: "minutes", multiple: false, required: false
        }

 	}
    
	page(name: "weatherPage", title: "AccuWeather Virtual Weather Station Setup", install: true) {
		
        section("AccuWeather API Key...") {
            input "accuWeatherApiKey", "text", title: "Enter your AccuWeather API key", defaultValue: "uxXZcGX1x9zxA7FUnSaM2vmezbHj3kph", required: false
        }
        
        section("AccuWeather Location Key") {
            input "accuWeatherLocationKey", "text", title: "Enter the location key for retrieving weather", defaultValue: "47173" ,required: false
        }
        
        section("Select which rain to add to your virtual rain guage...") {
        	input "isYesterdaysRainEnabled", "bool", title: "Yesterday's Rain", description: "Include?", defaultValue: true, required: false
        	input "isTodaysRainEnabled", "bool", title: "Today's Rain", description: "Include?", defaultValue: true, required: false
        	input "isForecastRainEnabled", "bool", title: "Tomorrow's Forecasted Rain", description: "Include?", defaultValue: false, required: false
            
        }
        
        section("Notification") {
            input "sendPushMessageSkippedWatering", "capability.notification", title: "Send Notification When Irrigation is skipped?", multiple: true, required: false
        }
       
       	section("Skip watering if virutal rain guage totals more than... (default 1)") {
            input "wetThreshold", "decimal", title: "mm?", defaultValue: "1", required: false
        }
        
        section("Run watering only if forecasted high temp (C) is greater than... (default 12)") {
            input "tempThreshold", "decimal", title: "Temp?", defaultValue: "12", required: false
        }
        
    }
}		

def installed() {
    scheduling()
    state.daysSinceLastWatering = [0,0,0]
}

def updated() {
    unschedule()
    scheduling()
    state.daysSinceLastWatering = [0,0,0]
}

// Scheduling
def scheduling() {
    schedule(waterTimeOne, "waterTimeOneStart")
    if (waterTimeTwo) {
        schedule(waterTimeTwo, "waterTimeTwoStart")
    }
    if (waterTimeThree) {
        schedule(waterTimeThree, "waterTimeThreeStart")
    }
}

def waterTimeOneStart() {
    state.currentTimerIx = 0
    scheduleCheck()
}
def waterTimeTwoStart() {
    state.currentTimerIx = 1
    scheduleCheck()
}
def waterTimeThreeStart() {
    state.currentTimerIx = 2
    scheduleCheck()
}

def scheduleCheck() {
	if(!isScheduleEnabled){
    	log.info "Schedule is not enabled"
        return
    }
    
    def schedulerState = switches?.latestValue("effect")?.toString() ?:"[noEffect]"
    log.info "Running Irrigation Schedule: ${app.label}"

    if (schedulerState == "onHold") {
        log.info("${app.label} sprinkler schedule on hold.")
        return
    } 
	if (schedulerState == "skip") { 
    	// delay this watering and reset device.effect to noEffect
        schedulerState = "delay" 
        for(s in switches) {
            if("noEffect" in s.supportedCommands.collect { it.name }) {
                s.noEffect()
                log.info ("${app.label} skipped one watering and will resume normal operations at next scheduled time")
            }
        }
 	}  
    /*
	if (schedulerState != "expedite") { 
    	// Change to delay if wet or too cold
        schedulerState = isWeatherDelay() ? "delay" : schedulerState
 	}
    if (schedulerState != "delay") {
        state.daysSinceLastWatering[state.currentTimerIx] = daysSince() + 1
    }
    */
// 	  Next line is useful log statement for debugging why the smart app may not be triggering.
    log.info("${app.label} scheduler state: ${schedulerState}. Days since last watering: ${daysSince()}. Is watering day? ${isWateringDay()}. Enought time? ${enoughTimeElapsed()} ")
	
    if (schedulerState == "expedite") {
    	water()
        return
    }
    
    if (isWateringDay() && enoughTimeElapsed() && schedulerState != "delay" && !isWeatherDelay()) {
    	water()
    } else {
    	state.daysSinceLastWatering[state.currentTimerIx] = daysSince() + 1
    }
}

def isWateringDay() {
    if(!wateringDays) return true

    def today = new Date().format("EEEE", location.timeZone)
    if (wateringDays.contains(today)) {
        return true
    }
    log.info "${app.label} watering is not scheduled for today"
    return false
}

def enoughTimeElapsed() {
    if(!days) return true
    return (daysSince() >= days)
}

def daysSince() {
    if(!state.daysSinceLastWatering) state.daysSinceLastWatering = [0,0,0]
    state.daysSinceLastWatering[state.currentTimerIx] ?: 0
}

def isWeatherDelay() { 
	log.info "${app.label} Is Checking The Weather"
		
        def currCond = getCurrentConditions()
    	def forecast = getForecast()

        def rainGauge = 0f
        def todaysmm = 0f
	    def yesterdaysmm = 0f
        def forecastmm = 0f
        
        if (isYesterdaysRainEnabled) {        
            yesterdaysmm = currCond.data[0].PrecipitationSummary.Past24Hours.Metric.Value
            rainGauge = rainGauge += yesterdaysmm
        }

        if (isTodaysRainEnabled) {
            todaysmm = forecast.DailyForecasts[0].Day.Rain.Value
			todaysmm += forecast.DailyForecasts[0].Night.Rain.Value
            rainGauge += todaysmm
        }

        if (isForecastRainEnabled) {
            forecastmm = forecast.DailyForecasts[1].Day.Rain.Value
            forecastmm += forecast.DailyForecasts[1].Night.Rain.Value
            rainGauge += forecastmm
        }
      	if (isRainGuageNotificationEnabled && sendPushMessageIrrigationStart != null) {
           	sendPushMessageIrrigationStart.deviceNotification("Virtual rain gauge requested for \"${app.label}\" reads ${rainGauge.round(2)} mm.\nToday's Rain: ${todaysmm} mm, \nYesterday's Rain: ${yesterdaysmm} mm, \nForecast Rain: ${forecastmm} mm")
         }
        
        log.info ("Virtual rain gauge reads ${rainGauge} mm")
        
 //     check to see if virtual rainguage exceeds threshold
        if (rainGauge > (wetThreshold?.toFloat() ?: 0.5)) {
            if(sendPushMessageSkippedWatering != null) {
                sendPushMessageSkippedWatering.deviceNotification("Skipping watering for \"${app.label}\" due to precipitation.")
            }
            log.info "${app.label} skipping watering today due to precipitation."
            for(s in switches) {
                if("rainDelayed" in s.supportedCommands.collect { it.name }) {
                    s.rainDelayed()
                    log.info "Watering is rain delayed for $s"
                }
            }
            return true
        }
        def maxThermometer = forecast.DailyForecasts[0].Temperature.Maximum.Value
        if (maxThermometer < (tempThreshold ?: 0)) {
            if(sendPushMessageSkippedWatering != null) {
                sendPushMessageSkippedWatering.deviceNotification("Skipping watering for \"${app.label}\": $maxThermometer forecast high temp is below threshold temp.")
            }
            log.info "${app.label} is skipping watering: temp is below threshold temp."
            return true
		} else {
        	log.info "Not skipping watering due to temperature."
        }
     //}
    log.debug ("Returning from isWeatherDelay...")
    return false
}

def safeToFloat(value) {
	//log.debug ("Entering safeToFloat()")
    //log.debug ("safeToFloat() value = $value")
    try {
        if(value) {
            log.debug ("safeToFloat() returning value.toFloat() : $value.toFloat()")
            return value.toFloat()
        } else {
            log.debug ("safeToFloat() in else, returning 0.0")
            return 0.0
        }
    } catch (e) {
    	log.debug("Exception occurred: $e")
        return 0.0
    }
}



//send watering times over to the device handler
def water(wateringAttempts = 1) {
	log.info ("Starting Irrigation Schedule: ${app.label}")
    state.daysSinceLastWatering[state.currentTimerIx] = 0
    //def wateringAttempts = 1
    
    log.debug ("isNotificationEnabled: ${isNotificationEnabled}")
    
    if(sendPushMessageIrrigationStart != null && wateringAttempts == 1) {
        sendPushMessageIrrigationStart.deviceNotification("Starting Irrigation: ${app.label}" ?: "null pointer on app name")
    }

    if(anyZoneTimes()) {
        def zoneTimes = []
        for(int z = 1; z <= 24; z++) {
            def zoneTime = settings["zone${z}"]
            if(zoneTime) {
            	zoneTimes += "${z}:${zoneTime}"
            	log.info("Zone ${z} on for ${zoneTime} minutes")
           	}
    	}
        log.debug "zoneTimes: ${zoneTimes}"
        switches.OnWithZoneTimes(zoneTimes.join(","))
    } 
    else {
        switches.on()
    }
    log.debug "switches: ${switches}"
    log.debug "switches.currentSwitch: ${switches.currentSwitch}"
	if (wateringAttempts <2) {
        // developers note: runIn() appears to only call void methods
        runIn(20, isWateringCheckOnce)
	}
	else {
        runIn(20, isWateringCheckTwice)
	}
}

def isWateringCheckOnce() { 
    def switchCurrentState = switches.currentSwitch    
    log.debug "switches: ${switches}"
	if (switchCurrentState != "on") {
		log.info "${app.label} is not 'on'. Trying watering again..."
		// try to start watering again
		water(2)
	}
}		

def isWateringCheckTwice() { 
    def switchCurrentState = switches.currentSwitch    
	if (switchCurrentState != "on") {
		switches.warning()
        if(sendPushMessageIrrigationStart != null) {
            sendPushMessageIrrigationStart.deviceNotification("${app.label} did not start after two attempts.  Check system")
            //sendPushover("${app.label} did not start after two attempts.  Check system","")
        }
        log.info "WARNING: ${app.label} failed to water. Check your system"
	}  
}		

def anyZoneTimes() {
    return zone1 || zone2 || zone3 || zone4 || zone5 || zone6 || zone7 || zone8 || zone9 || zone10 || zone11 || zone12 || zone13 || zone14 || zone15 || zone16 || zone17 || zone18 || zone19 || zone20 || zone21 || zone22 || zone23 || zone24
}

def getCurrentConditions() {
	def uri = "http://dataservice.accuweather.com/currentconditions/v1/${accuWeatherLocationKey}?apikey=${accuWeatherApiKey}&details=true"
    def params = [
        uri:  uri,
        contentType: 'application/json'
    ]
    try {
        httpGet(params) {resp ->
            //log.debug "resp data: ${resp.data[0].PrecipitationSummary.Past24Hours.Metric.Value}"
            return resp
            //log.debug "humidity: ${resp.data.main.humidity}"
        }
    } catch (e) {
        log.error "error: $e"
    }
}

def getForecast() {
	if(!accuWeatherApiKey || accuWeatherApiKey.length() == 0){
    	log.warn "AccuWeatherAPIKey is not set. Cannot get forecast"
        return null
    }
    
	def uri = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/${accuWeatherLocationKey}?apikey=${accuWeatherApiKey}&details=true&metric=true"
    def params2 = [
        uri:  uri,
        contentType: 'application/json'
    ]
    try {
        httpGet(params2) {respForecast ->
            //log.debug "resp data: ${resp2.data}"
            return respForecast.data
            //log.debug "humidity: ${resp.data.main.humidity}"
        }
    } catch (e) {
        log.error "error: $e"
    }
}