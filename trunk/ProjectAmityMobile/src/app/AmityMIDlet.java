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
import javax.microedition.io.file.FileConnection;

/**
 * @author student
 */
public class AmityMIDlet extends MIDlet implements CommandListener {

    private boolean midletPaused = false;
    private LocationProvider provider = null;
    private String userIDLoggedIn = "";
    //URLs
    private String loginUserURL = "";
    private String reportURL = "";
    //Server return messages
    private String loginServerMsg = "";
    private String reportServerMsg = "";
    private Player mPlayer;
    private VideoControl mVideoControl;
    private Display mDisplay;
    //<editor-fold defaultstate="collapsed" desc=" Generated Fields ">//GEN-BEGIN:|fields|0|
    private Form loginForm;
    private TextField NRICLoginFormtextField;
    private TextField passwordLoginFormtextField;
    private Form reportForm;
    private Form cameraCaptureForm;
    private StringItem testLocationstringItem;
    private Alert alert;
    private Command loginCommand;
    private Command exitCommand;
    private Command exitCommand1;
    private Command locationCommand;
    private Command snapPicCommand;
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
        if (displayable == cameraCaptureForm) {//GEN-BEGIN:|7-commandAction|1|27-preAction
            if (command == exitCommand1) {//GEN-END:|7-commandAction|1|27-preAction
                // write pre-action user code here
                switchDisplayable(null, getLoginForm());//GEN-LINE:|7-commandAction|2|27-postAction
                // write post-action user code here
            } else if (command == locationCommand) {//GEN-LINE:|7-commandAction|3|33-preAction
                // write pre-action user code here
                getLocation();
//GEN-LINE:|7-commandAction|4|33-postAction
                // write post-action user code here
            } else if (command == snapPicCommand) {//GEN-LINE:|7-commandAction|5|37-preAction
                // write pre-action user code here
                capture();
//GEN-LINE:|7-commandAction|6|37-postAction
                // write post-action user code here
            }//GEN-BEGIN:|7-commandAction|7|21-preAction
        } else if (displayable == loginForm) {
            if (command == exitCommand) {//GEN-END:|7-commandAction|7|21-preAction
                // write pre-action user code here
                exitMIDlet();//GEN-LINE:|7-commandAction|8|21-postAction
                // write post-action user code here
            } else if (command == loginCommand) {//GEN-LINE:|7-commandAction|9|18-preAction
                // write pre-action user code here
//                System.out.println("NRIC: " + NRICLoginFormtextField.getString());
//                System.out.println("Password: " + passwordLoginFormtextField.getString());
//                if (!NRICLoginFormtextField.getString().equals("") && !passwordLoginFormtextField.getString().equals("")) {
//                  loginAccount();
//                 } else {
//
//                    System.out.println("Empty Fields Detected");
//                    alert = new Alert("Error", "Username or Password cannot be blank.", null, AlertType.ERROR);
//                    alert.setTimeout(2000); //Timeout in 2 seconds
//                    switchDisplayable(alert, getLoginForm());
//                }
//GEN-LINE:|7-commandAction|10|18-postAction
                // write post-action user code here
                showCamera();
            }//GEN-BEGIN:|7-commandAction|11|7-postCommandAction
        }//GEN-END:|7-commandAction|11|7-postCommandAction
        // write post-action user code here
    }//GEN-BEGIN:|7-commandAction|12|
    //</editor-fold>//GEN-END:|7-commandAction|12|

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

    //<editor-fold defaultstate="collapsed" desc=" Generated Getter: reportForm ">//GEN-BEGIN:|15-getter|0|15-preInit
    /**
     * Returns an initiliazed instance of reportForm component.
     * @return the initialized component instance
     */
    public Form getReportForm() {
        if (reportForm == null) {//GEN-END:|15-getter|0|15-preInit
            // write pre-init user code here
            reportForm = new Form("Report", new Item[] { });//GEN-LINE:|15-getter|1|15-postInit
            // write post-init user code here
        }//GEN-BEGIN:|15-getter|2|
        return reportForm;
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
            cameraCaptureForm = new Form("Test Camera & GPS", new Item[] { getTestLocationstringItem() });//GEN-BEGIN:|25-getter|1|25-postInit
            cameraCaptureForm.addCommand(getExitCommand1());
            cameraCaptureForm.addCommand(getLocationCommand());
            cameraCaptureForm.addCommand(getSnapPicCommand());
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
            snapPicCommand = new Command("Snap Picture", Command.OK, 0);//GEN-LINE:|36-getter|1|36-postInit
            // write post-init user code here
        }//GEN-BEGIN:|36-getter|2|
        return snapPicCommand;
    }
    //</editor-fold>//GEN-END:|36-getter|2|

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

    static public String urlEncode(String sUrl) {


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
                default:
                    urlOK.append(ch);
                    break;
            }
        }
        return urlOK.toString();
    }

    private void getLocation() {
        new Thread() {

            public void run() {
                try {
                    testLocationstringItem.setText("");

                    if (provider.getState() == LocationProvider.AVAILABLE) {
                        System.out.println("Provider is available");
                        // 90 secs is the time out
                        Location location = provider.getLocation(90);
                        QualifiedCoordinates c = location.getQualifiedCoordinates();
                        int locationMethod = location.getLocationMethod();
                        double latitude = c.getLatitude();
                        System.out.println("Lat: " + c.getLatitude());
                        double longitude = c.getLongitude();
                        System.out.println("Lon: " + c.getLongitude());
                        float altitude = c.getAltitude();
                        //float hAccuracy = c.getHorizontalAccuracy();
                        //float vAccuracy = c.getVerticalAccuracy();
                        //Coordinates greenwich = new Coordinates(0, 51, 0);
                        //float disGreenwich = c.distance(greenwich);
                        testLocationstringItem.setText("Lat: " + latitude + "Lon: " + longitude + "Alt: " + altitude);


                    } else if (provider.getState() == LocationProvider.TEMPORARILY_UNAVAILABLE) {
                        testLocationstringItem.setText("Unavailable");
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
                    hc = (HttpConnection) Connector.open(loginUserURL + urlEncode("?nric=" + NRICLoginFormtextField.getString() + "&Password=" + passwordLoginFormtextField.getString()));
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

                    if (loginServerMsg.equals("T")) {

                        loginServerMsg = "";
                        userIDLoggedIn = NRICLoginFormtextField.getString();
                        Thread.sleep(500);

                        switchDisplayable(null, getReportForm());


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

    private void reportSubmit() {
        new Thread() {

            public void run() {
                StringBuffer serverMsg = new StringBuffer("");
                HttpConnection hc = null;
                InputStream is = null;


            }
        }.start();
    }

    private void showCamera() {
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

    public void capture() {

        new Thread() {

            public void run() {

                try {
                    // Get the image.
                    byte[] raw = mVideoControl.getSnapshot(null);
                    Image image = Image.createImage(raw, 0, raw.length);

//                    Image thumb = createThumbnail(image);
//
//                    // Place it in the main form.
//                    if (cameraCaptureForm.size() > 0 && cameraCaptureForm.get(0) instanceof StringItem) {
//                        cameraCaptureForm.delete(0);
//                    }
//                    cameraCaptureForm.append(thumb);


                    FileConnection fconn = (FileConnection) Connector.open("file:///SDCard/myfile.jpg", Connector.WRITE);
                    fconn.create();
                    OutputStream out = fconn.openOutputStream();
                    //out.write(image);
                    out.write(raw);
                    out.close();
                    fconn.close();

                    // Flip back to the main form.
                    mDisplay.setCurrent(cameraCaptureForm);

                    // Shut down the player.
                    mPlayer.close();
                    mPlayer = null;
                    mVideoControl = null;
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
        switchDisplayable(a, getReportForm());
        //  mDisplay.setCurrent(a, cameraCaptureForm);
    }
}
