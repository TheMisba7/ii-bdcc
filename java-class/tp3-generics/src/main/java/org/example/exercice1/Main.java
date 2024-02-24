package org.example.exercice1;

public class Main {
    public static void main(String[] args) {
        StorageGenerique<Integer> storage = new StorageGenerique<>();

        StorageGenerique<String> languages = new StorageGenerique<>();

        languages.addElement("JAVA");
        languages.addElement("Lua");
        languages.addElement("C");
        languages.addElement("RUST");
        languages.addElement("GO");

        System.out.println(languages.getSize());
        System.out.println(languages.getElement(3));
        languages.removeElement(1);
        System.out.println(languages.getElement(1));;

        storage.addElement(12);
        storage.addElement(29);
        storage.addElement(89);

        System.out.println(storage.getElement(2));
        storage.removeElement(2);

        System.out.println(storage.getSize());
    }
}
