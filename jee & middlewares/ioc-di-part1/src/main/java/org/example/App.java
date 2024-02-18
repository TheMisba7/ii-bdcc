package org.example;

import org.example.dao.DaoImpl;
import org.example.dao.IDao;
import org.example.service.IMetier;
import org.example.service.ServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        IMetier iMetier = null;
        if (!(args.length > 0))
            throw new UnsupportedOperationException("Please Provide injection type.");

        int type = Integer.parseInt(args[0]);
        Injection injection = Injection.parseInt(type);
        switch (injection) {
            case STATIC -> {
                System.out.println("Static injection");
                iMetier = new ServiceImpl(new DaoImpl());
            }
            case DYNAMIC -> {
                System.out.println("Dynamic injection");
                try (Scanner scanner = new Scanner(Objects.requireNonNull(App.class.getResourceAsStream("/config.txt")))){
                    String daoImpl = scanner.nextLine();
                    String metierImpl = scanner.nextLine();
                    IDao iDao = (IDao) Class.forName(daoImpl).getDeclaredConstructor().newInstance();
                    iMetier = (IMetier) Class.forName(metierImpl).getDeclaredConstructor(IDao.class).newInstance(iDao);
                } catch (IllegalAccessException | ClassNotFoundException |
                         InvocationTargetException | InstantiationException | NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }
            case SPRING_ANNOTATION -> {
                System.out.println("Dependency injection with spring annotation");
                ApplicationContext context = new AnnotationConfigApplicationContext("org.example.dao", "org.example.service");
                iMetier = context.getBean(IMetier.class);
            }
            case SPRING_XML -> {
                System.out.println("Dependency injection with spring xml");
                ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
                iMetier = context.getBean(IMetier.class);
            }
        }
        System.out.println("result: " + iMetier.calculate(9, 3));
    }

}
