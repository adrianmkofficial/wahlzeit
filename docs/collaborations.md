```java

/**
 * Description of collaboration: YourObjectPhoto with YourObject (SodaPhoto - Soda)
 */
public collaboration SodaPhotoSoda {

    // Client
    public role SodaPhoto {
        public Soda getSoda();
        public void setSoda(Soda soda);
    }

    // Service
    public role Soda;
}

// Bindings
public class Soda extends DataObject binds SodaPhotoSoda.Soda {}

public class SodaPhoto extends Photo binds SodaPhotoSoda.SodaPhoto {
    public Soda getSoda() {
        return SodaPhotoSoda.SodaPhoto.getSoda();
    }

    public void setSoda(Soda soda) {
        SodaPhotoSoda.SodaPhoto.setSoda(soda);
    }
}

/**
 * Description of collaboration: YourObject with YourObjectType (Soda - SodaType)
 */
public collaboration SodaSodaType {

    // Base Object
    public role Soda {
        public SodaType getSodaType();
        public void setSodaType(SodaType st);
    }

    // Type Object
    public role SodaType {
        public Soda createInstance(String variation, Double serving_size_ml);
        public boolean hasInstance(Soda s);
    }
}

// Bindings
public class Soda extends DataObject binds SodaSodaType.Soda {
    public SodaType getSodaType() {
        return SodaSodaType.Soda.getSodaType();
    }
    
    public void setSodaType(SodaType st) {
        SodaSodaType.Soda.setSodaType(st);
    }
}

public class SodaType extends DataObject binds SodaSodaType.SodaType {
    public Soda createInstance(String variation, Double serving_size_ml) {
        return SodaSodaType.SodaType.createInstance(String variation, Double serving_size_ml);
    }
    
    public boolean hasInstance(Soda s) {
        return SodaSodaType.SodaType.hasInstance(Soda s);
    }
}


/**
 * Description of collaboration: A collaboration of your choice (SodaManager - Soda)
 */

public collaboration SodaManagerSoda {

    // Manager
    public role SodaManager {
        public Soda getSoda(String variation, Double serving_size_ml);
    }
    
    // Element
    public role Soda { }
}

// Bindings
public class SodaManager extends ObjectManager binds SodaManagerSoda.SodaManager {
    public Soda getSoda(String variation, Double serving_size_ml){
        return SodaManagerSoda.SodaManager.getSoda(String variation, Double serving_size_ml);
    }
}

public class Soda extends DataObject binds SodaManagerSoda.Soda {}



```