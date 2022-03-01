import T1.Adjacency;



public class Tema1 {
    static int findCycle(int index, boolean[][] neighbors, int n, int[] found,String[] words,String[] used_words) {
        int ok=0;
        int done=0;
        used_words[found[index]-1]=words[index];
        for(int j=0;j<found[index]-1;++j) {
            if (words[index].equals(used_words[j])) {

                return 0;

            }
        }

        for (int i = 0; i < n; ++i) {
            if (neighbors[index][i] && index != i)
            {
                if (found[i] == 0) {
                    found[i] = found[index] + 1;
                    done = findCycle(i, neighbors, n, found,words,used_words);
                    if(done!=0)
                        break;
                } else {
                    if (found[i] + 2 <= found[index]) {
                        System.out.println(words[index]);
                        return i+1;
                    }
                }
            }
        }
        if (done > 0) {
            if (done-1 != index) {
                 System.out.println(words[index]);
                return done;
            } else {
                System.out.println(words[index]);
                return -1;
            }
        }
        if(done<0)
        {
            return -1;
        }

        return 0;
    }


    public static void main (String[] args) {

        long startTime = System.nanoTime();
        if (args.length < 3) {
            System.out.println("Introduceti n si p (nr. naturale) si cel putin o litera din alfabet!");
            return;
        }
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
         String words[] = new String[n];
        String temp; //StringBuilder
        for (int i = 0; i < n; ++i) {
            temp = "";
            for (int j = 0; j < p; ++j)
                temp += alphabet[(int) (Math.random() * k)];
            words[i]=temp;
        }
        long[] code_occ = new long[n];

        for (int i=0;i<n;++i) {
            for(int j=0;j<words[i].length();++j) {
                current_char=words[i].charAt(j);
                if (current_char < 'a') {
                    code_occ[i] = code_occ[i] | (1<<(current_char - 'A'));
                }
                else {
                    code_occ[i] = code_occ[i] | ((long)1<<(current_char - 'a' + 26));
                }
            }
        }
        boolean[][] neighbors= new boolean[n][n];
        Adjacency[] lst= new Adjacency[n];
        for(int i=0;i<n;++i) {
            k=0;
         lst[i]=new Adjacency();
            for (int j = 0; j < n; ++j) {
                if ((code_occ[i] & code_occ[j]) != 0 && i!=j) {
                    lst[i].adj.add(words[j]);
                    neighbors[i][j] = true;
                    k++;
                }
            }
        }

        if(n<50) {
            for (Adjacency i:lst) {
                System.out.println(i.adj);
            }
        }
     /**   for (int i=0;i<n;++i)
        {
            System.out.println(words[i]);
        }

        System.out.println(";");
      **/
     int fnd=0;
        int[] found=new int[n+1];
        String[] used_words=new String[n];
        for(int i=0;i<n;++i) {
            found[i]=1;
            if (findCycle(i, neighbors, n, found, words, used_words) != 0) {
                fnd=1;
                break;
            }
            for(int j=0;j<n;++j)
                found[i]=0;
        }
        if(fnd==0)
            System.out.println("Nu am gasit o submultime cu proprietatea data");

        if(n>=50) {
            long endTime = System.nanoTime();
            System.out.println(endTime - startTime + " nanoseconds");
        }
    }
}
