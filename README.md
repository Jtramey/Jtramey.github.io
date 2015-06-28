YikYak Automation
===========

Sample mobile application for automation testing

###Dependencies###
Appium java bindings [here](https://github.com/appium/java-client/)

Node.js [here](https://nodejs.org/)

Android SDK found [here](https://developer.android.com/sdk/index.html)

###Structure###

All of the main tests are under src/main/java. These are being run in Junit in order to later file them into a neat XML file that shows pass/fail conditions. I also plan on parsing the XML into HTML to show on an internal webpage.

This is a maven project so you will need to import it as one in order to automatically download all required packages. 

###Usage###

#####Start Appium Server######
```
cd {Appium installation directory}/node_modules/appium/bin
```

```
node appium.js
```

This will start your appium server on localhost. 

You may now run the junit test from inside an IDE. 


###Contact Information###

jonathon627@gmail.com

[Linkedin](https://www.linkedin.com/in/jonathonramey)