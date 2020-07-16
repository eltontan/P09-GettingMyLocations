package sg.edu.rp.c347.id18016094.p09_gettingmylocations;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;

import java.io.File;

public class MyService extends Service {

    boolean started;
    private FusedLocationProviderClient client;
    private LocationCallback mLocationCallBack;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public void onCreate() {
        Log.d("Service", "Service created");
        super.onCreate();

        client = LocationServices.getFusedLocationProviderClient(this);

        String folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/P09";
        File folder = new File(folderLocation);
        if (folder.exists() == false) {
            boolean result = folder.mkdir();
            if (result == false) {
                Toast.makeText(MyService.this, "Folder can't be created is External memory", Toast.LENGTH_SHORT).show();
                stopSelf();
            }
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (started == false){
            started = true;
            Log.d("Service", "Service started");
        } else {
            Log.d("Service", "Service is still running");
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("Service", "Service exited");
        super.onDestroy();
    }

}
