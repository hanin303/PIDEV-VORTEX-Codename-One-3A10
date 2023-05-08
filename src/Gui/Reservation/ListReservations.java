/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Reservation;

import Entity.Reservation;
import Services.ServiceReservation;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hanin
 */
public class ListReservations extends Form{
    //     public ListReservations(Form previous) {
//        setTitle("List Reservations");
//        setLayout(BoxLayout.y());
//        ArrayList<Reservation> reservations = ServiceReservation.getInstance().getAllReservations();
//        //widgets
//        SpanLabel sl = new SpanLabel();
//        sl.setText(reservations.toString());
//
//        //end
//        this.add(sl);
//
//    }
    public ListReservations(Form previous) {
    setTitle("List Reservations");
    setLayout(BoxLayout.y());
    ArrayList<Reservation> reservations = ServiceReservation.getInstance().getAllReservations();
    
    
   Button btnHome = new Button("Home");
   add(btnHome);

   btnHome.addActionListener((e) -> {
    ReservationHome home = new ReservationHome();
    home.show();
});
      
    for (Reservation reservation : reservations) {
        Container card = new Container(new BorderLayout());
        card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
        card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
        card.getStyle().setMargin(Component.BOTTOM, 10);
        card.getStyle().setBgColor(0xFFFFFF);
        
        Label idLabel = new Label("ID: " + reservation.getId_reservation());
        Label clientLabel = new Label("Client: " + reservation.getId_client());
        Label dateLabel = new Label("Date: " + reservation.getDate_reservation());
        Label heureDepartLabel = new Label("Heure de départ: " + reservation.getHeure_depart());
        Label heureArriveLabel = new Label("Heure d'arrivée: " + reservation.getHeure_arrive());
        Label typeTicketLabel = new Label("Type de ticket: " + reservation.getType_ticket());
        Label moyenTransportLabel = new Label("Moyen de transport: " + reservation.getId_moy());
        Label itLabel = new Label("Itineraire: " + reservation.getId_it());
        Label statusLabel = new Label("Status: " + reservation.getStatus());

        idLabel.getStyle().setFgColor(0x000000);
        clientLabel.getStyle().setFgColor(0x000000);
        dateLabel.getStyle().setFgColor(0x000000);
        heureDepartLabel.getStyle().setFgColor(0x000000);
        heureArriveLabel.getStyle().setFgColor(0x000000);
        typeTicketLabel.getStyle().setFgColor(0x000000);
        moyenTransportLabel.getStyle().setFgColor(0x000000);
        itLabel.getStyle().setFgColor(0x000000);
        statusLabel.getStyle().setFgColor(0x000000);

        card.add(BorderLayout.NORTH, idLabel);
        card.add(BorderLayout.CENTER, BoxLayout.encloseY(clientLabel,dateLabel, heureDepartLabel, heureArriveLabel,
                statusLabel, typeTicketLabel, moyenTransportLabel, itLabel));
        this.add(card);
        
        /*Button btndelete = new Button("delete");
        add(btndelete);*/
        Button btndelete = new Button("delete");
        FontImage.setMaterialIcon(btndelete, FontImage.MATERIAL_DELETE, 4);
        add(btndelete);
        
        /*Button updateButton = new Button("Update Reservation");
        updateButton.addActionListener(e -> {
        Form updateForm = new Form("Update Reservation");*/
        Button updateButton = new Button("Update Reservation");
        updateButton.setMaterialIcon(FontImage.MATERIAL_MODE_EDIT); // set the edit icon
        updateButton.addActionListener(e -> {
        Form updateForm = new Form("Update Reservation");

        TextField Date_reservationField = new TextField("", "Date reservation");
        /*Picker debutPicker = new Picker();
        debutPicker.setType(Display.PICKER_TYPE_DATE);
            //addStringValue("Date début", debutPicker);*/
        TextField Heure_departField = new TextField("", "Heure depart");
        TextField Heure_arriveField = new TextField("", "Heure arrive");
        TextField Type_ticketField = new TextField("", "Type ticket");
        TextField StatusField = new TextField("", "Status");
        TextField Id_moyField = new TextField("", "Id moy");
        TextField Id_itField = new TextField("", "Id it");
        TextField Id_clientField = new TextField("", "Id client");



    Button submitButton = new Button("Submit");
    submitButton.addActionListener(submitEvent -> {
        // Get the updated values of the fields
       
        //Date Date_reservation = Date_reservationField.getText();
        String Heure_depart = Heure_departField.getText();
        String Heure_arrive = Heure_arriveField.getText();
        String Type_ticket  =  Type_ticketField.getText();
        String Status = StatusField.getText();
        String Id_moy = Id_moyField.getText();
        String Id_it = Id_itField.getText();
        String Id_client = Id_clientField.getText();


       // Call the service function to update the station
         Services.ServiceReservation.getInstance().modifierReservation(reservation.getId_reservation(),
         reservation.getDate_reservation() , reservation.getHeure_depart() , reservation.getHeure_arrive(),
         reservation.getStatus(),reservation.getType_ticket(),reservation.getId_client(),reservation.getId_moy(),reservation.getId_it());

        // Show a confirmation message to the user
        Dialog.show("Success", "Reservation updated successfully", "OK", null);
            ListReservations refresh = new ListReservations(previous);
     refresh.show();
    });

    updateForm.add(Date_reservationField).add(Heure_departField).add(Heure_arriveField).add(Type_ticketField)
            .add(StatusField).add(Id_moyField).add(Id_itField).add(Id_clientField).add(submitButton);

    updateForm.show();
    
});

add(updateButton);
btndelete.addActionListener((e) -> {
     Services.ServiceReservation.getInstance().suppReservation(reservation);
     Dialog.show("Success", "Reservation deleted successfully", "OK", null);
     ListReservations refresh = new ListReservations(previous);
     refresh.show();

});
    }

        
    }

}

