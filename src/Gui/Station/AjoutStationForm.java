/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Station;

import Entity.Station;
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
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AjoutStationForm extends BaseForm {
    Form current;
    
    public AjoutStationForm(Resources res ){
    super("NewsFeed",BoxLayout.y()); 
    Toolbar tb = new Toolbar(true);
    current = this; 
    setToolbar(tb);
    getTitleArea().setUIID("container");
    setTitle("ajout station");
    getContentPane().setScrollVisible(false);
    
    TextField long_alt = new TextField("","Long_alt");
    long_alt.setUIID("TextFieldBlack");
    addStringValue("long_alt",long_alt);
    


    Button btnAjouter = new Button("Ajouter");
    addStringValue("",btnAjouter);
   
    btnAjouter.addActionListener((e)->{
        try{
            if(long_alt.getText()==""){
            Dialog.show("Verifier les données","","annuler","OR");}
            else{
                InfiniteProgress ip = new InfiniteProgress();
                final Dialog iDialog = ip.showInfiniteBlocking();
                Station s = new Station(String.valueOf(long_alt.getText().toString()));
                
                System.out.println("data station =="+s);
                
                Services.ServiceStation.getInstance().AjouterStation(s);
                iDialog.dispose();
                refreshTheme();
            }
        }catch(Exception ex){
            ex.printStackTrace();}
    });
    
        Button btnHome = new Button("Home");
add(btnHome);

btnHome.addActionListener((e) -> {
    StationHome home = new StationHome();
    home.show();
});
}
    public void addStringValue(String s, Component v){
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
                
    }

}
