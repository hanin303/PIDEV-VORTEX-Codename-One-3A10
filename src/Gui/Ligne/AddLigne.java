/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.Ligne;

import Entity.Ligne;
import Entity.Station;
import Gui.Station.StationHome;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Asus
 */
public class AddLigne extends BaseForm {
       Form current;
    
    public AddLigne(Resources res ){
    super("NewsFeed",BoxLayout.y()); 
    Toolbar tb = new Toolbar(true);
    current = this; 
    setToolbar(tb);
    getTitleArea().setUIID("container");
    setTitle("ajout Ligne");
    getContentPane().setScrollVisible(false);
    
    TextField Nom_ligne = new TextField("","Nom ligne");
    Nom_ligne.setUIID("TextFieldBlack");
    addStringValue("nom_ligne",Nom_ligne);
     TextField Type_ligne = new TextField("","Type Ligne");
    Type_ligne.setUIID("TextFieldBlack");
    addStringValue("type_ligne",Type_ligne);
    


    Button btnAjouter = new Button("Ajouter");
    addStringValue("",btnAjouter);
   
    btnAjouter.addActionListener((e)->{
        try{
            if(Nom_ligne.getText()=="" || Type_ligne.getText()==""){
            Dialog.show("Verifier les données","","annuler","OR");}
            else{
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                Ligne s = new Ligne(String.valueOf(Nom_ligne.getText().toString()),String.valueOf(Type_ligne.getText().toString()));
                
                System.out.println("data station =="+s);
                
                Services.ServiceLigne.getInstance().AddLigne(s);
                iDialog.dispose();
                refreshTheme();
            }
        }catch(Exception ex){
            ex.printStackTrace();}
    });
    
        Button btnHome = new Button("Home");
add(btnHome);

btnHome.addActionListener((e) -> {
    LigneHome home = new LigneHome();
    home.show();
});
}
    public void addStringValue(String s, Component v){
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
                
    }
    
    
}
