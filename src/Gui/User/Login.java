/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.User;

import Entity.User;
import Services.ServiceUser;
import com.codename1.components.SpanButton;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
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
public class Login extends Form {
    public Login(Form prev){
        setLayout(BoxLayout.yCenter());        
        setTitle("Se connecter");
        Label lusername = new Label("Username:");
        TextField tusername = new TextField("","votre username");
        Label lmdp = new Label("Mot de passe:");
        TextField tmdp= new TextField();
        tmdp.setHint("votre mot de passe");
        tmdp.setConstraint(TextField.PASSWORD);
        Button con = new Button("connecter");
        con.addActionListener((evt) -> {
            ServiceUser.getInstance().login(tusername.getText().toString(),tmdp.getText().toString());
            User u = ServiceUser.getInstance().login(tusername.getText().toString(), tmdp.getText().toString());
            Dialog.show("Success", "Connecté avec succés", "OK", null);
            new UserHome(u,prev).show();
        });
        addAll(lusername,tusername,lmdp,tmdp,con);
        Label addU = new Label("Créer votre compte");
        addU.getUnselectedStyle().setUnderline(true);
    
        // Add a PointerPressedListener to the label to show the SecondForm
        addU.addPointerPressedListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
            new AddUser(prev).show();
           }
          });
        addComponent(addU);
        Label forget = new Label("Mot de passe oublié ?");
        forget.getUnselectedStyle().setUnderline(true);
    
        // Add a PointerPressedListener to the label to show the SecondForm
        forget.addPointerPressedListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent evt) {
            new ForgetEmail(prev).show();
           }
          });
        // Add the label to the MainForm
         addComponent(forget);
       }
    
    
}
