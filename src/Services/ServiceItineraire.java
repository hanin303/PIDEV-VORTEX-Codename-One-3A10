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
import Entity.Iteneraire;
import Utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/**
 *
 * @author kalee
 */
public class ServiceItineraire {
    public static ServiceItineraire instance = null;
    
    private ConnectionRequest req;
    private Iteneraire itn;
    public boolean resultOK;
    ArrayList<Iteneraire> iteneraires;
    public static ServiceItineraire getInstance(){
        if (instance == null){
            instance = new ServiceItineraire();
        }
        return instance;
    }
    public ServiceItineraire(){
        req = new ConnectionRequest();
                
                
                
    }
    public void AjouterItineraire(Iteneraire it){
        String url = Statics.BASE_URL+"addItineraire?pts_depart="
                +it.getPts_depart()+"&pts_arrive="+it.getPts_arrive();
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data=="+str);});
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
  public ArrayList<Iteneraire> parseItineraire(String jsonText) {
        try {
            iteneraires = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> IteneraireListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("looc"+IteneraireListJson);
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) IteneraireListJson.get("root");
            for (Map<String, Object> obj : list) {
                Iteneraire i = new Iteneraire();
                float id = Float.parseFloat(obj.get("id").toString());
                i.setId((int) id);
                
                i.setPts_depart((String)obj.get("pts_depart"));
                i.setPts_arrive((String)obj.get("pts_arrive"));
                
               iteneraires.add(i);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(iteneraires);
        return iteneraires;
    }

  public ArrayList<Iteneraire> getAllIteneraires() {
        String url = Statics.BASE_URL + "displayitineraire";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                iteneraires = parseItineraire(new String(req.getResponseData()));
                
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return iteneraires;
    }
     public Iteneraire deleteIteneraire(Iteneraire it) {

        String url = Statics.BASE_URL + "deleteIteneraire?id="+it.getId()  ;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
//                itn = parseItenerairebyid(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });


        NetworkManager.getInstance().addToQueueAndWait(req);


        return itn;


    }
//     public Iteneraire parseItenerairebyid(String jsonText) {
//        try {
//
//            itn = new Iteneraire();
//
//            JSONParser j = new JSONParser();
//
//            Map<String, Object> ItineraireListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//
//
//                float id = Float.parseFloat(ItineraireListJson.get("id").toString());
//
//                itn.setId((int) id);
//
//
//
//                   itn.setPts_depart(ItineraireListJson.get("ptsDepart").toString())        ;
//                   itn.setPts_arrive(ItineraireListJson.get("ptsArrive").toString());
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//        } catch (IOException ex) {
//                 System.out.println("errrrrrrrrr");
//        }
//
//
//
//        return itn ;
//    }
//        
//        
//    }
//  
     public boolean suppItenraire(Iteneraire i)
    {

         String url = Statics.BASE_URL + "deleteIteneraire?id="+i.getId();
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
//     public boolean uppIteneraire(Iteneraire t) {
//
//
//        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
//        String url = Statics.BASE_URL + "updateIteneraire?id="+t.getId()
//                +"&pts_depart="+t.getPts_depart()+"&pts_arrive="+t.getPts_arrive();
//
//        req.setUrl(url);
//        req.setPost(false);
//
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
//                req.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(req);
//        return resultOK;
//    }
     
//     public Iteneraire getItenraire(int id) {
//
//        String url = Statics.BASE_URL + "deleteIteneraire?id="+ id  ;
//        req.setUrl(url);
//        req.setPost(false);
//        req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                itn = parseItenerairebyid(new String(req.getResponseData()));
//
//                req.removeResponseListener(this);
//            }
//        });
//
//
//        NetworkManager.getInstance().addToQueueAndWait(req);
//
//
//        return itn;
//
//
//    }
//     public Iteneraire parseItenerairebyid(String jsonText) {
//        try {
//
//            itn = new Iteneraire();
//
//            JSONParser j = new JSONParser();
//
//            Map<String, Object> IteneraireListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
//
//
//
//                float id = Float.parseFloat(IteneraireListJson.get("id").toString());
//
//                itn.setId((int) id);
//
//
//                   itn.setPts_depart(IteneraireListJson.get("ptsDepart").toString());
//                   itn.setPts_arrive(IteneraireListJson.get("ptsArrve").toString());
//               
//
//
//
//
//
//
//
//
//
//
//
//
//
//        } catch (IOException ex) {
//                 System.out.println("errrrrrrrrr");
//        }
//
//
//
//        return itn ;
//    }
     
     public void modifierItineraire(int id, String ptsd, String ptsa) {
    String url = Statics.BASE_URL + "updateIteneraire?id="+id+"&pts_depart="
            +ptsd+"&pts_arrive="+ptsa;
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



   
    

