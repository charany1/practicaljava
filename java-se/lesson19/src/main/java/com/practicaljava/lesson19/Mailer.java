package com.practicaljava.lesson19;

import java.io.*;
import java.text.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mailer	{
    private Session session = null;

    private String emailAddressee;
    private String emailAddress;

    private static  String emailSenderAddress = "test@yahoo.com";
    private static  String emailSubject = "Congratulations with Birthday!!!";
    private String emailText = "Happy birthday dear %s!!!";
    
    ArrayList<String> listOfStrings = new ArrayList<String>();

    Mailer() {
        Properties sessionProperties = new Properties();
        sessionProperties.put("mail.smtp.host", "smtp.mail.yahoo.com");
        sessionProperties.put("mail.smtp.user", emailSenderAddress);
        sessionProperties.put("mail.smtp.auth", "true");
        MyMailAuthenticator authentificatorForMessage = new MyMailAuthenticator();
        session = Session.getInstance(sessionProperties, authentificatorForMessage);
    }

    private void setPropsAndSendEmail(String emailAddressee, String emailText) {
        try{
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress(emailSenderAddress));	
            emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddressee, false));
            emailMessage.setSubject(emailSubject);
            emailMessage.setSentDate(new Date());
            emailMessage.setText(emailText);
            Transport.send(emailMessage);
            System.out.println("Message has been sent successfully");
        }catch(Exception e){
            System.out.println("Message has not been sent: " + e.getMessage());
        }
    }
 
    private void readBirthdayFileToList() throws IOException {
        FileInputStream birthdayFile = new FileInputStream("birthday.txt");
        BufferedReader birthdayFileReader = new BufferedReader(new InputStreamReader(birthdayFile));
        String currentLineOfBirthdayFile;
        while ((currentLineOfBirthdayFile = birthdayFileReader.readLine()) != null){
            listOfStrings.add(currentLineOfBirthdayFile);
        }
        birthdayFileReader.close();
        birthdayFile.close();
    }

 
    private void iterateThrowLinesOfBirthdayFile(){
        Iterator<String> iterator = listOfStrings.iterator(); 
        while (iterator.hasNext()){
            scanForManInfoAndSendEmail(iterator.next());
        }
    }
	
    private void scanForManInfoAndSendEmail(String stringFromArray){
        Scanner scannerOfLines = new Scanner(stringFromArray).useDelimiter("[,\n]");
        if (scannerOfLines.next().equals(getCurrentDateMMMd())) {
            emailAddressee = scannerOfLines.next();
            emailAddress = scannerOfLines.next();
            setPropsAndSendEmail(emailAddress, String.format(emailText, emailAddressee));
        }
    }
	
    private static String getCurrentDateMMMd(){
        return new SimpleDateFormat("MMM-d", Locale.US).format(new GregorianCalendar().getTime());
    }
 
    public static void main(String[] args){	  
        Mailer mm = new Mailer();
        try {
            mm.readBirthdayFileToList();
            mm.iterateThrowLinesOfBirthdayFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
