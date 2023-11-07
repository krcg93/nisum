package project.nisum.infrastructure.shared.utils;

public class Expression {

    public static String passwordExpression() {
        return "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])([A-Za-z\\d$@$!%*?&]|[^ ]){8,15}$";
    }
}
