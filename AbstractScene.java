package sceneSwitch;

import javax.swing.JPanel;

public abstract class AbstractScene extends JPanel{
  
  public abstract void init();
  public abstract boolean isInitialized();
  
}
