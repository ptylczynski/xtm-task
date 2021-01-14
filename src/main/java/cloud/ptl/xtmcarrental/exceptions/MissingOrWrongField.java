package cloud.ptl.xtmcarrental.exceptions;

import lombok.Data;

@Data
public class MissingOrWrongField extends Exception{
    private String fieldName;

    public MissingOrWrongField(String fieldName){
        super(fieldName);
        this.fieldName = fieldName;
    }
}
