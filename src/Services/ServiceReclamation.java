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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import Entity.Reclamation;
import Utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Date;
/**
 *
 * @author amens
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> reclamation;

    public static ServiceReclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
    

    public boolean ajouterReclamation(Reclamation r) {

        String url = Statics.BASE_URL + "addreclamationmobile";

        req.setUrl(url);
        req.setPost(false);
        req.addArgument("message_rec", r.getMessage_rec());
        req.addArgument("objet", r.getObjet());
        req.addArgument("statut", r.getStatut());
        req.addArgument("date_rec", r.getDate_rec().toString());
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //String formattedDate = dateFormat.format(r.getDate_rec());
        //req.addArgument("date_rec", formattedDate);

        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
        
        
 
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Reclamation> getAllReclamation() {
        ArrayList<Reclamation> result = new ArrayList<>();

        String url = Statics.BASE_URL + "displayreclamation";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp = new JSONParser();
                try {
                    Map<String, Object> mapRecl = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapRecl.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        Reclamation r = new Reclamation();
                        float id = Float.parseFloat(obj.get("id_reclamation").toString());
                        r.setId_reclamation((int) id);
                        r.setObjet(obj.get("objet").toString());
                        r.setMessage_rec(obj.get("message_rec").toString());
                        r.setStatut(obj.get("statut").toString());
                        //r.setDate_rec(obj.get("date_rec").toString());
                        

                        result.add(r);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;

    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public boolean deletereclamation(int id) {
        String url = Statics.BASE_URL + "reclamationdelete/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
//
//    //Update 

  public boolean modifierReclamation(Reclamation r) {

    String url = Statics.BASE_URL + "reclamationmodify/" + r.getId_reclamation();

    req.setUrl(url);
    req.setPost(false);
    req.addArgument("message_rec", r.getMessage_rec());
    req.addArgument("objet", r.getObjet() + "");
    req.addArgument("statut", r.getStatut());
    req.addArgument("date_rec", r.getDate_rec().toString());
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
            req.removeResponseListener(this);
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
}


  
  
  public List<Integer> getAllReclamationIds() {
    List<Integer> result = new ArrayList<>();
    ArrayList<Reclamation> reclamations = getAllReclamation();
    for (Reclamation r : reclamations) {
        result.add(r.getId_reclamation());
    }
    return result;
}

  
  
}
