/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hanin
 */
public class ServiceUser {
    public static ServiceUser instance = null;
    public ConnectionRequest request;
    private static ServiceUser service;
    private boolean resultOK;
    private ArrayList<User> users;

    private ServiceUser() {
        request = new ConnectionRequest();
    }

    public static ServiceUser getService() {
        if(service == null){
            service = new ServiceUser();
        }
        return service;
    }
    
    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
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
                JSONParser jsonParser;
                jsonParser = new JSONParser();
             resultOK = request.getResponseCode()==200;
            request.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return resultOK;
    }
    private User parseUser(String jsonText){
        User user= new User();

        return user;
    }
    public ArrayList<User> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String,Object>mapUsers = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapUsers.get("root");
            System.out.println("jsontxt"+jsonText.toString());
            for (Map<String, Object> obj : listOfMaps){
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                float cin = Float.parseFloat(obj.get("CIN").toString());
                float num_tel = Float.parseFloat(obj.get("num_tel").toString());
                u.setId_user((int)id);
                u.setUsername(obj.get("username").toString());
                System.out.println("iduser "+u.getId_user());
                u.setCin((int)cin);
                u.setNum_tel((int)num_tel);
                if(obj.get("image")!=null){
                    u.setImage(obj.get("image").toString());
                }
                else {
                    System.out.println("image null");
                }
                u.setMdp(obj.get("password").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setNom(obj.get("nom").toString());
                users.add(u);
            }


        } catch (IOException exp){
            System.out.println("ioexp"+exp.getMessage());
        }
        return users;
    }
    public ArrayList<User> getAllUsers(){
        String url= Statics.BASE_URL+"api/showAll";
        request.setPost(true);
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                System.out.println("resp "+new String(request.getResponseData()));
                users = parseUsers(new String(request.getResponseData()));
                System.out.println("users"+users.toString());
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return users;
    }

}
