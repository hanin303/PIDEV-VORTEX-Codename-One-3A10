/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Ligne;
import Utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/**
 *
 * @author hanin
 */
public class ServiceLigne {
     public static ServiceLigne instance = null;
     public boolean resultOK;
     private ConnectionRequest req;
     ArrayList<Ligne> ligne;

    private ServiceLigne() {
        req = new ConnectionRequest();
    }

    public static ServiceLigne getInstance() {
        if (instance == null) {
            instance = new ServiceLigne();
        }
        return instance;
    }
     public ArrayList<Ligne> parseReservations(String jsonText) {
        try {
            ligne = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reservationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reservationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Ligne r = new Ligne();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId_ligne((int) id);
                r.setNom_ligne((String)obj.get("nom_ligne"));
                r.setType_ligne((String)obj.get("type_ligne"));
               
               ligne.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return ligne;
    }

    public ArrayList<Ligne> getAllReservations() {
        String url = Statics.BASE_URL + "ligne/ligne/AllLignes";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ligne = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ligne;
    }
    
    public void AddLigne(Ligne l){
        String url = Statics.BASE_URL+"ligne/ligne/addLigneJSON/new?nom_ligne="+l.getNom_ligne()+"&type_ligne="+l.getType_ligne();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
    
    public boolean suppLigne(Ligne t)
    {

         String url = Statics.BASE_URL + "ligne/ligne/deleteEventJSON/"+t.getId_ligne();
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
    
    
    public void modifierLigne(int id, String nom_ligne, String type_ligne) {
    String url = Statics.BASE_URL + "ligne/ligne/updateEventJSON/" + id + "?&nom_ligne=" + nom_ligne+"&type_ligne="+type_ligne;
    ConnectionRequest req = new ConnectionRequest();
    req.setUrl(url);
    req.setHttpMethod("PUT");
    req.addResponseListener((NetworkEvent evt) -> {
        if (req.getResponseCode() == 200) {
            JSONParser parser = new JSONParser();
            try {
                Map<String, Object> response = parser.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                System.out.println(response);
            } catch (IOException | JSONException ex) {
                ex.printStackTrace();
            }
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
}

   
    
}
