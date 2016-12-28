package store.controller.usuario;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
 
@SuppressWarnings("serial")
@SessionScoped
@ManagedBean
public class BasicView implements Serializable {
     
    private TreeNode root;
     
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("CATALOGO", null);
//        TreeNode node0 = new DefaultTreeNode("Node 0", root);
        TreeNode node1 = new DefaultTreeNode("Node 1", root);
         
//        TreeNode node01 = new DefaultTreeNode("Node 0.1", node0);
         
//        TreeNode node10 = new DefaultTreeNode("Node 1.0", node1);
         
        node1.getChildren().add(new DefaultTreeNode("Node 1.1"));
 
    }
 
    public TreeNode getRoot() {
        return root;
    }
}
