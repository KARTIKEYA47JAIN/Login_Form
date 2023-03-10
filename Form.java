/**
 
*/

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.ArrayList;
import java.util.List;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class FrameWork extends Frame implements ActionListener
{
   JLabel Name, RegId, EmailId, ContactNumber; 
   TextField N, RId, EId, CNo;

   //Creaating Buttons
   //Button are Global: As they will be used in more than 1 methods
   Button submit = new Button("Submit");
   Button view = new Button("View");
   Button passButton = new Button("Login");//Button specifically for Password Window
   
   //Password will remain constant forever
   private String password = "oblivion";

   FrameWork()
   {

    //Creating a Frame
    Frame f = new Frame("Form");
    
    
    //Creating Label of Name
    Name =  new JLabel("Name :");
    Name.setFont(new Font("Serif", Font.BOLD, 16));
    Name.setBounds(50, 100, 200, 30);
    //Creating TextField for Name
    N = new TextField();
    N.setBounds(252, 100, 200, 30);
    
    //Creating Label of Registration Number
    RegId =  new JLabel("Registration Number :");
    RegId.setFont(new Font("Sans_Serif", Font.BOLD, 16));
    RegId.setBounds(50, 200, 200, 30);
    //Creating TextField for Registration Number
    RId = new TextField(9);
    RId.setBounds(252, 200, 200, 30);

    //Creating Label of Email-Id
    EmailId =  new JLabel("Email-Id :");
    EmailId.setFont(new Font("Sans_Serif", Font.BOLD, 16));
    EmailId.setBounds(50, 300, 200, 30);
    //Creating TextField for Email-Id
    EId = new TextField();
    EId.setBounds(252, 300, 200, 30);

    //Creating Label of Contact Number
    ContactNumber =  new JLabel("Contact Number :");
    ContactNumber.setFont(new Font("Sans_Serif", Font.BOLD, 16));
    ContactNumber.setBounds(50, 400, 200, 30);
    //Creating TextField for Contact Number
    CNo = new TextField(10);
    CNo.setBounds(252, 400, 200, 30);

    //Editing Buttons
    submit.setFont(new Font("Courier", Font.BOLD, 20));
    submit.setBounds(250, 500, 100, 40);

    view.setFont(new Font("Courier", Font.BOLD, 16));
    view.setBounds(506, 574, 100, 30);

    //Label
    f.add(Name);
    f.add(RegId);
    f.add(EmailId);
    f.add(ContactNumber);
    //TextFiield with Background Color
    f.add(N).setBackground(Color.white);
    f.add(RId).setBackground(Color.white);
    f.add(EId).setBackground(Color.white);
    f.add(CNo).setBackground(Color.white); 
    
    //Button
    submit.setForeground(Color.red);
    //submit.setBackground(Color.black);  
    f.add(submit);
    view.setForeground(Color.red);  
    f.add(view);


    //ACTIONLISTENER task for Submit and View Button
    submit.addActionListener(this);
    view.addActionListener(this);

    //Size of Frame
    f.setBackground(Color.white);
    f.setSize(600,600);
    f.setLayout(null);
    f.setVisible(true);  
   }
    
    //Functioning Of Button
    public void actionPerformed(ActionEvent e) 
    {

        String nam, Reg, Email, Contact, everify;
        int reglen=0, contactlen=0;

        //FRAME for Error 
        Frame f = new Frame();
     
        nam = N.getText(); 
        Reg = RId.getText();
        Email = EId.getText();
        Contact = CNo.getText();

        if (Email.length()>10 && e.getSource()!=view) //Email-Id can never be less than 5
        {
            everify = Email.substring(Email.length()-4,Email.length());

            //Verifying Contact Number, Registration Number
            if(Contact.length()==10 && Reg.length()==9)
            {
                //CONTACT NUMBER And REGISTRATION NUMBER
                for(int i=1;i<=10;i++)
                {
                    for(int j=48;j<=57;j++)
                    {
                        if((int)Contact.charAt(i-1) == j)
                            contactlen++;
                        
                        if(i==10)
                            continue;
                        else
                            if((int)Reg.charAt(i-1) == j)
                                reglen++;
                    }
                }
            }

            //Condtion for Contact Number, Registration Number and Email-Id
            if((contactlen == 10 && reglen==9) && (everify.equals(".com") || everify.equals(".edu")) && nam.length()>3)
            {
                //Creating Fwrite to Write in a File and Writing Directly in A File
                try 
                {
                    try (FileWriter Fwrite = new FileWriter("DataBook.txt",true)) 
                    {
                        // "," is going to act as a delimiter for the file
                        Fwrite.write(nam+",");
                        Fwrite.write(Reg+",");
                        Fwrite.write(Email+",");
                        Fwrite.write(Contact+",");
                        //Not closing the File currently, inorder to take multiple entries
                        //Fwrite.close();
                    }
                } 
                catch (IOException e1) 
                {
                    e1.printStackTrace();
                }
                N.setText("");
                RId.setText("");
                EId.setText("");
                CNo.setText(""); 
            }
            else
            {
                JOptionPane.showMessageDialog
                (
                    f,
                    "Error!!!\nName: Not Valid\nReg: 9 digit number\nEmail: .com /.edu is not present\nContactNo: 10 digit number\nOR\nAll the Fields are not filled!",
                    "Input is not Correct",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        }
        else
        {
            if(e.getSource()!=view && e.getSource()!=passButton) //If statement so that if View Button or passButton is pressed, the below message shall not pop up 
                JOptionPane.showMessageDialog
                (
                    f,
                    "Error!!!\nEmail: Syntax not valid\nOR\nAll the Fields are not filled!!",
                    "Input is not Correct",
                    JOptionPane.WARNING_MESSAGE
                );
        }
        
        
        //VIEW Button Programming
        if(e.getSource()==view)
        {

            //Creating a New Frame i.e. Password Frame for accepting Password
            JPasswordField pf = new JPasswordField(8);   
            JLabel pass=new JLabel("Password:");  
            
            //Editing Label
            pass.setFont(new Font("courrier", Font.BOLD+Font.ITALIC, 14));
            pass.setBounds(20,80, 80,30); 
            //Password Field   
            pf.setBounds(100,80,100,30);    
            
            //Editing Login Button
            passButton.setFont(new Font("Courier", Font.BOLD, 14));
            passButton.setBounds(70, 130, 80, 30);

            //Adding PasswordFiled,Button and Label to the Frame
            f.add(pf).setBackground(Color.white);;  
            f.add(pass);
            f.add(passButton);
            
            //Adding Action to passButton
            //Creating a Completely new Action for Login Button
            passButton.addActionListener(new ActionListener()
            {

                @Override
                public void actionPerformed(ActionEvent e)
                {
                    //Way to deal with Password Field, as Password Field by default needs char[] type but for password we need String Type
                    int flag = 0;//flag variable
                    char[] enter = pf.getPassword();
                    if(enter.length == password.length()) //If length of password matches with the default password
                    {
                        //Checking if the password is correct or not
                        for(int i=0;i<enter.length;i++)
                            if(enter[i] == password.charAt(i))
                                flag = 1;
                    }

                    if(flag==1 && e.getSource()==passButton)
                    {   
                        //Creating a new frame for Displaying Data
                        JFrame jf = new JFrame();
                        JPanel jp = new JPanel();

                        //Tables Heading Labels
                        String [] heading = {"Name", "Registration No.", "Email-Id", "Contact Number"};

                        //Editing the Panel
                        jp.setBounds(10,10,520,500);   

                        //Creating Border for the Panel
                        jp.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Employee data", TitledBorder.CENTER, TitledBorder.TOP));

                        
                        //Printing a New Frame
                        jf.setSize(540,500);    
                        jf.setLayout(null);    
                        jf.setVisible(true); 

                        try 
                        {
                            int row=0;
                            File read = new File("DataBook.txt");
                            Scanner myRead = new Scanner(read);
                            myRead.useDelimiter(",");
                            
                            
                            String str;
                            // arraylist to store strings
                            List<String> listOfStrings = new ArrayList<String>();

                            // checking end of file
                            while (myRead.hasNext()) 
                            {
                                str = myRead.next();
                                // adding each string to arraylist
                                listOfStrings.add(str);
                            }
                        
                            // convert any arraylist to array
                            String[] array = listOfStrings.toArray(new String[0]);

                            row = array.length/4;
                            System.out.println(row);
                            String[][] record = new String[row][4];

                            int c=0;
                            

                            for(int i=0;i<row;i++)  
                            {
                                for(int j=0;j<4;j++)
                                    {
                                        record[i][j] = array[c];
                                        c++;
                                    }
                            }

                            //Creating Table for File
                            JTable table = new JTable(record, heading);

                            //Editing Content of Table
                            table.getTableHeader().setFont(new Font("courrier",Font.BOLD,12));
                            table.setFont(new Font("courrier",Font.ITALIC,12));
                            //Displaying Table
                            jp.add(new JScrollPane(table));
                            jf.add(jp);
                            
                            myRead.close();
                        } 
                        catch (FileNotFoundException read) 
                        {
                           ((Throwable) read).printStackTrace();
                        }
                    } 
                    else
                        JOptionPane.showMessageDialog
                        (
                            f,
                            "File is Empty\nOR\nPassword Incorrect!!",
                            "EMPTY!!!t",
                            JOptionPane.WARNING_MESSAGE
                        );
                }

            }
            );

            //Creating a new frame for Password
            f.setSize(210,160);    
            f.setLayout(null);    
            f.setVisible(true); 
        }
        
    }

}

public class Form
{
    public static void main(String args[]) throws Exception
    {
        //Creating a File to store Data
        try
        {
                File db = new File("# mention the address where the text file will get saved");
                db.createNewFile();
        }
        catch (IOException db)
        {}

        FrameWork fw = new FrameWork();
    }
}
