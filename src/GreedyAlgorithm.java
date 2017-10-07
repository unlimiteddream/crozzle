import sun.rmi.log.LogInputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ty on 2017/9/22.
 */
public class GreedyAlgorithm {

    public Util util=new Util();

    public long begintime = System.currentTimeMillis();

    public Node bestNode=new Node();

    public static void main(String[] args) {
        GreedyAlgorithm greedyAlgorithm=new GreedyAlgorithm();

        Node rootNode=new Node();
        rootNode.grid=new Grid();
        rootNode.subSolutions=Util.getWordsFromFile("E:\\MarkingTest1.wls");
        greedyAlgorithm.insertChildren(rootNode);

        greedyAlgorithm.bestNode.grid.printImage();
        System.out.println(greedyAlgorithm.bestNode.score);
        System.out.println("\n\n");

    }

    public void insertChildren(Node node){

        if(System.currentTimeMillis()-begintime>1000*10||bestNode.score>=1000){
            bestNode.grid.printImage();
            System.out.println(bestNode.score);
            System.out.println("\n\n");
            System.exit(0);
        }

        List<String> subSols=getBestSubSolutions(node.grid,node.subSolutions);
        if(subSols.size()==0){
            if(node.score>bestNode.score)
                bestNode=node.copy();
        }else{
            Iterator<String> it=subSols.iterator();
            while(it.hasNext()){
                String subSolution=it.next();
                Node chilsNode=node.copy();
                chilsNode.score+=chilsNode.grid.insert(subSolution);
                chilsNode.subSolutions.remove(subSolution);

//                node.grid.printImage();
//                System.out.println(node.score);
//                System.out.println("\n\n");

                insertChildren(chilsNode);
            }
        }
    }

    public List<String> getBestSubSolutions(Grid grid,List<String> subSolutions){
        int bestScore=0;
        Iterator<String> it=subSolutions.iterator();
        List<String> bestSubSolutions=new ArrayList<String>();
        if(grid.isOneWordOrEmpty()){
            //找高分优先
//            while(it.hasNext()){
//                String word=it.next();
//                if( wordScore(word)>bestScore&&grid.canInsertWord(word)){
//                    bestScore=wordScore(word);
//                    bestSubSolutions=new ArrayList<String>();
//                    bestSubSolutions.add(word);
//                }else if(wordScore(word)==bestScore&&grid.canInsertWord(word)){
//                    bestSubSolutions.add(word);
//                }
//            }
            //找字母多优先
            int largestLetterNumber=0;
            while(it.hasNext()){
                String word=it.next();
                if(word.length()>largestLetterNumber){
                    largestLetterNumber=word.length();
                    bestSubSolutions=new ArrayList<String>();
                    bestSubSolutions.add(word);
                }
            }
        }else{
            while(it.hasNext()){
                String word=it.next();
                if( grid.canInsertWord(word)&&grid.testInsert(word)>bestScore){
                    bestScore=grid.testInsert(word);
                    bestSubSolutions=new ArrayList<String>();
                    if(word.indexOf('Z')!=-1)
                        bestSubSolutions.add(0,word);
                    else
                        bestSubSolutions.add(word);
                }else if(grid.canInsertWord(word)&&grid.testInsert(word)==bestScore){
                    if(word.indexOf('Z')!=-1)
                        bestSubSolutions.add(0,word);
                    else
                        bestSubSolutions.add(word);
                }
            }
            Iterator<String> it1=bestSubSolutions.iterator();
        }
        return bestSubSolutions;
    }

    public int wordScore(String word){
        int score=10;
        for(int i=0;i<word.length();i++){
            score+=util.interLetterScoreMap.get(word.charAt(i));
        }
        return score;
    }
}
