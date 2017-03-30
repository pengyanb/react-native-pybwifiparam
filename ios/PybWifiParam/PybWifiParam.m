//
//  PybWifiParam.m
//  PybWifiParam
//
//  Created by Yanbing Peng on 29/03/17.
//  Copyright © 2017 Yanbing Peng. All rights reserved.
//

#import "PybWifiParam.h"

@implementation PybWifiParam

RCT_EXPORT_MODULE();

RCT_EXPORT_METHOD(getWifiSSID:(RCTResponseSenderBlock)callback){
    CFArrayRef supportInterfaces = CNCopySupportedInterfaces();
    CFDictionaryRef captiveNetworkDict = nil;
    if(supportInterfaces != nil){
        captiveNetworkDict = CNCopyCurrentNetworkInfo(CFArrayGetValueAtIndex(supportInterfaces, 0));
    }
    NSString *ssid = @"";
    if(captiveNetworkDict != nil){
        NSDictionary *dict = (__bridge NSDictionary *)captiveNetworkDict;
        ssid = [dict objectForKey:@"SSID"];
        if(ssid != NULL){
            callback(@[[NSNull null], ssid]);
        }
        else{
            callback(@[@"Error: unable to obtain SSID.", [NSNull null]]);
        }
    }
    else{
        callback(@[@"Error: unable to obtain SSID", [NSNull null]]);
    }
}
@end
