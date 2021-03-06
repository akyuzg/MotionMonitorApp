## Motion Monitor Application

### Jetpack Libraries which are used in the project
1. Hilt Dagger
2. Room
3. Navigation
4. Coroutines and Flow
5. MVVM

### Database
1. Records table
2. Points table
#### Db Entity Relationships
1. each record has many points: 1 - n relation


### Architecture
MVVM with Clean Architecture using SOLID principles 
1. App Layer
    * Dependency Injection
2. Data Layer
    * Local Database
        - Room Entities
    * Repository Implementations
3. Domain Layer
    * Models
    * Repository Abstractions
    * Usecases
4. Presentation Layer

  
### Notes
- Coroutines and Flow used with Room database to collect ball coordinates 
