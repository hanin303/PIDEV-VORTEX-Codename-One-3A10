/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Itineraire;

import Entity.Iteneraire;
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
 * @author kalee
 */
public class AjoutItineraireForm extends BaseForm{
    Form current;
    public AjoutItineraireForm(Resources res ){
    super("NewsFeed",BoxLayout.y()); 
    Toolbar tb = new Toolbar(true);
    current = this; 
    setToolbar(tb);
    getTitleArea().setUIID("container");
    setTitle("ajout itineraire");
    getContentPane().setScrollVisible(false);
    
    TextField pts_depart = new TextField("","point du depart");
    pts_depart.setUIID("TextFieldBlack");
    addStringValue("pts_depart",pts_depart);
    
    TextField pts_arrive = new TextField("","point d'arrive");
    pts_arrive.setUIID("TextFieldBlack");
    addStringValue("pts_arrive",pts_arrive);
    
    Button btnAjouter = new Button("Ajouter");
    addStringValue("",btnAjouter);
   
    btnAjouter.addActionListener((e)->{
        try{
            if(pts_depart.getText()==""|pts_arrive.getText()==""){
            Dialog.show("Verifier les données","","annuler","OR");}
            else{
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                Iteneraire it = new Iteneraire(String.valueOf
                (pts_depart.getText().toString()),
                 pts_arrive.getText().toString());
                
                System.out.println("data itineraire =="+it);
                
                Services.ServiceItineraire.getInstance().AjouterItineraire(it);
                iDialog.dispose();
                refreshTheme();
            }
        }catch(Exception ex){
            ex.printStackTrace();}
    });
    
    
}
    public void addStringValue(String s, Component v){
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
                
    }
    
}
