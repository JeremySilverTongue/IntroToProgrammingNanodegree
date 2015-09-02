/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.silver.quicktips.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

import java.util.ArrayList;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "quickTips",
        version = "v1"
)
public class MyEndpoint {

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "addDebugTip")
    public void addDebugTip() {
        QuickTip.addDebugTip();
    }

    @ApiMethod(name = "addTip")
    public void addTip(@Named("title") String title, @Named("content") String content){
        QuickTip.StoreNew(title, content);
    }

    @ApiMethod(name = "getTips")
    public ArrayList<QuickTip> getTips(){
        return QuickTip.loadAll();
    }

    @ApiMethod(name = "addLesson5Notes")
    public void addLesson5Notes(){
        QuickTip.addLesson5Notes();
    }

    @ApiMethod(name = "clearTips")
    public void clearTips(){
        QuickTip.deleteAll();
    }

}
