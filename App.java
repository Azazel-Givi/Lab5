import java.io.File;
import java.io.FileNotFoundException;
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
            String path = System.getenv("LABA5json");
            if (path == null) {
                throw new FileNotFoundException();
            }
            File file = new File(path);
            Comand.read(collection, file);
            Comparator_3000 comparator = new Comparator_3000();
            collection.sort(comparator);
            //определяем дату
            Date creation = new Date();
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy hh:mm:ss a");
            time = formatForDateNow.format(creation);
            while (!Comand.looseEquals(str, "exit")) {
                collection.sort(comparator);
                str = in.nextLine();
                Comands(collection, str, in, true, path);
            }
            System.out.println("Goodbye");
        } catch(CollectionException f1){
            System.out.println(f1.getMessage());
            System.out.println(f1.getInfo());
        }
        catch(IdException i){
            System.out.println("Три строчки кода, которые не должны существовать");
        } catch (FileNotFoundException e) {
            System.out.println("File not found. Maybe it's in a closed directory or it just doesn't exist.");
        }
    }

    public static void Comands(LinkedList<HumanBeing> collection, String str, Scanner in, Boolean check, String path) throws CollectionException, IdException{
        try {
            if (Comand.looseEquals(str, "help")) {
                Comand.help();
            } else if (Comand.equalsPart(str, "info")) {
                Comand.info(collection, time);
            } else if (Comand.equalsPart(str, "show")) {
                Comand.show(collection);
            } else if (Comand.looseEquals(str, "add")) {
                Comand.add(in, collection, check);
            } else if (Comand.equalsPart(str, "update id")) {
                Comand.updateId(in, collection, str, check);
            } else if (Comand.equalsPart(str, "remove_by_id")) {
                Comand.removeById(collection, str);
            } else if (Comand.equalsPart(str, "clear")) {
                Comand.clear(collection);
            } else if (Comand.equalsPart(str, "save")) {
                Comand.save(collection, path);
            } else if (Comand.equalsPart(str, "execute_script")) {
                Comand.executeScript(collection, str);
            } else if (Comand.equalsPart(str, "head")) {
                Comand.head(collection);
            } else if (Comand.equalsPart(str, "remove_head")) {
                Comand.removeHead(collection);
            } else if (Comand.equalsPart(str, "add_if_min")) {
                Comand.addIfMin(in, collection, check);
            } else if (Comand.equalsPart(str, "remove_all_by_weapon_type")) {
                Comand.removeAllByWeaponType(collection, str);
            } else if (Comand.equalsPart(str, "filter_by_mood")) {
                Comand.filterByMood(collection, str);
            } else if (Comand.equalsPart(str, "filter_contains_soundtrack_name")) {
                Comand.filterContainsSoundtrackName(collection, str);
            } else if (!Comand.equals(str, "") && (!Comand.looseEquals(str, "exit") || (Comand.equalsPart(str, "FD")))) {
                System.out.println("Invalid command. Enter \"help\" to see valid commands.");
            }
        } catch (NoSuchElementException ignored){}
        catch (FileNotFoundException f){
            int i = 9;
        }
    }
}





