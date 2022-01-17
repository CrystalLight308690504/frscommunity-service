package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.pojo.system.User;

/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 18:26
 * @Version 1.0
 * @Descriotion
 *  JpaRepository<User,Long>，JpaSpecificationExecutor<User>  User 是dao的类型 Long 是指Dao的Id主键的类型
 */
public interface UserDao extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {


    User findUserByPhoneNumber(String phoneNumber);
    User findUserByUserName(String userName);
    User findUserByEmail(String email);
    User findUserByUserId(long userId);

}
