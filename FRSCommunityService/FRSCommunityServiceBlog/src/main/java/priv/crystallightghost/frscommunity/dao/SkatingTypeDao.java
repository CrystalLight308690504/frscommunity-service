package priv.crystallightghost.frscommunity.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import priv.crystallightghost.frscommunity.entity.skatingtype.SkatingType;


public interface SkatingTypeDao extends JpaRepository<SkatingType,Long>, JpaSpecificationExecutor<SkatingType> {

}
