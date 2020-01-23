
Architecture Follow
-MVVM

Folder Structure
- java -- Android Source code

java --> dataModel
            -> local (RoomDB related folder, DAO, Entity, DatabaseService)
            -> model (Model class)
            -> remote (Retrofit)
            -> repo (repository)
     --> di (dependecy inject)
           -> component (dagger2, dependecy )
           -> module    (dependency provider)
           -> scope     (lable)
     --> ui
          -> baseclasses (Base activity, Base viewModel)
          -> home  (Screem)
          -> splash
     --> utlities


- Android Test --> HomeActivityTest (Espresso UITesting folder)
- Test         --> JUnit test (Mockito)


****Tools and Library Used in Project*****

rxJava - for Background Task (Network and db)
network - retrofit
database - room
dependency Injection - dagger2
Image dowloading - Glide
JsonParsing - gson
swipe cards - Mindork's place holder lib
Mockito - For JUnit test
