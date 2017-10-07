import java.util.Iterator;

/**
 * Created by ty on 2017/9/20.
 */
public class Test {

    public static void main(String[] args){

//        String[] words=Util.getWordsFromFile("E:\\Test1.wls");
//        for(int i=0;i<words.length;i++){
//            System.out.println(words[i]);
//        }

//        Dictionary dictionary =new Dictionary();
//
//        WordsList wordsList=dictionary.dictionary.get('Z');
//        Iterator<Word> it=wordsList.wordsList.iterator();
//        while(it.hasNext()){
//            System.out.println(it.next().word);
//        }

//        Grid grid = new Grid(10,15);
//
//        grid.insert("HGFDHFGX");
//        grid.insert("AADFG");
//        grid.insert("XBBB");
//
//        grid.printImage();

        GreedyAlgorithm greedyAlgorithm=new GreedyAlgorithm();
        System.out.println(greedyAlgorithm.wordScore("ZZ"));
    }
}
