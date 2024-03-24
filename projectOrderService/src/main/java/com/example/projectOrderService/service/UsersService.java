package com.example.projectOrderService.service;

import com.example.projectOrderService.model.Orders;
import com.example.projectOrderService.model.Users;
import com.example.projectOrderService.repository.UsersRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final SessionFactory sessionFactory;

    @Autowired
    public UsersService(UsersRepository usersRepository,
                        SessionFactory sessionFactory) {
        this.usersRepository = usersRepository;
        this.sessionFactory = sessionFactory;
    }

    public Users createUser(Users users) {
        return usersRepository.save(users);
    }

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getOneUser(Long id) {
        return usersRepository.findById(id).orElse(null);
    }

    public Users updateUser(Users users) {

        if (users.getUserStatus().equals("Offline")) {
            try(Session session = sessionFactory.openSession()) {
                Transaction transaction = session.beginTransaction();
                Query orderQuery = session.createQuery("from Orders where responsible_id = :id and orderStatus != 'Выполнено'");
                orderQuery.setParameter("id", users.getId());
                List<Orders> orders = orderQuery.list();
                Query cntQuery = session.createQuery("select count(o.id) as cnt, users.id from Orders o where users.userStatus = 'Online' and users.id != :id group by users.id order by cnt");
                cntQuery.setParameter("id", users.getId());
                List cntList = cntQuery.list();
                Object result[] = (Object[]) cntList.get(0);
                Long respId = (Long) result[1];
                for(Orders order: orders) {
                    Query updOrder = session.createQuery("update Orders set responsible_id = :id where id = :orderId");
                    updOrder.setParameter("id", respId);
                    updOrder.setParameter("orderId", order.getId());
                    updOrder.executeUpdate();
                }
                transaction.commit();
            }
        }
        return usersRepository.save(users);
    }

    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

}
