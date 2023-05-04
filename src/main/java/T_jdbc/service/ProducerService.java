package T_jdbc.service;

import T_jdbc.conn.ConnectionFactory;
import T_jdbc.domain.Producer;
import T_jdbc.repository.ProducerRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
    private static void requiredValidId(Integer id){
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Invalid value for id");
    }
}