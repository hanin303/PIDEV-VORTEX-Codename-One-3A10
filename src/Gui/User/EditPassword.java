/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.User;

import Entity.User;
import Services.ServiceUser;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author MSI
 */
public class EditPassword extends Form{
    public EditPassword(User u,Form prev){
        setTitle("Modifier mot de passe");
        Button back = new Button();
        back.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, back.getStyle()));
        back.addActionListener((evt) -> {
        new UserHome(u,prev).show();
        });
        Container container = new Container(new FlowLayout(Component.LEFT));
        container.add(back);
        addComponent(container);
        setLayout(BoxLayout.yCenter()); 
        Label lmdp1 = new Label("Mot de passe:");
        TextField tmdp1 = new TextField();
        tmdp1.setConstraint(TextField.PASSWORD);
        Label lmdp2 = new Label("Comfirmation de mot de passe:");
        TextField tmdp2 = new TextField();
        tmdp2.setConstraint(TextField.PASSWORD);
        Button modif = new Button("Modifier");
        modif.addActionListener((evt) -> {
            if(ServiceUser.getService().modifPass(u.getId_user(),tmdp1.getText())){
                Dialog.show("Success", "Mot de passe modifié avec succés", "OK", null);
                UserHome refresh = new UserHome(u,prev);
                refresh.show();  
            }                    
        });
        addAll(lmdp1,tmdp1,lmdp2,tmdp2,modif);
    }
    
}
