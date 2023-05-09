/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui.Ligne;

import Entity.Ligne;
import Services.ServiceLigne;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class ListLigne  extends Form{
     public ListLigne(Form previous) {
    setTitle("List Ligne");
    setLayout(BoxLayout.y());
    ArrayList<Ligne> ligne = ServiceLigne.getInstance().getAllReservations();
    
    for (Ligne reservation : ligne) {
        Container card = new Container(new BorderLayout());
        card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
        card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
        card.getStyle().setMargin(Component.BOTTOM, 10);
        card.getStyle().setBgColor(0xFFFFFF);

        Label clientLabel = new Label("id: " + reservation.getId_ligne());
        Label dateLabel = new Label("nom ligne: " + reservation.getNom_ligne());
        Label heureDepartLabel = new Label("type ligne: " + reservation.getType_ligne());
       
        
        clientLabel.getStyle().setFgColor(0x000000);
        dateLabel.getStyle().setFgColor(0x000000);
        heureDepartLabel.getStyle().setFgColor(0x000000);
       
        card.add(BorderLayout.NORTH, clientLabel);
        card.add(BorderLayout.CENTER, BoxLayout.encloseY(dateLabel, heureDepartLabel));
        this.add(card);
        Button btndelete = new Button("delete");
        add(btndelete);
        btndelete.addActionListener((e) -> {
       Services.ServiceLigne.getInstance().suppLigne(reservation);
       ListLigne refresh = new ListLigne(previous);
       refresh.show();

});
        
        Button updateButton = new Button("Update Ligne");
updateButton.addActionListener(e -> {
    Form updateForm = new Form("Update Ligne");

    TextField NomLigne= new TextField("", "Nom ligne");
     TextField TypeLigne = new TextField("", "Type lign");

    Button submitButton = new Button("Submit");
    submitButton.addActionListener(submitEvent -> {
        // Get the updated values of the fields
       
        String Nom_Ligne = NomLigne.getText();
        String Type_Ligne = TypeLigne.getText();

        // Call the service function to update the station
                Services.ServiceLigne.getInstance().modifierLigne(reservation.getId_ligne(), Nom_Ligne, Type_Ligne);

     

        // Show a confirmation message to the user
        Dialog.show("Success", "Station updated successfully", "OK", null);
            ListLigne refresh = new ListLigne(previous);
     refresh.show();
    });

    updateForm.add(NomLigne).add(TypeLigne).add(submitButton);
    updateForm.show();
    
});

add(updateButton);
        
        
    }

}
    
}
