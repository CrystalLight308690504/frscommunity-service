package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.entity.user.User;
import priv.crystallightghost.frscommunity.entity.user.UserFollowerEntity;


/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 18:26
 * @Version 1.0
 * @Descriotion JpaRepository<User, Long>，JpaSpecificationExecutor<User>  User 是dao的类型 Long 是指Dao的Id主键的类型
 */
public interface UserFollowerEntityDao extends JpaRepository<UserFollowerEntity, Long>, JpaSpecificationExecutor<UserFollowerEntity> {
    Slice<UserFollowerEntity> findByUser(User user, Pageable pageable);
    Slice<UserFollowerEntity> findByUserFollowed(User userFollowed, Pageable pageable);
}
