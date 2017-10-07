import java.sql.Time;
import java.util.Date;
import java.util.Timer;

/**
 * Created by ty on 2017/9/21.
 */
public class Grid {

    public int r;
    public int c;
    public char[][] crozzle;
    public Util util = new Util();

    public Grid() {
        this.r = 9;
        this.c = 14;
        crozzle = new char[10][15];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 15; j++) {
                crozzle[i][j] = '.';
            }
        }
    }

    public Grid(int R, int C) {
        this.r = R - 1;
        this.c = C - 1;
        crozzle = new char[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                crozzle[i][j] = '.';
            }
        }
    }

    /**
     * 向表中添加一个单词
     *
     * @param subSolution
     */
    public int insert(String subSolution) {
        if (isEmpty()) {
            int r, c, direction;
            while (true) {
                r = (int) (Math.random() * this.r);
                c = (int) (Math.random() * this.c);
                direction = (int) Math.random() % 2;
                if (canInsertWordAtThisPoint(r, c, direction, subSolution)) {
                    addWord(new Point(r, c), direction, subSolution);
                    break;
                }
            }
            return 10;
        } else if (isOneWordOrEmpty()) {
//            addWord(new Point(r , c - subSolution.length() + 1), 0, subSolution);
//            addWord(new Point(r - subSolution.length() + 1, c), 1, subSolution);
            int r, c, direction;
            while (true) {
                r = (int) (Math.random() * this.r);
                c = (int) (Math.random() * this.c);
                direction = (int) Math.random() % 2;
                if (canInsertWordAtThisPoint(r, c, direction, subSolution)) {
                    addWord(new Point(r, c), direction, subSolution);
                    break;
                }
            }
            return 10;
        } else {
            int bestScore = 0;
            Point bestPoint = new Point(0, 0);
            for (int i = 0; i < 26; i++) {
                if (subSolution.indexOf((char) Util.interLetterScore[i][0]) != -1) {
                    for (int j = 0; j <= r; j++) {
                        for (int k = 0; k <= c; k++) {
                            if (crozzle[j][k] == (char) Util.interLetterScore[i][0] && canInsetWord(j, k, subSolution)) {
                                if (util.getScore(crozzle[j][k], subSolution) > bestScore) {
                                    bestScore = util.getScore(crozzle[j][k], subSolution);
                                    bestPoint = new Point(j, k);
                                }
                            }
                        }
                    }
                }
            }
            insertWord(bestPoint.r, bestPoint.c, subSolution);
            return util.getScore(crozzle[bestPoint.r][bestPoint.c], subSolution);
        }
    }

    public int testInsert(String subSolution) {
        if (isEmpty()) {
            addWord(new Point(0, 0), 0, subSolution);
            return 10;
        } else if (isOneWordOrEmpty()) {
            addWord(new Point(r - subSolution.length() + 1, c), 1, subSolution);
            return 10;
        } else {
            int bestScore = 0;
            Point bestPoint = new Point(0, 0);
            for (int i = 0; i < 26; i++) {
                if (subSolution.indexOf((char) Util.interLetterScore[i][0]) != -1) {
                    for (int j = 0; j <= r; j++) {
                        for (int k = 0; k <= c; k++) {
                            if (crozzle[j][k] == (char) Util.interLetterScore[i][0] && canInsetWord(j, k, subSolution)) {
                                if (util.getScore(crozzle[j][k], subSolution) > bestScore) {
                                    bestScore = util.getScore(crozzle[j][k], subSolution);
                                    bestPoint = new Point(j, k);
                                }
                            }
                        }
                    }
                }
            }
            return util.getScore(crozzle[bestPoint.r][bestPoint.c], subSolution);
        }
    }

    public boolean isOneWordOrEmpty() {
        int r = -1;
        int c = -1;
        for (int i = 0; i <= this.r; i++) {
            for (int j = 0; j <= this.c; j++) {
                if (crozzle[i][j] != '.') {
                    if (r == -1) {
                        r = i;
                        c = j;
                    } else if (i != r && j != c)
                        return false;
                    else {
                        r = i;
                        c = j;
                    }
                }
            }
        }
        return true;
    }

    public boolean isEmpty() {
        for (int i = 0; i <= this.r; i++) {
            for (int j = 0; j <= this.c; j++) {
                if (crozzle[i][j] != '.') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean canInsetWord(int r, int c, String subSolution) {
        if (r > this.r || c > this.c)
            return false;
        if (crozzle[r][c] == '.')
            return false;
        else if (subSolution.indexOf(crozzle[r][c]) == -1)
            return false;
        else if ((r == this.r || crozzle[r + 1][c] == '.') && (r == 0 || crozzle[r - 1][c] == '.')) { //试试纵向可不可以加
            int crossPoint = subSolution.indexOf(crozzle[r][c]);
            //有改
            if (r - crossPoint < 0 || r - crossPoint + subSolution.length() - 1 > this.r || (r - crossPoint - 1 >= 0 && crozzle[r - crossPoint - 1][c] != '.') || (r - crossPoint + subSolution.length() <= this.r && crozzle[r - crossPoint + subSolution.length()][c] != '.'))
                return false;
            else {
                for (int i = r - crossPoint; i < r - crossPoint + subSolution.length(); i++) {
                    for (int j = c - 1; j <= c + 1; j++) {
                        if (i == r)
                            continue;
                        else if (j < 0 || j > this.c)
                            continue;
                        else if (crozzle[i][j] != '.')
                            return false;
                    }
                }
            }
            return true;
        } else if ((c == this.c || crozzle[r][c + 1] == '.') && (c == 0 || crozzle[r][c - 1] == '.')) {
            int crossPoint = subSolution.indexOf(crozzle[r][c]);
            if (c - crossPoint < 0 || c - crossPoint + subSolution.length() - 1 > this.c || (c - crossPoint - 1 >= 0 && crozzle[r][c - crossPoint - 1] != '.') || (c - crossPoint + subSolution.length() <= this.c && crozzle[r][c - crossPoint + subSolution.length()] != '.'))
                return false;
            else {
                for (int i = c - crossPoint; i < c - crossPoint + subSolution.length(); i++) {
                    for (int j = r - 1; j <= r + 1; j++) {
                        if (i == c)
                            continue;
                        else if (j < 0 || j > this.r)
                            continue;
                        else if (crozzle[j][i] != '.')
                            return false;
                    }

                }
            }
            return true;
        } else
            return false;
    }


    private void insertWord(int r, int c, String subSolution) {
        if ((r == this.r || crozzle[r + 1][c] == '.') && (r == 0 || crozzle[r - 1][c] == '.')) { //试试纵向可不可以加
            for (int i = 0; i < subSolution.length(); i++) {
                int crossPoint = subSolution.indexOf(crozzle[r][c]);
                crozzle[r - crossPoint + i][c] = subSolution.charAt(i);
            }
        } else {
            for (int i = 0; i < subSolution.length(); i++) {
                int crossPoint = subSolution.indexOf(crozzle[r][c]);
                crozzle[r][c - crossPoint + i] = subSolution.charAt(i);
            }
        }
    }

    private void addWord(Point begainPoint, int direction, String subSolution) {
        int r = begainPoint.r;
        int c = begainPoint.c;
        if (direction == 0) {
            for (int i = 0; i < subSolution.length(); i++) {
                crozzle[r][c + i] = subSolution.charAt(i);
            }
        } else {
            for (int i = 0; i < subSolution.length(); i++) {
                crozzle[r + i][c] = subSolution.charAt(i);
            }
        }
    }

    public void printImage() {
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                System.out.print(" " + crozzle[i][j] + " ");
            }
            System.out.print("\n");
        }
    }

    private boolean canIsertLetter(int r, int c, char letter) {
        //System.out.println(r+" "+c);
        if (r > this.r || c > this.c || r < 0 || c < 0)
            return false;
        else if (crozzle[r][c] == letter)
            return true;
        else if ((r == this.r || crozzle[r + 1][c] == '.' || crozzle[r + 1][c] == letter) && (r == 0 || crozzle[r - 1][c] == '.' || crozzle[r - 1][c] == letter) &&
                (c == 0 || crozzle[r][c - 1] == '.' || crozzle[r][c - 1] == letter) && (c == this.c || crozzle[r][c + 1] == '.' || crozzle[r][c + 1] == letter))
            return true;
        else
            return false;
    }

    public boolean canInsertWord(String word) {
        if (isEmpty())
            return true;
        else {
            for (int i = 0; i <= r; i++) {
                for (int j = 0; j <= c; j++) {
                    if (canInsetWord(i, j, word))
                        return true;
                }
            }
            return false;
        }
    }


    public Grid copy() {
        Grid newGrid = new Grid();
        for (int i = 0; i <= r; i++) {
            for (int j = 0; j <= c; j++) {
                newGrid.crozzle[i][j] = this.crozzle[i][j];
            }
        }
        return newGrid;
    }

    private boolean canInsertWordAtThisPoint(int r, int c, int direction, String subSolution) {
        if (r >= 0 && r <= this.r && c >= 0 && c <= this.c) {  //随即到的点是正确的
            if (direction == 0) {  //横向
                if (r + subSolution.length() - 1 > this.r) {  //在这个点加上这个单词后不会越界
                    for (int i = r - 1; i <= r + 1; i++) {
                        for (int j = c - 1; j < c + subSolution.length() + 1; j++) {
                            if (i < 0 || i > this.r || j < 0 || j > this.c)
                                continue;
                            else if (crozzle[i][j] != '.')
                                return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                if (c + subSolution.length() - 1 > this.r) {  //在这个点加上这个单词后不会越界
                    for (int i = r - 1; i < r + subSolution.length() + 1; i++) {
                        for (int j = c - 1; j <= c + 1; j++) {
                            if (i < 0 || i > this.r || j < 0 || j > this.c)
                                continue;
                            else if (crozzle[i][j] != '.')
                                return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}
