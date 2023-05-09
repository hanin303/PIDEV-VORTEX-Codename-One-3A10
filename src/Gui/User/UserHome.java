/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;

import Entity.User;
import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;

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
        if(u.getId_role()==1){
        
        getToolbar().addCommandToSideMenu(modifPass);
        getToolbar().addCommandToSideMenu(users);
        getToolbar().addCommandToSideMenu(log);
    }else if(u.getId_role()==4){
        getToolbar().addCommandToSideMenu(modifPass);
        getToolbar().addCommandToSideMenu(log);

    }
    }
}
