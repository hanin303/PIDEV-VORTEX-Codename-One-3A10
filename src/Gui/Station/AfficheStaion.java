/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Station;

import Entity.Station;
import Services.ServiceStation;
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
 * @author User
 */
public class AfficheStaion  extends Form{
    public AfficheStaion(Form previous) {
    setTitle("List Station");
    setLayout(BoxLayout.y());
    ArrayList<Station> stations = ServiceStation.getInstance().getAllStations();
    
    
            Button btnHome = new Button("Home");
add(btnHome);

btnHome.addActionListener((e) -> {
    StationHome home = new StationHome();
    home.show();
});
    for (Station station : stations) {
        Container card = new Container(new BorderLayout());
        card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
        card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
        card.getStyle().setMargin(Component.BOTTOM, 10);
        card.getStyle().setBgColor(0xFFFFFF);

        Label idLabel = new Label("ID: " + station.getId());
        Label longaltLabel = new Label("Long_Lat: " + station.getLong_alt());

        idLabel.getStyle().setFgColor(0x000000);
        longaltLabel.getStyle().setFgColor(0x000000);
   

        card.add(BorderLayout.NORTH, idLabel);
        card.add(BorderLayout.CENTER, BoxLayout.encloseY(longaltLabel));
        this.add(card);
                           Button btndelete = new Button("delete");


add(btndelete);

Button updateButton = new Button("Update Station");
updateButton.addActionListener(e -> {
    Form updateForm = new Form("Update Station");

    TextField longAltField = new TextField("", "Long Altitude");

    Button submitButton = new Button("Submit");
    submitButton.addActionListener(submitEvent -> {
        // Get the updated values of the fields
       
        String longAlt = longAltField.getText();

        // Call the service function to update the station
                Services.ServiceStation.getInstance().modifierStation(station.getId(),longAlt);

     

        // Show a confirmation message to the user
        Dialog.show("Success", "Station updated successfully", "OK", null);
            AfficheStaion refresh = new AfficheStaion(previous);
     refresh.show();
    });

    updateForm.add(longAltField).add(submitButton);
    updateForm.show();
    
});

add(updateButton);
btndelete.addActionListener((e) -> {
     Services.ServiceStation.getInstance().suppStation(station);
     AfficheStaion refresh = new AfficheStaion(previous);
     refresh.show();

});
    }

}
}
