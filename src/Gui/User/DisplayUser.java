/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.User;

import Entity.User;
import Services.ServiceUser;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class DisplayUser extends Form{
    public DisplayUser(Form previous){
        setTitle("Liste des utilisateurs");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        ArrayList<User> users = ServiceUser.getInstance().getAllUsers();
        //User usr = ServiceUser.getService().getUser(5);
        //System.out.println("user"+usr);
        System.out.println(users);

        for( User user: users) {

            Container card = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
            card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
            card.getStyle().setMargin(Component.BOTTOM, 2);
            card.getStyle().setBgColor(0xFFFFFF);
            System.out.println(user.getId_user());
            Label id = new Label("ID: " + user.getId_user());
            Label nom = new Label("Nom: " + user.getNom());
            Label prenom = new Label("Prénom: " + user.getPrenom());
            Label username = new Label("Username: " + user.getUsername());
            Label email = new Label("E-mail" + user.getEmail());
            Label num_tel = new Label("Numéro téléphone" + user.getNum_tel());
            Label cin = new Label("CIN" + user.getCin());
          //  Label role = new Label("Nom" + user.getRole().getNom());
            id.getStyle().setFgColor(0x000000);
            nom.getStyle().setFgColor(0x000000);
            prenom.getStyle().setFgColor(0x000000);
            username.getStyle().setFgColor(0x000000);
            email.getStyle().setFgColor(0x000000);
            num_tel.getStyle().setFgColor(0x000000);
            cin.getStyle().setFgColor(0x000000);
            //role.getStyle().setFgColor(0x000000);
            /*
            card.add(BorderLayout.CENTER, BoxLayout.encloseY(id));
            card.add(BorderLayout.NORTH, nom);
            card.add(BorderLayout.NORTH, prenom);
            card.add(BorderLayout.NORTH, username);
            card.add(BorderLayout.NORTH, email);
            card.add(BorderLayout.NORTH,num_tel);
            card.add(BorderLayout.NORTH, cin);
            //card.add(BorderLayout.NORTH, role);
           */

            Button supp = new Button("Supprimer");
            Button modif = new Button("Modifer");
            modif.addActionListener((evt) -> {
                new EditProfile(user.getId_user()).show();
            });
            supp.addActionListener((evt) -> {
                ServiceUser.getInstance().deleteUser(user.getId_user());
                DisplayUser refresh = new DisplayUser(previous);
                refresh.show();
            });
            //card.add(BorderLayout.CENTER, supp);
            //card.add(BorderLayout.CENTER, modif);
            card.addAll(id,nom,prenom,username,email,num_tel,cin,supp,modif);
            //addAll(card,supp, modif)s
            this.add(card);
        }

    }
    
}

    
