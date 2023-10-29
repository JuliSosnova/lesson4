import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;
/** Данная программа считывает содержимое файла ,
 * подсчитывает количество вхождений кажого символа и выводит результат в отдельный файл в порядке убывания количества вхождений.
 *В случае отсутсвия исходного программа выводит сообщение об ошибке.
 * */
public class Main {
    public static void main(String[] args) {
        StringBuffer s=new StringBuffer();
        try (Scanner in = new Scanner(new FileReader("input.txt"))) {
            while (( in.hasNextLine())){
                s.append(in.nextLine());
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        SortedMap<Character, Integer> table = new TreeMap<>();
        for(int i=0;i<s.length();i++){
                char symbol=s.charAt(i);
                if (symbol != ' ' && symbol != '\n') {
                    table.put(symbol, table.getOrDefault(symbol, 0) + 1);
                }
       }
        Comparator<Character> valueComparator = (c1, c2) -> table.get(c2).compareTo(table.get(c1));//создает компаратор чтобы отсортировать по убывания значений
        TreeMap<Character, Integer> sortedtable = new TreeMap<>(valueComparator);//создает новый словарь отсортированый по созданному компаратору
        sortedtable.putAll(table);// кладет все значения из table в новый словарь,чтобы отсортировать
        try (OutputStream out = new FileOutputStream("output.txt")) {
            for (Map.Entry<Character, Integer> entry : sortedtable.entrySet()) {
                String res=entry.getKey() + "= " + entry.getValue()+", ";
                out.write(res.getBytes(Charset.forName("UTF-8")));
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}