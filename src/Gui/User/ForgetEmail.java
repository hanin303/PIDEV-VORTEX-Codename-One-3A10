/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.User;

import Services.ServiceUser;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

import java.util.ArrayList;

/**
 *
 * @author MSI
 */
public class ForgetEmail extends Form{
    public ForgetEmail(Form prev){
        
        setTitle("Récupération de mot de passe");
         // Create the back button with a back icon
        Button back = new Button();
        back.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, back.getStyle()));
        back.addActionListener((evt) -> {
        new Login(prev).show();
        });

        Container container = new Container(new FlowLayout(Component.LEFT));
        container.add(back);
        
        // Add the container to the form
        addComponent(container);
        setLayout(BoxLayout.yCenter());
        Label lemail= new Label("Entrer votre adresse e-mail");
        TextField temail= new TextField("");
        Button send = new Button("Envoyer");
        send.addActionListener((evt) -> {
            ArrayList<String> code ;
            code= ServiceUser.getInstance().emailPass(temail.getText().toString());

            float id = Float.parseFloat(code.get(1).toString());
            ForgetCode forgetCode = new ForgetCode(code.get(0),(int)id,prev);
            forgetCode.show();
        });
        addAll(lemail,temail,send);
       
        
       

    }
}
