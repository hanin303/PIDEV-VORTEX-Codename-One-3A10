/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.User;

import Services.ServiceUser;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
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
public class ForgetCode extends Form{
    public ForgetCode(String code,int id,Form prev){
        setTitle("Récupération de mot de passe");
        Button back = new Button();
        back.setIcon(FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, back.getStyle()));
        back.addActionListener((evt) -> {

        new Login(prev).show();
        });

        Container container = new Container(new FlowLayout(Component.LEFT));
        container.add(back);
        addComponent(container);
        setLayout(BoxLayout.yCenter()); 
        Label lcode = new Label("Entrer votre code de récupération");
        TextField tcode = new TextField("");
        Button send = new Button("Envoyer");
        send.addActionListener((evt) -> {
            if (tcode.getText().toString().equals(code)){
                System.out.println("good");
                //EditPassword editPassword = new EditPassword(id,prev);
               // editPassword.show();
            }
            else
                System.out.println("bad");

        });
        addAll(lcode,tcode,send);

    }
    
}
