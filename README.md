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

### Class: DatePickerDefinition

- Name: Mandatory
  
- Description: Optional
  
- Default Value:

  1. Can be blank.

  2. Format: `yyyy-MM-dd`

     Example: `2020-01-01`
 
     Defaulted to ISO format `yyyy-MM-dd` (output of `<input type="date"/>`)

### Class: TimestampPickerDefinition

- Name: Mandatory
  
- Description: Optional
  
- Default Value:

  1. Can be blank.

  2. Format: `yyyy-MM-dd'T'HH:mm`

     Example: `2020-01-01T14:10`
 
     Defaulted to ISO format `yyyy-MM-dd'T'HH:mm` (output of `<input type="datetime-local"/>`)


### Usage:

- Parameterized:

![Screenshot](parameterized.png)

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
                $class: 'TimestampPickerDefinition', 
                defaultValue: '2019-08-08T10:10', 
                description: 'Date to run', 
                name: 'Start Date'
            ],
            [
                $class: 'DatePickerDefinition', 
                defaultValue: '2019-08-10', 
                description: 'Date to wait', 
                name: 'End Date'
            ]
        ]
    )
    echo ("Date to run: " + dates['Start Date'])
    echo ("Date to wait: " + dates['End Date'])
}
```

![Screenshot](userinput1.png)
![Screenshot](userinput2.png)

### Console Output:
```
Started by user VIMALRAJ CHANDRA SEKARAN
Running in Durability level: MAX_SURVIVABILITY
[Pipeline] Start of Pipeline
[Pipeline] node
Running on Jenkins in C:\Program Files (x86)\Jenkins\workspace\FirstPipelineJob
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
[Pipeline] }
[Pipeline] // node
[Pipeline] End of Pipeline
Finished: SUCCESS
```