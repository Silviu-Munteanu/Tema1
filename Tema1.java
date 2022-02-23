import java.util.ArrayList;

public class Tema1 {
    public static void main (String args[]) {
        if (args.length < 3)
            System.out.println("Introduceti n,p nr. naturale si cel putin o litera din alfabet!");
        for (int i = 0; i < args[0].length(); ++i) {
            if (!Character.isDigit(args[0].charAt(i))) {
                System.out.println("n trebuie sa fie un nr. intreg!");
                return;
            }
        }
        for (int i = 0; i < args[1].length(); ++i) {
            if (!Character.isDigit(args[1].charAt(i))) {
                System.out.println("p trebuie sa fie un nr. intreg!");
                return;
            }
        }
        int n = Integer.parseInt(args[0]);
        int p = Integer.parseInt(args[1]);
        boolean[] alphabet_occ = new boolean[60];
        char[] alphabet = new char[60];
        char current_char;
        int k = 0;
        for (int i = 2; i < args.length; ++i) {
            current_char = args[i].charAt(0);
            if (args[i].length() != 1 || !Character.isLetter(current_char)) {
                System.out.println("Fiecare element din alfabet trebuie sa fie o litera!");
                return;
            }
            if (current_char < 'a')
                if (!alphabet_occ[current_char - 'A']) {
                    alphabet_occ[current_char - 'A'] = true;
                } else {
                    System.out.println("Literele din alfabet trebuie sa fie distincte");
                    return;
                }
            else {
                if (!alphabet_occ[current_char - 'a' + 26]) {
                    alphabet_occ[current_char - 'a' + 26] = true;
                } else {
                    System.out.println("Literele din alfabet trebuie sa fie distincte");
                    return;
                }
            }
            alphabet[k++] = current_char;
        }
        ArrayList<String> words = new ArrayList<>();
        String temp;
        for (int i = 0; i < n; ++i) {
            temp = "";
            for (int j = 0; j < p; ++j)
                temp += alphabet[(int) (Math.random() * k)];
            words.add(temp);
        }
        int sz=words.size();
        long code_occ[] = new long[sz];

        for (int i=0;i<sz;++i) {
            for(int j=0;j<words.get(i).length();++j) {
                current_char=words.get(i).charAt(j);
                if (current_char < 'a') {
                    code_occ[i] = code_occ[i] | (1<<(current_char - 'A'));
                }
                else {
                    code_occ[i] = code_occ[i] | ((long)1<<(current_char - 'a' + 26));
                }
            }
        }
        boolean neighbors[][]= new boolean[sz][sz];
        for(int i=0;i<sz;++i) {
            for (int j = 0; j < sz; ++j) {
                if ((code_occ[i] & code_occ[j]) != 0) {
                    neighbors[i][j] = true;
                }
            }
        }
            System.out.println(code_occ[0]);
            System.out.println(words);

    }
}
