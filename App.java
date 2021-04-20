import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    String str = "";
    static String time;

    public App() {
    }


    public void Start() {
        try {
            System.out.println("Hello!");
            Scanner in = new Scanner(System.in);
            LinkedList<HumanBeing> collection = new LinkedList<>();
            //читаем данные из файла
            Comand.Read(collection, "text.json");
            Comparator_3000 comparator = new Comparator_3000();
            collection.sort(comparator);
            //определяем дату
            Date creation = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy hh:mm:ss a");
            time = formatForDateNow.format(creation);
            while (!Comand.loose_equals(str, "exit")) {
                collection.sort(comparator);
                str = in.nextLine();
                Comands(collection, str, in, true);
            }
            System.out.println("Goodbye");
        } catch(FileException f1){
            System.out.println(f1.getMessage());
            System.out.println(f1.getInfo());
        }
        catch(IdException i){
            System.out.println("Три строчки кода, которые не должны существовать");
        }
    }

    public static void Comands(LinkedList<HumanBeing> collection, String str, Scanner in, Boolean check) throws FileException, IdException{
        if (Comand.loose_equals(str, "help")) {
            Comand.help();
        } else if (Comand.loose_equals(str, "info")) {
            Comand.Info(collection, time);
        } else if (Comand.loose_equals(str, "show")) {
            Comand.Show(collection);
        } else if (Comand.loose_equals(str, "add")) {
            Comand.Add(in, collection, check);
        } else if (Comand.equals_part(str, "update id")) {
            Comand.UpdateId(in, collection, str, check);
        } else if (Comand.equals_part(str, "remove_by_id")) {
            Comand.RemoveById(collection, str);
        } else if (Comand.loose_equals(str, "clear")) {
            Comand.Clear(collection);
        } else if (Comand.loose_equals(str, "save")) {
            Comand.Save(collection);
        } else if (Comand.loose_equals(str, "execute_script")) {
            Comand.execute_script(collection, "prog.txt");
        } else if (Comand.loose_equals(str, "head")) {
            Comand.Head(collection);
        } else if (Comand.loose_equals(str, "remove_head")) {
            Comand.RemoveHead(collection);
        } else if (Comand.equals_part(str, "add_if_min")) {
            Comand.Add_if_min(in, collection, check);
        } else if (Comand.equals_part(str, "remove_all_by_weapon_type")) {
            Comand.remove_all_by_weapon_type(collection, str);
        } else if (Comand.equals_part(str, "filter_by_mood")) {
            Comand.filter_by_mood(collection, str);
        } else if (Comand.equals_part(str, "filter_contains_soundtrack_name")) {
            Comand.filter_contains_soundtrack_name(collection, str);
        } else if (!Comand.equals(str, "") && (!Comand.loose_equals(str, "exit"))) {
            System.out.println("Invalid command. Enter \"help\" to see valid commands.");
        }
    }
}





