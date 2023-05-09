/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Ligne;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author hanin
 */
public class LigneHome extends Form {
     public LigneHome() {
     
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddReservation = new Button("Add Ligne");
        Button btnListReservations = new Button("List Ligne");
       // btnAddReservation.addActionListener(e-> new AddLigneForm(this).show());
        //btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        btnListReservations.addActionListener(e-> new ListLigne(this).show());
        add(btnAddReservation);
        add(btnListReservations);

    }
    
    
}
