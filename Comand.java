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
    public static boolean looseEquals(String str1, String str2) {
        str1 = str1.trim();
        str2 = str2.trim();
        if (equals(str1, str2)) {
            return true;
        } else {
            boolean check = true;
            if (str2 == null || str1 == null) {
                check = false;
            } else if (str1.length() != str2.length()) {
                check = false;
            } else if (!str1.regionMatches(true, 0, str2, 0, str2.length())) {
                check = false;
            }
            return check;
        }
    }

    // сравнивает, совпадает ли начальная часть введенной строки с командой
    public static boolean equalsPart(String str, String norm) {
        str = str.trim();
        boolean check = true;
        if (str.length() >= norm.length()) {
            for (int i = 0; i <= norm.length() - 1; i++) {
                if (!looseEquals(str.substring(i, i + 1), norm.substring(i, i + 1))) {
                    check = false;
                    break;
                }
            }
        } else {
            check = false;
        }
        return check;
    }

    // удаляет из первой строки вторую
    public static String trimString(String trimmed, String norm) {
        int index = -1;
        if (!equals(trimmed, "") && (trimmed.length() >= norm.length())) {
            for (int i = 0; i < norm.length(); i++) {
                if (looseEquals(norm.substring(i, i + 1), trimmed.substring(i, i + 1))) {
                    index = i;
                }
            }
            trimmed = trimmed.substring(index + 1);
            trimmed = trimmed.trim();
        }
        return trimmed;
    }

    // выдаем элемент колелкции в строковом представлении
    public static String toString(HumanBeing human) {
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

    public static String toString(LinkedList<HumanBeing> collection) {
        String str = "[\n";
        for (int i = 0; i < collection.size(); i++) {
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
        return str.substring(0, str.length() - 2) + "\n]";
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
        if (equals("", str)) {
            throw new NullException("command and ID'");
        }
        num = Integer.parseInt(str);
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId() == num) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IndexNotFoundException();
        }
        return index;
    }

    public static int setId(int num, boolean check) throws IdException {
        for (int i = 0; i < ID.size(); i++) {
            if (num == ID.get(i) && (check)) {
                throw new IdException();
            } else if (num == ID.get(i) && (!check)) {
                num = num + 1;
                num = setId(num, check);
                break;
            }
        }
        return num;
    }

    public static WeaponType getWeapon(String type) throws NullException, TypeException {
        WeaponType endType = null;
        if (looseEquals(type, "SHOTGUN")) {
            endType = WeaponType.SHOTGUN;
        } else if (equals(type, "RIFLE")) {
            endType = WeaponType.RIFLE;
        } else if (looseEquals(type, "KNIFE")) {
            endType = WeaponType.KNIFE;
        } else if (looseEquals(type, "MACHINE_GUN")) {
            endType = WeaponType.MACHINE_GUN;
        } else if (equals(type, "")) {
            throw new NullException("weaponType");
        } else if (!looseEquals(type, "SHOTGUN") && !looseEquals(type, "RIFLE") && !looseEquals(type, "KNIFE") && !looseEquals(type, "MACHINE_GUN")) {
            throw new TypeException("WeaponType");
        }
        return endType;
    }

    public static Mood getMood(String type) throws NullException, TypeException {
        Mood endType = null;
        if (Comand.looseEquals(type, "SADNESS")) {
            endType = Mood.SADNESS;
        } else if (Comand.looseEquals(type, "SORROW")) {
            endType = Mood.SORROW;
        } else if (Comand.looseEquals(type, "LONGING")) {
            endType = Mood.LONGING;
        } else if (Comand.looseEquals(type, "GLOOM")) {
            endType = Mood.GLOOM;
        } else if (Comand.looseEquals(type, "FRENZY")) {
            endType = Mood.FRENZY;
        } else if (Comand.looseEquals(type, "")) {
            throw new NullException("mood");
        } else if (!Comand.looseEquals(type, "SADNESS") && !Comand.looseEquals(type, "SORROW") && !Comand.looseEquals(type, "LONGING") && !Comand.looseEquals(type, "GLOOM") && !Comand.looseEquals(type, "FRENZY")) {
            throw new TypeException("Mood");
        }
        return endType;
    }

    public static int getMin(LinkedList<HumanBeing> collect) {
        int speed = 968;
        for (int i = 0; i < collect.size(); i++) {
            if (collect.get(i).getImpactSpeed() < speed) {
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
        System.out.println("removeAllByWeaponType weaponType : удалить из коллекции все элементы, значение поля weaponType которого эквивалентно заданному");
        System.out.println("filterByMood mood : вывести элементы, значение поля mood которых равно заданному");
        System.out.println("filter_contains_soundtrack_name soundtrackName : вывести элементы, значение поля soundtrackName которых содержит заданную подстроку");

    }

    public static void add(Scanner in, LinkedList<HumanBeing> collect, boolean check) throws CollectionException, IdException {
        // создаем объекты
        HumanBeing human = new HumanBeing();
        // ввод данных в нужные поля
        setAll(human, in, check);

        // добавляем в коллекцию данные по умолчанию
        id = id + 1;
        human.setId(setId(id, false));
        id = human.getId();
        collect.add(human);
        System.out.println("A new object has been added to the collection");
    }

    public static void info(LinkedList<HumanBeing> collection, String time) {
        System.out.println(collection.getClass());
        System.out.println("Creation  time: " + time);
        System.out.println("Number of elements: " + collection.size());
    }

    public static void updateId(Scanner in, LinkedList<HumanBeing> collection, String str, Boolean check) throws CollectionException {
        String strNew = trimString(str, "update id");
        try {
            int index = findId(collection, strNew);
            setAll(collection.get(index), in, check);
            System.out.println("Object updated");

        } catch (NumberFormatException e) {
            System.out.println("Please, enter command with id");
        } catch (NullException | IndexNotFoundException n) {
            System.out.println(n.getMessage());
        }
    }

    public static void removeById(LinkedList<HumanBeing> collection, String str) {
        String strNew = trimString(str, "remove_by_id");
        try {
            collection.remove(findId(collection, strNew));
            System.out.println("Item deleted");
        } catch (NumberFormatException e) {
            System.out.println("Please, enter command with id");
        } catch (NullException | IndexNotFoundException n) {
            System.out.println(n.getMessage());
        }
    }

    public static void removeAllByWeaponType(LinkedList<HumanBeing> collection, String str) {
        try {
            String strType = trimString(str, "removeAllByWeaponType");
            WeaponType type;
            int index = -1;
            if (equals("", str)) {
                throw new NullException("weapon type");
            }
            type = getWeapon(strType);
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).getWeaponType() == type) {
                    index = i;
                    collection.remove(i);
                }
            }
            if (index == -1) {
                throw new IndexNotFoundException();
            } else {
                System.out.println("All elements with weapon_type equal to " + strType + " are removed.");
            }
        } catch (NullException | IndexNotFoundException | TypeException n) {
            System.out.println(n.getMessage());
        }

    }

    public static void filterByMood(LinkedList<HumanBeing> collection, String str) {
        try {
            String strType = trimString(str, "filterByMood");
            Mood type;
            int index = -1;
            if (equals("", str)) {
                throw new NullException("mood");
            }
            type = getMood(strType);
            for (int i = 0; i < collection.size(); i++) {
                if (collection.get(i).getMood() == type) {
                    index = i;
                    System.out.println(toString(collection.get(i)));
                }
            }
            if (index == -1) {
                throw new IndexNotFoundException();
            }
        } catch (NullException | IndexNotFoundException | TypeException n) {
            System.out.println(n.getMessage());
        }
    }


    public static void show(LinkedList<HumanBeing> collection) {
        if (collection.size() > 0) {
            for (int i = 0; i < collection.size(); i++) {
                System.out.print("Element №" + i + ": ");
                System.out.println(toString(collection.get(i)));
            }
        } else {
            System.out.println("Сollection is empty");
        }
    }

    public static void clear(LinkedList<HumanBeing> collection) {
        collection.clear();
        System.out.println("Сollection is empty");
    }

    public static void head(LinkedList<HumanBeing> collection) {
        if (collection.size() > 0) {
            System.out.println(toString(collection.get(0)));
        } else {
            System.out.println("Collection is empty");
        }
    }

    public static void removeHead(LinkedList<HumanBeing> collection) {
        if (collection.size() > 0) {
            head(collection);
            collection.remove(0);
            System.out.println("First item deleted");
        } else {
            System.out.println("Collection is empty");
        }
    }

    public static void addIfMin(Scanner in, LinkedList<HumanBeing> collection, Boolean check) throws CollectionException {
        HumanBeing human = new HumanBeing();
        setAll(human, in, check);

        if (human.getImpactSpeed() < getMin(collection)) {
            id = id + 1;
            human.setId(id);
            collection.add(human);
            System.out.println("A new object has been added to the collection");
        } else {
            System.out.println("A new object has not been added to the collection");
        }
    }

    public static void filterContainsSoundtrackName(LinkedList<HumanBeing> collection, String str1) {
        try {
            String str2 = trimString(str1, "filter_contains_soundtrack_name");
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
            } else if (collection.size() == 0) {
                System.out.println("Collection is empty");
            }
        } catch (NullException | IndexNotFoundException n) {
            System.out.println(n.getMessage());
        }
    }
    public static void executeScript(LinkedList<HumanBeing> collection, String path) throws CollectionException, IdException{
        try {
            path = trimString(path, "execute_Script");
            if (path == null){
                System.out.println("File not found");
                throw new FileNotFoundException();
            }
            File file = new File(path);
            if (checkFile(file, false)){
                throw new AccessRightsException();
            }
            String str = "";
            Scanner scanner = new Scanner(file);
            while (!equals(str, "exit")&&(scanner.hasNextLine())){
                str = scanner.nextLine();
                App.Comands(collection, str, scanner, false, path);
            }
            System.out.println("The script was executed.");
            scanner.close();
        }catch (FileNotFoundException | AccessRightsException a){
            System.out.println("Please write a new file path or \"skip\" to skip this step ");
            Scanner in = new Scanner(System.in);
            String newPath = readNewPath(in);
            if (equalsPart(newPath, "skip")){
                System.out.println("Please, enter a new command");
            }else {
                executeScript(collection, newPath);
            }
        }
    }
    public static void save(LinkedList<HumanBeing> collection, String path) {
        try {
            if (path == null){
                System.out.println("File not found");
                throw new FileNotFoundException();
            }
            File file = new File(path);
            if (checkFile(file, false)){
                throw new AccessRightsException();
            }
            PrintWriter writer = new PrintWriter(file);
            writer.write(toString(collection));
            writer.close();
        } catch (FileNotFoundException | AccessRightsException a){
        System.out.println("Please write a new file path or \"skip\" to skip this step ");
        Scanner in = new Scanner(System.in);
        String newPath = readNewPath(in);
        if (equalsPart(newPath, "skip")){
            System.out.println("Please, enter a new command");
        }else {
            save(collection, newPath);
        }
    }

    }
    public static boolean checkFile(File file, Boolean check) throws FileNotFoundException{
        boolean error = false;
        if (!file.exists()) {
            System.out.println("File not found");
            throw new FileNotFoundException();
        }
        else if (!file.canRead() && check) {
            System.out.println("read");
            error = true;
            System.out.println("Access rights exception. Unable to read");
        }
        else if (!file.canWrite() && !check){
            error = true;
            System.out.println("Access rights exception. Unable to write");
        }
        return error;
    }

    public static void read(LinkedList<HumanBeing> collection, String path) {
        String str;
        int i = id + 1;
        try {
            if (path == null){
                throw new FileNotFoundException();
            }
            File file = new File(path);
            if (checkFile(file, true)){
                throw new AccessRightsException();
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                str = scanner.nextLine();
                if (str.contains("{")) {
                    i = setId(id + 1, false);
                    HumanBeing human = new HumanBeing();
                    human.setId(i);
                    collection.add(human);

                } else if (!equals(str, "")) {
                    str = str.trim();
                    str = str.substring(1);
                    i = setVar(collection, str, i);
                }
            }
            System.out.println("The file was read!");
        } catch (NullException | IndexNotFoundException | IdException | TypeException | SizeException | NumberFormatException e) {
            System.out.println("An error was found in the file.");
            deleteHuman(collection, i);
        } catch (FileNotFoundException | AccessRightsException a){
            System.out.println("Please write a new file path or \"skip\" to skip this step ");
            Scanner in = new Scanner(System.in);
            String newPath = readNewPath(in);
            if (equalsPart(newPath, "skip")){
                System.out.println("Please, enter a new command");
            }else {
                read(collection, newPath);
            }
        }
    }
    public static String readNewPath(Scanner in){
        String newPath = "";
        newPath = in.nextLine();
        if (looseEquals(newPath, "")){
            newPath = readNewPath(in);
        }
        return newPath;
    }
    public static void deleteHuman(LinkedList<HumanBeing> collection, int i) {
        try {
            int index = findId(collection, i + "");
            collection.remove(index);
        } catch (NullException | IndexNotFoundException ignored) {
        }
    }

    //метод для получения значений полей из json файла
    public static String trimJson(String json, String trim, Boolean quotes) throws NullException {
        json = trimString(json, trim);
        if (equals(json, "")) {
            throw new NullException("");
        }
        json = json.trim();
        if (looseEquals(json.substring(json.length() - 1), ",")) {
            if (quotes) {
                json = json.substring(1, json.length() - 2);
            } else {
                json = json.substring(0, json.length() - 1);
            }
        } else if (quotes) {
            json = json.substring(1, json.length() - 1);
        }
        json = json.trim();
        json = json.substring(1);
        json = json.trim();
        json = json.substring(1);
        json = json.trim();
        return json;
    }


    //JSON TAMING
    //определяем поля, содержащие строки
    public static void setNamesById(LinkedList<HumanBeing> collection, String str, int i, String type) throws NullException, IndexNotFoundException {
       if (looseEquals(type, "name")) {
           collection.get(findId(collection, i + "")).setName(str);
       }
       else if (looseEquals(type, "soundtrackName")) {
           collection.get(findId(collection, i + "")).setSoundtrackName(str);
       }
       else if (looseEquals(type, "carName")) {
           collection.get(findId(collection, i + "")).setCarName(str);
       }
       else if (looseEquals(type, "name")) {
           collection.get(findId(collection, i + "")).setName(str);
       }
    }
    //определяем поля, имеющие числовое значение
    public static void setNumberById(LinkedList<HumanBeing> collection, String str, int i, String type) throws NullException, IndexNotFoundException, SizeException {
        if (looseEquals(type, "x")){
            collection.get(findId(collection, i + "")).setCoordx(Integer.parseInt(str));
        }
        else if (looseEquals(type, "y")) {
            collection.get(findId(collection, i + "")).setCoordy(Float.parseFloat(str));
        }
        else if (looseEquals(type, "impactSpeed")) {
            int impactSpeed = Integer.parseInt(str);
            if (impactSpeed > 967) {
                throw new SizeException(967);
            }
            if (equals(str, "")) {
                throw new NullException("impactSpeed");
            }
            collection.get(findId(collection, i + "")).setImpactSpeed(impactSpeed);
        }
    }
    //определяем id элемента
    public static int setIdById(LinkedList<HumanBeing> collection, String str, int i) throws NullException, IndexNotFoundException, IdException, NumberFormatException {
        int num = Integer.parseInt(str);
        if (num < 0) {
            throw new IdException();
        }
        setId(num, true);
        ID.add(num);
        collection.get(findId(collection, i + "")).setId(num);
        return num;
    }
    //определяем поля, имеющие логическое значение
    public static void setBooleanById(LinkedList<HumanBeing> collection, String str, int i, String type) throws TypeException, NullException, IndexNotFoundException {
        if (looseEquals(str, "true")) {
            if (looseEquals(type, "RealHero")) {
                collection.get(findId(collection, i + "")).setRealHero(true);
            }
            else if (looseEquals(type, "HasToothpick")) {
                collection.get(findId(collection, i + "")).setHasToothpick(true);
            }
            else if (looseEquals(type, "carCool")) {
                collection.get(findId(collection, i + "")).setCarCool(true);
            }
        }
        else if (looseEquals(str, "false")) {
            if (looseEquals(type, "RealHero")) {
                collection.get(findId(collection, i + "")).setRealHero(false);
            }
            else if (looseEquals(type, "HasToothpick")) {
                collection.get(findId(collection, i + "")).setHasToothpick(false);
            }
            else if (looseEquals(type, "carCool")) {
                collection.get(findId(collection, i + "")).setCarCool(false);
            }
        }
        else {
            throw new TypeException("");
        }
    }
    public static void setWeaponTypeById(LinkedList<HumanBeing> collection, String str, int i) throws NullException, TypeException, IndexNotFoundException {
        collection.get(findId(collection, i + "")).setWeaponType(getWeapon(str));
    }
    public static void setMoodTypeById(LinkedList<HumanBeing> collection, String str, int i) throws NullException, TypeException, IndexNotFoundException {
        collection.get(findId(collection, i + "")).setMood(getMood(str));
    }


    public static int setVar(LinkedList<HumanBeing> collection, String str, int i) throws NullException, IndexNotFoundException, IdException, TypeException, SizeException, NumberFormatException {
        if (equalsPart(str, "name")) {
            str = trimJson(str, "name", true);
            setNamesById(collection, str, i, "name");

        } else if (equalsPart(str, "soundtrackName")) {
            str = trimJson(str, "soundtrackName", true);
            setNamesById(collection, str, i, "soundtrackName");

        } else if (equalsPart(str, "carName")) {
            str = trimJson(str, "carName", true);
            setNamesById(collection, str, i, "carName");

        } else if (equalsPart(str, "x")) {
            str = trimJson(str, "x", false);
            setNumberById(collection, str, i, "x");

        } else if (equalsPart(str, "y")) {
            str = trimJson(str, "y", false);
            setNumberById(collection, str, i, "y");

        } else if (equalsPart(str, "impactSpeed")) {
            str = trimJson(str, "impactSpeed", false);
            setNumberById(collection, str, i, "impactSpeed");

        } else if (equalsPart(str, "id")) {
            str = trimJson(str, "id", false);
            i = setIdById(collection, str, i);

        } else if (equalsPart(str, "realHero")) {
            str = trimJson(str, "realHero", false);
            setBooleanById(collection, str, i, "realHero");

        } else if (equalsPart(str, "HasToothpick")) {
            str = trimJson(str, "HasToothpick", false);
            setBooleanById(collection, str, i, "HasToothpick");

        } else if (equalsPart(str, "carCool")) {
            str = trimJson(str, "carCool", false);
            setBooleanById(collection, str, i, "carCool");

        } else if (equalsPart(str, "weaponType")) {
            str = trimJson(str, "weaponType", true);
            setWeaponTypeById(collection, str, i);

        } else if (equalsPart(str, "mood")) {
            str = trimJson(str, "mood", true);
            setMoodTypeById(collection, str, i);
        }
        return i;
    }
    //устанавливает все значения объекта, кроме id
    public static void setAll(HumanBeing human, Scanner in, Boolean check) throws CollectionException{
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
