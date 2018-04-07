package com.example.christanismerilbanzouzi.bance_project.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.christanismerilbanzouzi.bance_project.Common.Common;
import com.example.christanismerilbanzouzi.bance_project.Droit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class GetDroit extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_GEDROIT = "com.example.christanismerilbanzouzi.bance_project.action.FOO";
    public  static  final  String EXTRA_TEXTE_DROIT ="TEST";
    String inputLine;
    public GetDroit() {
        super("GetDroit");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, GetDroit.class);
        intent.setAction(ACTION_GEDROIT);
        Log.i("START", "startActionGet-Droit: ");
        context.startService(intent);

    }
    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionGetDroit(Context context) {
        Intent intent = new Intent(context, GetDroit.class);
        intent.setAction(ACTION_GEDROIT);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("START", "startActionGet-Droit: dsfkhjjdsfjk ");
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GEDROIT.equals(action)) {
                handleActionGetDroit();
                System.out.println("12"+this.inputLine.toString());
                String textDroit= new String(this.inputLine.toString());
                System.out.println("1"+textDroit);
                Common.droits+=""+textDroit.toString();
            }
        }
    }

    private void handleActionGetDroit() {
        Log.i("Droit", "handleActionGetDroit: START");
        URL url = null;
        this.inputLine= new String("");
        try {

            System.out.println("Début try Connexion URL");
            URL urlChantier = new URL("http://banzouzicmeril.fr/FicherTexte/droit.txt");
            URLConnection yc = urlChantier.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String line = new String("");
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                inputLine +=""+ line.toString();
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(Droit.DROITS_UPDATE));
    }

}
