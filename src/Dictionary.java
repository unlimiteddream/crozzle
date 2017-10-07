import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ty on 2017/9/20.
 */
public class Dictionary {

    public Map<Character,WordsList> dictionary=new HashMap<Character,WordsList>();

    public Dictionary(){
        List<String> words=Util.getWordsFromFile("E:\\Test1.wls");
        int i='A';
        WordsList wl=null;
        for(;i<='Z';i++){
            wl=new WordsList();
            dictionary.put((char)i,wl);
//            for(int j=0;j<words.size();j++){
//                if(Util.haveCh((char)i,words[j])){
//                    wl.addWord(new Word(words[j]));
//                }
//            }
        }
    }


}
