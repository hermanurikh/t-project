package ru.tsystems.tproject.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.tsystems.tproject.entities.Option;
import ru.tsystems.tproject.services.API.OptionService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by german on 15.11.14.
 */

public class Test {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    public static void main(String[] args) throws Exception{
        Converter converter = new Converter();
        System.out.println(Converter.getMD5("gu14929.cyber8"));
        System.out.println(converter.encodePassword("gu14929.cyber8", null));
        System.out.println(converter.isPasswordValid("1833584d5305bc838a1b8f65cef8a653", "gu14929.cyber8", null));
    }
}

