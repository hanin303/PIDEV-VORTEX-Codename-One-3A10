/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Ticket;

import Entity.Reservation;
import Entity.Ticket;
import Gui.Reservation.ReservationHome;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hanin
 */
public class AddTicketForm extends Form{
    Form current;
    
     public AddTicketForm(Resources res) {
       super("Add New Ticket",BoxLayout.y()); 
    Toolbar tb = new Toolbar(true);
    current = this; 
    setToolbar(tb);
    getTitleArea().setUIID("container");
    //setTitle("Add New Reservation");
    getContentPane().setScrollVisible(false);
    
    TextField nom_ticket = new TextField("","nom_ticket");
    nom_ticket.setUIID("TextFieldBlack");
    addStringValue("nom_ticket",nom_ticket);
    
    TextField prix = new TextField("","prix");
    prix.setUIID("TextFieldBlack");
    addStringValue("prix",prix);
    
    TextField status = new TextField("","status");
    status.setUIID("TextFieldBlack");
    addStringValue("status",status);
    
//    TextField id_reservation = new TextField("","id_reservation");
//    id_reservation.setUIID("TextFieldBlack");
//    addStringValue("id_reservation",id_reservation);
//   

    Button btnAjouter = new Button("Ajouter");
    addStringValue("",btnAjouter);
   
    btnAjouter.addActionListener((e)->{
        try{
            if(nom_ticket.getText()==""|prix.getText()==""|status.getText()==""){
            Dialog.show("Verifier les données","","annuler","OR");}
            else{
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                Ticket t = new Ticket(String.valueOf(nom_ticket.getText().toString()),prix.getText().toString(),status.getText().toString());   
                System.out.println("data ticket =="+t);
                
                Services.ServiceTicket.getInstance().AjouterTicket(t);
                iDialog.dispose();
                refreshTheme();
            }
        }catch(Exception ex){
            ex.printStackTrace();}
    });
    
        Button btnHome = new Button("Home");
        add(btnHome);

        btnHome.addActionListener((e) -> {
        TicketHome home = new TicketHome();
    home.show();
});
}
    public void addStringValue(String s, Component v){
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        //add(createLineSeparator(0xeeeeee));
                
    }
    
}
