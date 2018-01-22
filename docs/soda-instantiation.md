
## SodaPhoto

- UploadPhotoFormHandler#doHandlePost() is called to process the user interaction of an image upload.
- Calls createPhoto()-method which SodaPhotoManager.class inherits from PhotoManager.class.
- PhotoManager#createPhoto() calls PhotoUtil#createPhoto()-method.
- PhotoUtil#createPhoto() calls createPhoto()-method on the singleton instance of the SodaPhotoFactory which creates a new SodaPhoto.

#### Object creation solution as a point in the solution space
- Delegation: seperate-object (SodaPhotoFactory#createPhoto(PhotoId))
- Selection: by-subclassing (PhotoFactory delegates to SodaPhotoFactory)
- Configuration: in-code
- Instantiation: in-code (constructor call w/ "new")
- Initialization: default (constructor arguments)
- Building: N/A (currently no dependent object structure e.g. SodaType is built during upload)


## Soda

A Soda features a SodaType. SodaType stores common attributes e.g. the "core" name of a soda (Coca-Cola), the manufacturer and the country of origin.
The Soda object itself additionally features the attributes variation (e.g. "Original" / "Diet" / "Cherry") as well as a serving size in ml.

- SodaManager#createSoda()-method is called to create a new Soda and its SodaType.
- createSoda()-method itself calls the SodaType#getSodaType()-method which returns the according SodaType object.
- SodaType#createInstance()-method is called to create an actual Soda object.

**NOTE:** Currently all of these methods remain unused in production code as there is no UI element that a user has access to for creation of a Soda.

#### Object creation solution as a point in the solution space
- Delegation: seperate-object (SodaManager#createSoda)
- Selection: on-the-spot (concrete class not varying: Soda)
- Configuration: none
- Instantiation: in-code (SodaManager creates Soda using keyword new)
- Initialization: default (constructor arguments)
- Building: default (SodaManager creates dependent SodaType)