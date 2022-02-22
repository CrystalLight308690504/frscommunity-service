package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.pojo.system.UserFollower;

/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 18:26
 * @Version 1.0
 * @Descriotion JpaRepository<User, Long>，JpaSpecificationExecutor<User>  User 是dao的类型 Long 是指Dao的Id主键的类型
 */
public interface UserFollowerDao extends JpaRepository<UserFollower, Long>, JpaSpecificationExecutor<UserFollower> {
    boolean existsByUserIdAndUserFollowedId(long userId, long userFollowedId);
    UserFollower findByUserIdAndUserFollowedId(long userId, long userFollowedId);
    long countByUserFollowedId(long userFollowedId);
    long countByUserId(long userId);

}
