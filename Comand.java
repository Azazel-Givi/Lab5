import com.sun.deploy.net.MessageHeader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public abstract class Comand {
    static int id = 0;
    static ArrayList<Integer> ID = new ArrayList<>();

    // сравнивает строки с учетом возможных ошибок (регистр, пробелы)
    public static boolean loose_equals(String str1, String str2){
        str1 = str1.trim();
        str2 = str2.trim();
        if (equals(str1, str2)){
            return true;
        }
        else {
            boolean check = true;
            if (str2 == null || str1 == null) {
                check = false;
            }
            else if (str1.length() != str2.length()) {
                check = false;
            }
            else if (!str1.regionMatches(true,0, str2, 0, str2.length())) {
                check = false;
            }
            return check;
        }
    }
    // сравнивает, совпадает ли начальная часть введенной строки с командой
    public static boolean equals_part(String str, String norm){
        str = str.trim();
        boolean check = true;
        if (str.length() >= norm.length()){
            for (int i =0; i <= norm.length()-1; i++) {
                if (!loose_equals(str.substring(i, i+1), norm.substring(i, i+1))) {
                    check = false;
                    break;
                }
            }
        }
        else {
            check = false;
        }
        return check;
    }
    // удаляет из первой строки вторую
    public static String TrimString(String trimmed, String norm){
        int index = -1;
        if (!equals(trimmed, "")&&(trimmed.length() >= norm.length())){
            for (int i = 0; i < norm.length(); i++) {
                if (loose_equals(norm.substring(i, i+1), trimmed.substring(i, i+1))) {
                    index = i;
                }
            }
            trimmed = trimmed.substring(index + 1);
            trimmed = trimmed.trim();
        }
        return trimmed;
    }
    // выдаем элемент колелкции в строковом представлении
    public static String toString(HumanBeing human){
        return human.getName() + " \n   id: " + human.getId() +
            ", \n   x: " + human.getCoordx() +
            ", \n   y: " + human.getCoordx() +
            ", \n   realHero: " + human.getRealHero() +
            ", \n   hasToothpick: " + human.getHasToothpick() +
            ", \n   impactSpeed: " + human.getImpactSpeed() +
            ", \n   soundtrackName: " + human.getSoundtrackName() +
            ", \n   weaponType: " + human.getWeaponType() +
            ", \n   mood: " + human.getMood() +
            ", \n   carName: " + human.getCarName() +
            ", \n   carCool: " + human.getCarCool();
    }
    public static String toString(LinkedList<HumanBeing> collection){
        String str = "[\n";
        for (int i=0; i<collection.size(); i++) {
            str = str + "   {" +
                    "\n      \"name\": \"" + collection.get(i).getName() + "\"," +
                    "\n      \"id\": " + collection.get(i).getId() + "," +
                    "\n      \"x\": " + collection.get(i).getCoordx() + "," +
                    "\n      \"y\": " + collection.get(i).getCoordx() + "," +
                    "\n      \"realHero\": " + collection.get(i).getRealHero() + "," +
                    "\n      \"hasToothpick\": " + collection.get(i).getHasToothpick() + "," +
                    "\n      \"impactSpeed\": " + collection.get(i).getImpactSpeed() + "," +
                    "\n      \"soundtrackName\": \"" + collection.get(i).getSoundtrackName() + "\"," +
                    "\n      \"weaponType\": \"" + collection.get(i).getWeaponType() + "\"," +
                    "\n      \"mood\": \"" + collection.get(i).getMood() + "\"," +
                    "\n      \"carName\": \"" + collection.get(i).getCarName() + "\"," +
                    "\n      \"carCool\": " + collection.get(i).getCarCool() +
                    "\n   },\n";
        }
        return str.substring(0, str.length()-2) + "\n]";
    }
    // сравниваем две строки строго
    public static boolean equals(String obj1, String obj2) {
        boolean check = true;
        if (obj2 == null || obj1 == null) {
            check = false;
        }
        if (obj1.length() != obj2.length()) {
            check = false;
        } else {
            for (int j = 0; j < obj2.length(); j++) {
                if (obj1.charAt(j) != obj2.charAt(j)) {
                    check = false;
                }
            }
        }
        return check;
    }

    public static int findId(LinkedList<HumanBeing> collection, String str) throws NumberFormatException, NullException, IndexNotFoundException {
        int num = 0;
        int index = -1;
        if (equals("", str)){
            throw new NullException("command and ID'");
        }
        num = Integer.parseInt(str);
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId() == num){
                index = i;
                break;
            }
        }
        if (index == -1){
            throw new IndexNotFoundException();
        }
        return index;
    }
    public static int Set_id(int num, boolean check) throws IdException {
        for (int i = 0; i < ID.size(); i++) {
            if (num == ID.get(i) && (check)) {
                throw new IdException();
            }
            else if (num == ID.get(i) && (!check)){
                num = num + 1;
                num = Set_id(num, check);
                break;
            }
        }
        return num;
    }
    public static WeaponType get_weapon(String type) throws NullException, TypeException{
        WeaponType end_type = null;
        if (loose_equals(type, "SHOTGUN")) {
            end_type = WeaponType.SHOTGUN;
        } else if (equals(type, "RIFLE")) {
            end_type = WeaponType.RIFLE;
        } else if (loose_equals(type, "KNIFE")) {
            end_type = WeaponType.KNIFE;
        } else if (loose_equals(type, "MACHINE_GUN")) {
            end_type = WeaponType.MACHINE_GUN;
        } else if (equals(type, "")) {
            throw new NullException("weaponType");
        } else if (!loose_equals(type, "SHOTGUN") && !loose_equals(type, "RIFLE") && !loose_equals(type, "KNIFE") && !loose_equals(type, "MACHINE_GUN")) {
            throw new TypeException("WeaponType");
        }
        return end_type;
    }
    public static Mood get_mood(String type) throws NullException, TypeException{
        Mood end_type = null;
        if (Comand.loose_equals(type, "SADNESS")) {
            end_type = Mood.SADNESS;
        } else if (Comand.loose_equals(type, "SORROW")) {
            end_type = Mood.SORROW;
        } else if (Comand.loose_equals(type, "LONGING")) {
            end_type = Mood.LONGING;
        } else if (Comand.loose_equals(type, "GLOOM")) {
            end_type = Mood.GLOOM;
        } else if (Comand.loose_equals(type, "FRENZY")) {
            end_type = Mood.FRENZY;
        } else if(Comand.loose_equals(type, "")){
            throw new NullException("mood");
        } else if (!Comand.loose_equals(type, "SADNESS") && !Comand.loose_equals(type, "SORROW") && !Comand.loose_equals(type, "LONGING") && !Comand.loose_equals(type, "GLOOM") && !Comand.loose_equals(type, "FRENZY")) {
            throw new TypeException("Mood");
        }
        return end_type;
    }
    public static int get_min(LinkedList<HumanBeing> collect){
        int speed = 968;
        for (int i =0; i < collect.size(); i++){
            if (collect.get(i).getImpactSpeed() < speed){
                speed = collect.get(i).getImpactSpeed();
            }
        }
        return speed;
    }







    public static void help() {
        System.out.println("Вам доступны команды:");
        System.out.println("help : вывести справку по доступным командам");
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции");
        System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        System.out.println("add {element} : добавить новый элемент в коллекцию");
        System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному");
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
        System.out.println("clear : очистить коллекцию");
        System.out.println("save : сохранить коллекцию в файл");
        System.out.println("execute_script file_name : считать и исполнить скрипт из указанного файла");
        System.out.println("exit : завершить программу (без сохранения в файл)");
        System.out.println("head : вывести первый элемент коллекции");
        System.out.println("remove_head : вывести первый элемент коллекции и удалить его");
        System.out.println("add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
        System.out.println("remove_all_by_weapon_type weaponType : удалить из коллекции все элементы, значение поля weaponType которого эквивалентно заданному");
        System.out.println("filter_by_mood mood : вывести элементы, значение поля mood которых равно заданному");
        System.out.println("filter_contains_soundtrack_name soundtrackName : вывести элементы, значение поля soundtrackName которых содержит заданную подстроку");

    }

    public static void Add(Scanner in, LinkedList<HumanBeing> collect, boolean check) throws FileException, IdException {
        // создаем объекты
        HumanBeing human = new HumanBeing();
        // ввод данных в нужные поля
        setAll(human, in, check);

        // добавляем в коллекцию данные по умолчанию
        id = id + 1;
        human.setId(Set_id(id, false));
        id = human.getId();
        collect.add(human);
        System.out.println("A new object has been added to the collection");
    }

    public static void Info(LinkedList<HumanBeing> collection, String time){
        System.out.println(collection.getClass());
        System.out.println("Creation  time: " + time);
        System.out.println("Number of elements: " + collection.size());
    }

    public static void UpdateId (Scanner in, LinkedList<HumanBeing> collection, String str, Boolean check) throws FileException{
        String str_new = TrimString(str, "update id");
        try {
            int index = findId(collection, str_new);
            setAll(collection.get(index), in, check);
            System.out.println("Object updated");

        } catch (NumberFormatException e){
            System.out.println("Please, enter command with id");
        }
        catch (NullException | IndexNotFoundException n){
            System.out.println(n.getMessage());
        }
    }
    public static void RemoveById(LinkedList<HumanBeing> collection, String str){
        String str_new = TrimString(str, "remove_by_id");
        try {
            collection.remove(findId(collection, str_new));
            System.out.println("Item deleted");
        } catch (NumberFormatException e){
            System.out.println("Please, enter command with id");
        }
        catch (NullException | IndexNotFoundException n){
            System.out.println(n.getMessage());
        }
    }
    public static void remove_all_by_weapon_type(LinkedList<HumanBeing> collection, String str){
        try {
            String str_type = TrimString(str, "remove_all_by_weapon_type");
            WeaponType type;
            int index = -1;
            if (equals("", str)) {
                throw new NullException("weapon type");
            }
            type = get_weapon(str_type);
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).getWeaponType() == type) {
                    index = i;
                    collection.remove(i);
                }
            }
            if (index == -1) {
                throw new IndexNotFoundException();
            }
            else{
                System.out.println("All elements with weapon_type equal to " + str_type + " are removed.");
            }
        }
        catch(NullException | IndexNotFoundException | TypeException n){
            System.out.println(n.getMessage());
        }

    }

    public static void filter_by_mood(LinkedList<HumanBeing> collection, String str){
        try {
            String str_type = TrimString(str, "filter_by_mood");
            Mood type;
            int index = -1;
            if (equals("", str)) {
                throw new NullException("mood");
            }
            type = get_mood(str_type);
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).getMood() == type) {
                    index = i;
                    System.out.println(toString(collection.get(i)));
                }
            }
            if (index == -1) {
                throw new IndexNotFoundException();
            }
        }
        catch(NullException | IndexNotFoundException | TypeException n){
            System.out.println(n.getMessage());
        }
    }


    public static void Show (LinkedList<HumanBeing> collection){
        if (collection.size() > 0) {
            for (int i = 0; i < collection.size(); i++) {
                System.out.print("Element №" + i + ": ");
                System.out.println(toString(collection.get(i)));
            }
        } else {
            System.out.println("Сollection is empty");
        }
    }

    public static void Clear(LinkedList<HumanBeing> collection){
        collection.clear();
        System.out.println("Сollection is empty");
    }

    public static void Head(LinkedList<HumanBeing> collection){
        if (collection.size() > 0) {
            System.out.println(toString(collection.get(0)));
        } else {
            System.out.println("Collection is empty");
        }
    }

    public static void RemoveHead (LinkedList<HumanBeing> collection){
        if (collection.size() > 0) {
            Head(collection);
            collection.remove(0);
            System.out.println("First item deleted");
        }else{
            System.out.println("Collection is empty");
        }
    }

    public static void Add_if_min(Scanner in, LinkedList<HumanBeing> collection, Boolean check) throws FileException{
        HumanBeing human = new HumanBeing();
        setAll(human, in, check);

        if (human.getImpactSpeed() < get_min(collection)){
            id = id + 1;
            human.setId(id);
            collection.add(human);
            System.out.println("A new object has been added to the collection");
        }else{
            System.out.println("A new object has not been added to the collection");
        }
    }

    public static void filter_contains_soundtrack_name(LinkedList<HumanBeing> collection, String str1){
        try {
            String str2 = TrimString(str1, "filter_contains_soundtrack_name");
            if (equals("", str2)) {
                throw new NullException("soundtrack name");
            }
            boolean check = false;
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).getSoundtrackName().contains(str2)) {
                    System.out.println(toString(collection.get(i)));
                    check = true;
                }
            }
            if (!check) {
                throw new IndexNotFoundException();
            }
            else if (collection.size() == 0){
                System.out.println("Collection is empty");
            }
        } catch(NullException | IndexNotFoundException n){
            System.out.println(n.getMessage());
        }
    }

    public static void Save(LinkedList<HumanBeing> collection){
        try {
            PrintWriter writer = new PrintWriter("text.json");
            writer.write(toString(collection));
            writer.close();
        } catch(FileNotFoundException e){
            System.out.println("Can't find file to write.");
        }
    }


    public static void Read(LinkedList<HumanBeing> collection, String path){
        String str;
        int i = id + 1;
        try {
            if (path == null){
                System.out.println("ПЕРЕМЕННАЯ ПУСТАЯ!!!");
                throw  new NullException("laba");
            }
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                if (str.contains("{")) {
                    i = Set_id(id + 1, false);
                    HumanBeing human = new HumanBeing();
                    human.setId(i);
                    collection.add(human);

                } else if (!equals(str, "")) {
                    str = str.trim();
                    str = str.substring(1);
                    i = set_var(collection, str, i);
                }
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (NullException | IndexNotFoundException | IdException | TypeException | SizeException e){
            System.out.println("An error was found in the file.");
            delete_human(collection, i);
        }
    }
    public static void delete_human(LinkedList<HumanBeing> collection, int i){
        try {
            int index = findId(collection, i + "");
            collection.remove(index);
        } catch (NullException | IndexNotFoundException ignored){}
    }
    public static int set_var(LinkedList<HumanBeing> collection, String str, int i) throws NullException, IndexNotFoundException, IdException, TypeException, SizeException {
        if (equals_part(str, "name")) {
                str = TrimString(str, "name");
                str = str.trim();
                str = str.substring(2);
                str = str.trim();
                str = str.substring(1, str.length() - 2);
                if (equals(str, "")){
                    throw new NullException("name");
                }
                collection.get(findId(collection, i + "")).setName(str);

            } else if (equals_part(str, "soundtrackName")) {
                str = TrimString(str, "soundtrackName");
                str = str.trim();
                str = str.substring(2);
                str = str.trim();
                str = str.substring(1, str.length() - 2);
            if (equals(str, "")){
                throw new NullException("soundtrackName");
            }
                collection.get(findId(collection, i + "")).setSoundtrackName(str);
            } else if (equals_part(str, "carName")) {
                str = TrimString(str, "carName");
                str = str.substring(2);
                str = str.trim();
                str = str.substring(1, str.length() - 2);
            if (equals(str, "")){
                throw new NullException("carName");
            }
                collection.get(findId(collection, i + "")).setCarName(str);
            } else if (equals_part(str, "x")) {
                str = TrimString(str, "x");
                str = str.substring(2);
                str = str.trim();
                str = str.substring(0, str.length()-1);
            if (equals(str, "")){
                throw new NullException("x");
            }
                collection.get(findId(collection, i + "")).setCoordx(Integer.parseInt(str));
            } else if (equals_part(str, "y")) {
                str = TrimString(str, "y");
                str = str.substring(2);
                str = str.trim();
                str = str.substring(0, str.length()-1);
            if (equals(str, "")){
                throw new NullException("y");
            }
                collection.get(findId(collection, i + "")).setCoordy(Float.parseFloat(str));
            } else if (equals_part(str, "impactSpeed")) {
                str = TrimString(str, "impactSpeed");
                str = str.substring(2);
                str = str.trim();
                str = str.substring(0, str.length()-1);
                int impactSpeed = Integer.parseInt(str);
                if (impactSpeed > 967){
                    throw new SizeException(967);
                }
            if (equals(str, "")){
                throw new NullException("impactSpeed");
            }
                collection.get(findId(collection, i + "")).setImpactSpeed(impactSpeed);
            } else if (equals_part(str, "id")) {
                str = TrimString(str, "id");
                str = str.substring(2);
                str = str.trim();
                str = str.substring(0, str.length()-1);
                int num = Integer.parseInt(str);
                if (num < 0){
                    throw new IdException();
                }
                Set_id(num, true);
                ID.add(num);
                collection.get(findId(collection, i + "")).setId(num);
                i = num;
            } else if (equals_part(str, "realHero")) {
                str = TrimString(str, "realHero");
                str = str.substring(2);
                str = str.trim();
                str = str.substring(0, str.length() - 1);
                if (loose_equals(str, "true")) {
                    collection.get(findId(collection, i + "")).setRealHero(true);
                } else if (loose_equals(str, "false")) {
                    collection.get(findId(collection, i + "")).setRealHero(false);
                } else {
                    throw new TypeException("realHero");
                }
            } else if (equals_part(str, "HasToothpick")) {
                str = TrimString(str, "HasToothpick");
                str = str.substring(2);
                str = str.trim();
                str = str.substring(0, str.length() - 1);
                if (loose_equals(str, "true")) {
                    collection.get(findId(collection, i + "")).setHasToothpick(true);
                } else if (loose_equals(str, "false")) {
                    collection.get(findId(collection, i + "")).setHasToothpick(false);
                } else {
                    throw new TypeException("HasToothpick");
                }
            } else if (equals_part(str, "carCool")) {
                str = TrimString(str, "carCool");
                str = str.substring(2);
                if (loose_equals(str, "true")) {
                    collection.get(findId(collection, i + "")).setCarCool(true);
                } else if (loose_equals(str, "false")) {
                    collection.get(findId(collection, i + "")).setCarCool(false);
                } else {
                    throw new TypeException("carCool");
                }
            } else if (equals_part(str, "weaponType")) {
                str = TrimString(str, "weaponType");
                str = str.substring(2);
                str = str.trim();
                str = str.substring(1, str.length() - 2);
                collection.get(findId(collection, i + "")).setWeaponType(get_weapon(str));
            } else if (equals_part(str, "mood")) {
                str = TrimString(str, "mood");
                str = str.substring(2);
                str = str.trim();
                str = str.substring(1, str.length() - 2);
                collection.get(findId(collection, i + "")).setMood(get_mood(str));
            }
        return i;
    }
    public static void execute_script(LinkedList<HumanBeing> collection, String path) throws FileException, IdException{
        File file = new File(path);
        String str = "";
        try {
            Scanner scanner = new Scanner(file);
            while (!equals(str, "exit")&&(scanner.hasNextLine())){
                str = scanner.nextLine();
                App.Comands(collection, str, scanner, false);
            }
            System.out.println("Script executed.");
            scanner.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    //устанавливает все значения объекта, кроме id
    public static void setAll(HumanBeing human, Scanner in, Boolean check) throws FileException{
        human.setName(in, check);
        human.setCoordx(in, check);
        human.setCoordy(in, check);
        human.setRealHero(in, check);
        human.setHasToothpick(in, check);
        human.setImpactSpeed(in, check);
        human.setSoundtrackName(in, check);
        human.setWeaponType(in, check);
        human.setMood(in, check);
        human.setCarName(in, check);
        human.setCarCool(in, check);
    }
}
