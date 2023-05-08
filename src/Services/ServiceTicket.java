/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Ticket;
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
public class ServiceTicket {
    
     public static ServiceTicket instance = null;
     public boolean resultOK;
     private ConnectionRequest req;
     ArrayList<Ticket> tickets;

    private ServiceTicket() {
        req = new ConnectionRequest();
    }

    public static ServiceTicket getInstance() {
        if (instance == null) {
            instance = new ServiceTicket();
        }
        return instance;
    }
     public ArrayList<Ticket> parseTickets(String jsonText) {
        try {
            tickets = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ticketsListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ticketsListJson.get("root");
            for (Map<String, Object> obj : list) {
                Ticket t = new Ticket();
                float id = Float.parseFloat(obj.get("id").toString());
                t.setId_t((int) id);
                t.setNom_ticket((String)obj.get("nom_ticket"));
                t.setPrix((String)obj.get("prix"));
                t.setStatus((String)obj.get("status"));
                //t.setId_reservation((String)obj.get("id_reservation"));

                tickets.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return tickets;
    }

    public ArrayList<Ticket> getAllTickets() {
        String url = Statics.BASE_URL + "ticket/getAllTickets";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tickets =  parseTickets(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tickets;
    }
    
        public void AjouterTicket(Ticket t){
        String url = Statics.BASE_URL+"ticket/addTicketJSON?nom_ticket="+t.getNom_ticket()+
                "&prix=" + t.getPrix()+ "&status=" + t.getStatus();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req); 
    }
    
    public boolean suppTicket(Ticket t)
    {

        String url = Statics.BASE_URL + "ticket/deleteTicketJSON/" +t.getId_t();
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
    
 public void modifierTicket(int id_t, String nom_ticket , String prix , String status) {
 String url = Statics.BASE_URL + "ticket/updateTicketJSON/"+id_t+"?nom_ticket="+nom_ticket+"&prix="+prix+"&status="+status;
    
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
