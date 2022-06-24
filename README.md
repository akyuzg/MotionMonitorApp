## Motion Traking Application

### Jetpack Libraries which are used in the project
1. Hilt Dagger
2. Room
3. Navigation
4. Coroutines and Flow
5. MVVM

### Architecture
MVVM with Clean Architecture 


1. Data Layer
    * Local Database
        - Room Entities
    * Repository Implementations
2. Domain Layer
    * Models
    * Repository Abstractions
    * Usecases
3. Presentation Layer
  
### Notes
- Coroutines and Flow used with Room database and used for collecting ball coordinates 
- You can record x, y, z coordinates. But no object used to show the movement in z-axis.
