# Date Picker Plugin
Date Picker Plugin for Jenkins (built with Gradle)


### Author:

###### Name: Vimalraj Chandra Sekaran

###### Github ID: rajvimalc

###### Email: rajvimalc@gmail.com

### Based on the code from:
https://github.com/jenkinsci/date-parameter-plugin

Thanks to Github User:
###### JuHyun Lee (leejaycoke)


## About parameters:

### Class: DateTimePickerDefinition

- Name: Mandatory
  
- Description: Optional
  
- Default Value:

  1. Can be blank.

  2. Format: `yyyy-MM-dd'T'HH:mm`

     Example: `2020-01-01T14:10`
 
     Defaulted to ISO format `yyyy-MM-dd'T'HH:mm` (output of `<input type="datetime-local"/>`)

### Class: DatePickerDefinition

- Name: Mandatory
  
- Description: Optional
  
- Default Value:

  1. Can be blank.

  2. Format: `yyyy-MM-dd`

     Example: `2020-01-01`
 
     Defaulted to `yyyy-MM-dd` format (output of `<input type="date"/>`)

### Class: TimePickerDefinition

- Name: Mandatory
  
- Description: Optional
  
- Default Value:

  1. Can be blank.

  2. Format: `HH:mm`

     Example: `14:10`
 
     Defaulted to `HH:mm` format (output of `<input type="time"/>`)


### Usage:

- Parameterized:

![Screenshot](usage/parameterized.png)

- Pipeline Script:

```
node {
    stage('Init') {
       echo ("Initializing...")
    }
    def dates = input (
        id: 'dates', 
        message: "When to run?", 
        parameters: [
            [
                $class: 'DateTimePickerDefinition', 
                defaultValue: '2019-08-08T10:10', 
                description: 'Date to run', 
                name: 'Start Date'
            ],
            [
                $class: 'DatePickerDefinition', 
                defaultValue: '2019-08-10', 
                description: 'Date to wait', 
                name: 'End Date'
            ],
            [
                $class: 'TimePickerDefinition', 
                defaultValue: '14:10', 
                description: 'End time', 
                name: 'End Time'
            ]
        ]
    )
    echo ("Date to run: " + dates['Start Date'])
    echo ("Date to wait: " + dates['End Date'])
    echo ("End Time: " + dates['End Time'])
}
```

![Screenshot](usage/userinput-datetime.png)
![Screenshot](usage/userinput-date.png)
![Screenshot](usage/userinput-time.png)

### Console Output:
```
Started by user VIMALRAJ CHANDRA SEKARAN
Running in Durability level: MAX_SURVIVABILITY
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in C:\Program Files (x86)\Jenkins\workspace\DatePickerPipelineJob
[Pipeline] {
[Pipeline] stage
[Pipeline] { (Init)
[Pipeline] echo
Initializing...
[Pipeline] }
[Pipeline] // stage
[Pipeline] input
Input requested
Approved by VIMALRAJ CHANDRA SEKARAN
[Pipeline] echo
Date to run: 2019-08-08T10:10
[Pipeline] echo
Date to wait: 2019-08-10
[Pipeline] echo
End Time: 16:10
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
```