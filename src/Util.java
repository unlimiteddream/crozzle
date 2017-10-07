import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ty on 2017/9/20.
 */
public class Util {

    public static int[][] interLetterScore={
            {'Z',64},{'X',32},{'Y',32},{'W',16},{'V',16},
            {'T',8},{'S',8},{'R',8},{'Q',8},{'P',8},
            {'N',4}, {'L',4},{'M',4},{'K',4},{'J',4},
            {'H',2},{'G',2}, {'F',2},{'D',2},{'C',2},{'B',2},
            {'A',1},{'E',1},{'I',1},{'O',1},{'U',1}};

    public static int[][] nonInterLetterScore={
            {'Z',0},{'X',0},{'Y',0},{'W',0},{'V',0},
            {'T',0},{'S',0},{'R',0},{'Q',0},{'P',0},
            {'N',0}, {'L',0},{'M',0},{'K',0},{'J',0},
            {'H',0},{'G',0}, {'F',0},{'D',0},{'C',0},{'B',0},
            {'A',0},{'E',0},{'I',0},{'O',0},{'U',0}};

    public Map<Character,Integer> nonInterLetterScoreMap=new HashMap<Character,Integer>();

    public Map<Character,Integer> interLetterScoreMap=new HashMap<Character,Integer>();

    public static List<String> getWordsFromFile(String filePath){
        File file =new File(filePath);
        Reader reader =null;
        StringBuffer sb=new StringBuffer();
        try {
            reader=new InputStreamReader(new FileInputStream(file));
            int ch=0;
            while((ch=reader.read())!=-1){
                sb.append((char)ch);
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] words=sb.toString().split(",");
        List<String> subSolutions=new ArrayList<String>();
        for(int i=0;i<words.length;i++){
            subSolutions.add(words[i]);
        }
        return subSolutions;
    }

    public static boolean haveCh(Character ch,String str){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==ch)
                return true;
        }
        return false;
    }

    public Util() {
        for (int i=0;i<26;i++){
            interLetterScoreMap.put((char)interLetterScore[i][0],interLetterScore[i][1]);
            nonInterLetterScoreMap.put((char)nonInterLetterScore[i][0],nonInterLetterScore[i][1]);
        }
    }

    public int getScore(char crossLetter,String word){
        int score=10;
        int time=0;
        for(int i=0;i<word.length();i++){
            if(time==0&&word.charAt(i)==crossLetter) {
                score += interLetterScoreMap.get(crossLetter);
                time++;
            }
            else
                score+=nonInterLetterScoreMap.get(word.charAt(i));
        }
        return score;
    }
}
