/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Ticket;

import Entity.User;
import Gui.Reservation.AddReservationForm;
import Gui.Reservation.ListReservations;
import Gui.User.UserHome;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hanin
 */
public class TicketHome extends Form{
    public TicketHome(User u, Form prev) {
     
        setTitle("Home");
        Button back = new Button();
        back.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, back.getStyle()));
        back.addActionListener((evt) -> {
        new UserHome(u,prev).show();
        });
        Container container = new Container(new FlowLayout(Component.LEFT));
        container.add(back);
        addComponent(container);
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddTicket = new Button("Add Ticket");
        //Button btnListTickets = new Button("List Tickets");
        Button btnListTickets = new Button("List Tickets");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_LIST, "Button", 6);
        btnListTickets.setIcon(icon);

        Resources  res = null;
        btnAddTicket.addActionListener(e-> new AddTicketForm(u,prev,res).show());
        btnListTickets.addActionListener(e-> new ListTickets(u,this).show());
        add(btnAddTicket);
        add(btnListTickets);

    }
}
