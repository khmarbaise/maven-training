Maven Training
==============

This is intended to have examples for a Maven Workshop / Training.

* 010-simplest-pom
* 020-project-with-compiler-configuration
* 030-project-with-unit-test
* 031-project-with-resources
* 032-project-with-filtered-resources
* 040-project-dependency-exclude
* 045-project-with-sources
* 046-project-with-unit-test jar (see utbc https://github.com/khmarbaise/utbc)
* 050-project-without-warnings

May be i have to move the site examples after 555...

* 100-maven-site-m2 (First basic example)
* 110-maven-site-m3 
* 120-maven-site-m2m3 (working for M2 and M3)
* 130-maven-site-more (self defined content index.apt etc.)
* 140-maven-site-docs (multi language)

* 500-assembly-plugin
* 501-assembly-plugin
* 502-assembly-plugin (utility with CLI + packaging + appassembly plugin)

* 550-dependency-management
* 555-plugin-management

* 600-profiles (Activate profiles)

* 900-release

* Aggregator example (?) (the root pom in this folder!)

* Inheritance example
  - supose ?

* multimodule-deps
  Example for ordering of the reactor + inheritance

* Example with code generation
  -> sapm (antlr)

* Integration test
  - See hudson-exported also for different <type>war</type>

ToDo
----
* Check the site generation parts
  - Unit Test results?

* Create a more complex example with parent POM for 
  handling of the site generation in mvn2/mvn3


* WAR example ?
  -  supose-war (simple wepapp with dependencies in relationship with a multimodule build)
  -  which one? Take a look into the Maven book ? Waether Example

* maven-changelog-plugin
  - changes.xml  (sapm)

* CIEnv example
  - hudson-piling
    - example for multiple executions
  - dependency plugin copy
  - Example for maven-rpm-plugin

* utbc (test-jar) Example

