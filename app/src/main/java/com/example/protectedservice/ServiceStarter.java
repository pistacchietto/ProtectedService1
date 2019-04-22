package com.example.protectedservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import static com.firebase.jobdispatcher.Lifetime.FOREVER;

public class ServiceStarter extends BroadcastReceiver {	
	@Override
	public void onReceive(Context context, Intent intent) {
        Intent serviceLauncher = new Intent(context, BackgroundService.class);
        //serviceLauncher.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {

            //serviceLauncher.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //Intent i = new Intent(context, Payload.class);
            //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //context.startActivity(i);
        //}
        context.startService(serviceLauncher);
 /*           Bundle myExtrasBundle = new Bundle();

            String android_id = Settings.Secure.getString(context.getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            myExtrasBundle.putString("mpath", context.getApplicationInfo().dataDir);
            myExtrasBundle.putString("mandroid_id", android_id);
            FirebaseJobDispatcher mDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(context));
            //FirebaseJobDispatcher mDispatcher = new FirebaseJobDispatcher(new AlarmManagerDriver(context));

            Job myJob = mDispatcher.newJobBuilder()
                    .setService(Payload.class)
                    .setTag("TagPaylo")
                    .setRecurring(true)
                    //.setTrigger(Trigger.executionWindow(5, 30))
                    .setTrigger(Trigger.executionWindow(0, 20))
                    .setLifetime(FOREVER)
                    //.setReplaceCurrent(false)
                    .setReplaceCurrent(true)
                    .setConstraints(Constraint.ON_ANY_NETWORK)
                    .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                    .setExtras(myExtrasBundle)
                    .build();
            mDispatcher.schedule(myJob);

        }
        else {
            Intent serviceLauncher = new Intent(context, BackgroundService.class);
            context.startService(serviceLauncher);
        }
*/    }

}
