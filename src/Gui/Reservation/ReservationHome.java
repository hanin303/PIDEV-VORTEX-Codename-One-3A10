/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Reservation;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hanin
 */
public class ReservationHome extends Form {

    public ReservationHome() {
     
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddReservation = new Button("Add Reservation");
        Button btnListReservations = new Button("List Reservations");
        Resources  res = null;
        btnAddReservation.addActionListener(e-> new AddReservationForm(res).show());
        //btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        btnListReservations.addActionListener(e-> new ListReservations(this).show());
        add(btnAddReservation);
        add(btnListReservations);

    }
    
}
