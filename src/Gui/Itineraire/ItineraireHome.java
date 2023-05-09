/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Itineraire;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;


public class ItineraireHome extends Form  {
    public ItineraireHome(){
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddItineraire = new Button("Add Itineraire");
        Button btnListItineraires = new Button("List Itineraires");
        Resources res = null;
        btnAddItineraire.addActionListener(e-> new AjoutItineraireForm(res).show());
        //btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        btnListItineraires.addActionListener(e-> new AffichageItineraire(this).show());
        add(btnAddItineraire);
        add(btnListItineraires);


    }
    
}
