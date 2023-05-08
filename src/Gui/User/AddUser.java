/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.User;

import Entity.Role;
import Entity.User;
import Services.ServiceUser;
import com.codename1.components.SpanLabel;
import com.codename1.components.Switch;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author MSI
 */
public class AddUser extends Form{
    public AddUser() {
        setTitle("Inscription");
        setLayout(BoxLayout.yCenter());
        Label lnom = new Label("nom:");
        TextField tnom = new TextField("","votre nom");
        Label lprenom = new Label("Prénom:");
        TextField tprenom = new TextField("","votre prénom");
        Label lusername = new Label("votre username");
        TextField tusername = new TextField("","Username:");
        Label lemail = new Label("Adresse E-mail:");
        TextField temail = new TextField("","votre adresse e-mail");
        Label lmdp = new Label("Mot de passe:");
        TextField tmdp = new TextField();
        TextField tconfirmmdp = new TextField();
        tmdp.setHint("votre mot de passe");
        tmdp.setConstraint(TextField.PASSWORD);
        Label lnum_tel = new Label("Numéro de téléphone:");
        TextField tnum_tel = new TextField("","votre numéro de téléphone");
        tnum_tel.setConstraint(TextField.PHONENUMBER);
        Label lcin = new Label("Numéro de carte d'identité:");
        TextField tcin = new TextField("","Votre numéro de carte d'identité");
        tcin.setConstraint(TextField.NUMERIC);
        Button addU = new Button("s'inscrire");
        addU.addActionListener((new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                User user = new User(tnom.getText(),tprenom.getText(),tusername.getText(),temail.getText(),tmdp.getText(),Integer.parseInt(tnum_tel.getText()),Integer.parseInt(tcin.getText()));
                if(ServiceUser.getService().addUser(user)){
                    Dialog.show("success", "compte ajouté avec succés", "OK",null);
                }else{
                Dialog.show("error", "ajout a échoué", "OK",null);
                }
            }
        }));
        addAll(lnom,tnom,lprenom,tprenom,lusername,tusername,lemail,temail,lmdp,tmdp,lnum_tel,tnum_tel,lcin,tcin,addU);
        Label login = new Label("Vous avez déjà un compte ?");
        login.getUnselectedStyle().setUnderline(true);
    
        // Add a PointerPressedListener to the label to show the SecondForm
        login.addPointerPressedListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
            new Login().show();
           }
          });
    
        // Add the label to the MainForm
         addComponent(login);
         
}
}
