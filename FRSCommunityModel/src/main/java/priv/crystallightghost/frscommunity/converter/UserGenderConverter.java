package priv.crystallightghost.frscommunity.converter;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 17:46
 * @Version 1.0
 * @Descriotion
 */
public class UserGenderConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String s) {

        if (StringUtils.isEmpty(s))
            return  1;
        if ("男".equals(s)) {
            return 1;
        }else {
            return 0;
        }


    }

    @Override
    public String convertToEntityAttribute(Integer i) {
        if (StringUtils.isEmpty(i))
            return  "";
        if ( 1==i ) {
            return "男";
        }else {
            return "女";
        }
    }
}
