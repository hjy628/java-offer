package com.hjy.spring.escape.use_jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @auther: hjy
 * @Date: 2021/1/12 16:53
 * @Description:
 */

public class CouponSerialize extends JsonSerializer<Coupon> {
    /**
     * Method that can be called to ask implementation to serialize
     * values of type this serializer handles.
     *
     * @param value       Value to serialize; can <b>not</b> be null.
     * @param gen         Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     */
    @Override
    public void serialize(Coupon value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            //开始序列化
        gen.writeStartObject();

        gen.writeStringField("id",String.valueOf(value.getId()));
        gen.writeStringField("userId",String.valueOf(value.getUserId()));
        gen.writeStringField("couponCode",String.valueOf(value.getCouponCode()));
        gen.writeStringField("assignTime",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(value.getAssignedTime()));
        gen.writeStringField("status",String.valueOf(value.getStatus().getDesc()));
        gen.writeStringField("name",String.valueOf(value.getTemplate().getName()));
        gen.writeStringField("logo",String.valueOf(value.getTemplate().getLogo()));


        //结束序列化
        gen.writeEndObject();
    }
}
