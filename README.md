# React Native Wifi Parameter
React Native Module can retrieve current Wifi SSID on ios and android

## Installation
```sh
npm install --save react-native-pybwifiparam

react-native link
```
Or:

### Install Manually (iOS)
* Drag node_modules/react-native-pybwifiparam/ios/PybWifiParam.xcodeproj to your Xcode project.
* Select main project Targets' Build Phase. expand "Link Binary With Libraries" section and add libPybWifiParam.a
* Select main project Targets' Build Settins, search for "Header Search Paths", add "$(SRCROOT)/../node_modules/react-native-pybwifiparam/ios" and to the search path and set it to "recursive".

### Install Manually (Android)

* Add to settings.gradle
```settings.gradle
include ':react-native-pybwifiparam'
project(':react-native-pybwifiparam').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-pybwifiparam/android')
```
* Add to android/app/build.gradle
```build.gradle
dependencies{
    ...
    compile project(':react-native-pybwifiparam')
    ...
}
```

* Add to MainApplication.java
```java
import com.pengyanb.pybwifiparam.PybWifiParamPackage;

@Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
            ...
            new PybWifiParamPackage(),
            ...
      );
    }
```

### Usage

```js
import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

import PybWifiParam from 'react-native-pybwifiparam';

export default class WifiParamTest extends Component {
    constructor(props){
        super(props);
        this.state={SSID:"UNKNOWN", error:""};
    }
    componentDidMount(){
        PybWifiParam.getWifiSSID((error, ssid)=>{
            if(error !== null){
                this.setState({error: error});
            }
            else{
                this.setState({SSID: ssid});
            }
        });
    }
  render() {
    return (
      <View style={styles.container}>
        <Text>{this.state.SSID}</Text>
        <Text>{this.state.error}</Text>
      </View>
    );
  }
}
```
