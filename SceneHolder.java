package sceneSwitch;

import java.util.ArrayList;

public class SceneHolder{
  private ArrayList<AbstractScene> allScenes;
  private AbstractScene initialScene;
  
  public void addScene(Class<? extends AbstractScene> sceneClass){
    if(allScenes == null)
      allScenes = new ArrayList<AbstractScene>();
    
    if(sceneAlreadyExists(sceneClass))
      return;
      
      try{
        allScenes.add(sceneClass.getDeclaredConstructor().newInstance());
      }catch(Exception exception){
        exception.printStackTrace();
      }
      
  }
  
  public void addInitialScene(Class<? extends AbstractScene> sceneClass){
    if(allScenes == null)
      allScenes = new ArrayList<AbstractScene>();
    
    if(sceneAlreadyExists(sceneClass)){
      return;
    }
      
      try{
        AbstractScene scene = sceneClass.getDeclaredConstructor().newInstance();
        initialScene = scene;
        allScenes.add(scene);
      }catch(Exception exception){
        exception.printStackTrace();
      }
  }
  
  public AbstractScene getScene(Class<? extends AbstractScene> sceneClass) throws NoSuchSceneAddedException{
    AbstractScene answer = null;
    
    if(allScenes == null)
      throw new NoSuchSceneAddedException();
      
    for(AbstractScene scene : allScenes){
      if(scene.getClass().equals(sceneClass))
        answer = scene;
    }
      
    if(answer == null)
      throw new NoSuchSceneAddedException();
    
    return answer;
  }
  
  public AbstractScene getInitialScene(){
    AbstractScene answer = null;
    
    try{
      answer = getScene(initialScene.getClass());
    }catch(NoSuchSceneAddedException exception){
      exception.printStackTrace();
    }
    
    return answer;
  }
  
  private boolean sceneAlreadyExists(Class<? extends AbstractScene> sceneClass){
    for(AbstractScene scene : allScenes){
      if(scene.getClass().equals(sceneClass))
        return true;
    }
    
    return false;
  }
}
