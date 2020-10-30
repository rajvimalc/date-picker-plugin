package dev.vcs.jenkins.datepicker;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Vimalraj Chandra Sekaran (rajvimalc)
 * Email: rajvimalc@gmail.com
 * Created on: 8/7/2019 3:25 PM
 */
public class IntegerParamMethod {

    private final static Pattern PATTERN = Pattern.compile("^(?<name>.+)\\((?<parameter>[0-9]+)\\);?$");

    @Getter
    public final String name;

    @Getter
    public final Integer parameter;

    @Getter
    private final String code;

    public IntegerParamMethod(String code) {
        this.code = code;
        Matcher matcher = PATTERN.matcher(code);
        name = matcher.matches() ? matcher.group("name") : null;
        parameter = matcher.matches() ? Integer.parseInt(matcher.group("parameter")) : null;
    }

}
