# RestAPIFramework
This is an example of a framework which uses Java, Cucumber, Maven, Rest Assured to test a dummy API.

Created Maven project, added junit, cucumber, rest assured dependencies in the pom.xml file

Defined the framework structure:
  - cucumber.Options with the TestRunner class used to execute the tests
  - features with the feature file containing the scenarios defined in Gherkin
  - resources with
    - the APIResources enum class for defining the API endpoints and methods needed
    - global.properties file used to store the URLs accesed
    - TestDataBuild used for adding test input data 
    - Utils class used for the code maintainability containing methods for getting global properties, requesting specifications, logging, getting JSON path, anything that hardcoded which should be found in the tests
    - stepsDefinition file defining the Gherking steps
    
