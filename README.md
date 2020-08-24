# OOD Practice - Theater Reservation System
## Controller
- ReservationSystem
    - The main method, create theater and set up the service system, only has static methods
    - Trigger the service by call the **serve()** method of ReservationService
- ReservationService
    - Commandline parser, control the working flow of this system, only has static methods
    - Get commands, ask the view to display and ask the model to change data
## Model
- Seat
    - The basic data unit in this system
    - Has **seatName**, **isAccessible**, **reservedFor** properties to store attributes of a seat object
    - The **toString()** return different pattern according to the values of isAccessible and the reservedFor, helping the view part to display
    - The isAccessible and reservedFor don't directly support the reserve action of theater
- Row
    - The middle-level data unit, acting as a list of seats, creating seats when initialized
    - The **isAccessible** and the seatsRemain used to help the reservation to quickly find which row to book
    - The **toString()** method will concatenate the view of seats, support the view part
- Theater
    - The top-level data unit in the Model, creating rows when initialized, also acts as as the data modifier
    - The fields **name**, **numberOfRows**, **seatsPerRow**, and **rowByNumber** are used to support the reservation
    - The numberOfRows uses a HashMap to map the rows and their number, in case for further modification of the naming pattern
    - The **findRow()** is used to find an available row and return its number to the controller
    - The **reserve()** is used to perform the reservation action, it can only be triggered when the findRow() let the controller know there is a valid row
    - The **toString()** concatenates row numbers and the views of rows, support the view part
## View
- TheaterDisplayer
    - The **display(Theater)** method is used to visualize the theater when the user tell the controller to trigger the view function
