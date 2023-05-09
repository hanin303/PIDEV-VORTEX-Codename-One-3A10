/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Itineraire;

import Entity.User;
import Gui.User.Login;
import Gui.User.*;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;


public class ItineraireHome extends Form  {
    public ItineraireHome(User u, Form prev){
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
        Button btnAddItineraire = new Button("Add Itineraire");
        Button btnListItineraires = new Button("List Itineraires");
        Button MapButton = new Button("Map");
        Resources res = null;
        btnAddItineraire.addActionListener(e-> new AjoutItineraireForm(u,prev,res).show());
        //btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        btnListItineraires.addActionListener(e-> new AffichageItineraire(u,this).show());
        MapButton.addActionListener(e->new MapForm(u,prev));
        add(btnAddItineraire);
        add(btnListItineraires);
        add(MapButton);


    }
    
}
