package domain.model;

public enum Role {
    ADMIN("ADMIN"), USER("USER");


    private String stringValue;

    private Role(String stringValue){
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }
}
