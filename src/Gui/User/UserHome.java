/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.User;

import com.codename1.ui.Command;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author hanin
 */
public class UserHome extends Form{
    public UserHome(int id){
        setTitle("Home Page");
        setLayout(BoxLayout.yCenter());
        Form edit=new EditProfile(1);
        add(edit);
        //Form F1=new ChangePassword();
        Command modifPass = new Command("Modifier mot de passe") {
        @Override
        public void actionPerformed(ActionEvent evt) {
         new EditPassword(1).show();
    }
};
        getToolbar().addCommandToSideMenu(modifPass);
    }
    
}
