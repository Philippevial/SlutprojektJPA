package org.example;

import org.example.daos.AdminDao;
import org.example.daos.StudentDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    Menu menu = new Menu();
    menu.runApp();
    }
}
