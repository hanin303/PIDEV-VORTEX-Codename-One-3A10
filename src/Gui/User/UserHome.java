/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;

import Entity.User;
import Gui.Itineraire.*;
import Gui.Ligne.*;
import Gui.Reclamation.*;
import Gui.Reservation.*;
import Gui.Station.*;
import Gui.Ticket.*;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author hanin
 */
public class UserHome extends Form{
    public UserHome(User u,Form previous){
        setTitle("Home Page");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Form edit=new Edit(u,u.getId_user(),previous);
        add(edit);
        //Form F1=new ChangePassword();
        Command modifPass = new Command("Modifier mot de passe") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new EditPassword(u,previous).show();
    }
};
        Command users = new Command("Utilisateurs") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new DisplayUser(u,previous).show();
    }
};
        Command log = new Command("Déconnexion") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new Login(previous).show();
    }
};
         Command ajout = new Command("Ajouter utilisateur") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new AddAdmin(u,previous).show();
    }
};
         Command home_it = new Command("Iténeraires") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new ItineraireHome(u,previous).show();
    }
};
         Command rec = new Command("Mes réclamations") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new Reclamationlist(u,previous).show();
    }
};
         Command res = new Command("Mes réservations") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new ReservationHome(u,previous).show();
    }
};
        Command ticket = new Command("Mes tickets") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new TicketHome(u,previous).show();
    }
};
         Command ligne = new Command("Lignes urbaines") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new LigneHome(u,previous).show();
    }
};
        Command station = new Command("Stations") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new StationHome(u,previous).show();
    }
};
        if(u.getId_role()==1){ 
        getToolbar().addCommandToSideMenu(modifPass);
        getToolbar().addCommandToSideMenu(ajout);
        getToolbar().addCommandToSideMenu(users);
        getToolbar().addCommandToSideMenu(home_it);
        getToolbar().addCommandToSideMenu(ligne);
        getToolbar().addCommandToSideMenu(station);
        getToolbar().addCommandToSideMenu(log);
    }else if(u.getId_role()==2){
        getToolbar().addCommandToSideMenu(modifPass);
        getToolbar().addCommandToSideMenu(home_it);
        getToolbar().addCommandToSideMenu(ligne);
        getToolbar().addCommandToSideMenu(station);
        getToolbar().addCommandToSideMenu(rec);
        getToolbar().addCommandToSideMenu(log);
    }else if(u.getId_role()==3){
        getToolbar().addCommandToSideMenu(modifPass);
        getToolbar().addCommandToSideMenu(home_it);
        getToolbar().addCommandToSideMenu(ligne);
        getToolbar().addCommandToSideMenu(rec);
        getToolbar().addCommandToSideMenu(log);
    }else{
        getToolbar().addCommandToSideMenu(modifPass);
        getToolbar().addCommandToSideMenu(rec);
        getToolbar().addCommandToSideMenu(ligne);
        getToolbar().addCommandToSideMenu(res);
        getToolbar().addCommandToSideMenu(ticket);
        getToolbar().addCommandToSideMenu(log);

    }
    }
}
