/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.User;

import Entity.User;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author MSI
 */
public class EditProfile extends Form{
    public EditProfile(){
        setTitle("Mon profile");
        setLayout(BoxLayout.yCenter());
        Label lnom = new Label("nom:");
        TextField tnom = new TextField();
        Label lprenom = new Label("Prénom:");
        TextField tprenom = new TextField();
        Label lusername = new Label("Username");
        TextField tusername = new TextField();
        Label lemail = new Label("Adresse E-mail:");
        TextField temail = new TextField();
        Label lnum_tel = new Label("Numéro de téléphone:");
        TextField tnum_tel = new TextField();
        tnum_tel.setConstraint(TextField.NUMERIC);
        Label lcin = new Label("Numéro de carte d'identité:");
        TextField tcin = new TextField();
        tcin.setConstraint(TextField.NUMERIC);
        Button modif = new Button("Modifier");
        modif.addActionListener((evt) -> {
        });
        addAll(lnom,tnom,lprenom,tprenom,lusername,tusername,lemail,temail,lnum_tel,tnum_tel,lcin,tcin,modif);
    }
}
