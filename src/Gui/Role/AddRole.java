/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.Role;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author MSI
 */
public class AddRole extends Form{
    public AddRole(){
        setTitle("Ajouter un role");
        setLayout(BoxLayout.yCenter());
        Label lnom = new Label("Nom:");
        TextField tnom= new TextField("","nom de role");
        Button addR= new Button("Ajouter");
        addR.addActionListener((evt) -> {
        });
        addAll(lnom,tnom,addR);
      
    }
    
    
}
