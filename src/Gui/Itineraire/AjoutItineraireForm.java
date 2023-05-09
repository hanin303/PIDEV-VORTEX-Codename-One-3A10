/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Itineraire;

import Entity.Iteneraire;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
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
    tb.addSearchCommand((e)->{});
    Tabs swipe = new Tabs();
    Label s1 = new Label();
    Label s2 = new Label();
    
    
    
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
    Button btnHome = new Button("Home");
add(btnHome);

btnHome.addActionListener((e) -> {
    ItineraireHome home = new ItineraireHome ();
    home.show();
});
    
}
    public void addStringValue(String s, Component v){
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
                
    }

   
    
}
