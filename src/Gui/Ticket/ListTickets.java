/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui.Ticket;


import Entity.Ticket;
import Entity.User;
import Services.ServiceTicket;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hanin
 */
public class ListTickets extends Form{
       public ListTickets(User u,Form previous) {
    setTitle("List Tickets");
    setLayout(BoxLayout.y());
    ArrayList<Ticket> tickets = ServiceTicket.getInstance().getAllTickets();
    
    
   Button btnHome = new Button("Home");
   add(btnHome);

   btnHome.addActionListener((e) -> {
    TicketHome home = new  TicketHome(u,previous);
    home.show();
});
      
    for (Ticket ticket : tickets) {
        Container card = new Container(new BorderLayout());
        card.getStyle().setBorder(Border.createLineBorder(1, ColorUtil.GRAY));
        card.getStyle().setMarginUnit(Style.UNIT_TYPE_DIPS);
        card.getStyle().setMargin(Component.BOTTOM, 10);
        card.getStyle().setBgColor(0xFFFFFF);
        
        Label idLabel = new Label("ID: " + ticket.getId_t());
        Label nomTicketLabel = new Label("Nom Ticket: " + ticket.getNom_ticket());
        Label prixLabel = new Label("Prix: " + ticket.getPrix());
        Label statusLabel = new Label("Status: " + ticket.getStatus());
        //Label id_reservationLabel = new Label("id_reservaion: " + ticket.getId_reservation());
      

        card.add(BorderLayout.NORTH, idLabel);
        card.add(BorderLayout.CENTER, BoxLayout.encloseY(nomTicketLabel,prixLabel, statusLabel ));
        //card.add(BorderLayout.CENTER, BoxLayout.encloseY(nomTicketLabel,prixLabel, statusLabel , id_reservationLabel ));

        this.add(card);
             
        Button btnPaiement = new Button("Paiement");
        FontImage.setMaterialIcon(btnPaiement, FontImage.MATERIAL_CREDIT_CARD, 4);
                //FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_CREDIT_CARD, "card icon", 5);

        add(btnPaiement);
        btnPaiement.addActionListener((e) -> {
            Stripe.apiKey = "sk_test_51Mg75HLu7B1VCpQ0mSqMJ2ucVpFhYufToAINJK1T6932bHEpdiBaD6tMbEDEwA5Aa4Fh1b9WBLhXcb02dhZNLM79008NYLc3Cf";
           
                try {
             //Create a customer object for the user who is paying
            Map<String, Object> customerParams = new HashMap<String, Object>();
            customerParams.put("email", "haninbenjemaa@gmail.com");
            Customer payer = Customer.create(customerParams);
            System.out.println(payer.getId()); 
            
//            Customer a = Customer.retrieve("cus_NRSVBhMI96zG1n");
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            System.out.println(gson.toJson(a)); 
               
                //recuperer donne
                
                Map<String, Object> retrieveParams = new HashMap<String, Object>();
		List<String> expandList = new ArrayList<String>();
		expandList.add("sources");
		retrieveParams.put("expand", expandList);
                //add customer id here : it will start with cus_
		Customer customer = Customer.retrieve(payer.getId(), retrieveParams, null); 
                
                //add card details //add this card parameters to token parameters  
		Map<String, Object> cardParam = new HashMap<String, Object>(); 
		cardParam.put("number", "4242424242424242");
		cardParam.put("exp_month", "11");
		cardParam.put("exp_year", "2026");
		cardParam.put("cvc", "123");

		Map<String, Object> tokenParam = new HashMap<String, Object>();
		tokenParam.put("card", cardParam);

		Token token = Token.create(tokenParam); // create a token

		Map<String, Object> source = new HashMap<String, Object>();
                
		source.put("source", token.getId()); //add token as source
                
                // add the customer details to which card is need to link
		Card card1 = (Card)customer.getSources().create(source); 
		String cardDetails = card1.toJson();
		System.out.println("Card Details : " + cardDetails);
		customer = Customer.retrieve(payer.getId());//change the customer id or use to get customer by id.
		System.out.println("After adding card, customer details : " + customer);
                      
               //PaymentMethod paymentMethod = PaymentMethod.create(cardParam);
               
               System.out.println(customer.getId());     
               //Use the payment method to make a charge
               Map<String, Object> chargeParams = new HashMap<String, Object>();
               chargeParams.put("amount", "100");
               chargeParams.put("currency", "usd");
               //chargeParams.put("description", "Example charge");
               //chargeParams.put("source", token.getId());
               chargeParams.put("customer", customer.getId());
               Charge charge = Charge.create(chargeParams);      
       
            System.out.println("Payment successful!");
        } catch (StripeException E) {
            System.out.println("Error: " + E.getMessage());
        }
                
     Dialog.show("Success", "paiement successfully", "OK", null);
     ListTickets refresh = new ListTickets(u,previous);
     refresh.show();

});
          
        
         Button btndelete = new Button("delete");
        FontImage.setMaterialIcon(btndelete, FontImage.MATERIAL_DELETE, 4);
        add(btndelete);
        
        Button updateButton = new Button("Update Ticket");
        updateButton.setMaterialIcon(FontImage.MATERIAL_MODE_EDIT); // set the edit icon
        updateButton.addActionListener(e -> {
        Form updateForm = new Form("Update Ticket");

        TextField nomTicketField = new TextField("", "Nom_ticket");
        TextField prixField = new TextField("", "Prix");
        TextField statusField = new TextField("", "Status");
        //TextField id_reservationField = new TextField("", "id_reservation");
     
    Button submitButton = new Button("Submit");
    submitButton.addActionListener(submitEvent -> {
        // Get the updated values of the fields
       
        String nom_ticket =  nomTicketField.getText();
        String prix = prixField.getText();
        String status = statusField.getText();
        //String id_reservation  = id_reservationField.getText();
    
        // Call the service function to update the station
         Services.ServiceTicket.getInstance().modifierTicket(ticket.getId_t(),nom_ticket,prix,status);
         //Services.ServiceTicket.getInstance().modifierTicket(ticket.getId_t(),nom_ticket,prix,status,id_reservation);
        // Show a confirmation message to the user
        Dialog.show("Success", "Ticket updated successfully", "OK", null);
            ListTickets refresh = new ListTickets(u,previous);
     refresh.show();
    });

    updateForm.add(nomTicketField).add(prixField).add(statusField).add(submitButton);
    updateForm.show();
    
});

add(updateButton);
btndelete.addActionListener((e) -> {
     Services.ServiceTicket.getInstance().suppTicket(ticket);
     Dialog.show("Success", "Ticket deleted successfully", "OK", null);
     ListTickets refresh = new ListTickets(u,previous);
     refresh.show();

});
    }

        
    }

}
