/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Ticket;


import Entity.Ticket;
import Services.ServiceTicket;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

/**
 *
 * @author hanin
 */
public class ListTickets extends Form{
       public ListTickets(Form previous) {
    setTitle("List Tickets");
    setLayout(BoxLayout.y());
    ArrayList<Ticket> tickets = ServiceTicket.getInstance().getAllTickets();
    
    
   Button btnHome = new Button("Home");
   add(btnHome);

   btnHome.addActionListener((e) -> {
    TicketHome home = new  TicketHome();
    home.show();
});
      
    for (Ticket ticket : tickets) {
        Container card = new Container(new BorderLayout());
        card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
        card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
        card.getStyle().setMargin(Component.BOTTOM, 10);
        card.getStyle().setBgColor(0xFFFFFF);
        
        Label idLabel = new Label("ID: " + ticket.getId_t());
        Label nomTicketLabel = new Label("Nom Ticket: " + ticket.getNom_ticket());
        Label prixLabel = new Label("Prix: " + ticket.getPrix());
        Label statusLabel = new Label("Status: " + ticket.getStatus());
        //Label id_reservationLabel = new Label("id_reservaion: " + ticket.getId_reservation());
      

        card.add(BorderLayout.NORTH, idLabel);
        card.add(BorderLayout.CENTER, BoxLayout.encloseY(nomTicketLabel,prixLabel, statusLabel ));
        //card.add(BorderLayout.CENTER, BoxLayout.encloseY(nomTicketLabel,prixLabel, statusLabel , id_reservationLabel ));

        this.add(card);
        
        Button btndelete = new Button("delete");
        add(btndelete);
        
        Button updateButton = new Button("Update Ticket");
        updateButton.addActionListener(e -> {
        Form updateForm = new Form("Update Ticket");

        TextField nomTicketField = new TextField("", "Nom_ticket");
        TextField prixField = new TextField("", "Prix");
        TextField statusField = new TextField("", "Status");
        //TextField id_reservationField = new TextField("", "id_reservation");
     
    Button submitButton = new Button("Submit");
    submitButton.addActionListener(submitEvent -> {
        // Get the updated values of the fields
       
        String nom_ticket =  nomTicketField.getText();
        String prix = prixField.getText();
        String status = statusField.getText();
        //String id_reservation  = id_reservationField.getText();
    
        // Call the service function to update the station
         Services.ServiceTicket.getInstance().modifierTicket(ticket.getId_t(),nom_ticket,prix,status);
         //Services.ServiceTicket.getInstance().modifierTicket(ticket.getId_t(),nom_ticket,prix,status,id_reservation);
        // Show a confirmation message to the user
        Dialog.show("Success", "Ticket updated successfully", "OK", null);
            ListTickets refresh = new ListTickets(previous);
     refresh.show();
    });

    updateForm.add(nomTicketField).add(prixField).add(statusField).add(submitButton);
    updateForm.show();
    
});

add(updateButton);
btndelete.addActionListener((e) -> {
     Services.ServiceTicket.getInstance().suppTicket(ticket);
     Dialog.show("Success", "Ticket deleted successfully", "OK", null);
     ListTickets refresh = new ListTickets(previous);
     refresh.show();

});
    }

        
    }

}
