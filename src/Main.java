import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        writeNameAgeToFile();

        getUniqueNamesFromFile();

        List<Person> persons = getPersonListFromFile("persons.txt");
        persons.forEach(System.out::println);
    }

    public static void writeNameAgeToFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ваше имя:");
        String name = scanner.nextLine();
        System.out.println("Введите ваш возраст:");
        int age = scanner.nextInt();
        try (FileWriter writer = new FileWriter("name.txt", true)) {
            writer.write(name + ";" + age + "\n");
            System.out.println("Информация успешно записана в файл name.txt");
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static void getUniqueNamesFromFile() {
        Set<String> uniqueNames = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("persons.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String [] parts = line.split("[; ,]");
                if (parts.length>0){
                    uniqueNames.add(parts[0].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        System.out.println("Уникальные имена: ");
        for (String name : uniqueNames) {
            System.out.println(name);
        }
    }

    public static List<Person> getPersonListFromFile(String filename) {
        List<Person> persons = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("[;,]");
                if (parts.length == 2) {
                    persons.add(new Person(parts[0].trim(), Integer.parseInt(parts[1].trim())));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
        return persons;
    }
}
