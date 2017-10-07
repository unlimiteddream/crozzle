import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/9/21.
 */
public class Node {

    public Grid grid;
    public List<String> subSolutions;
    public int score;

    public Node() {
        grid=null;
        subSolutions=null;
        score=0;
    }

    public Node copy(){
        Node newNode= new Node();
        newNode.grid=this.grid.copy();
        newNode.subSolutions=new ArrayList<String>();
        Iterator<String> it=this.subSolutions.iterator();
        while (it.hasNext()){
            newNode.subSolutions.add(it.next());
        }
        newNode.score=this.score;
        return newNode;
    }

}
