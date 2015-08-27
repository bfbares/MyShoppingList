package com.borjabares.myshoppinglist.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class HibernateAwareObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = -284719090434877186L;

    public HibernateAwareObjectMapper() {
        Hibernate4Module hibernate4Module = new Hibernate4Module();
        registerModule(hibernate4Module);
        this.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        this.setTimeZone(TimeZone.getTimeZone("GMT"));
    }
}