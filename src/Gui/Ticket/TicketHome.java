/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Ticket;

import Gui.Reservation.AddReservationForm;
import Gui.Reservation.ListReservations;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hanin
 */
public class TicketHome extends Form{
    public TicketHome() {
     
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTicket = new Button("Add Ticket");
        Button btnListTickets = new Button("List Tickets");
        Resources  res = null;
        btnAddTicket.addActionListener(e-> new AddTicketForm(res).show());
        btnListTickets.addActionListener(e-> new ListTickets(this).show());
        add(btnAddTicket);
        add(btnListTickets);

    }
}
