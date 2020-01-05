//Note: All of your TrieMapInterface method implementations must function recursively
//I have left the method signatures from my own solution, which may be useful hints in how to approach the problem
//You are free to change/remove/etc. any of the methods, as long as your class still supports the TrieMapInterface
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TrieMap implements TrieMapInterface{
  TrieMapNode root;
  boolean word;


  
  public TrieMap(){
    root = new TrieMapNode();
  }


  
  //Indirectly recursive method to meet definition of interface
  public void put(String key, String value){

    put(root,key,value);
    
  }
  
  //Recursive method
  public void put(TrieMapNode current, String curKey, String value){

    if (current != root && curKey.length() ==0){
      current.setValue(value);
      return;
    }

    char nextLetter = curKey.charAt(0);
    //String key = String.valueOf(nextLetter);

    if(current.getChildren().get(nextLetter) == null ){
      current.getChildren().put(nextLetter,new TrieMapNode());
    }


    put(current.getChildren().get(nextLetter),curKey.substring(1),value);


    
  }
  
  //Indirectly recursive method to meet definition of interface
  public String get(String key){
    return get(root,key);
  }
  
  //Recursive method
  public String get(TrieMapNode current, String curKey){
      if (current != root && curKey.length() ==0){
        return current.getValue();
      }

      char nextLetter = curKey.charAt(0);

      if(!current.getChildren().containsKey(nextLetter) ){
        return null;
      }

    return  get(current.getChildren().get(nextLetter), curKey.substring(1));
  }
  
  //Indirectly recursive method to meet definition of interface
  public boolean containsKey(String key){
    return containsKey(root,key);
  }
  
  //Recursive method
  public boolean containsKey(TrieMapNode current, String curKey){
    if (current != root && curKey.length() ==0){
      if(current.getValue()!=null){
        return true;
      }else{
        return false;
      }
    }

    char nextLetter = curKey.charAt(0);

    if(!current.getChildren().containsKey(curKey.charAt(0)) ){
      return false;
    }

    return containsKey(current.getChildren().get(nextLetter), curKey.substring(1));

  }
  
  //Indirectly recursive method to meet definition of interface
  public ArrayList<String> getValuesForPrefix(String prefix){
    TrieMapNode t = findNode(root,prefix);

    if(t == null){
      return new ArrayList<String>();
    }


    return getValues(t);
  }
  
  //Recursive helper function to find node that matches a prefix
  public TrieMapNode findNode(TrieMapNode current, String curKey){

    if (current != root && curKey.length() ==0){
      return current;
    }

    char nextLetter = curKey.charAt(0);

    if(!current.getChildren().containsKey(nextLetter) ){
      return null;
    }

    return  findNode(current.getChildren().get(nextLetter), curKey.substring(1));
  }
  
  //Recursive helper function to get all keys in a node's subtree
  public ArrayList<String> getValues(TrieMapNode current){
    ArrayList<String> t = new ArrayList<String>();

    if(current.getValue()!=null){
      t.add(current.getValue());
    }

    for(TrieMapNode n: current.getChildren().values()){
      if(n!=null){
        t.addAll(getValues(n));
      }
    }
    return t;
  }
  
  //Indirectly recursive method to meet definition of interface
  public void print(){
    print(root);
    
  }
  
  //Recursive method to print values in tree
  public void print(TrieMapNode current){
    if(current.getValue() != null){
      System.out.println(current.getValue());
    }
    for(TrieMapNode n: current.getChildren().values()){
      print(n);
    }
  }
  
  public static void main(String[] args){
    //You can add some code in here to test out your TrieMap initially
    //The TrieMapTester includes a more detailed test
    TrieMap t = new TrieMap();
    t.put("a","a");
    t.put("ar","ar");
    System.out.println(t.get("r"));
    t.print();
  }
}