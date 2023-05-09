/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Reclamation;

import com.codename1.l10n.SimpleDateFormat;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import Entity.Reclamation;
import Services.ServiceReclamation;
import java.util.Date;
import com.codename1.ui.spinner.Picker;
import java.lang.Character;



/**
 *
 * @author amens
 */
/*
public class AjouterReclamation  extends BaseForm {
    
    
      public AjouterReclamation(Resources res) {
        setTitle("Ajouter une Reclamation");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));


        
         TextField text_recField = new TextField("", " text_rec", 40, TextField.ANY);
        TextField sujetField = new TextField("", "sujet", 40, TextField.ANY);
        TextField statutField = new TextField("", "statut", 40, TextField.ANY);
        Picker dateField = new Picker();
        dateField.setType(Display.PICKER_TYPE_DATE_AND_TIME);
       

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        text_recField.setUnselectedStyle(textFieldStyle);
        sujetField.setUnselectedStyle(textFieldStyle);
        statutField.setUnselectedStyle(textFieldStyle);
        dateField.setUnselectedStyle(textFieldStyle);
        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {
 
            
            String  text_rec = text_recField.getText();
        
            String sujet = sujetField.getText();
            
            String statut = statutField.getText();
            
            Date date_rec = (Date) dateField.getValue();

            
            Reclamation r = new Reclamation(text_rec, sujet, statut, date_rec);

            // Add the Livreur using the LivreurService
           ServiceReclamation.getInstance().ajouterReclamation(r);

            // Show a confirmation dialog
            Dialog.show("Succès", "La Reclamation a été ajouté avec succès", "OK", null);

            // Go back to the LivreurList form
           // new LivreurList(res).showBack();
        });

        // Add the text fields and button to the form
      
        addComponent(text_recField);
        addComponent(sujetField);
        addComponent(statutField);
        addComponent(dateField);
        addComponent(ajouterButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        //retourButton.addActionListener(e -> new LivreurList(res).showBack());
        addComponent(retourButton);
    }
      
      

    
}*/
public class AjouterReclamation extends BaseForm {

    public AjouterReclamation(Resources res) {
        setTitle("Ajouter une Reclamation");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        TextField text_recField = new TextField("", " text_rec", 40, TextField.ANY);
        TextField sujetField = new TextField("", "sujet", 40, TextField.ANY);
        TextField statutField = new TextField("", "statut", 40, TextField.ANY);
        Picker dateField = new Picker();
        dateField.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color
        text_recField.setUnselectedStyle(textFieldStyle);
        sujetField.setUnselectedStyle(textFieldStyle);
        statutField.setUnselectedStyle(textFieldStyle);
        dateField.setUnselectedStyle(textFieldStyle);

        Button ajouterButton = new Button("Ajouter");
        ajouterButton.addActionListener(e -> {
            String  text_rec = text_recField.getText();
            String sujet = sujetField.getText();
            String statut = statutField.getText();
            //Date date_rec = (Date) dateField.getValue();

            //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //String formattedDate = dateFormat.format(date_rec);
            if (!isInputValid(text_rec)) {
                Dialog.show("Erreur", "Le champ text_rec contient un mot interdit", "OK", null);
                return;
            }

            Reclamation req = new Reclamation(text_rec, sujet, statut,"2023-12-30");
            ServiceReclamation.getInstance().ajouterReclamation(req);

            Dialog.show("Succès", "La Reclamation a été ajouté avec succès", "OK", null);
        });

        addComponent(text_recField);
        addComponent(sujetField);
        addComponent(statutField);
        addComponent(dateField);
        addComponent(ajouterButton);

        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new Reclamationlist(res).showBack());
        addComponent(retourButton);
    }
    
 private boolean isInputValid(String input) {
    String[] forbiddenWords = {"fuck", "shit", "pute", "bitch"};

    for (String word : forbiddenWords) {
        int index = input.indexOf(word);
        if (index != -1) {
            int wordEnd = index + word.length();
            if ((index == 0 || !Character.isLowerCase(input.charAt(index - 1))) &&
                (wordEnd == input.length() || !Character.isLowerCase(input.charAt(wordEnd))) &&
                (index == 0 || !Character.isUpperCase(input.charAt(index - 1))) &&
                (wordEnd == input.length() || !Character.isUpperCase(input.charAt(wordEnd)))){
                return false;
            }
        }
    }

    return true;
}
}
