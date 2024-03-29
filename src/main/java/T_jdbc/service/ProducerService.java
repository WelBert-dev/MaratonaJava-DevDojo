package T_jdbc.service;

import T_jdbc.conn.ConnectionFactory;
import T_jdbc.domain.Producer;
import T_jdbc.repository.ProducerRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProducerService {
    public static void save(Producer producer) {
        ProducerRepository.save(producer);
    }
    public static void delete(Integer id) {
        requiredValidId(id);

        ProducerRepository.delete(id);
    }
    public static void update(Producer producer) {
        requiredValidId(producer.getId());

        ProducerRepository.update(producer);
    }
    public static List<Producer> findAll() {
        return ProducerRepository.findAll();
    }
    public static List<Producer> findByName(String name) {
        if (name == null || name.equals(""))
            throw new IllegalArgumentException("Invalid value for name!");

        return ProducerRepository.findByName(name);
    }
    public static void showProducerMetaData(){
        ProducerRepository.showProducerMetaData();
    }
    public static void showDriverMetaData(){
        ProducerRepository.showDriverMetaData();
    }
    public static void showTypeScrollWorking() {
        ProducerRepository.showTypeScrollWorking();
    }
    public static List<Producer> findByNameAndUpdateToUpperCaseInDatabase(String name) {
        if (name == null || name.equals(""))
            throw new IllegalArgumentException("Invalid value for name!");

        return ProducerRepository.findByNameAndUpdateToUpperCaseInDatabase(name);
    }
    public static List<Producer> findByNameAndInsertWhenNotFound(String name) {
        if (name == null || name.equals(""))
            throw new IllegalArgumentException("Invalid value for name!");

        return ProducerRepository.findByNameAndInsertWhenNotFound(name);
    }
    public static List<Producer> findByNameAndDeleteWhenFound(String name) {
        if (name == null || name.equals(""))
            throw new IllegalArgumentException("Invalid value for name!");

        return ProducerRepository.findByNameAndDeleteWhenFound(name);
    }
    public static List<Producer> findByNamePreparedStatement(String name) {
        return ProducerRepository.findByNamePreparedStatement(name);
    }

    public static void updatePreparedStatement(Producer producer) {
        ProducerRepository.updatePreparedStatement(producer);
    }
    public static List<Producer> findByNameCallableStatement(String name) {
        return ProducerRepository.findByNameCallableStatement(name);
    }
    private static void requiredValidId(Integer id){
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Invalid value for id!");
    }
}
