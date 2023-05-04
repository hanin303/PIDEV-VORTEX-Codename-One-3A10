/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Reservation;
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

/**
 *
 * @author hanin
 */
public class ServiceReservation {
     public static ServiceReservation instance = null;
     public boolean resultOK;
     private ConnectionRequest req;
     ArrayList<Reservation> reservations;

    private ServiceReservation() {
        req = new ConnectionRequest();
    }

    public static ServiceReservation getInstance() {
        if (instance == null) {
            instance = new ServiceReservation();
        }
        return instance;
    }
     public ArrayList<Reservation> parseReservations(String jsonText) {
        try {
            reservations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reservationsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reservationsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reservation r = new Reservation();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId_reservation((int) id);
                r.setDate_reservation((String)obj.get("date_reservation"));
                r.setHeure_depart((String)obj.get("heure_depart"));
                r.setHeure_arrive((String)obj.get("heure_arrive"));
                r.setStatus((String)obj.get("status"));
                r.setType_ticket((String)obj.get("type_ticket"));
                r.setId_client((String)obj.get("id_client"));
                r.setId_moy((String)obj.get("id_moy"));
                r.setId_it((String)obj.get("id_it"));

               reservations.add(r);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return reservations;
    }

    public ArrayList<Reservation> getAllReservations() {
        String url = Statics.BASE_URL + "reservation/getAllReservation";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservations = parseReservations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservations;
    }
    
}
