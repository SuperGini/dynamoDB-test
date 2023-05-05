package com.gini.entity.v2;

import software.amazon.awssdk.enhanced.dynamodb.AttributeConverter;
import software.amazon.awssdk.enhanced.dynamodb.AttributeValueType;
import software.amazon.awssdk.enhanced.dynamodb.EnhancedType;
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.EnhancedAttributeValue;
import software.amazon.awssdk.enhanced.dynamodb.internal.converter.attribute.StringAttributeConverter;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.time.Instant;

public class ConverterX implements AttributeConverter<Department> {

    @Override
    public AttributeValue transformFrom(Department input) {
        StringAttributeConverter converter  = StringAttributeConverter.create();
     //   converter.


       return converter.transformFrom(input.getDepartmentCode());

       //arunca null pointer excpetion daca e null
        //    return  EnhancedAttributeValue.fromString(input.getDepartmentCode()).toAttributeValue();

    }

    @Override
    public Department transformTo(AttributeValue input) {
        return null;
    }

    @Override
    public EnhancedType<Department> type() {

        return null;
    }

    @Override
    public AttributeValueType attributeValueType() {
        return null;
    }
}
