package cloud.ptl.xtmcarrental.utils;

import cloud.ptl.xtmcarrental.exceptions.ObjectDoesNotExists;

import java.util.Optional;

public class CheckExistence {
    public static void of(Optional<?> optional) throws ObjectDoesNotExists {
        if(optional.isEmpty())
            throw new ObjectDoesNotExists();
    }
}
