package sceneSwitch;

import javax.swing.JFrame;

public class SceneSwitchContext{
  private JFrame frame;
  private SceneSwitcher sceneSwitcher;
  private SceneHolder sceneHolder;
  
  public SceneSwitchContext(JFrame frame){
    this.frame = frame;
    
    sceneHolder = new SceneHolder();
    
    sceneSwitcher = new SceneSwitcher(frame, sceneHolder);
  }
  
  public void addScene(Class<? extends AbstractScene> sceneClass){
    sceneHolder.addScene(sceneClass);
  }
  
  public void addInitialScene(Class<? extends AbstractScene> sceneClass){
    sceneHolder.addInitialScene(sceneClass);
  }
  
  public void toInitialScene(){
    sceneSwitcher.toInitialScene();
  }
}
