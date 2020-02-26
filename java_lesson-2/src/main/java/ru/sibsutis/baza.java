import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

abstract class user {
    private String name;
    private String email;
    public user(String name, String email){
        this.name = name;
        this.email = email;
    }
    public user(String name){
        this.name = name;
    }
    protected String getname(){
        return this.name;
    }
    protected String getemail(){
        return this.email;
    }
}

class developer extends user{
    private String language;
    public developer(String name, String email, String language){
        super(name, email);
        this.language = language;
    }
    public String getlanguage(){
        return this.language;
    }
}

class manager extends user{
    private String rang;
    public manager(String name, String email, String rang){
        super(name, email);
        this.rang = rang;
    }
    public String getrang(){
        return this.rang;
    }
}

interface CSV{
    void toCSV(String str) throws IOException;
    String fromCSV(Integer number) throws IOException;
}

class dev implements CSV{
    private String output;
    private Integer counter;
    public void toCSV(String str) throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter("dev.csv", true);
        fw.write(str);
        fw.close();
    }
    public String fromCSV(Integer number) throws IOException {
        FileReader fr = new FileReader("dev.csv");
        Scanner out = new Scanner(fr);
        counter = 0;
        while(out.hasNextLine()){
            if(counter.equals(number)){
                output = out.nextLine();
                break;
            }
            else{
                out.nextLine();
            }
            counter++;
        }
        fr.close();
        return output;
    }
}

class man implements CSV{
    private String output;
    private Integer counter;
    public void toCSV(String str) throws IOException {
        java.io.FileWriter fw = new java.io.FileWriter("man.csv", true);
        fw.write(str);
        fw.close();
    }
    public String fromCSV(Integer number) throws IOException {
        FileReader fr = new FileReader("man.csv");
        Scanner out = new Scanner(fr);
        counter = 0;
        while(out.hasNextLine()){
            if(counter.equals(number)){
                output = out.nextLine();
                break;
            }
            else{
                out.nextLine();
            }
            counter++;
        }
        fr.close();
        return output;
    }
}
public class baza {
    private static void randomize() throws IOException {
        String[] fieldName = {"Alex", "Tom", "Pavel", "Bob", "Richard", "Emma", "Katerina", "Nikolai",
                "Tatiana", "Matthew"};
        String[] fieldEmail = {"aaa@gmail.com", "bbb@gmail.com", "ccc@gmail.com", "ddd@gmail.com", "eee@gmail.com", "fff@gmail.com"};
        String[] fieldLang = {"C/C++", "Java", "JavaScript", "R", "Ruby", "Python", "C#", "Go", "Swift",
                "Prolog", "Erlang", "Lisp", "Fortran", "Delphi", "Rust", "Lua", "Perl", "Scala", "PHP", "Haskell"};
        String[] fieldRang = {"Younger manager", "Middle manager", "Senior manager"};

        Random random = new Random();
        developer[] Developers = new developer[1000];
        manager[] Managers = new manager[1000];

        for(Integer i = 0; i < 1000; i++) {
            Developers[i] = new developer(fieldName[random.nextInt(fieldName.length)],
                    fieldEmail[random.nextInt(fieldEmail.length)], fieldLang[random.nextInt(fieldLang.length)]);
            Managers[i] = new manager(fieldName[random.nextInt(fieldName.length)],
                    fieldEmail[random.nextInt(fieldEmail.length)], fieldRang[random.nextInt(fieldRang.length)]);
        }
        dev wrDev = new dev();
        man wrMan = new man();
        File file = new File("/home/kirill/desktop/java_lessons/java_lesson-2", "dev.csv");
        if (file.exists() && file.isFile())
        {
            file.delete();
        }
        file.createNewFile();
        for(Integer i = 0; i < 1000; i++) {
            wrDev.toCSV(String.join(";", Developers[i].getname(), Developers[i].getemail(),
                    Developers[i].getlanguage(), "\n"));
        }
        file = new File("/home/kirill/desktop/java_lessons/java_lesson-2", "man.csv");
        if (file.exists() && file.isFile())
        {
            file.delete();
        }
        file.createNewFile();
        for(Integer i = 0; i < 1000; i++) {
            wrMan.toCSV(String.join(";", Managers[i].getname(), Managers[i].getemail(),
                    Managers[i].getrang(), "\n"));
        }
    }
    public static void main(String[] args) throws IOException {
        randomize(); // to create new CSVs files
        Integer number = 666;
        dev devR = new dev();
        man manR = new man();
        String string1 = devR.fromCSV(number);
        String string2 = manR.fromCSV(number);
        String str1[] = string1.split(";");
        String str2[] = string2.split(";");
        System.out.println("Number: " + number + " // Record: " + Arrays.toString(str1));
        System.out.println("Number: " + number + " // Record: " + Arrays.toString(str2));
    }

}
