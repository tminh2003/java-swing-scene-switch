package sceneSwitch;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SceneSwitchAction implements ActionListener{
  private AbstractScene destination;
  private SceneSwitcher sceneSwitcher;
  
  private SceneSwitchAction(){}
  
  public SceneSwitchAction(AbstractScene destination, SceneSwitcher sceneSwitcher){
    this.destination = destination;
    this.sceneSwitcher = sceneSwitcher;
  }
  
  public void actionPerformed(ActionEvent actionEvent){
    sceneSwitcher.toScene(destination);
  }
  
}
