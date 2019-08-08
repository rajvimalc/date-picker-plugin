package dev.vcs.jenkins.datepicker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Vimalraj Chandra Sekaran (rajvimalc)
 * Email: rajvimalc@gmail.com
 * Created on: 8/7/2019 3:25 PM
 */
public class IntegerParamMethod {

    private final static Pattern PATTERN = Pattern.compile("^(?<name>.+)\\((?<parameter>[0-9]+)\\);?$");

    public String name;

    public Integer parameter;

    private String code;

    public IntegerParamMethod(String code) {
        this.code = code;
        parse();
    }

    private void parse() {
        Matcher matcher = PATTERN.matcher(code);
        if (matcher.matches()) {
            name = matcher.group("name");
            parameter = Integer.parseInt(matcher.group("parameter"));
        }
    }

    public String getName() {
        return name;
    }

    public Integer getParameter() {
        return parameter;
    }

}
