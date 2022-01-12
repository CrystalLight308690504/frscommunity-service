package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.pojo.system.UserInformation;

/**
 * @Date 2022/1/11
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */
public interface UserInformationDao extends JpaRepository<UserInformation,Long>, JpaSpecificationExecutor<UserInformation> {

}
