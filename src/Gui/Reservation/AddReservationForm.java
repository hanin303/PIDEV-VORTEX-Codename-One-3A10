/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Reservation;

import Entity.Reservation;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hanin
 */
public class AddReservationForm extends Form{
     Form current;
    
     public AddReservationForm(Resources res) {
       super("Add New Reservation",BoxLayout.y()); 
    Toolbar tb = new Toolbar(true);
    current = this; 
    setToolbar(tb);
    getTitleArea().setUIID("container");
    //setTitle("Add New Reservation");
    getContentPane().setScrollVisible(false);
    
    /*TextField date_reservation = new TextField("","date_reservation");
    date_reservation.setUIID("TextFieldBlack");
    addStringValue("date_reservation",date_reservation);*/
    Picker debutPicker = new Picker();
            debutPicker.setType(Display.PICKER_TYPE_DATE);
            addStringValue("Date début", debutPicker);
    
    TextField heure_depart = new TextField("","heure_depart");
    heure_depart.setUIID("TextFieldBlack");
    addStringValue("heure_depart",heure_depart);
    
    TextField heure_arrive = new TextField("","heure_arrive");
    heure_arrive.setUIID("TextFieldBlack");
    addStringValue("heure_arrive",heure_arrive);
    
    TextField status = new TextField("","status");
    status.setUIID("TextFieldBlack");
    addStringValue("status",status);
    
    TextField type_ticket = new TextField("","type_ticket");
    type_ticket.setUIID("TextFieldBlack");
    addStringValue("type_ticket",type_ticket);
    
    TextField id_client = new TextField("","id_client");
    id_client.setUIID("TextFieldBlack");
    addStringValue("id_client",id_client);
    
    TextField id_moy = new TextField("","id_moy");
    id_moy.setUIID("TextFieldBlack");
    addStringValue("id_moy",id_moy);
    
    TextField id_it = new TextField("","id_it");
    id_it.setUIID("TextFieldBlack");
    addStringValue("id_it",id_it);
    

    Button btnAjouter = new Button("Ajouter");
    addStringValue("",btnAjouter);
   
    btnAjouter.addActionListener((e)->{
        try{
            if(heure_depart.getText()==""|heure_arrive.getText()==""|status.getText()==""|type_ticket.getText()==""|id_client.getText()==""|id_moy.getText()==""|id_it.getText()==""){
            Dialog.show("Verifier les données","","annuler","OR");}
            else{
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                Reservation r = new Reservation(debutPicker.getDate(),heure_depart.getText().toString(),heure_arrive.getText().toString(),
                        status.getText().toString(),type_ticket.getText().toString(),id_client.getText().toString(),id_moy.getText().toString(),id_it.getText().toString());   
                System.out.println("data reservation =="+r);
                
                Services.ServiceReservation.getInstance().AjouterReservation(r);
                iDialog.dispose();
                refreshTheme();
            }
        }catch(Exception ex){
            ex.printStackTrace();}
    });
    
        Button btnHome = new Button("Home");
        add(btnHome);

        btnHome.addActionListener((e) -> {
        ReservationHome home = new  ReservationHome();
    home.show();
});
}
    public void addStringValue(String s, Component v){
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        //add(createLineSeparator(0xeeeeee));
                
    }
    
    
    
}
