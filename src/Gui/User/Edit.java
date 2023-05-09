/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.User;

import Entity.User;
import Services.ServiceUser;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author MSI
 */
public class Edit extends Form{
    public Edit(User u,int id,Form previous){
        User user = ServiceUser.getService().getUser(id);
        setTitle("Mon profile");
        setLayout(BoxLayout.yCenter());
        Label lnom = new Label("nom:");
        TextField tnom = new TextField();
        tnom.setText(user.getNom());
        Label lprenom = new Label("Prénom:");
        TextField tprenom = new TextField();
        tprenom.setText(user.getPrenom());
        Label lusername = new Label("Username");
        TextField tusername = new TextField();
        tusername.setText(user.getUsername());
        Label lemail = new Label("Adresse E-mail:");
        TextField temail = new TextField();
        temail.setText(user.getEmail());
        Label lnum_tel = new Label("Numéro de téléphone:");
        TextField tnum_tel = new TextField();
        tnum_tel.setText(String.valueOf(user.getNum_tel()));
        tnum_tel.setConstraint(TextField.NUMERIC);
        Label lcin = new Label("Numéro de carte d'identité:");
        TextField tcin = new TextField();
        tcin.setText(String.valueOf(user.getCin()));
        tcin.setConstraint(TextField.NUMERIC);
        Button modif = new Button("Modifier");
        modif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                User user = new User(id,tnom.getText(),tprenom.getText(),tusername.getText(),temail.getText(),Integer.parseInt(tnum_tel.getText()),Integer.parseInt(tcin.getText()));
                if(ServiceUser.getInstance().modifUser(user)){
                    Dialog.show("Success", "utilisateur modifié avec succés", "OK", null);
                    UserHome refresh = new UserHome(u,previous);
                    refresh.show();
                }
    
            }
        });
        addAll(lnom,tnom,lprenom,tprenom,lusername,tusername,lemail,temail,lnum_tel,tnum_tel,lcin,tcin,modif);
    
    }
   

}

