import java.util.ArrayList;
import java.util.List;

/**
 * Created by ty on 2017/9/19.
 */
public class WordsList {

    public List<Word> wordsList;

    public WordsList(){
        wordsList=new ArrayList<Word>();
    }

    public void addWord(Word word){
        wordsList.add(word);
    }

}
