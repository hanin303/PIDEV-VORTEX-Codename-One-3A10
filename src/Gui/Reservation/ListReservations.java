/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Reservation;

import Entity.Reservation;
import Services.ServiceReservation;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import java.util.ArrayList;

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
    
    for (Reservation reservation : reservations) {
        Container card = new Container(new BorderLayout());
        card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
        card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
        card.getStyle().setMargin(Component.BOTTOM, 10);
        card.getStyle().setBgColor(0xFFFFFF);

        Label clientLabel = new Label("Client: " + reservation.getId_client());
        Label dateLabel = new Label("Date: " + reservation.getDate_reservation());
        Label heureDepartLabel = new Label("Heure de départ: " + reservation.getHeure_depart());
        Label heureArriveLabel = new Label("Heure d'arrivée: " + reservation.getHeure_arrive());
        Label typeTicketLabel = new Label("Type de ticket: " + reservation.getType_ticket());
        Label moyenTransportLabel = new Label("Moyen de transport: " + reservation.getId_moy());
        Label itLabel = new Label("Itineraire: " + reservation.getId_it());
        Label statusLabel = new Label("Status: " + reservation.getStatus());

        clientLabel.getStyle().setFgColor(0x000000);
        dateLabel.getStyle().setFgColor(0x000000);
        heureDepartLabel.getStyle().setFgColor(0x000000);
        heureArriveLabel.getStyle().setFgColor(0x000000);
        typeTicketLabel.getStyle().setFgColor(0x000000);
        moyenTransportLabel.getStyle().setFgColor(0x000000);
        itLabel.getStyle().setFgColor(0x000000);
        statusLabel.getStyle().setFgColor(0x000000);

        card.add(BorderLayout.NORTH, clientLabel);
        card.add(BorderLayout.CENTER, BoxLayout.encloseY(dateLabel, heureDepartLabel, heureArriveLabel,
                statusLabel, typeTicketLabel, moyenTransportLabel, itLabel));
        this.add(card);
    }

}
}
