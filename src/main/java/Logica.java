//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JPanel;

public class Logica extends JPanel {
    public boolean userWin = false;
    public boolean computerWin = false;
    char[][] nullField = new char[8][8];
    char[][] field = new char[8][8];
    private static boolean userMadeQueue = false;
    private static boolean userMadeBigQueue = false;
    private static boolean userMadeKill = false;
    private static boolean userMadeBigKill = false;
    private static boolean attackAgain = false;
    private static boolean attackBigAgain = false;
    private static final int cell_WINDOW_WIDTH = 83;
    private static final int cell_WINDOW_HEIGHT = 83;
    private static int takeFukX;
    private static int takeFukY;
    private static int takeFukBigX;
    private static int takeFukBigY;
    private static int newPositionTakeFukX;
    private static int newPositionTakeFukY;
    private static int newPositionTakeFukBigX;
    private static int newPositionTakeFukBigY;
    private static boolean computerMadeKill;
    private static boolean computerMadeBigKill;
    private static int positionWhoKillX;
    private static int positionWhoBigKillX;
    private static int positionWhoKillY;
    private static int positionWhoBigKillY;

    public Logica() {
        this.field = this.fillBox(this.nullField);
        this.printBoxInTerminal(this.field);
    }

    public void userClickMethod(int x, int y) {
        System.out.println("*******************userClickMethod***********************");
        int clickX = x / 83;
        int clickY = y / 83;
        System.out.println("attackAgain: " + attackAgain);
        System.out.println("attackBigAgain: " + attackBigAgain);
        if (attackAgain) {
            System.out.println("ПОВТОРНАЯ АТАКА");
            this.userKill(clickX, clickY);
        } else if (attackBigAgain) {
            System.out.println("ПОВТОРНАЯ АТАКА ДАМКИ");
            this.userBigKill(clickX, clickY);
        } else {
            System.out.println("Ход игрока");

            for(int i = 0; i < this.field.length; ++i) {
                for(int j = 0; j < this.field[0].length; ++j) {
                    if (this.field[i][j] == '9') {
                        this.field[i][j] = '0';
                    }
                }
            }

            List<Integer> list = this.hasThreeOrSix(this.field);
            if (list.isEmpty()) {
                System.out.println("Первый клик");
                if (this.field[clickX][clickY] == '2') {
                    this.field[clickX][clickY] = '3';
                }

                if (this.field[clickX][clickY] == '5') {
                    this.field[clickX][clickY] = '6';
                }

                this.printBoxInTerminal(this.field);
            } else {
                boolean checkCanKillSecondClick = this.userCanKill();
                List<Integer> listSix = new ArrayList();

                int j;
                for(int i = 0; i < this.field.length; ++i) {
                    for(j = 0; j < this.field[0].length; ++j) {
                        if (this.field[i][j] == '6') {
                            listSix.add(i);
                            listSix.add(j);
                        }
                    }
                }

                boolean checkCanKillSecondBigClick = this.userCanBigKill(listSix);
                j = (Integer)list.get(0);
                int b = (Integer)list.get(1);
                if (this.field[j][b] == '3') {
                    this.userQueue(clickX, clickY);
                } else {
                    this.userBigQueue(clickX, clickY, j, b);
                }

                if (checkCanKillSecondClick && !userMadeKill && userMadeQueue) {
                    if (this.field[takeFukX][takeFukY] == '2' || this.field[takeFukX][takeFukY] == '3') {
                        this.field[takeFukX][takeFukY] = '9';
                    }

                    if (this.field[takeFukX][takeFukY] == '0') {
                        this.field[takeFukX][takeFukY] = '9';
                        this.field[newPositionTakeFukX][newPositionTakeFukY] = '0';
                    }
                }

                if (checkCanKillSecondBigClick && !userMadeBigKill && userMadeBigQueue) {
                    if (this.field[takeFukBigX][takeFukBigY] == '5' || this.field[takeFukBigX][takeFukBigY] == '6') {
                        this.field[takeFukBigX][takeFukBigY] = '9';
                    }

                    if (this.field[takeFukBigX][takeFukBigY] == '0') {
                        this.field[takeFukBigX][takeFukBigY] = '9';
                        this.field[newPositionTakeFukBigX][newPositionTakeFukBigY] = '0';
                    }
                }

                int i;
                int j;
                if (!attackAgain) {
                    for(i = 0; i < this.field.length; ++i) {
                        for(j = 0; j < this.field[0].length; ++j) {
                            if (this.field[i][j] == '3' && (i != x || j != y)) {
                                this.field[i][j] = '2';
                            }
                        }
                    }
                }

                if (!attackBigAgain) {
                    for(i = 0; i < this.field.length; ++i) {
                        for(j = 0; j < this.field[0].length; ++j) {
                            if (this.field[i][j] == '6' && (i != x || j != y)) {
                                this.field[i][j] = '5';
                            }
                        }
                    }
                }

                System.out.println("Еще ход игрока");
                this.printBoxInTerminal(this.field);
                if (userMadeQueue || userMadeBigQueue) {
                    System.out.println("computerQueue: ");
                    this.computerQueue();
                }
            }
        }

    }

    public List<Integer> country(int clickX, int clickY) {
        System.out.println(clickX);
        System.out.println(clickY);
        System.out.println("Внутри country");
        List<Integer> country = new ArrayList();

        int buffer;
        int i;
        int j;
        try {
            buffer = 0;
            i = clickX + 1;

            for(j = clickY + 1; i < this.field.length || j < this.field[0].length; ++j) {
                if (this.field[i][j] == '2' || this.field[i][j] == '5') {
                    ++buffer;
                }

                if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i + 1][j + 1] != '0') {
                    ++buffer;
                }

                if (buffer == 0 && (this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i + 1][j + 1] == '0') {
                    System.out.println("дамка может атаковать вправо вниз - 1");
                    country.add(1);
                }

                ++i;
            }
        } catch (Exception var10) {
        }

        try {
            buffer = 0;
            i = clickX - 1;

            for(j = clickY - 1; i >= 0 || j >= 0; --j) {
                if (this.field[i][j] == '2' || this.field[i][j] == '5') {
                    ++buffer;
                }

                if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i - 1][j - 1] != '0') {
                    ++buffer;
                }

                if (buffer == 0 && (this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i - 1][j - 1] == '0') {
                    System.out.println("дамка может атаковать влево вверх - 2");
                    country.add(2);
                }

                --i;
            }
        } catch (Exception var9) {
        }

        try {
            buffer = 0;
            i = clickX - 1;

            for(j = clickY + 1; i >= 0 || j < this.field[0].length; ++j) {
                if (this.field[i][j] == '2' || this.field[i][j] == '5') {
                    ++buffer;
                }

                if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i - 1][j + 1] != '0') {
                    ++buffer;
                }

                if (buffer == 0 && (this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i - 1][j + 1] == '0') {
                    System.out.println("дамка может атаковать вправо вверх - 3");
                    country.add(3);
                }

                --i;
            }
        } catch (Exception var8) {
        }

        try {
            buffer = 0;
            i = clickX + 1;

            for(j = clickY - 1; i < this.field.length || j >= 0; --j) {
                if (this.field[i][j] == '2' || this.field[i][j] == '5') {
                    ++buffer;
                }

                if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i + 1][j - 1] != '0') {
                    ++buffer;
                }

                if (buffer == 0 && (this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i + 1][j - 1] == '0') {
                    System.out.println("дамка может атаковать влево вниз - 4");
                    country.add(4);
                }

                ++i;
            }
        } catch (Exception var7) {
        }

        return country;
    }

    public List<Integer> checking(int x, int y, List<Integer> listCountry) {
        System.out.println("полученные координаты '6' x и y -> x: " + x + ", y: " + y);
        List<Integer> list1 = new ArrayList();
        List<Integer> list2 = new ArrayList();
        List<Integer> list3 = new ArrayList();
        List<Integer> list4 = new ArrayList();

        for(int w = 0; w < listCountry.size(); ++w) {
            int count;
            int i;
            int j;
            if ((Integer)listCountry.get(w) == 1) {
                try {
                    count = 0;
                    i = x + 1;

                    for(j = y + 1; i < this.field.length && j < this.field[0].length; ++j) {
                        if (count == 1 && this.field[i][j] == '0') {
                            list1.add(i);
                            list1.add(j);
                        }

                        if (this.field[i][j] != '0') {
                            ++count;
                        }

                        ++i;
                    }
                } catch (Exception var12) {
                }
            }

            if ((Integer)listCountry.get(w) == 2) {
                try {
                    count = 0;
                    i = x - 1;

                    for(j = y - 1; i >= 0 && j >= 0; --j) {
                        if (count == 1 && this.field[i][j] == '0') {
                            list2.add(i);
                            list2.add(j);
                        }

                        if (this.field[i][j] != '0') {
                            ++count;
                        }

                        --i;
                    }
                } catch (Exception var13) {
                }
            }

            if ((Integer)listCountry.get(w) == 3) {
                try {
                    count = 0;
                    i = x - 1;

                    for(j = y + 1; i >= 0 && j < this.field[0].length; ++j) {
                        if (count == 1 && this.field[i][j] == '0') {
                            list3.add(i);
                            list3.add(j);
                        }

                        if (this.field[i][j] != '0') {
                            ++count;
                        }

                        --i;
                    }
                } catch (Exception var14) {
                }
            }

            if ((Integer)listCountry.get(w) == 4) {
                try {
                    count = 0;
                    i = x + 1;

                    for(j = y - 1; i < this.field.length && j >= 0; --j) {
                        if (count == 1 && this.field[i][j] == '0') {
                            list4.add(i);
                            list4.add(j);
                        }

                        if (this.field[i][j] != '0') {
                            ++count;
                        }

                        ++i;
                    }
                } catch (Exception var15) {
                }
            }
        }

        System.out.println("Список координат клеток, куда может атаковать '6' без учета продолжения атаки: ");
        System.out.println("list1: " + String.valueOf(list1));
        System.out.println("list2: " + String.valueOf(list2));
        System.out.println("list3: " + String.valueOf(list3));
        System.out.println("list4: " + String.valueOf(list4));
        return this.checkingEveryCoordinata(list1, list2, list3, list4);
    }

    public List<Integer> checkingEveryCoordinata(List<Integer> listSix1, List<Integer> listSix2, List<Integer> listSix3, List<Integer> listSix4) {
        List<Integer> list = new ArrayList();
        List<Integer> One = new ArrayList(listSix1);
        List<Integer> Two = new ArrayList(listSix2);
        List<Integer> Three = new ArrayList(listSix3);
        List<Integer> Fourth = new ArrayList(listSix4);

        int x;
        int number;
        int c;
        int a;
        int b;
        while(!listSix1.isEmpty()) {
            x = (Integer)listSix1.remove(0);
            number = (Integer)listSix1.remove(0);

            try {
                c = 0;
                a = x + 1;

                for(b = number + 1; a < this.field.length || b < this.field[0].length; ++b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b + 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b + 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    ++a;
                }
            } catch (Exception var31) {
            }

            try {
                c = 0;
                a = x - 1;

                for(b = number - 1; a >= 0 || b >= 0; --b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b - 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b - 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    --a;
                }
            } catch (Exception var30) {
            }

            try {
                c = 0;
                a = x - 1;

                for(b = number + 1; a >= 0 || b < this.field[0].length; ++b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b + 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b + 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    --a;
                }
            } catch (Exception var29) {
            }

            try {
                c = 0;
                a = x + 1;

                for(b = number - 1; a < this.field.length || b >= 0; --b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b - 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b - 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    ++a;
                }
            } catch (Exception var28) {
            }
        }

        while(!listSix2.isEmpty()) {
            x = (Integer)listSix2.remove(0);
            number = (Integer)listSix2.remove(0);

            try {
                c = 0;
                a = x + 1;

                for(b = number + 1; a < this.field.length || b < this.field[0].length; ++b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b + 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b + 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    ++a;
                }
            } catch (Exception var27) {
            }

            try {
                c = 0;
                a = x - 1;

                for(b = number - 1; a >= 0 || b >= 0; --b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b - 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b - 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    --a;
                }
            } catch (Exception var26) {
            }

            try {
                c = 0;
                a = x - 1;

                for(b = number + 1; a >= 0 || b < this.field[0].length; ++b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b + 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b + 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    --a;
                }
            } catch (Exception var25) {
            }

            try {
                c = 0;
                a = x + 1;

                for(b = number - 1; a < this.field.length || b >= 0; --b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b - 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b - 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    ++a;
                }
            } catch (Exception var24) {
            }
        }

        while(!listSix3.isEmpty()) {
            x = (Integer)listSix3.remove(0);
            number = (Integer)listSix3.remove(0);

            try {
                c = 0;
                a = x + 1;

                for(b = number + 1; a < this.field.length || b < this.field[0].length; ++b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b + 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b + 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    ++a;
                }
            } catch (Exception var23) {
            }

            try {
                c = 0;
                a = x - 1;

                for(b = number - 1; a >= 0 || b >= 0; --b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b - 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b - 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    --a;
                }
            } catch (Exception var22) {
            }

            try {
                c = 0;
                a = x - 1;

                for(b = number + 1; a >= 0 || b < this.field[0].length; ++b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b + 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b + 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    --a;
                }
            } catch (Exception var21) {
            }

            try {
                c = 0;
                a = x + 1;

                for(b = number - 1; a < this.field.length || b >= 0; --b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b - 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b - 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    ++a;
                }
            } catch (Exception var20) {
            }
        }

        while(!listSix4.isEmpty()) {
            x = (Integer)listSix4.remove(0);
            number = (Integer)listSix4.remove(0);

            try {
                c = 0;
                a = x + 1;

                for(b = number + 1; a < this.field.length || b < this.field[0].length; ++b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b + 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b + 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    ++a;
                }
            } catch (Exception var19) {
            }

            try {
                c = 0;
                a = x - 1;

                for(b = number - 1; a >= 0 || b >= 0; --b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b - 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b - 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    --a;
                }
            } catch (Exception var18) {
            }

            try {
                c = 0;
                a = x - 1;

                for(b = number + 1; a >= 0 || b < this.field[0].length; ++b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b + 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a - 1][b + 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    --a;
                }
            } catch (Exception var17) {
            }

            try {
                c = 0;
                a = x + 1;

                for(b = number - 1; a < this.field.length || b >= 0; --b) {
                    if (this.field[a][b] == '2' || this.field[a][b] == '5') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b - 1] != '0') {
                        ++c;
                    }

                    if ((this.field[a][b] == '1' || this.field[a][b] == '4') && this.field[a + 1][b - 1] == '0' && c == 0) {
                        list.add(x);
                        list.add(number);
                    }

                    ++a;
                }
            } catch (Exception var16) {
            }
        }

        List<Integer> resultList = new ArrayList(list);
        System.out.println("Проверяемый список: " + String.valueOf(resultList));
        number = 0;

        int i;
        for(c = 0; c < One.size(); c += 2) {
            a = (Integer)One.get(c);
            b = (Integer)One.get(c + 1);

            for(i = 0; i < list.size(); i += 2) {
                if (a == (Integer)list.get(i) && b == (Integer)list.get(i + 1)) {
                    ++number;
                }
            }
        }

        if (number == 0) {
            resultList.addAll(One);
        }

        number = 0;

        for(c = 0; c < Two.size(); c += 2) {
            a = (Integer)Two.get(c);
            b = (Integer)Two.get(c + 1);

            for(i = 0; i < list.size(); i += 2) {
                if (a == (Integer)list.get(i) && b == (Integer)list.get(i + 1)) {
                    ++number;
                }
            }
        }

        if (number == 0) {
            resultList.addAll(Two);
        }

        number = 0;

        for(c = 0; c < Three.size(); c += 2) {
            a = (Integer)Three.get(c);
            b = (Integer)Three.get(c + 1);

            for(i = 0; i < list.size(); i += 2) {
                if (a == (Integer)list.get(i) && b == (Integer)list.get(i + 1)) {
                    ++number;
                }
            }
        }

        if (number == 0) {
            resultList.addAll(Three);
        }

        number = 0;

        for(c = 0; c < Fourth.size(); c += 2) {
            a = (Integer)Fourth.get(c);
            b = (Integer)Fourth.get(c + 1);

            for(i = 0; i < list.size(); i += 2) {
                if (a == (Integer)list.get(i) && b == (Integer)list.get(i + 1)) {
                    ++number;
                }
            }
        }

        if (number == 0) {
            resultList.addAll(Fourth);
        }

        System.out.println("resultList: " + String.valueOf(resultList));
        return resultList;
    }

    public boolean userCanBigKill(List<Integer> listSix) {
        while(!listSix.isEmpty()) {
            int x = (Integer)listSix.remove(0);
            int y = (Integer)listSix.remove(0);

            int i;
            int j;
            try {
                i = x + 1;

                for(j = y + 1; i < this.field.length || j < this.field[0].length; ++j) {
                    if (this.field[i][j] == '2' || this.field[i][j] == '5') {
                        return false;
                    }

                    if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i + 1][j + 1] != '0') {
                        return false;
                    }

                    if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i + 1][j + 1] == '0') {
                        takeFukBigX = x;
                        takeFukBigY = y;
                        return true;
                    }

                    ++i;
                }
            } catch (Exception var9) {
            }

            try {
                i = x - 1;

                for(j = y - 1; i >= 0 || j >= 0; --j) {
                    if (this.field[i][j] == '2' || this.field[i][j] == '5') {
                        return false;
                    }

                    if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i - 1][j - 1] != '0') {
                        return false;
                    }

                    if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i - 1][j - 1] == '0') {
                        takeFukBigX = x;
                        takeFukBigY = y;
                        return true;
                    }

                    --i;
                }
            } catch (Exception var8) {
            }

            try {
                i = x - 1;

                for(j = y + 1; i >= 0 || j < this.field[0].length; ++j) {
                    if (this.field[i][j] == '2' || this.field[i][j] == '5') {
                        return false;
                    }

                    if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i - 1][j + 1] != '0') {
                        return false;
                    }

                    if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i - 1][j + 1] == '0') {
                        takeFukBigX = x;
                        takeFukBigY = y;
                        return true;
                    }

                    --i;
                }
            } catch (Exception var7) {
            }

            try {
                i = x + 1;

                for(j = y - 1; i < this.field.length || j >= 0; --j) {
                    if (this.field[i][j] == '2' || this.field[i][j] == '5') {
                        return false;
                    }

                    if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i + 1][j - 1] != '0') {
                        return false;
                    }

                    if ((this.field[i][j] == '1' || this.field[i][j] == '4') && this.field[i + 1][j - 1] == '0') {
                        takeFukBigX = x;
                        takeFukBigY = y;
                        return true;
                    }

                    ++i;
                }
            } catch (Exception var6) {
            }
        }

        return false;
    }

    public void userBigQueue(int x, int y, int a, int b) {
        int one = 0;
        int two = 0;
        int three = 0;
        int fourth = 0;
        int oneKill = 0;
        int twoKill = 0;
        int threeKill = 0;
        int fourthKill = 0;
        int positionAttackOneX = 0;
        int positionAttackOneY = 0;
        int positionAttackTwoX = 0;
        int positionAttackTwoY = 0;
        int positionAttackThreeX = 0;
        int positionAttackThreeY = 0;
        int positionAttackFourthX = 0;
        int positionAttackFourthY = 0;
        char firstPosition = '8';
        char secondPosition = '8';
        char thirdPosition = '8';
        char fourthPosition = '8';
        System.out.println("*******************userBigQueue***********************");
        List<Integer> listCountry = this.country(a, b);
        System.out.println("listCountry: " + String.valueOf(listCountry));
        List<Integer> coordinatesForAttack = this.checking(a, b, listCountry);
        System.out.println("Координаты для атаки дамкой, что бы эту дамку не забрали за фук: ");
        System.out.println("coordinatesForAttack: " + String.valueOf(coordinatesForAttack));
        int count = 0;
        int i;
        if (!coordinatesForAttack.isEmpty()) {
            for(i = 0; i < coordinatesForAttack.size(); i += 2) {
                if (x == (Integer)coordinatesForAttack.get(i) && y == (Integer)coordinatesForAttack.get(i + 1)) {
                    ++count;
                }
            }
        }

        System.out.println("*******************userBigQueue***********************");

        for(i = 1; i < this.field.length; ++i) {
            try {
                if (this.field[x][y] == '0' && this.field[x + i][y + i] == '6') {
                    if (one == 0) {
                        this.field[x][y] = '5';
                        this.field[x + i][y + i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x + i][y + i] != '0') {
                    ++one;
                }
            } catch (Exception var30) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x - i][y - i] == '6') {
                    if (two == 0) {
                        this.field[x][y] = '5';
                        this.field[x - i][y - i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x - i][y - i] != '0') {
                    ++two;
                }
            } catch (Exception var31) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x - i][y + i] == '6') {
                    if (three == 0) {
                        this.field[x][y] = '5';
                        this.field[x - i][y + i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x - i][y + i] != '0') {
                    ++three;
                }
            } catch (Exception var32) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x + i][y - i] == '6') {
                    if (fourth == 0) {
                        this.field[x][y] = '5';
                        this.field[x + i][y - i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x + i][y - i] != '0') {
                    ++fourth;
                }
            } catch (Exception var33) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x + i][y + i] == '6') {
                    if (oneKill == 1) {
                        this.field[x][y] = '5';
                        this.field[x + i][y + i] = '0';
                        firstPosition = this.field[positionAttackOneX][positionAttackOneY];
                        this.field[positionAttackOneX][positionAttackOneY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x + i][y + i] == '1' || this.field[x + i][y + i] == '4') {
                    ++oneKill;
                    positionAttackOneX = x + i;
                    positionAttackOneY = y + i;
                }
            } catch (Exception var34) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x - i][y - i] == '6') {
                    if (twoKill == 1) {
                        this.field[x][y] = '5';
                        this.field[x - i][y - i] = '0';
                        secondPosition = this.field[positionAttackTwoX][positionAttackTwoY];
                        this.field[positionAttackTwoX][positionAttackTwoY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x - i][y - i] == '1' || this.field[x - i][y - i] == '4') {
                    ++twoKill;
                    positionAttackTwoX = x - i;
                    positionAttackTwoY = y - i;
                }
            } catch (Exception var35) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x - i][y + i] == '6') {
                    if (threeKill == 1) {
                        this.field[x][y] = '5';
                        this.field[x - i][y + i] = '0';
                        thirdPosition = this.field[positionAttackThreeX][positionAttackThreeY];
                        this.field[positionAttackThreeX][positionAttackThreeY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x - i][y + i] == '1' || this.field[x - i][y + i] == '4') {
                    ++threeKill;
                    positionAttackThreeX = x - i;
                    positionAttackThreeY = y + i;
                }
            } catch (Exception var36) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x + i][y - i] == '6') {
                    if (fourthKill == 1) {
                        this.field[x][y] = '5';
                        this.field[x + i][y - i] = '0';
                        fourthPosition = this.field[positionAttackFourthX][positionAttackFourthY];
                        this.field[positionAttackFourthX][positionAttackFourthY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x + i][y - i] == '1' || this.field[x + i][y - i] == '4') {
                    ++fourthKill;
                    positionAttackFourthX = x + i;
                    positionAttackFourthY = y - i;
                }
            } catch (Exception var37) {
            }
        }

        System.out.println("oneKill: " + oneKill);
        System.out.println("twoKill: " + twoKill);
        System.out.println("threeKill: " + threeKill);
        System.out.println("fourthKill: " + fourthKill);
        System.out.println("######coordinatesForAttack#####: " + String.valueOf(coordinatesForAttack));
        if (!coordinatesForAttack.isEmpty() && count == 0 && userMadeBigQueue) {
            if (oneKill != 0) {
                System.out.println("firstPosition: " + firstPosition);
                this.field[positionAttackOneX][positionAttackOneY] = firstPosition;
            }

            if (twoKill != 0) {
                System.out.println("secondPosition: " + secondPosition);
                this.field[positionAttackTwoX][positionAttackTwoY] = secondPosition;
            }

            if (threeKill != 0) {
                System.out.println("thirdPosition: " + thirdPosition);
                this.field[positionAttackThreeX][positionAttackThreeY] = thirdPosition;
            }

            if (fourthKill != 0) {
                System.out.println("fourthPosition: " + fourthPosition);
                this.field[positionAttackFourthX][positionAttackFourthY] = fourthPosition;
            }

            userMadeBigKill = false;
            this.field[a][b] = '0';
            this.field[x][y] = '9';
            this.computerQueue();
        }

        if (this.checkBigAttack(x, y) && userMadeBigKill && this.field[x][y] == '5') {
            this.field[x][y] = '6';
            System.out.println("Дамкой можно срубить еще шашку противника");
            userMadeBigQueue = false;
            attackBigAgain = true;
        }

    }

    public boolean checkBigAttack(int x, int y) {
        this.printBoxInTerminal(this.field);
        int oneKill = 0;
        int twoKill = 0;
        int threeKill = 0;
        int fourthKill = 0;

        for(int i = 1; i < this.field.length; ++i) {
            try {
                if (this.field[x][y] == '5' && this.field[x + i][y + i] == '0') {
                    if (oneKill == 1) {
                        return true;
                    }
                } else if (this.field[x + i][y + i] == '1' || this.field[x + i][y + i] == '4') {
                    ++oneKill;
                }
            } catch (Exception var9) {
            }

            try {
                if (this.field[x][y] == '5' && this.field[x - i][y - i] == '0') {
                    if (twoKill == 1) {
                        return true;
                    }
                } else if (this.field[x - i][y - i] == '1' || this.field[x - i][y - i] == '4') {
                    ++twoKill;
                }
            } catch (Exception var10) {
            }

            try {
                if (this.field[x][y] == '5' && this.field[x - i][y + i] == '0') {
                    if (threeKill == 1) {
                        return true;
                    }
                } else if (this.field[x - i][y + i] == '1' || this.field[x - i][y + i] == '4') {
                    ++threeKill;
                }
            } catch (Exception var11) {
            }

            try {
                if (this.field[x][y] == '5' && this.field[x + i][y - i] == '0') {
                    if (fourthKill == 1) {
                        return true;
                    }
                } else if (this.field[x + i][y - i] == '1' || this.field[x + i][y - i] == '4') {
                    ++fourthKill;
                }
            } catch (Exception var12) {
            }
        }

        attackBigAgain = false;
        return false;
    }

    public void userBigKill(int x, int y) {
        System.out.println("userBigKill");
        int one = 0;
        int two = 0;
        int three = 0;
        int fourth = 0;
        int oneKill = 0;
        int twoKill = 0;
        int threeKill = 0;
        int fourthKill = 0;
        int positionAttackOneX = 0;
        int positionAttackOneY = 0;
        int positionAttackTwoX = 0;
        int positionAttackTwoY = 0;
        int positionAttackThreeX = 0;
        int positionAttackThreeY = 0;
        int positionAttackFourthX = 0;
        int positionAttackFourthY = 0;
        int a = 0;
        int b = 0;

        try {
            for(int i = 0; i < this.field.length; ++i) {
                for(int j = 0; j < this.field[0].length; ++j) {
                    if (this.field[i][j] == '6') {
                        a = i;
                        b = j;
                    }
                }
            }
        } catch (Exception var34) {
        }

        System.out.println("*******************userBigQueue***********************");
        List<Integer> listCountry = this.country(a, b);
        System.out.println("listCountry: " + String.valueOf(listCountry));
        List<Integer> coordinatesForAttack = this.checking(a, b, listCountry);
        System.out.println("Координаты для атаки дамкой, что бы эту дамку не забрали за фук: ");
        System.out.println("coordinatesForAttack: " + String.valueOf(coordinatesForAttack));
        int count = 0;

        int i;
        for(i = 0; i < coordinatesForAttack.size(); i += 2) {
            if (x == (Integer)coordinatesForAttack.get(i) && y == (Integer)coordinatesForAttack.get(i + 1)) {
                ++count;
            }
        }

        System.out.println("*******************userBigQueue***********************");

        for(i = 1; i < this.field.length; ++i) {
            try {
                if (this.field[x][y] == '0' && this.field[x + i][y + i] == '6') {
                    if (one == 0) {
                        this.field[x][y] = '5';
                        this.field[x + i][y + i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x + i][y + i] != '0') {
                    ++one;
                }
            } catch (Exception var26) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x - i][y - i] == '6') {
                    if (two == 0) {
                        this.field[x][y] = '5';
                        this.field[x - i][y - i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x - i][y - i] != '0') {
                    ++two;
                }
            } catch (Exception var27) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x - i][y + i] == '6') {
                    if (three == 0) {
                        this.field[x][y] = '5';
                        this.field[x - i][y + i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x - i][y + i] != '0') {
                    ++three;
                }
            } catch (Exception var28) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x + i][y - i] == '6') {
                    if (fourth == 0) {
                        this.field[x][y] = '5';
                        this.field[x + i][y - i] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = false;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x + i][y - i] != '0') {
                    ++fourth;
                }
            } catch (Exception var29) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x + i][y + i] == '6') {
                    if (oneKill == 1) {
                        this.field[x][y] = '5';
                        this.field[x + i][y + i] = '0';
                        this.field[positionAttackOneX][positionAttackOneY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x + i][y + i] == '1' || this.field[x + i][y + i] == '4') {
                    ++oneKill;
                    positionAttackOneX = x + i;
                    positionAttackOneY = y + i;
                }
            } catch (Exception var30) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x - i][y - i] == '6') {
                    if (twoKill == 1) {
                        this.field[x][y] = '5';
                        this.field[x - i][y - i] = '0';
                        this.field[positionAttackTwoX][positionAttackTwoY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x - i][y - i] == '1' || this.field[x - i][y - i] == '4') {
                    ++twoKill;
                    positionAttackTwoX = x - i;
                    positionAttackTwoY = y - i;
                }
            } catch (Exception var31) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x - i][y + i] == '6') {
                    if (threeKill == 1) {
                        this.field[x][y] = '5';
                        this.field[x - i][y + i] = '0';
                        this.field[positionAttackThreeX][positionAttackThreeY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x - i][y + i] == '1' || this.field[x - i][y + i] == '4') {
                    ++threeKill;
                    positionAttackThreeX = x - i;
                    positionAttackThreeY = y + i;
                }
            } catch (Exception var32) {
            }

            try {
                if (this.field[x][y] == '0' && this.field[x + i][y - i] == '6') {
                    if (fourthKill == 1) {
                        this.field[x][y] = '5';
                        this.field[x + i][y - i] = '0';
                        this.field[positionAttackFourthX][positionAttackFourthY] = '0';
                        newPositionTakeFukBigX = x;
                        newPositionTakeFukBigY = y;
                        userMadeBigKill = true;
                        userMadeBigQueue = true;
                    }
                } else if (this.field[x + i][y - i] == '1' || this.field[x + i][y - i] == '4') {
                    ++fourthKill;
                    positionAttackFourthX = x + i;
                    positionAttackFourthY = y - i;
                }
            } catch (Exception var33) {
            }
        }

        if (!coordinatesForAttack.isEmpty() && count == 0 && userMadeBigQueue) {
            userMadeBigKill = false;
            this.field[a][b] = '0';
            this.field[x][y] = '9';
            this.computerQueue();
        }

        if (!userMadeBigKill && userMadeBigQueue) {
            this.field[x][y] = '9';
        }

        if (this.checkBigAttack(x, y) && userMadeBigKill && this.field[x][y] == '5') {
            this.field[x][y] = '6';
            System.out.println("Дамкой можно срубить еще шашку противника");
            userMadeBigQueue = false;
            attackBigAgain = true;
        } else {
            userMadeBigQueue = true;
            attackBigAgain = false;
            this.computerQueue();
        }

    }

    public void userQueue(int x, int y) {
        System.out.println("*******************userQueue***********************");

        try {
            if (this.field[x][y] == '0' && this.field[x + 1][y - 1] == '3') {
                if (x == 0) {
                    this.field[x][y] = '5';
                } else {
                    this.field[x][y] = '2';
                }

                this.field[x + 1][y - 1] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = false;
                userMadeQueue = true;
            }
        } catch (Exception var5) {
        }

        try {
            if (this.field[x][y] == '0' && this.field[x + 1][y + 1] == '3') {
                if (x == 0) {
                    this.field[x][y] = '5';
                } else {
                    this.field[x][y] = '2';
                }

                this.field[x + 1][y + 1] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = false;
                userMadeQueue = true;
            }
        } catch (Exception var4) {
        }

        try {
            if (this.field[x][y] == '0' && this.field[x + 2][y + 2] == '3' && (this.field[x + 1][y + 1] == '1' || this.field[x + 1][y + 1] == '4')) {
                if (x == 0) {
                    this.field[x][y] = '5';
                } else {
                    this.field[x][y] = '2';
                }

                this.field[x + 1][y + 1] = '0';
                this.field[x + 2][y + 2] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception var9) {
        }

        try {
            if (this.field[x][y] == '0' && this.field[x + 2][y - 2] == '3' && (this.field[x + 1][y - 1] == '1' || this.field[x + 1][y - 1] == '4')) {
                if (x == 0) {
                    this.field[x][y] = '5';
                } else {
                    this.field[x][y] = '2';
                }

                this.field[x + 1][y - 1] = '0';
                this.field[x + 2][y - 2] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception var8) {
        }

        try {
            if (this.field[x][y] == '0' && this.field[x - 2][y + 2] == '3' && (this.field[x - 1][y + 1] == '1' || this.field[x - 1][y + 1] == '4')) {
                this.field[x][y] = '2';
                this.field[x - 1][y + 1] = '0';
                this.field[x - 2][y + 2] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception var7) {
        }

        try {
            if (this.field[x][y] == '0' && this.field[x - 2][y - 2] == '3' && (this.field[x - 1][y - 1] == '1' || this.field[x - 1][y - 1] == '4')) {
                this.field[x][y] = '2';
                this.field[x - 1][y - 1] = '0';
                this.field[x - 2][y - 2] = '0';
                newPositionTakeFukX = x;
                newPositionTakeFukY = y;
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception var6) {
        }

        if (this.checkAttack(x, y) && userMadeKill && this.field[x][y] == '2') {
            this.field[x][y] = '3';
            System.out.println("Можно срубить еще шашку противника");
            userMadeQueue = false;
            attackAgain = true;
        }

    }

    public void userKill(int x, int y) {
        System.out.println("userKill");

        try {
            if (this.field[x][y] == '0' && this.field[x + 2][y + 2] == '3' && (this.field[x + 1][y + 1] == '1' || this.field[x + 1][y + 1] == '4')) {
                System.out.println("Срубить влево-вверх");
                if (x == 0) {
                    this.field[x][y] = '5';
                } else {
                    this.field[x][y] = '2';
                }

                this.field[x + 1][y + 1] = '0';
                this.field[x + 2][y + 2] = '0';
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception var7) {
        }

        try {
            if (this.field[x][y] == '0' && this.field[x + 2][y - 2] == '3' && (this.field[x + 1][y - 1] == '1' || this.field[x + 1][y - 1] == '4')) {
                System.out.println("Срубить вправо-вверх");
                if (x == 0) {
                    this.field[x][y] = '5';
                } else {
                    this.field[x][y] = '2';
                }

                this.field[x + 1][y - 1] = '0';
                this.field[x + 2][y - 2] = '0';
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception var6) {
        }

        try {
            if (this.field[x][y] == '0' && this.field[x - 2][y + 2] == '3' && (this.field[x - 1][y + 1] == '1' || this.field[x - 1][y + 1] == '4')) {
                System.out.println("Срубить влево-вниз");
                if (x == 0) {
                    this.field[x][y] = '5';
                } else {
                    this.field[x][y] = '2';
                }

                this.field[x - 1][y + 1] = '0';
                this.field[x - 2][y + 2] = '0';
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception var5) {
        }

        try {
            if (this.field[x][y] == '0' && this.field[x - 2][y - 2] == '3' && (this.field[x - 1][y - 1] == '1' || this.field[x - 1][y - 1] == '4')) {
                System.out.println("Срубить вправо-вниз");
                if (x == 0) {
                    this.field[x][y] = '5';
                } else {
                    this.field[x][y] = '2';
                }

                this.field[x - 1][y - 1] = '0';
                this.field[x - 2][y - 2] = '0';
                userMadeKill = true;
                userMadeQueue = true;
            }
        } catch (Exception var4) {
        }

        if (this.checkAttack(x, y) && userMadeKill && this.field[x][y] == '2') {
            this.field[x][y] = '3';
            System.out.println("Можно срубить еще шашку противника");
            userMadeQueue = false;
            attackAgain = true;
        } else {
            userMadeQueue = true;
            attackAgain = false;
            this.computerQueue();
        }

    }

    public boolean checkAttack(int x, int y) {
        this.printBoxInTerminal(this.field);

        try {
            if (this.field[x][y] == '2' && this.field[x + 2][y + 2] == '0' && this.field[x + 1][y + 1] == '1') {
                attackAgain = true;
                return true;
            }

            if (this.field[x][y] == '2' && this.field[x + 2][y - 2] == '0' && this.field[x + 1][y - 1] == '1') {
                attackAgain = true;
                return true;
            }

            if (this.field[x][y] == '2' && this.field[x - 2][y + 2] == '0' && this.field[x - 1][y + 1] == '1') {
                attackAgain = true;
                return true;
            }

            if (this.field[x][y] == '2' && this.field[x - 2][y - 2] == '0' && this.field[x - 1][y - 1] == '1') {
                attackAgain = true;
                return true;
            }
        } catch (Exception var4) {
        }

        attackAgain = false;
        return false;
    }

    public List<Integer> hasThreeOrSix(char[][] field) {
        List<Integer> list = new ArrayList();

        for(int i = 0; i < field.length; ++i) {
            for(int j = 0; j < field[0].length; ++j) {
                if (field[i][j] == '3' || field[i][j] == '6') {
                    list.add(i);
                    list.add(j);
                }
            }
        }

        return list;
    }

    public boolean userCanKill() {
        for(int i = 0; i < this.field.length; ++i) {
            for(int j = 0; j < this.field[0].length; ++j) {
                try {
                    if (this.field[i][j] == '0' && (this.field[i + 2][j + 2] == '2' || this.field[i + 2][j + 2] == '3') && (this.field[i + 1][j + 1] == '1' || this.field[i + 1][j + 1] == '4')) {
                        takeFukX = i + 2;
                        takeFukY = j + 2;
                        return true;
                    }
                } catch (Exception var7) {
                }

                try {
                    if (this.field[i][j] == '0' && (this.field[i + 2][j - 2] == '2' || this.field[i + 2][j - 2] == '3') && (this.field[i + 1][j - 1] == '1' || this.field[i + 1][j - 1] == '4')) {
                        takeFukX = i + 2;
                        takeFukY = j - 2;
                        return true;
                    }
                } catch (Exception var6) {
                }

                try {
                    if (this.field[i][j] == '0' && (this.field[i - 2][j + 2] == '2' || this.field[i - 2][j + 2] == '3') && (this.field[i - 1][j + 1] == '1' || this.field[i - 1][j + 1] == '4')) {
                        takeFukX = i - 2;
                        takeFukY = j + 2;
                        return true;
                    }
                } catch (Exception var5) {
                }

                try {
                    if (this.field[i][j] == '0' && (this.field[i - 2][j - 2] == '2' || this.field[i - 2][j - 2] == '3') && (this.field[i - 1][j - 1] == '1' || this.field[i - 1][j - 1] == '4')) {
                        takeFukX = i - 2;
                        takeFukY = j - 2;
                        return true;
                    }
                } catch (Exception var4) {
                }
            }
        }

        return false;
    }

    public void computerQueue() {
        int computer = 0;

        int i;
        int j;
        for(i = 0; i < this.field.length; ++i) {
            for(j = 0; j < this.field[0].length; ++j) {
                if (this.field[i][j] == '1' || this.field[i][j] == '4') {
                    ++computer;
                }
            }
        }

        if (computer == 0) {
            System.out.println("Победил User");
            this.userWin = true;
        } else {
            System.out.println("Ход перешел к компьютеру");
            this.printBoxInTerminal(this.field);

            for(i = 0; i < this.field.length; ++i) {
                for(j = 0; j < this.field[0].length; ++j) {
                    if (this.field[i][j] == '3') {
                        this.field[i][j] = '9';
                    }

                    if (this.field[i][j] == '6') {
                        this.field[i][j] = '9';
                    }
                }
            }

            System.out.println("Первое отображение доски в computerQueue");
            this.printBoxInTerminal(this.field);
            computerMadeKill = false;
            Random random = new Random();
            j = 0;

            int computerY;
            int i;
            while(j < 1) {
                try {
                    int computerX = random.nextInt(8);
                    computerY = random.nextInt(8);
                    if (!this.computerCanKill()) {
                        if (this.field[computerX][computerY] == '0' || this.field[computerX][computerY] == '9') {
                            i = random.nextInt(1, 3);
                            if (i == 1) {
                                try {
                                    if (this.field[computerX - 1][computerY - 1] == '1') {
                                        if (computerX == 7) {
                                            this.field[computerX][computerY] = '4';
                                        } else {
                                            this.field[computerX][computerY] = '1';
                                        }

                                        this.field[computerX - 1][computerY - 1] = '0';
                                        ++j;
                                    }
                                } catch (Exception var9) {
                                }
                            } else {
                                try {
                                    if (this.field[computerX - 1][computerY + 1] == '1') {
                                        if (computerX == 7) {
                                            this.field[computerX][computerY] = '4';
                                        } else {
                                            this.field[computerX][computerY] = '1';
                                        }

                                        this.field[computerX - 1][computerY + 1] = '0';
                                        ++j;
                                    }
                                } catch (Exception var8) {
                                }
                            }
                        }
                    } else {
                        this.computerKill();

                        while(this.computerCanSecondKill()) {
                            this.computerSecondKill();
                        }

                        ++j;
                    }
                } catch (Exception var10) {
                }
            }

            this.printBoxInTerminal(this.field);
            userMadeQueue = false;
            attackAgain = false;
            List<Integer> userCanBigAttack = new ArrayList();

            for(computerY = 0; computerY < this.field.length; ++computerY) {
                for(i = 0; i < this.field[0].length; ++i) {
                    if (this.field[computerY][i] == '6' || this.field[computerY][i] == '5') {
                        userCanBigAttack.add(computerY);
                        userCanBigAttack.add(i);
                    }
                }
            }

            computerY = 0;

            for(i = 0; i < this.field.length; ++i) {
                for(int j = 0; j < this.field[0].length; ++j) {
                    if (this.field[i][j] == '2' || this.field[i][j] == '3' || this.field[i][j] == '5' || this.field[i][j] == '6') {
                        ++computerY;
                    }
                }
            }

            if (computerY == 0) {
                System.out.println("Победил Computer");
                this.computerWin = true;
            }
        }
    }

    public boolean computerCanKill() {
        int x;
        int y;
        for(x = 0; x < this.field.length; ++x) {
            for(y = 0; y < this.field[0].length; ++y) {
                try {
                    if ((this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x + 2][y + 2] == '1' && this.field[x + 1][y + 1] == '2') {
                        return true;
                    }
                } catch (Exception var10) {
                }

                try {
                    if ((this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x + 2][y - 2] == '1' && this.field[x + 1][y - 1] == '2') {
                        return true;
                    }
                } catch (Exception var11) {
                }

                try {
                    if ((this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x - 2][y + 2] == '1' && this.field[x - 1][y + 1] == '2') {
                        return true;
                    }
                } catch (Exception var12) {
                }

                try {
                    if ((this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x - 2][y - 2] == '1' && this.field[x - 1][y - 1] == '2') {
                        return true;
                    }
                } catch (Exception var13) {
                }
            }
        }

        for(x = 0; x < this.field.length; ++x) {
            for(y = 0; y < this.field.length; ++y) {
                for(int i = 0; i < this.field.length; ++i) {
                    for(int j = 0; j < this.field.length; ++j) {
                        try {
                            if (this.field[x][y] == '4' && this.field[x + j][y + j] == '0' && (this.field[x + i][y + i] == '2' || this.field[x + j][y + j] == '5')) {
                                attackAgain = true;
                                return true;
                            }
                        } catch (Exception var9) {
                        }

                        try {
                            if (this.field[x][y] == '4' && this.field[x - j][y + j] == '0' && (this.field[x - i][y + i] == '2' || this.field[x - j][y + j] == '5')) {
                                attackAgain = true;
                                return true;
                            }
                        } catch (Exception var8) {
                        }

                        try {
                            if (this.field[x][y] == '4' && this.field[x + j][y - j] == '0' && (this.field[x + i][y - i] == '2' || this.field[x + j][y - j] == '5')) {
                                attackAgain = true;
                                return true;
                            }
                        } catch (Exception var7) {
                        }

                        try {
                            if (this.field[x][y] == '4' && this.field[x - j][y - j] == '0' && (this.field[x - i][y - i] == '2' || this.field[x - j][y - j] == '5')) {
                                attackAgain = true;
                                return true;
                            }
                        } catch (Exception var6) {
                        }
                    }
                }
            }
        }

        return false;
    }

    public void computerKill() {
        System.out.println("computerKill()");

        for(int x = 0; x < this.field.length; ++x) {
            for(int y = 0; y < this.field[0].length; ++y) {
                try {
                    if (!computerMadeKill && (this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x + 2][y + 2] == '1' && (this.field[x + 1][y + 1] == '2' || this.field[x + 1][y + 1] == '5')) {
                        if (x == 7) {
                            this.field[x][y] = '4';
                        } else {
                            this.field[x][y] = '1';
                        }

                        this.field[x + 1][y + 1] = '0';
                        this.field[x + 2][y + 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                    }
                } catch (Exception var6) {
                }

                try {
                    if (!computerMadeKill && (this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x + 2][y - 2] == '1' && (this.field[x + 1][y - 1] == '2' || this.field[x + 1][y - 1] == '5')) {
                        if (x == 7) {
                            this.field[x][y] = '4';
                        } else {
                            this.field[x][y] = '1';
                        }

                        this.field[x + 1][y - 1] = '0';
                        this.field[x + 2][y - 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                    }
                } catch (Exception var7) {
                }

                try {
                    if (!computerMadeKill && (this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x - 2][y + 2] == '1' && (this.field[x - 1][y + 1] == '2' || this.field[x - 1][y + 1] == '5')) {
                        if (x == 7) {
                            this.field[x][y] = '4';
                        } else {
                            this.field[x][y] = '1';
                        }

                        this.field[x - 1][y + 1] = '0';
                        this.field[x - 2][y + 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                    }
                } catch (Exception var8) {
                }

                try {
                    if (!computerMadeKill && (this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x - 2][y - 2] == '1' && (this.field[x - 1][y - 1] == '2' || this.field[x - 1][y - 1] == '5')) {
                        if (x == 7) {
                            this.field[x][y] = '4';
                        } else {
                            this.field[x][y] = '1';
                        }

                        this.field[x - 1][y - 1] = '0';
                        this.field[x - 2][y - 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                    }
                } catch (Exception var9) {
                }

                for(int i = 0; i < this.field.length; ++i) {
                    for(int j = 0; j < this.field.length; ++j) {
                        try {
                            if (this.field[x][y] == '0' && this.field[x + j][y + j] == '4' && (this.field[x + i][y + i] == '2' || this.field[x + j][y + j] == '5')) {
                                this.field[x][y] = '4';
                                this.field[x + j][y + j] = '0';
                                this.field[x + i][y + i] = '0';
                                computerMadeKill = true;
                                positionWhoKillX = x;
                                positionWhoKillY = y;
                            }
                        } catch (Exception var10) {
                        }

                        try {
                            if (this.field[x][y] == '0' && this.field[x - j][y + j] == '4' && (this.field[x - i][y + i] == '2' || this.field[x - j][y + j] == '5')) {
                                this.field[x][y] = '4';
                                this.field[x - j][y + j] = '0';
                                this.field[x - i][y + i] = '0';
                                computerMadeKill = true;
                                positionWhoKillX = x;
                                positionWhoKillY = y;
                            }
                        } catch (Exception var11) {
                        }

                        try {
                            if (this.field[x][y] == '0' && this.field[x + j][y - j] == '4' && (this.field[x + i][y - i] == '2' || this.field[x + j][y - j] == '5')) {
                                this.field[x][y] = '4';
                                this.field[x + j][y - j] = '0';
                                this.field[x + i][y - i] = '0';
                                computerMadeKill = true;
                                positionWhoKillX = x;
                                positionWhoKillY = y;
                            }
                        } catch (Exception var12) {
                        }

                        try {
                            if (this.field[x][y] == '0' && this.field[x - j][y - j] == '4' && (this.field[x - i][y - i] == '2' || this.field[x - j][y - j] == '5')) {
                                this.field[x][y] = '4';
                                this.field[x - j][y - j] = '0';
                                this.field[x - i][y - i] = '0';
                                computerMadeKill = true;
                                positionWhoKillX = x;
                                positionWhoKillY = y;
                            }
                        } catch (Exception var13) {
                        }
                    }
                }
            }
        }

    }

    public boolean computerCanSecondKill() {
        for(int i = 0; i < this.field.length; ++i) {
            for(int j = 0; j < this.field[0].length; ++j) {
                try {
                    if (positionWhoKillX == i + 2 && positionWhoKillY == j + 2 && (this.field[i][j] == '0' || this.field[i][j] == '9') && this.field[i + 2][j + 2] == '1' && this.field[i + 1][j + 1] == '2') {
                        return true;
                    }
                } catch (Exception var4) {
                }

                try {
                    if (positionWhoKillX == i + 2 && positionWhoKillY == j - 2 && (this.field[i][j] == '0' || this.field[i][j] == '9') && this.field[i + 2][j - 2] == '1' && this.field[i + 1][j - 1] == '2') {
                        return true;
                    }
                } catch (Exception var5) {
                }

                try {
                    if (positionWhoKillX == i - 2 && positionWhoKillY == j + 2 && (this.field[i][j] == '0' || this.field[i][j] == '9') && this.field[i - 2][j + 2] == '1' && this.field[i - 1][j + 1] == '2') {
                        return true;
                    }
                } catch (Exception var6) {
                }

                try {
                    if (positionWhoKillX == i - 2 && positionWhoKillY == j - 2 && (this.field[i][j] == '0' || this.field[i][j] == '9') && this.field[i - 2][j - 2] == '1' && this.field[i - 1][j - 1] == '2') {
                        return true;
                    }
                } catch (Exception var7) {
                }
            }
        }

        return false;
    }

    public void computerSecondKill() {
        System.out.println("computerSecondKill");

        for(int x = 0; x < this.field.length; ++x) {
            for(int y = 0; y < this.field[0].length; ++y) {
                try {
                    if (positionWhoKillX == x + 2 && positionWhoKillY == y + 2 && computerMadeKill && (this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x + 2][y + 2] == '1' && this.field[x + 1][y + 1] == '2') {
                        if (x == 7) {
                            this.field[x][y] = '4';
                        } else {
                            this.field[x][y] = '1';
                        }

                        this.field[x + 1][y + 1] = '0';
                        this.field[x + 2][y + 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                        System.out.println("First");
                    }
                } catch (Exception var4) {
                }

                try {
                    if (positionWhoKillX == x + 2 && positionWhoKillY == y - 2 && computerMadeKill && (this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x + 2][y - 2] == '1' && this.field[x + 1][y - 1] == '2') {
                        if (x == 7) {
                            this.field[x][y] = '4';
                        } else {
                            this.field[x][y] = '1';
                        }

                        this.field[x + 1][y - 1] = '0';
                        this.field[x + 2][y - 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                        System.out.println("Second");
                    }
                } catch (Exception var5) {
                }

                try {
                    if (positionWhoKillX == x - 2 && positionWhoKillY == y + 2 && computerMadeKill && (this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x - 2][y + 2] == '1' && this.field[x - 1][y + 1] == '2') {
                        if (x == 7) {
                            this.field[x][y] = '4';
                        } else {
                            this.field[x][y] = '1';
                        }

                        this.field[x - 1][y + 1] = '0';
                        this.field[x - 2][y + 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                        System.out.println("Third");
                    }
                } catch (Exception var6) {
                }

                try {
                    if (positionWhoKillX == x - 2 && positionWhoKillY == y - 2 && computerMadeKill && (this.field[x][y] == '0' || this.field[x][y] == '9') && this.field[x - 2][y - 2] == '1' && this.field[x - 1][y - 1] == '2') {
                        if (x == 7) {
                            this.field[x][y] = '4';
                        } else {
                            this.field[x][y] = '1';
                        }

                        this.field[x - 1][y - 1] = '0';
                        this.field[x - 2][y - 2] = '0';
                        computerMadeKill = true;
                        positionWhoKillX = x;
                        positionWhoKillY = y;
                        System.out.println("Forth");
                    }
                } catch (Exception var7) {
                }
            }
        }

    }

    public char[][] fillBox(char[][] field) {
        for(int i = 0; i < field.length; ++i) {
            for(int j = 0; j < field[0].length; ++j) {
                if (i % 2 == 0 && j % 2 != 0 && i < 3 || i % 2 != 0 && j % 2 == 0 && i < 3) {
                    field[i][j] = '1';
                } else if ((i % 2 != 0 || j % 2 == 0 || i <= 4) && (i % 2 == 0 || j % 2 != 0 || i <= 4)) {
                    if ((i % 2 != 0 || j % 2 == 0) && (i % 2 == 0 || j % 2 != 0)) {
                        field[i][j] = '*';
                    } else {
                        field[i][j] = '0';
                    }
                } else {
                    field[i][j] = '2';
                }
            }
        }

        return field;
    }

    public void printBoxInTerminal(char[][] field) {
        for(int i = 0; i < field.length; ++i) {
            for(int j = 0; j < field[0].length; ++j) {
                System.out.print(field[i][j] + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    public char[][] getField() {
        return this.field;
    }

    public boolean userIsWinner() {
        return this.userWin;
    }

    public boolean computerIsWinner() {
        return this.computerWin;
    }
}
