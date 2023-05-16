/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Ligne;

import Entity.User;
import Gui.User.UserHome;
import com.codename1.components.WebBrowser;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hanin
 */
public class LigneHome extends Form {
     public LigneHome(User u, Form previous) {
     
        setTitle("Home");
        Button back = new Button();
        back.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, back.getStyle()));
        back.addActionListener((evt) -> {
        new UserHome(u,previous).show();
        });
        Container container = new Container(new FlowLayout(Component.LEFT));
        container.add(back);
        addComponent(container);
        setLayout(BoxLayout.y());
        
        add(new Label("Choose an option"));
        Button btnAddReservation = new Button("Add Ligne");
        Button btnListReservations = new Button("List Ligne");
        Button button = new Button("Visit Web site");
        Resources res = null;
        btnAddReservation.addActionListener(e-> new AddLigne(u,previous,res).show());
        //btnListTasks.addActionListener(e-> new ListTasksForm(this).show());
        btnListReservations.addActionListener(e-> new ListLigne(u,this).show());
        add(btnAddReservation);
        add(btnListReservations);
        add(button);
         button.addActionListener(evt -> {
            // Create a new web browser component
            WebBrowser webBrowser = new WebBrowser();

            // Set the URL to load
            webBrowser.setURL("http://127.0.0.1:8000/login");

            // Create a new form and add the web browser component to it
            Form webViewForm = new Form();
            webViewForm.setLayout(new BorderLayout());
            webViewForm.add(BorderLayout.CENTER, webBrowser);

            // Show the new form
            webViewForm.show();
        });

    }
    
    
}
