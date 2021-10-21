package com.zhangyk.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonUtils {

    /*
    * 如果用户不想自定义时间格式，直接用工具类自定义的默认的格式即可
    *
    * 这是自定义工具类的一个特点：两个相似功能的函数，经常是一个中调用另一个，从而节约大量代码。
    * */
    public static String getIsonDate() throws JsonProcessingException {
        return getJsonDate("yyyy-MM-dd HH:mm:ss");
    }

    /*
    * dataFormat:传入date的字符串格式，要能用于SimpleDateFormat
    * */
    public static String getJsonDate(String dataFormat) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Date date = new Date();

        //使用objectMapper来格式化输出:不使用时间戳的方式
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);
        //自定义日期的格式，这还是需要simpledateformat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dataFormat);
        //把定义好的时间格式设置为mapper的时间格式
        objectMapper.setDateFormat(simpleDateFormat);

        String str = objectMapper.writeValueAsString(date);

        return str;

    }
}
