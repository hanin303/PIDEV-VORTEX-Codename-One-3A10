/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Reclamation;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import Entity.Reclamation;
import Services.ServiceReclamation;

/**
 *
 * @author amens
 */
public class ModifierReclamation extends BaseForm {

    public ModifierReclamation(Resources res, Reclamation reclamation) {
        setTitle("Modifier le livreur");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Create text fields for each attribute of Livreur that can be modified
        //TextField text_recField = new TextField(reclamation.getMessage_rec(), "text_rec", 40, TextField.ANY);
        //TextField sujetField = new TextField(reclamation.getObjet(), "sujet", 40, TextField.ANY);
        TextField text_recField = new TextField("", " text_rec", 40, TextField.ANY);
        TextField sujetField = new TextField("", "sujet", 40, TextField.ANY);
        TextField statutField = new TextField("", "statut", 40, TextField.ANY);
        Picker dateField = new Picker();
        dateField.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        Style textFieldStyle = new Style();
        textFieldStyle.setFgColor(0x000000); // black color

        text_recField.setUnselectedStyle(textFieldStyle);
        sujetField.setUnselectedStyle(textFieldStyle);

        // Create a button to save the modifications
        Button modifierButton = new Button("Modifier");
        modifierButton.addActionListener(e -> {
            // Get the new values from the text fields

           // String text_rec = text_recField.getText();
            //String sujet = sujetField.getText();
            String  text_rec = text_recField.getText();
            String sujet = sujetField.getText();
            String statut = statutField.getText();

            // Create a new reclamation object with the new values
            Reclamation newreclamation = new Reclamation(reclamation.getId_reclamation(), text_rec, sujet, statut,"2023-12-30");

            // Call the modifierreclamation method of ServiceLivreur to modify the reclamation
            if (ServiceReclamation.getInstance().modifierReclamation(newreclamation)) { 
                // Show a confirmation dialog if the modification was successful
                Dialog.show("Succès", "Le reponse a été modifié avec succès", "OK", null);
            } else {
                Dialog.show("Erreur", "Une erreur s'est produite lors de la modification", "OK", null);
            }

            // Go back to the reclamationList form
            new Reclamationlist(res).showBack();
        });

        // Add the text fields and button to the form
       
        addComponent(sujetField);
        
         addComponent(text_recField);
         addComponent(statutField);
         addComponent(dateField);
         
        addComponent(modifierButton);

        // Add a back button to the top of the form
        Button retourButton = new Button("Retour");
        retourButton.addActionListener(e -> new Reclamationlist(res).showBack());
        addComponent(retourButton);
    }

}
