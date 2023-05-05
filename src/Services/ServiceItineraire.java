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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kalee
 */
public class ServiceItineraire {
    public static ServiceItineraire instance = null;
    
    private ConnectionRequest req;
    
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
    
    public ArrayList<Iteneraire> AffichageItineraire(){
        ArrayList<Iteneraire> result = new ArrayList<>();
        req.setUrl(Statics.BASE_URL+"/displayitineraire");
        req.addResponseListener(new ActionListener<NetworkEvent>(){
        @Override 
        public void actionPerformed (NetworkEvent evt){
            JSONParser jscnp;
            jscnp = new JSONParser();
            try{
            Map<String,Object> mapItineraires = jscnp.parseJSON
        (new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String,Object>> ListOfMaps =(List<Map<String,Object>> )mapItineraires.get("root");
            for (Map<String,Object> obj : ListOfMaps){
            Iteneraire i = new Iteneraire();
            float id = Float.parseFloat(obj.get("id").toString());
            String pts_depart = obj.get("pts_depart").toString();
            String pts_arrive = obj.get("pts_arrive").toString();
            i.setId((int)id);
            i.setPts_depart(pts_depart);
            i.setPts_arrive(pts_arrive);
            result.add(i);}
            } 
            
            catch(Exception ex){
        ex.printStackTrace();}
        }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
        
    }
    
}
