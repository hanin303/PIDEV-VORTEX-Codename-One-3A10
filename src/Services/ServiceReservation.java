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
import java.util.Date;
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
                //r.setDate_reservation((Date)obj.get("date_reservation"));
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
    
        public void AjouterReservation(Reservation r){
        String url = Statics.BASE_URL+"reservation/addReservationJSON/new?date_reservation="+r.getDate_reservation()+
                "&heure_depart=" + r.getHeure_depart()+ "&heure_arrive=" + r.getHeure_arrive()+ "&type_ticket=" + r.getType_ticket()
                + "&status=" + r.getStatus()+ "&id_client=" + r.getId_client()+ "&id_it=" + r.getId_it()+ "&id_moy=" + r.getId_moy();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req); 
    }
    
    public boolean suppReservation(Reservation r)
    {

        String url = Statics.BASE_URL + "reservation/deleteReservationJSON/" +r.getId_reservation();
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
    
 public void modifierReservation(int id_reservation, Date date_reservation , String heure_depart , String heure_arrive , String status,String type_ticket,String id_client,String id_moy,String id_it) {
    String url = Statics.BASE_URL + "reservation/updateReservationJSON/"+id_reservation+"?date_reservation="+date_reservation
            +"&heure_depart="+heure_depart+"&heure_arrive="+heure_arrive+"&status="+status+"&type_ticket="+type_ticket+"&id_client="+id_client 
             +"&id_moy="+id_moy+"&id_it="+id_it;
    
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
