package priv.crystallightghost.frscommunity.converter;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 * @Author CrystalLightGhost
 * @Date 2020/9/11 17:46
 * @Version 1.0
 * @Descriotion
 */
public class DateConverter implements AttributeConverter<String, Date> {

    @Override
    public Date convertToDatabaseColumn(String date) {

        if (StringUtils.isEmpty(date)) {
            long l = System.currentTimeMillis();
            return new Date(l);
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        if (StringUtils.isEmpty(date)) {
            long l = System.currentTimeMillis();
            return new Date(l);
        }
        try {
            java.util.Date datePared = sdf2.parse(date);
            return new Date(datePared.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long l = System.currentTimeMillis();
        return new Date(l);

    }

    @Override
    public String convertToEntityAttribute(Date date) {

        if (StringUtils.isEmpty(date)) {
            long l = System.currentTimeMillis();
            return "";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date);
        return str;

    }
}
