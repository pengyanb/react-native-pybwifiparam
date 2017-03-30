'use strict';

var ReactNative = require('react-native');
var {
    NativeModules
} = ReactNative;

var PybWifiParam = {};

PybWifiParam.getWifiSSID = function(callback){
    NativeModules.PybWifiParam.getWifiSSID(callback);
};

module.exports = PybWifiParam;
