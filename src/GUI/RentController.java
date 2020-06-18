/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Blog;
import Entity.Evenement;
import Entity.Rent;
import com.codename1.components.SpanLabel;
import static com.codename1.io.Log.e;
import com.codename1.ui.TextField;

import com.codename1.main.Controller;
import com.codename1.main.MainView;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

import service.RentService;

/**
 *
 * @author LENOVO
 */
public class RentController extends Controller {

    public RentController()
    {
        super();
    }
    EncodedImage enc ;
    private Resources theme;
    private Container cc ;
        private Container dd ;

    private Label titre;
    private Form f;
    private Button readmore;
    private Button rech1;
    private Button Stat;
    private SpanLabel decouvrir;
    private TextField rech;
        
    @Override
    public void initialize() {
        this.rootContainer.removeAll();
        try {
            SpanLabel lb;
            theme = UIManager.initFirstTheme("/theme");
            titre = new Label();
            RentService serviceTask=new RentService();
            ArrayList<Rent> lis=serviceTask.getList();
            Container parentContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            parentContainer.setScrollableY(true);
              
            enc = EncodedImage.create("/giphy.gif");            
              decouvrir = new SpanLabel();
            decouvrir.setText("Découvrir les Voitures a louer :");
           
                        Container vv = new Container(BoxLayout.x());

            AutoCompleteTextField rech = new AutoCompleteTextField("bmw", "mercedes", "audi", "volkswagen");
             rech.setMinimumElementsShownInPopup(5);
                            rech1=new Button("search");

                            vv.add(rech);
                            vv.add(rech1);
                            
                            
                            rech1.addActionListener((evt) -> {
                            
                    RentService serviceTask1=new RentService();
            ArrayList<Rent> lis1 = serviceTask1.getList3(rech.getText());
                    
                                
                                
                                 for (Rent e1 : lis1)
            {                
               
                cc = new Container();
                titre= new Label();
                
               Image image = URLImage.createToStorage(enc,e1.getImageId(),Controller.ip+"/Pidev-web/web/uploads/"+e1.getImageId());
                cc.add(image);
                 readmore=new Button("Read More");
                readmore.addActionListener((evt1) -> {
                RentDetailController RentDetailController = new RentDetailController();
                 try {
                RentDetailController.initialize(e1);
                this.rootContainer.removeAll();
                this.rootContainer.add(BorderLayout.CENTER,RentDetailController.getView());
                this.rootContainer.revalidate(); } catch (IOException ex) {
                ex.printStackTrace();
                }
                });
                titre.setText(e1.getMarque());
                titre.getAllStyles().setUnderline(true);
                Label tt = new Label(e1.getMarque());
                Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
                xx.add(titre);

                cc.add(xx);
                cc.add(readmore);
                parentContainer.add(cc);   


                     
                }         
                                
                                
            
                                    this.rootContainer.revalidate();       

                            
                            
                            
                            
                            });
                            
                            
               parentContainer.add(decouvrir);
            parentContainer.add(vv);

            for (Rent e : lis)
            {                
                
                cc = new Container();
                titre= new Label();
                
               Image image = URLImage.createToStorage(enc,e.getImageId(),Controller.ip+"/Pidev-web/web/uploads/"+e.getImageId());
                cc.add(image);
                 readmore=new Button("Read More");
                readmore.addActionListener((evt) -> {
                RentDetailController RentDetailController = new RentDetailController();
                 try {
                RentDetailController.initialize(e);
                this.rootContainer.removeAll();
                this.rootContainer.add(BorderLayout.CENTER,RentDetailController.getView());
                this.rootContainer.revalidate(); } catch (IOException ex) {
                ex.printStackTrace();
                }
                });
                titre.setText(e.getMarque());
                titre.getAllStyles().setUnderline(true);
                Label tt = new Label(e.getMarque());
                Container xx = new Container(new BoxLayout(BoxLayout.X_AXIS));
                xx.add(titre);

                cc.add(xx);
                cc.add(readmore);
                parentContainer.add(cc);   

                     
            }            
        this.rootContainer.add(BorderLayout.CENTER,parentContainer);   
        } catch (IOException ex) {
        }        
        this.rootContainer.revalidate();       
    }    
    
    
    
    
}
