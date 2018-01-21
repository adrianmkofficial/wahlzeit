
## SodaPhoto

- UploadPhotoFormHandler.doHandlePost() is called to process the user interaction of an image upload.
- Calls createPhoto()-method which SodaPhotoManager.class inherits from PhotoManager.class.
- PhotoManager.createPhoto() calls PhotoUtil.createPhoto()-method.
- PhotoUtil.createPhoto() calls createPhoto()-method on the singleton instance of the SodaPhotoFactory which creates a new SodaPhoto.

#### Object creation solution as a point in the solution space
- Delegation: seperate-object (SodaPhotoFactory#createPhoto(PhotoId))
- Selection: by-subclassing  -> (PhotoFactory delegates to SodaPhotoFactory)
- Configuration: in-code
- Instantiation: in-code (constructor call w/ "new")
- Initialization: default (constructor arguments)
- Building: N/A (currently no dependent object structure e.g. SodaType is built during upload)


## Soda


#### Object creation solution as a point in the solution space
- Delegation:
- Selection:
- Configuration:
- Instantiation:
- Initialization:
- Building: