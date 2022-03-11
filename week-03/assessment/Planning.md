# Solar Farm Planning

## User Tasks 

### Interface - UserTasks
 * Add panel 
 * Delete panel 
 * Update panel
 * View panels 

### Implimentation 
#### Add Panel (new class)
* User must be able to select add panel from menu option 
* enter a section (string)
* enter a row (int 1-250)
* enter a column (int 1 - 250)
* make sure a panel does not already exist there
* enter a material (choose from list) - this will be enum
* enter the year installed 
* tracking (yes/no)
* save panel 
* success or failure message 
* Each panel will be a new object 

#### Delete Panel (new class)
* User must be able to select delete panel from menu option 
* enter section
* enter row
* enter column
* if the panel exists delete it else send a message back to the user

#### Update Panel 
* User must be able to select update panel from menu option
* enter section
* enter row
* enter column
* If the panel exists update fields, else send message to user


## Classes
### Panels 
* unique identifier 
* string section 
* int row
* int column
* Material material 
* int year (date type??)
* boolean isTracking?


## Models

## Enumns
 ### Material


## Data
## DataAccessException extends Exception (checked acception)

### Panel File Repository 
fields 
- String filepath 

Public Methods 
* Panel Add(panel)
* boolean update (panel)
* list<Panel> findBySection(string)
* boolean deleteById(int)

Private Methods
* list<Panel> findAll()
* void writeAll (list<Panel>);
* String serialize(Panel)
* Panel deserialize 

### Panel Repository (interface)


## Domain
#### Panel Result
Fields 
Panel panel
List <string> errorMessages

Public Methods
* void addError(string)
* void setPanel(panel)
* panel getPanel() 
* boolean isSuccess() - returns true if error.Messages.size() is 0 else false 
* List<String> getMessage() - cloning, not direct reference (return message as new array)


## UI


#### View

Public Methods
- String readString(String)
- String readRequired(String)
- in readInt(String)

#### Controller


