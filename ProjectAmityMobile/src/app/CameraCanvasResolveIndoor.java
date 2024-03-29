package app;
import javax.microedition.lcdui.*;
import javax.microedition.media.MediaException;
import javax.microedition.media.control.VideoControl;


public class CameraCanvasResolveIndoor
    extends Canvas {

  private AmityMIDlet mAmityMIDlet;

  public CameraCanvasResolveIndoor(AmityMIDlet midlet, VideoControl videoControl) {
    int width = getWidth();
    int height = getHeight();

    mAmityMIDlet = midlet;

    videoControl.initDisplayMode(VideoControl.USE_DIRECT_VIDEO, this);
    try {
      //videoControl.setDisplayLocation(2, 2);
      videoControl.setDisplaySize(width, height);
      //Need to..
    }
    catch (MediaException me) {
      try { videoControl.setDisplayFullScreen(true); }
      catch (MediaException me2) {}
    }
    videoControl.setVisible(true);
  }

  public void paint(Graphics g) {
    int width = getWidth();
    int height = getHeight();

    // Draw a green border around the VideoControl.
//    g.setColor(0x00ff00);
//    g.drawRect(0, 0, width - 1, height - 1);
//    g.drawRect(1, 1, width - 3, height - 3);
  }

  public void keyPressed(int keyCode) {

    int action = getGameAction(keyCode);
    if (action == FIRE)
      mAmityMIDlet.captureResolveIndoor();
  }
}
