package sceneSwitch;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class SceneSwitcher{ 
  private JFrame frame;
  private SceneHolder sceneHolder;
  
  private SceneSwitcher(){}
  
  public SceneSwitcher(JFrame frame, SceneHolder sceneHolder){
    
    this.frame = frame;
    this.sceneHolder = sceneHolder;
    
  }
  
  public void toScene(AbstractScene scene){
    if(!scene.isInitialized())
      initialize(scene);
      
    frame.getContentPane().removeAll();
    frame.add(scene);
    frame.revalidate();
    frame.repaint();
  }
  
  public void toInitialScene(){
    toScene(sceneHolder.getInitialScene());
  }
  
  public void initialize(AbstractScene scene){
    scene.init();
    giveActionListenerToButtons(scene);
  }
  
  private void giveActionListenerToButtons(AbstractScene scene){
    Class<? extends AbstractScene> sceneClass = scene.getClass();
    
    Field[] allFields = sceneClass.getDeclaredFields();
    
    for(Field field : allFields){
      if(isASceneSwitchButton(field)){
        JButton button = getButtonWithField(field, scene);
        ActionListener action = getActionListenerWithField(field, scene);
        
        button.addActionListener(action);
        
      }
    }
  }
  
  private boolean isASceneSwitchButton(Field field){
    field.setAccessible(true);
    if(field.getType().isAssignableFrom(JButton.class) && field.isAnnotationPresent(ToScene.class)){
      return true;
    }
    
    return false;
  }
  
  private JButton getButtonWithField(Field field, AbstractScene scene){
    JButton answer = null;
    
    try{
      answer = (JButton) field.get(scene);
    }catch(IllegalAccessException exception){
      exception.printStackTrace();
    }
    return answer;
  }
  
  private SceneSwitchAction getActionListenerWithField(Field field, AbstractScene scene){
    SceneSwitchAction answer = null;
    
    Class<? extends AbstractScene> destinationClass = field.getAnnotation(ToScene.class).destination();
    
    AbstractScene destination = null;
    
    try{
      destination = sceneHolder.getScene(destinationClass);
    }catch(NoSuchSceneAddedException exception){
      exception.printStackTrace();
    }
    answer = new SceneSwitchAction(destination, this);
    
    return answer;
  }
  
}
