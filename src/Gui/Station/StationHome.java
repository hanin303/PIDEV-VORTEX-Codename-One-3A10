/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Station;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hanin
 */
public class StationHome extends Form{
     public StationHome() {
     
        setTitle("Home");
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddStation = new Button("Add Station");
        Button btnListStations = new Button("List Stations");
        Resources  res = null;
        btnAddStation.addActionListener(e-> new AjoutStationForm(res).show());
        //btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        btnListStations.addActionListener(e-> new AfficheStaion(this).show());
        add(btnAddStation);
        add(btnListStations);

    }
    
    
}
