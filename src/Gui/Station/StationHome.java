/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Station;

import Entity.User;
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
public class StationHome extends Form{
     public StationHome(User u, Form prev) {
     
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
        Button btnAddStation = new Button("Add Station");
        Button btnListStations = new Button("List Stations");
        Button btnMap = new Button("Position Station");
        Resources  res = null;
        btnAddStation.addActionListener(e-> new AjoutStationForm(u,prev,res).show());
        //btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        btnListStations.addActionListener(e-> new AfficheStaion(u,this).show());
        btnMap.addActionListener(e-> new MapForm(u,prev));
        add(btnAddStation);
        add(btnListStations);
        add(btnMap);


    }
    
    
}
