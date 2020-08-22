package com.maistruk.hibernate_1;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.maistruk.hibernate_1.manyToMany.LaptopManyToMany;
import com.maistruk.hibernate_1.manyToMany.StudentManyToMany;
import com.maistruk.hibernate_1.model.Alien;
import com.maistruk.hibernate_1.model.AlienName;
import com.maistruk.hibernate_1.model.Alien_2;
import com.maistruk.hibernate_1.model.Laptop;
import com.maistruk.hibernate_1.model.Student;
import com.maistruk.hibernate_1.oneToMany.LaptopOneToMany;
import com.maistruk.hibernate_1.oneToMany.StudentOneToMany;
import com.maistruk.hibernate_1.oneToOne.LaptopOneToOne;
import com.maistruk.hibernate_1.oneToOne.StudentOneToOne;

public class Main {

    public static void main(String[] args) {
        // saveAlien();

        // getAlien();

        // saveAlien2();

        // getAlien2CacheLevel_1();

        // saveOneToOne();

        // saveOneToMany();

        // getStudentOneToManyEAGER();

        // saveManyToMany();

        // saveStudentsHQL();

        // getStudentsHQL();

        // getStudentsSQL();

        // saveLaptopsHQL();

        // getLaptopsHQL();

        getLoad();

    }

    public static void saveAlien() {
        Alien alien = new Alien();
        alien.setId(102);
        alien.setName("Navin");
        alien.setColor("Red");

        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory factory = config.buildSessionFactory(reg);
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(alien);

        transaction.commit();
    }

    public static void getAlien() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory factory = config.buildSessionFactory(reg);
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Alien alien = (Alien) session.get(Alien.class, 102);

        transaction.commit();

        System.out.println(alien);
    }

    public static void saveAlien2() {
        AlienName name = new AlienName();
        name.setFirstName("Navin");
        name.setLastName("Reddy");
        Alien_2 alien = new Alien_2();
        alien.setId(101);
        alien.setColor("Red");
        alien.setName(name);

        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien_2.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory factory = config.buildSessionFactory(reg);
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(alien);

        transaction.commit();
    }

    public static void getAlien2CacheLevel_1() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Alien_2.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory factory = config.buildSessionFactory(reg);
        Session session1 = factory.openSession();
        session1.beginTransaction();
        
        Alien_2 alien = (Alien_2) session1.get(Alien_2.class, 101);
        System.out.println(alien);

        // Second value we get from cache because we already called this value
        // We do not go in database second time for value
        alien = (Alien_2) session1.get(Alien_2.class, 101);
        System.out.println(alien);
        session1.getTransaction().commit();
        session1.close();

        // Each session have own cache level 1, so we can not take value from session1
        // cache
        // So we should get value from database again
        Session session2 = factory.openSession();
        session2.beginTransaction();
        alien = (Alien_2) session2.get(Alien_2.class, 101);
        System.out.println(alien);
        session2.getTransaction().commit();
        session2.close();
    }

    public static void saveOneToOne() {
        LaptopOneToOne laptop = new LaptopOneToOne();
        laptop.setId(101);
        laptop.setName("Dell");

        StudentOneToOne student = new StudentOneToOne();
        student.setId(1);
        student.setName("Navin");
        student.setMarks(50);
        student.setLaptop(laptop);

        Configuration config = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(StudentOneToOne.class).addAnnotatedClass(LaptopOneToOne.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(reg);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(laptop);
        session.save(student);

        session.getTransaction().commit();
    }

    public static void saveOneToMany() {
        LaptopOneToMany laptop = new LaptopOneToMany();
        laptop.setId(101);
        laptop.setName("Dell");
        LaptopOneToMany laptop2 = new LaptopOneToMany();
        laptop2.setId(102);
        laptop2.setName("Hp");
        LaptopOneToMany laptop3 = new LaptopOneToMany();
        laptop3.setId(103);
        laptop3.setName("Asus");
        LaptopOneToMany laptop4 = new LaptopOneToMany();
        laptop4.setId(104);
        laptop4.setName("Macbook");
        LaptopOneToMany laptop5 = new LaptopOneToMany();
        laptop5.setId(105);
        laptop5.setName("Acer");

        StudentOneToMany student = new StudentOneToMany();
        student.setId(1);
        student.setName("Navin");
        student.setMarks(50);
        student.getLaptops().add(laptop);
        student.getLaptops().add(laptop2);
        StudentOneToMany student2 = new StudentOneToMany();
        student2.setId(2);
        student2.setName("Roman");
        student2.setMarks(60);
        student2.getLaptops().add(laptop3);
        StudentOneToMany student3 = new StudentOneToMany();
        student3.setId(3);
        student3.setName("Vika");
        student3.setMarks(70);
        student3.getLaptops().add(laptop4);
        student3.getLaptops().add(laptop5);

        laptop.setStudent(student);
        laptop2.setStudent(student);
        laptop3.setStudent(student2);
        laptop4.setStudent(student3);
        laptop5.setStudent(student3);

        Configuration config = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(StudentOneToMany.class).addAnnotatedClass(LaptopOneToMany.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(reg);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(student);
        session.save(student2);
        session.save(student3);
        session.save(laptop);
        session.save(laptop2);
        session.save(laptop3);
        session.save(laptop4);
        session.save(laptop5);

        session.getTransaction().commit();
    }

    public static void getStudentOneToManyEAGER() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(StudentOneToMany.class).addAnnotatedClass(LaptopOneToMany.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(reg);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        StudentOneToMany student = (StudentOneToMany) session.get(StudentOneToMany.class, 3);

        System.out.println(student);
        session.getTransaction().commit();
    }

    public static void saveManyToMany() {
        LaptopManyToMany laptop = new LaptopManyToMany();
        laptop.setId(101);
        laptop.setName("Dell");
        LaptopManyToMany laptop2 = new LaptopManyToMany();
        laptop2.setId(102);
        laptop2.setName("Hp");

        StudentManyToMany student = new StudentManyToMany();
        student.setId(1);
        student.setName("Navin");
        student.setMarks(50);
        student.getLaptops().add(laptop);
        student.getLaptops().add(laptop2);

        laptop.getStudents().add(student);
        laptop2.getStudents().add(student);

        Configuration config = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(StudentManyToMany.class).addAnnotatedClass(LaptopManyToMany.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(reg);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(laptop);
        session.save(laptop2);
        session.save(student);

        session.getTransaction().commit();
    }

    public static void saveStudentsHQL() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(reg);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Random random = new Random();

        for (int i = 1; i <= 50; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("name" + i);
            student.setMarks(random.nextInt(50) + 50);
            session.save(student);
        }

        session.getTransaction().commit();
    }

    public static void getStudentsHQL() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(reg);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query;
        List<Student> students;
        Student student;
        Object[] object;
        List<Object[]> objects;

//        query = session.createQuery("from Student"); // SQL -> SELECT * FROM student
//        query = session.createQuery("from Student where marks > 80"); // SQL -> SELECT * FROM student WHERE makrs > 80
//        students = query.list();
//        for (Student stud : students) {
//            System.out.println(stud);
//        }
//
//        query = session.createQuery("from Student where id = 4"); // SQL -> SELECT * FROM student WHERE id = 4
//        student = (Student) query.uniqueResult();
//        System.out.println(student);
//
//        query = session.createQuery("select id, name, marks from Student where id = 4");
//        object = (Object[]) query.uniqueResult();
//        System.out.println(object[0] + " " + object[1] + " " + object[2]);
//
//        query = session.createQuery("select id, name, marks from Student");
//        objects = (List<Object[]>) query.list();
//        for (Object[] obj : objects) {
//            System.out.println(obj[0] + " " + obj[1] + " " + obj[2]);
//        }
//
//        query = session.createQuery("select id, name from Student s where s.marks > 70");
//        objects = (List<Object[]>) query.list();
//        for (Object[] obj : objects) {
//            System.out.println(obj[0] + " " + obj[1]);
//        }

        int mark = 70;
        query = session.createQuery("select sum(marks) from Student s where s.marks > :mark");
        query.setParameter("mark", mark);
        Long marks = (Long) query.uniqueResult();
        System.out.println(marks);

        session.getTransaction().commit();
    }

    public static void getStudentsSQL() {
        // Narive Queries...
        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Student.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(reg);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        SQLQuery query = session.createSQLQuery("select * from student where marks > 80");
//        query.addEntity(Student.class);
//        List<Student> students = query.list();
//        for(Student stud : students) {
//            System.out.println(stud);
//        }

        SQLQuery query = session.createSQLQuery("select id, name from student where marks > 80");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List students = query.list();
        for (Object stud : students) {
            Map map = (Map) stud;
            System.out.println(map.get("id") + " " + map.get("name"));
        }

        session.getTransaction().commit();
    }

    public static void saveLaptopsHQL() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Laptop.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(reg);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Random random = new Random();

        for (int i = 1; i <= 50; i++) {
            Laptop laptop = new Laptop();
            laptop.setId(i);
            laptop.setBrand("brand" + i);
            laptop.setPrice(random.nextInt(200) + 200);
            session.save(laptop);
        }

        session.getTransaction().commit();
    }

    public static void getLaptopsHQL() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Laptop.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(reg);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Laptop laptop = new Laptop();
        laptop.setId(51);
        laptop.setBrand("Sony");
        laptop.setPrice(750);
        session.save(laptop);

        session.getTransaction().commit();
    }

    public static void getLoad() {
        Configuration config = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Laptop.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = config.buildSessionFactory(reg);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // get method always take object from database
//        Laptop laptop = (Laptop) session.get(Laptop.class, 6);
//        System.out.println(laptop);

        // load method give you a Proxy object
        // If we not using this object so load method will not take it from database,
        // for example if we not put our laptop in System.out.println(laptop) so we not
        // take it from database
        Laptop laptop = (Laptop) session.load(Laptop.class, 6);
        System.out.println(laptop);

        session.getTransaction().commit();
    }

}
