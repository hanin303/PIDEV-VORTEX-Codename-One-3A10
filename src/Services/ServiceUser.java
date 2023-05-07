/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import Utils.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author hanin
 */
public class ServiceUser {
    public ConnectionRequest request;
    private static ServiceUser service;
    private boolean resultOK;

    private ServiceUser() {
        request = new ConnectionRequest();
    }

    public static ServiceUser getService() {
        if(service == null){
            service = new ServiceUser();
        }
        return service;
    }
    public boolean addUser(User u){
        String url= Statics.BASE_URL+"api/add";
        request.addArgument("nom", u.getNom());
        request.addArgument("prenom", u.getPrenom());
        request.addArgument("username", u.getUsername());
        request.addArgument("email", u.getEmail());
        request.addArgument("mdp", u.getMdp());
        request.addArgument("num_tel", String.valueOf(u.getNum_tel()));
        request.addArgument("cin", String.valueOf(u.getCin()));
                request.setUrl(url);


        request.setPost(true);
        request.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
             resultOK = request.getResponseCode()==200;
            request.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }
    
            
}
