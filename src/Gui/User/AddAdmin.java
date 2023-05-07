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
public class AddAdmin extends Form{
    public AddAdmin(){
        setTitle("Ajouter un utilisateur");
        setLayout(BoxLayout.yCenter());
        Label lnom = new Label("nom:");
        TextField tnom = new TextField();
        Label lprenom = new Label("Prénom:");
        TextField tprenom = new TextField();
        Label lusername = new Label("Username");
        TextField tusername = new TextField();
        Label lemail = new Label("Adresse E-mail:");
        TextField temail = new TextField();
        Label lmdp = new Label("Mot de passe:");
        TextField tmdp = new TextField();
        tmdp.setConstraint(TextField.PASSWORD);
        Label lnum_tel = new Label("Numéro de téléphone:");
        TextField tnum_tel = new TextField();
        tnum_tel.setConstraint(TextField.PHONENUMBER);
        Label lcin = new Label("Numéro de carte d'identité:");
        TextField tcin = new TextField();
        tcin.setConstraint(TextField.NUMERIC);
        Button addU = new Button("Ajouter");
        addU.addActionListener((evt) -> {
        });
        addAll(lnom,tnom,lprenom,tprenom,lusername,tusername,lemail,temail,lmdp,tmdp,lnum_tel,tnum_tel,lcin,tcin,addU);
    }
}
