import java.io.*;
import java.util.*;


class Phonebook {
    private static HashMap<String, LinkedList<Long>> pb = new HashMap<>();


    private static void addPB(String name, long phone) {
        LinkedList<Long> ll = new LinkedList<>();
        if (pb.containsKey(name)) {
            pb.get(name).add(phone);
        } else {
            ll.add(phone);
            pb.put(name, ll);
        }
    }

    //delPB - удаляет запись по номеру телефона
    private static void delPB(String name) {
        pb.remove(name);
    }


    //PrintPhonebook - выводит на екран все записи БД
    public static void PrintPhonebook() {
        System.out.println("Телефонный справочник: ");
        for (Map.Entry<String, LinkedList<Long>> k : pb.entrySet()) {
            System.out.println(k.getKey() + " : " + k.getValue());
        }
    }

    public static void sortedMap() {
        TreeMap<Integer, Map.Entry<String, LinkedList<Long>>> sort = new TreeMap<>(Collections.reverseOrder());
        for (Map.Entry<String, LinkedList<Long>> item : pb.entrySet()) {
            sort.put(item.getValue().size(), item);
        }
        for (Map.Entry<Integer, Map.Entry<String, LinkedList<Long>>> k : sort.entrySet()) {
            System.out.println(k.getKey() + " : " + k.getValue());
        }

    }


    public static String[] FindNumberPhone(String surname) {
        List<String> result = new ArrayList<String>();
        for (Map.Entry entry : pb.entrySet()) {
            if (surname.equalsIgnoreCase((String) entry.getValue())) {
                result.add((String) entry.getKey());
            }
        }
        if (result.size() == 0) result.add("абонент с такой фамилией не найден");
        return result.toArray(new String[0]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //переменная описывает вызываемое действие
        String act;

        //вывод записей на екран
        PrintPhonebook();

        //вывод на екран описания возможных действий с указанием команд
        System.out.println(
                "1)Добавить запись\n"
                        + "2)Удалить запись\n"
                        + "3)Вывести список\n"
                        + "4)выход\n");
        System.out.println("Выберите номер операции: ");
        act = bf.readLine();
        while (!act.equals("4")) {
            //добавление записи
            if (act.equals("1")) {
                System.out.println("Введите фамилию:");
                String name = bf.readLine();
                System.out.println("Введите телефон:");
                long phone = Long.parseLong(bf.readLine());
                addPB(name, phone);
                PrintPhonebook();
                System.out.println();
            } else {
                //удаление записи
                if (act.equals("2")) {
                    System.out.println("Введите имя:");
                    String phone = bf.readLine();
                    delPB(phone);
                    PrintPhonebook();
                    System.out.println();
                } else {
                    //поиск номеров по фамилии
                    if (act.equals("3")) {
                        sortedMap();
                    }
                }
            }

            //запрос на следующее действие
            System.out.println(
                    "1)Добавить запись\n"
                            + "2)Удалить запись\n"
                            + "3)Вывести список\n"
                            + "4)выход\n");
            System.out.println("Выберите номер операции: ");
            act = bf.readLine();
        }
        System.out.println("До свидания!");
    }
}
