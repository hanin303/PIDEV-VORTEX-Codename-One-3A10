/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entity.Station;
import Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import org.json.JSONException;


/**
 *
 * @author hanin
 */
public class ServiceStation {
    
     public static ServiceStation instance = null;
   public boolean resultOK;

    
    private ConnectionRequest req;
         ArrayList<Station> stations;

    
    public static ServiceStation getInstance(){
        if (instance == null){
            instance = new ServiceStation();
        }
        return instance;
    }
    public ServiceStation(){
        req = new ConnectionRequest();
                
                
                
    }
    public void AjouterStation(Station s){
        String url = Statics.BASE_URL+"Mobile/ajoutMobile?long_alt="
                +s.getLong_alt();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    public ArrayList<Station> parseStations(String jsonText) {
        try {
            stations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> stationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) stationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Station r = new Station();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId((int) id);
                r.setLong_alt((String)obj.get("longAlt"));
               

               stations.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return stations;
    }

    public ArrayList<Station> getAllStations() {
        String url = Statics.BASE_URL + "Mobile/afficheMobile";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                stations = parseStations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return stations;
    }
    
public boolean suppStation(Station t)
    {

         String url = Statics.BASE_URL + "Mobile/deleteMobile?id="+t.getId();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
             @Override
             public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);             }
         });
          NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;



    }
         
public void modifierStation(int id, String long_alt) {
    String url = Statics.BASE_URL + "Mobile/updateMobile?id=" + id + "&long_alt=" + long_alt;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setHttpMethod("PUT");
    req.addResponseListener((NetworkEvent evt) -> {
        if (req.getResponseCode() == 200) {
            JSONParser parser = new JSONParser();
            try {
                Map<String, Object> response = parser.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                System.out.println(response);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}

}
