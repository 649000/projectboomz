/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
import javax.microedition.location.*;
import javax.microedition.io.*;
import javax.microedition.media.*;
import javax.microedition.media.control.*;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Vector;
import javax.microedition.io.file.FileConnection;
//import org.apache.commons.codec.*;

/**
 * @author student
 */
public class AmityMIDlet extends MIDlet implements CommandListener, ItemCommandListener {
    // variables for messaging module

    private String logInMessage = "";
    private String sendMessageURL = "http://172.10.20.2:8080/ProjectAmity/messageMobile/send";
    private String checkMessageURL = "http://172.10.20.2:8080/ProjectAmity/messageMobile/check";
    private String msgSender, msgTo, msgSubject, msgMessage;
    private boolean checkForMessages = false;
    private boolean midletPaused = false;
    private LocationProvider provider = null;
    private String userIDLoggedIn = "";
    private String userPostalCode = "";
    //URLs
    private String loginUserURL = "http://172.10.20.2:8080/ProjectAmity/resident/mLogin";
    private String reportOutdoorURL = "http://172.10.20.2:8080/ProjectAmity/report/saveOutdoor";
    private String reportIndoorURL = "http://172.10.20.2:8080/ProjectAmity/report/saveIndoor";
    private String getPostalcode = "http://172.10.20.2:8080/ProjectAmity/resident/mPostalCode";
    //Need to get building info, level and stairwell
    private String buildingInfoURL = "http://172.10.20.2:8080/ProjectAmity/building/buildingInfo";
    //Server return messages
    private String loginServerMsg = "";
    private String reportOutdoorServerMsg = "";
    private String reportIndoorServerMsg = "";
    private String buildingInfoServerMsg = "";
    //temp variables to store the GPS coordinates
    private double latitude = 1.345390;
    private double longitude = 103.933733;
    private float altitude = 0;
    private byte[] imageByteOutdoor;
    private byte[] imageByteIndoor;
    private String imageName = "";
    private Player mPlayer;
    private VideoControl mVideoControl;
    private Display mDisplay;
    private Date d;
    private Command exitCommand2;
    private Command snapPicCommandIndoor;
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Form loginForm;
    private TextField NRICLoginFormtextField;
    private TextField passwordLoginFormtextField;
    private Form reportMainForm;
    private ChoiceGroup reportMainFormChoiceGroup;
    private Form cameraCaptureForm;
    private StringItem testLocationstringItem;
    private Alert alert;
    private Form outdoorReportForm;
    private TextField outdoorTitletextField;
    private ImageItem outdoorImageItem;
    private TextField outdoorDescriptiontextField;
    private StringItem outdoorCoordinarteStringItem;
    private Form indoorReportForm;
    private TextField indoorTitleTextField;
    private StringItem indoorPostalStringItem;
    private ChoiceGroup indoorLevelChoiceGroup;
    private ImageItem indoorImageItem;
    private TextField indoorDescriptiontextField;
    private ChoiceGroup indoorStairwellChoiceGroup;
    private Form mainMenuForm;
    private ChoiceGroup mainMenuChoiceGroup;
    private Form messageMainForm;
    private ChoiceGroup messageMainChoice;
    private StringItem stringItem;
    private Form messageCreateForm;
    private TextField tbxTo;
    private TextField tbxSubject;
    private TextField tbxMessage;
    private Form messageViewForm;
    private StringItem lblSubject;
    private StringItem lblFrom;
    private StringItem lblFromID;
    private StringItem lblTo;
    private StringItem lblToID;
    private StringItem lblMessage;
    private Command loginCommand;
    private Command exitCommand;
    private Command exitCommand1;
    private Command locationCommand;
    private Command snapPicCommand;
    private Command reportMenuOkCommand;
    private Command indoorReportBbackCommand;
    private Command outdoorReportBackCommand;
    private Command outdoorReportSubmitCommand;
    private Command indoorReportSubmitCommand;
    private Command mainMenuOkCommand;
    private Command outdoorSelectNewCommand;
    private Command outdoorSnapPicCommand;
    private Command reportMenuBackCommand;
    private Command messageMainBackCommand;
    private Command messageMainNextCommand;
    private Command messageCreateBackCommand;
    private Command messageCreateSendCommand;
    private Command messageViewBackCommand;
    private Command messageViewReplyCommand;
    private Command mainMenuBackCommand;
    //</editor-fold>//GEN-END:|fields|0|

    /**
     * The AmityMIDlet constructor.
     */
    public AmityMIDlet() {
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Methods ">//GEN-BEGIN:|methods|0|
    //</editor-fold>//GEN-END:|methods|0|
    //<editor-fold defaultstate="collapsed" desc=" Generated Method: initialize ">//GEN-BEGIN:|0-initialize|0|0-preInitialize
    /**
     * Initilizes the application.
     * It is called only once when the MIDlet is started. The method is called before the <code>startMIDlet</code> method.
     */
    private void initialize() {//GEN-END:|0-initialize|0|0-preInitialize
        // write pre-initialize user code here
//GEN-LINE:|0-initialize|1|0-postInitialize
        // write post-initialize user code here
    }//GEN-BEGIN:|0-initialize|2|
    //</editor-fold>//GEN-END:|0-initialize|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: startMIDlet ">//GEN-BEGIN:|3-startMIDlet|0|3-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Started point.
     */
    public void startMIDlet() {//GEN-END:|3-startMIDlet|0|3-preAction
        // write pre-action user code here
        d = new Date();
        //Initialize mDisplay
        mDisplay = Display.getDisplay(this);
        switchDisplayable(null, getLoginForm());//GEN-LINE:|3-startMIDlet|1|3-postAction
        // write post-action user code here

        try {
            provider = LocationProvider.getInstance(null);
        } catch (Exception e) {
        }
    }//GEN-BEGIN:|3-startMIDlet|2|
    //</editor-fold>//GEN-END:|3-startMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: resumeMIDlet ">//GEN-BEGIN:|4-resumeMIDlet|0|4-preAction
    /**
     * Performs an action assigned to the Mobile Device - MIDlet Resumed point.
     */
    public void resumeMIDlet() {//GEN-END:|4-resumeMIDlet|0|4-preAction
        // write pre-action user code here
//GEN-LINE:|4-resumeMIDlet|1|4-postAction
        // write post-action user code here
    }//GEN-BEGIN:|4-resumeMIDlet|2|
    //</editor-fold>//GEN-END:|4-resumeMIDlet|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: switchDisplayable ">//GEN-BEGIN:|5-switchDisplayable|0|5-preSwitch
    /**
     * Switches a current displayable in a display. The <code>display</code> instance is taken from <code>getDisplay</code> method. This method is used by all actions in the design for switching displayable.
     * @param alert the Alert which is temporarily set to the display; if <code>null</code>, then <code>nextDisplayable</code> is set immediately
     * @param nextDisplayable the Displayable to be set
     */
    public void switchDisplayable(Alert alert, Displayable nextDisplayable) {//GEN-END:|5-switchDisplayable|0|5-preSwitch
        // write pre-switch user code here
        Display display = getDisplay();//GEN-BEGIN:|5-switchDisplayable|1|5-postSwitch
        if (alert == null) {
            display.setCurrent(nextDisplayable);
        } else {
            display.setCurrent(alert, nextDisplayable);
        }//GEN-END:|5-switchDisplayable|1|5-postSwitch
        // write post-switch user code here
    }//GEN-BEGIN:|5-switchDisplayable|2|
    //</editor-fold>//GEN-END:|5-switchDisplayable|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Displayables ">//GEN-BEGIN:|7-commandAction|0|7-preCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular displayable.
     * @param command the Command that was invoked
     * @param displayable the Displayable where the command was invoked
     */
    public void commandAction(Command command, Displayable displayable) {//GEN-END:|7-commandAction|0|7-preCommandAction
        // write pre-action user code here
        if (displayable == cameraCaptureForm) {//GEN-BEGIN:|7-commandAction|1|33-preAction
            if (command == locationCommand) {//GEN-END:|7-commandAction|1|33-preAction
                // write pre-action user code here
                getLocation();
//GEN-LINE:|7-commandAction|2|33-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|3|52-preAction
        } else if (displayable == indoorReportForm) {
            if (command == indoorReportBbackCommand) {//GEN-END:|7-commandAction|3|52-preAction
                // write pre-action user code here
                switchDisplayable(null, getReportMainForm());//GEN-LINE:|7-commandAction|4|52-postAction
                // write post-action user code here
            } else if (command == indoorReportSubmitCommand) {//GEN-LINE:|7-commandAction|5|58-preAction
                // write pre-action user code here
                if (!indoorTitleTextField.getString().equals("") && !indoorDescriptiontextField.getString().equals("")) { //&& indoorImageItem.getImage() != null) {
                    reportSubmitIndoor();
                } else {

                    System.out.println("Empty Fields Detected");
                    alert = new Alert("Error", "Invalid Entry. \nEmpty Fields detected", null, AlertType.ERROR);
                    alert.setTimeout(2000); //Timeout in 2 seconds
                    switchDisplayable(alert, getIndoorReportForm());
                }

//GEN-LINE:|7-commandAction|6|58-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|21-preAction
        } else if (displayable == loginForm) {
            if (command == exitCommand) {//GEN-END:|7-commandAction|7|21-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|8|21-postAction
                // write post-action user code here
            } else if (command == loginCommand) {//GEN-LINE:|7-commandAction|9|18-preAction
                // write pre-action user code here
                System.out.println("NRIC: " + NRICLoginFormtextField.getString());
                System.out.println("Password: " + passwordLoginFormtextField.getString());
                if (!NRICLoginFormtextField.getString().equals("") && !passwordLoginFormtextField.getString().equals("")) {
                    loginAccount();
                } else {

                    System.out.println("Empty Fields Detected");
                    alert = new Alert("Error", "Username or Password cannot be blank.", null, AlertType.ERROR);
                    alert.setTimeout(2000); //Timeout in 2 seconds
                    switchDisplayable(alert, getLoginForm());
                }

//GEN-LINE:|7-commandAction|10|18-postAction
                // write post-action user code here

            }//GEN-BEGIN:|7-commandAction|11|113-preAction
        } else if (displayable == mainMenuForm) {
            if (command == mainMenuBackCommand) {//GEN-END:|7-commandAction|11|113-preAction
                // write pre-action user code here
                userIDLoggedIn = "";
                switchDisplayable(null, getLoginForm());//GEN-LINE:|7-commandAction|12|113-postAction
                // write post-action user code here

            } else if (command == mainMenuOkCommand) {//GEN-LINE:|7-commandAction|13|61-preAction
                // write pre-action user code here
                if (mainMenuChoiceGroup.isSelected(0)) {
                    System.out.println("User has selected private messaging module");

                    checkForMessages = true;
                    checkForMessages();
                    switchDisplayable(null, getMessageMainForm());
                } else if (mainMenuChoiceGroup.isSelected(1)) {
                    System.out.println("User has selected reporting module");
                    switchDisplayable(null, getReportMainForm());
                } else {
                    System.out.println("User did not make a selection");
                    alert = new Alert("Error", "Invalid selection", null, AlertType.ERROR);
                    alert.setTimeout(2000); //Timeout in 2 seconds
                    switchDisplayable(alert, getMainMenuForm());
                }



//GEN-LINE:|7-commandAction|14|61-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|15|93-preAction
        } else if (displayable == messageCreateForm) {
            if (command == messageCreateBackCommand) {//GEN-END:|7-commandAction|15|93-preAction
                // write pre-action user code here
                switchDisplayable(null, getMessageMainForm());//GEN-LINE:|7-commandAction|16|93-postAction
                // write post-action user code here
            } else if (command == messageCreateSendCommand) {//GEN-LINE:|7-commandAction|17|95-preAction
                // write pre-action user code here

                // userIDLoggedIn = "tampines5981";
                msgSender = userIDLoggedIn;
                msgTo = tbxTo.getString();
                msgSubject = tbxSubject.getString();
                msgMessage = tbxMessage.getString();
                sendMessage();

//GEN-LINE:|7-commandAction|18|95-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|19|83-preAction
        } else if (displayable == messageMainForm) {
            if (command == messageMainBackCommand) {//GEN-END:|7-commandAction|19|83-preAction
                // write pre-action user code here
                checkForMessages = false;
                switchDisplayable(null, getMainMenuForm());//GEN-LINE:|7-commandAction|20|83-postAction
                // write post-action user code here
            } else if (command == messageMainNextCommand) {//GEN-LINE:|7-commandAction|21|85-preAction
                // write pre-action user code here
                if (messageMainChoice.isSelected(0)) {
                    System.out.println("User wants to compose new message");
                    switchDisplayable(null, getMessageCreateForm());
                } else if (messageMainChoice.isSelected(1)) {
                    System.out.println("User wants to check for messages");
                    checkForMessagesOnce();
                } else {
                    System.out.println("User did not make a selection");
                    alert = new Alert("Error", "Invalid selection", null, AlertType.ERROR);
                    alert.setTimeout(2000); //Timeout in 2 seconds
                    switchDisplayable(alert, getMainMenuForm());
                }

//GEN-LINE:|7-commandAction|22|85-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|23|102-preAction
        } else if (displayable == messageViewForm) {
            if (command == messageViewBackCommand) {//GEN-END:|7-commandAction|23|102-preAction
                // write pre-action user code here
                switchDisplayable(null, getMessageMainForm());//GEN-LINE:|7-commandAction|24|102-postAction
                // write post-action user code here
            } else if (command == messageViewReplyCommand) {//GEN-LINE:|7-commandAction|25|105-preAction
                // write pre-action user code here
                System.out.println("User wants to reply to message");
                getTbxTo().setString(lblFromID.getText());
                getTbxSubject().setString("Re: " + lblSubject.getText());
                switchDisplayable(null, getMessageCreateForm());
//GEN-LINE:|7-commandAction|26|105-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|27|50-preAction
        } else if (displayable == outdoorReportForm) {
            if (command == outdoorReportBackCommand) {//GEN-END:|7-commandAction|27|50-preAction
                // write pre-action user code here
                switchDisplayable(null, getReportMainForm());//GEN-LINE:|7-commandAction|28|50-postAction
                // write post-action user code here
            } else if (command == outdoorReportSubmitCommand) {//GEN-LINE:|7-commandAction|29|56-preAction
                if (!outdoorTitletextField.getString().equals("") && !outdoorDescriptiontextField.getString().equals("")) { //&& latitude != 0.0 && longitude != 0.0&& outdoorImageItem.getImage() != null) {
                    reportSubmitOutdoor();
                } else {

                    System.out.println("Empty Fields Detected");
                    alert = new Alert("Error", "Invalid Entry. \nEmpty Fields detected and/or Invalid GPS Coordinates.", null, AlertType.ERROR);
                    alert.setTimeout(2000); //Timeout in 2 seconds
                    switchDisplayable(alert, getOutdoorReportForm());
                }


//GEN-LINE:|7-commandAction|30|56-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|31|77-preAction
        } else if (displayable == reportMainForm) {
            if (command == reportMenuBackCommand) {//GEN-END:|7-commandAction|31|77-preAction
                // write pre-action user code here
                switchDisplayable(null, getMainMenuForm());//GEN-LINE:|7-commandAction|32|77-postAction
                // write post-action user code here
            } else if (command == reportMenuOkCommand) {//GEN-LINE:|7-commandAction|33|48-preAction
                // write pre-action user code here
                if (reportMainFormChoiceGroup.getSelectedIndex() == 0) {

//                    if (outdoorImageItem.getImage() != null & indoorImageItem.getImage() != null) {
//                        outdoorImageItem.setImage(null);
//                        indoorImageItem.setImage(null);
//                    }
                    System.out.println("Before getting Location");
                    //  getLocation();
                    switchDisplayable(null, getOutdoorReportForm());
                    outdoorCoordinarteStringItem.setText("Latitude: " + latitude + " Longitude: " + longitude + " Altitude: " + altitude);

                } else if (reportMainFormChoiceGroup.getSelectedIndex() == 1) {
//                    if (outdoorImageItem.getImage() != null & indoorImageItem.getImage() != null) {
//                        outdoorImageItem.setImage(null);
//                        indoorImageItem.setImage(null);

//                    }
                    getUserPostalCode();

                }
//                 else {
//                    System.out.println("User did not make a selection");
//                    alert = new Alert("Error", "Invalid selection", null, AlertType.ERROR);
//                    alert.setTimeout(2000); //Timeout in 2 seconds
//                    switchDisplayable(alert, getMainMenuForm());
//                }

//GEN-LINE:|7-commandAction|34|48-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|35|7-postCommandAction
        }//GEN-END:|7-commandAction|35|7-postCommandAction
 // write post-action user code here
        else if (command == snapPicCommand) {
            captureOutdoor();

        } else if (command == snapPicCommandIndoor) {
            captureIndoor();
        } else if (command == exitCommand1) {
            switchDisplayable(null, getOutdoorReportForm());
        } else if (command == exitCommand2) {
            switchDisplayable(null, getIndoorReportForm());
        }
    }//GEN-BEGIN:|7-commandAction|36|
    //</editor-fold>//GEN-END:|7-commandAction|36|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: loginForm ">//GEN-BEGIN:|14-getter|0|14-preInit
    /**
     * Returns an initiliazed instance of loginForm component.
     * @return the initialized component instance
     */
    public Form getLoginForm() {
        if (loginForm == null) {//GEN-END:|14-getter|0|14-preInit
            // write pre-init user code here
            loginForm = new Form("Project Amity", new Item[] { getNRICLoginFormtextField(), getPasswordLoginFormtextField() });//GEN-BEGIN:|14-getter|1|14-postInit
            loginForm.addCommand(getLoginCommand());
            loginForm.addCommand(getExitCommand());
            loginForm.setCommandListener(this);//GEN-END:|14-getter|1|14-postInit
            // write post-init user code here
        }//GEN-BEGIN:|14-getter|2|
        return loginForm;
    }
    //</editor-fold>//GEN-END:|14-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: NRICLoginFormtextField ">//GEN-BEGIN:|23-getter|0|23-preInit
    /**
     * Returns an initiliazed instance of NRICLoginFormtextField component.
     * @return the initialized component instance
     */
    public TextField getNRICLoginFormtextField() {
        if (NRICLoginFormtextField == null) {//GEN-END:|23-getter|0|23-preInit
            // write pre-init user code here
            NRICLoginFormtextField = new TextField("NRIC:", null, 32, TextField.ANY);//GEN-LINE:|23-getter|1|23-postInit
            // write post-init user code here
        }//GEN-BEGIN:|23-getter|2|
        return NRICLoginFormtextField;
    }
    //</editor-fold>//GEN-END:|23-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: reportMainForm ">//GEN-BEGIN:|15-getter|0|15-preInit
    /**
     * Returns an initiliazed instance of reportMainForm component.
     * @return the initialized component instance
     */
    public Form getReportMainForm() {
        if (reportMainForm == null) {//GEN-END:|15-getter|0|15-preInit
            // write pre-init user code here
            reportMainForm = new Form("Location Based Report", new Item[] { getReportMainFormChoiceGroup() });//GEN-BEGIN:|15-getter|1|15-postInit
            reportMainForm.addCommand(getReportMenuOkCommand());
            reportMainForm.addCommand(getReportMenuBackCommand());
            reportMainForm.setCommandListener(this);//GEN-END:|15-getter|1|15-postInit
            // write post-init user code here
        }//GEN-BEGIN:|15-getter|2|
        return reportMainForm;
    }
    //</editor-fold>//GEN-END:|15-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: loginCommand ">//GEN-BEGIN:|17-getter|0|17-preInit
    /**
     * Returns an initiliazed instance of loginCommand component.
     * @return the initialized component instance
     */
    public Command getLoginCommand() {
        if (loginCommand == null) {//GEN-END:|17-getter|0|17-preInit
            // write pre-init user code here
            loginCommand = new Command("Login", Command.OK, 0);//GEN-LINE:|17-getter|1|17-postInit
            // write post-init user code here
        }//GEN-BEGIN:|17-getter|2|
        return loginCommand;
    }
    //</editor-fold>//GEN-END:|17-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand ">//GEN-BEGIN:|20-getter|0|20-preInit
    /**
     * Returns an initiliazed instance of exitCommand component.
     * @return the initialized component instance
     */
    public Command getExitCommand() {
        if (exitCommand == null) {//GEN-END:|20-getter|0|20-preInit
            // write pre-init user code here
            exitCommand = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|20-getter|1|20-postInit
            // write post-init user code here
        }//GEN-BEGIN:|20-getter|2|
        return exitCommand;
    }
    //</editor-fold>//GEN-END:|20-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: passwordLoginFormtextField ">//GEN-BEGIN:|24-getter|0|24-preInit
    /**
     * Returns an initiliazed instance of passwordLoginFormtextField component.
     * @return the initialized component instance
     */
    public TextField getPasswordLoginFormtextField() {
        if (passwordLoginFormtextField == null) {//GEN-END:|24-getter|0|24-preInit
            // write pre-init user code here
            passwordLoginFormtextField = new TextField("Password:", null, 32, TextField.ANY | TextField.PASSWORD);//GEN-LINE:|24-getter|1|24-postInit
            // write post-init user code here
        }//GEN-BEGIN:|24-getter|2|
        return passwordLoginFormtextField;
    }
    //</editor-fold>//GEN-END:|24-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: cameraCaptureForm ">//GEN-BEGIN:|25-getter|0|25-preInit
    /**
     * Returns an initiliazed instance of cameraCaptureForm component.
     * @return the initialized component instance
     */
    public Form getCameraCaptureForm() {
        if (cameraCaptureForm == null) {//GEN-END:|25-getter|0|25-preInit
            // write pre-init user code here
            cameraCaptureForm = new Form("Testing Purposes Form", new Item[] { getTestLocationstringItem() });//GEN-BEGIN:|25-getter|1|25-postInit
            cameraCaptureForm.addCommand(getLocationCommand());
            cameraCaptureForm.setCommandListener(this);//GEN-END:|25-getter|1|25-postInit
            // write post-init user code here
        }//GEN-BEGIN:|25-getter|2|
        return cameraCaptureForm;
    }
    //</editor-fold>//GEN-END:|25-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: exitCommand1 ">//GEN-BEGIN:|26-getter|0|26-preInit
    /**
     * Returns an initiliazed instance of exitCommand1 component.
     * @return the initialized component instance
     */
    public Command getExitCommand1() {
        if (exitCommand1 == null) {//GEN-END:|26-getter|0|26-preInit
            // write pre-init user code here
            exitCommand1 = new Command("Exit", Command.EXIT, 0);//GEN-LINE:|26-getter|1|26-postInit
            // write post-init user code here
        }//GEN-BEGIN:|26-getter|2|
        return exitCommand1;
    }
    //</editor-fold>//GEN-END:|26-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: testLocationstringItem ">//GEN-BEGIN:|31-getter|0|31-preInit
    /**
     * Returns an initiliazed instance of testLocationstringItem component.
     * @return the initialized component instance
     */
    public StringItem getTestLocationstringItem() {
        if (testLocationstringItem == null) {//GEN-END:|31-getter|0|31-preInit
            // write pre-init user code here
            testLocationstringItem = new StringItem("Current Location:", null);//GEN-LINE:|31-getter|1|31-postInit
            // write post-init user code here
        }//GEN-BEGIN:|31-getter|2|
        return testLocationstringItem;
    }
    //</editor-fold>//GEN-END:|31-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: locationCommand ">//GEN-BEGIN:|32-getter|0|32-preInit
    /**
     * Returns an initiliazed instance of locationCommand component.
     * @return the initialized component instance
     */
    public Command getLocationCommand() {
        if (locationCommand == null) {//GEN-END:|32-getter|0|32-preInit
            // write pre-init user code here
            locationCommand = new Command("Get Location", Command.OK, 0);//GEN-LINE:|32-getter|1|32-postInit
            // write post-init user code here
        }//GEN-BEGIN:|32-getter|2|
        return locationCommand;
    }
    //</editor-fold>//GEN-END:|32-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: alert ">//GEN-BEGIN:|34-getter|0|34-preInit
    /**
     * Returns an initiliazed instance of alert component.
     * @return the initialized component instance
     */
    public Alert getAlert() {
        if (alert == null) {//GEN-END:|34-getter|0|34-preInit
            // write pre-init user code here
            alert = new Alert("alert");//GEN-BEGIN:|34-getter|1|34-postInit
            alert.setTimeout(Alert.FOREVER);//GEN-END:|34-getter|1|34-postInit
            // write post-init user code here
        }//GEN-BEGIN:|34-getter|2|
        return alert;
    }
    //</editor-fold>//GEN-END:|34-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: snapPicCommand ">//GEN-BEGIN:|36-getter|0|36-preInit
    /**
     * Returns an initiliazed instance of snapPicCommand component.
     * @return the initialized component instance
     */
    public Command getSnapPicCommand() {
        if (snapPicCommand == null) {//GEN-END:|36-getter|0|36-preInit
            // write pre-init user code here
            snapPicCommand = new Command("Snap Picture", Command.SCREEN, 0);//GEN-LINE:|36-getter|1|36-postInit
            // write post-init user code here
        }//GEN-BEGIN:|36-getter|2|
        return snapPicCommand;
    }
    //</editor-fold>//GEN-END:|36-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: outdoorReportForm ">//GEN-BEGIN:|38-getter|0|38-preInit
    /**
     * Returns an initiliazed instance of outdoorReportForm component.
     * @return the initialized component instance
     */
    public Form getOutdoorReportForm() {
        if (outdoorReportForm == null) {//GEN-END:|38-getter|0|38-preInit
            // write pre-init user code here
            outdoorReportForm = new Form("Outdoor Reports", new Item[] { getOutdoorTitletextField(), getOutdoorImageItem(), getOutdoorDescriptiontextField(), getOutdoorCoordinarteStringItem() });//GEN-BEGIN:|38-getter|1|38-postInit
            outdoorReportForm.addCommand(getOutdoorReportBackCommand());
            outdoorReportForm.addCommand(getOutdoorReportSubmitCommand());
            outdoorReportForm.setCommandListener(this);//GEN-END:|38-getter|1|38-postInit
            // write post-init user code here
        }//GEN-BEGIN:|38-getter|2|
        return outdoorReportForm;
    }
    //</editor-fold>//GEN-END:|38-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: indoorReportForm ">//GEN-BEGIN:|39-getter|0|39-preInit
    /**
     * Returns an initiliazed instance of indoorReportForm component.
     * @return the initialized component instance
     */
    public Form getIndoorReportForm() {
        if (indoorReportForm == null) {//GEN-END:|39-getter|0|39-preInit
            // write pre-init user code here
            indoorReportForm = new Form("Indoor Report", new Item[] { getIndoorPostalStringItem(), getIndoorLevelChoiceGroup(), getIndoorStairwellChoiceGroup(), getIndoorTitleTextField(), getIndoorImageItem(), getIndoorDescriptiontextField() });//GEN-BEGIN:|39-getter|1|39-postInit
            indoorReportForm.addCommand(getIndoorReportBbackCommand());
            indoorReportForm.addCommand(getIndoorReportSubmitCommand());
            indoorReportForm.setCommandListener(this);//GEN-END:|39-getter|1|39-postInit
            // write post-init user code here
        }//GEN-BEGIN:|39-getter|2|
        return indoorReportForm;
    }
    //</editor-fold>//GEN-END:|39-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: mainMenuForm ">//GEN-BEGIN:|40-getter|0|40-preInit
    /**
     * Returns an initiliazed instance of mainMenuForm component.
     * @return the initialized component instance
     */
    public Form getMainMenuForm() {
        if (mainMenuForm == null) {//GEN-END:|40-getter|0|40-preInit
            // write pre-init user code here
            mainMenuForm = new Form("Project Amity", new Item[] { getMainMenuChoiceGroup() });//GEN-BEGIN:|40-getter|1|40-postInit
            mainMenuForm.addCommand(getMainMenuOkCommand());
            mainMenuForm.addCommand(getMainMenuBackCommand());
            mainMenuForm.setCommandListener(this);//GEN-END:|40-getter|1|40-postInit
            // write post-init user code here
        }//GEN-BEGIN:|40-getter|2|
        return mainMenuForm;
    }
    //</editor-fold>//GEN-END:|40-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: mainMenuChoiceGroup ">//GEN-BEGIN:|41-getter|0|41-preInit
    /**
     * Returns an initiliazed instance of mainMenuChoiceGroup component.
     * @return the initialized component instance
     */
    public ChoiceGroup getMainMenuChoiceGroup() {
        if (mainMenuChoiceGroup == null) {//GEN-END:|41-getter|0|41-preInit
            // write pre-init user code here
            mainMenuChoiceGroup = new ChoiceGroup("Choose or Die:", Choice.EXCLUSIVE);//GEN-BEGIN:|41-getter|1|41-postInit
            mainMenuChoiceGroup.append("Private Messaging", null);
            mainMenuChoiceGroup.append("Location Based Report", null);
            mainMenuChoiceGroup.setSelectedFlags(new boolean[] { false, false });//GEN-END:|41-getter|1|41-postInit
            // write post-init user code here
        }//GEN-BEGIN:|41-getter|2|
        return mainMenuChoiceGroup;
    }
    //</editor-fold>//GEN-END:|41-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: reportMainFormChoiceGroup ">//GEN-BEGIN:|44-getter|0|44-preInit
    /**
     * Returns an initiliazed instance of reportMainFormChoiceGroup component.
     * @return the initialized component instance
     */
    public ChoiceGroup getReportMainFormChoiceGroup() {
        if (reportMainFormChoiceGroup == null) {//GEN-END:|44-getter|0|44-preInit
            // write pre-init user code here
            reportMainFormChoiceGroup = new ChoiceGroup("For real, Choose or Die:", Choice.EXCLUSIVE);//GEN-BEGIN:|44-getter|1|44-postInit
            reportMainFormChoiceGroup.append("Outdoor Reporting", null);
            reportMainFormChoiceGroup.append("Indoor Reporting", null);
            reportMainFormChoiceGroup.setFitPolicy(Choice.TEXT_WRAP_DEFAULT);
            reportMainFormChoiceGroup.setSelectedFlags(new boolean[] { false, false });//GEN-END:|44-getter|1|44-postInit
            // write post-init user code here
        }//GEN-BEGIN:|44-getter|2|
        return reportMainFormChoiceGroup;
    }
    //</editor-fold>//GEN-END:|44-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: reportMenuOkCommand ">//GEN-BEGIN:|47-getter|0|47-preInit
    /**
     * Returns an initiliazed instance of reportMenuOkCommand component.
     * @return the initialized component instance
     */
    public Command getReportMenuOkCommand() {
        if (reportMenuOkCommand == null) {//GEN-END:|47-getter|0|47-preInit
            // write pre-init user code here
            reportMenuOkCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|47-getter|1|47-postInit
            // write post-init user code here
        }//GEN-BEGIN:|47-getter|2|
        return reportMenuOkCommand;
    }
    //</editor-fold>//GEN-END:|47-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: outdoorReportBackCommand ">//GEN-BEGIN:|49-getter|0|49-preInit
    /**
     * Returns an initiliazed instance of outdoorReportBackCommand component.
     * @return the initialized component instance
     */
    public Command getOutdoorReportBackCommand() {
        if (outdoorReportBackCommand == null) {//GEN-END:|49-getter|0|49-preInit
            // write pre-init user code here
            outdoorReportBackCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|49-getter|1|49-postInit
            // write post-init user code here
        }//GEN-BEGIN:|49-getter|2|
        return outdoorReportBackCommand;
    }
    //</editor-fold>//GEN-END:|49-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: indoorReportBbackCommand ">//GEN-BEGIN:|51-getter|0|51-preInit
    /**
     * Returns an initiliazed instance of indoorReportBbackCommand component.
     * @return the initialized component instance
     */
    public Command getIndoorReportBbackCommand() {
        if (indoorReportBbackCommand == null) {//GEN-END:|51-getter|0|51-preInit
            // write pre-init user code here
            indoorReportBbackCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|51-getter|1|51-postInit
            // write post-init user code here
        }//GEN-BEGIN:|51-getter|2|
        return indoorReportBbackCommand;
    }
    //</editor-fold>//GEN-END:|51-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: outdoorReportSubmitCommand ">//GEN-BEGIN:|55-getter|0|55-preInit
    /**
     * Returns an initiliazed instance of outdoorReportSubmitCommand component.
     * @return the initialized component instance
     */
    public Command getOutdoorReportSubmitCommand() {
        if (outdoorReportSubmitCommand == null) {//GEN-END:|55-getter|0|55-preInit
            // write pre-init user code here
            outdoorReportSubmitCommand = new Command("Submit", Command.OK, 0);//GEN-LINE:|55-getter|1|55-postInit
            // write post-init user code here
        }//GEN-BEGIN:|55-getter|2|
        return outdoorReportSubmitCommand;
    }
    //</editor-fold>//GEN-END:|55-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: indoorReportSubmitCommand ">//GEN-BEGIN:|57-getter|0|57-preInit
    /**
     * Returns an initiliazed instance of indoorReportSubmitCommand component.
     * @return the initialized component instance
     */
    public Command getIndoorReportSubmitCommand() {
        if (indoorReportSubmitCommand == null) {//GEN-END:|57-getter|0|57-preInit
            // write pre-init user code here
            indoorReportSubmitCommand = new Command("Submit", "Submit", Command.OK, 0);//GEN-LINE:|57-getter|1|57-postInit
            // write post-init user code here
        }//GEN-BEGIN:|57-getter|2|
        return indoorReportSubmitCommand;
    }
    //</editor-fold>//GEN-END:|57-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: mainMenuOkCommand ">//GEN-BEGIN:|60-getter|0|60-preInit
    /**
     * Returns an initiliazed instance of mainMenuOkCommand component.
     * @return the initialized component instance
     */
    public Command getMainMenuOkCommand() {
        if (mainMenuOkCommand == null) {//GEN-END:|60-getter|0|60-preInit
            // write pre-init user code here
            mainMenuOkCommand = new Command("Ok", Command.OK, 0);//GEN-LINE:|60-getter|1|60-postInit
            // write post-init user code here
        }//GEN-BEGIN:|60-getter|2|
        return mainMenuOkCommand;
    }
    //</editor-fold>//GEN-END:|60-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: outdoorTitletextField ">//GEN-BEGIN:|62-getter|0|62-preInit
    /**
     * Returns an initiliazed instance of outdoorTitletextField component.
     * @return the initialized component instance
     */
    public TextField getOutdoorTitletextField() {
        if (outdoorTitletextField == null) {//GEN-END:|62-getter|0|62-preInit
            // write pre-init user code here
            outdoorTitletextField = new TextField("Title:", null, 32, TextField.ANY);//GEN-LINE:|62-getter|1|62-postInit
            // write post-init user code here
        }//GEN-BEGIN:|62-getter|2|
        return outdoorTitletextField;
    }
    //</editor-fold>//GEN-END:|62-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: indoorTitleTextField ">//GEN-BEGIN:|63-getter|0|63-preInit
    /**
     * Returns an initiliazed instance of indoorTitleTextField component.
     * @return the initialized component instance
     */
    public TextField getIndoorTitleTextField() {
        if (indoorTitleTextField == null) {//GEN-END:|63-getter|0|63-preInit
            // write pre-init user code here
            indoorTitleTextField = new TextField("Title:", null, 32, TextField.ANY);//GEN-LINE:|63-getter|1|63-postInit
            // write post-init user code here
        }//GEN-BEGIN:|63-getter|2|
        return indoorTitleTextField;
    }
    //</editor-fold>//GEN-END:|63-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: outdoorImageItem ">//GEN-BEGIN:|64-getter|0|64-preInit
    /**
     * Returns an initiliazed instance of outdoorImageItem component.
     * @return the initialized component instance
     */
    public ImageItem getOutdoorImageItem() {
        if (outdoorImageItem == null) {//GEN-END:|64-getter|0|64-preInit
            // write pre-init user code here
            outdoorImageItem = new ImageItem("Image:", null, ImageItem.LAYOUT_CENTER | Item.LAYOUT_TOP | Item.LAYOUT_BOTTOM | Item.LAYOUT_VCENTER | ImageItem.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_VEXPAND, "<Missing Image>");//GEN-BEGIN:|64-getter|1|64-postInit
            outdoorImageItem.addCommand(getOutdoorSnapPicCommand());
            outdoorImageItem.setItemCommandListener(this);//GEN-END:|64-getter|1|64-postInit
            // write post-init user code here
        }//GEN-BEGIN:|64-getter|2|
        return outdoorImageItem;
    }
    //</editor-fold>//GEN-END:|64-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Method: commandAction for Items ">//GEN-BEGIN:|8-itemCommandAction|0|8-preItemCommandAction
    /**
     * Called by a system to indicated that a command has been invoked on a particular item.
     * @param command the Command that was invoked
     * @param displayable the Item where the command was invoked
     */
    public void commandAction(Command command, Item item) {//GEN-END:|8-itemCommandAction|0|8-preItemCommandAction
        // write pre-action user code here
        if (item == outdoorImageItem) {//GEN-BEGIN:|8-itemCommandAction|1|68-preAction
            if (command == outdoorSnapPicCommand) {//GEN-END:|8-itemCommandAction|1|68-preAction
                // write pre-action user code here
                showCameraOutdoor();
//GEN-LINE:|8-itemCommandAction|2|68-postAction
                // write post-action user code here
            }//GEN-BEGIN:|8-itemCommandAction|3|8-postItemCommandAction
        }//GEN-END:|8-itemCommandAction|3|8-postItemCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|8-itemCommandAction|4|68-postAction
    //</editor-fold>//GEN-END:|8-itemCommandAction|4|68-postAction


    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: outdoorSelectNewCommand ">//GEN-BEGIN:|65-getter|0|65-preInit
    /**
     * Returns an initiliazed instance of outdoorSelectNewCommand component.
     * @return the initialized component instance
     */
    public Command getOutdoorSelectNewCommand() {
        if (outdoorSelectNewCommand == null) {//GEN-END:|65-getter|0|65-preInit
            // write pre-init user code here
            outdoorSelectNewCommand = new Command("Ok", Command.OK, 1);//GEN-LINE:|65-getter|1|65-postInit
            // write post-init user code here
        }//GEN-BEGIN:|65-getter|2|
        return outdoorSelectNewCommand;
    }
    //</editor-fold>//GEN-END:|65-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: outdoorSnapPicCommand ">//GEN-BEGIN:|67-getter|0|67-preInit
    /**
     * Returns an initiliazed instance of outdoorSnapPicCommand component.
     * @return the initialized component instance
     */
    public Command getOutdoorSnapPicCommand() {
        if (outdoorSnapPicCommand == null) {//GEN-END:|67-getter|0|67-preInit
            // write pre-init user code here
            outdoorSnapPicCommand = new Command("Snap Picture", Command.OK, 2);//GEN-LINE:|67-getter|1|67-postInit
            // write post-init user code here
        }//GEN-BEGIN:|67-getter|2|
        return outdoorSnapPicCommand;
    }
    //</editor-fold>//GEN-END:|67-getter|2|
    //</editor-fold>
    //</editor-fold>

    public Command getExitCommand2() {
        if (exitCommand2 == null) {
            // write pre-init user code here
            exitCommand2 = new Command("Exit", Command.EXIT, 0);
            // write post-init user code here

        }
        return exitCommand2;
    }

    public Command snapPicCommandIndoor() {
        if (snapPicCommandIndoor == null) {
            // write pre-init user code here
            snapPicCommandIndoor = new Command("Snap Picture", Command.SCREEN, 0);
            // write post-init user code here
        }
        return snapPicCommandIndoor;
    }

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: indoorPostalStringItem ">//GEN-BEGIN:|69-getter|0|69-preInit
    /**
     * Returns an initiliazed instance of indoorPostalStringItem component.
     * @return the initialized component instance
     */
    public StringItem getIndoorPostalStringItem() {
        if (indoorPostalStringItem == null) {//GEN-END:|69-getter|0|69-preInit
            // write pre-init user code here
            indoorPostalStringItem = new StringItem("Postal Code:", null);//GEN-LINE:|69-getter|1|69-postInit
            // write post-init user code here
        }//GEN-BEGIN:|69-getter|2|
        return indoorPostalStringItem;
    }
    //</editor-fold>//GEN-END:|69-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: indoorImageItem ">//GEN-BEGIN:|72-getter|0|72-preInit
    /**
     * Returns an initiliazed instance of indoorImageItem component.
     * @return the initialized component instance
     */
    public ImageItem getIndoorImageItem() {
        if (indoorImageItem == null) {//GEN-END:|72-getter|0|72-preInit
            // write pre-init user code here
            indoorImageItem = new ImageItem("Image:", null, ImageItem.LAYOUT_DEFAULT, "<Missing Image>", Item.PLAIN);//GEN-LINE:|72-getter|1|72-postInit
            // write post-init user code here
        }//GEN-BEGIN:|72-getter|2|
        return indoorImageItem;
    }
    //</editor-fold>//GEN-END:|72-getter|2|
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: outdoorDescriptiontextField ">//GEN-BEGIN:|73-getter|0|73-preInit
    /**
     * Returns an initiliazed instance of outdoorDescriptiontextField component.
     * @return the initialized component instance
     */
    public TextField getOutdoorDescriptiontextField() {
        if (outdoorDescriptiontextField == null) {//GEN-END:|73-getter|0|73-preInit
            // write pre-init user code here
            outdoorDescriptiontextField = new TextField("Description:", null, 500, TextField.ANY);//GEN-BEGIN:|73-getter|1|73-postInit
            outdoorDescriptiontextField.setLayout(ImageItem.LAYOUT_DEFAULT | ImageItem.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_VEXPAND);//GEN-END:|73-getter|1|73-postInit
            // write post-init user code here
        }//GEN-BEGIN:|73-getter|2|
        return outdoorDescriptiontextField;
    }
    //</editor-fold>//GEN-END:|73-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: indoorDescriptiontextField ">//GEN-BEGIN:|74-getter|0|74-preInit
    /**
     * Returns an initiliazed instance of indoorDescriptiontextField component.
     * @return the initialized component instance
     */
    public TextField getIndoorDescriptiontextField() {
        if (indoorDescriptiontextField == null) {//GEN-END:|74-getter|0|74-preInit
            // write pre-init user code here
            indoorDescriptiontextField = new TextField("Description:", null, 32, TextField.ANY);//GEN-LINE:|74-getter|1|74-postInit
            // write post-init user code here
        }//GEN-BEGIN:|74-getter|2|
        return indoorDescriptiontextField;
    }
    //</editor-fold>//GEN-END:|74-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: outdoorCoordinarteStringItem ">//GEN-BEGIN:|75-getter|0|75-preInit
    /**
     * Returns an initiliazed instance of outdoorCoordinarteStringItem component.
     * @return the initialized component instance
     */
    public StringItem getOutdoorCoordinarteStringItem() {
        if (outdoorCoordinarteStringItem == null) {//GEN-END:|75-getter|0|75-preInit
            // write pre-init user code here
            outdoorCoordinarteStringItem = new StringItem("Current Coordinates:", null);//GEN-BEGIN:|75-getter|1|75-postInit
            outdoorCoordinarteStringItem.setLayout(ImageItem.LAYOUT_DEFAULT | ImageItem.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_VEXPAND);//GEN-END:|75-getter|1|75-postInit
            // write post-init user code here
        }//GEN-BEGIN:|75-getter|2|
        return outdoorCoordinarteStringItem;
    }
    //</editor-fold>//GEN-END:|75-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: reportMenuBackCommand ">//GEN-BEGIN:|76-getter|0|76-preInit
    /**
     * Returns an initiliazed instance of reportMenuBackCommand component.
     * @return the initialized component instance
     */
    public Command getReportMenuBackCommand() {
        if (reportMenuBackCommand == null) {//GEN-END:|76-getter|0|76-preInit
            // write pre-init user code here
            reportMenuBackCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|76-getter|1|76-postInit
            // write post-init user code here
        }//GEN-BEGIN:|76-getter|2|
        return reportMenuBackCommand;
    }
    //</editor-fold>//GEN-END:|76-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: indoorLevelChoiceGroup ">//GEN-BEGIN:|79-getter|0|79-preInit
    /**
     * Returns an initiliazed instance of indoorLevelChoiceGroup component.
     * @return the initialized component instance
     */
    public ChoiceGroup getIndoorLevelChoiceGroup() {
        if (indoorLevelChoiceGroup == null) {//GEN-END:|79-getter|0|79-preInit
            // write pre-init user code here
            indoorLevelChoiceGroup = new ChoiceGroup("Level:", Choice.EXCLUSIVE);//GEN-LINE:|79-getter|1|79-postInit
            // write post-init user code here
        }//GEN-BEGIN:|79-getter|2|
        return indoorLevelChoiceGroup;
    }
    //</editor-fold>//GEN-END:|79-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: indoorStairwellChoiceGroup ">//GEN-BEGIN:|80-getter|0|80-preInit
    /**
     * Returns an initiliazed instance of indoorStairwellChoiceGroup component.
     * @return the initialized component instance
     */
    public ChoiceGroup getIndoorStairwellChoiceGroup() {
        if (indoorStairwellChoiceGroup == null) {//GEN-END:|80-getter|0|80-preInit
            // write pre-init user code here
            indoorStairwellChoiceGroup = new ChoiceGroup("Lobby/Stairwell:", Choice.EXCLUSIVE);//GEN-LINE:|80-getter|1|80-postInit
            // write post-init user code here
        }//GEN-BEGIN:|80-getter|2|
        return indoorStairwellChoiceGroup;
    }
    //</editor-fold>//GEN-END:|80-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageMainForm ">//GEN-BEGIN:|81-getter|0|81-preInit
    /**
     * Returns an initiliazed instance of messageMainForm component.
     * @return the initialized component instance
     */
    public Form getMessageMainForm() {
        if (messageMainForm == null) {//GEN-END:|81-getter|0|81-preInit
            // write pre-init user code here
            messageMainForm = new Form("Private Messaging", new Item[] { getMessageMainChoice(), getStringItem() });//GEN-BEGIN:|81-getter|1|81-postInit
            messageMainForm.addCommand(getMessageMainBackCommand());
            messageMainForm.addCommand(getMessageMainNextCommand());
            messageMainForm.setCommandListener(this);//GEN-END:|81-getter|1|81-postInit
            // write post-init user code here
        }//GEN-BEGIN:|81-getter|2|
        return messageMainForm;
    }
    //</editor-fold>//GEN-END:|81-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageMainBackCommand ">//GEN-BEGIN:|82-getter|0|82-preInit
    /**
     * Returns an initiliazed instance of messageMainBackCommand component.
     * @return the initialized component instance
     */
    public Command getMessageMainBackCommand() {
        if (messageMainBackCommand == null) {//GEN-END:|82-getter|0|82-preInit
            // write pre-init user code here
            messageMainBackCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|82-getter|1|82-postInit
            // write post-init user code here
        }//GEN-BEGIN:|82-getter|2|
        return messageMainBackCommand;
    }
    //</editor-fold>//GEN-END:|82-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageMainNextCommand ">//GEN-BEGIN:|84-getter|0|84-preInit
    /**
     * Returns an initiliazed instance of messageMainNextCommand component.
     * @return the initialized component instance
     */
    public Command getMessageMainNextCommand() {
        if (messageMainNextCommand == null) {//GEN-END:|84-getter|0|84-preInit
            // write pre-init user code here
            messageMainNextCommand = new Command("Next", Command.OK, 0);//GEN-LINE:|84-getter|1|84-postInit
            // write post-init user code here
        }//GEN-BEGIN:|84-getter|2|
        return messageMainNextCommand;
    }
    //</editor-fold>//GEN-END:|84-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageMainChoice ">//GEN-BEGIN:|87-getter|0|87-preInit
    /**
     * Returns an initiliazed instance of messageMainChoice component.
     * @return the initialized component instance
     */
    public ChoiceGroup getMessageMainChoice() {
        if (messageMainChoice == null) {//GEN-END:|87-getter|0|87-preInit
            // write pre-init user code here
            messageMainChoice = new ChoiceGroup("What would you like to do?", Choice.EXCLUSIVE);//GEN-BEGIN:|87-getter|1|87-postInit
            messageMainChoice.append("Compose a Message", null);
            messageMainChoice.append("Check for New Messages", null);
            messageMainChoice.setSelectedFlags(new boolean[] { false, false });//GEN-END:|87-getter|1|87-postInit
            // write post-init user code here
        }//GEN-BEGIN:|87-getter|2|
        return messageMainChoice;
    }
    //</editor-fold>//GEN-END:|87-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: stringItem ">//GEN-BEGIN:|90-getter|0|90-preInit
    /**
     * Returns an initiliazed instance of stringItem component.
     * @return the initialized component instance
     */
    public StringItem getStringItem() {
        if (stringItem == null) {//GEN-END:|90-getter|0|90-preInit
            // write pre-init user code here
            stringItem = new StringItem("", "Projecty Amity Mobile\'s Private Messaing module automatically checks for new messages every 10 seconds.");//GEN-LINE:|90-getter|1|90-postInit
            // write post-init user code here
        }//GEN-BEGIN:|90-getter|2|
        return stringItem;
    }
    //</editor-fold>//GEN-END:|90-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageCreateForm ">//GEN-BEGIN:|91-getter|0|91-preInit
    /**
     * Returns an initiliazed instance of messageCreateForm component.
     * @return the initialized component instance
     */
    public Form getMessageCreateForm() {
        if (messageCreateForm == null) {//GEN-END:|91-getter|0|91-preInit
            // write pre-init user code here
            messageCreateForm = new Form("Compose New Message", new Item[] { getTbxTo(), getTbxSubject(), getTbxMessage() });//GEN-BEGIN:|91-getter|1|91-postInit
            messageCreateForm.addCommand(getMessageCreateBackCommand());
            messageCreateForm.addCommand(getMessageCreateSendCommand());
            messageCreateForm.setCommandListener(this);//GEN-END:|91-getter|1|91-postInit
            // write post-init user code here
        }//GEN-BEGIN:|91-getter|2|
        return messageCreateForm;
    }
    //</editor-fold>//GEN-END:|91-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageCreateBackCommand ">//GEN-BEGIN:|92-getter|0|92-preInit
    /**
     * Returns an initiliazed instance of messageCreateBackCommand component.
     * @return the initialized component instance
     */
    public Command getMessageCreateBackCommand() {
        if (messageCreateBackCommand == null) {//GEN-END:|92-getter|0|92-preInit
            // write pre-init user code here
            messageCreateBackCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|92-getter|1|92-postInit
            // write post-init user code here
        }//GEN-BEGIN:|92-getter|2|
        return messageCreateBackCommand;
    }
    //</editor-fold>//GEN-END:|92-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageCreateSendCommand ">//GEN-BEGIN:|94-getter|0|94-preInit
    /**
     * Returns an initiliazed instance of messageCreateSendCommand component.
     * @return the initialized component instance
     */
    public Command getMessageCreateSendCommand() {
        if (messageCreateSendCommand == null) {//GEN-END:|94-getter|0|94-preInit
            // write pre-init user code here
            messageCreateSendCommand = new Command("Send", Command.OK, 0);//GEN-LINE:|94-getter|1|94-postInit
            // write post-init user code here
        }//GEN-BEGIN:|94-getter|2|
        return messageCreateSendCommand;
    }
    //</editor-fold>//GEN-END:|94-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tbxTo ">//GEN-BEGIN:|97-getter|0|97-preInit
    /**
     * Returns an initiliazed instance of tbxTo component.
     * @return the initialized component instance
     */
    public TextField getTbxTo() {
        if (tbxTo == null) {//GEN-END:|97-getter|0|97-preInit
            // write pre-init user code here
            tbxTo = new TextField("To:", null, 32, TextField.ANY);//GEN-LINE:|97-getter|1|97-postInit
            // write post-init user code here
        }//GEN-BEGIN:|97-getter|2|
        return tbxTo;
    }
    //</editor-fold>//GEN-END:|97-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tbxSubject ">//GEN-BEGIN:|98-getter|0|98-preInit
    /**
     * Returns an initiliazed instance of tbxSubject component.
     * @return the initialized component instance
     */
    public TextField getTbxSubject() {
        if (tbxSubject == null) {//GEN-END:|98-getter|0|98-preInit
            // write pre-init user code here
            tbxSubject = new TextField("Subject:", null, 32, TextField.ANY);//GEN-LINE:|98-getter|1|98-postInit
            // write post-init user code here
        }//GEN-BEGIN:|98-getter|2|
        return tbxSubject;
    }
    //</editor-fold>//GEN-END:|98-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: tbxMessage ">//GEN-BEGIN:|99-getter|0|99-preInit
    /**
     * Returns an initiliazed instance of tbxMessage component.
     * @return the initialized component instance
     */
    public TextField getTbxMessage() {
        if (tbxMessage == null) {//GEN-END:|99-getter|0|99-preInit
            // write pre-init user code here
            tbxMessage = new TextField("Message:", null, 500, TextField.ANY);//GEN-LINE:|99-getter|1|99-postInit
            // write post-init user code here
        }//GEN-BEGIN:|99-getter|2|
        return tbxMessage;
    }
    //</editor-fold>//GEN-END:|99-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageViewForm ">//GEN-BEGIN:|100-getter|0|100-preInit
    /**
     * Returns an initiliazed instance of messageViewForm component.
     * @return the initialized component instance
     */
    public Form getMessageViewForm() {
        if (messageViewForm == null) {//GEN-END:|100-getter|0|100-preInit
            // write pre-init user code here
            messageViewForm = new Form("New Unread Message", new Item[] { getLblSubject(), getLblFrom(), getLblFromID(), getLblTo(), getLblToID(), getLblMessage() });//GEN-BEGIN:|100-getter|1|100-postInit
            messageViewForm.addCommand(getMessageViewBackCommand());
            messageViewForm.addCommand(getMessageViewReplyCommand());
            messageViewForm.setCommandListener(this);//GEN-END:|100-getter|1|100-postInit
            // write post-init user code here
        }//GEN-BEGIN:|100-getter|2|
        return messageViewForm;
    }
    //</editor-fold>//GEN-END:|100-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageViewBackCommand ">//GEN-BEGIN:|101-getter|0|101-preInit
    /**
     * Returns an initiliazed instance of messageViewBackCommand component.
     * @return the initialized component instance
     */
    public Command getMessageViewBackCommand() {
        if (messageViewBackCommand == null) {//GEN-END:|101-getter|0|101-preInit
            // write pre-init user code here
            messageViewBackCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|101-getter|1|101-postInit
            // write post-init user code here
        }//GEN-BEGIN:|101-getter|2|
        return messageViewBackCommand;
    }
    //</editor-fold>//GEN-END:|101-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: messageViewReplyCommand ">//GEN-BEGIN:|104-getter|0|104-preInit
    /**
     * Returns an initiliazed instance of messageViewReplyCommand component.
     * @return the initialized component instance
     */
    public Command getMessageViewReplyCommand() {
        if (messageViewReplyCommand == null) {//GEN-END:|104-getter|0|104-preInit
            // write pre-init user code here
            messageViewReplyCommand = new Command("Reply", Command.OK, 0);//GEN-LINE:|104-getter|1|104-postInit
            // write post-init user code here
        }//GEN-BEGIN:|104-getter|2|
        return messageViewReplyCommand;
    }
    //</editor-fold>//GEN-END:|104-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lblSubject ">//GEN-BEGIN:|106-getter|0|106-preInit
    /**
     * Returns an initiliazed instance of lblSubject component.
     * @return the initialized component instance
     */
    public StringItem getLblSubject() {
        if (lblSubject == null) {//GEN-END:|106-getter|0|106-preInit
            // write pre-init user code here
            lblSubject = new StringItem("Subject:", null);//GEN-LINE:|106-getter|1|106-postInit
            // write post-init user code here
        }//GEN-BEGIN:|106-getter|2|
        return lblSubject;
    }
    //</editor-fold>//GEN-END:|106-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lblFrom ">//GEN-BEGIN:|107-getter|0|107-preInit
    /**
     * Returns an initiliazed instance of lblFrom component.
     * @return the initialized component instance
     */
    public StringItem getLblFrom() {
        if (lblFrom == null) {//GEN-END:|107-getter|0|107-preInit
            // write pre-init user code here
            lblFrom = new StringItem("From:", null);//GEN-LINE:|107-getter|1|107-postInit
            // write post-init user code here
        }//GEN-BEGIN:|107-getter|2|
        return lblFrom;
    }
    //</editor-fold>//GEN-END:|107-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lblFromID ">//GEN-BEGIN:|108-getter|0|108-preInit
    /**
     * Returns an initiliazed instance of lblFromID component.
     * @return the initialized component instance
     */
    public StringItem getLblFromID() {
        if (lblFromID == null) {//GEN-END:|108-getter|0|108-preInit
            // write pre-init user code here
            lblFromID = new StringItem("", null);//GEN-LINE:|108-getter|1|108-postInit
            // write post-init user code here
        }//GEN-BEGIN:|108-getter|2|
        return lblFromID;
    }
    //</editor-fold>//GEN-END:|108-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lblTo ">//GEN-BEGIN:|109-getter|0|109-preInit
    /**
     * Returns an initiliazed instance of lblTo component.
     * @return the initialized component instance
     */
    public StringItem getLblTo() {
        if (lblTo == null) {//GEN-END:|109-getter|0|109-preInit
            // write pre-init user code here
            lblTo = new StringItem("To:", null);//GEN-LINE:|109-getter|1|109-postInit
            // write post-init user code here
        }//GEN-BEGIN:|109-getter|2|
        return lblTo;
    }
    //</editor-fold>//GEN-END:|109-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lblToID ">//GEN-BEGIN:|110-getter|0|110-preInit
    /**
     * Returns an initiliazed instance of lblToID component.
     * @return the initialized component instance
     */
    public StringItem getLblToID() {
        if (lblToID == null) {//GEN-END:|110-getter|0|110-preInit
            // write pre-init user code here
            lblToID = new StringItem("", null);//GEN-LINE:|110-getter|1|110-postInit
            // write post-init user code here
        }//GEN-BEGIN:|110-getter|2|
        return lblToID;
    }
    //</editor-fold>//GEN-END:|110-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: lblMessage ">//GEN-BEGIN:|111-getter|0|111-preInit
    /**
     * Returns an initiliazed instance of lblMessage component.
     * @return the initialized component instance
     */
    public StringItem getLblMessage() {
        if (lblMessage == null) {//GEN-END:|111-getter|0|111-preInit
            // write pre-init user code here
            lblMessage = new StringItem("", null);//GEN-LINE:|111-getter|1|111-postInit
            // write post-init user code here
        }//GEN-BEGIN:|111-getter|2|
        return lblMessage;
    }
    //</editor-fold>//GEN-END:|111-getter|2|

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: mainMenuBackCommand ">//GEN-BEGIN:|112-getter|0|112-preInit
    /**
     * Returns an initiliazed instance of mainMenuBackCommand component.
     * @return the initialized component instance
     */
    public Command getMainMenuBackCommand() {
        if (mainMenuBackCommand == null) {//GEN-END:|112-getter|0|112-preInit
            // write pre-init user code here
            mainMenuBackCommand = new Command("Back", Command.BACK, 0);//GEN-LINE:|112-getter|1|112-postInit
            // write post-init user code here
        }//GEN-BEGIN:|112-getter|2|
        return mainMenuBackCommand;
    }
    //</editor-fold>//GEN-END:|112-getter|2|

    /**
     * Returns a display instance.
     * @return the display instance.
     */
    public Display getDisplay() {
        return Display.getDisplay(this);
    }

    /**
     * Exits MIDlet.
     */
    public void exitMIDlet() {
        switchDisplayable(null, null);
        destroyApp(true);
        notifyDestroyed();

    }

    /**
     * Called when MIDlet is started.
     * Checks whether the MIDlet have been already started and initialize/starts or resumes the MIDlet.
     */
    public void startApp() {
        if (midletPaused) {
            resumeMIDlet();
        } else {
            initialize();
            startMIDlet();
        }
        midletPaused = false;
    }

    /**
     * Called when MIDlet is paused.
     */
    public void pauseApp() {
        midletPaused = true;
    }

    /**
     * Called to signal the MIDlet to terminate.
     * @param unconditional if true, then the MIDlet has to be unconditionally terminated and all resources has to be released.
     */
    public void destroyApp(boolean unconditional) {
    }

    private String[] split(String original, String separator) {
        Vector nodes = new Vector();
        // Parse nodes into vector
        int index = original.indexOf(separator);
        while (index >= 0) {
            nodes.addElement(original.substring(0, index));
            original = original.substring(index + separator.length());
            index = original.indexOf(separator);
        }
        // Get the last node
        nodes.addElement(original);

        // Create split string array
        String[] result = new String[nodes.size()];
        if (nodes.size() > 0) {
            for (int loop = 0; loop < nodes.size(); loop++) {
                result[loop] = (String) nodes.elementAt(loop);
                System.out.println(result[loop]);
            }

        }
        return result;
    }

    static public String urlEncode(String sUrl) {

        //Url Encoding for period has been omitted
        StringBuffer urlOK = new StringBuffer();
        for (int i = 0; i < sUrl.length(); i++) {
            char ch = sUrl.charAt(i);
            switch (ch) {
                case '<':
                    urlOK.append("%3C");
                    break;
                case '>':
                    urlOK.append("%3E");
                    break;
                case '#':
                    urlOK.append("%23");
                    break;
                case '/':
                    urlOK.append("%2F");
                    break;
                case ' ':
                    urlOK.append("%20");
                    break;
                case '-':
                    urlOK.append("%2D");
                    break;
                case '.':
                    urlOK.append("%2E");
                    break;
                default:
                    urlOK.append(ch);
                    break;
            }
        }
        return urlOK.toString();
    }
    private static char[] map1 = new char[64];

    static {
        int i = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            map1[i++] = c;
        }
        for (char c = 'a'; c <= 'z'; c++) {
            map1[i++] = c;
        }
        for (char c = '0'; c <= '9'; c++) {
            map1[i++] = c;
        }
        map1[i++] = '+';
        map1[i++] = '/';
    }

    public static String base64Encode(byte[] in) {
        int iLen = in.length;
        int oDataLen = (iLen * 4 + 2) / 3;// output length without padding
        int oLen = ((iLen + 2) / 3) * 4;// output length including padding
        char[] out = new char[oLen];
        int ip = 0;
        int op = 0;
        int i0;
        int i1;
        int i2;
        int o0;
        int o1;
        int o2;
        int o3;
        while (ip < iLen) {
            i0 = in[ip++] & 0xff;
            i1 = ip < iLen ? in[ip++] & 0xff : 0;
            i2 = ip < iLen ? in[ip++] & 0xff : 0;
            o0 = i0 >>> 2;
            o1 = ((i0 & 3) << 4) | (i1 >>> 4);
            o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
            o3 = i2 & 0x3F;
            out[op++] = map1[o0];
            out[op++] = map1[o1];
            out[op] = op < oDataLen ? map1[o2] : '=';
            op++;
            out[op] = op < oDataLen ? map1[o3] : '=';
            op++;
        }
        return new String(out);
    }

    private void getLocation() {
        new Thread() {

            public void run() {
                try {


                    if (provider.getState() == LocationProvider.AVAILABLE) {
                        System.out.println("Provider is available");
                        // 90 secs is the time out
                        Location location = provider.getLocation(90);
                        QualifiedCoordinates c = location.getQualifiedCoordinates();
                        int locationMethod = location.getLocationMethod();
                        latitude = c.getLatitude();
                        longitude = c.getLongitude();
                        altitude = c.getAltitude();
                        //float hAccuracy = c.getHorizontalAccuracy();
                        //float vAccuracy = c.getVerticalAccuracy();
                        //Coordinates greenwich = new Coordinates(0, 51, 0);
                        //float disGreenwich = c.distance(greenwich);
                        // testLocationstringItem.setText("Lat: " + latitude + "Lon: " + longitude + "Alt: " + altitude);
                        switchDisplayable(null, getOutdoorReportForm());
                        outdoorCoordinarteStringItem.setText("Latitude: " + latitude + " Longitude: " + longitude + " Altitude: " + altitude);


                    } else if (provider.getState() == LocationProvider.TEMPORARILY_UNAVAILABLE) {
                        switchDisplayable(null, getOutdoorReportForm());
                        outdoorCoordinarteStringItem.setText("Unable to get coordinates");
                        System.out.println("Provider is unavailable");
                    }

                } catch (LocationException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    private void loginAccount() {
        new Thread() {

            public void run() {
                StringBuffer serverMsg = new StringBuffer("");
                HttpConnection hc = null;
                InputStream is = null;

                try {
                    hc = (HttpConnection) Connector.open(loginUserURL + urlEncode("?nric=" + NRICLoginFormtextField.getString() + "&password=" + passwordLoginFormtextField.getString()));
                    is = hc.openInputStream();
                    int ch = is.read();
                    while (ch != -1) {
                        serverMsg.append((char) ch);

                        ch = is.read();
                    }
                    System.out.println("Login THREAD: " + serverMsg.toString().trim());
                    loginServerMsg = serverMsg.toString().trim();
                    is.close();
                    hc.close();
                    String temp[] = split(loginServerMsg, "|");
                    if (temp[0].equals("T")) {
                        // logInMessage is a variable that YJ needs.
                        logInMessage = temp[1];
                        loginServerMsg = "";
                        userIDLoggedIn = temp[2];
                        Thread.sleep(500);
                        switchDisplayable(null, getMainMenuForm());
                    }

                    if (loginServerMsg.equals("F")) {
                        loginServerMsg = "";
                        System.out.println("Wrong Login Info");
                        alert = new Alert("Error", "Username/Password is invalid.", null, AlertType.ERROR);
                        alert.setTimeout(2000); //Timeout in 2 seconds
                        switchDisplayable(alert, getLoginForm());
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }.start();
    }

    private void reportSubmitOutdoor() {
        new Thread() {

            public void run() {

                StringBuffer serverMsg = new StringBuffer("");
                HttpConnection hc = null;
                InputStream is = null;

                try {

                    hc = (HttpConnection) Connector.open(reportOutdoorURL + urlEncode("?userid=" + userIDLoggedIn + "&title=" + outdoorTitletextField.getString() + "&description=" + outdoorDescriptiontextField.getString() + "&latitude=" + latitude + "&longitude=" + longitude + "&altitude=" + altitude + "&imagename=" + imageName), Connector.READ_WRITE);

                    hc.setRequestMethod(HttpConnection.POST);
                    hc.setRequestProperty("Content-Type", "application/octet-stream");
                    OutputStream out = hc.openOutputStream();

                    //Base64 b = new Base64();
                    //     String imageString = b.encodeToString(imageByteOutdoor);\

                      String imageString = base64Encode(imageByteOutdoor);
                      out.write(imageString.getBytes());
                    //out.write(imageByteIndoor);
                    

                    is = hc.openInputStream();
                    int ch = is.read();
                    while (ch != -1) {
                        serverMsg.append((char) ch);

                        ch = is.read();
                    }
                    System.out.println("Submit Report Outdoors THREAD: " + serverMsg.toString().trim());
                    reportOutdoorServerMsg = serverMsg.toString().trim();
                    
                    out.close();
                    is.close();
                    hc.close();

                    if (reportOutdoorServerMsg.equals("T")) {
                        outdoorCoordinarteStringItem.setText("");
                        reportOutdoorServerMsg = "";
                        alert = new Alert("Success", "Report has been successfully submitted.", null, AlertType.CONFIRMATION);
                        alert.setTimeout(2000); //Timeout in 2 seconds
                        switchDisplayable(alert, getMainMenuForm());
                    }

                    if (reportOutdoorServerMsg.equals("F")) {
                        reportOutdoorServerMsg = "";

                        alert = new Alert("Error", "Unable to post report.", null, AlertType.ERROR);
                        alert.setTimeout(2000); //Timeout in 2 seconds
                        switchDisplayable(alert, getOutdoorReportForm());
                    }
                } catch (Exception e) {
                    System.out.println("Error in Submitting Report for Outdoors.");
                    e.printStackTrace();
                }


            }
        }.start();
    }

    private void reportSubmitIndoor() {
        new Thread() {

            public void run() {

                StringBuffer serverMsg = new StringBuffer("");
                HttpConnection hc = null;
                InputStream is = null;

                try {

                    hc = (HttpConnection) Connector.open(reportIndoorURL + urlEncode("?userid=" + userIDLoggedIn + "&title=" + indoorTitleTextField.getString() + "&description=" + indoorDescriptiontextField.getString() + "&postalCode=" + userPostalCode + "&imagename=" + imageName), Connector.READ_WRITE);

                    hc.setRequestMethod(HttpConnection.POST);
                    OutputStream out = hc.openOutputStream();

                    //Base64 b = new Base64();
                    //     String imageString = b.encodeToString(imageByteOutdoor);
                    //  String imageString = base64Encode(imageByteOutdoor);
                    //  out.write(imageString.getBytes());

                    is = hc.openInputStream();
                    int ch = is.read();
                    while (ch != -1) {
                        serverMsg.append((char) ch);

                        ch = is.read();
                    }
                    System.out.println("Submit Report Indoors THREAD: " + serverMsg.toString().trim());
                    reportIndoorServerMsg = serverMsg.toString().trim();
                    // out.flush();
                    out.close();
                    is.close();
                    hc.close();

                    if (reportIndoorServerMsg.equals("T")) {

                        reportIndoorServerMsg = "";
                        indoorPostalStringItem.setText("");
                        alert = new Alert("Success", "Report has been successfully submitted.", null, AlertType.CONFIRMATION);
                        alert.setTimeout(2000); //Timeout in 2 seconds
                        switchDisplayable(alert, getMainMenuForm());
                    }

                    if (reportIndoorServerMsg.equals("F")) {
                        reportIndoorServerMsg = "";

                        alert = new Alert("Error", "Unable to post report.", null, AlertType.ERROR);
                        alert.setTimeout(2000); //Timeout in 2 seconds
                        switchDisplayable(alert, getIndoorReportForm());
                    }
                } catch (Exception e) {
                    System.out.println("Error in Submitting Report for Indoors.");
                    e.printStackTrace();
                }


            }
        }.start();
    }

    private void showCameraOutdoor() {
        System.out.println("Method showCamera() Starts here");
        try {
            mPlayer = Manager.createPlayer("capture://video");
            mPlayer.realize();

            mVideoControl = (VideoControl) mPlayer.getControl("VideoControl");

            Canvas canvas = new CameraCanvas(this, mVideoControl);
            canvas.addCommand(getExitCommand1());
            canvas.addCommand(getSnapPicCommand());
            canvas.setCommandListener(this);
            //  mDisplay.setCurrent(canvas);
            switchDisplayable(null, canvas);

            /*
            Form form = new Form("Camera form");
            Item item = (Item)mVideoControl.initDisplayMode(
            GUIControl.USE_GUI_PRIMITIVE, null);
            form.append(item);
            form.addCommand(mBackCommand);
            form.addCommand(mCaptureCommand);
            form.setCommandListener(this);
            mDisplay.setCurrent(form);
             */

            mPlayer.start();
        } catch (IOException ioe) {
            handleException(ioe);
        } catch (MediaException me) {
            handleException(me);
        }
    }

    private void showCameraIndoor() {
        System.out.println("Method showCamera() Starts here");
        try {
            mPlayer = Manager.createPlayer("capture://video");
            mPlayer.realize();

            mVideoControl = (VideoControl) mPlayer.getControl("VideoControl");

            Canvas canvas = new CameraIndoorCanvas(this, mVideoControl);
            canvas.addCommand(getExitCommand2());
            canvas.addCommand(snapPicCommandIndoor());
            canvas.setCommandListener(this);
            switchDisplayable(null, canvas);
            mPlayer.start();
        } catch (IOException ioe) {
            handleException(ioe);
        } catch (MediaException me) {
            handleException(me);
        }
    }

    public void captureOutdoor() {

        new Thread() {

            public void run() {

                try {
                    // Get the image.
                    byte[] raw = mVideoControl.getSnapshot("encoding=jpeg");
                    Image image = Image.createImage(raw, 0, raw.length);
                    imageByteOutdoor = raw;

                    String fileName = "AmityImage_" + d.getTime() + ".jpg";
                    imageName = fileName;

//                    Image thumb = createThumbnail(image);
//
//                    // Place it in the main form.
//                    if (cameraCaptureForm.size() > 0 && cameraCaptureForm.get(0) instanceof StringItem) {
//                        cameraCaptureForm.delete(0);
//                    }
//                    cameraCaptureForm.append(thumb);

                    System.out.println("FileConnection Starts here");
                    //The following only works for nokia photos.
                    FileConnection fconn = (FileConnection) Connector.open("file:///C:/Data/Images/" + fileName, Connector.WRITE);
                    fconn.create();
                    OutputStream out = fconn.openOutputStream();
                    //out.write(image);
                    out.write(raw);
                    out.flush();
                    out.close();
                    fconn.close();


                    // Shut down the player.
                    mPlayer.close();
                    mPlayer = null;
                    mVideoControl = null;
                    

                    // Flip back to the main form.
                    mDisplay.setCurrent(getOutdoorReportForm());
                    outdoorImageItem.setImage(image);

                } catch (MediaException me) {
                    handleException(me);
                } catch (Exception e) {
                    handleException(e);
                }

            }
        }.start();


    }

    public void captureIndoor() {

        new Thread() {

            public void run() {

                try {
                    // Get the image.
                    byte[] raw = mVideoControl.getSnapshot("encoding=jpeg");
                    Image image = Image.createImage(raw, 0, raw.length);
                    imageByteIndoor = raw;

                    String fileName = "AmityImage_" + d.getTime() + ".jpg";
                    imageName = fileName;
//                    Image thumb = createThumbnail(image);
//
//                    // Place it in the main form.
//                    if (cameraCaptureForm.size() > 0 && cameraCaptureForm.get(0) instanceof StringItem) {
//                        cameraCaptureForm.delete(0);
//                    }
//                    cameraCaptureForm.append(thumb);

                    System.out.println("FileConnection Starts here");
                    //The following only works for nokia photos.
                    FileConnection fconn = (FileConnection) Connector.open("file:///C:/Data/Images/" + fileName, Connector.WRITE);
                    fconn.create();
                    OutputStream out = fconn.openOutputStream();
                    //out.write(image);
                    out.write(raw);
                    out.flush();
                    out.close();
                    fconn.close();


                    // Shut down the player.
                    mPlayer.close();
                    mPlayer = null;
                    mVideoControl = null;
                   
                  

                    // Flip back to the main form.
                    mDisplay.setCurrent(getIndoorReportForm());
                      indoorImageItem.setImage(image);

                } catch (MediaException me) {
                    handleException(me);
                } catch (Exception e) {
                    handleException(e);
                }

            }
        }.start();


    }

    private Image createThumbnail(Image image) {
        int sourceWidth = image.getWidth();
        int sourceHeight = image.getHeight();

        int thumbWidth = 64;
        int thumbHeight = -1;

        if (thumbHeight == -1) {
            thumbHeight = thumbWidth * sourceHeight / sourceWidth;
        }

        Image thumb = Image.createImage(thumbWidth, thumbHeight);
        Graphics g = thumb.getGraphics();

        for (int y = 0; y < thumbHeight; y++) {
            for (int x = 0; x < thumbWidth; x++) {
                g.setClip(x, y, 1, 1);
                int dx = x * sourceWidth / thumbWidth;
                int dy = y * sourceHeight / thumbHeight;
                g.drawImage(image, x - dx, y - dy, Graphics.LEFT | Graphics.TOP);
            }
        }

        Image immutableThumb = Image.createImage(thumb);

        return immutableThumb;
    }

    private void handleException(Exception e) {
        Alert a = new Alert("Exception", e.toString(), null, null);
        a.setTimeout(2000);
        switchDisplayable(a, getReportMainForm());
        //  mDisplay.setCurrent(a, cameraCaptureForm);
    }

    private void getUserPostalCode() {
        //not done
        new Thread() {

            public void run() {
                StringBuffer serverMsg = new StringBuffer("");
                HttpConnection hc = null;
                InputStream is = null;

                try {
                    hc = (HttpConnection) Connector.open(getPostalcode + urlEncode("?userid=" + userIDLoggedIn));
                    is = hc.openInputStream();
                    int ch = is.read();
                    while (ch != -1) {
                        serverMsg.append((char) ch);

                        ch = is.read();
                    }
                    System.out.println("Get Postal Code  THREAD: " + serverMsg.toString().trim());
                    userPostalCode = serverMsg.toString().trim();
                    String splitted[] = split(userPostalCode, "~");
                    //splitted[0] = postal code
                    //splitted[1] = Level, requires split by |
                    //splitted[2] = stairwell, requires split by |
                    is.close();
                    hc.close();

                    switchDisplayable(null, getIndoorReportForm());
                    indoorPostalStringItem.setText(splitted[0]);
                    String level[] = split(splitted[1], "|");
                    String stairwell[] = split(splitted[2], "|");

                    for (int i = 1; i < level.length; i++) {
                        System.out.println("Level: " + level[i]);
                        indoorLevelChoiceGroup.append(level[i], null);
                    }
                    for (int k = 1; k < stairwell.length; k++) {
                        System.out.println("Stairwell: " + stairwell[k]);
                        indoorStairwellChoiceGroup.append(stairwell[k], null);
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }.start();
    }

    private void sendMessage() {
        new Thread() {

            public void run() {
                StringBuffer serverMsg = new StringBuffer("");
                HttpConnection hc = null;
                InputStream is = null;

                try {
                    hc = (HttpConnection) Connector.open(sendMessageURL + urlEncode("?sender=" + msgSender + "&receiver=" + msgTo + "&subject=" + msgSubject + "&message=" + msgMessage));
                    is = hc.openInputStream();
                    int ch = is.read();
                    while (ch != -1) {
                        serverMsg.append((char) ch);

                        ch = is.read();
                    }
                    System.out.println("Message Send  THREAD: " + serverMsg.toString().trim());
                    String returnCode = serverMsg.toString().trim();
                    is.close();
                    hc.close();

                    System.out.println(returnCode);

                    if (returnCode.length() == 1) {
                        if (returnCode.equalsIgnoreCase("T")) {
                            // Successfully sent
                            alert = new Alert("Success", "Your message has been successfully sent!", null, AlertType.INFO);
                            alert.setTimeout(3000); //Timeout in 2 seconds
                            getTbxTo().setString("");
                            getTbxSubject().setString("");
                            getTbxMessage().setString("");
                            switchDisplayable(alert, getMessageMainForm());
                        } else if (returnCode.equalsIgnoreCase("F")) {
                            alert = new Alert("Error", "An error occured while we were tring to send your message. Please try again.", null, AlertType.ERROR);
                            alert.setTimeout(3000); //Timeout in 2 seconds
                            switchDisplayable(alert, getMessageCreateForm());
                        }
                    } else if (returnCode.substring(0, 1).equalsIgnoreCase("@")) {
                        String[] errors = split(returnCode, "|");
                        String message = "There are problems with your inputs. Ensure that the following inputs are valid:\n";
                        for (int i = 1; i < errors.length; i++) {
                            message += "\n" + errors[i];
                        }
                        alert = new Alert("Error", message, null, AlertType.ERROR);
                        alert.setTimeout(7000); //Timeout in 2 seconds
                        switchDisplayable(alert, getMessageCreateForm());
                    }
//                    switchDisplayable(null, getIndoorReportForm());
//                    indoorPostalStringItem.setText(userPostalCode);

                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }.start();
    }

    private void checkForMessages() {
        new Thread() {

            public void run() {
                while (checkForMessages) {
                    StringBuffer serverMsg = new StringBuffer("");
                    HttpConnection hc = null;
                    InputStream is = null;

                    try {
                        // userIDLoggedIn = "tampines5981";
                        //  logInMessage = "2010/07/21 11:24:46";
                        hc = (HttpConnection) Connector.open(checkMessageURL + urlEncode("?user=" + userIDLoggedIn + "&timeStamp=" + logInMessage));
                        is = hc.openInputStream();
                        int ch = is.read();
                        while (ch != -1) {
                            serverMsg.append((char) ch);

                            ch = is.read();
                        }
                        System.out.println("Message Checking  THREAD: " + serverMsg.toString().trim());
                        String returnCode = serverMsg.toString().trim();
                        is.close();
                        hc.close();

                        System.out.println(returnCode);

                        if (returnCode.length() > 1) {
                            String[] message = split(returnCode, "|");
                            // There are new messages
                            getLblFrom().setText(message[1]);
                            getLblFromID().setText(message[2]);
                            getLblTo().setText(message[3]);
                            getLblToID().setText(message[4]);
                            getLblSubject().setText(message[5]);
                            getLblMessage().setLabel("On " + message[7] + ", " + message[1] + " wrote: ");
                            getLblMessage().setText(message[6]);
                            if (checkForMessages) {
                                switchDisplayable(null, getMessageViewForm());
                            }
                        } else {
                        }
                        //                    switchDisplayable(null, getIndoorReportForm());
                        //                    indoorPostalStringItem.setText(userPostalCode);

                        Thread.sleep(10000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }.start();
    }

    private void checkForMessagesOnce() {
        new Thread() {

            public void run() {
                StringBuffer serverMsg = new StringBuffer("");
                HttpConnection hc = null;
                InputStream is = null;

                try {
                    // userIDLoggedIn = "tampines5981";
                    //  logInMessage = "2010/07/21 11:24:46";
                    hc = (HttpConnection) Connector.open(checkMessageURL + urlEncode("?user=" + userIDLoggedIn + "&timeStamp=" + logInMessage));
                    is = hc.openInputStream();
                    int ch = is.read();
                    while (ch != -1) {
                        serverMsg.append((char) ch);

                        ch = is.read();
                    }
                    System.out.println("Message Checking  THREAD: " + serverMsg.toString().trim());
                    String returnCode = serverMsg.toString().trim();
                    is.close();
                    hc.close();

                    System.out.println(returnCode);

                    if (returnCode.length() > 1) {
                        String[] message = split(returnCode, "|");
                        // There are new messages
                        getLblFrom().setText(message[1]);
                        getLblFromID().setText(message[2]);
                        getLblTo().setText(message[3]);
                        getLblToID().setText(message[4]);
                        getLblSubject().setText(message[5]);
                        getLblMessage().setLabel("On " + message[7] + ", " + message[1] + " wrote: ");
                        getLblMessage().setText(message[6]);
                        if (checkForMessages) {
                            switchDisplayable(null, getMessageViewForm());
                        }
                    } else {
                        // There are no new messages
                        alert = new Alert("No New Messages", "You have no new messages", null, AlertType.INFO);
                        alert.setTimeout(2000); //Timeout in 2 seconds
                        switchDisplayable(alert, getMessageMainForm());
                    }

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }.start();
    }
}
