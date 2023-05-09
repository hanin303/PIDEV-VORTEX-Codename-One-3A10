/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.User;
import Entity.Role;
import Utils.Statics;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;

import java.io.IOException;
import java.util.ArrayList;
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
    private ArrayList<Role> roles;

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
    public boolean deleteUser(int id){
        String url= Statics.BASE_URL+"api/delete?id="+id;
        //request.addArgument("id",String.valueOf(id));
        request.setUrl(url);
        request.setPost(true);
        request.setCheckSSLCertificates(false);

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
    public boolean modifUser(User u){
        String url= Statics.BASE_URL+"api/edit?id="+String.valueOf(u.getId_user())+"&nom="+u.getNom()+"&prenom="+u.getPrenom()+"&username="+u.getUsername()+"&email="+u.getEmail()+"&num_tel="+u.getNum_tel()+"&cin="+u.getCin();
        /*request.addArgument("id",String.valueOf(u.getId_user()));
        request.addArgument("nom", u.getNom());
        request.addArgument("prenom", u.getPrenom());
        request.addArgument("username", u.getUsername());
        request.addArgument("email", u.getEmail());
        request.addArgument("mdp", u.getMdp());
        request.addArgument("num_tel", String.valueOf(u.getNum_tel()));
        request.addArgument("cin", String.valueOf(u.getCin()));*/
        request.setUrl(url);


        request.setPost(true);
        request.setCheckSSLCertificates(false);
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
    
    public boolean modifPass(int id,String mdp){
        String url= Statics.BASE_URL+"api/change?id="+id+"&mdp="+mdp;
        request.setUrl(url);
        request.setPost(true);
        request.setCheckSSLCertificates(false);
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
    public User login(String username,String password){
        String url= Statics.BASE_URL+"api/login";
        request.setContentType(" application/json");
        request.setPost(true);
        String jsonBody = "{\"username\":\""+username+"\",\"password\":\""+password+"\"}";
        request.setRequestBody(jsonBody);
        request.setUrl(url);
        request.setCheckSSLCertificates(false);

        final User[] user = new User[1];
        request.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonParser;
                jsonParser = new JSONParser();
                resultOK = request.getResponseCode()==200; request.removeResponseCodeListener(this);
                System.out.println("respl "+new String(request.getResponseData()));
                user[0] = parseUser(new String(request.getResponseData()));
                System.out.println("usersl "+user[0]);
                request.removeResponseListener(this);

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return user[0];
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
        request.setCheckSSLCertificates(false);

//        request.setUseNativeHttp(true);
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
       // NetworkManager.getInstance().setDefaultProtocol("http");

        return resultOK;
    }
    public boolean addAdmin(User u){
        String url= Statics.BASE_URL+"api/addAdmin";
        request.addArgument("nom", u.getNom());
        request.addArgument("prenom", u.getPrenom());
        request.addArgument("username", u.getUsername());
        request.addArgument("email", u.getEmail());
        request.addArgument("mdp", u.getMdp());
        request.addArgument("num_tel", String.valueOf(u.getNum_tel()));
        request.addArgument("cin", String.valueOf(u.getCin()));
        request.addArgument("id_role", String.valueOf(u.getRole().getId_role()));
        request.setUrl(url);


        request.setPost(true);
        request.setCheckSSLCertificates(false);

//        request.setUseNativeHttp(true);
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
       // NetworkManager.getInstance().setDefaultProtocol("http");

        return resultOK;
    }
    private User parseUser(String jsonText){
        User u= new User();
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            float id = Float.parseFloat(obj.get("id").toString());
            float cin = Float.parseFloat(obj.get("CIN").toString());
            float num_tel = Float.parseFloat(obj.get("num_tel").toString());
            float id_role = Float.parseFloat(obj.get("id_role").toString());
            u.setId_role((int)id_role);
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
            u.setEmail(obj.get("email").toString());


        } catch (IOException exp){
            System.out.println("ioexp"+exp.getMessage());
        }
        return u;
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
                float id_role = Float.parseFloat(obj.get("id_role").toString());
                u.setId_role((int)id_role);
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
                u.setEmail(obj.get("email").toString());
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
        request.setCheckSSLCertificates(false);
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
    public ArrayList<Role> getAllRoles(){
        String url= Statics.BASE_URL+"api/showRole";
        request.setPost(true);
        request.setUrl(url);
        request.setCheckSSLCertificates(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
                System.out.println("resp "+new String(request.getResponseData()));
                roles = parseRoles(new String(request.getResponseData()));
                System.out.println("roles"+roles.toString());
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return roles;
    }
    public ArrayList<Role> parseRoles(String jsonText) {
        try {
            roles = new ArrayList<>();
            JSONParser j = new JSONParser();

            Map<String,Object>mapUsers = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapUsers.get("root");
            System.out.println("jsontxt"+jsonText.toString());
            for (Map<String, Object> obj : listOfMaps){
                Role r = new Role();
                float id = Float.parseFloat(obj.get("id").toString());
                r.setId_role((int)id);
                r.setNom(obj.get("nom").toString());
                
              
                roles.add(r);
            }


        } catch (IOException exp){
            System.out.println("ioexp"+exp.getMessage());
        }
        return roles;
    }
    public User getUser(int id){
        String url= Statics.BASE_URL+"api/show?id="+id;
        request.setPost(true);
        request.setUrl(url);
        request.setCheckSSLCertificates(false);
        final User[] user = new User[1];
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
              //  System.out.println("resp "+new String(request.getResponseData()));
                user[0] = parseUser(new String(request.getResponseData()));
               // System.out.println("users"+users.toString());
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return user[0];
    }
    public Role getRole(int id){
        String url= Statics.BASE_URL+"api/showR?id="+id;
        request.setPost(true);
        request.setUrl(url);
        request.setCheckSSLCertificates(false);
        final Role[] role = new Role[1];
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent networkEvent) {
              //  System.out.println("resp "+new String(request.getResponseData()));
                role[0] = parseRole(new String(request.getResponseData()));
               // System.out.println("users"+users.toString());
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return role[0];
    }
     private Role parseRole(String jsonText){
        Role r= new Role();
        try {
            JSONParser j = new JSONParser();
            Map<String,Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            float id = Float.parseFloat(obj.get("id").toString());
                        
            r.setId_role((int)id);
            r.setNom(obj.get("nom").toString());


        } catch (IOException exp){
            System.out.println("ioexp"+exp.getMessage());
        }
        return r;
    }

    public ArrayList<String> emailPass(String email) {

        String url= Statics.BASE_URL+"api/email";
        request.addArgument("email",email);
        ArrayList<String> code = new ArrayList<>();
        request.setUrl(url);
        request.setPost(true);
        request.setCheckSSLCertificates(false);
        request.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser j = new JSONParser();
                String jsonText =new String(request.getResponseData());
                try {
                    Map<String,Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
                    code.add(obj.get("code").toString());
                    code.add(obj.get("id").toString());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                request.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        System.out.println("code array string "+code);
        return code;
    }
}
